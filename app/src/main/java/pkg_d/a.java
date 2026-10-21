package pkg_d;

import android.os.Bundle;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends pkg_e.j implements pkg_e.j.d {
    protected List<pkg_f.c0.d> M;

    @Override // pkg_e.j
    protected int B0() {
        if (this.M == null) {
            this.M = pkg_f.z.K(f()).Q(false);
        }
        return this.M.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // pkg_e.j
    /* JADX INFO: renamed from: B1, reason: merged with bridge method [inline-methods] */
    public pkg_e.j.k h1(int i) {
        pkg_f.c0.d dVar = this.M.get(i);
        pkg_e.j.g gVar = new pkg_e.j.g(dVar.d(), dVar.f(), dVar.i());
        if (!dVar.m()) {
            gVar.e();
        }
        return gVar;
    }

    @Override // pkg_e.j.d
    public final String[] a() {
        return pkg_f.z.L(this.M);
    }

    @Override // pkg_e.j
    protected void g1(Object obj) {
        this.M = (List) obj;
    }

    @Override // pkg_e.j
    protected pkg_e.j.i m1() {
        List<pkg_f.c0.d> listM = m(pkg_f.c0.d.class);
        this.M = listM;
        if (listM != null) {
            return null;
        }
        return new pkg_e.j.i.d(false);
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        if (this.M != null && s() == null) {
            Z(this.M);
        }
        super.onSaveInstanceState(bundle);
    }
}
