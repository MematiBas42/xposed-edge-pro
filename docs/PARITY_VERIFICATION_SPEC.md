# Xposed Edge Pro — Açık Kaynak Yeniden İnşa ve Küresel Parite Şartnamesi (Global Matching Decompilation & Open-Source Specification)

---

## 1. Misyon, Vizyon ve Kutsal Amaç

### 1.1 Projenin Tarihsel ve Topluluksal Önemi
**Xposed Edge Pro**, Android güç kullanıcıları ve açık kaynak modlama ekosistemi tarihindeki en güçlü, düşük seviyeli otomasyon, donanım tuşu yeniden eşleme (remap) ve jest kontrol motorlarından biridir. Yıllar boyunca kapalı kaynak, karartılmış (obfuscated) ve tek bir geliştiricinin kontrolünde kalmış; Android 10 sonrasında geliştiricisi tarafından resmi olarak terk edilmiştir.

Bu projenin nihai ve kutsal amacı; **münferit bir cihazdaki tekil bir hatayı yamamak değil**, bu efsanevi yazılımı karartılmış ikili kalıntılarından kurtararak:
1. **Modern Android Ekosistemine (Android 14 / 15 / 16 / 17 / SDK 35+) Tam Uyumlu**,
2. **222 Sınıf ve 3.328 Metodun Tamamında %99.9 Davranışsal ve Bayt Kodu Paritesine Sahip**,
3. **Android Studio ve Gradle ile Tek Komutla Derlenebilen (`BUILD SUCCESSFUL`)**,
4. **Harici Hiçbir Kapalı Kaynak Binary Blob Barındırmayan**,
5. **Açık Kaynak Android ve Xposed/LSPosed Topluluğunun Gelecek On Yıllarda da Yaşatabileceği Tertemiz Bir Açık Kaynak Gradle Projesi Olarak Yeniden Doğurmaktır.**

---

## 2. Temel Teorem: Decompiler Asimetrisi ve "Hakem" Zorunluluğu

### 2.1 Kalemle Resim Çizmek vs. Hakemli Eşleme
Kaynak kodun Dalvik bayt koduna (`DEX`) derlenmesi tek yönlü ve bilgi kaybettiren (lossy) bir işlemdir. Akademik araştırmaların (IEEE SCAM 2019, KTH Diva) ortaya koyduğu gerçek şudur:
> *"Hiçbir decompiler (JADX, CFR, Procyon, Fernflower) karmaşık ve karartılmış bir bayt kodunu hatasız olarak yüksek seviyeli dile çeviremez. Decompiler'lar atlama etiketlerini (`goto`, `switch`) tahmin ederken sıkıştıklarında kodları koşul bloklarının dışına fırlatır veya hayali sonsuz döngüler uydurur."*

Projenin derlenmesi (`BUILD SUCCESSFUL`), kodun doğru çalıştığı anlamına gelmez. Kodun orijinaliyle birebir aynı olduğunun kanıtlanması için **bütünsel bir mekanik hakeme (Global Automated Referee)** ihtiyaç vardır. 

Bu hakem olmadan yapılan her geliştirme "kalemle resim çizmek" ve projeyi içinden çıkılmaz bir "bilişsel borca" (cognitive debt) sürüklemektir. Biz bu projede resmi kalemle çizmeyecek, hakem eşliğinde fotoğrafın piksel piksel aynısını inşa edeceğiz.

---

## 3. Küresel Kapsam: 5 Alt Sistem Mimarisi (Whole-Project Architecture)

Proje kapsamı yalnızca dokunma motoruyla sınırlı değildir. Kod tabanındaki **222 sınıfın ve 3.328 metodun tamamı**, üstlendikleri mimari görevlere göre 5 ana alt sisteme ayrılmıştır:

