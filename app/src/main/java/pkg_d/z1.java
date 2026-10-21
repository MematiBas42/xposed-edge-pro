package pkg_d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class z1 extends c implements pkg_d.d.a {
    private pkg_a.b[] M;
    private CharSequence[] N;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (z1.this.M != null) {
                z1.this.W("result", new pkg_a.b.h3(z1.this.M));
            }
            z1.this.L();
        }
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.action_sub_gestures);
        S(R.drawable.ic_ok, new a());
    }

    @Override // pkg_e.j
    protected int B0() {
        pkg_a.b[] bVarArr = (pkg_a.b[]) h().getParcelableArray("actions");
        this.M = bVarArr;
        if (bVarArr == null) {
            this.M = pkg_a.b.h3.K;
        }
        int length = pkg_a.b.h3.J.length;
        this.N = new CharSequence[length];
        for (int i = 0; i < length; i++) {
            this.N[i] = u(pkg_a.b.h3.J[i]);
        }
        return length;
    }

    @Override // pkg_d.c
    protected void C1(Bundle bundle, int i) {
        if (i != 1) {
            if (i != 2) {
                return;
            }
            if (bundle.getBoolean("result", false)) {
                W("result", new pkg_a.b.h3(this.M));
            }
            L();
            return;
        }
        Context contextM0 = M0();
        pkg_a.b bVar = (pkg_a.b) bundle.getParcelable("result");
        if (bVar != null) {
            int iE0 = E0();
            this.M[iE0] = bVar;
            h().putBoolean("changed", true);
            pkg_e.j.g gVar = (pkg_e.j.g) L0(iE0);
            gVar.setSubText(bVar.n(contextM0));
            gVar.setImageDrawable(D0(bVar));
        }
    }

    public z1 L1(pkg_a.b[] bVarArr) {
        pkg_a.b[] bVarArr2;
        if (bVarArr != null) {
            bVarArr2 = new pkg_a.b[bVarArr.length];
            System.arraycopy(bVarArr, 0, bVarArr2, 0, bVarArr.length);
        } else {
            bVarArr2 = null;
        }
        h().putParcelableArray("actions", bVarArr2);
        return this;
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        pkg_a.b bVar = this.M[i];
        return new pkg_e.j.g(this.N[i], bVar.n(M0()), D0(bVar));
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        P(new pkg_d.d().I1(6, o(), this.N[i]), 1);
    }

    @Override // pkg_e.j
    protected boolean l1(int i) {
        F1(this.M[i], 1, 1);
        return true;
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        h().putParcelableArray("actions", this.M);
        super.onSaveInstanceState(bundle);
    }

    @Override // pkg_e.j0.c
    protected void y() {
        if (h().getBoolean("changed", false)) {
            N(new pkg_e.m().u(u(R.string.check_save_message)), 2);
        } else {
            super.y();
        }
    }
}
