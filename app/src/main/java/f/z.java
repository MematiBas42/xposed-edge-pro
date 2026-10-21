package f;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import com.jozein.xedgepro.ApplicationMain;
import com.jozein.xedgepro.ui.ActivityPerformAction;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static volatile z f496d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f497e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final f f498f = new f();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final Comparator<ResolveInfo> f499g = new Comparator() { // from class: f.x
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return z.Y((ResolveInfo) obj, (ResolveInfo) obj2);
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f500a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final PackageManager f501b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private e f502c;

    /* JADX INFO: Access modifiers changed from: private */
    static class b extends z {
        private final boolean h;
        private int i;
        private final List<h> j;
        private int[] k;
        private List<c0.d> l;
        private List<c0.d> m;

        class a implements Application.ActivityLifecycleCallbacks {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private boolean f503a = false;

            a() {
            }

            private boolean b(Activity activity) {
                return (activity == null || activity.getClass() == ActivityPerformAction.class) ? false : true;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void c() {
                if (b.this.z0()) {
                    b.this.L0();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                if (b(activity) && bundle == null) {
                    if (b.this.i > 0) {
                        b.s0(b.this);
                    } else {
                        b.this.i = 1;
                        this.f503a = true;
                    }
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                if (b(activity) && activity.isFinishing()) {
                    if (b.this.i > 1) {
                        b.t0(b.this);
                        return;
                    }
                    b.this.i = 0;
                    this.f503a = false;
                    b.this.w0();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                if (this.f503a) {
                    this.f503a = false;
                    h0.a().post(new Runnable() { // from class: f.a0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.z.c();
                        }
                    });
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }
        }

        /* JADX INFO: renamed from: f.z$b$b, reason: collision with other inner class name */
        class C0010b extends BroadcastReceiver {
            C0010b() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                ApplicationMain.g();
                try {
                    b.this.D0(intent);
                } catch (Throwable th) {
                    v.d(th);
                }
            }
        }

        b(Context context) {
            super(context);
            this.i = 0;
            this.j = new ArrayList();
            this.h = Build.VERSION.SDK_INT < 30 || p0.q(context, l.j, "android.permission.QUERY_ALL_PACKAGES");
            F0(context);
        }

        private void A0(g gVar) {
            int iC = gVar.c();
            int i = gVar.f508b;
            if (i == -4) {
                B0(gVar.f510d, iC);
                b0(gVar.f510d, iC);
            } else if (i == -3) {
                PackageInfo packageInfoB = gVar.b();
                C0(packageInfoB, iC);
                a0(packageInfoB, iC);
            } else {
                if (i != -2) {
                    return;
                }
                PackageInfo packageInfoB2 = gVar.b();
                C0(packageInfoB2, iC);
                Z(packageInfoB2, iC);
            }
        }

        /* JADX WARN: Bottom block not found for handler: all -> 0x001f */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void E0(g gVar) {
            List listA = gVar.a();
            if (listA != null) {
                List<c0.d> listO0 = z.o0(listA);
                Collections.sort(listO0, z.f498f);
                synchronized (this) {
                    try {
                        this.l = listO0;
                        this.m = listO0;
                        this.k = gVar.f509c;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        private void F0(Context context) {
            Application application;
            if (context instanceof Application) {
                application = (Application) context;
            } else if (context instanceof Activity) {
                application = ((Activity) context).getApplication();
            } else if (context instanceof Service) {
                application = ((Service) context).getApplication();
            } else {
                context = context.getApplicationContext();
                application = (Application) context;
            }
            application.registerActivityLifecycleCallbacks(new a());
            application.registerReceiver(new C0010b(), new IntentFilter(g.f506f), null, x0());
        }

        private h G0(int i) {
            synchronized (this.j) {
                int size = this.j.size();
                for (int i2 = 0; i2 < size; i2++) {
                    h hVar = this.j.get(i2);
                    if (hVar.f514a == i) {
                        this.j.remove(i2);
                        return hVar;
                    }
                }
                return null;
            }
        }

        private void H0(h hVar) {
            synchronized (this.j) {
                this.j.remove(hVar);
            }
        }

        private g I0(int i, int[] iArr) {
            g gVarE = null;
            if (h0.c(x0())) {
                v.d(new UnsupportedOperationException("Waiting on ui thread!!!"));
                return null;
            }
            h hVar = new h(i, iArr);
            u0(hVar);
            try {
                gVarE = hVar.e(this.f500a);
            } catch (Throwable th) {
                v.d(th);
            }
            H0(hVar);
            return gVarE;
        }

        private <T extends Parcelable> List<T> J0(int i, int[] iArr) {
            g gVarI0 = I0(i, iArr);
            if (gVarI0 == null) {
                return null;
            }
            try {
                return gVarI0.a();
            } catch (Throwable th) {
                v.d(th);
                return null;
            }
        }

        private void K0() {
            int[] iArrE = o0.e(this.f500a);
            new h(0, v0(iArrE) ? 1 : 2, iArrE).d(this.f500a);
        }

        static /* synthetic */ int s0(b bVar) {
            int i = bVar.i + 1;
            bVar.i = i;
            return i;
        }

        static /* synthetic */ int t0(b bVar) {
            int i = bVar.i - 1;
            bVar.i = i;
            return i;
        }

        private void u0(h hVar) {
            synchronized (this.j) {
                this.j.add(hVar);
            }
        }

        private boolean v0(int[] iArr) {
            if (this.h) {
                if (iArr == null || iArr.length == 0) {
                    return true;
                }
                if (iArr.length == 1 && iArr[0] == 0) {
                    return true;
                }
            }
            return false;
        }

        private List<c0.d> y0(int i) {
            if (this.h && i == 0) {
                return null;
            }
            List<c0.d> list = this.m;
            if (list != null) {
                return list;
            }
            if (!z0()) {
                K0();
            }
            return null;
        }

        void B0(String str, int i) {
            List<c0.d> list;
            int[] iArr;
            int iJ;
            synchronized (this) {
                list = this.l;
                iArr = this.k;
            }
            if (list == null || (iJ = z.j(list, str, i)) < 0) {
                return;
            }
            ArrayList arrayList = new ArrayList(list);
            arrayList.remove(iJ);
            synchronized (this) {
                this.l = arrayList;
                this.m = arrayList;
                this.k = iArr;
            }
        }

        void C0(PackageInfo packageInfo, int i) {
            List<c0.d> list;
            int[] iArr;
            synchronized (this) {
                list = this.l;
                iArr = this.k;
            }
            if (list != null) {
                ArrayList arrayList = new ArrayList(list);
                c0.d dVar = new c0.d(packageInfo);
                int iJ = z.j(arrayList, packageInfo.packageName, i);
                if (iJ >= 0) {
                    arrayList.set(iJ, dVar);
                } else {
                    arrayList.add(iJ ^ (-1), dVar);
                }
                synchronized (this) {
                    this.l = arrayList;
                    this.m = arrayList;
                    this.k = iArr;
                }
            }
        }

        void D0(Intent intent) {
            g gVarE = g.e(intent);
            if (gVarE != null) {
                if (gVarE.f508b <= 0) {
                    A0(gVarE);
                    return;
                }
                int i = gVarE.f507a;
                if (i == 0) {
                    E0(gVarE);
                    return;
                }
                h hVarG0 = G0(i);
                if (hVarG0 != null) {
                    hVarG0.a(gVarE);
                }
            }
        }

        @Override // f.z
        c0.d J(String str, int i) {
            return z.n(y0(i), str, i);
        }

        void L0() {
            K0();
        }

        @Override // f.z
        List<c0.d> S(boolean z, boolean z2) {
            List<c0.d> listO0;
            int[] iArr;
            synchronized (this) {
                listO0 = this.l;
                iArr = this.k;
            }
            int[] iArrE = o0.e(this.f500a);
            if (listO0 == null || !z.W(iArr, iArrE)) {
                try {
                    List listJ0 = J0(5, iArrE);
                    if (listJ0 != null) {
                        listO0 = z.o0(listJ0);
                        Collections.sort(listO0, z.f498f);
                        synchronized (this) {
                            this.l = listO0;
                            this.m = listO0;
                            this.k = iArrE;
                        }
                    }
                } catch (Throwable th) {
                    v.d(th);
                }
            }
            if (listO0 == null) {
                listO0 = z.o0(this.f501b.getInstalledPackages(z2 ? 33281 : 0));
            }
            List<c0.d> listL = z.l(listO0, z, z2);
            Collections.sort(listL, new d(Locale.getDefault()));
            return listL;
        }

        @Override // f.z
        List<AppWidgetProviderInfo> d0() {
            List<AppWidgetProviderInfo> listJ0;
            return (this.h || (listJ0 = J0(8, null)) == null) ? super.d0() : listJ0;
        }

        @Override // f.z
        List<ResolveInfo> g0(int[] iArr) {
            List<ResolveInfo> listJ0;
            return (v0(iArr) || (listJ0 = J0(6, iArr)) == null) ? super.g0(iArr) : listJ0;
        }

        @Override // f.z
        List<PackageInfo> h0(int i, int[] iArr) {
            if (!v0(iArr)) {
                List<PackageInfo> listJ0 = J0((i & 1) != 0 ? 5 : 4, iArr);
                if (listJ0 != null) {
                    return listJ0;
                }
            }
            return super.h0(i, iArr);
        }

        @Override // f.z
        List<ResolveInfo> i0() {
            List<ResolveInfo> listJ0;
            return (this.h || (listJ0 = J0(7, null)) == null) ? super.i0() : listJ0;
        }

        void w0() {
            synchronized (this) {
                this.l = null;
                this.k = null;
            }
            new h(0, 3, null).d(this.f500a);
        }

        Handler x0() {
            return h0.a();
        }

        boolean z0() {
            return this.i > 0;
        }
    }

    private static class c extends z {
        c(Context context) {
            super(context);
        }
    }

    private static final class d implements Comparator<c0.b> {
        private final Collator A;
        final Locale z;

        d(Locale locale) {
            this.z = locale;
            this.A = "en".equals(locale.getLanguage()) ? null : Collator.getInstance(locale);
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c0.b bVar, c0.b bVar2) {
            boolean zM = bVar.m();
            boolean zM2 = bVar2.m();
            if (zM != zM2) {
                return (zM ? 1 : 0) - (zM2 ? 1 : 0);
            }
            Collator collator = this.A;
            int iCompareTo = collator != null ? bVar.c(this.z, collator).compareTo(bVar2.c(this.z, this.A)) : bVar.g(this.z).compareToIgnoreCase(bVar2.g(this.z));
            return iCompareTo != 0 ? iCompareTo : bVar.k() - bVar2.k();
        }
    }

    public interface e {
        void a(c0.d dVar);

        void b(String str, int i);

        void c(c0.d dVar);
    }

    private static final class f implements Comparator<c0.b> {
        private f() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c0.b bVar, c0.b bVar2) {
            int iCompareTo = bVar.i().compareTo(bVar2.i());
            return iCompareTo != 0 ? iCompareTo : bVar.k() - bVar2.k();
        }

        public int b(c0.b bVar, String str, int i) {
            int iCompareTo = bVar.i().compareTo(str);
            return iCompareTo != 0 ? iCompareTo : bVar.k() - i;
        }
    }

    private static final class g {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        static final String f506f = l.k + "REPLY_PACKAGES";

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f507a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f508b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int[] f509c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final String f510d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Parcelable f511e;

        private g(int i, int i2, int[] iArr, String str, Parcelable parcelable) {
            this.f507a = i;
            this.f508b = i2;
            this.f509c = iArr;
            this.f510d = str;
            this.f511e = parcelable;
        }

        g(int i, PackageInfo packageInfo, String str, int i2) {
            this(i2, i, null, str, packageInfo);
        }

        g(h hVar, List<? extends Parcelable> list) {
            this(hVar.f514a, hVar.f515b, hVar.f516c, null, c0.u(list));
        }

        static String d(int i) {
            if (i == -4) {
                return "PACKAGE_REMOVED";
            }
            if (i != -3) {
                return i != -2 ? h.b(i) : "PACKAGE_ADDED";
            }
            return "PACKAGE_CHANGED";
        }

        static g e(Intent intent) {
            if (intent == null || !f506f.equals(intent.getAction())) {
                return null;
            }
            return new g(intent.getIntExtra("id", 0), intent.getIntExtra("option", 0), intent.getIntArrayExtra("users"), intent.getStringExtra("package"), intent.getParcelableExtra("data"));
        }

        <T extends Parcelable> List<T> a() {
            Parcelable parcelable = this.f511e;
            if (parcelable == null) {
                return null;
            }
            return c0.h(parcelable);
        }

        PackageInfo b() {
            return (PackageInfo) this.f511e;
        }

        int c() {
            return this.f507a;
        }

        void f(Context context) {
            context.sendBroadcast(new Intent(f506f).setPackage(l.j).putExtra("id", this.f507a).putExtra("option", this.f508b).putExtra("users", this.f509c).putExtra("package", this.f510d).putExtra("data", this.f511e));
        }

        public String toString() {
            StringBuilder sb;
            String str;
            if (this.f508b > 0) {
                sb = new StringBuilder();
                sb.append("ReplyData{ id=");
                sb.append(this.f507a);
                sb.append(" option=");
                sb.append(d(this.f508b));
                sb.append(", ");
                sb.append(this.f511e);
                str = "}";
            } else {
                sb = new StringBuilder();
                sb.append("ReplyData{ ");
                sb.append(d(this.f508b));
                sb.append(", ");
                sb.append(this.f510d);
                sb.append("(");
                sb.append(this.f507a);
                str = ")}";
            }
            sb.append(str);
            return sb.toString();
        }
    }

    private static final class h {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        static final String f512e = l.k + "REQUEST_PACKAGES";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final AtomicInteger f513f = new AtomicInteger(1);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f514a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f515b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int[] f516c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private volatile g f517d;

        h(int i, int i2, int[] iArr) {
            this.f514a = i;
            this.f515b = i2;
            this.f516c = iArr;
        }

        h(int i, int[] iArr) {
            this.f514a = f513f.getAndIncrement();
            this.f515b = i;
            this.f516c = iArr;
        }

        static String b(int i) {
            switch (i) {
                case 0:
                    return "INVALID_OPTION";
                case 1:
                    return "REGISTER_SYNC";
                case 2:
                    return "REGISTER_SYNC_AND_GET";
                case 3:
                    return "UNREGISTER_SYN";
                case 4:
                    return "GET_PACKAGES";
                case 5:
                    return "GET_PACKAGES_WITH_ACTIVITIES";
                case 6:
                    return "GET_LAUNCHER_ACTIVITIES";
                case 7:
                    return "GET_SHORTCUTS";
                case 8:
                    return "GET_APP_WIDGET_PROVIDERS";
                default:
                    return Integer.toString(i);
            }
        }

        static h c(Intent intent) {
            if (f512e.equals(intent.getAction())) {
                return new h(intent.getIntExtra("id", 0), intent.getIntExtra("option", 0), intent.getIntArrayExtra("users"));
            }
            return null;
        }

        void a(g gVar) {
            synchronized (this) {
                this.f517d = gVar;
                notifyAll();
            }
        }

        void d(Context context) {
            context.sendBroadcast(new Intent(f512e).setPackage("android").putExtra("id", this.f514a).putExtra("option", this.f515b).putExtra("users", this.f516c));
        }

        g e(Context context) {
            d(context);
            synchronized (this) {
                long jUptimeMillis = SystemClock.uptimeMillis() + 6000;
                while (this.f517d == null) {
                    long jUptimeMillis2 = jUptimeMillis - SystemClock.uptimeMillis();
                    if (jUptimeMillis2 <= 0) {
                        return null;
                    }
                    try {
                        wait(jUptimeMillis2);
                    } catch (InterruptedException unused) {
                    }
                }
                return this.f517d;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && h.class == obj.getClass() && this.f514a == ((h) obj).f514a;
        }

        public int hashCode() {
            return this.f514a;
        }

        public String toString() {
            return "RequestData{id=" + this.f514a + ", option=" + b(this.f515b) + ", userIds=" + Arrays.toString(this.f516c) + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class i extends z {
        private boolean h;
        private BroadcastReceiver i;
        private Handler j;
        private int[] k;
        private Locale l;
        private List<c0.e> m;
        private long n;

        class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    i.this.y0(intent);
                } catch (Throwable th) {
                    v.d(th);
                }
            }
        }

        class b extends BroadcastReceiver {
            b() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    Uri data = intent.getData();
                    if (data == null) {
                        return;
                    }
                    i.this.v0(intent.getAction(), data.getSchemeSpecificPart(), o0.h(intent.getIntExtra("android.intent.extra.UID", 0)));
                } catch (Throwable th) {
                    v.d(th);
                }
            }
        }

        i(Context context) {
            super(context);
            this.h = false;
            this.n = 0L;
            z0();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void y0(Intent intent) {
            A0();
            h hVarC = h.c(intent);
            if (hVarC != null) {
                int i = hVarC.f515b;
                if (i == 1) {
                    this.h = true;
                    return;
                }
                if (i == 2) {
                    this.h = true;
                } else if (i == 3) {
                    this.h = false;
                    return;
                }
                B0(hVarC);
            }
        }

        private void z0() {
            this.f500a.registerReceiver(new a(), new IntentFilter(h.f512e), null, s0(), 2);
        }

        void A0() {
            if (z.f497e || this.i != null) {
                return;
            }
            this.i = new b();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addDataScheme("package");
            c0.w(this.f500a, this.i, intentFilter, s0());
        }

        void B0(h hVar) {
            List listH0;
            switch (hVar.f515b) {
                case 2:
                case 5:
                    listH0 = h0(33281, hVar.f516c);
                    break;
                case 3:
                default:
                    v.c("Unknown option: " + hVar.f515b);
                    return;
                case 4:
                    listH0 = h0(0, hVar.f516c);
                    break;
                case 6:
                    listH0 = g0(hVar.f516c);
                    break;
                case 7:
                    listH0 = i0();
                    break;
                case 8:
                    listH0 = d0();
                    break;
            }
            new g(hVar, listH0).f(this.f500a);
        }

        @Override // f.z
        List<c0.e> O(c0.c cVar, int i) {
            return h0.c(s0()) ? t0(cVar, i) : super.O(cVar, i);
        }

        @Override // f.z
        void c0(final String str, final String str2, final int i) {
            s0().post(new Runnable() { // from class: f.b0
                @Override // java.lang.Runnable
                public final void run() {
                    this.z.v0(str, str2, i);
                }
            });
        }

        Handler s0() {
            if (this.j == null) {
                this.j = h0.b();
            }
            return this.j;
        }

        List<c0.e> t0(c0.c cVar, int i) {
            long j;
            int[] iArr;
            A0();
            Locale locale = Locale.getDefault();
            int[] iArrE = o0.e(this.f500a);
            long jUptimeMillis = SystemClock.uptimeMillis();
            List<c0.e> listP0 = null;
            if (this.m != null) {
                if (jUptimeMillis <= this.n && z.W(iArrE, this.k)) {
                    listP0 = this.m;
                } else {
                    this.m = null;
                }
            }
            if (listP0 == null) {
                listP0 = z.p0(g0(iArrE));
                j = (iArrE == null || (iArr = this.k) == null || iArrE.length > iArr.length) ? 60000L : 7200000L;
            } else {
                j = 0;
            }
            if (this.m == null || !locale.equals(this.l)) {
                Collections.sort(listP0, new d(locale));
            }
            this.k = iArrE;
            this.l = locale;
            this.m = listP0;
            if (j > 0) {
                this.n = j + SystemClock.uptimeMillis();
            }
            return z.m(listP0, cVar, i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Code duplicated, block: B:35:0x0090  */
        /* JADX INFO: renamed from: u0, reason: merged with bridge method [inline-methods] */
        public void v0(String str, String str2, int i) {
            if (str == null || str2 == null) {
                return;
            }
            try {
                if (o0.i(this.f500a, i)) {
                    PackageInfo packageInfoI = null;
                    byte b2 = -1;
                    int iHashCode = str.hashCode();
                    int i2 = 0;
                    if (iHashCode != 172491798) {
                        if (iHashCode != 525384130) {
                            if (iHashCode == 1544582882 && str.equals("android.intent.action.PACKAGE_ADDED")) {
                                b2 = 0;
                            }
                        } else if (str.equals("android.intent.action.PACKAGE_REMOVED")) {
                            b2 = 2;
                        }
                    } else if (str.equals("android.intent.action.PACKAGE_CHANGED")) {
                        b2 = 1;
                    }
                    if (b2 == 0) {
                        packageInfoI = c0.i(str2, 33281, i);
                        x0(packageInfoI, i);
                        Z(packageInfoI, i);
                        if (i == 0) {
                            String str3 = l.j;
                            if (str3.equals(str2)) {
                                v.c("<<< " + str3 + " reinstalled >>>");
                            } else {
                                i2 = -2;
                            }
                        } else {
                            i2 = -2;
                        }
                    } else if (b2 == 1) {
                        i2 = -3;
                        packageInfoI = c0.i(str2, 33281, i);
                        x0(packageInfoI, i);
                        a0(packageInfoI, i);
                    } else if (b2 == 2) {
                        i2 = -4;
                        w0(str2, i);
                        b0(str2, i);
                    }
                    if (!this.h || i2 == 0) {
                        return;
                    }
                    new g(i2, packageInfoI, str2, i).f(this.f500a);
                }
            } catch (Throwable th) {
                v.d(th);
            }
        }

        void w0(String str, int i) {
            List<c0.e> list = this.m;
            if (list != null) {
                this.m = z.j0(list, str, i);
            }
        }

        void x0(PackageInfo packageInfo, int i) {
            List<c0.e> list = this.m;
            if (list != null) {
                this.m = z.j0(list, packageInfo.packageName, i);
                List<ResolveInfo> listF0 = f0(c0.a().setPackage(packageInfo.packageName), 33280, i);
                if (listF0 == null || listF0.size() <= 0) {
                    return;
                }
                this.m.addAll(z.p0(listF0));
                this.l = null;
            }
        }
    }

    z(Context context) {
        Context applicationContext = context.getApplicationContext();
        context = applicationContext != null ? applicationContext : context;
        this.f500a = context;
        this.f501b = context.getPackageManager();
    }

    public static z K(Context context) {
        z bVar;
        if (f496d == null) {
            c0.z(context.getPackageManager());
            synchronized (z.class) {
                if (f496d == null) {
                    if (o0.l()) {
                        bVar = new i(context);
                    } else {
                        bVar = o0.m() == 0 ? new b(context) : new c(context);
                    }
                    f496d = bVar;
                }
            }
        }
        return f496d;
    }

    public static <E extends c0.b> String[] L(List<E> list) {
        if (list == null) {
            return new String[0];
        }
        int size = list.size();
        String[] strArr = new String[size];
        Locale locale = Locale.getDefault();
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = list.get(i2).g(locale);
        }
        return strArr;
    }

    static PackageManager P() {
        return f496d.f501b;
    }

    static boolean W(int[] iArr, int[] iArr2) {
        return Arrays.equals(iArr, iArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean X(String str, int i2, c0.e eVar) {
        return str.equals(eVar.i()) && i2 == eVar.k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int Y(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        int i2 = activityInfo.applicationInfo.uid;
        ActivityInfo activityInfo2 = resolveInfo2.activityInfo;
        int i3 = i2 - activityInfo2.applicationInfo.uid;
        if (i3 != 0) {
            return i3;
        }
        int iCompareTo = activityInfo.name.compareTo(activityInfo2.name);
        return iCompareTo != 0 ? iCompareTo : resolveInfo.activityInfo.packageName.compareTo(resolveInfo2.activityInfo.packageName);
    }

    public static <E extends c0.b> int i(List<E> list, c0.b bVar) {
        if (list.isEmpty()) {
            return -1;
        }
        return Collections.binarySearch(list, bVar, new d(Locale.getDefault()));
    }

    static <T extends c0.b> int j(List<T> list, String str, int i2) {
        int size = list.size() - 1;
        int i3 = 0;
        while (i3 <= size) {
            int i4 = (i3 + size) >>> 1;
            int iB = f498f.b(list.get(i4), str, i2);
            if (iB < 0) {
                i3 = i4 + 1;
            } else {
                if (iB <= 0) {
                    return i4;
                }
                size = i4 - 1;
            }
        }
        return i3 ^ (-1);
    }

    static List<c0.e> j0(List<c0.e> list, final String str, final int i2) {
        if (Build.VERSION.SDK_INT >= 24) {
            list.removeIf(new Predicate() { // from class: f.y
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return z.X(str, i2, (c0.e) obj);
                }
            });
            return list;
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i3 = 0; i3 < size; i3++) {
            c0.e eVar = list.get(i3);
            if (!str.equals(eVar.i()) || i2 != eVar.k()) {
                arrayList.add(eVar);
            }
        }
        return arrayList;
    }

    public static <E extends c0.b> void k(List<E> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Locale locale = Locale.getDefault();
        if (locale.equals(list.get(0).h())) {
            return;
        }
        Collections.sort(list, new d(locale));
    }

    public static void k0(String str, String str2, int i2) {
        z zVar = f496d;
        if (zVar != null) {
            zVar.c0(str, str2, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<c0.d> l(List<c0.d> list, boolean z, boolean z2) {
        if (list == null || list.size() == 0) {
            return list;
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            c0.d dVar = list.get(i2);
            if ((!z2 || dVar.h.length > 0) && (z || !dVar.l())) {
                arrayList.add(dVar);
            }
        }
        return arrayList;
    }

    public static void l0(String str, String str2, int[] iArr) {
        z zVar = f496d;
        if (zVar == null || iArr == null) {
            return;
        }
        for (int i2 : iArr) {
            zVar.c0(str, str2, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:22:0x003a  */
    /* JADX WARN: Code duplicated, block: B:24:0x003e  */
    /* JADX WARN: Code duplicated, block: B:27:0x0045  */
    /* JADX WARN: Code duplicated, block: B:32:0x0050  */
    /* JADX WARN: Code duplicated, block: B:33:0x0052  */
    /* JADX WARN: Code duplicated, block: B:36:0x0061  */
    /* JADX WARN: Code duplicated, block: B:43:0x0064 A[SYNTHETIC] */
    public static List<c0.e> m(List<c0.e> list, c0.c cVar, int i2) {
        if (cVar != null && cVar.d()) {
            cVar = null;
        }
        if (cVar == null && i2 == 0) {
            return list;
        }
        int i3 = i2 & 15;
        int i4 = i2 & 983040;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i5 = 0; i5 < size; i5++) {
            c0.e eVar = list.get(i5);
            if (i3 == 1) {
                if (eVar.n()) {
                    if (i4 == 65536) {
                        if (!eVar.m()) {
                            if (cVar != null || !cVar.c(eVar.i(), eVar.k())) {
                                arrayList.add(eVar);
                            }
                        }
                    } else if (i4 == 131072 || !eVar.m()) {
                        if (cVar != null) {
                            arrayList.add(eVar);
                        } else {
                            arrayList.add(eVar);
                        }
                    }
                }
            } else if (i3 != 2 || !eVar.n()) {
                if (i4 == 65536) {
                    if (!eVar.m()) {
                        if (cVar != null) {
                            arrayList.add(eVar);
                        } else {
                            arrayList.add(eVar);
                        }
                    }
                } else if (i4 == 131072) {
                    if (cVar != null) {
                        arrayList.add(eVar);
                    } else {
                        arrayList.add(eVar);
                    }
                } else if (cVar != null) {
                    arrayList.add(eVar);
                } else {
                    arrayList.add(eVar);
                }
            }
        }
        return arrayList;
    }

    public static void m0(boolean z) {
        f497e = z;
    }

    static c0.d n(List<c0.d> list, String str, int i2) {
        int iJ;
        if (list == null || (iJ = j(list, str, i2)) < 0) {
            return null;
        }
        return list.get(iJ);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<c0.d> o0(List<PackageInfo> list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new c0.d(list.get(i2)));
        }
        return arrayList;
    }

    private Drawable p(ComponentName componentName, String str, String str2, int i2) {
        c0.a aVarI = I(str, str2, i2);
        if (aVarI != null) {
            return aVarI.d();
        }
        if (componentName == null) {
            componentName = new ComponentName(str, str2);
        }
        return c0.k(c0.m(c0.b(componentName, i2)), i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<c0.e> p0(List<ResolveInfo> list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new c0.e(list.get(i2)));
        }
        return arrayList;
    }

    private CharSequence w(ComponentName componentName, String str, String str2, int i2) {
        c0.a aVarI = I(str, str2, i2);
        if (aVarI != null) {
            return aVarI.f();
        }
        if (componentName == null) {
            componentName = new ComponentName(str, str2);
        }
        return c0.l(c0.p(c0.b(componentName, i2)), i2);
    }

    public final CharSequence A(String str, String str2, int i2) {
        return w(null, str, str2, i2);
    }

    public u<String, ArrayList<AppWidgetProviderInfo>> B() {
        List<AppWidgetProviderInfo> listD0 = d0();
        u<String, ArrayList<AppWidgetProviderInfo>> uVar = new u<>();
        if (listD0 != null) {
            for (AppWidgetProviderInfo appWidgetProviderInfo : listD0) {
                String packageName = appWidgetProviderInfo.provider.getPackageName();
                ArrayList<AppWidgetProviderInfo> arrayList = uVar.get(packageName);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    uVar.put(packageName, arrayList);
                }
                arrayList.add(appWidgetProviderInfo);
            }
        }
        return uVar;
    }

    public final Drawable C(String str) {
        return D(str, o0.d());
    }

    public final Drawable D(String str, int i2) {
        c0.d dVarJ = J(str, i2);
        return dVarJ != null ? dVarJ.d() : c0.k(c0.n(c0.c(str, i2)), i2);
    }

    public final ApplicationInfo E(String str) {
        return F(str, o0.d());
    }

    public final ApplicationInfo F(String str, int i2) {
        c0.d dVarJ = J(str, i2);
        return dVarJ != null ? dVarJ.f420a : c0.c(str, i2);
    }

    public final CharSequence G(String str) {
        return H(str, o0.d());
    }

    public final CharSequence H(String str, int i2) {
        c0.d dVarJ = J(str, i2);
        return dVarJ != null ? dVarJ.f() : c0.l(c0.q(c0.c(str, i2)), i2);
    }

    c0.a I(String str, String str2, int i2) {
        c0.d dVarJ = J(str, i2);
        if (dVarJ == null) {
            return null;
        }
        for (c0.a aVar : dVarJ.q()) {
            if (aVar.r().equals(str2)) {
                return aVar;
            }
        }
        return null;
    }

    c0.d J(String str, int i2) {
        return null;
    }

    public final List<c0.e> M() {
        return O(null, 0);
    }

    public final List<c0.e> N(c0.c cVar, int i2) {
        return O(cVar, i2);
    }

    List<c0.e> O(c0.c cVar, int i2) {
        List<c0.e> listM = m(p0(g0(o0.e(this.f500a))), cVar, i2);
        Collections.sort(listM, new d(Locale.getDefault()));
        return listM;
    }

    public final List<c0.d> Q(boolean z) {
        return S(z, false);
    }

    public final List<c0.d> R() {
        return S(true, true);
    }

    List<c0.d> S(boolean z, boolean z2) {
        List<c0.d> listL = l(o0(h0(z2 ? 33281 : 0, z ? o0.e(this.f500a) : null)), z, z2);
        Collections.sort(listL, new d(Locale.getDefault()));
        return listL;
    }

    public List<c0.e> T() {
        List<ResolveInfo> listI0 = i0();
        int size = listI0.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new c0.e(listI0.get(i2)));
        }
        Collections.sort(arrayList, new d(Locale.getDefault()));
        return arrayList;
    }

    public final boolean U(String str) {
        return V(str, o0.d());
    }

    public final boolean V(String str, int i2) {
        if (J(str, i2) != null) {
            return true;
        }
        try {
            c0.c(str, i2);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    final void Z(PackageInfo packageInfo, int i2) {
        if (this.f502c != null) {
            c0.d dVarJ = J(packageInfo.packageName, i2);
            if (dVarJ == null) {
                dVarJ = new c0.d(packageInfo);
            }
            try {
                this.f502c.c(dVarJ);
            } catch (Throwable th) {
                v.d(th);
            }
        }
    }

    final void a0(PackageInfo packageInfo, int i2) {
        if (this.f502c != null) {
            c0.d dVarJ = J(packageInfo.packageName, i2);
            if (dVarJ == null) {
                dVarJ = new c0.d(packageInfo);
            }
            try {
                this.f502c.a(dVarJ);
            } catch (Throwable th) {
                v.d(th);
            }
        }
    }

    final void b0(String str, int i2) {
        e eVar = this.f502c;
        if (eVar != null) {
            try {
                eVar.b(str, i2);
            } catch (Throwable th) {
                v.d(th);
            }
        }
    }

    void c0(String str, String str2, int i2) {
    }

    List<AppWidgetProviderInfo> d0() {
        return AppWidgetManager.getInstance(this.f500a).getInstalledProviders();
    }

    List<ResolveInfo> e0(Intent intent, int i2, int[] iArr) {
        List<ResolveInfo> listF0;
        if (iArr == null || iArr.length == 0) {
            listF0 = f0(intent, i2, 0);
        } else if (iArr.length == 1) {
            listF0 = f0(intent, i2, iArr[0]);
        } else {
            ArrayList arrayList = new ArrayList();
            for (int i3 : iArr) {
                List<ResolveInfo> listF1 = f0(intent, i2, i3);
                if (listF1 != null) {
                    arrayList.addAll(listF1);
                }
            }
            listF0 = arrayList;
        }
        return (listF0 == null || listF0.size() <= 0) ? this.f501b.queryIntentActivities(intent, i2) : listF0;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0057  */
    List<ResolveInfo> f0(Intent intent, int i2, int i3) {
        List<ResolveInfo> listV = c0.v(intent, i2, i3);
        if (listV == null || listV.size() <= 0 || i2 == 0) {
            return listV;
        }
        List<ResolveInfo> listV2 = c0.v(intent, 0, i3);
        if (listV2 != null) {
            int size = listV2.size();
            if (size == 0) {
                listV2 = null;
            } else if (size > 1) {
                Collections.sort(listV2, f499g);
            }
        }
        int size2 = listV.size();
        ArrayList arrayList = new ArrayList(size2);
        for (int i4 = 0; i4 < size2; i4++) {
            ResolveInfo resolveInfo = listV.get(i4);
            if (resolveInfo.activityInfo.enabled) {
                if (listV2 == null) {
                    arrayList.add(resolveInfo);
                } else {
                    boolean z = Collections.binarySearch(listV2, resolveInfo, f499g) >= 0;
                    ApplicationInfo applicationInfo = resolveInfo.activityInfo.applicationInfo;
                    if (z == applicationInfo.enabled) {
                        arrayList.add(resolveInfo);
                    } else if (z) {
                        applicationInfo.enabled = true;
                        arrayList.add(resolveInfo);
                    }
                }
            }
        }
        return arrayList;
    }

    List<ResolveInfo> g0(int[] iArr) {
        return e0(c0.a(), 33280, iArr);
    }

    List<PackageInfo> h0(int i2, int[] iArr) {
        List<PackageInfo> listE;
        if (iArr == null || iArr.length == 0) {
            listE = c0.e(i2, 0);
        } else if (iArr.length == 1) {
            listE = c0.e(i2, iArr[0]);
        } else {
            ArrayList arrayList = new ArrayList();
            for (int i3 : iArr) {
                List<PackageInfo> listE2 = c0.e(i2, i3);
                if (listE2 != null) {
                    arrayList.addAll(listE2);
                }
            }
            listE = arrayList;
        }
        return (listE == null || listE.size() <= 0) ? this.f501b.getInstalledPackages(i2) : listE;
    }

    List<ResolveInfo> i0() {
        return e0(new Intent("android.intent.action.CREATE_SHORTCUT"), 0, null);
    }

    public void n0(e eVar) {
        if (eVar != null && this.f502c != null) {
            v.c("Override original listener!");
        }
        this.f502c = eVar;
    }

    public final Drawable o(ComponentName componentName, int i2) {
        return p(componentName, componentName.getPackageName(), componentName.getClassName(), i2);
    }

    public final Drawable q(Intent intent) {
        return r(intent, o0.d());
    }

    public final Drawable r(Intent intent, int i2) throws PackageManager.NameNotFoundException {
        ComponentName component = intent.getComponent();
        if (component != null) {
            return o(component, i2);
        }
        ResolveInfo resolveInfoX = c0.x(intent, 65536, i2);
        if (resolveInfoX != null) {
            return c0.k(c0.o(resolveInfoX), i2);
        }
        String str = intent.getPackage();
        if (str != null) {
            return D(str, i2);
        }
        throw new PackageManager.NameNotFoundException(intent.toUri(0));
    }

    public final Drawable s(String str, String str2) {
        return t(str, str2, o0.d());
    }

    public final Drawable t(String str, String str2, int i2) {
        return p(null, str, str2, i2);
    }

    public final CharSequence u(ComponentName componentName) {
        return v(componentName, o0.d());
    }

    public final CharSequence v(ComponentName componentName, int i2) {
        return w(componentName, componentName.getPackageName(), componentName.getClassName(), i2);
    }

    public final CharSequence x(Intent intent) {
        return y(intent, o0.d());
    }

    public final CharSequence y(Intent intent, int i2) throws PackageManager.NameNotFoundException {
        ComponentName component = intent.getComponent();
        if (component != null) {
            return v(component, i2);
        }
        ResolveInfo resolveInfoX = c0.x(intent, 65536, i2);
        if (resolveInfoX != null) {
            return c0.l(c0.r(resolveInfoX), i2);
        }
        String str = intent.getPackage();
        if (str != null) {
            return H(str, i2);
        }
        throw new PackageManager.NameNotFoundException(intent.toUri(0));
    }

    public final CharSequence z(String str, String str2) {
        return A(str, str2, o0.d());
    }
}
