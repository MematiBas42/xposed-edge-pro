package com.jozein.xedgepro.xposed;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
class n0 extends j2 {
    private static final String h0;
    private static final String i0;
    private static final boolean j0;
    private final w1 H;
    private final m2 I;
    private final n3 J;
    private Handler K;
    private boolean L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private int T;
    private int U;
    private int V;
    private int W;
    private volatile boolean X;
    private int Y;
    private boolean Z;
    private boolean a0;
    private int b0;
    private int c0;
    private int d0;
    private Field e0;
    private int f0;
    private final Runnable g0;
    public boolean mTransientState;

    class a extends XC_MethodHook {
        a() {
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            n0.this.I.A(methodHookParam.thisObject);
            if (n0.this.Z) {
                return;
            }
            n0.this.A0(n0.this.c0());
        }
    }

    class b extends XC_MethodHook {
        b() {
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            n0.this.I.A(methodHookParam.thisObject);
            if (n0.this.Z && ((Boolean) methodHookParam.getResult()).booleanValue() && ((Integer) methodHookParam.args[1]).intValue() == 0) {
                n0.this.A0(n0.this.c0());
            }
        }
    }

    class c extends XC_MethodHook {
        c() {
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            n0.this.I.A(methodHookParam.thisObject);
            if (n0.this.Z && ((Boolean) methodHookParam.getResult()).booleanValue() && ((Integer) methodHookParam.args[0]).intValue() == 0) {
                n0.this.A0(n0.this.c0());
            }
        }
    }

    class d extends XC_MethodHook {
        d() {
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            n0.this.I.A(methodHookParam.thisObject);
            n0.this.A0(XposedHelpers.getIntField(methodHookParam.thisObject, "mRotation"));
        }
    }

    class e extends XC_MethodHook {
        e() {
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            n0.this.A0(((Integer) methodHookParam.args[0]).intValue());
        }
    }

    class f extends XC_MethodHook {
        f() {
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            int[] iArr = (int[]) n0.this.u("mNavigationBarWidthForRotation");
            int[] iArr2 = (int[]) n0.this.u("mNavigationBarHeightForRotation");
            boolean z = false;
            if (!n0.this.X ? iArr2[n0.this.U] == 0 || iArr[n0.this.U] == 0 : iArr2[n0.this.T] == 0 || iArr[n0.this.T] == 0) {
                z = true;
            }
            if (n0.this.O != z) {
                n0.this.O = z;
                n0 n0Var = n0.this;
                n0Var.y0(n0Var.Q);
            }
        }
    }

    class g extends f.f0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final IBinder f169a = new w3();

        g() {
        }

