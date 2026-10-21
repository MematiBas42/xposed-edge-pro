package com.jozein.xedgepro.xposed;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/* JADX INFO: loaded from: classes.dex */
class l2 extends p2 {
    static final float E;

    class a extends XC_MethodHook {
        a() {
        }

        View a(Object obj) {
            return (View) XposedHelpers.getObjectField(XposedHelpers.getSurroundingThis(obj), "mView");
        }

        /* JADX WARN: Code duplicated, block: B:12:0x0031  */
        protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            boolean z;
            Object[] objArr = methodHookParam.args;
            if (objArr[0] instanceof MotionEvent) {
                MotionEvent motionEvent = (MotionEvent) objArr[0];
                if (motionEvent.getActionMasked() == 8 && motionEvent.getAxisValue(9) == l2.E) {
                    try {
                        View viewA = a(methodHookParam.thisObject);
                        if (viewA != null) {
                            l2.this.l(viewA, motionEvent);
                        }
                    } catch (Throwable unused) {
                    }
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            if (z) {
                XposedHelpers.callMethod(methodHookParam.thisObject, "finishInputEvent", new Object[]{methodHookParam.args[0], Boolean.TRUE});
                methodHookParam.setResult((Object) null);
            }
        }
    }

    class b extends pkg_f.g0 {
        final /* synthetic */ q3.a D;
        final /* synthetic */ int E;
        final /* synthetic */ int F;
        final /* synthetic */ Context G;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Handler handler, int i, long j, q3.a aVar, int i2, int i3, Context context) {
            super(handler, i, j);
            this.D = aVar;
            this.E = i2;
            this.F = i3;
            this.G = context;
        }

        @Override // pkg_f.g0
        protected boolean a(int i) {
            if (!this.D.C.isAttachedToWindow()) {
                return false;
            }
            q3.a aVarG = q3.g(this.D, this.E, this.F);
            if (aVarG == null) {
                l2.this.o(this.G, null);
            } else {
                if (aVarG.C != null) {
                    return true;
                }
                l2.this.o(this.G, aVarG.A);
            }
            return false;
        }

        @Override // pkg_f.g0
        protected void b() {
            l2.m(this.G, false);
        }
    }

    static {
        E = pkg_f.l.CONST_IS_PRO ? 1.2321E-6f : 1.234321E-6f;
    }

    l2(ClassLoader classLoader) {
        try {
            XposedBridge.hookAllMethods(XposedHelpers.findClass("android.view.ViewRootImpl$WindowInputEventReceiver", classLoader), "onInputEvent", new a());
        } catch (Throwable th) {
            p2.g(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(final View view, MotionEvent motionEvent) {
        final int rawX = (int) motionEvent.getRawX();
        final int rawY = (int) motionEvent.getRawY();
        view.post(new Runnable() { // from class: com.jozein.xedgepro.xposed.k2
            @Override // java.lang.Runnable
            public final void run() {
                n(view, rawX, rawY);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void m(Context context, boolean z) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        if (accessibilityManager.isEnabled() == z) {
            return;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            XposedHelpers.setBooleanField(accessibilityManager, "mIsEnabled", z);
            XposedHelpers.callMethod(accessibilityManager, "notifyAccessibilityStateChanged", new Object[0]);
        } else if (i >= 21) {
            XposedHelpers.setBooleanField(accessibilityManager, "mIsEnabled", z);
            XposedHelpers.callMethod(accessibilityManager, "handleNotifyAccessibilityStateChanged", new Object[0]);
        } else {
            synchronized (XposedHelpers.getObjectField(accessibilityManager, "mHandler")) {
                XposedHelpers.setBooleanField(accessibilityManager, "mIsEnabled", z);
                XposedHelpers.callMethod(accessibilityManager, "notifyAccessibilityStateChangedLh", new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(View view, int i, int i2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i3 = i - iArr[0];
        int i4 = i2 - iArr[1];
        Context context = view.getContext();
        q3.a aVarE = q3.e(view, i3, i4);
        if (aVarE != null) {
            CharSequence charSequence = aVarE.A;
            if (charSequence != null) {
                o(context, charSequence);
                return;
            }
            if (aVarE.C != null) {
                AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
                try {
                    if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
                        m(context, true);
                        new b(pkg_f.h0.d(), 3, 150L, aVarE, i, i2, context).e();
                        return;
                    } else {
                        q3.a aVarG = q3.g(aVarE, i, i2);
                        if (aVarG != null) {
                            o(context, aVarG.A);
                            return;
                        }
                    }
                } catch (Throwable th) {
                    pkg_f.v.d(th);
                }
            }
        }
        o(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(Context context, CharSequence charSequence) {
        pkg_a.b.s0.G(context, 20, q3.c(charSequence));
    }
}
