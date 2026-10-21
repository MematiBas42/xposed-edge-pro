package pkg_d;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class x extends e {
    private AppWidgetManager M;
    private pkg_a.x N;
    private ArrayList<Integer> O;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F1(View view) {
        N(new pkg_e.a0().u(u(R.string.action_floating_widget), u(R.string.widget_hint)), 0);
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.select_floating_widget);
        S(R.drawable.ic_action_bar_help, new View.OnClickListener() { // from class: d.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                x.this.F1(view);
            }
        });
    }

    @Override // pkg_e.j
    protected int B0() {
        this.M = AppWidgetManager.getInstance(f());
        pkg_a.x xVarH = pkg_a.x.h();
        this.N = xVarH;
        ArrayList<Integer> arrayListI = xVarH.i();
        this.O = arrayListI;
        for (int size = arrayListI.size() - 1; size >= 0; size--) {
            if (this.M.getAppWidgetInfo(this.O.get(size).intValue()) == null) {
                try {
                    this.N.m(this.O.remove(size).intValue());
                } catch (Throwable th) {
                    g0(th);
                }
            }
        }
        return this.O.size() + 1;
    }

    @Override // pkg_d.e
    protected void B1(Bundle bundle, int i) {
        AppWidgetProviderInfo appWidgetInfo;
        try {
            if (i == 1) {
                int i2 = bundle.getInt("result", 0);
                if (i2 == 0 || (appWidgetInfo = this.M.getAppWidgetInfo(i2)) == null || this.O.contains(Integer.valueOf(i2))) {
                    return;
                }
                this.N.a(i2, appWidgetInfo, f());
                this.O.add(Integer.valueOf(i2));
                x0(this.O.size() - 1);
            } else if (i == 2) {
                int i3 = bundle.getInt("result", -1);
                int iE0 = E0();
                pkg_a.b.m0 m0Var = new pkg_a.b.m0(this.O.get(E0()).intValue());
                if (i3 == 0) {
                    C1(m0Var);
                    return;
                }
                if (i3 == 1) {
                    pkg_a.b.t(f(), m0Var);
                    return;
                }
                if (i3 != 2) {
                    if (i3 != 3) {
                        return;
                    }
                    N(new pkg_e.m().u(u(R.string.check_clear_all)), 3);
                } else {
                    int iIntValue = this.O.get(iE0).intValue();
                    new AppWidgetHost(f(), 1).deleteAppWidgetId(iIntValue);
                    this.N.m(iIntValue);
                    this.O.remove(iE0);
                    u1(iE0);
                }
            } else {
                if (i != 3) {
                    return;
                }
                new AppWidgetHost(f(), 1).deleteHost();
                this.N.b();
                this.O.clear();
                w1();
            }
        } catch (Throwable th) {
            g0(th);
        }
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        if (i >= this.O.size()) {
            return b1();
        }
        try {
            return f1(this.M.getAppWidgetInfo(this.O.get(i).intValue()));
        } catch (Throwable th) {
            pkg_f.v.d(th);
            return f1(null);
        }
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        if (i < this.O.size()) {
            C1(new pkg_a.b.m0(this.O.get(i).intValue()));
        } else {
            P(new f2().b0(o()), 1);
        }
    }

    @Override // pkg_e.j
    protected boolean l1(int i) {
        if (i >= this.O.size()) {
            return super.l1(i);
        }
        pkg_e.z zVar = new pkg_e.z();
        zVar.u(new CharSequence[]{u(R.string.select), u(R.string.perform), u(R.string.delete), u(R.string.delete_all)});
        N(zVar, 2);
        return true;
    }
}
