package com.jozein.xedgepro.xposed;

import android.content.Intent;
import android.os.Build;
import de.robv.android.xposed.XposedHelpers;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Field f84a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f85b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Field f86c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Field f87d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Field f88e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Field f89f;

    static Object a(Object obj) {
        if (f89f == null) {
            f89f = XposedHelpers.findField(obj.getClass(), Build.VERSION.SDK_INT >= 33 ? "token" : "appToken");
        }
        return f89f.get(obj);
    }

    static int b(Object obj) {
        if (Build.VERSION.SDK_INT >= 26 && v.f242f) {
            try {
                if (f85b == null) {
                    f85b = p2.b(obj.getClass(), "getDisplayId", new Class[0]);
                }
                int iIntValue = ((Integer) f85b.invoke(obj, new Object[0])).intValue();
                if (iIntValue == -1) {
                    return 0;
                }
                return iIntValue;
            } catch (Throwable th) {
                f.v.d(th);
            }
        }
        return 0;
    }

    static Intent c(Object obj) {
        if (f87d == null) {
            f87d = XposedHelpers.findField(obj.getClass(), "intent");
        }
        return (Intent) f87d.get(obj);
    }

    static String d(Object obj) {
        try {
            if (f88e == null) {
                f88e = XposedHelpers.findField(obj.getClass(), "packageName");
            }
            return (String) f88e.get(obj);
        } catch (Throwable th) {
            f.v.d(th);
            try {
                return f.c0.f(c(obj));
            } catch (Throwable th2) {
                f.v.d(th2);
                return null;
            }
        }
    }

    private static Object e(Object obj) {
        try {
            if (f86c == null) {
                f86c = XposedHelpers.findField(obj.getClass(), "task");
            }
            return f86c.get(obj);
        } catch (Throwable th) {
            f.v.d(th);
            return null;
        }
    }

    static int f(Object obj) {
        return p3.d(e(obj));
    }

    static int g(Object obj) {
        try {
            if (f84a == null) {
                f84a = XposedHelpers.findField(obj.getClass(), Build.VERSION.SDK_INT > 28 ? "mUserId" : "userId");
            }
            return f84a.getInt(obj);
        } catch (Throwable th) {
            f.v.d(th);
            return 0;
        }
    }
}
