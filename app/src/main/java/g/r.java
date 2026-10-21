package g;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class r extends View {
    private final Paint[] A;
    private final boolean B;
    private Path C;
    private ArrayList<i> D;
    private int E;
    private final h F;
    private final g G;
    private int H;
    private boolean I;
    private e J;
    private boolean K;
    private final int[] z;

    private static class b implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final float f576a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final float f577b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final float f578c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Paint f579d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final float[] f580e = new float[14];

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final PointF f581f = new PointF();

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private float f582g = -1.0f;
        private float h = 0.0f;
        private float i = 0.0f;
        private float j = 0.0f;
        private Path k = null;
        private boolean l = false;

        b(Context context, Paint paint) {
            this.f579d = paint;
            float fM = a0.m(context);
            this.f576a = 0.03f * fM;
            this.f577b = 0.075f * fM;
            this.f578c = fM * 0.08f;
        }

        private void g() {
            float fI = r.i(this.f582g, this.h, this.i, this.j);
            float f2 = this.f578c;
            if (fI < f2 * f2) {
                this.l = false;
                return;
            }
            this.l = true;
            float f3 = r.f(this.f582g, this.h, this.i, this.j);
            float f4 = f3 + 1.5707964f;
            PointF pointFJ = r.j(this.f582g, this.h, f4, this.f576a, this.f581f);
            float[] fArr = this.f580e;
            fArr[6] = pointFJ.x;
            fArr[7] = pointFJ.y;
            float f5 = f3 - 1.5707964f;
            PointF pointFJ2 = r.j(this.f582g, this.h, f5, this.f576a, this.f581f);
            float[] fArr2 = this.f580e;
            fArr2[8] = pointFJ2.x;
            fArr2[9] = pointFJ2.y;
            float f6 = this.i;
            float f7 = this.j;
            double d2 = f3;
            Double.isNaN(d2);
            PointF pointFJ3 = r.j(f6, f7, (float) (d2 + 3.141592653589793d), this.f578c, this.f581f);
            float f8 = pointFJ3.x;
            float f9 = pointFJ3.y;
            PointF pointFJ4 = r.j(f8, f9, f4, this.f576a, this.f581f);
            float[] fArr3 = this.f580e;
            fArr3[4] = pointFJ4.x;
            fArr3[5] = pointFJ4.y;
            PointF pointFJ5 = r.j(f8, f9, f5, this.f576a, this.f581f);
            float[] fArr4 = this.f580e;
            fArr4[10] = pointFJ5.x;
            fArr4[11] = pointFJ5.y;
            PointF pointFJ6 = r.j(f8, f9, f4, this.f577b, this.f581f);
            float[] fArr5 = this.f580e;
            fArr5[2] = pointFJ6.x;
            fArr5[3] = pointFJ6.y;
            PointF pointFJ7 = r.j(f8, f9, f5, this.f577b, this.f581f);
            float[] fArr6 = this.f580e;
            fArr6[12] = pointFJ7.x;
            fArr6[13] = pointFJ7.y;
            Path path = this.k;
            if (path == null) {
                this.k = new Path();
            } else {
                path.reset();
            }
            Path path2 = this.k;
            float[] fArr7 = this.f580e;
            path2.moveTo(fArr7[0], fArr7[1]);
            for (int i = 1; i < 7; i++) {
                Path path3 = this.k;
                float[] fArr8 = this.f580e;
                int i2 = i * 2;
                path3.lineTo(fArr8[i2], fArr8[i2 + 1]);
            }
            this.k.close();
        }

        @Override // g.r.e
        public void a(d dVar) {
            float[] fArr = this.f580e;
            float fE = dVar.e();
            this.i = fE;
            fArr[0] = fE;
            float[] fArr2 = this.f580e;
            float fG = dVar.g();
            this.j = fG;
            fArr2[1] = fG;
            g();
        }

        @Override // g.r.e
        public Path b(d dVar) {
            a(dVar);
            Path path = this.l ? this.k : null;
            this.k = null;
            return path;
        }

        @Override // g.r.e
        public void c(Canvas canvas) {
            Path path;
            if (!this.l || (path = this.k) == null) {
                return;
            }
            canvas.drawPath(path, this.f579d);
        }

        @Override // g.r.e
        public void d(d dVar) {
        }

        @Override // g.r.e
        public boolean e(d dVar) {
            return false;
        }

        @Override // g.r.e
        public void f(d dVar) {
            float fE = dVar.e();
            this.f582g = fE;
            this.i = fE;
            float fG = dVar.g();
            this.h = fG;
            this.j = fG;
        }
    }

    private static class c implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Paint f583a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f586d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Path f587e;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Path[] f584b = new Path[10];

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Path[] f585c = new Path[10];

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f588f = false;

        c(Paint paint, boolean z) {
            this.f583a = paint;
            this.f586d = z;
            this.f587e = z ? null : new Path();
            for (int i = 0; i < 10; i++) {
                this.f584b[i] = new Path();
            }
        }

        private void g(int i, float f2, float f3) {
            Path[] pathArr = this.f585c;
            pathArr[i] = this.f584b[i];
            pathArr[i].moveTo(f2, f3);
            this.f585c[i].lineTo(f2, f3 + 0.1f);
        }

        @Override // g.r.e
        public void a(d dVar) {
            this.f588f = true;
            int iB = dVar.b();
            for (int i = 0; i < iB; i++) {
                int iF = dVar.f(i);
                Path[] pathArr = this.f585c;
                if (pathArr[iF] != null) {
                    pathArr[iF].lineTo(dVar.c(i), dVar.a(i));
                } else {
                    g(iF, dVar.c(i), dVar.a(i));
                }
            }
        }

        @Override // g.r.e
        public Path b(d dVar) {
            a(dVar);
            int i = 0;
            this.f588f = false;
            if (this.f586d) {
                while (i < 10) {
                    Path[] pathArr = this.f585c;
                    if (pathArr[i] != null) {
                        pathArr[i].reset();
                        this.f585c[i] = null;
                    }
                    i++;
                }
                return null;
            }
            this.f587e.reset();
            while (i < 10) {
                Path[] pathArr2 = this.f585c;
                if (pathArr2[i] != null) {
                    this.f587e.addPath(pathArr2[i]);
                    this.f585c[i].reset();
                    this.f585c[i] = null;
                }
                i++;
            }
            return this.f587e;
        }

        @Override // g.r.e
        public void c(Canvas canvas) {
            if (this.f588f) {
                for (int i = 0; i < 10; i++) {
                    Path[] pathArr = this.f585c;
                    if (pathArr[i] != null) {
                        canvas.drawPath(pathArr[i], this.f583a);
                    }
                }
            }
        }

        @Override // g.r.e
        public void d(d dVar) {
            a(dVar);
            if (this.f586d) {
                int iH = dVar.h();
                Path[] pathArr = this.f585c;
                if (pathArr[iH] != null) {
                    pathArr[iH].reset();
                    this.f585c[iH] = null;
                }
            }
        }

        @Override // g.r.e
        public boolean e(d dVar) {
            Path path;
            this.f588f = true;
            int iH = dVar.h();
            int iB = dVar.b();
            for (int i = 0; i < iB; i++) {
                int iF = dVar.f(i);
                float fC = dVar.c(i);
                float fA = dVar.a(i);
                Path[] pathArr = this.f585c;
                if (pathArr[iF] == null) {
                    g(iF, fC, fA);
                } else {
                    if (i == iH) {
                        if (this.f586d) {
                            pathArr[iF].reset();
                        }
                        this.f585c[iF].moveTo(fC, fA);
                        path = this.f585c[iF];
                        fA += 0.1f;
                    } else {
                        path = pathArr[iF];
                    }
                    path.lineTo(fC, fA);
                }
            }
            return true;
        }

        @Override // g.r.e
        public void f(d dVar) {
            this.f588f = true;
            g(0, dVar.e(), dVar.g());
        }
    }

    private interface d {
        float a(int i);

        int b();

        float c(int i);

        int d();

        float e();

        int f(int i);

        float g();

        int h();
    }

    private interface e {
        void a(d dVar);

        Path b(d dVar);

        void c(Canvas canvas);

        void d(d dVar);

        boolean e(d dVar);

        void f(d dVar);
    }

    private static class f implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Paint f589a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f590b = -1.0f;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f591c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f592d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private float f593e = 0.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f594f = false;

        f(Paint paint) {
            this.f589a = paint;
        }

        @Override // g.r.e
        public void a(d dVar) {
            this.f594f = true;
            this.f592d = dVar.e();
            this.f593e = dVar.g();
        }

        @Override // g.r.e
        public Path b(d dVar) {
            a(dVar);
            this.f594f = false;
            Path path = new Path();
            path.moveTo(this.f590b, this.f591c);
            path.lineTo(this.f592d, this.f593e);
            return path;
        }

        @Override // g.r.e
        public void c(Canvas canvas) {
            if (this.f594f) {
                canvas.drawLine(this.f590b, this.f591c, this.f592d, this.f593e, this.f589a);
            }
        }

        @Override // g.r.e
        public void d(d dVar) {
        }

        @Override // g.r.e
        public boolean e(d dVar) {
            return false;
        }

        @Override // g.r.e
        public void f(d dVar) {
            this.f594f = false;
            this.f590b = dVar.e();
            this.f591c = dVar.g();
        }
    }

    private static class g implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public q f595a;

        private g() {
            this.f595a = null;
        }

        @Override // g.r.d
        public float a(int i) {
            return this.f595a.B[i].f575c;
        }

        @Override // g.r.d
        public int b() {
            return this.f595a.B.length;
        }

        @Override // g.r.d
        public float c(int i) {
            return this.f595a.B[i].f574b;
        }

        @Override // g.r.d
        public int d() {
            return this.f595a.A & 255;
        }

        @Override // g.r.d
        public float e() {
            return this.f595a.B[h()].f574b;
        }

        @Override // g.r.d
        public int f(int i) {
            return this.f595a.B[i].f573a;
        }

        @Override // g.r.d
        public float g() {
            return this.f595a.B[h()].f575c;
        }

        @Override // g.r.d
        public int h() {
            return (this.f595a.A & 65280) >> 8;
        }
    }

    private static class h implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public MotionEvent f596a;

        private h() {
            this.f596a = null;
        }

        @Override // g.r.d
        public float a(int i) {
            return this.f596a.getY(i);
        }

        @Override // g.r.d
        public int b() {
            return this.f596a.getPointerCount();
        }

        @Override // g.r.d
        public float c(int i) {
            return this.f596a.getX(i);
        }

        @Override // g.r.d
        public int d() {
            return this.f596a.getActionMasked();
        }

        @Override // g.r.d
        public float e() {
            return this.f596a.getX();
        }

        @Override // g.r.d
        public int f(int i) {
            return this.f596a.getPointerId(i);
        }

        @Override // g.r.d
        public float g() {
            return this.f596a.getY();
        }

        @Override // g.r.d
        public int h() {
            return this.f596a.getActionIndex();
        }
    }

    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Path f597a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Paint f598b;

        i(Path path, Paint paint) {
            this.f597a = path;
            this.f598b = paint;
        }
    }

    private static class j implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Paint f599a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final float f600b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final RectF f601c = new RectF();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f602d = -1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private float f603e = 0.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private float f604f = 0.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private float f605g = 0.0f;
        private boolean h = false;

        j(Context context, Paint paint) {
            this.f599a = paint;
            this.f600b = a0.m(context) * 0.01f;
        }

        private boolean g() {
            float f2 = this.f600b;
            float f3 = f2 + f2;
            float f4 = this.f604f;
            float f5 = this.f602d;
            if (f4 - f5 > f3 || f5 - f4 > f3) {
                float f6 = this.f605g;
                float f7 = this.f603e;
                if (f6 - f7 > f3 || f7 - f6 > f3) {
                    return true;
                }
            }
            return false;
        }

        @Override // g.r.e
        public void a(d dVar) {
            this.h = true;
            this.f604f = dVar.e();
            float fG = dVar.g();
            this.f605g = fG;
            float f2 = this.f602d;
            float f3 = this.f604f;
            if (f2 < f3) {
                RectF rectF = this.f601c;
                rectF.left = f2;
                rectF.right = f3;
            } else {
                RectF rectF2 = this.f601c;
                rectF2.left = f3;
                rectF2.right = f2;
            }
            float f4 = this.f603e;
            if (f4 < fG) {
                RectF rectF3 = this.f601c;
                rectF3.top = f4;
                rectF3.bottom = fG;
            } else {
                RectF rectF4 = this.f601c;
                rectF4.top = fG;
                rectF4.bottom = f4;
            }
        }

        @Override // g.r.e
        public Path b(d dVar) {
            a(dVar);
            this.h = false;
            Path path = new Path();
            if (g()) {
                RectF rectF = this.f601c;
                float f2 = this.f600b;
                path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
            } else {
                path.addRect(this.f601c, Path.Direction.CW);
            }
            return path;
        }

        @Override // g.r.e
        public void c(Canvas canvas) {
            if (this.h) {
                if (!g()) {
                    canvas.drawRect(this.f601c, this.f599a);
                    return;
                }
                RectF rectF = this.f601c;
                float f2 = this.f600b;
                canvas.drawRoundRect(rectF, f2, f2, this.f599a);
            }
        }

        @Override // g.r.e
        public void d(d dVar) {
        }

        @Override // g.r.e
        public boolean e(d dVar) {
            return false;
        }

        @Override // g.r.e
        public void f(d dVar) {
            this.h = false;
            float fE = dVar.e();
            this.f604f = fE;
            this.f602d = fE;
            float fG = dVar.g();
            this.f605g = fG;
            this.f603e = fG;
        }
    }

    private static class k implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Paint f606a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Path f607b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f608c = false;

        k(Paint paint) {
            this.f606a = paint;
        }

        @Override // g.r.e
        public void a(d dVar) {
            Path path = this.f607b;
            if (path != null) {
                path.lineTo(dVar.e(), dVar.g());
                this.f608c = true;
            }
        }

        @Override // g.r.e
        public Path b(d dVar) {
            a(dVar);
            this.f608c = false;
            Path path = this.f607b;
            this.f607b = null;
            return path;
        }

        @Override // g.r.e
        public void c(Canvas canvas) {
            Path path;
            if (!this.f608c || (path = this.f607b) == null) {
                return;
            }
            canvas.drawPath(path, this.f606a);
        }

        @Override // g.r.e
        public void d(d dVar) {
        }

        @Override // g.r.e
        public boolean e(d dVar) {
            return false;
        }

        @Override // g.r.e
        public void f(d dVar) {
            this.f608c = false;
            this.f607b = new Path();
            float fE = dVar.e();
            float fG = dVar.g();
            this.f607b.moveTo(fE, fG);
            this.f607b.lineTo(fE, fG + 0.1f);
        }
    }

    public r(Context context, int i2) {
        this(context, new int[]{i2}, false);
    }

    public r(Context context, int[] iArr) {
        this(context, iArr, true);
    }

    private r(Context context, int[] iArr, boolean z) {
        super(context);
        this.C = null;
        this.D = null;
        this.E = 0;
        this.F = new h();
        this.G = new g();
        this.H = 0;
        this.I = false;
        this.J = null;
        this.K = true;
        this.z = iArr;
        this.B = z;
        this.A = new Paint[iArr.length * 2];
    }

    private void d(d dVar) {
        e eVar;
        e eVar2;
        e eVar3;
        e eVar4;
        int iD = dVar.d();
        if (iD == 0) {
            this.K = true;
            e eVar5 = this.J;
            if (eVar5 != null) {
                eVar5.f(dVar);
                return;
            }
            return;
        }
        if (iD != 1) {
            if (iD != 2) {
                if (iD != 5) {
                    if (iD != 6) {
                        if (this.K) {
                            this.K = false;
                            invalidate();
                            return;
                        }
                    } else if (this.K && (eVar4 = this.J) != null) {
                        eVar4.d(dVar);
                    }
                } else if (this.K && (eVar3 = this.J) != null) {
                    this.K = eVar3.e(dVar);
                    invalidate();
                    return;
                }
            } else if (this.K && (eVar2 = this.J) != null) {
                eVar2.a(dVar);
                invalidate();
                return;
            }
        } else if (this.K && (eVar = this.J) != null) {
            Path pathB = eVar.b(dVar);
            if (pathB == null) {
                this.K = false;
            } else if (this.B) {
                if (this.D == null) {
                    this.D = new ArrayList<>();
                }
                int length = this.I ? this.H + this.z.length : this.H;
                int size = this.D.size();
                if (size > this.E) {
                    for (int i2 = size - 1; i2 >= this.E; i2--) {
                        this.D.remove(i2);
                    }
                }
                this.D.add(new i(pathB, this.A[length]));
                this.E++;
            } else {
                if (this.C == null) {
                    this.C = new Path();
                }
                this.C.addPath(pathB);
            }
            invalidate();
            return;
        }
        if (this.K) {
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float f(float f2, float f3, float f4, float f5) {
        return (float) Math.atan2(f5 - f3, f4 - f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float i(float f2, float f3, float f4, float f5) {
        float f6 = f4 - f2;
        float f7 = f5 - f3;
        return (f6 * f6) + (f7 * f7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PointF j(float f2, float f3, float f4, float f5, PointF pointF) {
        double d2 = f2;
        double d3 = f4;
        double dCos = Math.cos(d3);
        double d4 = f5;
        Double.isNaN(d4);
        Double.isNaN(d2);
        pointF.x = (float) (d2 + (dCos * d4));
        double d5 = f3;
        double dSin = Math.sin(d3);
        Double.isNaN(d4);
        Double.isNaN(d5);
        pointF.y = (float) (d5 + (dSin * d4));
        return pointF;
    }

    public void e(q qVar) {
        g gVar = this.G;
        gVar.f595a = qVar;
        d(gVar);
    }

    public boolean g() {
        ArrayList<i> arrayList;
        return this.B && (arrayList = this.D) != null && this.E < arrayList.size();
    }

    public boolean h() {
        return this.B && this.D != null && this.E > 0;
    }

    public void k() {
        ArrayList<i> arrayList;
        if (!this.B || (arrayList = this.D) == null) {
            return;
        }
        int i2 = this.E + 1;
        int size = arrayList.size();
        if (i2 > size) {
            i2 = size;
        }
        if (i2 != this.E) {
            this.E = i2;
            invalidate();
        }
    }

    public void l(int i2, int i3, boolean z) {
        e cVar;
        if (i3 >= this.z.length) {
            f.v.d(new Throwable("Index out of range: " + i3 + ", size: " + this.z.length));
            return;
        }
        Context context = getContext();
        this.H = i3;
        this.I = z;
        int length = z ? this.z.length + i3 : i3;
        Paint paint = this.A[length];
        if (paint == null) {
            paint = new Paint(1);
            paint.setStrokeWidth(a0.m(context) * 0.015f);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStyle(z ? Paint.Style.FILL_AND_STROKE : Paint.Style.STROKE);
            int[] iArr = this.z;
            if (iArr[i3] != 0) {
                paint.setColor(iArr[i3]);
            } else {
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            }
            this.A[length] = paint;
        }
        if (i2 == 0) {
            paint.setStyle(Paint.Style.STROKE);
            cVar = new c(paint, false);
        } else if (i2 == 1) {
            paint.setStyle(Paint.Style.STROKE);
            cVar = new c(paint, true);
        } else if (i2 == 2) {
            paint.setStyle(Paint.Style.STROKE);
            cVar = new k(paint);
        } else if (i2 == 3) {
            cVar = new f(paint);
        } else if (i2 == 4) {
            cVar = new j(context, paint);
        } else {
            if (i2 != 5) {
                f.v.c("Unknown style: " + i2);
                return;
            }
            cVar = new b(context, paint);
        }
        this.J = cVar;
    }

    public void m() {
        if (!this.B || this.D == null) {
            return;
        }
        int i2 = this.E;
        int i3 = i2 - 1;
        if (i3 < 0) {
            i3 = 0;
        }
        if (i3 != i2) {
            this.E = i3;
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        e eVar;
        if (!this.B) {
            Path path = this.C;
            if (path != null) {
                canvas.drawPath(path, this.A[this.I ? this.H + this.z.length : this.H]);
            }
        } else if (this.D != null) {
            for (int i2 = 0; i2 < this.E; i2++) {
                i iVar = this.D.get(i2);
                canvas.drawPath(iVar.f597a, iVar.f598b);
            }
        }
        if (!this.K || (eVar = this.J) == null) {
            return;
        }
        eVar.c(canvas);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        h hVar = this.F;
        hVar.f596a = motionEvent;
        d(hVar);
        return true;
    }
}
