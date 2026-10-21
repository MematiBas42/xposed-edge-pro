package pkg_a;

import com.jozein.xedgepro.service.SuperTileService;

/* JADX INFO: loaded from: classes.dex */
public class u {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f43c = pkg_f.l.CONST_PREFS + "tiles";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final String[] f44d = SuperTileService.F;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static b f45e = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final pkg_a.b.d f46a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final pkg_a.b.d[] f47b;

    public static class b extends u {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final SuperTileService[] f48f;

        private b() {
            super();
            this.f48f = new SuperTileService[u.f44d.length];
        }

        private void m() {
            pkg_f.p pVarC = c();
            pVarC.j();
            pVarC.d(u.f44d.length).C();
            int i = 0;
            while (true) {
                String[] strArr = u.f44d;
                if (i >= strArr.length) {
                    pVarC.n();
                    return;
                } else {
                    pVarC.c(strArr[i]).f(this.f47b[i]).C();
                    i++;
                }
            }
        }

        boolean k() {
            for (int i = 0; i < u.f44d.length; i++) {
                if (this.f48f[i] != null) {
                    return true;
                }
            }
            return false;
        }

        public void l(SuperTileService superTileService) {
            int iE = u.e(superTileService.getClass().getName());
            if (iE >= 0) {
                this.f48f[iE] = superTileService;
            }
        }

        public void n(int i, pkg_a.b.d dVar) {
            this.f47b[i] = dVar;
            m();
            SuperTileService superTileService = this.f48f[i];
            if (superTileService != null) {
                superTileService.f();
            }
        }

        public void o(SuperTileService superTileService) {
            int iE = u.e(superTileService.getClass().getName());
            if (iE >= 0) {
                this.f48f[iE] = null;
            }
        }

        public void p(String str) {
            int iE = u.e(str);
            if (iE >= 0) {
                this.f48f[iE] = null;
            }
        }

        void q() {
            boolean z = true;
            for (int i = 0; i < u.f44d.length; i++) {
                if (this.f48f[i] != null) {
                    if (z) {
                        g();
                        z = false;
                    }
                    this.f48f[i].f();
                }
            }
        }
    }

    private u() {
        this.f46a = new pkg_a.b.d(pkg_a.b.r());
        this.f47b = new pkg_a.b.d[f44d.length];
        g();
    }

    public static b d() {
        b bVar;
        synchronized (b.class) {
            if (f45e == null) {
                f45e = new b();
            }
            bVar = f45e;
        }
        return bVar;
    }

    static int e(String str) {
        int length = f44d.length;
        int length2 = str.length();
        char cCharAt = str.charAt(length2 - 1);
        char cCharAt2 = str.charAt(length2 - 2);
        for (int i = 0; i < length; i++) {
            String str2 = f44d[i];
            int length3 = str2.length();
            if (str2.charAt(length3 - 1) == cCharAt && str2.charAt(length3 - 2) == cCharAt2) {
                return i;
            }
        }
        return -1;
    }

    public static void j() {
        b bVar;
        synchronized (b.class) {
            b bVar2 = f45e;
            if (bVar2 != null && !bVar2.k()) {
                f45e = null;
            }
            bVar = f45e;
        }
        if (bVar != null) {
            bVar.q();
        }
    }

    public pkg_a.b.d a(int i) {
        return this.f47b[i];
    }

    public pkg_a.b.d b(String str) {
        int iE = e(str);
        return iE >= 0 ? this.f47b[iE] : this.f46a;
    }

    pkg_f.p c() {
        return new pkg_f.p(f43c);
    }

    void f(String str, pkg_a.b.d dVar) {
        int iE = e(str);
        if (iE >= 0) {
            this.f47b[iE] = dVar;
        }
    }

    void g() {
        for (int i = 0; i < f44d.length; i++) {
            this.f47b[i] = this.f46a;
        }
        pkg_f.p pVarC = c();
        if (pVarC.E()) {
            int iH = pVarC.h();
            for (int i2 = 0; i2 < iH && pVarC.t(); i2++) {
                f(pVarC.a(), (pkg_a.b.d) pVarC.g());
            }
        }
        pVarC.m();
    }

    public String h(int i) {
        return f44d[i];
    }

    public int i() {
        return this.f47b.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        int length = f44d.length;
        sb.append('[');
        sb.append(length);
        sb.append(" tiles ");
        for (int i = 0; i < length; i++) {
            sb.append('[');
            sb.append(f44d[i]);
            sb.append(' ');
            sb.append(this.f47b[i].K);
            sb.append("] ");
        }
        sb.append(']');
        return sb.toString();
    }
}
