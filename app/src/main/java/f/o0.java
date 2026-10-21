package f;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.SparseArray;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f470a = new int[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f471b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int[] f472c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static boolean f473d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static SparseArray<UserHandle> f474e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f475f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static Method f476g;
    private static boolean h;
    private static Method i;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f477a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int[] f478b;

        a(Context context) {
            int i = o0.f471b;
            this.f477a = i;
            this.f478b = null;
            if (o0.f473d) {
                int[] iArrE = o0.e(context);
                this.f478b = iArrE;
                if (iArrE != null && iArrE.length == 1 && iArrE[0] == i) {
                    this.f478b = o0.f470a;
                }
            }
        }

        public boolean a(int i) {
            int[] iArr = this.f478b;
            if (iArr == null) {
                return true;
            }
            if (iArr.length == 0) {
                return i == this.f477a;
            }
            for (int i2 : iArr) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        public boolean b() {
            return this.f478b == null;
        }
    }

    static {
        int iM = m();
        f471b = iM;
        f472c = new int[]{iM};
        f473d = false;
        int i2 = Build.VERSION.SDK_INT;
        f475f = i2 >= 24;
        f476g = null;
        h = i2 >= 24;
        i = null;
    }

    public static int d() {
        return f471b;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0053 A[Catch: all -> 0x006f, TryCatch #1 {all -> 0x006f, blocks: (B:4:0x0006, B:6:0x0010, B:16:0x004b, B:18:0x0053, B:20:0x005f, B:15:0x0042, B:9:0x0015, B:11:0x001a, B:12:0x002e), top: B:29:0x0006, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:20:0x005f A[Catch: all -> 0x006f, TRY_LEAVE, TryCatch #1 {all -> 0x006f, blocks: (B:4:0x0006, B:6:0x0010, B:16:0x004b, B:18:0x0053, B:20:0x005f, B:15:0x0042, B:9:0x0015, B:11:0x001a, B:12:0x002e), top: B:29:0x0006, inners: #0 }] */
    public static int[] e(Context context) {
        List<UserHandle> userProfiles;
        int size;
        int[] iArr;
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                UserManager userManager = (UserManager) context.getSystemService("user");
                if (userManager != null) {
                    if (h) {
                        try {
                            if (i == null) {
                                i = f0.n().h(UserManager.class, "getEnabledProfileIds", Integer.TYPE);
                            }
                            return (int[]) i.invoke(userManager, Integer.valueOf(f471b));
                        } catch (Throwable th) {
                            h = false;
                            v.c(th.toString());
                            if (f471b == m()) {
                                userProfiles = userManager.getUserProfiles();
                                size = userProfiles.size();
                                iArr = new int[size];
                                for (int i2 = 0; i2 < size; i2++) {
                                    iArr[i2] = userProfiles.get(i2).hashCode();
                                }
                                return iArr;
                            }
                            return f472c;
                        }
                    }
                    if (f471b == m()) {
                        userProfiles = userManager.getUserProfiles();
                        size = userProfiles.size();
                        iArr = new int[size];
                        while (i2 < size) {
                            iArr[i2] = userProfiles.get(i2).hashCode();
                        }
                        return iArr;
                    }
                    v.d(th);
                }
            } catch (Throwable th2) {
                v.d(th2);
            }
        }
        return f472c;
    }

    public static a f(Context context) {
        return new a(context);
    }

    public static int g(int i2) {
        if (Build.VERSION.SDK_INT < 21 || i2 == 0 || i2 == f471b) {
            return 0;
        }
        return i2 < 500 ? 1 : 2;
    }

    public static int h(int i2) {
        return i2 / 100000;
    }

    public static boolean i(Context context, int i2) {
        UserManager userManager;
        if (i2 == f471b || !f473d || Build.VERSION.SDK_INT < 21) {
            return true;
        }
        if (f475f && (userManager = (UserManager) context.getSystemService("user")) != null) {
            try {
                if (f476g == null) {
                    f0 f0VarN = f0.n();
                    Class<?> cls = Integer.TYPE;
                    f476g = f0VarN.h(UserManager.class, "isSameProfileGroup", cls, cls);
                }
                return ((Boolean) f476g.invoke(userManager, Integer.valueOf(i2), Integer.valueOf(f471b))).booleanValue();
            } catch (Throwable th) {
                f475f = false;
                v.c(th.toString());
            }
        }
        int[] iArrE = e(context);
        if (iArrE != null && iArrE.length > 0) {
            for (int i3 : iArrE) {
                if (i3 == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean j(int i2) {
        return (Build.VERSION.SDK_INT < 21 || i2 == 0 || i2 == f471b) ? false : true;
    }

    public static boolean k(int[] iArr) {
        return iArr == null || iArr.length == 0 || (iArr.length == 1 && iArr[0] == f471b);
    }

    public static boolean l() {
        return Process.myUid() <= 1000;
    }

    public static int m() {
        return h(Process.myUid());
    }

    private static UserHandle n(int i2) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInt(i2);
        UserHandle userHandle = new UserHandle(parcelObtain);
        parcelObtain.recycle();
        return userHandle;
    }

    public static void o(int i2) {
        String str;
        if (i2 == f471b) {
            return;
        }
        f471b = i2;
        f473d = true;
        f472c = new int[]{i2};
        if (i2 == 0) {
            str = "Switching to main user.";
        } else {
            str = "Switching to user " + i2 + ", multi-user not full supported.";
        }
        v.c(str);
    }

    public static UserHandle p() {
        return q(-1);
    }

    public static UserHandle q(int i2) {
        UserHandle userHandle;
        SparseArray<UserHandle> sparseArray = f474e;
        if (sparseArray != null && (userHandle = sparseArray.get(i2)) != null) {
            return userHandle;
        }
        UserHandle userHandleN = n(i2);
        SparseArray<UserHandle> sparseArrayClone = sparseArray != null ? sparseArray.clone() : new SparseArray<>();
        sparseArrayClone.append(i2, userHandleN);
        f474e = sparseArrayClone;
        return userHandleN;
    }

    public static UserHandle r() {
        return q(0);
    }
}
