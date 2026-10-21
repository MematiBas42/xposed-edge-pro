package pkg_d;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class q0 extends c implements pkg_d.d.a, pkg_e.j.d {
    private static final int N = pkg_a.p.g.f30b;
    private pkg_a.z M;

    private int I1(int i) {
        return pkg_a.p.g.f29a[i * 2];
    }

    private CharSequence J1(int i) {
        return u(pkg_a.p.g.f29a[(i * 2) + 1]);
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.more_triggers);
    }

    @Override // pkg_e.j
    protected int B0() {
        this.M = g().h();
        return N;
    }

    @Override // pkg_d.c
    protected void C1(Bundle bundle, int i) {
        pkg_a.b bVar;
        if (i == 1 && (bVar = (pkg_a.b) bundle.getParcelable("result")) != null) {
            int iE0 = E0();
            Context contextM0 = M0();
            this.M.m0(contextM0, I1(iE0), bVar);
            pkg_e.j.g gVar = (pkg_e.j.g) L0(iE0);
            gVar.setSubText(bVar.n(contextM0));
            gVar.setImageDrawable(D0(bVar));
        }
    }

    @Override // pkg_e.j.d
    public String[] a() {
        String[] strArr = new String[N];
        for (int i = 0; i < N; i++) {
            strArr[i] = J1(i).toString();
        }
        return strArr;
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        int iI1 = I1(i);
        pkg_a.b bVarK = this.M.k(iI1);
        pkg_e.j.g gVar = new pkg_e.j.g(J1(i), bVarK.n(M0()), D0(bVarK));
        if (Build.VERSION.SDK_INT < 21 && iI1 == 27) {
            gVar.setEnabled(false);
        }
        return gVar;
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        P(new pkg_d.d().H1(6, J1(i)), 1);
    }

    @Override // pkg_e.j
    protected boolean l1(int i) {
        F1(this.M.k(I1(i)), 1, 6);
        return true;
    }
}
