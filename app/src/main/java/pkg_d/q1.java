package pkg_d;

import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class q1 extends pkg_e.j {
    private pkg_a.b.j2 M;

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.select_icon);
    }

    @Override // pkg_e.j
    protected int B0() {
        pkg_a.b.j2 j2Var = new pkg_a.b.j2();
        this.M = j2Var;
        return j2Var.a();
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        return new pkg_e.j.g(this.M.b(i), this.M.d(i), (CharSequence) null);
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        Bundle bundle = new Bundle(1);
        bundle.putString("result", this.M.d(i));
        U(bundle);
        L();
    }
}
