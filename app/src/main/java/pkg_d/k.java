package pkg_d;

import android.os.Bundle;
import com.jozein.xedgepro.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class k extends e implements pkg_e.j.d {
    private List<pkg_f.c0.e> M;

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.select_app);
    }

    @Override // pkg_e.j
    protected int B0() {
        if (this.M == null) {
            this.M = pkg_f.z.K(f()).M();
        }
        return this.M.size();
    }

    @Override // pkg_d.e
    protected void B1(Bundle bundle, int i) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // pkg_e.j
    /* JADX INFO: renamed from: E1, reason: merged with bridge method [inline-methods] */
    public pkg_e.j.k h1(int i) {
        pkg_f.c0.e eVar = this.M.get(i);
        pkg_e.j.g gVar = new pkg_e.j.g(eVar.d(), eVar.f(), (CharSequence) null);
        if (!eVar.m()) {
            gVar.e();
        }
        return gVar;
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
        C1(this.M.get(i).q());
    }

    @Override // pkg_e.j
    protected boolean l1(int i) {
        if (i >= this.M.size()) {
            return super.l1(i);
        }
        D1(this.M.get(i).q());
        return true;
    }

    @Override // pkg_e.j
    protected pkg_e.j.i m1() {
        List<pkg_f.c0.e> listM = m(pkg_f.c0.e.class);
        this.M = listM;
        if (listM != null) {
            return null;
        }
        return new pkg_e.j.i.b();
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Z(this.M);
    }
}
