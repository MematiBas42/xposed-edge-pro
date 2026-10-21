package pkg_d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class q extends w1 {
    private pkg_a.b[] N;
    private int O;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (q.this.h().getBoolean("changed", false)) {
                q.this.S1();
            }
            q.this.L();
        }
    }

    private int Q1(int i, int i2) {
        return (i * this.O) + i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S1() {
        Bundle bundle = new Bundle(1);
        bundle.putParcelableArray("result", this.N);
        U(bundle);
    }

    @Override // pkg_d.w1, pkg_e.j
    protected int B0() {
        pkg_a.b[] bVarArr;
        pkg_a.b[] bVarArr2 = (pkg_a.b[]) h().getParcelableArray("actions");
        this.N = bVarArr2;
        int i = 4;
        if (bVarArr2 == null) {
            this.N = new pkg_a.b[4];
        }
        int i2 = 0;
        while (true) {
            bVarArr = this.N;
            if (i2 >= bVarArr.length) {
                break;
            }
            if (bVarArr[i2] == null) {
                bVarArr[i2] = pkg_a.b.r();
            }
            i2++;
        }
        if (bVarArr.length == 9) {
            i = 3;
        } else if (bVarArr.length != 16) {
            i = 2;
        }
        this.O = i;
        return i;
    }

    @Override // pkg_d.w1, pkg_d.c
    protected void C1(Bundle bundle, int i) {
        if (i != 1) {
            if (i == 2) {
                if (bundle.getBoolean("result", false)) {
                    S1();
                }
                L();
                return;
            } else {
                pkg_f.v.c("Unknown code: " + i);
                return;
            }
        }
        Context contextF = f();
        pkg_a.b bVar = (pkg_a.b) bundle.getParcelable("result");
        if (bVar != null) {
            int iE0 = E0();
            int iF0 = F0();
            this.N[Q1(iE0, iF0)] = bVar;
            h().putBoolean("changed", true);
            pkg_e.j.g gVar = (pkg_e.j.g) K0(iF0);
            gVar.setSubText(bVar.n(contextF));
            gVar.setImageDrawable(D0(bVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // pkg_d.w1, pkg_e.j
    /* JADX INFO: renamed from: L1 */
    public pkg_e.j.k i1(int i) {
        Context contextM0 = M0();
        pkg_a.b bVar = this.N[Q1(I0(), i)];
        return new pkg_e.j.g(I1(i), bVar.n(contextM0), D0(bVar));
    }

    @Override // pkg_d.w1
    protected void M1() {
        c0(R.string.action_custom_panel);
        if (h().getBoolean("editable", false)) {
            S(R.drawable.ic_ok, new a());
        }
    }

    public q R1(CharSequence charSequence, pkg_a.b[] bVarArr, boolean z) {
        Bundle bundleH = h();
        bundleH.putCharSequence("alias", charSequence);
        bundleH.putParcelableArray("actions", bVarArr);
        bundleH.putBoolean("editable", z);
        return this;
    }

    @Override // pkg_d.w1, pkg_e.j
    protected void k1(int i) {
        y1(i, this.O);
    }

    @Override // pkg_d.w1, pkg_e.j
    protected void o1(int i) {
        CharSequence charSequence = h().getCharSequence("alias");
        if (charSequence == null) {
            charSequence = u(R.string.action_custom_panel);
        }
        P(new pkg_d.d().J1(6, charSequence, J1(I0()), I1(i)), 1);
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        h().putParcelableArray("actions", this.N);
        super.onSaveInstanceState(bundle);
    }

    @Override // pkg_d.w1, pkg_e.j
    protected boolean p1(int i) {
        G1(this.N[Q1(I0(), i)], 1, 6, h().getBoolean("editable"));
        return true;
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
