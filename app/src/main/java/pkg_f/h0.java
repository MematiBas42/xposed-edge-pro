package pkg_f;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class h0 extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile Handler f436a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile Handler f437b;

    public h0(Looper looper) {
        super(looper);
    }

    public static Handler a() {
        if (f436a == null) {
            synchronized (h0.class) {
                if (f436a == null) {
                    f436a = new h0(Looper.getMainLooper());
                }
            }
        }
        return f436a;
    }

    public static Handler b() {
        Handler handler = f437b;
        return handler != null ? handler : a();
    }

    public static boolean c(Handler handler) {
        int i = Build.VERSION.SDK_INT;
        Looper looper = handler.getLooper();
        if (i >= 23) {
            return looper.isCurrentThread();
        }
        return looper.getThread() == Thread.currentThread();
    }

    public static Handler d() {
        Looper looperMyLooper = Looper.myLooper();
        return (looperMyLooper == null || looperMyLooper == Looper.getMainLooper()) ? a() : new h0(looperMyLooper);
    }

    public static Handler e(String str) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        return new h0(handlerThread.getLooper());
    }

    public static void f(Handler handler, Runnable runnable) {
        if (c(handler)) {
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }

    public static void g(Handler handler) {
        f437b = handler;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        try {
            super.dispatchMessage(message);
        } catch (Throwable th) {
            v.d(th);
        }
    }
}
