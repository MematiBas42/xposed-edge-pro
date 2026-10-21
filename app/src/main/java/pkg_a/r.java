package pkg_a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import pkg_f.l;
import pkg_f.v;

/* JADX INFO: loaded from: classes.dex */
public abstract class r implements l {
    private BroadcastReceiver A = null;
    private final String z;

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                r.this.b(intent);
            } catch (Throwable th) {
                v.d(th);
            }
        }
    }

    r(String str) {
        this.z = str;
    }

    protected abstract void b(Intent intent);

    protected void c(Context context, Intent intent) {
        intent.setAction(this.z).setPackage("android");
        context.sendBroadcast(intent);
    }

    public void d(Context context, Handler handler) {
        try {
            a aVar = new a();
            this.A = aVar;
            context.registerReceiver(aVar, new IntentFilter(this.z), null, handler, 2);
        } catch (Throwable th) {
            v.d(th);
        }
    }
}
