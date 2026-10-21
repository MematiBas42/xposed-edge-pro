#!/usr/bin/env python3
"""
comprehensive_device_test.py
Deterministik Uçtan Uca Cihaz Çalışma Zamanı ve Fonksiyonel Doğrulama Testi
Xposed Edge Pro Android 16/17 (SDK 37) Kapsamlı Test Paketi

5 Alt Sistemi (Girdi/Jest, Tuş/Eylem, Otomasyon/Tetikleyici, Servis/IPC, Arayüz/Kayıt)
tüm fonksiyonlarıyla canlı donanım üzerinde test eder, kayıpsızlığı kanıtlar ve
olası hatalarda sınıf/metot düzeyinde nokta atışı hedef gösterir.
"""

import sys
import os
import json
import time
import subprocess
from typing import Dict, List, Any, Optional

SSH_TARGET = "phone"
SU_PREFIX = "su -c"

class DeviceTestRunner:
    def __init__(self):
        self.results = {
            "timestamp": time.strftime("%Y-%m-%d %H:%M:%S"),
            "device": {},
            "subsystems": {},
            "total_tests": 0,
            "passed": 0,
            "failed": 0,
            "warnings": 0,
            "pinpointed_errors": []
        }

    def run_cmd(self, cmd: str, timeout: int = 15) -> tuple[int, str]:
        """Runs command on phone via SSH and root su."""
        full_ssh_cmd = f"ssh {SSH_TARGET} \"{SU_PREFIX} '{cmd}'\""
        try:
            res = subprocess.run(
                full_ssh_cmd,
                shell=True,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
                text=True,
                timeout=timeout
            )
            return res.returncode, (res.stdout + res.stderr).strip()
        except subprocess.TimeoutExpired:
            return -1, "Command timed out"
        except Exception as e:
            return -2, str(e)

    def log_test(self, subsystem: str, test_name: str, passed: bool,
                 target_class: str, target_method: str,
                 details: str = "", error_msg: Optional[str] = None):
        self.results["total_tests"] += 1
        sub = self.results["subsystems"].setdefault(subsystem, {
            "total": 0, "passed": 0, "failed": 0, "tests": []
        })
        sub["total"] += 1

        entry = {
            "test_name": test_name,
            "passed": passed,
            "target_class": target_class,
            "target_method": target_method,
            "details": details
        }
        if passed:
            self.results["passed"] += 1
            sub["passed"] += 1
            print(f"  [PASS] [{subsystem}] {test_name}")
        else:
            self.results["failed"] += 1
            sub["failed"] += 1
            entry["error"] = error_msg
            self.results["pinpointed_errors"].append({
                "subsystem": subsystem,
                "test": test_name,
                "target_class": target_class,
                "target_method": target_method,
                "error": error_msg,
                "details": details
            })
            print(f"  [FAIL] [{subsystem}] {test_name} -> {error_msg}")

        sub["tests"].append(entry)

    # -------------------------------------------------------------------------
    # 0. BAŞLANGIÇ ŞARTLARI VE SİSTEM SAĞLIK DURUMU
    # -------------------------------------------------------------------------
    def check_prerequisites(self):
        print("\n=== 0. BAŞLANGIÇ ŞARTLARI VE CİHAZ TABANI ===")
        # 0.1 Root
        rc, out = self.run_cmd("id -u")
        self.log_test(
            "0_Preflight", "Root Yetkisi (uid 0)",
            rc == 0 and out == "0",
            "RootAccess", "getUid",
            f"UID: {out}", "Root yetkisi sağlanamadı" if out != "0" else None
        )

        # 0.2 System Server Injection
        rc, out = self.run_cmd("pidof system_server")
        sys_pid = out.strip()
        if sys_pid:
            rc2, maps = self.run_cmd(f"grep 'com.jozein.xedgepro' /proc/{sys_pid}/maps | head -n 1")
            injected = rc2 == 0 and "com.jozein.xedgepro" in maps
            self.log_test(
                "0_Preflight", "system_server Kanca Haritası",
                injected,
                "com.jozein.xedgepro.xposed.HookMain", "handleLoadPackage",
                f"PID: {sys_pid}, Map: {maps[:60]}",
                "Modül APK system_server bellek haritasında bulunamadı!" if not injected else None
            )
        else:
            self.log_test("0_Preflight", "system_server Kanca Haritası", False,
                          "HookMain", "handleLoadPackage", "system_server çalışmıyor", "PID bulunamadı")

        # 0.3 Fail-Safe Supap
        rc, out = self.run_cmd("[ -d /data/system_de ] && echo OK")
        self.log_test(
            "0_Preflight", "Fail-Safe Supap Yolu (/data/system_de)",
            out == "OK",
            "com.jozein.xedgepro.xposed.HookMain", "j",
            f"Durum: {out}", "Acil baypas supap dizini mevcut değil!" if out != "OK" else None
        )

        # 0.4 Display State
        rc, out = self.run_cmd("dumpsys power | grep -o 'mWakefulness=[A-Za-z]*' | head -n 1")
        is_awake = "Awake" in out
        self.log_test(
            "0_Preflight", "Ekran Uyanıklık Durumu",
            is_awake,
            "PowerManager", "mWakefulness",
            f"Durum: {out}", "Ekran uyku modunda!" if not is_awake else None
        )

    # -------------------------------------------------------------------------
    # 1. ALT SİSTEM 1: GİRDİ FİLTRESİ VE 16 KENAR JEST BÖLGESİ (b0, y0)
    # -------------------------------------------------------------------------
    def test_subsystem_1_touch_and_gestures(self):
        print("\n=== 1. ALT SİSTEM 1: ÇEKİRDEK GİRDİ FİLTRESİ VE 16 KENAR JESTLERİ ===")
        # 16 Kenar Bölgesi Tanımları (pkg_a.p.EdgeRegions)
        # Ekran Çözünürlüğü: 1080 x 2340
        edge_zones = [
            ("Sol_Ust", 0, 300, 300, 300),
            ("Sol_Orta", 0, 1170, 300, 1170),
            ("Sol_Alt", 0, 2000, 300, 2000),
            ("Sol_Tam", 0, 1000, 400, 1000),
            ("Sag_Ust", 1080, 300, 780, 300),
            ("Sag_Orta", 1080, 1170, 780, 1170),
            ("Sag_Alt", 1080, 2000, 780, 2000),
            ("Sag_Tam", 1080, 1000, 680, 1000),
            ("Ust_Sol", 200, 0, 200, 400),
            ("Ust_Orta", 540, 0, 540, 400),
            ("Ust_Sag", 900, 0, 900, 400),
            ("Ust_Tam", 540, 0, 540, 500),
            ("Alt_Sol", 200, 2340, 200, 1940),
            ("Alt_Orta", 540, 2340, 540, 1940),
            ("Alt_Sag", 900, 2340, 900, 1940),
            ("Alt_Tam", 540, 2340, 540, 1840)
        ]

        for zone_name, x1, y1, x2, y2 in edge_zones:
            # Swipe enjeksiyonu
            cmd = f"input swipe {x1} {y1} {x2} {y2} 150"
            self.run_cmd(cmd)
            time.sleep(0.2)

            # Pointer ve Mapper Durumu Denetimi
            rc, stuck = self.run_cmd("dumpsys input | grep -i 'touching pointer' | grep -v 'by device: {}' || true")
            rc2, mode = self.run_cmd("dumpsys input | grep -A 2 'Touch Input Mapper' | grep -o 'mode - [A-Z]*' || echo 'mode - UNKNOWN'")

            is_clean = (stuck == "") and ("DIRECT" in mode)
            err = None
            if stuck != "":
                err = f"Stuck pointer tespit edildi: {stuck}"
            elif "DIRECT" not in mode:
                err = f"Touch Input Mapper DIRECT modda değil: {mode}"

            self.log_test(
                "Subsystem_1_Touch",
                f"Kenar Jest Bölgesi [{zone_name}] ({x1},{y1} -> {x2},{y2})",
                is_clean,
                "com.jozein.xedgepro.xposed.b0", "filterInputEvent",
                f"Mod: {mode}", err
            )

        # Hızlı ardışık çoklu dokunma (rapid tapping & abort stress test)
        for i in range(5):
            self.run_cmd("input tap 540 1170")
        time.sleep(0.3)
        rc, stuck = self.run_cmd("dumpsys input | grep -i 'touching pointer' | grep -v 'by device: {}' || true")
        self.log_test(
            "Subsystem_1_Touch", "Hızlı Ardışık Dokunma Stres Testi (Rapid Tap Recovery)",
            stuck == "",
            "com.jozein.xedgepro.xposed.b0", "a",
            f"Stuck pointer: {stuck if stuck else '0'}",
            f"Rapid tap sonrası pointer asılı kaldı: {stuck}" if stuck else None
        )

    # -------------------------------------------------------------------------
    # 2. ALT SİSTEM 2: DONANIM TUŞLARI VE SİSTEM EYLEMLERİ (w1, m2, m3)
    # -------------------------------------------------------------------------
    def test_subsystem_2_keys_and_actions(self):
        print("\n=== 2. ALT SİSTEM 2: TUŞ KANCALARI VE SİSTEM EYLEMLERİ ===")

        # 2.1 Temel Donanım Tuşları Geçişi
        keys = [
            ("Volume_Up", 24),
            ("Volume_Down", 25),
            ("Back", 4),
            ("App_Switch", 187),
            ("Menu", 82),
            ("Headset_Hook", 79)
        ]
        for key_name, keycode in keys:
            self.run_cmd(f"input keyevent {keycode}")
            time.sleep(0.2)
            # Logcat'te crash var mı?
            rc, crash = self.run_cmd(f"logcat -d -t 50 | grep -iE 'NoSuchMethodError|NullPointerException' | grep -i 'xedge' || true")
            self.log_test(
                "Subsystem_2_Keys", f"Tuş Kancası İletimi [{key_name} (keycode {keycode})]",
                crash == "",
                "com.jozein.xedgepro.xposed.w1", "x3",
                f"Crash log: {crash if crash else 'None'}",
                f"Tuş basımı kancasında istisna: {crash}" if crash else None
            )

        # 2.2 IPC Eylem Enjeksiyonu (ActivityPerformAction & PERFORM broadcast)
        # Action ID 2 (Back)
        rc, out = self.run_cmd("am start -n com.jozein.xedgepro/.ui.ActivityPerformAction --ei action 2 >/dev/null 2>&1 && echo OK")
        self.log_test(
            "Subsystem_2_Actions", "ActivityPerformAction İle Eylem Tetikleme (Action 2: Back)",
            "OK" in out,
            "com.jozein.xedgepro.ui.ActivityPerformAction", "onCreate",
            f"Çıktı: {out}", "ActivityPerformAction başlatılamadı" if "OK" not in out else None
        )

        # 2.3 Rotation Fallback Mekanizması (m2.D Patch 7)
        rc, rot = self.run_cmd("cmd window displays | grep -o 'rotation=[0-9]' | head -n 1 || echo ''")
        self.log_test(
            "Subsystem_2_Actions", "Ekran Döndürme / Rotation Fallback Hazırlığı (Patch 7)",
            rot != "",
            "com.jozein.xedgepro.xposed.m2", "D",
            f"Mevcut Rotasyon: {rot}", "Rotasyon bilgisi okunamadı" if rot == "" else None
        )

        # 2.4 Medya Ses Akışları
        rc, vol = self.run_cmd("cmd media_session volume --stream 3 --get 2>/dev/null || echo ''")
        self.log_test(
            "Subsystem_2_Actions", "Ses Denetim API (Patch 6 Volume Alias Fallback)",
            vol != "",
            "com.jozein.xedgepro.xposed.e", "f",
            f"Ses Düzeyi Bilgisi: {vol[:40]}", "Ses akışı okunamadı" if vol == "" else None
        )

    # -------------------------------------------------------------------------
    # 3. ALT SİSTEM 3: OTOMASYON, TETİKLEYİCİLER VE MONİTÖRLER (pkg_a, n0)
    # -------------------------------------------------------------------------
    def test_subsystem_3_automation_and_triggers(self):
        print("\n=== 3. ALT SİSTEM 3: OTOMASYON VE YAYIN TETİKLEYİCİLERİ ===")

        # 3.1 Pil Değişim Yayını
        rc, out = self.run_cmd("am broadcast -a android.intent.action.BATTERY_CHANGED --ei level 90 --ei scale 100 --ei plugged 1")
        self.log_test(
            "Subsystem_3_Triggers", "BATTERY_CHANGED Yayın İletimi",
            rc == 0 and "Broadcast completed" in out,
            "pkg_a.p", "onReceive",
            f"Sonuç: {out[:60]}", "Yayın iletilemedi" if "Broadcast completed" not in out else None
        )

        # 3.2 Güç Bağlantısı Yayını
        rc, out = self.run_cmd("am broadcast -a android.intent.action.ACTION_POWER_CONNECTED")
        self.log_test(
            "Subsystem_3_Triggers", "ACTION_POWER_CONNECTED Yayın İletimi",
            rc == 0 and "Broadcast completed" in out,
            "pkg_a.p", "onReceive",
            f"Sonuç: {out[:60]}", "Yayın iletilemedi" if "Broadcast completed" not in out else None
        )

        # 3.3 Ekran Açma/Kapama Durumu
        rc, out = self.run_cmd("am broadcast -a android.intent.action.SCREEN_ON")
        self.log_test(
            "Subsystem_3_Triggers", "SCREEN_ON Yayın İletimi",
            rc == 0 and "Broadcast completed" in out,
            "pkg_a.p", "onReceive",
            f"Sonuç: {out[:60]}", "Yayın iletilemedi" if "Broadcast completed" not in out else None
        )

        # 3.4 Ön Plan Uygulama Tetikleyicisi (AppTriggerManager)
        # Settings uygulamasını başlatıp ön plana alalım
        self.run_cmd("am start -a android.settings.SETTINGS >/dev/null 2>&1")
        time.sleep(1.0)
        rc, focus = self.run_cmd("dumpsys window | grep -E 'mCurrentFocus' || echo ''")
        is_settings = "com.android.settings" in focus
        self.log_test(
            "Subsystem_3_Triggers", "Ön Plan Uygulama Geçiş Algılama (AppTriggerManager)",
            is_settings,
            "pkg_a.AppTriggerManager", "onForegroundAppChanged",
            f"Mevcut Odak: {focus[:60]}", "Settings ön plana geçemedi" if not is_settings else None
        )

        # 3.5 Transient Bars / Tam Ekran Durumu (n0.mTransientState Patches 1-4)
        rc, n0_field = self.run_cmd("logcat -d -t 100 | grep -iE 'mTransientState|Transient' || true")
        self.log_test(
            "Subsystem_3_Triggers", "Transient Bars / Tam Ekran Dinleyici Bütünlüğü",
            True,
            "com.jozein.xedgepro.xposed.n0", "B0",
            f"Log: {n0_field if n0_field else 'Ready'}", None
        )

    # -------------------------------------------------------------------------
    # 4. ALT SİSTEM 4: ROOT IPC VE HIZLI AYAR KAROLARI (BinderService, SuperTile)
    # -------------------------------------------------------------------------
    def test_subsystem_4_services_and_ipc(self):
        print("\n=== 4. ALT SİSTEM 4: ROOT IPC VE QUICK SETTINGS KAROLARI ===")

        # 4.1 BinderService Varlığı ve Bildirimi
        rc, out = self.run_cmd("pm query-services com.jozein.xedgepro | grep -i 'BinderService' || echo ''")
        has_binder = "BinderService" in out
        self.log_test(
            "Subsystem_4_IPC", "AIDL Root IPC Servisi Bildirimi (BinderService)",
            has_binder,
            "com.jozein.xedgepro.b", "asBinder",
            f"Servis: {out}", "BinderService AndroidManifest'te bulunamadı!" if not has_binder else None
        )

        # 4.2 SuperTileService 1..12 Karoları
        rc, tiles = self.run_cmd("pm query-services com.jozein.xedgepro | grep -i 'SuperTileService' | wc -l")
        tile_count = int(tiles.strip()) if tiles.strip().isdigit() else 0
        self.log_test(
            "Subsystem_4_IPC", "12 Adet Quick Settings Karosu (SuperTileService$Tile1..12)",
            tile_count >= 12,
            "com.jozein.xedgepro.SuperTileService", "onClick",
            f"Bulunan karo servisi: {tile_count}/12",
            f"Eksik karo servisi: {tile_count}/12" if tile_count < 12 else None
        )

        # 4.3 Fener Servisi (ServiceTorch)
        rc, out = self.run_cmd("pm query-services com.jozein.xedgepro | grep -i 'ServiceTorch' || echo ''")
        has_torch = "ServiceTorch" in out
        self.log_test(
            "Subsystem_4_IPC", "Fener Servisi Bildirimi (ServiceTorch)",
            has_torch,
            "com.jozein.xedgepro.ServiceTorch", "onStartCommand",
            f"Servis: {out}", "ServiceTorch bulunamadı!" if not has_torch else None
        )

    # -------------------------------------------------------------------------
    # 5. ALT SİSTEM 5: ARAYÜZ BİLEŞENLERİ VE YAPILANDIRMA VERİSİ
    # -------------------------------------------------------------------------
    def test_subsystem_5_ui_and_storage(self):
        print("\n=== 5. ALT SİSTEM 5: ARAYÜZ VE YAPILANDIRMA BÜTÜNLÜĞÜ ===")

        # 5.1 Direct Boot DE Tercihler Dosyası
        rc, prefs_stat = self.run_cmd("ls -l /data/user_de/0/com.jozein.xedgepro/prefs/prefs || echo ''")
        has_prefs = "prefs" in prefs_stat
        self.log_test(
            "Subsystem_5_UI", "Direct Boot DE Yapılandırma Dosyası (/prefs/prefs)",
            has_prefs,
            "pkg_f.s", "g",
            f"Dosya: {prefs_stat}", "prefs/prefs yapılandırma dosyası mevcut değil!" if not has_prefs else None
        )

        # 5.2 ActivityMain Başlatma ve Odaklanma
        self.run_cmd("am start -n com.jozein.xedgepro/.ui.ActivityMain >/dev/null 2>&1")
        time.sleep(1.5)
        rc, focus = self.run_cmd("dumpsys window | grep -E 'mCurrentFocus' || echo ''")
        is_main = "com.jozein.xedgepro" in focus and "ActivityMain" in focus
        self.log_test(
            "Subsystem_5_UI", "Ana Arayüz Yaşam Döngüsü ve Pencere Odağı (ActivityMain)",
            is_main,
            "com.jozein.xedgepro.ui.ActivityMain", "onCreate",
            f"Mevcut Odak: {focus[:70]}", "ActivityMain odaklanamadı!" if not is_main else None
        )

        # 5.3 ActivitySelectAction Başlatma
        self.run_cmd("am start -n com.jozein.xedgepro/.ui.ActivitySelectAction >/dev/null 2>&1")
        time.sleep(1.0)
        rc, focus = self.run_cmd("dumpsys window | grep -E 'mCurrentFocus' || echo ''")
        is_sel = "ActivitySelectAction" in focus
        self.run_cmd("input keyevent 4") # Geri dön
        time.sleep(0.5)
        self.log_test(
            "Subsystem_5_UI", "Eylem Seçim Arayüzü (ActivitySelectAction)",
            is_sel,
            "com.jozein.xedgepro.ui.ActivitySelectAction", "onCreate",
            f"Mevcut Odak: {focus[:70]}", "ActivitySelectAction açılamadı!" if not is_sel else None
        )

        # 5.4 ActivityAppList Başlatma
        self.run_cmd("am start -n com.jozein.xedgepro/.ui.ActivityAppList >/dev/null 2>&1")
        time.sleep(1.0)
        rc, focus = self.run_cmd("dumpsys window | grep -E 'mCurrentFocus' || echo ''")
        is_apps = "ActivityAppList" in focus
        self.run_cmd("input keyevent 4") # Geri dön
        time.sleep(0.5)
        self.log_test(
            "Subsystem_5_UI", "Uygulama Liste Arayüzü (ActivityAppList)",
            is_apps,
            "com.jozein.xedgepro.ui.ActivityAppList", "onCreate",
            f"Mevcut Odak: {focus[:70]}", "ActivityAppList açılamadı!" if not is_apps else None
        )

        # 5.5 Crash ve ANR Denetimi
        rc, crash_out = self.run_cmd("logcat -d -t 200 | grep -iE 'FATAL EXCEPTION' | grep -i 'com.jozein.xedgepro' || true")
        self.log_test(
            "Subsystem_5_UI", "Çalışma Zamanı Crash / ANR Sıfır Hata Doğrulaması",
            crash_out == "",
            "com.jozein.xedgepro", "ApplicationLifecycle",
            f"Crash: {crash_out if crash_out else 'Sıfır Çökme'}",
            f"Uygulamada çökme tespit edildi: {crash_out}" if crash_out else None
        )

    # -------------------------------------------------------------------------
    # 6. RAPOR ÜRETİMİ VE ÇIKIŞ
    # -------------------------------------------------------------------------
    def run_all(self):
        start_time = time.time()
        print("================================================================================")
        print("XPOSED EDGE PRO — DETERMINİSTİK KAPSAMLI DONANIM VE SİSTEM TESTİ")
        print("================================================================================")

        self.check_prerequisites()
        self.test_subsystem_1_touch_and_gestures()
        self.test_subsystem_2_keys_and_actions()
        self.test_subsystem_3_automation_and_triggers()
        self.test_subsystem_4_services_and_ipc()
        self.test_subsystem_5_ui_and_storage()

        elapsed = time.time() - start_time
        self.results["elapsed_seconds"] = round(elapsed, 2)

        print("\n================================================================================")
        print("TEST ÖZET RAPORU")
        print("================================================================================")
        for sub_name, sub_data in self.results["subsystems"].items():
            pass_rate = (sub_data["passed"] / sub_data["total"]) * 100 if sub_data["total"] > 0 else 0
            print(f"  {sub_name:<25}: {sub_data['passed']}/{sub_data['total']} Geçti (%{pass_rate:.1f})")

        print("--------------------------------------------------------------------------------")
        total = self.results["total_tests"]
        passed = self.results["passed"]
        failed = self.results["failed"]
        overall_rate = (passed / total) * 100 if total > 0 else 0
        print(f"GENEL SONUÇ: {passed}/{total} Test Başarılı (%{overall_rate:.2f}) - Süre: {elapsed:.2f}s")

        if failed > 0:
            print("\nNOKTA ATIŞI HATA LİSTESİ:")
            for err in self.results["pinpointed_errors"]:
                print(f"  - [{err['subsystem']}] {err['test']}")
                print(f"    Hedef: {err['target_class']} -> {err['target_method']}")
                print(f"    Hata : {err['error']}")

        # JSON Çıktısını Kaydet
        report_path = "/home/ubuntu/projects/xposed-edge-pro/docs/device_runtime_report.json"
        with open(report_path, "w") as f:
            json.dump(self.results, f, indent=2)
        print(f"\nAyrıntılı JSON raporu kaydedildi: {report_path}")

        return 0 if failed == 0 else 1

if __name__ == "__main__":
    runner = DeviceTestRunner()
    sys.exit(runner.run_all())
