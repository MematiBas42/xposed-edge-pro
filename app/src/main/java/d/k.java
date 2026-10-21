package d;

import android.os.Bundle;
import com.jozein.xedgepro.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class k extends e implements e.j.d {
    private List<f.c0.e> M;

    @Override // e.j0.c
    protected void B() {
        super.B();
        c0(R.string.select_app);
    }

    @Override // e.j
    protected int B0() {
        if (this.M == null) {
            this.M = f.z.K(f()).M();
        }
        return this.M.size();
    }

    @Override // d.e
    protected void B1(Bundle bundle, int i) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // e.j
    /* JADX INFO: renamed from: E1, reason: merged with bridge method [inline-methods] */
    public e.j.k h1(int i) {
        f.c0.e eVar = this.M.get(i);
        e.j.g gVar = new e.j.g(eVar.d(), eVar.f(), (CharSequence) null);
        if (!eVar.m()) {
            gVar.e();
        }
        return gVar;
    }

    @Override // e.j.d
    public String[] a() {
        return f.z.L(this.M);
    }

    @Override // e.j
    protected void g1(Object obj) {
        this.M = (List) obj;
    }

    @Override // e.j
    protected void k1(int i) {
        C1(this.M.get(i).q());
    }

    @Override // e.j
    protected boolean l1(int i) {
        if (i >= this.M.size()) {
            return super.l1(i);
        }
        D1(this.M.get(i).q());
        return true;
    }

    @Override // e.j
    protected e.j.i m1() {
        List<f.c0.e> listM = m(f.c0.e.class);
        this.M = listM;
        if (listM != null) {
            return null;
        }
        return new e.j.i.b();
    }

    @Override // e.j, e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Z(this.M);
    }
}
