package pkg_d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class n extends pkg_e.j implements pkg_e.j.d {
    private pkg_a.b[] M;

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.select_icon);
    }

    @Override // pkg_e.j
    protected int B0() {
        pkg_a.b[] bVarArrI = pkg_a.b.i(0);
        this.M = bVarArrI;
        return bVarArrI.length;
    }

    @Override // pkg_e.j.d
    public String[] a() {
        Context contextM0 = M0();
        int length = this.M.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = this.M[i].p(contextM0).toString();
        }
        return strArr;
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        pkg_a.b bVar = this.M[i];
        return new pkg_e.j.g(D0(bVar), bVar.p(M0()), (CharSequence) null);
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        String resourceName = q().getResourceName(this.M[i].B);
        if (resourceName.indexOf(47) != -1) {
            resourceName = resourceName.substring(resourceName.indexOf(47) + 1);
        }
        Bundle bundle = new Bundle(1);
        bundle.putString("result", resourceName);
        U(bundle);
        L();
    }
}
