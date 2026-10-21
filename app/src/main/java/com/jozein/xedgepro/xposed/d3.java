package com.jozein.xedgepro.xposed;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.provider.Settings;
import android.util.Property;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class d3 implements b0.a {
    private static final Property<b, Float> K = new a(Float.class, "setAnimateProgress");
    private final Context A;
    private final Handler B;
    private final w1 C;
    private final Paint D;
    private final Paint E;
    private final Paint F;
    private final Paint G;
    private final Paint H;
    private b I = null;
    private int J = 0;
    private final a.p z;

    class a extends Property<b, Float> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(b bVar) {
            return Float.valueOf(bVar.R);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(b bVar, Float f2) {
            bVar.h(f2.floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class b extends View implements a.p.f, b0.b {
        private final float A;
        private final float B;
        private final float C;
        private final float D;
        private final float E;
        private final float F;
        private final float G;
        private final float H;
        private final float I;
        private final float J;
        private float K;
        private float L;
        private float M;
        private final int[] N;
        private int O;
        private boolean P;
        private final float Q;
        private float R;
        private final float z;

        public b(int[] iArr, float f2, float f3, float f4, float f5, int i, boolean z) {
            super(d3.this.A);
            this.K = 0.0f;
            this.L = 0.0f;
            this.M = 0.0f;
            this.O = 0;
            this.P = false;
            this.R = 0.0f;
            float fN = d3.this.C.g2().N();
            float f6 = 0.3f * fN;
            this.z = f6;
            float f7 = (0.01f * fN) / 2.0f;
            float f8 = 0.025f * fN;
            this.A = f8;
            float f9 = fN * 0.018f;
            this.B = f9;
            this.Q = f9 * 4.0f;
            float f10 = 2.0f * f7;
            d3.this.D.setStrokeWidth(f10);
            d3.this.E.setStrokeWidth(f7);
            d3.this.G.setStrokeWidth(f8);
            d3.this.H.setStrokeWidth(f10);
            this.N = iArr;
            this.C = f2 - 0.1f;
            this.D = f3 - 0.1f;
            this.E = f4;
            this.F = f5;
            float f11 = i / 100.0f;
            this.G = f11;
            this.H = f6 * f11;
            this.I = z ? -f7 : f7;
            this.J = f7;
            setKeepScreenOn(true);
        }

        private void f(Canvas canvas, float f2, float f3, float f4, float f5, Paint paint) {
            float f6 = this.A;
            float f7 = f6 * 3.0f;
            canvas.drawLine(f2, f3, f4, f5, paint);
            canvas.drawCircle(f2, f3, this.M, paint);
            int i = this.N[this.O];
            if (i == 1) {
                canvas.drawCircle(f4, f5, f6, paint);
            } else {
                if (i != 2) {
                    if (i == 3) {
                        canvas.drawCircle(f4, f5, f6, paint);
                    } else {
                        if (i != 4) {
                            canvas.drawCircle(f4, f5, f6, paint);
                            return;
                        }
                        canvas.drawCircle(f4, f5, 2.0f * f6, paint);
                    }
                }
                f6 *= 2.0f;
                canvas.drawCircle(f4, f5, f6, paint);
            }
            float f8 = f6;
            canvas.drawLine(f4 - f7, f5, f4 - f8, f5, paint);
            canvas.drawLine(f4 + f8, f5, f4 + f7, f5, paint);
            canvas.drawLine(f4, f5 - f7, f4, f5 - f8, paint);
            canvas.drawLine(f4, f5 + f8, f4, f5 + f7, paint);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void g(float f2, float f3) {
            this.K = f2;
            this.L = f3;
            invalidate();
        }

        @Override // com.jozein.xedgepro.xposed.b0.b
        public void a(final float f2, final float f3) {
            d3.this.B.post(new Runnable() { // from class: com.jozein.xedgepro.xposed.e3
                @Override // java.lang.Runnable
                public final void run() {
                    this.z.g(f2, f3);
                }
            });
        }

        void h(float f2) {
            this.R = f2;
            invalidate();
        }

        public void i() {
            long j;
            int i = this.N[this.O];
            if (i == 1) {
                d3.this.C.W1().h0(this.K, this.L);
                j = 300;
            } else if (i == 2) {
                d3.this.C.W1().d0(this.K, this.L);
                j = 600;
            } else {
                if (i != 3) {
                    if (i != 4) {
                        d3.x(0L);
                        return;
                    }
                    d3.this.C.W1().V().P(this.K, this.L, this);
                    this.R = 1.0f;
                    invalidate();
                    d3.this.C.w4(3000L);
                    return;
                }
                d3.this.C.W1().X(this.K, this.L);
                j = 450;
            }
            d3.x((long) (j * Settings.Global.getFloat(getContext().getContentResolver(), "animator_duration_scale", 1.0f)));
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<b, Float>) d3.K, 0.0f, 1.0f);
            objectAnimatorOfFloat.setDuration(j);
            objectAnimatorOfFloat.start();
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0044 A[PHI: r8
          0x0044: PHI (r8v6 float) = (r8v3 float), (r8v4 float) binds: [B:13:0x0042, B:16:0x004b] A[DONT_GENERATE, DONT_INLINE]] */
        public void j(float f2, float f3) {
            boolean z = this.M >= this.H;
            float f4 = f2 - this.E;
            float f5 = this.G;
            float f6 = f4 * f5;
            float f7 = (f3 - this.F) * f5;
            float fSqrt = (float) Math.sqrt((f6 * f6) + (f7 * f7));
            this.M = fSqrt;
            float f8 = this.E + f6;
            this.K = f8;
            float f9 = this.F + f7;
            this.L = f9;
            float f10 = 0.0f;
            if (f8 < 0.0f) {
                this.K = 0.0f;
            } else {
                float f11 = this.C;
                if (f8 > f11) {
                    this.K = f11;
                }
            }
            if (f9 < 0.0f) {
                this.L = f10;
            } else {
                f10 = this.D;
                if (f9 > f10) {
                    this.L = f10;
                }
            }
            boolean z2 = fSqrt >= this.H;
            if (!z && z2) {
                if (this.P) {
                    int i = this.O + 1;
                    this.O = i;
                    int[] iArr = this.N;
                    if (i >= iArr.length || iArr[i] <= 0) {
                        this.O = 0;
                    }
                } else {
                    this.P = true;
                }
            }
            invalidate();
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            Paint paint;
            float f8 = this.R;
            if (f8 <= 0.0f) {
                canvas.drawCircle(this.E, this.F, this.z, d3.this.F);
                canvas.drawCircle(this.E, this.F, this.z, d3.this.D);
                if (this.M >= this.H) {
                    float f9 = this.E;
                    float f10 = this.I;
                    float f11 = f9 + f10;
                    float f12 = this.F;
                    float f13 = this.J;
                    f(canvas, f11, f12 + f13, this.K + f10, this.L + f13, d3.this.E);
                    f(canvas, this.E, this.F, this.K, this.L, d3.this.D);
                    return;
                }
                return;
            }
            int i = this.N[this.O];
            if (i == 1) {
                f2 = this.K;
                f3 = this.L;
                f4 = this.B * f8 * 4.0f;
            } else {
                if (i != 2) {
                    if (i == 3) {
                        canvas.drawCircle(this.K, this.L, ((this.B * f8) * 4.0f) / 2.0f, d3.this.G);
                        f5 = this.K;
                        f6 = this.L;
                        f7 = this.B * this.R * 4.0f;
                        paint = d3.this.H;
                    } else {
                        if (i != 4) {
                            return;
                        }
                        canvas.drawCircle(this.K, this.L, this.Q, d3.this.G);
                        f5 = this.K;
                        f6 = this.L;
                        f7 = this.Q;
                        paint = d3.this.D;
                    }
                    canvas.drawCircle(f5, f6, f7, paint);
                    return;
                }
                f2 = this.K;
                f3 = this.L;
                f4 = this.B * ((f8 * 4.0f) + 1.0f);
            }
            canvas.drawCircle(f2, f3, f4, d3.this.G);
        }
    }

    d3(w1 w1Var) {
        this.z = w1Var.b2();
        this.A = w1Var.T1();
        this.B = w1Var.U1();
        this.C = w1Var;
        Paint paint = new Paint(1);
        this.D = paint;
        paint.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint(1);
        this.E = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(2144128204);
        Paint paint3 = new Paint(1);
        this.F = paint3;
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(2144128204);
        this.G = paint3;
        Paint paint4 = new Paint(paint3);
        this.H = paint4;
        paint4.setStyle(Paint.Style.STROKE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t() {
        b bVar = this.I;
        this.I = null;
        if (bVar != null) {
            x(0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(float f2, float f3) {
        b bVar = this.I;
        if (bVar != null) {
            bVar.j(f2, f3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(float f2, float f3, int i) {
        this.D.setColor(this.z.v(16, -14509620));
        Point pointI = this.C.g2().I(new Point());
        int i2 = this.J;
        this.I = new b(i2 == 0 ? this.z.z() : new int[]{i2}, pointI.x, pointI.y, f2, f3, this.z.v(15, 250), i == 1);
        t3.n().j(this.I, 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(float f2, float f3) {
        b bVar = this.I;
        this.I = null;
        if (bVar != null) {
            bVar.j(f2, f3);
            if (bVar.M >= bVar.H) {
                bVar.i();
            } else {
                x(0L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void x(long j) {
        t3.n().u(7, j);
    }

    @Override // com.jozein.xedgepro.xposed.b0.a
    public void b(final float f2, final float f3, long j) {
        this.B.post(new Runnable() { // from class: com.jozein.xedgepro.xposed.a3
            @Override // java.lang.Runnable
            public final void run() {
                this.z.w(f2, f3);
            }
        });
    }

    @Override // com.jozein.xedgepro.xposed.b0.a
    public void c() {
        this.B.post(new Runnable() { // from class: com.jozein.xedgepro.xposed.z2
            @Override // java.lang.Runnable
            public final void run() {
                this.z.t();
            }
        });
    }

    @Override // com.jozein.xedgepro.xposed.b0.a
    public void d(final float f2, final float f3, long j) {
        this.B.post(new Runnable() { // from class: com.jozein.xedgepro.xposed.b3
            @Override // java.lang.Runnable
            public final void run() {
                this.z.u(f2, f3);
            }
        });
    }

    @Override // com.jozein.xedgepro.xposed.b0.a
    public void e(final int i, final float f2, final float f3, long j) {
        this.B.post(new Runnable() { // from class: com.jozein.xedgepro.xposed.c3
            @Override // java.lang.Runnable
            public final void run() {
                this.z.v(f2, f3, i);
            }
        });
    }

    void s() {
        if (this.I != null) {
            c();
        }
    }

    public d3 y(int i) {
        this.J = i;
        return this;
    }
}
