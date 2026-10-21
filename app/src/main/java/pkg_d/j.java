package pkg_d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class j extends c implements pkg_d.d.a, pkg_e.j.d {
    private static final int[] O = pkg_a.AppTriggerManager.F;
    private static final int[] P = pkg_a.AppTriggerManager.E;
    private pkg_a.AppTriggerManager M;
    private List<pkg_f.c0.d> N;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J1(View view) {
        if (this.M.i()) {
            return;
        }
        N(new pkg_e.m().u(u(R.string.check_clear_all)), 2);
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.app_state);
        S(R.drawable.ic_delete, new View.OnClickListener() { // from class: d.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                j.this.J1(view);
            }
        });
    }

    @Override // pkg_e.j
    protected int B0() {
        this.M = pkg_a.AppTriggerManager.g();
        if (this.N == null) {
            this.N = pkg_f.z.K(f()).Q(false);
        }
        return this.N.size();
    }

    @Override // pkg_d.c
    protected void C1(Bundle bundle, int i) {
        try {
            if (i == 1) {
                pkg_a.b bVar = (pkg_a.b) bundle.getParcelable("result");
                if (bVar != null) {
                    int iE0 = E0();
                    int iF0 = F0();
                    String strI = this.N.get(iE0).i();
                    this.M.j(f(), strI, P[iF0], bVar);
                    ((pkg_e.j.g) L0(iE0)).setHighLight(this.M.h(strI));
                    pkg_e.j.g gVar = (pkg_e.j.g) K0(iF0);
                    gVar.setImageDrawable(D0(bVar));
                    gVar.setSubText(bVar.n(gVar.getContext()));
                    return;
                }
                return;
            }
            if (i != 2) {
                return;
            }
            if (bundle.getBoolean("result", false)) {
                Context contextF = f();
                this.M.e(contextF);
                int iI0 = I0();
                for (int i2 = 0; i2 < O.length; i2++) {
                    pkg_e.j.g gVar2 = (pkg_e.j.g) K0(i2);
                    if (gVar2 != null) {
                        pkg_a.b bVarF = this.M.f(this.N.get(iI0).i(), P[i2]);
                        gVar2.setSubText(bVarF.n(contextF));
                        gVar2.setImageDrawable(D0(bVarF));
                    }
                }
                t1();
                e0(R.string.saved);
            }
        } catch (Throwable th) {
            g0(th);
        }
    }

    @Override // pkg_e.j
    protected boolean N0(int i) {
        return true;
    }

    @Override // pkg_e.j.d
    public String[] a() {
        return pkg_f.z.L(this.N);
    }

    @Override // pkg_e.j
    protected void g1(Object obj) {
        this.N = (List) obj;
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        pkg_f.c0.d dVar = this.N.get(i);
        String strI = dVar.i();
        pkg_e.j.g gVar = new pkg_e.j.g(dVar.e(), dVar.f(), strI);
        if (!dVar.m()) {
            gVar.e();
        }
        gVar.setHighLight(this.M.h(strI));
        return gVar;
    }

    @Override // pkg_e.j
    protected View i1(int i) {
        pkg_a.b bVarF = this.M.f(this.N.get(I0()).i(), P[i]);
        return new pkg_e.j.g(u(O[i]), bVarF.n(M0()), D0(bVarF));
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        y1(i, P.length);
    }

    @Override // pkg_e.j
    protected pkg_e.j.i m1() {
        List<pkg_f.c0.d> listM = m(pkg_f.c0.d.class);
        this.N = listM;
        if (listM != null) {
            return null;
        }
        return new pkg_e.j.i.d(false);
    }

    @Override // pkg_e.j
    protected void o1(int i) {
        P(new pkg_d.d().J1(6, u(R.string.app_state), this.N.get(I0()).i(), u(O[i])), 1);
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Z(this.N);
    }

    @Override // pkg_e.j
    protected boolean p1(int i) {
        F1(this.M.f(this.N.get(I0()).i(), P[i]), 1, 6);
        return true;
    }
}