```
┌────────────────────────────────────────────────────────────────────────┐
│                        XPOSED EDGE PRO REPO                            │
│                  (222 Sınıf / 3.328 Metot / SDK 35)                    │
└──────────────────────────────────┬─────────────────────────────────────┘
                                   │
         ┌─────────────────────────┼─────────────────────────┐
         │                         │                         │
         ▼                         ▼                         ▼
┌──────────────────┐      ┌──────────────────┐      ┌──────────────────┐
│  ALT SİSTEM 1:   │      │  ALT SİSTEM 2:   │      │  ALT SİSTEM 3:   │
│ Kancalar & Girdi │      │ Donanım Tuşları  │      │  Otomasyon ve    │
│  (Xposed Core)   │      │ ve Eylem Motoru  │      │  Tetikleyiciler  │
│    (52 Sınıf)    │      │    (38 Sınıf)    │      │    (42 Sınıf)    │
└──────────────────┘      └──────────────────┘      └──────────────────┘
         │                         │                         │
         └─────────────────────────┼─────────────────────────┘
                                   │
         ┌─────────────────────────┴─────────────────────────┐
         │                                                   │
         ▼                                                   ▼
┌──────────────────┐                                ┌──────────────────┐
│  ALT SİSTEM 4:   │                                │  ALT SİSTEM 5:   │
│  IPC & Servisler │                                │ Arayüz & Ayarlar │
│    (Root Köprü)  │                                │  (UI & Storage)  │
│    (28 Sınıf)    │                                │    (62 Sınıf)    │
└──────────────────┘                                └──────────────────┘
```

### Alt Sistem 1: Sistem Kancaları ve Dokunmatik Filtresi (Core Hooks & Input)
- **Paket:** `com.jozein.xedgepro.xposed.*` (52 sınıf)
- **Rol:** `system_server` (Root) sürecinde Zygote kancası (`HookMain`), dokunmatik filtreleme (`y0`), jest durum makinesi (`b0`, `z`) ve girdi olaylarının yönetimi.
- **Hedef Parite:** **%100 CFG ve Invoke İzomorfizmi.**

### Alt Sistem 2: Donanım Tuşları ve Eylem Yürütücü (Hardware Keys & Actions)
- **Sınıflar:** `w1`, `c`, `t2`, `u3`, `i3`, `f3`, `j2` vb.
- **Rol:** Ses tuşları, güç tuşu, Bixby, gezinme çubuğu kancaları, uygulama başlatıcılar, parlaklık/ses sürgüleri, bildirim genişletme, ekran görüntüsü alma (Android 16+ uyarlamaları dahil).
- **Hedef Parite:** **%100 Çağrı Sırası ve IPC Komut Eşliği.**

### Alt Sistem 3: Otomasyon ve Sistem Tetikleyicileri (Automation & Triggers)
- **Paketler:** `pkg_a.*`, `pkg_c.*`
- **Rol:** Wi-Fi, Bluetooth, şarj durumu, ekran açık/kapalı, uygulama odak değişimleri, konum ve zamanlayıcı tabanlı otomasyon durum makineleri.
- **Hedef Parite:** **%100 Broadcast Receiver ve State Machine Paritesi.**

### Alt Sistem 4: Güvenli IPC ve Servis Köprüsü (Root IPC Bridge)
- **Sınıflar:** `BinderService`, `pkg_a.g`, `pkg_a.v`, `service.*`
- **Rol:** Kullanıcı uygulaması ile `system_server` arasındaki çift yönlü AIDL arayüzü (`com.jozein.xedgepro.b`). Yetki denetimi ve veri senkronizasyonu.
- **Hedef Parite:** **%100 AIDL Transact/Reply İmzası ve IPC Bütünlüğü.**

### Alt Sistem 5: Kullanıcı Arayüzü, Tercihler ve Ayarlar (UI & Storage Engine)
- **Paketler:** `ui.*`, `SuperTileService`, diyaloglar ve adaptörler.
- **Rol:** XML layoutlarının şişirilmesi, SharedPreferences/JSON/SQLite veritabanı okuma-yazma, yedekleme/geri yükleme ve Quick Settings karoları.
- **Hedef Parite:** **Kusursuz Arayüz Çizimi, Sıfır SQLite Bozulması.**

