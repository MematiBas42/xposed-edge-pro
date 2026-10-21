package pkg_d;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class c2 extends pkg_e.j {
    private pkg_a.u.b M;

    private void C1(String str) {
        pkg_a.b.s0.D(f(), 22, str);
    }

    private CharSequence D1(String str) {
        PackageManager packageManager = f().getPackageManager();
        try {
            return packageManager.getServiceInfo(new ComponentName(pkg_f.l.CONST_PKG, str), 512).loadLabel(packageManager);
        } catch (Throwable th) {
            pkg_f.v.d(th);
            return str;
        }
    }

    private boolean E1(String str) {
        try {
            int componentEnabledSetting = f().getPackageManager().getComponentEnabledSetting(new ComponentName(pkg_f.l.CONST_PKG, str));
            return componentEnabledSetting == 1 || componentEnabledSetting == 0;
        } catch (Throwable th) {
            pkg_f.v.d(th);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F1(String str, CompoundButton compoundButton, boolean z) {
        H1(str, z);
    }

    private void G1(String str) {
        pkg_a.b.s0.D(f(), 23, str);
    }

    private void H1(String str, boolean z) {
        int i = z ? 1 : 2;
        if (!z) {
            try {
                this.M.p(str);
                G1(str);
            } catch (Throwable th) {
                pkg_f.v.d(th);
                return;
            }
        }
        f().getPackageManager().setComponentEnabledSetting(new ComponentName(pkg_f.l.CONST_PKG, str), i, 1);
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.tiles);
    }

    @Override // pkg_e.j
    protected int B0() {
        pkg_a.u.b bVarD = pkg_a.u.d();
        this.M = bVarD;
        return bVarD.i();
    }

    @Override // pkg_e.j0.c
    protected void J(Bundle bundle, int i) {
        pkg_a.u.b bVar;
        pkg_a.b.d dVar;
        int i2;
        if (i == 1) {
            pkg_a.b bVar2 = (pkg_a.b) bundle.getParcelable("result");
            if (bVar2 == null) {
                return;
            }
            try {
                int iE0 = E0();
                if (bVar2 instanceof pkg_a.b.d) {
                    bVar = this.M;
                    dVar = (pkg_a.b.d) bVar2;
                } else {
                    bVar = this.M;
                    dVar = new pkg_a.b.d(bVar2);
                }
                bVar.n(iE0, dVar);
                pkg_e.j.f fVar = (pkg_e.j.f) L0(iE0);
                fVar.setSubText(bVar2.n(M0()));
                fVar.setImageDrawable(D0(bVar2));
                return;
            } catch (Throwable th) {
                g0(th);
                return;
            }
        }
        if (i == 2 && (i2 = bundle.getInt("result", -1)) >= 0) {
            int iE1 = E0();
            if (i2 == 0) {
                k1(iE1);
                return;
            }
            if (i2 == 1) {
                ((pkg_e.j.f) L0(iE1)).setChecked(true);
                C1(this.M.h(iE1));
            } else if (i2 == 2) {
                G1(this.M.h(iE1));
            } else {
                if (i2 != 3) {
                    return;
                }
                ((pkg_e.j.f) L0(iE1)).h();
            }
        }
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        pkg_a.b.d dVarA = this.M.a(i);
        final String strH = this.M.h(i);
        pkg_e.j.f fVar = new pkg_e.j.f(D1(strH), dVarA.n(M0()), D0(dVarA), E1(strH));
        fVar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: d.b2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                c2.this.F1(strH, compoundButton, z);
            }
        });
        return fVar;
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        P(new t().K1(this.M.a(i), 6), 1);
    }

    @Override // pkg_e.j
    protected boolean l1(int i) {
        pkg_e.j.f fVar = (pkg_e.j.f) L0(i);
        CharSequence[] charSequenceArr = new CharSequence[4];
        charSequenceArr[0] = u(R.string.edit_tile);
        charSequenceArr[1] = u(R.string.add_tile);
        charSequenceArr[2] = u(R.string.remove_tile);
        charSequenceArr[3] = u(fVar.g() ? R.string.disable_tile : R.string.enable_tile);
        N(new pkg_e.z().u(charSequenceArr), 2);
        return true;
    }
}
