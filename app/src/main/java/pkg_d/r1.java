package pkg_d;

import android.os.Bundle;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class r1 extends t0 {
    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.saved_multi_actions);
    }

    @Override // pkg_d.t0
    protected pkg_a.n B1() {
        return pkg_a.b.k2.A();
    }

    @Override // pkg_d.t0
    protected pkg_a.b[] C1(Bundle bundle) {
        pkg_a.b.q1 q1Var = (pkg_a.b.q1) bundle.getParcelable("result");
        return q1Var != null ? q1Var.I : new pkg_a.b[0];
    }

    @Override // pkg_d.t0
    protected pkg_a.b D1(int i) {
        return new pkg_a.b.k2(i);
    }

    @Override // pkg_d.t0
    protected pkg_e.j E1(CharSequence charSequence, pkg_a.b[] bVarArr, boolean z) {
        return new s0().S1(bVarArr, z);
    }

    @Override // pkg_d.t0
    protected void F1() {
        H1(E1(null, new pkg_a.b[0], true));
    }
}
