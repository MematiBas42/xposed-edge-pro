package com.jozein.xedgepro.xposed;

/* JADX INFO: loaded from: classes.dex */
class n3 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f192c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f.p f193a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f194b;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(p2.A);
        sb.append(f.l.r ? "sys_prefs_pro" : "sys_prefs");
        f192c = sb.toString();
    }

    n3() {
        f.p pVar = new f.p(f192c);
        this.f193a = pVar;
        this.f194b = new int[1];
        if (pVar.E()) {
            for (int i = 0; i < 1; i++) {
                this.f194b[i] = this.f193a.h();
            }
        }
        this.f193a.m();
    }

    private void b() {
        boolean z;
        int i = 0;
        while (true) {
            if (i >= 1) {
                z = true;
                break;
            } else {
                if (this.f194b[i] != 0) {
                    z = false;
                    break;
                }
                i++;
            }
        }
        if (z) {
            try {
                this.f193a.H().delete();
                return;
            } catch (Throwable th) {
                f.v.d(th);
            }
        }
        this.f193a.j();
        for (int i2 = 0; i2 < 1; i2++) {
            this.f193a.d(this.f194b[i2]);
        }
        this.f193a.n();
    }

    public int a(int i) {
        return this.f194b[i];
    }

    public void c(int i, int i2) {
        int[] iArr = this.f194b;
        if (iArr[i] != i2) {
            iArr[i] = i2;
            b();
        }
    }
}
