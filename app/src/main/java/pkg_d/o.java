package pkg_d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class o extends c {

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            o.this.J1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J1() {
        Bundle bundleH = h();
        pkg_a.h hVar = (pkg_a.h) bundleH.getParcelable("if");
        pkg_a.b bVar = (pkg_a.b) bundleH.getParcelable("then");
        pkg_a.b bVar2 = (pkg_a.b) bundleH.getParcelable("else");
        if (hVar == null || hVar.z == 0 || ((bVar == null || bVar.z == 0) && (bVar2 == null || bVar2.z == 0))) {
            e0(R.string.condition_invalid);
        } else {
            W("result", new pkg_a.b.d0(hVar, bVar, bVar2));
        }
        L();
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.conditional);
        S(R.drawable.ic_ok, new a());
    }

    @Override // pkg_e.j
    protected int B0() {
        return 3;
    }

    @Override // pkg_d.c
    protected void C1(Bundle bundle, int i) {
        pkg_a.b bVarE;
        Bundle bundleH;
        String str;
        if (i == 0) {
            pkg_a.h oVar = (pkg_a.h) bundle.getParcelable("result");
            if (oVar == null) {
                oVar = new pkg_a.h.o();
            }
            h().putParcelable("if", oVar);
            ((pkg_e.j.k) L0(0)).setSubText(oVar.b(M0()));
            return;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                return;
            }
            bVarE = (pkg_a.b) bundle.getParcelable("result");
            if (bVarE == null) {
                bVarE = pkg_a.b.e();
            }
            bundleH = h();
            str = "else";
        } else {
            bVarE = (pkg_a.b) bundle.getParcelable("result");
            if (bVarE == null) {
                bVarE = pkg_a.b.e();
            }
            bundleH = h();
            str = "then";
        }
        bundleH.putParcelable(str, bVarE);
        pkg_e.j.g gVar = (pkg_e.j.g) L0(i2);
        gVar.setSubText(bVarE.n(gVar.getContext()));
        gVar.setImageDrawable(D0(bVarE));
    }

    public o K1(pkg_a.h hVar, pkg_a.b bVar, pkg_a.b bVar2) {
        Bundle bundleH = h();
        bundleH.putParcelable("if", hVar);
        bundleH.putParcelable("then", bVar);
        bundleH.putParcelable("else", bVar2);
        return this;
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        Context contextM0 = M0();
        Bundle bundleH = h();
        if (i == 0) {
            pkg_a.h oVar = (pkg_a.h) bundleH.getParcelable("if");
            if (oVar == null) {
                oVar = new pkg_a.h.o();
            }
            return new pkg_e.j.k(u(R.string.conditional_if), oVar.b(contextM0));
        }
        if (i != 1) {
            pkg_a.b bVarR = (pkg_a.b) bundleH.getParcelable("else");
            if (bVarR == null) {
                bVarR = pkg_a.b.r();
            }
            return new pkg_e.j.g(u(R.string.conditional_else), bVarR.n(contextM0), D0(bVarR));
        }
        pkg_a.b bVarR2 = (pkg_a.b) bundleH.getParcelable("then");
        if (bVarR2 == null) {
            bVarR2 = pkg_a.b.r();
        }
        return new pkg_e.j.g(u(R.string.conditional_then), bVarR2.n(contextM0), D0(bVarR2));
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        pkg_d.d dVar = null;
        CharSequence charSequenceU;
        int i2;
        if (i == 0) {
            P(new p(), 0);
            return;
        }
        int i3 = 1;
        if (i != 1) {
            i3 = 2;
            if (i != 2) {
                return;
            }
            dVar = new pkg_d.d();
            charSequenceU = u(R.string.conditional);
            i2 = R.string.conditional_else;
        } else {
            dVar = new pkg_d.d();
            charSequenceU = u(R.string.conditional);
            i2 = R.string.conditional_then;
        }
        P(dVar.I1(5, charSequenceU, u(i2)), i3);
    }

    @Override // pkg_e.j
    protected boolean l1(int i) {
        if (i == 1 || i == 2) {
            pkg_a.b bVarR = (pkg_a.b) h().getParcelable(i == 1 ? "then" : "else");
            if (bVarR == null) {
                bVarR = pkg_a.b.r();
            }
            F1(bVarR, i, 5);
        }
        return true;
    }
}
