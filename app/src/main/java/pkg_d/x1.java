package pkg_d;

import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class x1 extends s0 {
    private void T1() {
        pkg_a.z zVarH = g().h();
        if (zVarH != null) {
            zVarH.n0(getActivity(), h().getInt("side"), new pkg_a.b.q1(J1()));
        }
    }

    @Override // pkg_d.s0
    protected int K1() {
        return 6;
    }

    @Override // pkg_d.s0
    protected void M1(int i, pkg_a.b bVar) {
        super.M1(i, bVar);
        T1();
    }

    @Override // pkg_d.s0
    protected void O1(int i, boolean z) {
        super.O1(i, z);
        T1();
    }

    @Override // pkg_d.s0
    protected void P1(int i) {
        super.P1(i);
        T1();
    }

    @Override // pkg_d.s0
    protected void Q1(int i, pkg_a.b bVar) {
        super.Q1(i, bVar);
        T1();
    }

    @Override // pkg_d.s0
    protected void R1() {
        c0(h().getInt("side", 0) == 0 ? R.string.left_side_bar : R.string.right_side_bar);
    }

    public x1 U1(pkg_a.p pVar, int i) {
        h().putInt("side", i);
        S1(pVar.n(i).I, true);
        return this;
    }

    @Override // pkg_d.s0, pkg_e.j0.c
    protected void y() {
        L();
    }
}