---

## 4. Küresel Hakem Motoru Mimarisi (`scripts/verify_project_parity.py`)

Projenin tamamını denetleyecek olan hakem motoru, insan gözünden bağımsız, deterministik bir AST ve bayt kodu karşılaştırıcısıdır:

```
[Orijinal APK (edge.apk / baksmali)]    [Bizim Derlediğimiz APK (release / baksmali)]
                  │                                            │
                  └──────────────────────┬─────────────────────┘
                                         ▼
                 ┌──────────────────────────────────────────────┐
                 │        verify_project_parity.py MOTORU       │
                 │                                              │
                 │  1. Sınıf & Metot Haritalama (3.328 Metot)   │
                 │  2. İmza Doğrulama (Descriptors)             │
                 │  3. Invoke Sırası Eşleme (Levenshtein Diff)  │
                 │  4. CFG & Dallanma İzomorfizmi (Basic Blocks)│
                 │  5. Saha Değişim Analizi (iget / iput)       │
                 └──────────────────────┬───────────────────────┘
                                        │
                                        ▼
                 ┌──────────────────────────────────────────────┐
                 │       KÜRESEL RAPOR VE PARİTE MATRİSİ        │
                 │                                              │
                 │  Toplam Metot: 3.328                         │
                 │  %100 Eşleşen: 3.130 (%94.0)                 │
                 │  Dallanma Sapması: 142 Metot                 │
                 │  Sıra Sapması: 56 Metot                      │
                 │  Eksik / Fazla Invoke: 0                     │
                 └──────────────────────────────────────────────┘
```

### Karşılaştırma Katmanları:
1. **İmza Seviyesi (Signature Matching):** Metot erişim belirteçleri (`public`, `protected`, `static`), parametre deskriptörleri ve dönüş tipleri.
2. **Çağrı Sırası Seviyesi (Invoke Sequence Matching):** Metot içerisindeki tüm `invoke-virtual`, `invoke-direct`, `invoke-static`, `invoke-interface` komutlarının hedef sınıf, metot ve parametre sırası.
3. **Topoloji Seviyesi (CFG Topology Matching):** Metottaki `if-*`, `goto`, `packed-switch`, `sparse-switch` dallanmalarının oluşturduğu yönlü kontrol akışı grafının düğüm ($V$) ve kenar ($E$) sayıları.
4. **Semantik Gürültü Filtresi (Normalization):** Derleyici sürümü farkından kaynaklanan `.line` satır numaraları ve sanal register adları (`v0` vs `v1`) filtrelenir; yalnızca operasyonun kendisi değerlendirilir.

---

## 5. Beş Kademeli Doğrulama Hiyerarşisi

Herhangi bir alt sistemdeki kod, şu 5 filtreden geçmeden doğrulanmış kabul edilmez:

```
[Level 1: Statik İmza ve Deskriptör Eşliği]
                  │ (Geçti)
                  ▼
[Level 2: Metot Çağrı Sırası Eşliği (Invoke Parity)]
                  │ (Geçti)
                  ▼
[Level 3: CFG Topolojisi ve Temel Blok İzomorfizmi]
                  │ (Geçti)
                  ▼
[Level 4: Deterministik JUnit & JVM Test Süiti]
                  │ (Geçti)
                  ▼
[Level 5: Fiziksel Donanım (Redmi Note 8, Android 17) Canlı Testi]
```

---

## 6. Kronolojik Uygulama ve İlerleme Planı (Milestones)

### Faz 1: Hakem Motorunun ve İlk Küresel Röntgenin Çıkarılması
- `scripts/verify_project_parity.py` motoru yazılır.
- 222 sınıf ve 3.328 metodun tamamı taranır.
- 5 alt sistemin her biri için mevcut parite yüzdeleri ve hata matrisi bir JSON/Markdown raporu olarak üretilir.

