package pkg_a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f33f = pkg_f.l.CONST_PREFIX + "STATE_CHANGED";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f34a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f35b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f36c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final b f37d = new b(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private BroadcastReceiver f38e = null;

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra;
            if (!t.f33f.equals(intent.getAction()) || (intExtra = intent.getIntExtra("bits", -1)) < 0 || t.this.f35b == intExtra) {
                return;
            }
            int i = t.this.f35b ^ intExtra;
            t.this.f35b = intExtra;
            if (t.this.f37d.f42c <= 0) {
                return;
            }
            int i2 = 1;
            for (int i3 = 0; i3 < 31; i3++) {
                if ((i & i2) != 0) {
                    i ^= i2;
                    try {
                        t.this.f37d.c(i2, (intExtra & i2) != 0);
                    } catch (Throwable th) {
                        pkg_f.v.d(th);
                        return;
                    }
                }
                i2 <<= 1;
                if (i == 0) {
                    return;
                }
            }
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private c[] f40a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int[] f41b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f42c;

        private b() {
            this.f40a = null;
            this.f41b = null;
            this.f42c = 0;
        }

        /* synthetic */ b(a aVar) {
            this();
        }

        void b(String str, int i, c cVar) {
            int iE = u.e(str);
            if (iE < 0) {
                return;
            }
            if (this.f40a == null) {
                c[] cVarArr = new c[u.f44d.length];
                this.f40a = cVarArr;
                this.f41b = new int[cVarArr.length];
            }
            c[] cVarArr2 = this.f40a;
            if (cVarArr2[iE] != null) {
                this.f42c--;
            }
            cVarArr2[iE] = cVar;
            this.f41b[iE] = i;
            this.f42c++;
        }

        void c(int i, boolean z) {
            if (this.f42c <= 0) {
                return;
            }
            int i2 = 0;
            while (true) {
                int[] iArr = this.f41b;
                if (i2 >= iArr.length) {
                    return;
                }
                if (iArr[i2] == i) {
                    c[] cVarArr = this.f40a;
                    if (cVarArr[i2] != null) {
                        cVarArr[i2].a(z);
                    }
                }
                i2++;
            }
        }

        void d(String str) {
            int iE;
            if (this.f40a != null && (iE = u.e(str)) >= 0) {
                c[] cVarArr = this.f40a;
                c cVar = cVarArr[iE];
                cVarArr[iE] = null;
                if (cVar != null) {
                    this.f42c--;
                }
            }
        }
    }

    public interface c {
        void a(boolean z);
    }

    public t(Context context) {
        this.f34a = context;
    }

    public static boolean i(int i) {
        return m(i) > 0;
    }

    private void k() {
        int i = this.f36c;
        int i2 = this.f35b;
        if (i == i2) {
            return;
        }
        Intent intent = new Intent(f33f);
        intent.putExtra("bits", i2);
        try {
            intent.setPackage("com.android.systemui");
            this.f34a.sendBroadcast(intent);
            intent.setPackage(pkg_f.l.CONST_PKG);
            this.f34a.sendBroadcast(intent);
            this.f36c = i2;
        } catch (Throwable th) {
            pkg_f.v.d(th);
        }
    }

    private static int m(int i) {
        if (i == 73) {
            return 2;
        }
        if (i == 97) {
            return 64;
        }
        switch (i) {
            case 29:
                return 1;
            case 30:
                return 4;
            case 31:
                return 32;
            case 32:
                return 8;
            case 33:
                return 16;
            case 34:
                return 128;
            case 35:
                return 256;
            default:
                return -1;
        }
    }

    public void e() {
        if (this.f38e != null) {
            try {
                pkg_a.b.s0.B(this.f34a, 21);
                this.f34a.unregisterReceiver(this.f38e);
                this.f38e = null;
            } catch (Throwable unused) {
            }
        }
    }

    public void f() {
        a aVar = new a();
        this.f38e = aVar;
        try {
            this.f34a.registerReceiver(aVar, new IntentFilter(f33f), 2);
            pkg_a.b.s0.B(this.f34a, 21);
        } catch (Throwable th) {
            pkg_f.v.d(th);
        }
    }

    public void g() {
        this.f36c = 0;
        k();
    }

    public boolean h(int i, boolean z) {
        int iM = m(i);
        if (iM <= 0) {
            return z;
        }
        return (iM & this.f35b) != 0;
    }

    public void j(String str, int i, c cVar) {
        int iM = m(i);
        if (iM <= 0) {
            return;
        }
        this.f37d.b(str, iM, cVar);
    }

    public void l(int i, boolean z) {
        int i2;
        int iM = m(i);
        if (iM <= 0) {
            return;
        }
        if (z) {
            i2 = iM | this.f35b;
        } else {
            i2 = (iM ^ (-1)) & this.f35b;
        }
        this.f35b = i2;
        k();
    }

    public void n(String str) {
        this.f37d.d(str);
    }
}
