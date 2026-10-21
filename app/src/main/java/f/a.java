package f;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f406c = l.k + "SCHEDULE-";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f407d = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final String f408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f409b = 0;

    /* JADX INFO: renamed from: f.a$a, reason: collision with other inner class name */
    private static class C0005a extends a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Context f410e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final Handler f411f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final c f412g;
        private final int h;
        private BroadcastReceiver i;

        /* JADX INFO: renamed from: f.a$a$a, reason: collision with other inner class name */
        class C0006a extends BroadcastReceiver {
            C0006a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    C0005a c0005a = C0005a.this;
                    long j = c0005a.f409b;
                    c0005a.f409b = 0L;
                    c0005a.f412g.a(C0005a.this, j);
                    C0005a c0005a2 = C0005a.this;
                    if (c0005a2.f409b <= 0) {
                        context.unregisterReceiver(c0005a2.i);
                        C0005a.this.i = null;
                    }
                } catch (Throwable th) {
                    v.d(th);
                }
            }
        }

        C0005a(Context context, Handler handler, c cVar, int i) {
            this.f410e = context;
            this.f411f = handler;
            this.f412g = cVar;
            this.h = i;
        }

        @Override // f.a
        public void a() {
            this.f409b = 0L;
            BroadcastReceiver broadcastReceiver = this.i;
            if (broadcastReceiver != null) {
                try {
                    this.f410e.unregisterReceiver(broadcastReceiver);
                    this.i = null;
                    ((AlarmManager) this.f410e.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(this.f410e, 0, new Intent(this.f408a), Build.VERSION.SDK_INT >= 31 ? 301989888 : 268435456));
                } catch (Throwable th) {
                    v.d(th);
                }
            }
        }

        @Override // f.a
        public void d(long j) {
            if (this.i == null) {
                C0006a c0006a = new C0006a();
                this.i = c0006a;
                this.f410e.registerReceiver(c0006a, new IntentFilter(this.f408a), null, this.f411f, 2);
            }
            AlarmManager alarmManager = (AlarmManager) this.f410e.getSystemService("alarm");
            Intent intent = new Intent(this.f408a);
            intent.setPackage("android");
            try {
                alarmManager.setExact(this.h, j, PendingIntent.getBroadcast(this.f410e, 0, intent, Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728));
                this.f409b = j;
            } catch (Throwable th) {
                v.d(th);
            }
        }
    }

    private static class b extends a implements AlarmManager.OnAlarmListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Context f414e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final Handler f415f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final c f416g;
        private final int h;
        private C0005a i = null;

        b(Context context, Handler handler, c cVar, int i) {
            this.f414e = context;
            this.f415f = handler;
            this.f416g = cVar;
            this.h = i;
        }

        @Override // f.a
        public void a() {
            C0005a c0005a = this.i;
            if (c0005a != null) {
                c0005a.a();
                return;
            }
            this.f409b = 0L;
            try {
                ((AlarmManager) this.f414e.getSystemService("alarm")).cancel(this);
            } catch (Throwable th) {
                v.d(th);
            }
        }

        @Override // f.a
        public void d(long j) {
            C0005a c0005a = this.i;
            if (c0005a != null) {
                c0005a.d(j);
                return;
            }
            AlarmManager alarmManager = (AlarmManager) this.f414e.getSystemService("alarm");
            if (this.f409b > 0) {
                try {
                    alarmManager.cancel(this);
                } catch (Throwable th) {
                    v.d(th);
                }
            }
            try {
                alarmManager.setExact(this.h, j, this.f408a, this, this.f415f);
                this.f409b = j;
            } catch (Throwable th2) {
                v.d(th2);
                C0005a c0005a2 = new C0005a(this.f414e, this.f415f, this.f416g, this.h);
                this.i = c0005a2;
                c0005a2.d(j);
            }
        }

        @Override // android.app.AlarmManager.OnAlarmListener
        public void onAlarm() {
            try {
                long j = this.f409b;
                this.f409b = 0L;
                this.f416g.a(this, j);
            } catch (Throwable th) {
                v.d(th);
            }
        }
    }

    public interface c {
        void a(a aVar, long j);
    }

    a() {
        StringBuilder sb = new StringBuilder();
        sb.append(f406c);
        int i = f407d + 1;
        f407d = i;
        sb.append(i);
        this.f408a = sb.toString();
    }

    public static a c(Context context, Handler handler, c cVar, int i) {
        return Build.VERSION.SDK_INT >= 24 ? new b(context, handler, cVar, i) : new C0005a(context, handler, cVar, i);
    }

    public abstract void a();

    public long b() {
        return this.f409b;
    }

    public abstract void d(long j);
}
