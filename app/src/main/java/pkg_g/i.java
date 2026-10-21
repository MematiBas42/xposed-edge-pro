package pkg_g;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f555a = 255;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ColorFilter f556b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ColorStateList f557c;

    protected void a(Canvas canvas, Drawable drawable) {
        if (canvas == null || drawable == null) {
            return;
        }
        drawable.setAlpha(this.f555a);
        drawable.setColorFilter(this.f556b);
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            drawable.setTintList(this.f557c);
        }
        drawable.draw(canvas);
        drawable.setAlpha(255);
        drawable.setColorFilter(null);
        if (i >= 21) {
            drawable.setTintList(null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f555a;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        return this.f556b;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.f555a = i;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f556b = colorFilter;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        this.f557c = colorStateList;
    }
}
