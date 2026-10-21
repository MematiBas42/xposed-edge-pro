package com.jozein.xedgepro.xposed;

import android.view.MotionEvent;
import com.jozein.xedgepro.service.BinderService;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final w1 f118a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f0 f119b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f122e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f120c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f121d = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f123f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final ArrayList<pkg_g.q> f124g = new ArrayList<>(256);

    g0(w1 w1Var) {
        this.f118a = w1Var;
        this.f119b = new f0(w1Var.T1(), w1Var.U1(), true);
    }

    void a(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (!this.f120c) {
            if (actionMasked != 0) {
                pkg_f.v.d(new IllegalStateException("Recording MotionEvent while not downed!"));
                return;
            }
            this.f120c = true;
            this.f122e = motionEvent.getDownTime();
            this.f123f = this.f118a.V1().j0();
            boolean zO = this.f118a.b2().o(6);
            this.f121d = zO;
            if (zO) {
                this.f119b.i();
            }
        }
        pkg_g.q qVar = new pkg_g.q(motionEvent, this.f122e);
        this.f124g.add(qVar);
        if (this.f121d) {
            this.f119b.d(qVar);
        }
    }

    void b(boolean z) {
        if (this.f120c) {
            this.f120c = false;
            this.f118a.a3();
            if (this.f121d) {
                this.f121d = false;
                this.f119b.e();
            }
            if (!z) {
                try {
                    BinderService.m(this.f118a.T1(), this.f123f, this.f124g);
                } catch (Throwable th) {
                    pkg_f.v.d(th);
                }
            }
            this.f124g.clear();
        }
    }
}
