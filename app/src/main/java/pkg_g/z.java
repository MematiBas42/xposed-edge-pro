package pkg_g;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class z extends Drawable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Rect f640e = new Rect();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final Rect f641f = new Rect();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final Paint f642g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Drawable f643a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f644b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f645c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Bitmap f646d;

    static {
        Paint paint = new Paint(1);
        f642g = paint;
        paint.setStyle(Paint.Style.FILL);
    }

    public z(Drawable drawable, int i, int i2) {
        this.f643a = drawable;
        this.f644b = i;
        this.f645c = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int iWidth = bounds.width();
        int iMin = Math.min(iWidth, bounds.height());
        int iCenterX = bounds.centerX();
        int iCenterY = bounds.centerY();
        int i = this.f645c;
        boolean z = i != 0;
        if (z) {
            Paint paint = f642g;
            paint.setColor(i);
            canvas.drawCircle(iCenterX, iCenterY, iMin / 2, paint);
        }
        if (this.f643a == null) {
            return;
        }
        int i2 = (int) ((iMin * (z ? 0.6f : 0.8f)) / 2.0f);
        Rect rect = f641f;
        rect.set(iCenterX - i2, iCenterY - i2, iCenterX + i2, iCenterY + i2);
        int i3 = this.f644b;
        if (i3 == 0 || i3 == -1) {
            this.f643a.setBounds(rect);
            this.f643a.draw(canvas);
            return;
        }
        Bitmap bitmap = this.f646d;
        if (bitmap == null || bitmap.getWidth() * 2 < iWidth) {
            h.a().e(this.f643a, this.f644b);
            this.f646d = a0.i(this.f643a, rect.width(), rect.height());
            h.a().b(this.f643a);
        }
        Rect rect2 = f640e;
        rect2.set(0, 0, this.f646d.getWidth(), this.f646d.getHeight());
        canvas.drawBitmap(this.f646d, rect2, rect, f642g);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (this.f645c == 0) {
            super.getOutline(outline);
        } else {
            Rect bounds = getBounds();
            outline.setRoundRect(bounds, Math.min(bounds.width(), bounds.height()) / 2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        pkg_f.v.d(new UnsupportedOperationException("set color filter!"));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        pkg_f.v.d(new UnsupportedOperationException("set tint list!"));
    }
}
