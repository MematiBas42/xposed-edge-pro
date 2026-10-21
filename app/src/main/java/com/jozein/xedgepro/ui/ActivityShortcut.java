package com.jozein.xedgepro.ui;

import pkg_a.b;
import android.os.Bundle;
import android.view.View;
import pkg_d.d;
import pkg_e.j0;
import pkg_f.l0;
import pkg_f.v;

/* JADX INFO: loaded from: classes.dex */
public class ActivityShortcut extends j0 {

    public static class a extends d {
        @Override // pkg_e.j0.c
        protected void A() {
        }

        @Override // pkg_e.j0.c
        protected void U(Bundle bundle) {
            ((ActivityShortcut) getActivity()).A(bundle);
        }

        @Override // pkg_e.j0.c
        protected void z(View view) {
        }
    }

    public void A(Bundle bundle) {
        String str;
        if (bundle != null) {
            pkg_a.b bVar = (pkg_a.b) bundle.getParcelable("result");
            if (bVar == null) {
                str = "Result action: null!";
            } else {
                setResult(-1, l0.b(this, bVar));
            }
            finish();
        }
        str = "Result == null";
        v.c(str);
        setResult(0);
        finish();
    }

    @Override // pkg_e.j0
    protected j0.c v() {
        return new a().G1(3);
    }
}
