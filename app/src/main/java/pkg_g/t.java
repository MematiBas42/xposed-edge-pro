package pkg_g;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class t extends i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f613d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final float f614e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Drawable f615f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Drawable f616g;

    public t(Drawable drawable, Drawable drawable2, float f2) {
        this(drawable, drawable2, f2, false);
    }

    public t(Drawable drawable, Drawable drawable2, float f2, boolean z) {
        this.f613d = z;
        this.f615f = drawable;
        this.f616g = drawable2;
        this.f614e = f2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        a(canvas, this.f615f);
        a(canvas, this.f616g);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f616g;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        Drawable drawable2 = this.f615f;
        return drawable2 != null ? (int) (drawable2.getIntrinsicHeight() / this.f614e) : super.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f616g;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        Drawable drawable2 = this.f615f;
        return drawable2 != null ? (int) (drawable2.getIntrinsicWidth() / this.f614e) : super.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Drawable drawable = this.f615f;
        if (drawable != null) {
            if (this.f613d) {
                int i = rect.left;
                int i2 = rect.top;
                int i3 = rect.right;
                int i4 = rect.bottom;
                float f2 = this.f614e;
                drawable.setBounds(i, i2, ((int) ((i3 - i) * f2)) + i, ((int) ((i4 - i2) * f2)) + i2);
            } else {
                int iCenterX = rect.centerX();
                int iCenterY = rect.centerY();
                int iWidth = (int) ((rect.width() * this.f614e) / 2.0f);
                int iHeight = (int) ((rect.height() * this.f614e) / 2.0f);
                this.f615f.setBounds(iCenterX - iWidth, iCenterY - iHeight, iCenterX + iWidth, iCenterY + iHeight);
            }
        }
        Drawable drawable2 = this.f616g;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }
}
