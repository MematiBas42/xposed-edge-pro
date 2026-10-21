package com.jozein.xedgepro.xposed;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Hashtable<String, pkg_f.o> f250a = new Hashtable<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f251b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static a f252c = null;

    private static class a extends pkg_f.q.a implements Runnable {
        private final pkg_f.q.a A;
        private final Context B;
        private final ArrayList<String> z = new ArrayList<>();

        a(pkg_f.q.a aVar, Context context) {
            this.A = aVar;
            this.B = context;
        }

        @Override // pkg_f.q.a
        public pkg_f.q a(String str) {
            pkg_f.o oVarF = w.f(str);
            if (oVarF != null) {
                return oVarF;
            }
            pkg_f.o oVar = (pkg_f.o) pkg_a.g.a.f(this.B).g(str);
            if (oVar != null) {
                w.e(str, oVar);
                return oVar;
            }
            this.z.add(str);
            b(this);
            pkg_f.q.a aVar = this.A;
            return aVar != null ? aVar.a(str) : super.a(str);
        }

        void b(Runnable runnable) {
            pkg_a.g.a.f(this.B).h(runnable);
        }

        @Override // java.lang.Runnable
        public void run() {
            for (String str : this.z) {
                pkg_f.o oVar = (pkg_f.o) pkg_a.g.a.f(this.B).g(str);
                if (oVar != null) {
                    w.e(str, oVar);
                }
            }
            this.z.clear();
        }
    }

    static void c() {
        f250a.clear();
        pkg_f.q.b();
    }

    static void d(Intent intent) {
        String stringExtra = intent.getStringExtra("name");
        if (stringExtra != null) {
            e(stringExtra, new pkg_f.o(stringExtra, intent.getLongExtra("last_modified", 0L), intent.getByteArrayExtra("buffer")));
            f251b = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, pkg_f.o oVar) {
        f250a.put(str, oVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static pkg_f.o f(String str) {
        return f250a.get(str);
    }

    static boolean g() {
        return f251b;
    }

    static void h(Context context) {
        if (f252c == null) {
            a aVar = new a(pkg_f.q.e(), context);
            f252c = aVar;
            pkg_f.q.k(aVar);
        }
    }
}
