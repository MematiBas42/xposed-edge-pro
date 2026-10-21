package pkg_d;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import com.jozein.xedgepro.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class v1 extends pkg_e.j {
    private List<pkg_f.c0.e> M;

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.select_shortcut);
    }

    @Override // pkg_e.j
    protected int B0() {
        if (this.M == null) {
            this.M = pkg_f.z.K(f()).T();
        }
        return this.M.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // pkg_e.j
    /* JADX INFO: renamed from: B1, reason: merged with bridge method [inline-methods] */
    public pkg_e.j.k h1(int i) {
        pkg_f.c0.e eVar = this.M.get(i);
        return new pkg_e.j.g(eVar.d(), eVar.f(), (CharSequence) null);
    }

    @Override // pkg_e.j
    protected void g1(Object obj) {
        this.M = (List) obj;
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        pkg_f.c0.e eVar = this.M.get(i);
        Intent intent = new Intent("android.intent.action.CREATE_SHORTCUT");
        intent.setComponent(new ComponentName(eVar.i(), eVar.r()));
        startActivityForResult(intent, 0);
    }

    @Override // pkg_e.j
    protected pkg_e.j.i m1() {
        List<pkg_f.c0.e> listM = m(pkg_f.c0.e.class);
        this.M = listM;
        if (listM != null) {
            return null;
        }
        return new pkg_e.j.i.e();
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            try {
                pkg_a.b bVarE = pkg_f.l0.e(intent, M0(), this.M.get(E0()).i());
                if (bVarE != null) {
                    W("result", bVarE);
                    L();
                }
            } catch (Throwable th) {
                pkg_f.v.d(th);
            }
        }
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Z(this.M);
    }
}
