package d;

import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class q1 extends e.j {
    private a.b.j2 M;

    @Override // e.j0.c
    protected void B() {
        super.B();
        c0(R.string.select_icon);
    }

    @Override // e.j
    protected int B0() {
        a.b.j2 j2Var = new a.b.j2();
        this.M = j2Var;
        return j2Var.a();
    }

    @Override // e.j
    protected View h1(int i) {
        return new e.j.g(this.M.b(i), this.M.d(i), (CharSequence) null);
    }

    @Override // e.j
    protected void k1(int i) {
        Bundle bundle = new Bundle(1);
        bundle.putString("result", this.M.d(i));
        U(bundle);
        L();
    }
}
