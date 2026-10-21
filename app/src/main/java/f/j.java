package f;

import android.content.Context;
import com.jozein.xedgepro.R;
import java.text.DateFormatSymbols;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f452b = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Calendar f453a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f454a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f455b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f456c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f457d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f458e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int f459f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final int f460g;

        public a(long j) {
            long j2 = (j / 60000) * 60000;
            this.f454a = j2;
            j jVar = new j(j2);
            this.f455b = jVar.q();
            this.f456c = jVar.i();
            this.f457d = jVar.c();
            this.f458e = jVar.d();
            this.f459f = jVar.g();
            this.f460g = jVar.h();
        }

        public String toString() {
            return "TimePoint { " + this.f455b + ", " + this.f456c + "+1/" + this.f457d + "+1, " + this.f458e + ", " + this.f459f + ":" + this.f460g + " }";
        }
    }

    public j() {
        this.f453a = Calendar.getInstance();
    }

    public j(long j) {
        Calendar calendar = Calendar.getInstance();
        this.f453a = calendar;
        calendar.setTimeInMillis(j);
    }

    public static int b(int i, int i2) {
        if (i2 != 1) {
            return f452b[i2];
        }
        return r(i) ? 29 : 28;
    }

    public static String[] e(Context context) {
        return new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", context.getString(R.string.last_day_of_month)};
    }

    public static String f(Context context, int i) {
        if (i == 0) {
            return "-";
        }
        StringBuilder sb = new StringBuilder();
        String[] strArrE = e(context);
        for (int i2 = 0; i2 < strArrE.length; i2++) {
            if (((1 << i2) & i) != 0) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(strArrE[i2]);
            }
        }
        return sb.toString();
    }

    public static String[] j() {
        return new DateFormatSymbols().getMonths();
    }

    public static String k(int i) {
        if (i == 0) {
            return "-";
        }
        StringBuilder sb = new StringBuilder();
        String[] strArrM = m();
        for (int i2 = 0; i2 < strArrM.length; i2++) {
            if (((1 << i2) & i) != 0) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(strArrM[i2]);
            }
        }
        return sb.toString();
    }

    public static long l() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        for (int i2 = i + 1; i2 < i + 9; i2++) {
            if (r(i2)) {
                calendar.set(i2, 1, 29, 0, 0, 0);
                return calendar.getTimeInMillis();
            }
        }
        return 0L;
    }

    public static String[] m() {
        return new DateFormatSymbols().getShortMonths();
    }

    public static String[] n() {
        String[] strArr = new String[7];
        System.arraycopy(new DateFormatSymbols().getShortWeekdays(), 1, strArr, 0, 7);
        return strArr;
    }

    public static String[] o() {
        String[] strArr = new String[7];
        System.arraycopy(new DateFormatSymbols().getWeekdays(), 1, strArr, 0, 7);
        return strArr;
    }

    public static String p(int i) {
        if (i == 0) {
            return "-";
        }
        StringBuilder sb = new StringBuilder();
        String[] strArrN = n();
        for (int i2 = 0; i2 < strArrN.length; i2++) {
            if (((1 << i2) & i) != 0) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(strArrN[i2]);
            }
        }
        return sb.toString();
    }

    public static boolean r(int i) {
        if (i % 100 != 0) {
            if (i % 4 == 0) {
                return true;
            }
        } else if (i % 400 == 0) {
            return true;
        }
        return false;
    }

    public int a() {
        return this.f453a.getActualMaximum(5);
    }

    public int c() {
        return this.f453a.get(5) - 1;
    }

    public int d() {
        return this.f453a.get(7) - 1;
    }

    public int g() {
        return this.f453a.get(11);
    }

    public int h() {
        return this.f453a.get(12);
    }

    public int i() {
        return this.f453a.get(2);
    }

    public int q() {
        return this.f453a.get(1);
    }
}
