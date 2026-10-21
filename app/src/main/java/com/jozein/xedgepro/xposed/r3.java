package com.jozein.xedgepro.xposed;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import de.robv.android.xposed.XposedHelpers;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
class r3 extends g {
    private final Object G;
    private final pkg_a.p H;
    private final Vibrator I;
    private final AudioAttributes J;
    private Method K;
    private Method L;

    r3(Object obj, Class<?> cls, Context context, pkg_a.p pVar) {
        super(cls);
        this.K = null;
        this.L = null;
        this.G = obj;
        this.H = pVar;
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        if (vibrator == null || !vibrator.hasVibrator()) {
            this.I = null;
            this.J = null;
            pkg_f.v.c("No vibrator.");
        } else {
            this.I = vibrator;
            if (Build.VERSION.SDK_INT >= 21) {
                this.J = new AudioAttributes.Builder().setContentType(4).setUsage(13).build();
            } else {
                this.J = null;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    private boolean r(int i) {
        int i2 = 4;
        if (i != 3) {
            if (i != 4) {
                if (i != 5) {
                    i2 = 3;
                } else {
                    i2 = 0;
                }
            } else if (Build.VERSION.SDK_INT < 21) {
                i2 = 3;
            }
        } else if (Build.VERSION.SDK_INT >= 28) {
            i2 = 17;
        } else {
            i2 = 3;
        }
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                if (this.K == null) {
                    this.K = j("performHapticFeedback", Integer.TYPE, Boolean.TYPE, String.class);
                }
                return ((Boolean) this.K.invoke(this.G, Integer.valueOf(i2), Boolean.TRUE, pkg_f.l.CONST_APP_TAG)).booleanValue();
            }
            if (this.K == null) {
                this.K = i("performHapticFeedbackLw");
            }
            return ((Boolean) this.K.invoke(this.G, null, Integer.valueOf(i2), Boolean.TRUE)).booleanValue();
        } catch (Throwable th) {
            pkg_f.v.d(th);
            return false;
        }
    }

    private void u(int i) {
        int i2 = 20;
        if (i != 3 && i != 4 && i == 5) {
            i2 = 50;
        }
        t(i2);
    }

    private void v() {
        int iV = this.H.v(6, 50);
        if (iV > 100) {
            iV = 100;
        }
        t(iV);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x000b  */
    private void w(int i) {
        int i2;
        VibrationEffect vibrationEffectCreatePredefined;
        if (i == 3) {
            i2 = 1;
        } else if (i != 4) {
            i2 = 5;
            if (i != 5 || Build.VERSION.SDK_INT < 28) {
                i2 = 0;
            }
        } else if (Build.VERSION.SDK_INT >= 27) {
            i2 = 2;
        } else {
            i2 = 0;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            vibrationEffectCreatePredefined = VibrationEffect.createPredefined(i2);
        } else {
            if (this.L == null) {
                this.L = XposedHelpers.findMethodExact(VibrationEffect.class, "get", new Class[]{Integer.TYPE});
            }
            try {
                vibrationEffectCreatePredefined = (VibrationEffect) this.L.invoke(null, Integer.valueOf(i2));
            } catch (Throwable th) {
                pkg_f.v.d(th);
                return;
            }
        }
        this.I.vibrate(vibrationEffectCreatePredefined, this.J);
    }

    void s() {
        int iV;
        if (this.I == null || (iV = this.H.v(34, 0)) == 0) {
            return;
        }
        if (iV == 1) {
            v();
        } else {
            if (r(iV)) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    w(iV);
                    return;
                } catch (Throwable unused) {
                }
            }
            u(iV);
        }
    }

    void t(int i) {
        Vibrator vibrator = this.I;
        if (vibrator == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(i, -1), this.J);
        } else {
            vibrator.vibrate(i);
        }
    }
}
