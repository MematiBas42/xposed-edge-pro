package com.jozein.xedgepro.xposed;

import android.content.Intent;
import android.media.AudioManager;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import de.robv.android.xposed.XposedHelpers;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final w1 f91a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f92b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private AudioManager f93c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Object f94d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Runnable f95e = new Runnable() { // from class: com.jozein.xedgepro.xposed.d
        @Override // java.lang.Runnable
        public final void run() {
            this.z.h();
        }
    };

    e(w1 w1Var) {
        this.f94d = null;
        this.f91a = w1Var;
        this.f92b = w1Var.U1();
        try {
            this.f93c = (AudioManager) w1Var.T1().getSystemService("audio");
            this.f94d = XposedHelpers.callStaticMethod(AudioManager.class, "getService", new Object[0]);
        } catch (Throwable th) {
            f.v.d(th);
        }
    }

    void a(int i, int i2, int i3) {
        if (i >= 0) {
            this.f93c.adjustStreamVolume(i, i2, i3);
        } else {
            this.f93c.adjustVolume(i2, i3);
        }
    }

    void b(int i) {
        long jUptimeMillis = SystemClock.uptimeMillis() - 10;
        this.f93c.dispatchMediaKeyEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, 0, i, 0));
        this.f93c.dispatchMediaKeyEvent(new KeyEvent(jUptimeMillis, jUptimeMillis + 10, 1, i, 0));
        if (Build.VERSION.SDK_INT >= 21) {
            this.f92b.removeCallbacks(this.f95e);
            if (i != 126 || g()) {
                return;
            }
            this.f92b.postDelayed(this.f95e, 1000L);
        }
    }

    int c() {
        try {
            return ((Integer) XposedHelpers.callMethod(this.f94d, "getActiveStreamType", new Object[]{Integer.MIN_VALUE})).intValue();
        } catch (Throwable unused) {
            return this.f93c.isMusicActive() ? 3 : 2;
        }
    }

    int d() {
        return this.f93c.getRingerMode();
    }

    int e(int i) {
        return this.f93c.getStreamMaxVolume(i);
    }

    int[] f() {
        try {
            return (int[]) XposedHelpers.getObjectField(this.f94d, "mStreamVolumeAlias");
        } catch (Throwable unused) {
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        }
    }

    boolean g() {
        return this.f93c.isMusicActive();
    }

    void h() {
        if (Build.VERSION.SDK_INT < 21 || g()) {
            return;
        }
        int iD = f.o0.d();
        try {
            MediaSessionManager mediaSessionManager = (MediaSessionManager) this.f91a.T1().getSystemService("media_session");
            List<MediaController> activeSessions = iD == 0 ? mediaSessionManager.getActiveSessions(null) : (List) XposedHelpers.callMethod(mediaSessionManager, "getActiveSessionsForUser", (Class[]) null, new Object[]{Integer.valueOf(iD)});
            if (activeSessions.size() > 0) {
                activeSessions.get(0).getTransportControls().play();
                return;
            }
            l0 l0VarR1 = this.f91a.R1();
            String strK = l0VarR1.K.k("android.intent.category.APP_MUSIC", false);
            if (strK != null) {
                long jUptimeMillis = SystemClock.uptimeMillis() - 10;
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                intent.setPackage(strK);
                intent.addFlags(32);
                intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(jUptimeMillis, jUptimeMillis, 0, 126, 0));
                l0VarR1.Y(intent);
                Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
                intent2.setPackage(strK);
                intent2.addFlags(32);
                intent2.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(jUptimeMillis, jUptimeMillis + 10, 1, 126, 0));
                l0VarR1.Y(intent2);
            }
        } catch (Throwable th) {
            f.v.d(th);
        }
    }

    void i(int i) {
        this.f93c.playSoundEffect(i, -1.0f);
    }

    void j(int i) {
        this.f93c.setRingerMode(i);
    }

    void k(int i, int i2, int i3) {
        this.f93c.setStreamVolume(i, i2, i3);
    }
}
