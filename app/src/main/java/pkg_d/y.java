package pkg_d;

import android.os.Bundle;
import com.jozein.xedgepro.ApplicationMain;
import com.jozein.xedgepro.R;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class y extends pkg_e.j implements pkg_e.j.d {
    private a M;

    private static class a implements pkg_f.z.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final List<pkg_f.c0.d> f371a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final pkg_f.z f372b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private WeakReference<y> f373c;

        a(y yVar, List<pkg_f.c0.d> list) {
            this.f371a = list;
            pkg_f.z zVarK = pkg_f.z.K(yVar.f());
            this.f372b = zVarK;
            zVarK.n0(this);
            g(yVar);
        }

        static a d(y yVar, Object obj) {
            if (!(obj instanceof a)) {
                return null;
            }
            a aVar = (a) obj;
            pkg_f.z.k(aVar.f371a);
            aVar.g(yVar);
            return aVar;
        }

        @Override // pkg_f.z.e
        public void a(pkg_f.c0.d dVar) {
            f(dVar);
        }

        @Override // pkg_f.z.e
        public void b(String str, int i) {
            int iI1 = y.I1(this.f371a, str, i);
            if (iI1 >= 0) {
                this.f371a.remove(iI1);
                y yVarE = e();
                if (yVarE != null) {
                    yVarE.u1(iI1);
                }
            }
        }

        @Override // pkg_f.z.e
        public void c(pkg_f.c0.d dVar) {
            f(dVar);
        }

        y e() {
            WeakReference<y> weakReference = this.f373c;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        void f(pkg_f.c0.d dVar) {
            y yVarE = e();
            int iIndexOf = this.f371a.indexOf(dVar);
            int i = pkg_f.z.i(this.f371a, dVar);
            if (i < 0) {
                i ^= -1;
            }
            if (iIndexOf < 0) {
                this.f371a.add(i, dVar);
                if (yVarE != null) {
                    yVarE.x0(i);
                    return;
                }
                return;
            }
            if (i > iIndexOf) {
                i--;
            }
            if (iIndexOf == i) {
                this.f371a.set(iIndexOf, dVar);
                if (yVarE != null) {
                    yVarE.Q0(iIndexOf);
                    return;
                }
                return;
            }
            this.f371a.remove(iIndexOf);
            this.f371a.add(i, dVar);
            if (yVarE != null) {
                yVarE.u1(iIndexOf);
                yVarE.x0(i);
            }
        }

        void g(y yVar) {
            this.f373c = new WeakReference<>(yVar);
        }

        void h() {
            this.f372b.n0(null);
        }
    }

    private void H1() {
        a aVar = this.M;
        if (aVar != null) {
            aVar.h();
            this.M = null;
        }
    }

    static int I1(List<pkg_f.c0.d> list, String str, int i) {
        if (str == null) {
            return -1;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            pkg_f.c0.d dVar = list.get(i2);
            if (str.equals(dVar.i()) && i == dVar.k()) {
                return i2;
            }
        }
        return -1;
    }

    private static boolean J1(pkg_f.c0.d dVar) {
        if (!ApplicationMain.isModuleActivated()) {
            return false;
        }
        if (!dVar.m()) {
            return true;
        }
        String strI = dVar.i();
        return ((dVar.k() == 0 && pkg_f.l.CONST_PKG.equals(strI)) || "android".equals(strI) || "com.android.systemui".equals(strI)) ? false : true;
    }

    private void L1(int i, pkg_f.c0.d dVar) {
        if (i == R.string.freeze) {
            if (dVar.n()) {
                N(new pkg_e.m().u(u(R.string.unsafe_to_freeze_system_app)), 1);
                return;
            } else {
                M1(dVar, false);
                return;
            }
        }
        if (i == R.string.unfreeze) {
            if (ApplicationMain.isModuleActivated()) {
                M1(dVar, true);
                return;
            } else {
                N(new pkg_e.m().u(u(R.string.module_not_activated)), 2);
                return;
            }
        }
        if (i == R.string.launch) {
            if (ApplicationMain.isModuleActivated()) {
                pkg_a.b.t(f(), new pkg_a.b.l(dVar.i(), null, dVar.k()));
                return;
            } else if (dVar.m()) {
                pkg_f.p0.t(f(), dVar.i());
                return;
            } else {
                e0(R.string.module_not_activated);
                return;
            }
        }
        if (i == R.string.view_on_play_store) {
            pkg_f.p0.v(getActivity(), dVar.i());
            return;
        }
        if (i == R.string.app_info) {
            if (dVar.k() == pkg_f.o0.m() || !ApplicationMain.isModuleActivated()) {
                pkg_f.p0.u(f(), dVar.i());
            } else {
                pkg_a.b.s0.E(f(), 29, dVar.i(), dVar.k());
            }
        }
    }

    private void M1(pkg_f.c0.d dVar, boolean z) {
        pkg_a.b.s0.E(f(), z ? 2 : 1, dVar.i(), dVar.k());
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.freezer);
    }

    @Override // pkg_e.j
    protected int B0() {
        if (this.M == null) {
            this.M = new a(this, pkg_f.z.K(f()).Q(true));
        }
        return this.M.f371a.size();
    }

    @Override // pkg_e.j0.c
    protected void G() {
        super.G();
        H1();
    }

    @Override // pkg_e.j0.c
    protected void J(Bundle bundle, int i) {
        if (bundle == null) {
            return;
        }
        Bundle bundleH = h();
        int iI1 = I1(this.M.f371a, bundleH.getString("package"), bundleH.getInt("user_id", 0));
        if (iI1 < 0) {
            iI1 = E0();
        }
        pkg_f.c0.d dVar = this.M.f371a.get(iI1);
        if (i != 0) {
            if (i == 1 && bundle.getBoolean("result", false)) {
                M1(dVar, false);
                return;
            }
            return;
        }
        int i2 = bundle.getInt("result");
        int[] intArray = bundleH.getIntArray("item_ids");
        if (intArray == null || i2 < 0 || i2 >= intArray.length) {
            return;
        }
        L1(intArray[i2], dVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // pkg_e.j
    /* JADX INFO: renamed from: K1, reason: merged with bridge method [inline-methods] */
    public pkg_e.j.g h1(int i) {
        pkg_f.c0.d dVar = this.M.f371a.get(i);
        pkg_e.j.g gVar = new pkg_e.j.g(dVar.e(), dVar.f(), dVar.i());
        if (!dVar.m()) {
            gVar.e();
        }
        return gVar;
    }

    @Override // pkg_e.j.d
    public final String[] a() {
        return pkg_f.z.L(this.M.f371a);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        H1();
    }

    @Override // pkg_e.j
    protected void g1(Object obj) {
        if (obj instanceof List) {
            this.M = new a(this, (List) obj);
        }
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        int[] iArr;
        pkg_f.c0.d dVar = this.M.f371a.get(i);
        boolean zJ1 = J1(dVar);
        Bundle bundleH = h();
        bundleH.putString("package", dVar.i());
        bundleH.putInt("user_id", dVar.k());
        if (zJ1) {
            iArr = new int[4];
            iArr[0] = dVar.m() ? R.string.freeze : R.string.unfreeze;
            iArr[1] = R.string.launch;
            iArr[2] = R.string.view_on_play_store;
            iArr[3] = R.string.app_info;
        } else {
            iArr = new int[]{R.string.launch, R.string.view_on_play_store, R.string.app_info};
        }
        bundleH.putIntArray("item_ids", iArr);
        CharSequence[] charSequenceArr = new CharSequence[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            charSequenceArr[i2] = u(iArr[i2]);
        }
        N(new pkg_e.z().u(charSequenceArr), 0);
    }

    @Override // pkg_e.j
    protected final pkg_e.j.i m1() {
        a aVarD = a.d(this, s());
        this.M = aVarD;
        if (aVarD != null) {
            return null;
        }
        return new pkg_e.j.i.d(true);
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        a aVar = this.M;
        if (aVar != null) {
            Z(aVar);
        }
        super.onSaveInstanceState(bundle);
    }
}
