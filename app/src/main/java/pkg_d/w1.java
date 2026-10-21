package pkg_d;

import android.content.Context;
import android.os.Bundle;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class w1 extends c implements pkg_d.d.a {
    private pkg_a.z M;

    @Override // pkg_e.j0.c
    protected final void B() {
        super.B();
        M1();
    }

    @Override // pkg_e.j
    protected int B0() {
        this.M = g().h();
        return 4;
    }

    @Override // pkg_d.c
    protected void C1(Bundle bundle, int i) {
        if (i != 1) {
            pkg_f.v.c("Unknown code: " + i);
            return;
        }
        Context contextF = f();
        pkg_a.b bVar = (pkg_a.b) bundle.getParcelable("result");
        if (bVar != null) {
            int iE0 = E0();
            int iF0 = F0();
            this.M.l0(contextF, 3, iE0, iF0, bVar);
            pkg_e.j.g gVar = (pkg_e.j.g) K0(iF0);
            gVar.setSubText(bVar.n(contextF));
            gVar.setImageDrawable(D0(bVar));
        }
    }

    protected String I1(int i) {
        return getString(R.string.column_index_f, Integer.valueOf(i + 1));
    }

    protected String J1(int i) {
        return getString(R.string.row_index_f, Integer.valueOf(i + 1));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // pkg_e.j
    /* JADX INFO: renamed from: K1, reason: merged with bridge method [inline-methods] */
    public pkg_e.j.k h1(int i) {
        return new pkg_e.j.k(this, J1(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // pkg_e.j
    /* JADX INFO: renamed from: L1, reason: merged with bridge method [inline-methods] */
    public pkg_e.j.k i1(int i) {
        Context contextM0 = M0();
        pkg_a.b bVarM = this.M.m(I0(), i);
        return new pkg_e.j.g(I1(i), bVarM.n(contextM0), D0(bVarM));
    }

    protected void M1() {
        c0(R.string.shortcuts_panel);
    }

    @Override // pkg_e.j
    protected boolean N0(int i) {
        return true;
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        y1(i, 4);
    }

    @Override // pkg_e.j
    protected void o1(int i) {
        P(new pkg_d.d().J1(6, u(R.string.shortcuts_panel), J1(I0()), I1(i)), 1);
    }

    @Override // pkg_e.j
    protected boolean p1(int i) {
        F1(this.M.m(I0(), i), 1, 6);
        return true;
    }
}
