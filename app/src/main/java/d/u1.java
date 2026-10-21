package d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class u1 extends c implements d.a {
    private a.s.f M;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K1(a.s sVar, CompoundButton compoundButton, boolean z) {
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

    @Override // e.j0.c
    protected void B() {
        super.B();
        c0(R.string.schedule);
        S(R.drawable.ic_save, new View.OnClickListener() { // from class: d.s1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.z.L1(view);
            }
        });
    }

    @Override // e.j
    protected int B0() {
        if (this.M == null) {
            this.M = a.s.f.h();
        }
        return this.M.n() + 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:63:0x01d8  */
    @Override // d.c
    protected void C1(Bundle bundle, int i) {
        a.s sVarG;
        e.j.k kVar;
        CharSequence charSequenceP;
        e.b0 b0VarV;
        int i2;
        int i3;
        a.s.f fVar;
        a.s bVar;
        int iE0 = E0();
        String string = null;
        switch (i) {
            case 1:
                sVarG = this.M.g(iE0);
                sVarG.A = bundle.getInt("hour", sVarG.A);
                sVarG.B = bundle.getInt("minute", sVarG.B);
                ((e.j.k) K0(0)).setText(sVarG.b());
                kVar = (e.j.k) L0(iE0);
                charSequenceP = sVarG.d();
                kVar.setText(charSequenceP);
                h().putBoolean("changed", true);
                break;
            case 2:
                a.s.c cVar = (a.s.c) this.M.g(iE0);
                cVar.F = bundle.getInt("result", 0);
                kVar = (e.j.k) K0(1);
                charSequenceP = f.j.p(cVar.F);
                kVar.setText(charSequenceP);
                h().putBoolean("changed", true);
                break;
            case 3:
                int i4 = bundle.getInt("result", -1);
                a.s.b bVar2 = (a.s.b) this.M.g(iE0);
                if (i4 == 0) {
                    b0VarV = new e.b0().v(f.j.j(), bVar2.F);
                    i2 = 4;
                } else if (i4 == 1) {
                    b0VarV = new e.b0().v(f.j.e(M0()), bVar2.G);
                    i2 = 5;
                }
                N(b0VarV, i2);
                break;
            case 4:
                a.s.b bVar3 = (a.s.b) this.M.g(iE0);
                bVar3.F = bundle.getInt("result", bVar3.F);
                kVar = (e.j.k) K0(1);
                charSequenceP = f.j.k(bVar3.F);
                kVar.setText(charSequenceP);
                h().putBoolean("changed", true);
                break;
            case 5:
                a.s.b bVar4 = (a.s.b) this.M.g(iE0);
                bVar4.G = bundle.getInt("result", bVar4.G);
                ((e.j.k) K0(1)).setSubText(f.j.f(M0(), bVar4.G));
                h().putBoolean("changed", true);
                break;
            case 6:
                a.b bVar5 = (a.b) bundle.getParcelable("result");
                if (bVar5 != null) {
                    this.M.g(iE0).C = bVar5;
                    e.j.g gVar = (e.j.g) K0(2);
                    Context context = gVar.getContext();
                    gVar.setText(bVar5.n(context));
                    gVar.setImageDrawable(D0(bVar5));
                    ((e.j.k) L0(iE0)).setSubText(bVar5.n(context));
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
                            ((e.j.ViewOnTouchListenerC0004j) L0(iE0)).setChecked(false);
                            break;
                        case R.string.enable /* 2131099892 */:
                            ((e.j.ViewOnTouchListenerC0004j) L0(iE0)).setChecked(true);
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
                            N(new e.y().K(u(R.string.enter_name), null, this.M.g(iE0).D), 8);
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
                kVar = (e.j.k) L0(iE0);
                charSequenceP = sVarG.d();
                kVar.setText(charSequenceP);
                h().putBoolean("changed", true);
                break;
            case 9:
                int i6 = bundle.getInt("result", -1);
                if (i6 == 0) {
                    fVar = this.M;
                    bVar = new a.s.c();
                } else if (i6 == 1) {
                    fVar = this.M;
                    bVar = new a.s.b();
                }
                fVar.f(bVar);
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

    @Override // e.j
    protected boolean N0(int i) {
        return i < this.M.n();
    }

    @Override // e.j
    protected View h1(int i) {
        if (i >= this.M.n()) {
            return b1();
        }
        final a.s sVarG = this.M.g(i);
        e.j.ViewOnTouchListenerC0004j viewOnTouchListenerC0004j = new e.j.ViewOnTouchListenerC0004j(sVarG.d(), sVarG.C.n(M0()), sVarG.z);
        viewOnTouchListenerC0004j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: d.t1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f367a.K1(sVarG, compoundButton, z);
            }
        });
        return viewOnTouchListenerC0004j;
    }

    @Override // e.j
    protected View i1(int i) {
        a.s sVarG = this.M.g(I0());
        if (i == 0) {
            return new e.j.k(this, sVarG.b());
        }
        if (i != 1) {
            if (i != 2) {
                return new e.j.k(this, null);
            }
        } else {
            if (sVarG instanceof a.s.c) {
                return new e.j.k(this, f.j.p(((a.s.c) sVarG).F));
            }
            if (sVarG instanceof a.s.b) {
                a.s.b bVar = (a.s.b) sVarG;
                return new e.j.k(f.j.k(bVar.F), f.j.f(M0(), bVar.G));
            }
        }
        return new e.j.g(sVarG.C.n(M0()), (CharSequence) null, D0(sVarG.C));
    }

    @Override // e.j
    protected void k1(int i) {
        if (i < this.M.n()) {
            y1(i, 3);
        } else {
            N(new e.z().u(new CharSequence[]{u(R.string.as_week), u(R.string.as_date)}), 9);
        }
    }

    @Override // e.j
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
        N(new e.z().u(charSequenceArr), 7);
        return true;
    }

    @Override // e.j
    protected void o1(int i) {
        a.s sVarG = this.M.g(I0());
        if (i == 0) {
            N(new e.n0().w(null, sVarG.A, sVarG.B), 1);
            return;
        }
        if (i != 1) {
            if (i != 2) {
                return;
            }
            P(new d().I1(6, u(R.string.schedule), this.M.g(I0()).d()), 6);
        } else if (sVarG instanceof a.s.c) {
            N(new e.b0().v(f.j.o(), ((a.s.c) sVarG).F), 2);
        } else if (sVarG instanceof a.s.b) {
            N(new e.z().u(new CharSequence[]{u(R.string.modify_months), u(R.string.modify_days_of_month)}), 3);
        }
    }

    @Override // e.j
    protected boolean p1(int i) {
        if (i != 2) {
            return true;
        }
        F1(this.M.g(I0()).C, 6, 6);
        return true;
    }

    @Override // e.j0.c
    protected void y() {
        if (h().getBoolean("changed", false)) {
            N(new e.m().u(u(R.string.check_save_message)), 10);
        } else {
            super.y();
        }
    }
}
