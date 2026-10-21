package pkg_d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class g extends e {
    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.action_adjust);
    }

    @Override // pkg_e.j
    protected int B0() {
        return 13;
    }

    @Override // pkg_d.e
    protected void B1(Bundle bundle, int i) {
        int i2 = bundle.getInt("result", -1);
        if (i2 != -1) {
            C1(new pkg_a.b.i(I0(), i2));
        }
    }

    protected int E1(Context context, int i) {
        if (i != 0) {
            return pkg_a.b.i.z(context, i);
        }
        pkg_a.z zVarH = g().h();
        if (zVarH != null) {
            return (zVarH.B() >>> 24) / 16;
        }
        return -1;
    }

    @Override // pkg_e.j
    protected boolean N0(int i) {
        return true;
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        return new pkg_e.j.k(this, pkg_a.b.i.C(M0(), i));
    }

    @Override // pkg_e.j
    protected View i1(int i) {
        return new pkg_e.j.k(this, pkg_a.b.i.a(M0(), i));
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        y1(i, pkg_a.b.i.D(i));
    }

    @Override // pkg_e.j
    protected void o1(int i) {
        pkg_a.b.i iVar;
        int iI0 = I0();
        if (i == 0) {
            iVar = new pkg_a.b.i(iI0, -1);
        } else {
            if (i != 1) {
                Context contextM0 = M0();
                int iB = pkg_a.b.i.B(iI0);
                int iA = pkg_a.b.i.A(contextM0, iI0);
                int iE1 = E1(contextM0, iI0);
                if (iE1 < 0) {
                    iE1 = (iB + iA) / 2;
                }
                M(new pkg_e.g0().x(pkg_a.b.i.C(contextM0, iI0), null, iB, iE1, iA));
                return;
            }
            iVar = new pkg_a.b.i(iI0, -2);
        }
        C1(iVar);
    }

    @Override // pkg_e.j
    protected boolean p1(int i) {
        if (i >= 2) {
            return super.p1(i);
        }
        D1(new pkg_a.b.i(I0(), i == 0 ? -1 : -2));
        return true;
    }
}
