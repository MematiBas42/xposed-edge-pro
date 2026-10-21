package d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class g extends e {
    @Override // e.j0.c
    protected void B() {
        super.B();
        c0(R.string.action_adjust);
    }

    @Override // e.j
    protected int B0() {
        return 13;
    }

    @Override // d.e
    protected void B1(Bundle bundle, int i) {
        int i2 = bundle.getInt("result", -1);
        if (i2 != -1) {
            C1(new a.b.i(I0(), i2));
        }
    }

    protected int E1(Context context, int i) {
        if (i != 0) {
            return a.b.i.z(context, i);
        }
        a.z zVarH = g().h();
        if (zVarH != null) {
            return (zVarH.B() >>> 24) / 16;
        }
        return -1;
    }

    @Override // e.j
    protected boolean N0(int i) {
        return true;
    }

    @Override // e.j
    protected View h1(int i) {
        return new e.j.k(this, a.b.i.C(M0(), i));
    }

    @Override // e.j
    protected View i1(int i) {
        return new e.j.k(this, a.b.i.a(M0(), i));
    }

    @Override // e.j
    protected void k1(int i) {
        y1(i, a.b.i.D(i));
    }

    @Override // e.j
    protected void o1(int i) {
        a.b.i iVar;
        int iI0 = I0();
        if (i == 0) {
            iVar = new a.b.i(iI0, -1);
        } else {
            if (i != 1) {
                Context contextM0 = M0();
                int iB = a.b.i.B(iI0);
                int iA = a.b.i.A(contextM0, iI0);
                int iE1 = E1(contextM0, iI0);
                if (iE1 < 0) {
                    iE1 = (iB + iA) / 2;
                }
                M(new e.g0().x(a.b.i.C(contextM0, iI0), null, iB, iE1, iA));
                return;
            }
            iVar = new a.b.i(iI0, -2);
        }
        C1(iVar);
    }

    @Override // e.j
    protected boolean p1(int i) {
        if (i >= 2) {
            return super.p1(i);
        }
        D1(new a.b.i(I0(), i == 0 ? -1 : -2));
        return true;
    }
}
