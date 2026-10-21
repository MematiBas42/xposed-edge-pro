package pkg_g;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class s extends Drawable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final RectF f609d = new RectF();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Paint f610a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f611b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f612c;

    public s(int i, float f2) {
        Paint paint = new Paint(1);
        this.f610a = paint;
        this.f612c = i;
        this.f611b = f2;
        paint.setColor(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        RectF rectF = f609d;
        rectF.set(0.0f, 0.0f, bounds.width(), bounds.height());
        float f2 = this.f611b;
        canvas.drawRoundRect(rectF, f2, f2, this.f610a);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f610a.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(getBounds(), this.f611b);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f610a.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f610a.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        this.f610a.setColor(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (colorStateList == null) {
            this.f610a.setColor(this.f612c);
        } else {
            this.f610a.setColor(colorStateList.getDefaultColor());
        }
    }
}
