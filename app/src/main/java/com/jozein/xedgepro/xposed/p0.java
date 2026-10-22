package com.jozein.xedgepro.xposed;

import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class p0 extends j2 {
    private final w1 H;
    private boolean I;
    private Object J;
    private Object K;

    class a extends XC_MethodHook {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f203a;

        a() {
            int i = Build.VERSION.SDK_INT;
            this.f203a = (i < 26 || i > 28) ? 0 : 2;
        }

        protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
            p0.this.A(methodHookParam.thisObject);
            if (methodHookParam.args != null && methodHookParam.args.length > this.f203a && (methodHookParam.args[this.f203a] instanceof Integer)) {
                boolean z = (((Integer) methodHookParam.args[this.f203a]).intValue() & 2) != 0;
                if (z != p0.this.I) {
                    p0.this.I = z;
                    p0.this.H.n3(z);
                }
            }
        }
    }

    private static class b extends NullPointerException {
        b() {
        }
    }

    p0(ClassLoader classLoader, w1 w1Var) {
        super(Build.VERSION.SDK_INT >= 29 ? "com.android.server.inputmethod.InputMethodManagerService" : "com.android.server.InputMethodManagerService", classLoader);
        this.I = false;
        this.K = null;
        this.H = w1Var;
        try {
            o("setImeWindowStatusLocked", new a());
        } catch (Throwable th) {
            p2.g(th);
        }
    }

    private Object getUserDataCompat() {
        try {
            int userId = pkg_f.o0.d();
            return XposedHelpers.callMethod(v(), "getUserData", new Object[]{Integer.valueOf(userId)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private IBinder getFocusedWindowToken() {
        for (String fieldName : new String[]{"mLastImeTargetWindow", "mCurFocusedWindow"}) {
            try {
                Object window = u(fieldName);
                if (window instanceof IBinder) {
                    return (IBinder) window;
                }
            } catch (Throwable unused) {
            }
        }
        try {
            Object userData = getUserDataCompat();
            if (userData != null) {
                Object bindingState = XposedHelpers.getObjectField(userData, "mImeBindingState");
                if (bindingState != null) {
                    Object window = XposedHelpers.getObjectField(bindingState, "mFocusedWindow");
                    if (window instanceof IBinder) {
                        return (IBinder) window;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    private Object F() {
        try {
            Object ctx = u("mCurInputContext");
            if (ctx != null) {
                return ctx;
            }
        } catch (Throwable unused) {
        }
        try {
            Object userData = getUserDataCompat();
            if (userData != null) {
                return XposedHelpers.getObjectField(userData, "mCurInputConnection");
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    private String G() {
        if (Build.VERSION.SDK_INT >= 33) {
            try {
                return (String) r("getSelectedMethodIdLocked", new Object[0]);
            } catch (Throwable unused) {
            }
            try {
                Object userData = getUserDataCompat();
                if (userData != null) {
                    Object bindingController = XposedHelpers.getObjectField(userData, "mBindingController");
                    if (bindingController != null) {
                        return (String) XposedHelpers.callMethod(bindingController, "getSelectedMethodId", new Object[0]);
                    }
                }
            } catch (Throwable unused) {
            }
        }
        try {
            return (String) u("mCurMethodId");
        } catch (Throwable unused) {
        }
        return null;
    }

    private Object H() {
        if (this.K == null) {
            this.K = XposedHelpers.newInstance(XposedHelpers.findClass("com.android.internal.inputmethod.InputConnectionCommandHeader", m().getClassLoader()), new Object[]{0});
        }
        return this.K;
    }

    private Object I() {
        if (this.J == null) {
            try {
                this.J = Build.VERSION.SDK_INT >= 33 ? XposedHelpers.findClass("com.android.server.inputmethod.ImfLock", m().getClassLoader()) : u("mMethodMap");
            } catch (Throwable th) {
                Object v = v();
                this.J = v != null ? v : this;
            }
        }
        return this.J;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M() {
        try {
            XposedHelpers.callMethod(u("mMenuController"), "showInputMethodMenu", new Object[]{Boolean.TRUE, 0});
        } catch (Throwable th) {
            pkg_f.v.c(th.toString());
            Message message = new Message();
            message.what = 1;
            message.arg1 = 1;
            message.arg2 = 0;
            try {
                ((Handler.Callback) v()).handleMessage(message);
            } catch (Throwable th2) {
                pkg_f.v.d(th2);
            }
        }
    }

    private void O(String str, int i) {
        if (Build.VERSION.SDK_INT >= 34) {
            try {
                Object internal = g3.q("com.android.server.inputmethod.InputMethodManagerInternal");
                if (internal != null) {
                    int userId = pkg_f.o0.d();
                    XposedHelpers.callMethod(internal, "switchToInputMethod", new Object[]{str, Integer.valueOf(userId)});
                    return;
                }
            } catch (Throwable unused) {
            }
        }
        synchronized (I()) {
            try {
                r("setInputMethodLocked", str, Integer.valueOf(i));
            } catch (Throwable th) {
                pkg_f.v.c(th.toString());
            }
        }
    }

    void J() {
        if (Build.VERSION.SDK_INT >= 34) {
            try {
                Object internal = g3.q("com.android.server.inputmethod.InputMethodManagerInternal");
                if (internal != null) {
                    XposedHelpers.callMethod(internal, "hideInputMethod", new Object[]{3, 0});
                    return;
                }
            } catch (Throwable unused) {
            }
        }
        synchronized (I()) {
            IBinder token = getFocusedWindowToken();
            if (Build.VERSION.SDK_INT >= 30) {
                try {
                    r("hideCurrentInputLocked", token, 0, null, 3);
                } catch (Throwable th) {
                    try {
                        XposedHelpers.findMethodBestMatch(m(), "hideCurrentInputLocked", token, 0, 3).invoke(v(), token, 0, 3);
                    } catch (Throwable th2) {
                        pkg_f.v.c("hideCurrentInputLocked fallback error: " + th2);
                    }
                }
            } else {
                r("hideCurrentInputLocked", 0, null);
            }
        }
    }

    boolean K(int i) {
        try {
            Object objF = F();
            long jUptimeMillis = SystemClock.uptimeMillis();
            KeyEvent keyEvent = new KeyEvent(jUptimeMillis, jUptimeMillis, 0, i, 0, 0, -1, 0, 6);
            KeyEvent keyEvent2 = new KeyEvent(jUptimeMillis, SystemClock.uptimeMillis(), 1, i, 0, 0, -1, 0, 6);
            if (Build.VERSION.SDK_INT >= 33) {
                Object objH = H();
                p2.a(objF, "sendKeyEvent", objH, keyEvent);
                p2.a(objF, "sendKeyEvent", objH, keyEvent2);
            } else {
                p2.a(objF, "sendKeyEvent", keyEvent);
                p2.a(objF, "sendKeyEvent", keyEvent2);
            }
            return true;
        } catch (b unused) {
            return true;
        } catch (Throwable th) {
            pkg_f.v.d(th);
            return false;
        }
    }

    boolean L(String str) {
        if (str != null && str.length() != 0) {
            try {
                Object objF = F();
                if (Build.VERSION.SDK_INT >= 33) {
                    Object objH = H();
                    p2.a(objF, "finishComposingText", objH);
                    p2.a(objF, "commitText", objH, str, 1);
                } else {
                    p2.a(objF, "finishComposingText", new Object[0]);
                    p2.a(objF, "commitText", str, 1);
                }
                return true;
            } catch (b unused) {
            } catch (Throwable th) {
                pkg_f.v.d(th);
                return false;
            }
        }
        return true;
    }

    boolean N(int i) {
        try {
            Object objF = F();
            if (Build.VERSION.SDK_INT >= 33) {
                p2.a(objF, "performContextMenuAction", H(), Integer.valueOf(i));
            } else {
                p2.a(objF, "performContextMenuAction", Integer.valueOf(i));
            }
            return true;
        } catch (b unused) {
            return true;
        } catch (Throwable th) {
            pkg_f.v.d(th);
            return false;
        }
    }

    void P() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 34) {
            try {
                Object binder = g3.m();
                if (binder != null) {
                    XposedHelpers.callMethod(binder, "showInputMethodPickerFromSystem", new Object[]{0, 0});
                    return;
                }
            } catch (Throwable unused) {
            }
        }
        if (i >= 31) {
            ((Handler) u("mHandler")).post(new Runnable() { // from class: com.jozein.xedgepro.xposed.o0
                @Override // java.lang.Runnable
                public final void run() {
                    p0.this.M();
                }
            });
            return;
        }
        if (i >= 29) {
            r("showInputMethodMenu", Boolean.TRUE, 0);
        } else if (i >= 21) {
            r("showInputMethodMenu", Boolean.TRUE);
        } else {
            r("showInputMethodMenuInternal", Boolean.TRUE);
        }
    }

    void Q() {
        IBinder token = getFocusedWindowToken();
        synchronized (I()) {
            if (Build.VERSION.SDK_INT >= 30) {
                try {
                    r("showCurrentInputLocked", token, 0, null, 0);
                } catch (Throwable th) {
                    try {
                        int userId = pkg_f.o0.d();
                        XposedHelpers.callMethod(v(), "showCurrentInputLocked", new Object[]{token, 0, 0, Integer.valueOf(userId)});
                    } catch (Throwable th2) {
                        try {
                            XposedHelpers.callMethod(v(), "showCurrentInputLocked", new Object[]{token, 0, 0});
                        } catch (Throwable th3) {
                            pkg_f.v.c("showCurrentInputLocked fallback error: " + th3);
                        }
                    }
                }
            } else {
                r("showCurrentInputLocked", 0, null);
            }
        }
    }

    void R() {
        if (Build.VERSION.SDK_INT >= 34) {
            try {
                Object binder = g3.m();
                if (binder != null) {
                    XposedHelpers.callMethod(binder, "onImeSwitchButtonClickFromSystem", new Object[]{0});
                    return;
                }
            } catch (Throwable unused) {
            }
        }
        Object objU;
        List<InputMethodInfo> enabledInputMethodList = null;
        try {
            int i = Build.VERSION.SDK_INT;
            if (i >= 33) {
                objU = r("getCurTokenLocked", new Object[0]);
            } else {
                objU = i >= 29 ? u("mCurToken") : null;
            }
            if (((Boolean) r(i >= 28 ? "switchToPreviousInputMethod" : "switchToLastInputMethod", objU)).booleanValue()) {
                return;
            }
        } catch (Throwable th) {
            pkg_f.v.c(th.toString());
        }
        InputMethodManager inputMethodManager = (InputMethodManager) this.H.T1().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        int iD = pkg_f.o0.d();
        if (iD != 0) {
            try {
                enabledInputMethodList = (List) XposedHelpers.callMethod(inputMethodManager, "getEnabledInputMethodListAsUser", new Object[]{Integer.valueOf(iD)});
            } catch (Throwable th2) {
                pkg_f.v.c(th2.toString());
            }
        }
        if (enabledInputMethodList == null) {
            enabledInputMethodList = inputMethodManager.getEnabledInputMethodList();
        }
        int size = enabledInputMethodList.size();
        if (size <= 1) {
            if (size > 0) {
                return;
            }
            pkg_f.v.c("No IME.");
        } else {
            String strG = G();
            String id = enabledInputMethodList.get(0).getId();
            if (id.equals(strG)) {
                id = enabledInputMethodList.get(1).getId();
            }
            O(id, -1);
        }
    }

    @Override // com.jozein.xedgepro.xposed.j2
    void A(Object obj) {
        if (obj != null) {
            if (m().isInstance(obj)) {
                super.A(obj);
                return;
            }
            try {
                Object callback = XposedHelpers.getObjectField(obj, "mCallback");
                if (callback != null && m().isInstance(callback)) {
                    super.A(callback);
                    return;
                }
            } catch (Throwable ignored) {
            }
        }
        super.A(obj);
    }

    @Override // com.jozein.xedgepro.xposed.j2
    boolean s(Object obj) {
        if (obj != null && m().isInstance(obj)) {
            return true;
        }
        if (obj != null) {
            try {
                Object callback = XposedHelpers.getObjectField(obj, "mCallback");
                if (callback != null && m().isInstance(callback)) {
                    return true;
                }
            } catch (Throwable ignored) {
            }
        }
        return super.s(obj);
    }

    @Override // com.jozein.xedgepro.xposed.j2
    protected Object x() {
        Object imms = null;
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                Object internal = g3.q("com.android.server.inputmethod.InputMethodManagerInternal");
                if (internal != null) {
                    try {
                        imms = XposedHelpers.getObjectField(internal, "mService");
                    } catch (Throwable unused) {
                        try {
                            imms = XposedHelpers.getSurroundingThis(internal);
                        } catch (Throwable unused2) {
                            imms = null;
                        }
                    }
                }
            } catch (Throwable th) {
                pkg_f.v.c("InputMethodManagerInternal resolve error: " + th);
            }
        } else {
            try {
                imms = this.H.g2().u("mInputMethodManager");
            } catch (Throwable th) {
                pkg_f.v.c("Legacy mInputMethodManager resolve error: " + th);
            }
        }
        if (imms == null) {
            try {
                Object binder = g3.m();
                if (binder != null) {
                    if (m().isInstance(binder)) {
                        imms = binder;
                    } else {
                        try {
                            Object callback = XposedHelpers.getObjectField(binder, "mCallback");
                            if (callback != null && m().isInstance(callback)) {
                                imms = callback;
                            }
                        } catch (Throwable unused) {
                        }
                    }
                }
            } catch (Throwable th) {
                pkg_f.v.c("g3.m() binder resolve error: " + th);
            }
        }
        return imms;
    }
}
