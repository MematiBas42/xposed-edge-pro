package pkg_g;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class g extends Drawable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f535d = h(-15043609);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f536e = h(-28378);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final Rect f537f = new Rect();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final RectF f538g = new RectF();
    private static final Paint h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Drawable f539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f540b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f541c;

    public static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Drawable.ConstantState f542a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Bitmap f543b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Bitmap f544c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f545d;

        private b(int i) {
            if (i != 0 && i != 1 && i != 2) {
                pkg_f.v.d(new IllegalArgumentException("badge=" + i));
                i = 0;
            }
            this.f545d = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.graphics.drawable.Drawable.ConstantState
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public g newDrawable() {
            return new g(null, this);
        }

        public g b(int i) {
            return i == this.f545d ? newDrawable() : new g(this.f542a.newDrawable(), i);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }
    }

    static {
        Paint paint = new Paint(1);
        h = paint;
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public g(Drawable drawable) {
        this(drawable, new b(0));
    }

    public g(Drawable drawable, int i) {
        this(drawable, new b(i));
    }

    private g(Drawable drawable, b bVar) {
        this.f541c = 255;
        this.f539a = drawable;
        this.f540b = bVar;
    }

    private void a(Canvas canvas, int i) {
        Rect bounds = getBounds();
        float fWidth = (bounds.width() * 0.375f) / 2.0f;
        float f2 = bounds.left + fWidth;
        float f3 = bounds.bottom - fWidth;
        float f4 = 0.35f * fWidth;
        float f5 = 0.5f * f4;
        Paint paintG = g();
        paintG.setColor(i);
        paintG.setStyle(Paint.Style.FILL);
        canvas.drawCircle(f2, f3, fWidth, paintG);
        paintG.setColor(-1);
        paintG.setStyle(Paint.Style.STROKE);
        paintG.setStrokeWidth(f5);
        RectF rectF = f538g;
        rectF.set((f2 - f4) + f5, (f3 - f4) - f5, f2 + f4 + f5, (f3 + f4) - f5);
        canvas.drawArc(rectF, 30.0f, -150.0f, false, paintG);
        rectF.offset((-f5) * 2.0f, f5 * 2.0f);
        paintG.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), f4, paintG);
    }

    private void b(Canvas canvas) {
        boolean zI = i();
        int alpha = getAlpha();
        Rect bounds = getBounds();
        b bVar = this.f540b;
        Bitmap bitmap = zI ? bVar.f543b : bVar.f544c;
        if (bitmap != null && bitmap.getWidth() * 2 < bounds.width()) {
            bitmap = null;
        }
        if (bitmap == null && k()) {
            Drawable drawableF = f();
            if (drawableF == null) {
                return;
            }
            if (zI) {
                drawableF.setColorFilter(a0.A);
            }
            Bitmap bitmapI = a0.i(drawableF, bounds.width(), bounds.height());
            if (zI) {
                this.f540b.f543b = bitmapI;
                drawableF.clearColorFilter();
            } else {
                this.f540b.f544c = bitmapI;
            }
            bitmap = bitmapI;
        }
        if (bitmap != null) {
            Rect rect = f537f;
            rect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            canvas.drawBitmap(bitmap, rect, bounds, g());
            return;
        }
        Drawable drawableF2 = f();
        if (drawableF2 == null) {
            return;
        }
        drawableF2.setBounds(bounds);
        if (zI) {
            drawableF2.setColorFilter(a0.A);
        }
        drawableF2.setAlpha(alpha);
        drawableF2.draw(canvas);
        if (zI) {
            drawableF2.clearColorFilter();
        }
        drawableF2.setAlpha(255);
    }

    private Paint g() {
        Paint paint = h;
        paint.setAlpha(getAlpha());
        return paint;
    }

    private static int h(int i) {
        int iAlpha = Color.alpha(i);
        int iRed = Color.red(i);
        int iGreen = Color.green(i);
        int iBlue = Color.blue(i);
        int i2 = iRed + iGreen + iBlue;
        return Color.argb(iAlpha, ((i2 + iRed) + iRed) / 5, ((i2 + iGreen) + iGreen) / 5, ((i2 + iBlue) + iBlue) / 5);
    }

    private boolean k() {
        return i() || (this.f541c & 512) != 0;
    }

    public void c() {
        this.f541c |= 512;
    }

    public int d() {
        return this.f540b.f545d;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i;
        b(canvas);
        int iD = d();
        if (iD == 1 || iD == 2) {
            boolean zI = i();
            if (iD == 1) {
                i = zI ? f535d : -15043609;
            } else {
                i = zI ? f536e : -28378;
            }
            a(canvas, (i & 16777215) | (getAlpha() << 24));
        }
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public b getConstantState() {
        Drawable drawable;
        b bVar = this.f540b;
        if (bVar.f542a == null && (drawable = this.f539a) != null) {
            bVar.f542a = drawable.getConstantState();
        }
        b bVar2 = this.f540b;
        if (bVar2.f542a != null) {
            return bVar2;
        }
        return null;
    }

    public Drawable f() {
        Drawable.ConstantState constantState;
        Drawable drawable = this.f539a;
        if (drawable != null || (constantState = this.f540b.f542a) == null) {
            return drawable;
        }
        Drawable drawableNewDrawable = constantState.newDrawable();
        this.f539a = drawableNewDrawable;
        return drawableNewDrawable;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f541c & 255;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawableF = f();
        if (drawableF != null) {
            return drawableF.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawableF = f();
        if (drawableF != null) {
            return drawableF.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawableF = f();
        if (drawableF == null) {
            super.getOutline(outline);
        } else {
            drawableF.setBounds(getBounds());
            drawableF.getOutline(outline);
        }
    }

    boolean i() {
        return (this.f541c & 256) != 0;
    }

    void j(boolean z) {
        int i = this.f541c;
        int i2 = i & (-257);
        if (z) {
            i2 |= 256;
        }
        this.f541c = i2;
        if (i != i2) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawableMutate;
        Drawable drawableF = f();
        if (drawableF == null || (drawableMutate = drawableF.mutate()) == drawableF) {
            return this;
        }
        g gVar = new g(drawableMutate, this.f540b);
        gVar.f541c = this.f541c;
        return gVar;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f541c = (i & 255) | (this.f541c & (-256));
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
    }
}
