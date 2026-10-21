package com.jozein.xedgepro.xposed;

import android.content.Context;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f106a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f107b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private pkg_g.r f108c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f109d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f110e;

    f0(Context context, Handler handler, boolean z) {
        this.f106a = context;
        this.f107b = handler;
        this.f109d = z ? -16738393 : -24576;
        this.f110e = z ? 3 : 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(pkg_g.q qVar) {
        pkg_g.r rVar = this.f108c;
        if (rVar != null) {
            rVar.e(qVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        t3.n().t(this.f110e);
        this.f108c = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h() {
        if (this.f108c == null) {
            pkg_g.r rVar = new pkg_g.r(this.f106a, this.f109d);
            this.f108c = rVar;
            rVar.l(1, 0, false);
            t3.n().j(this.f108c, this.f110e);
        }
    }

    public void d(final pkg_g.q qVar) {
        this.f107b.post(new Runnable() { // from class: com.jozein.xedgepro.xposed.e0
            @Override // java.lang.Runnable
            public final void run() {
                f(qVar);
            }
        });
    }

    public void e() {
        this.f107b.post(new Runnable() { // from class: com.jozein.xedgepro.xposed.c0
            @Override // java.lang.Runnable
            public final void run() {
                g();
            }
        });
    }

    public void i() {
        this.f107b.post(new Runnable() { // from class: com.jozein.xedgepro.xposed.d0
            @Override // java.lang.Runnable
            public final void run() {
                h();
            }
        });
    }
}