        @Override // f.f0.d
        public Object a(String str, Object[] objArr) {
            str.hashCode();
            if (str.equals("asBinder")) {
                return this.f169a;
            }
            if (str.equals("onRotationChanged")) {
                n0.this.A0(((Integer) objArr[0]).intValue());
                return null;
            }
            f.v.c("IRotationWatcher." + str);
            return null;
        }
    }

    class h extends XC_MethodHook {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f171a;

        h(String str) {
            this.f171a = str;
        }

        protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            if ("qemu.hw.mainkeys".equals(methodHookParam.args[0])) {
                methodHookParam.setResult(this.f171a);
            }
        }
    }

    class i extends XC_MethodHook {
        i() {
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            int iIntValue;
            int iW;
            if ((n0.this.L || (n0.this.M && !n0.this.N)) && (iIntValue = ((Integer) methodHookParam.getResult()).intValue()) != (iW = n0.this.W(iIntValue))) {
                methodHookParam.setResult(Integer.valueOf(iW));
            }
        }
    }

    class j extends XC_MethodHook {
        j() {
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            int iIntValue;
            int iX;
            if ((n0.this.L || (n0.this.M && !n0.this.N)) && (iIntValue = ((Integer) methodHookParam.getResult()).intValue()) != (iX = n0.this.X(iIntValue))) {
                methodHookParam.setResult(Integer.valueOf(iX));
            }
        }
    }

    class k extends XC_MethodHook {
        k() {
        }

        protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            if (n0.this.L) {
                methodHookParam.setResult(Boolean.TRUE);
            }
        }
    }

    class l extends XC_MethodHook {
        l() {
        }

        protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            if (n0.this.M) {
                methodHookParam.setResult(Boolean.TRUE);
            }
        }
    }

    class m extends XC_MethodHook {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Field f177a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Field f178b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Field f179c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Field f180d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Class f181e;

        m(Class cls) {
            this.f181e = cls;
        }

        /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws IllegalAccessException {
            if (this.f177a == null) {
                this.f177a = XposedHelpers.findField(this.f181e, "mDisplayContent");
            }
            Object obj = this.f177a.get(methodHookParam.thisObject);
            if (obj != n0.this.d0().G()) {
                return;
            }
            if (this.f178b == null) {
                this.f178b = XposedHelpers.findField(this.f181e, "mSource");
            }
            Object obj2 = this.f178b.get(methodHookParam.thisObject);
            if (this.f179c == null) {
                this.f179c = XposedHelpers.findField(obj2.getClass(), "mType");
            }
            int i = this.f179c.getInt(obj2);
            if (Build.VERSION.SDK_INT >= 34) {
                if (i == 1) {
                    i = 0;
                } else if (i != 2) {
                    return;
                } else {
                    i = 1;
                }
            }
            if (this.f180d == null) {
                this.f180d = XposedHelpers.findField(obj2.getClass(), "mVisible");
            }
            boolean z = this.f180d.getBoolean(obj2);
            boolean z2 = false;
            if (z) {
                try {
                    Object objectField = XposedHelpers.getObjectField(XposedHelpers.getObjectField(obj, "mInsetsPolicy"), "mTransientControlTarget");
                    z2 = objectField != null && objectField == XposedHelpers.getObjectField(methodHookParam.thisObject, "mControlTarget");
                } catch (Throwable unused) {
                    z2 = false;
                }
            }
            n0.this.mTransientState = z2;
            if (i == 0) {
                n0.this.B0(z);
            } else if (i == 1) {
                n0.this.y0(z);
            }
        }
    }

    class n extends XC_MethodHook {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Field f183a;

        n(Field field) {
            this.f183a = field;
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            try {
                n0.this.C0((String) this.f183a.get(methodHookParam.thisObject), methodHookParam.args[0] != null);
            } catch (Throwable th) {
                f.v.d(th);
            }
        }
    }

    class o extends XC_MethodHook {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Field f185a;

        o(Field field) {
            this.f185a = field;
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            if (((Boolean) methodHookParam.getResult()).booleanValue()) {
                try {
                    n0.this.C0((String) this.f185a.get(methodHookParam.thisObject), ((Boolean) methodHookParam.args[0]).booleanValue());
                } catch (Throwable th) {
                    f.v.d(th);
                }
            }
        }
    }

    class p extends XC_MethodHook {
        p() {
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            n0.this.Z = true;
            n0.this.A0(((Integer) methodHookParam.args[0]).intValue());
        }
    }

    private class q extends XC_MethodHook {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f188a;

        private q() {
            this.f188a = -1;
        }

        /* synthetic */ q(n0 n0Var, h hVar) {
            this();
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            if (this.f188a != -1) {
                try {
                    Object objU = n0.this.u("mFocusedWindow");
                    if (objU == null) {
                        return;
                    }
                    XposedHelpers.setIntField(objU, "mSystemUiVisibility", this.f188a);
                    this.f188a = -1;
                } catch (Throwable th) {
                    f.v.d(th);
                }
            }
        }

        protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            int intField;
            int iW;
            this.f188a = -1;
            if (n0.this.L) {
                try {
                    Object objU = n0.this.u("mFocusedWindow");
                    if (objU == null || (intField = XposedHelpers.getIntField(objU, "mSystemUiVisibility")) == (iW = n0.this.W(intField))) {
                        return;
                    }
                    XposedHelpers.setIntField(objU, "mSystemUiVisibility", iW);
                    this.f188a = intField;
                } catch (Throwable th) {
                    f.v.d(th);
                    this.f188a = -1;
                }
            }
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        h0 = i2 >= 24 ? "mNavigationBarWidthForRotationDefault" : "mNavigationBarWidthForRotation";
        i0 = i2 >= 24 ? "mNavigationBarHeightForRotationDefault" : "mNavigationBarHeightForRotation";
        j0 = i2 >= 30;
    }

    /* JADX WARN: Code duplicated, block: B:74:0x01d7 A[Catch: all -> 0x01ea, TRY_LEAVE, TryCatch #5 {all -> 0x01ea, blocks: (B:72:0x01d3, B:74:0x01d7), top: B:88:0x01d3 }] */
    /* JADX WARN: Code duplicated, block: B:94:? A[RETURN, SYNTHETIC] */
    n0(Class<?> cls, ClassLoader classLoader, w1 w1Var, a.p pVar) {
        Class clsFindClass;
        super(cls);
        this.J = new n3();
        this.L = false;
        this.M = false;
        this.N = Build.VERSION.SDK_INT < 30;
        this.O = false;
        this.P = true;
        this.Q = false;
        this.R = false;
        this.S = false;
        this.T = 0;
        this.U = 1;
        this.V = 2;
        this.W = 3;
        this.X = false;
        this.Y = 0;
        this.Z = false;
        this.a0 = false;
        h hVar = null;
        this.e0 = null;
        this.f0 = -1;
        this.g0 = new Runnable() { // from class: com.jozein.xedgepro.xposed.m0
            @Override // java.lang.Runnable
            public final void run() {
                this.z.m0();
            }
        };
        this.H = w1Var;
        this.I = new m2(classLoader, w1Var);
        int iA0 = a0(pVar);
        if (iA0 == 1 || iA0 == 2) {
            try {
                XposedHelpers.findAndHookMethod(o3.f201a, "get", new Object[]{String.class, new h(iA0 != 1 ? "0" : "1")});
            } catch (Throwable th) {
                p2.g(th);
            }
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 30) {
            try {
                if (i2 >= 21) {
                    try {
                        clsFindClass = XposedHelpers.findClass("android.view.WindowManagerPolicyControl", classLoader);
                        this.N = false;
                    } catch (Throwable unused) {
                        int i3 = Build.VERSION.SDK_INT;
                        Class clsFindClass2 = XposedHelpers.findClass(i3 < 23 ? "com.android.internal.policy.impl.PolicyControl" : i3 <= 28 ? "com.android.server.policy.PolicyControl" : "com.android.server.wm.PolicyControl", classLoader);
                        if (i3 >= 24) {
                            this.N = false;
                        }
                        clsFindClass = clsFindClass2;
                    }
                    XposedBridge.hookAllMethods(clsFindClass, "getSystemUiVisibility", new i());
                    XposedBridge.hookAllMethods(clsFindClass, "getWindowFlags", new j());
                } else {
                    try {
                        p("expandedDesktopHidesStatusBar", new k());
                        p("expandedDesktopHidesNavigationBar", new l());
                        this.N = false;
                    } catch (Throwable unused2) {
                        p("updateSystemUiVisibilityLw", new q(this, hVar));
                        o("layoutWindowLw", new q(this, hVar));
                    }
                }
            } catch (Throwable th2) {
                p2.g(th2);
            }
        }
        try {
            if (!j0) {
                int i4 = Build.VERSION.SDK_INT;
                Class clsFindClass3 = XposedHelpers.findClass(i4 < 23 ? "com.android.internal.policy.impl.BarController" : i4 <= 28 ? "com.android.server.policy.BarController" : "com.android.server.wm.BarController", classLoader);
                Field fieldFindField = XposedHelpers.findField(clsFindClass3, "mTag");
                fieldFindField.setAccessible(true);
                XposedBridge.hookAllMethods(clsFindClass3, "setWindow", new n(fieldFindField));
                XposedHelpers.findAndHookMethod(clsFindClass3, "setBarShowingLw", new Object[]{Boolean.TYPE, new o(fieldFindField)});
            } else if (Build.VERSION.SDK_INT >= 30) {
                Class clsFindClass4 = XposedHelpers.findClass("com.android.server.wm.InsetsSourceProvider", classLoader);
                XposedBridge.hookAllMethods(clsFindClass4, "updateVisibility", new m(clsFindClass4));
            }
        } catch (Throwable th3) {
            p2.g(th3);
        }
        int i5 = Build.VERSION.SDK_INT;
        try {
            try {
                if (i5 >= 26) {
                    if (i5 <= 27) {
                        Class cls2 = Integer.TYPE;
                        p("setRotationLw", cls2, new p());
                        m2 m2Var = this.I;
                        Class cls3 = Boolean.TYPE;
                        m2Var.p("updateRotationUnchecked", cls3, cls3, new a());
                        if (i5 <= 27) {
                            this.I.p("updateOrientationFromAppTokensLocked", cls3, cls2, new b());
                        } else {
                            this.I.p("updateOrientationFromAppTokensLocked", cls2, cls3, new c());
                        }
                    }
                    if (Build.VERSION.SDK_INT < 24) {
                        p("updateRotation", Boolean.TYPE, new f());
                    }
                }
                try {
                    this.I.p("updateRotationUncheckedLocked", Boolean.TYPE, new d());
                } catch (Throwable th4) {
                    p2.g(th4);
                    p("setRotationLw", Integer.TYPE, new e());
                }
                if (Build.VERSION.SDK_INT < 24) {
                    p("updateRotation", Boolean.TYPE, new f());
                }
            } catch (Throwable th5) {
                p2.g(th5);
            }
        } catch (Throwable th6) {
            p2.g(th6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A0(int i2) {
        this.Y = i2;
        boolean z = i2 == this.U || i2 == this.W;
        if (this.X != z) {
            this.X = z;
            this.H.j3(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B0(boolean z) {
        if (this.P != z) {
            this.P = z;
            this.H.o3(z);
        }
        boolean z2 = this.P;
        if (z2 && this.mTransientState && this.S) {
            z2 = false;
        }
        boolean z3 = this.R;
        if (z3 && this.mTransientState && this.S) {
            z3 = false;
        }
        boolean z4 = (z3 || z2) ? false : true;
        if (this.S != z4) {
            this.S = z4;
            this.H.g3(z4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C0(String str, boolean z) {
        if (str.endsWith("StatusBar")) {
            B0(z);
            return;
        }
        if (str.endsWith("NavigationBar")) {
            y0(z);
            return;
        }
        f.v.c("Unknown tag: " + str);
    }

    private void V() {
        try {
            this.I.z("watchRotation", new g(), 0);
        } catch (Throwable th) {
            f.v.d(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int W(int i2) {
        if (this.L) {
            if (Build.VERSION.SDK_INT >= 21) {
                i2 = (i2 | 4096) & (-1073741825);
            }
            i2 = (i2 | 1028) & (-257);
        }
        if (!this.M || this.N) {
            return i2;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            i2 = (i2 | 4096) & Integer.MAX_VALUE;
        }
        return (i2 | 514) & (-257);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int X(int i2) {
        if (this.L) {
            i2 = (i2 | 1024) & (-67110913);
        }
        return (!this.M || this.N) ? i2 : i2 & (-134217729);
    }

    private int a0(a.p pVar) {
        return pVar.J() ? pVar.u(14) : this.J.a(0);
    }

    private Resources b0() {
        Context contextT1 = this.H.T1();
        return contextT1 != null ? contextT1.getResources() : Resources.getSystem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c0() {
        try {
            int iO = d0().O();
            this.Y = iO;
            return iO;
        } catch (Throwable unused) {
            return this.Y;
        }
    }

    private void f0() {
        try {
            Resources resourcesB0 = b0();
            int identifier = resourcesB0.getIdentifier("navigation_bar_width", "dimen", "android");
            int identifier2 = resourcesB0.getIdentifier("navigation_bar_height", "dimen", "android");
            int identifier3 = resourcesB0.getIdentifier("navigation_bar_height_landscape", "dimen", "android");
            this.b0 = (int) resourcesB0.getDimension(identifier);
            this.c0 = (int) resourcesB0.getDimension(identifier2);
            this.d0 = (int) resourcesB0.getDimension(identifier3);
        } catch (Throwable th) {
            f.v.d(th);
        }
    }

    private void g0() {
        f0();
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                Resources resourcesB0 = b0();
                int identifier = resourcesB0.getIdentifier("config_reverseDefaultRotation", "boolean", "android");
                boolean z = identifier > 0 && resourcesB0.getBoolean(identifier);
                Point pointI = this.I.I(new Point());
                if (pointI.x > pointI.y) {
                    this.U = 0;
                    this.W = 2;
                    if (z) {
                        this.T = 1;
                        this.V = 3;
                    } else {
                        this.T = 3;
                        this.V = 1;
                    }
                } else {
                    this.T = 0;
                    this.V = 2;
                    if (z) {
                        this.U = 3;
                        this.W = 1;
                    } else {
                        this.U = 1;
                        this.W = 3;
                    }
                }
            } else {
                int iT = t("mPortraitRotation");
                int iT2 = t("mLandscapeRotation");
                if (iT == 0 && iT2 == 0) {
                    return;
                }
                this.T = iT;
                this.U = iT2;
                this.V = t("mUpsideDownRotation");
                int iT3 = t("mSeascapeRotation");
                this.W = iT3;
                this.X = this.U == 0 || iT3 == 0;
            }
            this.a0 = true;
        } catch (Throwable th) {
            f.v.d(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m0() {
        int i2 = this.f0;
        this.f0 = -1;
        if (i2 < 0 || i2 == c0()) {
            return;
        }
        try {
            d0().D(-1);
        } catch (Throwable th) {
            f.v.d(th);
        }
    }

    private void s0(Object obj, boolean z) {
        if (obj == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 31) {
            XposedHelpers.callMethod(obj, z ? "showLw" : "hideLw", new Object[]{Boolean.TRUE});
            z0();
        } else {
            String str = z ? "show" : "hide";
            Boolean bool = Boolean.TRUE;
            XposedHelpers.callMethod(obj, str, new Object[]{bool, bool});
        }
    }

    private void u0(boolean z, boolean z2) {
        s0(Y(z), !z2);
    }

    private void w0(int i2, int i3, int i4) {
        int[] iArr;
        Object objU;
        if (Build.VERSION.SDK_INT >= 29) {
            Object objH = d0().H();
            iArr = (int[]) XposedHelpers.getObjectField(objH, h0);
            objU = XposedHelpers.getObjectField(objH, i0);
        } else {
            iArr = (int[]) u(h0);
            objU = u(i0);
        }
        int[] iArr2 = (int[]) objU;
        int i5 = this.T;
        if (iArr2[i5] == i3) {
            int i6 = this.U;
            if (iArr2[i6] == i4 && iArr[i5] == i2 && iArr[i6] == i2) {
                return;
            }
        }
        int i7 = this.V;
        iArr2[i7] = i3;
        iArr2[i5] = i3;
        int i8 = this.U;
        int i9 = this.W;
        iArr2[i9] = i4;
        iArr2[i8] = i4;
        iArr[i9] = i2;
        iArr[i8] = i2;
        iArr[i7] = i2;
        iArr[i5] = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y0(boolean z) {
        this.Q = z;
        boolean z2 = z && !this.O;
        if (this.R != z2) {
            this.R = z2;
            this.H.i3(z2);
        }
        boolean z3 = this.P;
        if (z3 && this.mTransientState && this.S) {
            z3 = false;
        }
        boolean z4 = this.R;
        if (z4 && this.mTransientState && this.S) {
            z4 = false;
        }
        boolean z5 = (z4 || z3) ? false : true;
        if (this.S != z5) {
            this.S = z5;
            this.H.g3(z5);
        }
    }

    Object Y(boolean z) {
        Object objH = d0().H();
        String str = z ? "mStatusBar" : "mNavigationBar";
        Object objectField = XposedHelpers.getObjectField(objH, str);
        if (objectField == null) {
            f.v.c(str + " not exists.");
        }
        return objectField;
    }

    Point Z() {
        try {
            Object objM = d0().M();
            if (objM != null) {
                Rect rectG = u3.g(objM, new Rect());
                return new Point((rectG.left + rectG.right) / 2, (rectG.top + rectG.bottom) / 2);
            }
        } catch (Throwable th) {
            f.v.d(th);
        }
        Point pointI = this.I.I(new Point());
        pointI.x /= 2;
        pointI.y /= 2;
        return pointI;
    }

    m2 d0() {
        return this.I;
    }

    void e0(Handler handler, Object obj) {
        this.K = handler;
        try {
            if (this.c0 == 0) {
                f0();
            }
            this.I.A(obj);
        } catch (Throwable th) {
            f.v.d(th);
        }
    }

    boolean h0() {
        return this.M;
    }

    boolean i0() {
        return this.L;
    }

    boolean j0() {
        return this.X;
    }

    boolean k0() {
        return this.R;
    }

    boolean l0() {
        return this.P;
    }

    void n0() {
        if (this.K == null) {
            this.K = this.H.U1();
        }
        this.I.U();
    }

    void o0() {
        g0();
    }

    void p0() {
        if (!this.a0) {
            g0();
        }
        if (Build.VERSION.SDK_INT > 27 || f.p0.p() != 1) {
            V();
        }
    }

    boolean q0() {
        try {
            if (Build.VERSION.SDK_INT < 29) {
                r("requestTransientBars", u("mStatusBar"));
                return true;
            }
        } catch (Throwable th) {
            f.v.d(th);
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:15:0x0021  */
    void r0(int i2) {
        int i3;
        int i4;
        int iC0 = c0();
        switch (i2) {
            case 1:
                i3 = this.T;
                break;
            case 2:
                i3 = this.U;
                break;
            case 3:
                i3 = this.V;
                break;
            case 4:
                i3 = this.W;
                break;
            case 5:
                i4 = iC0 + 1;
                i3 = i4 & 3;
                break;
            case 6:
                i4 = iC0 + 2;
                i3 = i4 & 3;
                break;
            case 7:
                i4 = iC0 + 3;
                i3 = i4 & 3;
                break;
            default:
                i3 = this.T;
                if (iC0 == i3 || iC0 == this.V) {
                    i3 = this.U;
                }
                break;
        }
        d0().D(i3);
        this.K.removeCallbacks(this.g0);
        this.f0 = i3;
        this.K.postDelayed(this.g0, 500L);
    }

    void t0(boolean z) {
        if (this.N) {
            try {
                if (z) {
                    w0(0, 0, 0);
                } else {
                    w0(this.b0, this.c0, this.d0);
                }
            } catch (Throwable th) {
                f.v.d(th);
            }
        }
        this.M = z;
        if (j0) {
            u0(false, z);
        } else {
            z0();
        }
    }

    void v0(boolean z) {
        this.L = z;
        if (j0) {
            u0(true, z);
        } else {
            z0();
        }
    }

    @Override // com.jozein.xedgepro.xposed.j2
    protected Object x() {
        return this.H.v();
    }

    void x0() {
        int iU = this.H.b2().u(14);
        if (iU != this.J.a(0)) {
            this.J.c(0, iU);
        }
    }

    void z0() {
        r("updateRotation", Boolean.FALSE);
    }
}
