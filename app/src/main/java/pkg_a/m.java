package pkg_a;

/* JADX INFO: loaded from: classes.dex */
public class m implements pkg_f.l {
    private static m B;
    private final pkg_f.p z = new pkg_f.p(pkg_f.l.CONST_LOCAL_PREFS);
    private final int[] A = new int[6];

    private m() {
        d();
    }

    public static m b() {
        m mVar;
        synchronized (m.class) {
            if (B == null) {
                B = new m();
            }
            mVar = B;
        }
        return mVar;
    }

    public static void c() {
        synchronized (m.class) {
            B = null;
        }
    }

    private void e() {
        this.z.j();
        for (int i = 0; i < 6; i++) {
            this.z.d(this.A[i]);
        }
        this.z.n();
    }

    public int a(int i) {
        return this.A[i];
    }

    public void d() {
        if (this.z.E()) {
            for (int i = 0; i < 6; i++) {
                this.A[i] = this.z.h();
            }
        }
        this.z.m();
    }

    public void f(int i, int i2) {
        int[] iArr = this.A;
        if (iArr[i] != i2) {
            iArr[i] = i2;
            e();
        }
    }
}
