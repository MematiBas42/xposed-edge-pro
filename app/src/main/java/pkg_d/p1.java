package pkg_d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class p1 extends c implements pkg_d.d.a {
    private int M = 0;
    private pkg_a.b N = pkg_a.b.r();

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            p1.this.W("result", new pkg_a.b.f2(p1.this.M, p1.this.N));
            p1.this.L();
        }
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.action_repeat);
        S(R.drawable.ic_ok, new a());
    }

    @Override // pkg_e.j
    protected int B0() {
        Bundle bundleH = h();
        this.M = bundleH.getInt("count", 0);
        pkg_a.b bVar = (pkg_a.b) bundleH.getParcelable("action");
        this.N = bVar;
        if (bVar != null) {
            return 2;
        }
        this.N = pkg_a.b.r();
        return 2;
    }

    @Override // pkg_d.c
    protected void C1(Bundle bundle, int i) {
        if (i == 1) {
            CharSequence charSequence = bundle.getCharSequence("result");
            if (charSequence != null) {
                try {
                    this.M = Integer.parseInt(charSequence.toString());
                    ((pkg_e.j.k) L0(0)).setSubText(Integer.toString(this.M));
                    h().putBoolean("changed", true);
                    return;
                } catch (NumberFormatException e2) {
                    pkg_f.v.d(e2);
                    return;
                }
            }
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            if (bundle.getBoolean("result", false)) {
                W("result", new pkg_a.b.f2(this.M, this.N));
            }
            L();
            return;
        }
        pkg_a.b bVar = (pkg_a.b) bundle.getParcelable("result");
        if (bVar != null) {
            this.N = bVar;
            Context contextM0 = M0();
            pkg_e.j.g gVar = (pkg_e.j.g) L0(1);
            gVar.setSubText(bVar.n(contextM0));
            gVar.setImageDrawable(bVar.m(contextM0));
            h().putBoolean("changed", true);
        }
    }

    public p1 M1(int i, pkg_a.b bVar) {
        Bundle bundleH = h();
        bundleH.putInt("count", i);
        bundleH.putParcelable("action", bVar);
        return this;
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        if (i == 0) {
            return new pkg_e.j.k(u(R.string.count), Integer.toString(this.M));
        }
        Context contextM0 = M0();
        return new pkg_e.j.g(u(R.string.action), this.N.n(contextM0), this.N.m(contextM0));
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        if (i != 0) {
            E1(3, 2);
            return;
        }
        pkg_e.y yVar = new pkg_e.y();
        yVar.J(u(R.string.enter_count), null, Integer.toString(this.M), 7, 9);
        N(yVar, 1);
    }

    @Override // pkg_e.j
    protected boolean l1(int i) {
        if (i == 0) {
            return super.l1(i);
        }
        F1(this.N, 2, 3);
        return true;
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("count", this.M);
        bundle.putParcelable("action", this.N);
        super.onSaveInstanceState(bundle);
    }

    @Override // pkg_e.j0.c
    protected void y() {
        if (h().getBoolean("changed", false)) {
            N(new pkg_e.m().u(u(R.string.check_save_message)), 3);
        } else {
            super.y();
        }
    }
}
