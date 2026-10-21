package com.jozein.xedgepro.ui;

import a.b;
import android.os.Bundle;
import android.view.View;
import d.d;
import e.j0;
import f.l0;
import f.v;

/* JADX INFO: loaded from: classes.dex */
public class ActivityShortcut extends j0 {

    public static class a extends d {
        @Override // e.j0.c
        protected void A() {
        }

        @Override // e.j0.c
        protected void U(Bundle bundle) {
            ((ActivityShortcut) getActivity()).A(bundle);
        }

        @Override // e.j0.c
        protected void z(View view) {
        }
    }

    public void A(Bundle bundle) {
        String str;
        if (bundle != null) {
            b bVar = (b) bundle.getParcelable("result");
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

    @Override // e.j0
    protected j0.c v() {
        return new a().G1(3);
    }
}
