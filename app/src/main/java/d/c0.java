package d;

import android.content.Context;
import android.os.Bundle;
import android.widget.CompoundButton;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class c0 extends c implements a.p.b, d.a {
    private a.z M;
    private CharSequence[] N = null;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J1(int i, CompoundButton compoundButton, boolean z) {
        Context context = compoundButton.getContext();
        this.M.o0(context, a.p.b.h[i], z);
        if (z) {
            a.b.s0.C(context, 8, a.p.b.f14e[i]);
        }
    }

    @Override // e.j0.c
    protected void B() {
        super.B();
        c0(R.string.gestures);
    }

    @Override // e.j
    protected int B0() {
        this.N = new CharSequence[]{u(R.string.gesture_tap), u(R.string.gesture_double_tap), u(R.string.gesture_long_press), u(R.string.gesture_swipe_left), u(R.string.gesture_swipe_right), u(R.string.gesture_swipe_up), u(R.string.gesture_swipe_down)};
        this.M = g().h();
        return a.p.b.f14e.length;
    }

    @Override // d.c
    protected void C1(Bundle bundle, int i) {
        if (i == 1) {
            int i2 = bundle.getInt("result");
            if (i2 <= 1) {
                ((e.j.ViewOnTouchListenerC0004j) L0(E0())).setChecked(i2 == 0);
                return;
            }
            return;
        }
        if (i != 2) {
            return;
        }
        Context contextM0 = M0();
        a.b bVar = (a.b) bundle.getParcelable("result");
        if (bVar != null) {
            int iF0 = F0();
            this.M.l0(contextM0, 0, a.p.b.f14e[I0()], iF0, bVar);
            e.j.g gVar = (e.j.g) K0(iF0);
            gVar.setSubText(bVar.n(contextM0));
            gVar.setImageDrawable(D0(bVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // e.j
    /* JADX INFO: renamed from: K1, reason: merged with bridge method [inline-methods] */
    public e.j.k h1(final int i) {
        int i2 = a.p.b.f14e[i];
        e.j.f fVar = (i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3) ? new e.j.f(C0(a.p.b.f15f[i]), u(a.p.b.f16g[i]), u(R.string.low_priority), this.M.o(a.p.b.h[i])) : new e.j.f(C0(a.p.b.f15f[i]), u(a.p.b.f16g[i]), (CharSequence) null, this.M.o(a.p.b.h[i]));
        fVar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: d.b0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f321a.J1(i, compoundButton, z);
            }
        });
        return fVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // e.j
    /* JADX INFO: renamed from: L1, reason: merged with bridge method [inline-methods] */
    public e.j.k i1(int i) {
        int i2 = a.p.b.f14e[I0()];
        Context contextM0 = M0();
        a.b bVarI = this.M.i(i2, i);
        e.j.g gVar = new e.j.g(this.N[i], bVarI.n(contextM0), D0(bVarI));
        if (!a.p.E(i2, i)) {
            return gVar;
        }
        a.b.q qVarE = a.b.e();
        e.j.g gVar2 = new e.j.g(this.N[i], qVarE.n(contextM0), D0(qVarE));
        gVar2.setOnTouchListener(e.j.L);
        gVar2.e();
        return gVar2;
    }

    @Override // e.j
    protected boolean N0(int i) {
        return true;
    }

    @Override // e.j
    protected void k1(int i) {
        y1(i, this.N.length);
    }

    @Override // e.j
    protected boolean l1(int i) {
        N(new e.z().v(new CharSequence[]{u(R.string.enable), u(R.string.disable)}, !this.M.o(a.p.b.h[i]) ? 1 : 0), 1);
        return true;
    }

    @Override // e.j
    protected void o1(int i) {
        int iI0 = I0();
        if (a.p.E(a.p.b.f14e[iI0], i)) {
            return;
        }
        P(new d().I1(i != 0 ? 2 : 1, u(a.p.b.f16g[iI0]), this.N[i]), 2);
    }

    @Override // e.j
    protected boolean p1(int i) {
        F1(this.M.i(a.p.b.f14e[I0()], i), 2, i != 0 ? 2 : 1);
        H1(((Object) u(a.p.b.f16g[I0()])) + "/" + ((Object) this.N[i]));
        return true;
    }
}