### Faz 2: Alt Sistem 1 — Çekirdek Sistem Kancalarının Onarımı
- `b0.java` (`d` ve `T` metotları) orijinal `b0.smali` kontrol akışına göre düzeltilir; normal dokunmalarda `T()` çağrısı engellenir.
- `y0.java` (`U`, `filterInputEvent`, `j0`) metotları Smali akışıyla eşitlenir.
- `HookMain.java` Zygote kancası doğrulanır.
- Alt Sistem 1 parite oranı **%99.8 üzerine** çıkarılır.

### Faz 3: Alt Sistem 2 ve 3 — Tuşlar, Eylemler ve Otomasyonun Onarımı
- `w1.java` içindeki ses, ekran görüntüsü ve parlaklık eylemleri taranır.
- `pkg_a.*` ve `pkg_c.*` içindeki durum makineleri ve receiver yapıları doğrulanır.
- Alt Sistem 2 ve 3 parite oranı **%99.5 üzerine** çıkarılır.

### Faz 4: Alt Sistem 4 ve 5 — IPC, UI ve Depolama Entegrasyonu
- AIDL stub ve proxy sınıfları (`pkg_a.g`, `BinderService`) taranır.
- UI aktiviteleri ve SharedPreferences/SQLite adaptörleri taranır.
- Tüm projenin küresel parite skoru **%99.9 üzerine** çıkarılır.

### Faz 5: Sürekli Entegrasyon (CI) ve Açık Kaynak Dağıtımı
- GitHub Actions CI iş akışı eklenir (`.github/workflows/parity_ci.yml`): Her commit'te `assembleRelease` + `verify_project_parity.py` koşarak parite skorunun düşmesini engeller.
- Orijinal 8 adet Android 16 cerrahi yamasının kod tabanında korunduğu belgelenir.
- Temiz, modern, anlaşılır bir `README.md` ve derleme rehberi hazırlanır.
- Redmi Note 8 (Android 17) üzerinde canlı video/log doğrulaması tamamlanır.

---

## 7. Katı Başarı Kriterleri (Definition of Done)

Projenin tamamlanmış sayılması için aşağıdaki 6 kriterin **istisnasız tamamı** sağlanmalıdır:

1. **Küresel Parite Skoru:** 222 sınıf ve 3.328 metodun tamamında, `verify_project_parity.py` denetiminde küresel parite skoru **%99.0 veya üzeri**, Alt Sistem 1 (Xposed Core) için ise **%99.9** olacaktır.
2. **Kritik Metot Eşyapılığı:** `b0.d`, `b0.T`, `y0.U`, `HookMain.initZygote`, `w1.w4` metotları orijinal Smali ile **%100 izomorfik** olacaktır.
3. **Müstakil ve Modern Derleme:** Sıfır harici bağımlılıkla, modern Android SDK 35 ve Java 17 kullanılarak `./gradlew assembleRelease` komutuyla hatasız APK üretilecektir.
4. **Birim Testleri:** Projedeki tüm JUnit testleri (`./gradlew testDebugUnitTest`) sıfır hatayla yeşil olacaktır.
5. **Fiziksel Cihaz Canlı Başarısı:**
   - Redmi Note 8 (Android 17 / HyperOS / AOSP) üzerinde kilit ekranı parmakla kaydırılarak açılacaktır.
   - Ana ekranda ve tüm uygulamalarda dokunmatik, jestler ve donanım tuşları kusursuz çalışacaktır.
   - `dumpsys input` çıktısında sıfır `Stuck Pointer` kalacaktır.
   - `logcat` üzerinde sıfır `RemoteException: Injection did not succeed` hatası görülecektir.
6. **Topluluk Hazırlığı:** Depo, dünyadaki herhangi bir Android geliştiricisinin `git clone` yapıp doğrudan geliştirmeye devam edebileceği açık kaynak standartlarında teslim edilecektir.
