package com.jozein.xedgepro.xposed;

import android.app.ActivityManager;
import android.app.TaskInfo;
import android.os.Build;
import de.robv.android.xposed.XposedHelpers;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
class f3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Field f111a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Field f112b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Method f113c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Field f114d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Field f115e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Method f116f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static Field f117g;

    static int a(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (Build.VERSION.SDK_INT >= 29 && v.f242f) {
            try {
                if (f111a == null) {
                    f111a = XposedHelpers.findField(TaskInfo.class, "displayId");
                }
                int i = f111a.getInt(runningTaskInfo);
                if (i == -1) {
                    return 0;
                }
                return i;
            } catch (Throwable th) {
                pkg_f.v.d(th);
            }
        }
        return 0;
    }

    static int b(ActivityManager.RunningTaskInfo runningTaskInfo) {
        try {
            if (f117g == null) {
                f117g = XposedHelpers.findField(ActivityManager.RunningTaskInfo.class, "stackId");
            }
            return f117g.getInt(runningTaskInfo);
        } catch (Throwable th) {
            p2.sneakyThrow(th);
            return 0;
        }
    }

    static int c(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                if (f112b == null) {
                    f112b = XposedHelpers.findField(TaskInfo.class, "userId");
                }
                return f112b.getInt(runningTaskInfo);
            } catch (Throwable th) {
                pkg_f.v.d(th);
            }
        }
        return pkg_f.o0.d();
    }

    static int d(ActivityManager.RunningTaskInfo runningTaskInfo) {
        try {
            int i = Build.VERSION.SDK_INT;
            if (i >= 31) {
                if (f113c == null) {
                    f113c = XposedHelpers.findMethodExact(TaskInfo.class, "getWindowingMode", new Class[0]);
                }
                return ((Integer) f113c.invoke(runningTaskInfo, new Object[0])).intValue();
            }
            if (i >= 28) {
                if (f114d == null) {
                    f114d = XposedHelpers.findField(ActivityManager.RunningTaskInfo.class, "configuration");
                }
                if (f115e == null) {
                    f115e = XposedHelpers.findField(f114d.getType(), "windowConfiguration");
                }
                if (f116f == null) {
                    f116f = XposedHelpers.findMethodExact(f115e.getType(), "getWindowingMode", new Class[0]);
                }
                return ((Integer) f116f.invoke(f115e.get(f114d.get(runningTaskInfo)), new Object[0])).intValue();
            }
            return 0;
        } catch (Throwable th) {
            pkg_f.v.d(th);
            return 0;
        }
    }

    static boolean e(ActivityManager.RunningTaskInfo runningTaskInfo) {
        try {
            int i = Build.VERSION.SDK_INT;
            if (i >= 28) {
                return d(runningTaskInfo) == 2;
            }
            return i >= 24 && b(runningTaskInfo) == 4;
        } catch (Throwable th) {
            pkg_f.v.c(th.toString());
        }
        return false;
    }

    static boolean f(ActivityManager.RunningTaskInfo runningTaskInfo) {
        try {
            int i = Build.VERSION.SDK_INT;
            if (i < 28) {
                return i >= 24 && b(runningTaskInfo) == 3;
            }
            int iD = d(runningTaskInfo);
            if (i >= 33) {
                return iD == 6;
            }
            return iD == 3 || iD == 4;
        } catch (Throwable th) {
            pkg_f.v.c(th.toString());
        }
        return false;
    }
}
