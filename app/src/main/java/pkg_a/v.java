package pkg_a;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class v extends r {
    private static final String C = pkg_f.l.CONST_PREFS + "variables";
    private static final String D = pkg_f.l.CONST_PREFIX + "VARIABLES_CHANGED";
    private HashMap<String, pkg_f.t> B;

    public static class VarManager extends r {
        private static VarManager D;
        private final pkg_f.p B;
        private final ArrayList<String> C;

        private VarManager() {
            super(v.D);
            pkg_f.p pVar = new pkg_f.p(v.C);
            this.B = pVar;
            this.C = new ArrayList<>();
            if (pVar.E()) {
                do {
                    this.C.add(this.B.a());
                } while (this.B.t());
            }
            this.B.m();
        }

        public static VarManager g() {
            VarManager aVar;
            synchronized (a.class) {
                if (D == null) {
                    D = new VarManager();
                }
                aVar = D;
            }
            return aVar;
        }

        public static void h() {
            synchronized (a.class) {
                D = null;
            }
        }

        private void j() {
            this.B.j();
            int size = this.C.size();
            for (int i = 0; i < size; i++) {
                this.B.c(this.C.get(i)).C();
            }
            this.B.n();
        }

        @Override // pkg_a.r
        protected void b(Intent intent) {
        }

        public boolean e(Context context, String str) {
            if (this.C.contains(str)) {
                return false;
            }
            this.C.add(str);
            j();
            Intent intent = new Intent();
            new pkg_f.m(intent).j().d(0).c(str).l();
            c(context, intent);
            return true;
        }

        public String f(int i) {
            return this.C.get(i);
        }

        public void i(Context context, String str) {
            this.C.remove(str);
            j();
            Intent intent = new Intent();
            new pkg_f.m(intent).j().d(1).c(str).l();
            c(context, intent);
        }

        public int k() {
            return this.C.size();
        }
    }

    public v() {
        super(D);
        h();
    }

    @Override // pkg_a.r
    protected void b(Intent intent) {
        HashMap<String, pkg_f.t> map;
        String strA;
        pkg_f.m mVar = new pkg_f.m(intent);
        if (mVar.i()) {
            int iH = mVar.h();
            if (iH == 0) {
                String strA2 = mVar.a();
                if (strA2 != null) {
                    map = new HashMap<>(this.B);
                    map.put(strA2, new pkg_f.t(0));
                    this.B = map;
                }
            } else if (iH == 1 && (strA = mVar.a()) != null) {
                map = new HashMap<>(this.B);
                map.remove(strA);
                this.B = map;
            }
        }
        mVar.k();
    }

    public pkg_f.t g(String str) {
        return this.B.get(str);
    }

    public void h() {
        HashMap<String, pkg_f.t> map = new HashMap<>();
        pkg_f.p pVar = new pkg_f.p(C);
        if (pVar.E()) {
            do {
                map.put(pVar.a(), new pkg_f.t(0));
            } while (pVar.t());
        }
        pVar.m();
        this.B = map;
    }
}
