package pkg_d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class u1 extends c implements pkg_d.d.a {
    private pkg_a.s.f M;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K1(pkg_a.s sVar, CompoundButton compoundButton, boolean z) {
        if (sVar.z != z) {
            sVar.z = z;
            h().putBoolean("changed", true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L1(View view) {
        M1();
    }

    private void M1() {
        if (h().getBoolean("changed", false)) {
            try {
                this.M.m(f());
            } catch (Throwable th) {
                g0(th);
            }
            h().putBoolean("changed", false);
        }
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.schedule);
        S(R.drawable.ic_save, new View.OnClickListener() { // from class: d.s1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                u1.this.L1(view);
            }
        });
    }

    @Override // pkg_e.j
    protected int B0() {
        if (this.M == null) {
            this.M = pkg_a.s.f.h();
        }
        return this.M.n() + 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:63:0x01d8  */
    @Override // pkg_d.c
    protected void C1(Bundle bundle, int i) {
        pkg_a.s sVarG;
        pkg_e.j.k kVar;
        CharSequence charSequenceP;
        pkg_e.b0 b0VarV;
        int i2;
        int i3;
        pkg_a.s.f fVar;
        pkg_a.s bVar;
        int iE0 = E0();
        String string = null;
        switch (i) {
            case 1:
                sVarG = this.M.g(iE0);
                sVarG.A = bundle.getInt("hour", sVarG.A);
                sVarG.B = bundle.getInt("minute", sVarG.B);
                ((pkg_e.j.k) K0(0)).setText(sVarG.b());
                kVar = (pkg_e.j.k) L0(iE0);
                charSequenceP = sVarG.d();
                kVar.setText(charSequenceP);
                h().putBoolean("changed", true);
                break;
            case 2:
                pkg_a.s.c cVar = (pkg_a.s.c) this.M.g(iE0);
                cVar.F = bundle.getInt("result", 0);
                kVar = (pkg_e.j.k) K0(1);
                charSequenceP = pkg_f.j.p(cVar.F);
                kVar.setText(charSequenceP);
                h().putBoolean("changed", true);
                break;
            case 3:
                int i4 = bundle.getInt("result", -1);
                pkg_a.s.b bVar2 = (pkg_a.s.b) this.M.g(iE0);
                if (i4 == 0) {
                    N(new pkg_e.b0().v(pkg_f.j.j(), bVar2.F), 4);
                } else if (i4 == 1) {
                    N(new pkg_e.b0().v(pkg_f.j.e(M0()), bVar2.G), 5);
                }
                break;
            case 4:
                pkg_a.s.b bVar3 = (pkg_a.s.b) this.M.g(iE0);
                bVar3.F = bundle.getInt("result", bVar3.F);
                kVar = (pkg_e.j.k) K0(1);
                charSequenceP = pkg_f.j.k(bVar3.F);
                kVar.setText(charSequenceP);
                h().putBoolean("changed", true);
                break;
            case 5:
                pkg_a.s.b bVar4 = (pkg_a.s.b) this.M.g(iE0);
                bVar4.G = bundle.getInt("result", bVar4.G);
                ((pkg_e.j.k) K0(1)).setSubText(pkg_f.j.f(M0(), bVar4.G));
                h().putBoolean("changed", true);
                break;
            case 6:
                pkg_a.b bVar5 = (pkg_a.b) bundle.getParcelable("result");
                if (bVar5 != null) {
                    this.M.g(iE0).C = bVar5;
                    pkg_e.j.g gVar = (pkg_e.j.g) K0(2);
                    Context context = gVar.getContext();
                    gVar.setText(bVar5.n(context));
                    gVar.setImageDrawable(D0(bVar5));
                    ((pkg_e.j.k) L0(iE0)).setSubText(bVar5.n(context));
                    h().putBoolean("changed", true);
                    break;
                }
                break;
            case 7:
                int i5 = bundle.getInt("result");
                int[] intArray = h().getIntArray("dialog_items");
                if (intArray != null) {
                    switch (intArray[i5]) {
                        case R.string.delete /* 2131099853 */:
                            this.M.l(iE0);
                            u1(iE0);
                            h().putBoolean("changed", true);
                            break;
                        case R.string.disable /* 2131099856 */:
                            ((pkg_e.j.ViewOnTouchListenerC0004j) L0(iE0)).setChecked(false);
                            break;
                        case R.string.enable /* 2131099892 */:
                            ((pkg_e.j.ViewOnTouchListenerC0004j) L0(iE0)).setChecked(true);
                            break;
                        case R.string.move_down /* 2131099992 */:
                            i3 = iE0 + 1;
                            if (i3 < this.M.n()) {
                                this.M.i(iE0);
                                a1(iE0, i3);
                                h().putBoolean("changed", true);
                                break;
                            }
                            break;
                        case R.string.move_up /* 2131099993 */:
                            if (iE0 >= 1) {
                                this.M.j(iE0);
                                i3 = iE0 - 1;
                                a1(iE0, i3);
                                h().putBoolean("changed", true);
                                break;
                            }
                            break;
                        case R.string.rename /* 2131100034 */:
                            N(new pkg_e.y().K(u(R.string.enter_name), null, this.M.g(iE0).D), 8);
                            break;
                        default:
                            h().putBoolean("changed", true);
                            break;
                    }
                }
                break;
            case 8:
                sVarG = this.M.g(iE0);
                CharSequence charSequence = bundle.getCharSequence("result");
                if (charSequence != null && charSequence.length() != 0) {
                    string = charSequence.toString();
                }
                sVarG.D = string;
                kVar = (pkg_e.j.k) L0(iE0);
                charSequenceP = sVarG.d();
                kVar.setText(charSequenceP);
                h().putBoolean("changed", true);
                break;
            case 9:
                int i6 = bundle.getInt("result", -1);
                if (i6 == 0) {
                    this.M.f(new pkg_a.s.c());
                } else if (i6 == 1) {
                    this.M.f(new pkg_a.s.b());
                } else {
                    break;
                }
                x0(this.M.n() - 1);
                h().putBoolean("changed", true);
                break;
            case 10:
                if (bundle.getBoolean("result", false)) {
                    M1();
                }
                L();
                break;
            default:
                h().putBoolean("changed", true);
                break;
        }
    }

    @Override // pkg_e.j
    protected boolean N0(int i) {
        return i < this.M.n();
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        if (i >= this.M.n()) {
            return b1();
        }
        final pkg_a.s sVarG = this.M.g(i);
        pkg_e.j.ViewOnTouchListenerC0004j viewOnTouchListenerC0004j = new pkg_e.j.ViewOnTouchListenerC0004j(sVarG.d(), sVarG.C.n(M0()), sVarG.z);
        viewOnTouchListenerC0004j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: d.t1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                u1.this.K1(sVarG, compoundButton, z);
            }
        });
        return viewOnTouchListenerC0004j;
    }

    @Override // pkg_e.j
    protected View i1(int i) {
        pkg_a.s sVarG = this.M.g(I0());
        if (i == 0) {
            return new pkg_e.j.k(this, sVarG.b());
        }
        if (i != 1) {
            if (i != 2) {
                return new pkg_e.j.k(this, null);
            }
        } else {
            if (sVarG instanceof pkg_a.s.c) {
                return new pkg_e.j.k(this, pkg_f.j.p(((pkg_a.s.c) sVarG).F));
            }
            if (sVarG instanceof pkg_a.s.b) {
                pkg_a.s.b bVar = (pkg_a.s.b) sVarG;
                return new pkg_e.j.k(pkg_f.j.k(bVar.F), pkg_f.j.f(M0(), bVar.G));
            }
        }
        return new pkg_e.j.g(sVarG.C.n(M0()), (CharSequence) null, D0(sVarG.C));
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        if (i < this.M.n()) {
            y1(i, 3);
        } else {
            N(new pkg_e.z().u(new CharSequence[]{u(R.string.as_week), u(R.string.as_date)}), 9);
        }
    }

    @Override // pkg_e.j
    protected boolean l1(int i) {
        if (i >= this.M.n()) {
            return super.l1(i);
        }
        int[] iArr = new int[5];
        iArr[0] = this.M.g(i).z ? R.string.disable : R.string.enable;
        iArr[1] = R.string.rename;
        iArr[2] = R.string.move_up;
        iArr[3] = R.string.move_down;
        iArr[4] = R.string.delete;
        h().putIntArray("dialog_items", iArr);
        CharSequence[] charSequenceArr = new CharSequence[5];
        for (int i2 = 0; i2 < 5; i2++) {
            charSequenceArr[i2] = u(iArr[i2]);
        }
        N(new pkg_e.z().u(charSequenceArr), 7);
        return true;
    }

    @Override // pkg_e.j
    protected void o1(int i) {
        pkg_a.s sVarG = this.M.g(I0());
        if (i == 0) {
            N(new pkg_e.n0().w(null, sVarG.A, sVarG.B), 1);
            return;
        }
        if (i != 1) {
            if (i != 2) {
                return;
            }
            P(new pkg_d.d().I1(6, u(R.string.schedule), this.M.g(I0()).d()), 6);
        } else if (sVarG instanceof pkg_a.s.c) {
            N(new pkg_e.b0().v(pkg_f.j.o(), ((pkg_a.s.c) sVarG).F), 2);
        } else if (sVarG instanceof pkg_a.s.b) {
            N(new pkg_e.z().u(new CharSequence[]{u(R.string.modify_months), u(R.string.modify_days_of_month)}), 3);
        }
    }

    @Override // pkg_e.j
    protected boolean p1(int i) {
        if (i != 2) {
            return true;
        }
        F1(this.M.g(I0()).C, 6, 6);
        return true;
    }

    @Override // pkg_e.j0.c
    protected void y() {
        if (h().getBoolean("changed", false)) {
            N(new pkg_e.m().u(u(R.string.check_save_message)), 10);
        } else {
            super.y();
        }
    }
}
