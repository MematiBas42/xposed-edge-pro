package d;

import android.view.View;
import android.widget.CompoundButton;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public class z extends e.j implements a.p.a {
    private a.z M;

    class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f375a;

        a(int i) {
            this.f375a = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            z.this.M.q0(compoundButton.getContext(), this.f375a, z);
        }
    }

    @Override // e.j0.c
    protected void B() {
        super.B();
        c0(R.string.action_game_mode);
    }

    @Override // e.j
    protected int B0() {
        this.M = g().h();
        return 2;
    }

    @Override // e.j
    protected boolean N0(int i) {
        return true;
    }

    @Override // e.j
    protected View h1(int i) {
        return new e.j.k(this, u(i == 0 ? R.string.ignore_edges : R.string.intercept_keys));
    }

    @Override // e.j
    protected View i1(int i) {
        int i2;
        int i3;
        if (I0() == 0) {
            i2 = a.p.a.f10a[i];
            i3 = a.p.a.f12c[i];
        } else {
            i2 = a.p.a.f11b[i];
            i3 = a.p.a.f13d[i];
        }
        e.j.ViewOnTouchListenerC0004j viewOnTouchListenerC0004j = new e.j.ViewOnTouchListenerC0004j(u(i2), null, this.M.g0(i3));
        viewOnTouchListenerC0004j.setOnCheckedChangeListener(new a(i3));
        return viewOnTouchListenerC0004j;
    }

    @Override // e.j
    protected void k1(int i) {
        y1(i, i == 0 ? a.p.a.f10a.length : a.p.a.f11b.length);
    }

    @Override // e.j
    protected void o1(int i) {
        ((e.j.ViewOnTouchListenerC0004j) K0(i)).h();
    }
}
