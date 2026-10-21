package pkg_d;

import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class a2 extends e {
    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.action_successive_adjust);
    }

    @Override // pkg_e.j
    protected int B0() {
        return 15;
    }

    @Override // pkg_d.e
    protected void B1(Bundle bundle, int i) {
    }

    @Override // pkg_e.j
    protected boolean N0(int i) {
        return true;
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        return new pkg_e.j.k(this, pkg_a.b.i3.z(M0(), i));
    }

    @Override // pkg_e.j
    protected View i1(int i) {
        return new pkg_e.j.k(this, pkg_a.b.i3.A(M0(), I0(), i == 0));
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        y1(i, 2);
    }

    @Override // pkg_e.j
    protected void o1(int i) {
        C1(new pkg_a.b.i3(I0(), i == 0));
    }

    @Override // pkg_e.j
    protected boolean p1(int i) {
        D1(new pkg_a.b.i3(I0(), i == 0));
        return true;
    }
}
