package com.jozein.xedgepro;

import pkg_a.e;
import pkg_a.AppTriggerManager;
import pkg_a.j;
import pkg_a.k;
import pkg_a.m;
import pkg_a.s;
import pkg_a.u;
import pkg_a.w;
import pkg_a.x;
import pkg_a.z;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import com.jozein.xedgepro.ui.ActivityMain;
import pkg_f.f0;
import pkg_f.h0;
import pkg_f.l;
import pkg_f.l0;
import pkg_f.p0;
import pkg_f.q;
import pkg_f.q0;
import pkg_f.v;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationMain extends Application implements l {
    private static boolean B;
    private static boolean C;
    private static String D;
    private static String E;
    private List<z.b> A = null;
    private z z;

    class a implements z.b {
        a() {
        }

        @Override // pkg_a.z.b
        public void a(boolean z) {
            boolean unused = ApplicationMain.B = true;
            if (ApplicationMain.this.A != null) {
                Iterator it = ApplicationMain.this.A.iterator();
                while (it.hasNext()) {
                    ((z.b) it.next()).a(z);
                }
            }
        }

        @Override // pkg_a.z.b
        public void b(boolean z) {
            boolean unused = ApplicationMain.B = true;
            if (ApplicationMain.this.A != null) {
                Iterator it = ApplicationMain.this.A.iterator();
                while (it.hasNext()) {
                    ((z.b) it.next()).b(z);
                }
            }
        }
    }

    class b implements Application.ActivityLifecycleCallbacks {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void b() {
            AppTriggerManager.g();
            pkg_a.s.f.h();
            pkg_a.b.f0.A();
            pkg_a.b.k2.A();
            e.A();
            pkg_a.b.v0.d();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if ((activity instanceof ActivityMain) && activity.isFinishing()) {
                try {
                    ApplicationMain.this.unregisterActivityLifecycleCallbacks(this);
                } catch (Throwable th) {
                    pkg_f.v.d(th);
                }
                h0.a().post(new Runnable() { // from class: com.jozein.xedgepro.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        ApplicationMain.b.b();
                    }
                });
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    class c extends BroadcastReceiver {
        c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean unused = ApplicationMain.B = true;
            if (intent != null) {
                try {
                    int[] intArrayExtra = intent.getIntArrayExtra("alias");
                    if (intArrayExtra != null) {
                        q0.e(intArrayExtra);
                    }
                    String unused2 = ApplicationMain.D = intent.getStringExtra("build_time");
                    String unused3 = ApplicationMain.E = intent.getStringExtra("time");
                    if (intent.getBooleanExtra("send_files", false)) {
                        pkg_a.j.h(context);
                    }
                    ApplicationMain.this.unregisterReceiver(this);
                } catch (Throwable th) {
                    pkg_f.v.d(th);
                }
            }
        }
    }

    private ShortcutInfo f(String str, pkg_a.b bVar, int i, int i2) {
        Intent intentK = l0.k(bVar);
        CharSequence charSequenceN = bVar.n(this);
        return new ShortcutInfo.Builder(this, str).setIntent(intentK).setLongLabel(charSequenceN).setShortLabel(charSequenceN).setIcon(Icon.createWithResource(this, i)).setRank(i2).build();
    }

    public static void g() {
        B = true;
    }

    public static String i() {
        return E;
    }

    public static boolean isModuleActivated() {
        if (Build.VERSION.SDK_INT < 26 && !B && !C) {
            C = true;
            B = p();
        }
        return B;
    }

    public static String j() {
        return D;
    }

    private void k() {
        boolean zE0 = z.e0();
        z.w0();
        if (zE0) {
            pkg_a.b.s0.B(this, 25);
        }
        pkg_a.b.v0.a();
        z zVar = new z();
        this.z = zVar;
        pkg_a.w.a(zVar, this);
        registerActivityLifecycleCallbacks(new b());
    }

    public static boolean l() {
        return isModuleActivated() || p0.p() != 0;
    }

    private void m() {
        registerReceiver(new c(), new IntentFilter(pkg_a.b.s0.N), 2);
        pkg_a.b.s0.B(this, 17);
    }

    private static boolean p() {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        try {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            Field declaredField = Class.forName("de.robv.android.xposed.XposedBridge", false, systemClassLoader).getDeclaredField("sHookedMethodCallbacks");
            declaredField.setAccessible(true);
            Map map = (Map) declaredField.get(null);
            Field declaredField2 = Class.forName("de.robv.android.xposed.XposedBridge$CopyOnWriteSortedSet", false, systemClassLoader).getDeclaredField("elements");
            declaredField2.setAccessible(true);
            String str = pkg_f.l.CONST_PKG + '.';
            if (map != null) {
                synchronized (map) {
                    Iterator it = map.values().iterator();
                    while (it.hasNext()) {
                        for (Object obj : (Object[]) declaredField2.get(it.next())) {
                            if (obj.getClass().getName().startsWith(str)) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            pkg_f.v.d(th);
        }
        return false;
    }

    private void q() {
        ShortcutManager shortcutManager;
        if (Build.VERSION.SDK_INT >= 25 && (shortcutManager = (ShortcutManager) getSystemService("shortcut")) != null) {
            List<ShortcutInfo> dynamicShortcuts = shortcutManager.getDynamicShortcuts();
            if (dynamicShortcuts.size() > 0) {
                ShortcutInfo shortcutInfo = dynamicShortcuts.get(0);
                String id = shortcutInfo.getId();
                CharSequence shortLabel = shortcutInfo.getShortLabel();
                if ("gesture_control".equals(id) && new pkg_a.b.p0(0).n(this).equals(shortLabel)) {
                    return;
                }
                if ("key_control".equals(id) && new pkg_a.b.e1(0).n(this).equals(shortLabel)) {
                    return;
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(f("gesture_control", new pkg_a.b.p0(0), R.drawable.ic_shortcut_toggle_gesture, 1));
            arrayList.add(f("key_control", new pkg_a.b.e1(0), R.drawable.ic_shortcut_toggle_key, 0));
            shortcutManager.addDynamicShortcuts(arrayList);
        }
    }

    public void e(z.b bVar) {
        if (bVar == null) {
            return;
        }
        if (this.A == null) {
            this.A = new ArrayList();
        }
        this.A.add(bVar);
    }

    public z h() {
        return this.z;
    }

    public void n() {
        pkg_f.q.b();
        if (Build.VERSION.SDK_INT >= 24) {
            pkg_a.u.j();
        }
        pkg_a.m.c();
        e.D();
        pkg_a.x.l();
        pkg_a.b.v0.f();
        this.z.g();
        this.z.L();
        pkg_a.w.a(this.z, this);
        pkg_a.v.VarManager.h();
        pkg_a.k.k();
        AppTriggerManager.l();
        pkg_a.s.f.k();
        pkg_a.b.f0.B();
        pkg_a.b.k2.B();
        pkg_a.b.y0.K();
        pkg_f.v.c("Settings reloaded.");
    }

    public void o(z.b bVar) {
        this.A.remove(bVar);
    }

    @Override // android.app.Application
    public void onCreate() {
        k();
        pkg_f.z.K(this);
        super.onCreate();
        this.z.y0(this, new a());
        m();
        try {
            q();
        } catch (Throwable th) {
            pkg_f.v.d(th);
        }
    }

    @Override // android.app.Application
    public void registerOnProvideAssistDataListener(Application.OnProvideAssistDataListener onProvideAssistDataListener) {
        f0.r(onProvideAssistDataListener);
        super.registerOnProvideAssistDataListener(onProvideAssistDataListener);
    }
}
