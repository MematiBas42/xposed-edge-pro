package pkg_a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.jozein.xedgepro.ApplicationMain;

/* JADX INFO: loaded from: classes.dex */
public abstract class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f54a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BroadcastReceiver f55b;

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            y.this.b();
            try {
                y.this.a(intent.getStringArrayExtra("list"));
            } catch (Throwable th) {
                pkg_f.v.d(th);
            }
        }
    }

    public y(Context context) {
        this.f55b = null;
        this.f54a = context;
        if (!ApplicationMain.isModuleActivated()) {
            a(null);
            return;
        }
        a aVar = new a();
        this.f55b = aVar;
        this.f54a.registerReceiver(aVar, new IntentFilter(b.s0.M), 2);
        b.s0.B(this.f54a, 9);
    }

    protected abstract void a(String[] strArr);

    public void b() {
        BroadcastReceiver broadcastReceiver = this.f55b;
        if (broadcastReceiver != null) {
            this.f54a.unregisterReceiver(broadcastReceiver);
            this.f54a = null;
            this.f55b = null;
        }
    }
}
