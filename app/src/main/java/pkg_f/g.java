package pkg_f;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f434a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f435b;

    /* JADX WARN: Code duplicated, block: B:16:0x0042  */
    /* JADX WARN: Code duplicated, block: B:31:0x0023 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        int i = Build.VERSION.SDK_INT >= 24 ? 1 : 10;
        int i2 = 255;
        int i3 = i;
        try {
            int iM = p0.m("config_screenBrightnessSettingMinimum", -1);
            if (iM == -1) {
                iM = p0.m("config_screenBrightnessDim", i);
            }
            int iM2 = p0.m("config_screenBrightnessSettingMaximum", 255);
            if (iM2 < 255) {
                v.c("Got max brightness " + iM2 + ", force set to 255.");
            } else {
                i2 = iM2;
            }
            i3 = iM;
        } catch (Throwable th) {
            v.d(th);
        }
        if (i3 == -1) {
            i3 = i;
        }
        f434a = i3;
        f435b = i2;
    }

    public static int a(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "screen_brightness", (f434a + f435b) / 2);
    }
}
