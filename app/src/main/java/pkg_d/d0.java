package pkg_d;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jozein.xedgepro.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class d0 extends pkg_e.r {
    private ArrayList<pkg_g.q> E = new ArrayList<>(256);
    private boolean F = false;
    private boolean G = false;

    class a extends pkg_g.r {
        long L;
        final /* synthetic */ FrameLayout M;
        final /* synthetic */ TextView N;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, int i, FrameLayout frameLayout, TextView textView) {
            super(context, i);
            this.M = frameLayout;
            this.N = textView;
            this.L = -1L;
        }

        @Override // pkg_g.r, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            int buttonState = motionEvent.getButtonState();
            if (buttonState != 0 && buttonState != 1) {
                d0.this.r0();
                return true;
            }
            if (this.L == -1) {
                this.L = motionEvent.getDownTime();
                this.M.removeView(this.N);
            }
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (d0.this.G) {
                    d0.this.r0();
                    return true;
                }
                d0.this.G = true;
                if (d0.this.F) {
                    d0.this.q0();
                    return true;
                }
            }
            pkg_g.q qVar = new pkg_g.q(motionEvent, this.L);
            if (d0.this.E != null) {
                d0.this.E.add(qVar);
                e(qVar);
            }
            if (actionMasked == 1) {
                d0.this.G = false;
                if (d0.this.F) {
                    d0.this.q0();
                    return true;
                }
            } else if (actionMasked == 3) {
                d0.this.r0();
                return true;
            }
            invalidate();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q0() {
        ArrayList<pkg_g.q> arrayList = this.E;
        if (arrayList != null && !arrayList.isEmpty()) {
            try {
                W("result", new pkg_a.b.y0(f(), this.E, q().getConfiguration().orientation == 2));
            } catch (Throwable th) {
                g0(th);
            }
            this.E = null;
        }
        L();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r0() {
        this.G = false;
        L();
    }

    @Override // pkg_e.j0.c
    protected boolean D(int i, KeyEvent keyEvent) {
        return true;
    }

    @Override // pkg_e.j0.c
    protected boolean E(int i, KeyEvent keyEvent) {
        if (this.G) {
            this.F = true;
            if (i == 4) {
                this.E = null;
            }
        } else if (i != 4) {
            q0();
        } else {
            r0();
        }
        return true;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context context = viewGroup != null ? viewGroup.getContext() : f();
        int i = l().f638f;
        FrameLayout frameLayout = new FrameLayout(context);
        TextView textView = new TextView(context);
        textView.setText(R.string.get_gesture_hint);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        layoutParams.rightMargin = i;
        layoutParams.leftMargin = i;
        frameLayout.addView(textView, layoutParams);
        a aVar = new a(context, -16738393, frameLayout, textView);
        aVar.l(0, 0, false);
        frameLayout.addView(aVar, -1, -1);
        return frameLayout;
    }
}
