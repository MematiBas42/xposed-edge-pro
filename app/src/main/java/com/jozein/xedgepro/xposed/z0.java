package com.jozein.xedgepro.xposed;

import android.app.Application;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/* JADX INFO: loaded from: classes.dex */
class z0 extends p2 {

    class a extends XC_MethodHook {
        a() {
        }

        protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            try {
                z0.this.h((Application) methodHookParam.thisObject);
            } catch (Throwable th) {
                p2.g(th);
            }
        }
    }

    z0(ClassLoader classLoader) {
        if (pkg_f.o0.m() != 0 || pkg_f.f0.u(classLoader)) {
            return;
        }
        try {
            XposedHelpers.findAndHookMethod(Application.class, "onCreate", new Object[]{new a()});
        } catch (Throwable th) {
            pkg_f.v.d(th);
        }
    }

    void h(Application application) {
        Application.OnProvideAssistDataListener onProvideAssistDataListenerP = pkg_f.f0.p();
        if (onProvideAssistDataListenerP != null) {
            application.registerOnProvideAssistDataListener(onProvideAssistDataListenerP);
            application.unregisterOnProvideAssistDataListener(onProvideAssistDataListenerP);
        }
    }
}
