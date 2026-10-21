package pkg_d;

import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class b extends s0 {
    private pkg_a.e O;

    @Override // pkg_d.s0, pkg_e.j
    protected int B0() {
        pkg_a.e eVarA = pkg_a.e.A();
        this.O = eVarA;
        eVarA.C();
        int iV = this.O.v();
        pkg_a.b[] bVarArr = new pkg_a.b[iV];
        for (int i = 0; i < iV; i++) {
            bVarArr[i] = this.O.w(i);
        }
        S1(bVarArr, !h().getBoolean("select", false));
        return super.B0();
    }

    @Override // pkg_e.j0.c
    protected void G() {
        super.G();
        pkg_a.e.A().F(f());
    }

    @Override // pkg_d.s0
    protected int K1() {
        return 7;
    }

    @Override // pkg_d.s0
    protected void M1(int i, pkg_a.b bVar) {
        try {
            if (i == this.O.v()) {
                this.O.e(M0(), bVar);
            } else {
                this.O.f(M0(), i, bVar);
            }
            super.M1(i, bVar);
        } catch (Throwable th) {
            g0(th);
        }
    }

    @Override // pkg_d.s0
    protected void O1(int i, boolean z) {
        try {
            if (z) {
                this.O.m(i);
            } else {
                this.O.k(i);
            }
            super.O1(i, z);
        } catch (Throwable th) {
            g0(th);
        }
    }

    @Override // pkg_d.s0
    protected void P1(int i) {
        try {
            this.O.q(M0(), i);
            super.P1(i);
        } catch (Throwable th) {
            g0(th);
        }
    }

    @Override // pkg_d.s0
    protected void Q1(int i, pkg_a.b bVar) {
        try {
            this.O.u(M0(), i, bVar);
            super.Q1(i, bVar);
        } catch (Throwable th) {
            g0(th);
        }
    }

    @Override // pkg_d.s0
    protected void R1() {
        c0(R.string.action_collection);
    }

    public b T1(boolean z) {
        h().putBoolean("select", z);
        return this;
    }

    @Override // pkg_d.s0, pkg_e.j
    protected void k1(int i) {
        if (!h().getBoolean("select", false)) {
            super.k1(i);
        } else {
            W("result", this.O.w(i));
            L();
        }
    }

    @Override // pkg_d.s0, pkg_e.j0.c
    protected void y() {
        L();
    }
}
