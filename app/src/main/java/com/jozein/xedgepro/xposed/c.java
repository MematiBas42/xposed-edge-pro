package com.jozein.xedgepro.xposed;

import de.robv.android.xposed.XposedHelpers;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Field f90a;

    static int a(Object obj) throws IllegalAccessException {
        if (!v.f242f) {
            return 0;
        }
        if (f90a == null) {
            f90a = XposedHelpers.findField(obj.getClass(), "mDisplayId");
        }
        int i = f90a.getInt(obj);
        if (i == -1) {
            return 0;
        }
        return i;
    }
}
