package com.jozein.xedgepro.xposed;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Hashtable<String, f.o> f250a = new Hashtable<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f251b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static a f252c = null;

    private static class a extends f.q.a implements Runnable {
        private final f.q.a A;
        private final Context B;
        private final ArrayList<String> z = new ArrayList<>();

        a(f.q.a aVar, Context context) {
            this.A = aVar;
            this.B = context;
        }

        @Override // f.q.a
        public f.q a(String str) {
            f.o oVarF = w.f(str);
            if (oVarF != null) {
                return oVarF;
            }
            f.o oVar = (f.o) a.g.a.f(this.B).g(str);
            if (oVar != null) {
                w.e(str, oVar);
                return oVar;
            }
            this.z.add(str);
            b(this);
            f.q.a aVar = this.A;
            return aVar != null ? aVar.a(str) : super.a(str);
        }

        void b(Runnable runnable) {
            a.g.a.f(this.B).h(runnable);
        }

        @Override // java.lang.Runnable
        public void run() {
            for (String str : this.z) {
                f.o oVar = (f.o) a.g.a.f(this.B).g(str);
                if (oVar != null) {
                    w.e(str, oVar);
                }
            }
            this.z.clear();
        }
    }

    static void c() {
        f250a.clear();
        f.q.b();
    }

    static void d(Intent intent) {
        String stringExtra = intent.getStringExtra("name");
        if (stringExtra != null) {
            e(stringExtra, new f.o(stringExtra, intent.getLongExtra("last_modified", 0L), intent.getByteArrayExtra("buffer")));
            f251b = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, f.o oVar) {
        f250a.put(str, oVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static f.o f(String str) {
        return f250a.get(str);
    }

    static boolean g() {
        return f251b;
    }

    static void h(Context context) {
        if (f252c == null) {
            a aVar = new a(f.q.e(), context);
            f252c = aVar;
            f.q.k(aVar);
        }
    }
}
