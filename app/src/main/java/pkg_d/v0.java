package pkg_d;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class v0 extends pkg_e.j {
    private static final int[] N = {R.string.edit, R.string.insert, R.string.move_up, R.string.move_down, R.string.delete};
    private ArrayList<Integer> M;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (v0.this.h().getBoolean("changed", false)) {
                v0.this.I1();
            }
            v0.this.L();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I1() {
        pkg_a.z zVarH = g().h();
        if (zVarH != null) {
            int size = this.M.size();
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                iArr[i] = this.M.get(i).intValue();
            }
            if (zVarH.u0(M0(), iArr)) {
                e0(R.string.saved);
            }
        }
        h().putBoolean("changed", false);
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.pen_colors);
        S(R.drawable.ic_save, new a());
    }

    @Override // pkg_e.j
    protected int B0() {
        ArrayList<Integer> integerArrayList = h().getIntegerArrayList("colors");
        this.M = integerArrayList;
        if (integerArrayList == null) {
            pkg_a.z zVarH = g().h();
            if (zVarH != null) {
                int[] iArrY = zVarH.y();
                this.M = new ArrayList<>(iArrY.length);
                for (int i : iArrY) {
                    this.M.add(Integer.valueOf(i));
                }
            } else {
                this.M = new ArrayList<>();
            }
        }
        return this.M.size() + 1;
    }

    protected void E1(int i, int i2) {
        this.M.add(i, Integer.valueOf(i2));
        x0(i);
        h().putBoolean("changed", true);
    }

    protected void F1(int i, boolean z) {
        int i2 = z ? i - 1 : i + 1;
        ArrayList<Integer> arrayList = this.M;
        arrayList.add(i2, arrayList.remove(i));
        a1(i, i2);
        h().putBoolean("changed", true);
    }

    protected void G1(int i) {
        this.M.remove(i);
        u1(i);
        h().putBoolean("changed", true);
    }

    protected void H1(int i, int i2) {
        this.M.set(i, Integer.valueOf(i2));
        pkg_e.j.g gVar = (pkg_e.j.g) L0(i);
        gVar.setImageDrawable(new ColorDrawable(i2));
        gVar.setText(o1.T1(i2));
        h().putBoolean("changed", true);
    }

    @Override // pkg_e.j0.c
    protected void J(Bundle bundle, int i) {
        if (bundle == null) {
        }
        int iE0 = E0();
        if (i != 1) {
            if (i == 2) {
                E1(this.M.size(), bundle.getInt("result"));
                return;
            }
            if (i == 3) {
                E1(iE0, bundle.getInt("result"));
                return;
            }
            if (i == 4) {
                if (bundle.getInt("result", 0) != 0) {
                    H1(iE0, bundle.getInt("result"));
                    return;
                } else {
                    e0(R.string.invalid_color);
                    return;
                }
            }
            if (i != 5) {
                return;
            }
            if (bundle.getBoolean("result", false)) {
                I1();
            }
            L();
            return;
        }
        int i2 = bundle.getInt("result", -1);
        if (i2 >= 0) {
            switch (N[i2]) {
                case R.string.delete /* 2131099853 */:
                    G1(iE0);
                    break;
                case R.string.edit /* 2131099885 */:
                    N(new pkg_e.o().G(this.M.get(iE0).intValue()), 4);
                    break;
                case R.string.insert /* 2131099949 */:
                    N(new pkg_e.o(), 3);
                    break;
                case R.string.move_down /* 2131099992 */:
                    if (iE0 < this.M.size() - 1) {
                        F1(iE0, false);
                    }
                    break;
                case R.string.move_up /* 2131099993 */:
                    if (iE0 > 0) {
                        F1(iE0, true);
                    }
                    break;
            }
        }
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        return i < this.M.size() ? new pkg_e.j.g(new ColorDrawable(this.M.get(i).intValue()), o1.T1(this.M.get(i).intValue()), (CharSequence) null) : b1();
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        pkg_e.j0.b oVar;
        int i2;
        if (i < this.M.size()) {
            CharSequence[] charSequenceArr = new CharSequence[N.length];
            int i3 = 0;
            while (true) {
                int[] iArr = N;
                if (i3 >= iArr.length) {
                    break;
                }
                charSequenceArr[i3] = u(iArr[i3]);
                i3++;
            }
            oVar = new pkg_e.z().u(charSequenceArr);
            i2 = 1;
        } else {
            oVar = new pkg_e.o();
            i2 = 2;
        }
        N(oVar, i2);
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        h().putIntegerArrayList("colors", this.M);
        super.onSaveInstanceState(bundle);
    }

    @Override // pkg_e.j0.c
    protected void y() {
        if (h().getBoolean("changed", false)) {
            N(new pkg_e.m().u(u(R.string.check_save_message)), 5);
        } else {
            super.y();
        }
    }
}
