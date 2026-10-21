package pkg_d;

import android.os.Bundle;
import com.jozein.xedgepro.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f extends e implements pkg_e.j.d {
    private List<pkg_f.c0.d> M = null;

    private pkg_a.b E1(int i) {
        return this.M.get(I0()).q()[i].q();
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.select_activity);
    }

    @Override // pkg_e.j
    protected int B0() {
        if (this.M == null) {
            this.M = pkg_f.z.K(f()).R();
        }
        return this.M.size();
    }

    @Override // pkg_d.e
    protected void B1(Bundle bundle, int i) {
        if (i == 1) {
            int i2 = bundle.getInt("result", 0);
            if (i2 == 0 || i2 == 1) {
                try {
                    pkg_a.m.b().f(3, i2);
                    w1();
                } catch (Throwable th) {
                    g0(th);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // pkg_e.j
    /* JADX INFO: renamed from: F1, reason: merged with bridge method [inline-methods] */
    public pkg_e.j.k h1(int i) {
        pkg_f.c0.d dVar = this.M.get(i);
        pkg_e.j.g gVar = new pkg_e.j.g(dVar.d(), dVar.f(), dVar.i());
        if (!dVar.m()) {
            gVar.e();
        }
        return gVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // pkg_e.j
    /* JADX INFO: renamed from: G1, reason: merged with bridge method [inline-methods] */
    public pkg_e.j.k i1(int i) {
        pkg_f.c0.a aVar = this.M.get(I0()).q()[i];
        pkg_e.j.g gVar = new pkg_e.j.g(aVar.j(), aVar.f(), aVar.t());
        if (!aVar.u()) {
            gVar.e();
        }
        return gVar;
    }

    @Override // pkg_e.j
    protected boolean N0(int i) {
        return true;
    }

    @Override // pkg_e.j.d
    public String[] a() {
        return pkg_f.z.L(this.M);
    }

    @Override // pkg_e.j
    protected void g1(Object obj) {
        this.M = (List) obj;
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        y1(i, this.M.get(i).q().length);
    }

    @Override // pkg_e.j
    protected pkg_e.j.i m1() {
        List<pkg_f.c0.d> listM = m(pkg_f.c0.d.class);
        this.M = listM;
        if (listM != null) {
            return null;
        }
        return new pkg_e.j.i.a();
    }

    @Override // pkg_e.j
    protected void n1(int i, int i2) {
        if (i < 0 || i >= this.M.size() || this.M.get(i).q().length != i2) {
            return;
        }
        super.n1(i, i2);
    }

    @Override // pkg_e.j
    protected void o1(int i) {
        C1(E1(i));
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Z(this.M);
    }

    @Override // pkg_e.j
    protected boolean p1(int i) {
        D1(E1(i));
        return true;
    }
}
