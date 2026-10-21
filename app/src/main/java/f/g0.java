package f;

import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public abstract class g0 implements Runnable {
    private final int A;
    private final long B;
    private int C = 0;
    private final Handler z;

    public g0(Handler handler, int i, long j) {
        this.z = handler;
        this.A = i;
        this.B = j;
    }

    private void c() {
        try {
            b();
        } catch (Throwable th) {
            v.d(th);
        }
        this.C = this.A;
    }

    protected abstract boolean a(int i);

    protected abstract void b();

    public final void d() {
        this.C = 0;
        this.z.removeCallbacks(this);
        e();
    }

    public final void e() {
        if (this.C < this.A) {
            this.z.postDelayed(this, this.B);
        }
    }

    public final void f() {
        this.C = this.A;
        this.z.removeCallbacks(this);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a A[Catch: all -> 0x001e, TRY_LEAVE, TryCatch #0 {all -> 0x001e, blocks: (B:2:0x0000, B:4:0x0009, B:6:0x0012, B:7:0x001a), top: B:13:0x0000 }] */
    @Override // java.lang.Runnable
    public void run() {
        try {
            if (a(this.C)) {
                int i = this.C + 1;
                this.C = i;
                if (i < this.A) {
                    this.z.postDelayed(this, this.B);
                } else {
                    c();
                }
            } else {
                c();
            }
        } catch (Throwable th) {
            v.d(th);
            c();
        }
    }
}
