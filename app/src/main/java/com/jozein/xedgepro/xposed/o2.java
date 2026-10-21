package com.jozein.xedgepro.xposed;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.Toast;
import de.robv.android.xposed.XposedHelpers;
import java.io.File;
import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes.dex */
class o2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f195d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f196e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Constructor<?> f197f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f198a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f199b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f200c = false;

    static {
        String str = p2.z + "miui_fling_fix";
        f195d = str;
        f196e = new File(str).exists() ? 1 : -1;
        f197f = null;
    }

    o2(Context context, int i) {
        this.f198a = context;
        this.f199b = i;
    }

    private boolean d(Throwable th) {
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            if ("com.miui.internal.dynamicanimation.animation.DynamicAnimation".equals(stackTraceElement.getClassName())) {
                return true;
            }
        }
        return false;
    }

    void a(AbsListView absListView) {
        if (f196e != 1) {
            return;
        }
        try {
            if (f197f == null) {
                Constructor<?> declaredConstructor = XposedHelpers.findClass(AbsListView.class.getName() + "$FlingRunnable", AbsListView.class.getClassLoader()).getDeclaredConstructor(AbsListView.class);
                f197f = declaredConstructor;
                declaredConstructor.setAccessible(true);
            }
            XposedHelpers.setObjectField(absListView, "mFlingRunnable", f197f.newInstance(absListView));
        } catch (Throwable th) {
            f196e = 0;
            pkg_f.v.d(th);
        }
    }

    void b(Throwable th) {
        if (this.f200c) {
            return;
        }
        this.f200c = true;
        if (f196e == -1) {
            if (d(th)) {
                f196e = 1;
                pkg_f.v.c("MIUI system bug has occurred! Try to fix it.");
                Toast.makeText(this.f198a, "MIUI system bug has occurred! Try to fix it.", 1).show();
                try {
                    new File(f195d).createNewFile();
                } catch (Throwable th2) {
                    pkg_f.v.d(th2);
                }
            } else {
                f196e = 0;
            }
        }
        t3.n().t(this.f199b);
    }

    boolean c() {
        return this.f200c;
    }
}
