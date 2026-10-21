package d;

import android.os.Bundle;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends e.j implements e.j.d {
    protected List<f.c0.d> M;

    @Override // e.j
    protected int B0() {
        if (this.M == null) {
            this.M = f.z.K(f()).Q(false);
        }
        return this.M.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // e.j
    /* JADX INFO: renamed from: B1, reason: merged with bridge method [inline-methods] */
    public e.j.k h1(int i) {
        f.c0.d dVar = this.M.get(i);
        e.j.g gVar = new e.j.g(dVar.d(), dVar.f(), dVar.i());
        if (!dVar.m()) {
            gVar.e();
        }
        return gVar;
    }

    @Override // e.j.d
    public final String[] a() {
        return f.z.L(this.M);
    }

    @Override // e.j
    protected void g1(Object obj) {
        this.M = (List) obj;
    }

    @Override // e.j
    protected e.j.i m1() {
        List<f.c0.d> listM = m(f.c0.d.class);
        this.M = listM;
        if (listM != null) {
            return null;
        }
        return new e.j.i.d(false);
    }

    @Override // e.j, e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        if (this.M != null && s() == null) {
            Z(this.M);
        }
        super.onSaveInstanceState(bundle);
    }
}
