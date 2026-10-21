package pkg_g;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    private static class b extends h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f546a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ColorFilter f547b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ColorFilter f548c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final ColorFilter f549d;

        private b() {
            this.f548c = new PorterDuffColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
            this.f549d = new PorterDuffColorFilter(-16777216, PorterDuff.Mode.SRC_ATOP);
        }

        @Override // pkg_g.h
        void c(Drawable drawable) {
            drawable.setColorFilter(null);
        }

        @Override // pkg_g.h
        void f(Drawable drawable, int i) {
            ColorFilter colorFilter;
            if (i == -16777216) {
                colorFilter = this.f549d;
            } else if (i == -7829368) {
                colorFilter = this.f548c;
            } else {
                ColorFilter porterDuffColorFilter = this.f547b;
                if (porterDuffColorFilter == null || i != this.f546a) {
                    porterDuffColorFilter = new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_ATOP);
                }
                colorFilter = porterDuffColorFilter;
            }
            drawable.setColorFilter(colorFilter);
        }

        @Override // pkg_g.h
        public void h(Drawable drawable, Resources resources) {
            if (this.f547b == null) {
                this.f546a = resources.getColor(R.color.colorPrimary);
                this.f547b = new PorterDuffColorFilter(this.f546a, PorterDuff.Mode.SRC_ATOP);
            }
            if (drawable != null) {
                drawable.setColorFilter(this.f547b);
            }
        }
    }

    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final h f550a;

        static {
            f550a = Build.VERSION.SDK_INT >= 21 ? new d() : new b();
        }
    }

    private static class d extends h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f551a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ColorStateList f552b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ColorStateList f553c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final ColorStateList f554d;

        private d() {
            this.f553c = ColorStateList.valueOf(-7829368);
            this.f554d = ColorStateList.valueOf(-16777216);
        }

        @Override // pkg_g.h
        void c(Drawable drawable) {
            drawable.setTintList(null);
        }

        @Override // pkg_g.h
        void f(Drawable drawable, int i) {
            ColorStateList colorStateListValueOf;
            if (i == -16777216) {
                colorStateListValueOf = this.f554d;
            } else if (i == -7829368) {
                colorStateListValueOf = this.f553c;
            } else {
                ColorStateList colorStateList = this.f552b;
                colorStateListValueOf = (colorStateList == null || i != this.f551a) ? ColorStateList.valueOf(i) : colorStateList;
            }
            drawable.setTintList(colorStateListValueOf);
        }

        @Override // pkg_g.h
        public void h(Drawable drawable, Resources resources) {
            if (this.f552b == null) {
                int color = resources.getColor(R.color.colorPrimary);
                this.f551a = color;
                this.f552b = ColorStateList.valueOf(color);
            }
            if (drawable != null) {
                drawable.setTintList(this.f552b);
            }
        }
    }

    public static h a() {
        return c.f550a;
    }

    public final void b(Drawable drawable) {
        if (drawable != null) {
            c(drawable);
        }
    }

    abstract void c(Drawable drawable);

    public final void d(Drawable drawable) {
        if (drawable != null) {
            e(drawable, -16777216);
        }
    }

    public final void e(Drawable drawable, int i) {
        if (drawable != null) {
            if (i == 0) {
                b(drawable);
            } else {
                f(drawable, i);
            }
        }
    }

    abstract void f(Drawable drawable, int i);

    public final void g(Drawable drawable) {
        if (drawable != null) {
            e(drawable, -7829368);
        }
    }

    public abstract void h(Drawable drawable, Resources resources);
}
