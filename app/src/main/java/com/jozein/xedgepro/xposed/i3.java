package com.jozein.xedgepro.xposed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.telephony.TelephonyManager;

/* JADX INFO: loaded from: classes.dex */
class i3 implements a.p.g {
    private static final String[] r = {"android.intent.action.BOOT_COMPLETED", "android.intent.action.SCREEN_ON", "android.intent.action.SCREEN_OFF", "android.intent.action.USER_PRESENT", "android.intent.action.BATTERY_CHANGED", "android.intent.action.BATTERY_LOW", "android.intent.action.BATTERY_OKAY", "android.intent.action.ACTION_POWER_CONNECTED", "android.intent.action.ACTION_POWER_DISCONNECTED", "android.net.wifi.STATE_CHANGE", "android.net.conn.CONNECTIVITY_CHANGE", "android.intent.action.AIRPLANE_MODE", "android.intent.action.HEADSET_PLUG", "android.hardware.usb.action.USB_STATE", "android.intent.action.REBOOT", "android.intent.action.ACTION_SHUTDOWN", "android.intent.action.TIME_SET", "android.intent.action.TIMEZONE_CHANGED", "android.intent.action.USER_SWITCHED"};
    private static final String[] s = {"android.provider.Telephony.SMS_RECEIVED", "android.intent.action.NEW_OUTGOING_CALL", "android.intent.action.PHONE_STATE"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a.p f129c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final w1 f130d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Context f131e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f132f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f133g = 0;
    private int h = 0;
    private boolean i = true;
    private boolean j = true;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private int q = 0;

    class a extends BroadcastReceiver {
        a() {
        }

        /* JADX WARN: Code duplicated, block: B:71:0x0102  */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (action == null) {
                }
                boolean booleanExtra = true;
                switch (action) {
                    case "android.intent.action.BOOT_COMPLETED":
                        i3.this.f130d.p3();
                        break;
                    case "android.intent.action.SCREEN_ON":
                        i3.this.R(true);
                        break;
                    case "android.intent.action.SCREEN_OFF":
                        i3.this.R(false);
                        break;
                    case "android.intent.action.USER_PRESENT":
                        i3.this.J();
                        break;
                    case "android.intent.action.BATTERY_CHANGED":
                        if (i3.this.f133g == 0) {
                            i3.this.f133g = intent.getIntExtra("scale", 0);
                            i3.this.h = f.p0.m("config_lowBatteryWarningLevel", 15);
                        }
                        int intExtra = intent.getIntExtra("level", i3.this.f132f);
                        if (intExtra != i3.this.f132f) {
                            i3.this.N(intExtra);
                            break;
                        }
                        break;
                    case "android.intent.action.ACTION_POWER_CONNECTED":
                        i3.this.Q(true);
                        break;
                    case "android.intent.action.ACTION_POWER_DISCONNECTED":
                        i3.this.Q(false);
                        break;
                    case "android.intent.action.AIRPLANE_MODE":
                        i3.this.M(intent.getBooleanExtra("state", false));
                        break;
                    case "android.net.wifi.STATE_CHANGE":
                        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                        if (networkInfo != null) {
                            NetworkInfo.State state = networkInfo.getState();
                            if (state == NetworkInfo.State.CONNECTED) {
                                i3.this.T(true);
                            } else if (state == NetworkInfo.State.DISCONNECTED) {
                                i3.this.T(false);
                            }
                            break;
                        }
                        break;
                    case "android.net.conn.CONNECTIVITY_CHANGE":
                        i3 i3Var = i3.this;
                        if (intent.getBooleanExtra("noConnectivity", false)) {
                            booleanExtra = false;
                        }
                        i3Var.P(booleanExtra);
                        break;
                    case "android.intent.action.HEADSET_PLUG":
                        int intExtra2 = intent.getIntExtra("state", -1);
                        if (intExtra2 == -1) {
                            booleanExtra = intent.getBooleanExtra("state", false);
                        } else if (intExtra2 == 0) {
                            booleanExtra = false;
                        }
                        i3.this.O(booleanExtra);
                        break;
                    case "android.hardware.usb.action.USB_STATE":
                        i3.this.S(intent.getBooleanExtra("connected", false));
                        break;
                    case "android.intent.action.NEW_OUTGOING_CALL":
                        i3.this.U(6);
                        break;
                    case "android.intent.action.PHONE_STATE":
                        String stringExtra = intent.getStringExtra("state");
                        if (TelephonyManager.EXTRA_STATE_RINGING.equals(stringExtra)) {
                            i3.this.U(3);
                            break;
                        } else {
                            if (!TelephonyManager.EXTRA_STATE_OFFHOOK.equals(stringExtra)) {
                                i3.this.U(0);
                            } else {
                                i3.this.U(1);
                            }
                            break;
                        }
                        break;
                    case "android.intent.action.PRECISE_CALL_STATE":
                        if (intent.getIntExtra("foreground_state", -1) == 1) {
                            i3.this.U(2);
                            break;
                        }
                        break;
                    case "android.provider.Telephony.SMS_RECEIVED":
                        i3.this.K(22);
                        break;
                    case "android.intent.action.REBOOT":
                    case "android.intent.action.ACTION_SHUTDOWN":
                        i3.this.f130d.m3();
                        break;
                    case "android.intent.action.TIME_SET":
                    case "android.intent.action.TIMEZONE_CHANGED":
                        i3.this.f130d.q3();
                        break;
                    case "android.intent.action.USER_SWITCHED":
                        int intExtra3 = intent.getIntExtra("android.intent.extra.user_handle", -1);
                        if (intExtra3 >= 0) {
                            i3.this.f130d.A4(intExtra3);
                            break;
                        }
                        break;
                    default:
                        f.v.c("Unhandled action: " + action);
                        break;
                }
            } catch (Throwable th) {
                f.v.d(th);
            }
        }
    }

    i3(a.p pVar, w1 w1Var, Context context, Handler handler) {
        this.f129c = pVar;
        this.f130d = w1Var;
        this.f131e = context;
        try {
            L(context, handler);
        } catch (Throwable th) {
            f.v.d(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        if (this.j) {
            this.j = false;
            this.f130d.h3();
            K(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(int i) {
        a.b bVarK;
        int i2;
        if (!f.l.r || (i2 = (bVarK = this.f129c.k(i)).z) == 1 || i2 == 0) {
            return;
        }
        this.f130d.w3(bVarK);
    }

    private void L(Context context, Handler handler) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(1000);
        for (String str : r) {
            intentFilter.addAction(str);
        }
        if (f.l.r) {
            for (String str2 : s) {
                intentFilter.addAction(str2);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                intentFilter.addAction("android.intent.action.PRECISE_CALL_STATE");
            }
        }
        f.c0.w(context, new a(), intentFilter, handler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(boolean z) {
        if (this.n != z) {
            this.n = z;
            K(z ? 13 : 14);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N(int i) {
        int i2;
        if (i == this.f133g) {
            i2 = 4;
        } else {
            int i3 = this.h;
            if (i >= i3 || this.f132f < i3) {
                if (i >= i3 && this.f132f < i3) {
                    i2 = 6;
                }
                this.f132f = i;
            }
            i2 = 5;
        }
        K(i2);
        this.f132f = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(boolean z) {
        if (this.o != z) {
            this.o = z;
            K(z ? 15 : 16);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P(boolean z) {
        if (this.m != z) {
            this.m = z;
            K(z ? 11 : 12);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q(boolean z) {
        if (this.k != z) {
            this.k = z;
            K(z ? 7 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R(boolean z) {
        if (this.i != z) {
            this.i = z;
            if (z) {
                this.f130d.l3();
                K(1);
            } else {
                this.j = true;
                this.f130d.k3();
                K(2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S(boolean z) {
        if (this.p != z) {
            this.p = z;
            K(z ? 17 : 18);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(boolean z) {
        if (this.l != z) {
            this.l = z;
            K(z ? 9 : 10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U(int i) {
        int i2;
        int i3;
        if (i != 1) {
            if (i == 2) {
                int i4 = this.q;
                if (i4 != 3) {
                    if (i4 != 6) {
                        return;
                    }
                    this.q = 7;
                    i2 = 27;
                }
            } else if (i != 3) {
                if (i != 6) {
                    int i5 = this.q;
                    if (i5 != 3 && i5 != 4) {
                        i3 = (i5 == 6 || i5 == 7) ? 28 : 25;
                        this.q = 0;
                        return;
                    }
                    K(i3);
                    this.q = 0;
                    return;
                }
                if (this.q == 6) {
                    return;
                }
                this.q = 6;
                i2 = 26;
            } else {
                if (this.q == 3) {
                    return;
                }
                this.q = 3;
                i2 = 23;
            }
            K(i2);
            return;
        }
        if (this.q != 3) {
            return;
        }
        this.q = 4;
        K(24);
    }

    boolean A() {
        return j3.f(this.f131e);
    }

    boolean B() {
        return this.q != 0;
    }

    boolean C() {
        return this.k;
    }

    boolean D() {
        return this.q == 3;
    }

    boolean E() {
        return j3.g();
    }

    boolean F() {
        return this.p;
    }

    boolean G() {
        return j3.h(this.f131e);
    }

    boolean H() {
        return this.l;
    }

    boolean I() {
        return j3.i(this.f131e);
    }

    int q() {
        return this.f132f;
    }

    boolean r() {
        return this.n;
    }

    boolean s() {
        return j3.a(this.f131e);
    }

    boolean t() {
        return j3.b(this.f131e);
    }

    boolean u() {
        return j3.c();
    }

    boolean v() {
        return this.o;
    }

    boolean w() {
        return this.j;
    }

    boolean x() {
        return j3.d(this.f131e);
    }

    boolean y() {
        return j3.e(this.f131e);
    }

    boolean z() {
        return this.m;
    }
}
