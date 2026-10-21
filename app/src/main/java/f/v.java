package f;

import android.os.SystemClock;
import android.util.Log;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class v implements l {
    private static b z;

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String[] f490a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f491b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f492c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f493d;

        private b() {
            this.f490a = new String[1024];
            this.f491b = 0;
            this.f492c = 0;
            this.f493d = null;
        }

        private synchronized void b(String str) {
            this.f493d = null;
            String[] strArr = this.f490a;
            int i = this.f491b;
            strArr[i] = str;
            this.f492c++;
            int i2 = i + 1;
            this.f491b = i2;
            if (i2 >= strArr.length) {
                this.f491b = 0;
            }
        }

        void a(String str) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            int i = (int) (jElapsedRealtime % 1000);
            long j = jElapsedRealtime / 1000;
            int i2 = (int) (j & 60);
            long j2 = j / 60;
            int i3 = (int) (j2 % 60);
            long j3 = j2 / 60;
            int i4 = (int) (j3 / 24);
            b(String.format(Locale.ENGLISH, "%03d-%02d:%02d:%02d.%03d  %s\n", Integer.valueOf(i4), Integer.valueOf((int) (j3 % 24)), Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i), str));
        }

        synchronized String c() {
            if (this.f492c == 0) {
                return "";
            }
            if (this.f493d == null) {
                StringBuilder sb = new StringBuilder(1024);
                int length = this.f492c;
                String[] strArr = this.f490a;
                if (length > strArr.length) {
                    length = strArr.length;
                    sb.append("...\n");
                }
                int i = this.f491b - length;
                for (int i2 = 0; i2 < length; i2++) {
                    int length2 = i + i2;
                    if (length2 < 0) {
                        length2 += this.f490a.length;
                    }
                    sb.append(this.f490a[length2]);
                }
                this.f493d = sb.toString();
            }
            return this.f493d;
        }
    }

    public static void a() {
        if (z == null) {
            z = new b();
        }
    }

    public static String b() {
        b bVar = z;
        return bVar != null ? bVar.c() : "";
    }

    public static void c(String str) {
        Log.i(l.x, str);
        if (z != null) {
            f(str);
        }
    }

    public static void d(Throwable th) {
        Log.e(l.x, "Caught throwable:", th);
        if (z != null) {
            try {
                StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
                f(th.getClass().getName() + ": " + th.getMessage() + " - at " + stackTraceElement.getClassName() + '.' + stackTraceElement.getMethodName());
            } catch (Throwable unused) {
            }
        }
    }

    public static void e(Object... objArr) {
        String strDeepToString;
        int length = objArr.length;
        if (length == 0) {
            strDeepToString = "";
        } else if (length == 1) {
            strDeepToString = objArr[0] != null ? objArr[0].toString() : "null";
        } else {
            strDeepToString = Arrays.deepToString(objArr);
        }
        Log.i(l.x, strDeepToString);
        if (z != null) {
            f(strDeepToString);
        }
    }

    public static void f(String str) {
        b bVar = z;
        if (bVar != null) {
            bVar.a(str);
        }
    }
}
