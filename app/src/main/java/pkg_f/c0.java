package pkg_f;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Pair;
import android.util.SparseArray;
import de.robv.android.xposed.XposedHelpers;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.CollationKey;
import java.text.Collator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class c0 implements l {

    @SuppressWarnings("unchecked")
    public static <E extends Throwable> void sneakyThrow(Throwable e) throws E {
        throw (E) e;
    }
    private static Constructor<?> A = null;
    private static Method B = null;
    private static PackageManager C = null;
    private static Object D = null;
    private static Method E = null;
    private static Method F = null;
    private static Method G = null;
    private static Method H = null;
    private static Method I = null;
    public static String z = " (";

    public static class a extends b {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        ActivityInfo f419g;
        ComponentName h;
        private String i;

        a(ActivityInfo activityInfo) {
            super(activityInfo.applicationInfo);
            this.f419g = activityInfo;
        }

        @Override // pkg_f.c0.b
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return this.f420a.uid == aVar.f420a.uid && this.f419g.name.equals(aVar.f419g.name) && this.f419g.packageName.equals(aVar.f419g.packageName);
        }

        @Override // pkg_f.c0.b
        public int hashCode() {
            return this.f419g.name.hashCode() + this.f420a.uid;
        }

        @Override // pkg_f.c0.b
        public boolean m() {
            return this.f419g.enabled && this.f420a.enabled;
        }

        @Override // pkg_f.c0.b
        protected Drawable o() {
            return c0.m(this.f419g);
        }

        @Override // pkg_f.c0.b
        protected CharSequence p() {
            return c0.p(this.f419g);
        }

        public pkg_a.b.x q() {
            if (l()) {
                ActivityInfo activityInfo = this.f419g;
                return new pkg_a.b.f_launch(activityInfo.packageName, activityInfo.name, this.f421b);
            }
            ActivityInfo activityInfo2 = this.f419g;
            return new pkg_a.b.e(activityInfo2.packageName, activityInfo2.name);
        }

        public final String r() {
            return this.f419g.name;
        }

        public final ComponentName s() {
            if (this.h == null) {
                ActivityInfo activityInfo = this.f419g;
                this.h = new ComponentName(activityInfo.packageName, activityInfo.name);
            }
            return this.h;
        }

        public final String t() {
            if (this.i == null) {
                ActivityInfo activityInfo = this.f419g;
                this.i = c0.A(activityInfo.packageName, activityInfo.name);
            }
            return this.i;
        }

        @Override // pkg_f.c0.b
        public String toString() {
            StringBuilder sb;
            String strI = i();
            String strT = t();
            if (this.f421b == 0) {
                sb = new StringBuilder();
                sb.append(strI);
                sb.append('/');
            } else {
                sb = new StringBuilder();
                sb.append(strI);
                sb.append("(");
                sb.append(this.f421b);
                sb.append(")/");
            }
            sb.append(strT);
            return sb.toString();
        }

        public final boolean u() {
            return this.f419g.enabled;
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ApplicationInfo f420a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f421b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f422c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Locale f423d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private CollationKey f424e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private pkg_g.g.b f425f;

        public b(ApplicationInfo applicationInfo) {
            this.f420a = applicationInfo;
            this.f421b = o0.h(applicationInfo.uid);
        }

        private pkg_g.g b(int i) {
            int iG = o0.g(i);
            pkg_g.g.b bVar = this.f425f;
            if (bVar != null) {
                return bVar.b(iG);
            }
            pkg_g.g gVar = new pkg_g.g(o(), iG);
            if (i == this.f421b) {
                this.f425f = gVar.getConstantState();
            }
            return gVar;
        }

        public final ApplicationInfo a() {
            return this.f420a;
        }

        final CollationKey c(Locale locale, Collator collator) {
            CollationKey collationKey = this.f424e;
            if (collationKey != null && locale.equals(this.f423d)) {
                return collationKey;
            }
            CollationKey collationKey2 = collator.getCollationKey(g(locale));
            this.f424e = collationKey2;
            return collationKey2;
        }

        public pkg_g.g d() {
            return b(this.f421b);
        }

        public final Drawable e() {
            pkg_g.g gVarD = d();
            if (!m()) {
                pkg_g.a0.v(gVarD, true);
            }
            return gVarD;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ApplicationInfo applicationInfo = this.f420a;
            int i = applicationInfo.uid;
            ApplicationInfo applicationInfo2 = ((b) obj).f420a;
            return i == applicationInfo2.uid && applicationInfo.packageName.equals(applicationInfo2.packageName);
        }

        public final String f() {
            return g(Locale.getDefault());
        }

        String g(Locale locale) {
            if (!locale.equals(this.f423d)) {
                this.f422c = c0.l(p(), this.f421b).toString();
                this.f423d = locale;
            }
            return this.f422c;
        }

        Locale h() {
            return this.f423d;
        }

        public int hashCode() {
            return this.f420a.uid;
        }

        public final String i() {
            return this.f420a.packageName;
        }

        public pkg_g.g j() {
            return b(0);
        }

        public final int k() {
            return this.f421b;
        }

        public final boolean l() {
            return o0.j(this.f421b);
        }

        public boolean m() {
            return this.f420a.enabled;
        }

        public final boolean n() {
            int i = this.f420a.flags;
            return ((i & 1) == 0 && (i & 128) == 0) ? false : true;
        }

        protected Drawable o() {
            return c0.n(this.f420a);
        }

        protected CharSequence p() {
            return c0.q(this.f420a);
        }

        public String toString() {
            String strI = i();
            if (this.f421b == 0) {
                return strI;
            }
            return strI + "(" + this.f421b + ")";
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final SparseArray<Set<String>> f426a = new SparseArray<>();

        public void a(String str, int i) {
            Set<String> hashSet = this.f426a.get(i);
            if (hashSet == null) {
                hashSet = new HashSet<>();
                this.f426a.append(i, hashSet);
            }
            hashSet.add(str);
        }

        public void b(Set<String> set, int i) {
            Set<String> hashSet = this.f426a.get(i);
            if (hashSet == null) {
                hashSet = new HashSet<>();
                this.f426a.append(i, hashSet);
            }
            hashSet.addAll(set);
        }

        public boolean c(String str, int i) {
            Set<String> set = this.f426a.get(-1);
            if (set != null && set.contains(str)) {
                return true;
            }
            Set<String> set2 = this.f426a.get(i);
            return set2 != null && set2.contains(str);
        }

        public boolean d() {
            int size = this.f426a.size();
            if (size <= 0) {
                return true;
            }
            for (int i = 0; i < size; i++) {
                if (!this.f426a.valueAt(i).isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class d extends b {
        private static final a[] i = new a[0];

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final PackageInfo f427g;
        final a[] h;

        d(PackageInfo packageInfo) {
            super(packageInfo.applicationInfo);
            this.f427g = packageInfo;
            ActivityInfo[] activityInfoArr = packageInfo.activities;
            if (activityInfoArr == null || activityInfoArr.length <= 0) {
                this.h = i;
                return;
            }
            this.h = new a[activityInfoArr.length];
            int i2 = 0;
            while (true) {
                a[] aVarArr = this.h;
                if (i2 >= aVarArr.length) {
                    return;
                }
                aVarArr[i2] = new a(packageInfo.activities[i2]);
                i2++;
            }
        }

        public a[] q() {
            return this.h;
        }
    }

    public static class e extends a {
        final ResolveInfo j;

        e(ResolveInfo resolveInfo) {
            super(resolveInfo.activityInfo);
            this.j = resolveInfo;
        }

        @Override // pkg_f.c0.b
        public pkg_g.g d() {
            pkg_g.g gVarD = super.d();
            gVarD.c();
            return gVarD;
        }

        @Override // pkg_f.c0.a, pkg_f.c0.b
        protected Drawable o() {
            return c0.o(this.j);
        }

        @Override // pkg_f.c0.a, pkg_f.c0.b
        protected CharSequence p() {
            return c0.r(this.j);
        }

        @Override // pkg_f.c0.a
        public pkg_a.b.x q() {
            return new pkg_a.b.l(this.f420a.packageName, this.f419g.name, this.f421b);
        }

        public Intent v() {
            return c0.a().setComponent(s());
        }
    }

    public static class f extends b {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final ActivityManager.RunningTaskInfo f428g;
        final ActivityInfo h;

        public f(ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
            this(runningTaskInfo, q(runningTaskInfo, i));
        }

        public f(ActivityManager.RunningTaskInfo runningTaskInfo, Pair<ApplicationInfo, ActivityInfo> pair) {
            super((ApplicationInfo) pair.first);
            this.f428g = runningTaskInfo;
            this.h = (ActivityInfo) pair.second;
        }

        private static Pair<ApplicationInfo, ActivityInfo> q(ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
            try {
                ActivityInfo activityInfoB = c0.b(runningTaskInfo.baseActivity, i);
                return new Pair<>(activityInfoB.applicationInfo, activityInfoB);
            } catch (Throwable th) {
                v.c(th.toString());
                try {
                    return new Pair<>(c0.c(runningTaskInfo.baseActivity.getPackageName(), i), null);
                } catch (Throwable unused) {
                    return null;
                }
            }
        }

        @Override // pkg_f.c0.b
        public pkg_g.g d() {
            pkg_g.g gVarD = super.d();
            gVarD.c();
            return gVarD;
        }

        @Override // pkg_f.c0.b
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.f428g.id == ((f) obj).f428g.id;
        }

        @Override // pkg_f.c0.b
        public int hashCode() {
            return this.f428g.id;
        }

        @Override // pkg_f.c0.b
        protected Drawable o() {
            ActivityInfo activityInfo = this.h;
            return activityInfo != null ? c0.m(activityInfo) : super.o();
        }

        @Override // pkg_f.c0.b
        protected CharSequence p() {
            return c0.p(this.h);
        }

        public final ActivityManager.RunningTaskInfo r() {
            return this.f428g;
        }
    }

    public static String A(String str, String str2) {
        int length = str.length();
        return (str2.length() > length && str2.charAt(length) == '.' && str2.startsWith(str)) ? str2.substring(length) : str2;
    }

    public static String B(Intent intent) {
        ComponentName component = intent.getComponent();
        if (component != null) {
            return component.flattenToShortString();
        }
        String str = intent.getPackage();
        return str != null ? str : intent.toString();
    }

    public static String C(String str, String str2) {
        return str + '/' + A(str, str2);
    }

    public static Intent a() {
        return new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER");
    }

    public static ActivityInfo b(ComponentName componentName, int i) {
        try {
            if (i == o0.m()) {
                ActivityInfo activityInfo = j().getActivityInfo(componentName, 33280);
                if (o0.h(activityInfo.applicationInfo.uid) == i) {
                    return activityInfo;
                }
                sneakyThrow(new PackageManager.NameNotFoundException(componentName.flattenToShortString() + "(" + i + ")"));
                return null;
            }
            ActivityInfo[] activityInfoArr = i(componentName.getPackageName(), 33281, i).activities;
            if (activityInfoArr != null && activityInfoArr.length > 0) {
                String className = componentName.getClassName();
                for (ActivityInfo activityInfo2 : activityInfoArr) {
                    if (className.equals(activityInfo2.name)) {
                        return activityInfo2;
                    }
                }
            }
            sneakyThrow(new PackageManager.NameNotFoundException(componentName.flattenToShortString() + z + i + ")."));
            return null;
        } catch (Throwable th) {
            sneakyThrow(th);
            return null;
        }
    }

    public static ApplicationInfo c(String str, int i) {
        try {
            Object objInvoke;
            if (i == o0.m()) {
                return j().getApplicationInfo(str, 33280);
            }
            ApplicationInfo applicationInfo = null;
            try {
                if (Build.VERSION.SDK_INT < 24) {
                    Object objD = d();
                    if (objD != null) {
                        if (H == null) {
                            f0 f0VarN = f0.n();
                            Class<?> cls = objD.getClass();
                            Class<?> cls2 = Integer.TYPE;
                            H = f0VarN.h(cls, "getApplicationInfo", String.class, cls2, cls2);
                        }
                        objInvoke = H.invoke(objD, str, 33280, Integer.valueOf(i));
                    }
                    if (applicationInfo == null && o0.h(applicationInfo.uid) == i) {
                        return applicationInfo;
                    }
                    sneakyThrow(new PackageManager.NameNotFoundException(str + z + i + ")"));
                    return null;
                }
                if (H == null) {
                    f0 f0VarN2 = f0.n();
                    Class<?> cls3 = Integer.TYPE;
                    H = f0VarN2.h(PackageManager.class, "getApplicationInfoAsUser", String.class, cls3, cls3);
                }
                objInvoke = H.invoke(j(), str, 33280, Integer.valueOf(i));
                applicationInfo = (ApplicationInfo) objInvoke;
            } catch (Throwable unused) {
            }
            if (applicationInfo == null) {
            }
            sneakyThrow(new PackageManager.NameNotFoundException(str + z + i + ")"));
            return null;
        } catch (Throwable th) {
            sneakyThrow(th);
            return null;
        }
    }

    private static Object d() {
        Object objG = D;
        if (objG != null) {
            return objG;
        }
        try {
            objG = f0.n().g(j().getClass(), "mPm");
            D = objG;
            return objG;
        } catch (Throwable th) {
            v.d(th);
            return objG;
        }
    }

    public static List<PackageInfo> e(int i, int i2) {
        if (i2 == o0.m()) {
            return j().getInstalledPackages(i);
        }
        try {
            if (G == null) {
                String str = Build.VERSION.SDK_INT >= 24 ? "getInstalledPackagesAsUser" : "getInstalledPackages";
                f0 f0VarN = f0.n();
                Class<?> cls = Integer.TYPE;
                G = f0VarN.h(PackageManager.class, str, cls, cls);
            }
            return (List) G.invoke(j(), Integer.valueOf(i), Integer.valueOf(i2));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String f(Intent intent) {
        ComponentName component;
        if (intent == null) {
            return null;
        }
        String str = intent.getPackage();
        return (str != null || (component = intent.getComponent()) == null) ? str : component.getPackageName();
    }

    public static Intent g(String str, int i) throws PackageManager.NameNotFoundException {
        Intent intentA = a();
        intentA.setPackage(str);
        List<ResolveInfo> listV = v(intentA, 0, i);
        if (listV == null || listV.size() == 0) {
            intentA.removeCategory("android.intent.category.LAUNCHER");
            intentA.addCategory("android.intent.category.INFO");
            listV = v(intentA, 0, i);
        }
        if (listV != null && listV.size() > 0) {
            ActivityInfo activityInfo = listV.get(0).activityInfo;
            intentA.setClassName(activityInfo.packageName, activityInfo.name);
            intentA.setFlags(268435456);
            return intentA;
        }
        throw new PackageManager.NameNotFoundException(str + z + i + ").");
    }

    public static <T extends Parcelable> List<T> h(Parcelable parcelable) {
        try {
            Method methodJ = B;
            if (methodJ == null) {
                methodJ = f0.n().j(parcelable.getClass(), "getList", new Class[0]);
                B = methodJ;
            }
            return (List) methodJ.invoke(parcelable, new Object[0]);
        } catch (Throwable th) {
            v.d(th);
            return null;
        }
    }

    public static PackageInfo i(String str, int i, int i2) throws PackageManager.NameNotFoundException {
        Object objInvoke;
        if (i2 == o0.m()) {
            return j().getPackageInfo(str, i);
        }
        PackageInfo packageInfo = null;
        try {
            if (Build.VERSION.SDK_INT < 24) {
                Object objD = d();
                if (objD != null) {
                    if (I == null) {
                        f0 f0VarN = f0.n();
                        Class<?> cls = objD.getClass();
                        Class<?> cls2 = Integer.TYPE;
                        I = f0VarN.h(cls, "getPackageInfo", String.class, cls2, cls2);
                    }
                    objInvoke = I.invoke(objD, str, Integer.valueOf(i), Integer.valueOf(i2));
                }
                if (packageInfo == null && o0.h(packageInfo.applicationInfo.uid) == i2) {
                    return packageInfo;
                }
                throw new PackageManager.NameNotFoundException(str + z + i2 + ")");
            }
            if (I == null) {
                f0 f0VarN2 = f0.n();
                Class<?> cls3 = Integer.TYPE;
                I = f0VarN2.h(PackageManager.class, "getPackageInfoAsUser", String.class, cls3, cls3);
            }
            objInvoke = I.invoke(j(), str, Integer.valueOf(i), Integer.valueOf(i2));
            packageInfo = (PackageInfo) objInvoke;
        } catch (Throwable unused) {
        }
        if (packageInfo == null) {
        }
        throw new PackageManager.NameNotFoundException(str + z + i2 + ")");
    }

    public static PackageManager j() {
        if (C == null) {
            C = pkg_f.z.P();
        }
        return C;
    }

    public static pkg_g.g k(Drawable drawable, int i) {
        return new pkg_g.g(drawable, o0.g(i));
    }

    public static CharSequence l(CharSequence charSequence, int i) {
        return charSequence;
    }

    public static Drawable m(ActivityInfo activityInfo) {
        Drawable drawableS = s(activityInfo);
        return drawableS != null ? drawableS : s(activityInfo);
    }

    public static Drawable n(ApplicationInfo applicationInfo) {
        return s(applicationInfo);
    }

    public static Drawable o(ResolveInfo resolveInfo) {
        Drawable drawableT = t(resolveInfo);
        return drawableT != null ? drawableT : s(resolveInfo.activityInfo.applicationInfo);
    }

    public static CharSequence p(ActivityInfo activityInfo) {
        PackageManager packageManagerJ = j();
        CharSequence charSequenceLoadLabel = activityInfo.loadLabel(packageManagerJ);
        return charSequenceLoadLabel.length() > 0 ? charSequenceLoadLabel : activityInfo.applicationInfo.loadLabel(packageManagerJ);
    }

    public static CharSequence q(ApplicationInfo applicationInfo) {
        return applicationInfo.loadLabel(j());
    }

    public static CharSequence r(ResolveInfo resolveInfo) {
        PackageManager packageManagerJ = j();
        CharSequence charSequenceLoadLabel = resolveInfo.loadLabel(packageManagerJ);
        return charSequenceLoadLabel.length() > 0 ? charSequenceLoadLabel : resolveInfo.activityInfo.applicationInfo.loadLabel(packageManagerJ);
    }

    private static Drawable s(PackageItemInfo packageItemInfo) {
        return Build.VERSION.SDK_INT >= 22 ? packageItemInfo.loadUnbadgedIcon(j()) : packageItemInfo.loadIcon(j());
    }

    private static Drawable t(ResolveInfo resolveInfo) {
        if (Build.VERSION.SDK_INT < 23) {
            return resolveInfo.loadIcon(j());
        }
        PackageManager packageManagerJ = j();
        int i = resolveInfo.icon;
        if (i != 0) {
            String str = resolveInfo.resolvePackageName;
            Drawable drawable = str != null ? packageManagerJ.getDrawable(str, i, null) : null;
            if (drawable == null) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                drawable = packageManagerJ.getDrawable(activityInfo.packageName, i, activityInfo.applicationInfo);
            }
            if (drawable != null) {
                return drawable;
            }
        }
        return resolveInfo.activityInfo.loadUnbadgedIcon(packageManagerJ);
    }

    public static <T extends Parcelable> Parcelable u(List<T> list) {
        try {
            Constructor<?> constructorE = A;
            if (constructorE == null) {
                f0 f0VarN = f0.n();
                constructorE = f0VarN.e(f0VarN.c("android.content.pm.ParceledListSlice"), List.class);
                A = constructorE;
            }
            return (Parcelable) constructorE.newInstance(list);
        } catch (Throwable th) {
            v.d(th);
            return null;
        }
    }

    public static List<ResolveInfo> v(Intent intent, int i, int i2) {
        if (i2 == o0.m()) {
            return j().queryIntentActivities(intent, i);
        }
        try {
            if (F == null) {
                f0 f0VarN = f0.n();
                Class<?> cls = Integer.TYPE;
                F = f0VarN.h(PackageManager.class, "queryIntentActivitiesAsUser", Intent.class, cls, cls);
            }
            return (List) F.invoke(j(), intent, Integer.valueOf(i), Integer.valueOf(i2));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void w(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Handler handler) {
        try {
            if (Build.VERSION.SDK_INT >= 30) {
                XposedHelpers.callMethod(context, "registerReceiverForAllUsers", new Object[]{broadcastReceiver, intentFilter, null, handler});
            } else {
                XposedHelpers.callMethod(context, "registerReceiverAsUser", new Object[]{broadcastReceiver, o0.p(), intentFilter, null, handler});
            }
        } catch (Throwable th) {
            v.d(th);
            context.registerReceiver(broadcastReceiver, intentFilter, null, handler, 2);
        }
    }

    public static ResolveInfo x(Intent intent, int i, int i2) {
        try {
            if (i2 == o0.m()) {
                return j().resolveActivity(intent, i);
            }
            if (E == null) {
                f0 f0VarN = f0.n();
                Class<?> cls = Integer.TYPE;
                E = f0VarN.h(PackageManager.class, "resolveActivityAsUser", Intent.class, cls, cls);
            }
            ResolveInfo resolveInfo = (ResolveInfo) E.invoke(j(), intent, Integer.valueOf(i), Integer.valueOf(i2));
            if (resolveInfo == null || o0.h(resolveInfo.activityInfo.applicationInfo.uid) != i2) {
                return null;
            }
            return resolveInfo;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void y(Object obj) {
        D = obj;
    }

    static void z(PackageManager packageManager) {
        C = packageManager;
    }
}
