package g;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Resources f565a;

    public static class a extends o {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f566b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f567c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int[] f568d;

        public a(Resources resources) {
            super(resources);
            int iA = a.m.b().a(4);
            if (iA == 0) {
                this.f566b = -1;
                this.f567c = 0;
                this.f568d = resources.getIntArray(R.array.colors);
            } else {
                if (iA != 1) {
                    this.f566b = resources.getColor(R.color.colorPrimary);
                    this.f567c = 0;
                } else {
                    this.f566b = -1;
                    this.f567c = resources.getColor(R.color.colorPrimary);
                }
                this.f568d = null;
            }
        }

        @Override // g.o
        public boolean c() {
            return (this.f568d == null && this.f567c == 0) ? false : true;
        }

        @Override // g.o
        Drawable f(Drawable drawable, boolean z) {
            int i;
            int[] iArr = this.f568d;
            if (iArr == null) {
                i = this.f567c;
            } else if (z) {
                int i2 = this.f567c;
                int i3 = iArr[i2];
                int i4 = i2 + 1;
                this.f567c = i4;
                if (i4 >= iArr.length) {
                    this.f567c = 0;
                }
                i = i3;
            } else {
                i = -7829368;
            }
            return new z(drawable, this.f566b, i);
        }
    }

    public static class b extends o {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f569b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f570c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f571d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f572e;

        public b(a.p pVar, Resources resources) {
            super(resources);
            this.f569b = pVar.v(31, -1);
            boolean zO = pVar.o(42);
            this.f570c = -7829368;
            if (zO) {
                this.f571d = pVar.v(32, -15303552);
                this.f572e = a0.r(pVar.v(2, -16764873)) ? -12303292 : -3355444;
            } else {
                this.f572e = 0;
                this.f571d = 0;
            }
        }

        @Override // g.o
        public boolean c() {
            return this.f571d != 0;
        }

        @Override // g.o
        Drawable f(Drawable drawable, boolean z) {
            return z ? new z(drawable, this.f569b, this.f571d) : new z(drawable, this.f570c, this.f572e);
        }
    }

    o(Resources resources) {
        this.f565a = resources;
    }

    public final Drawable a(int i) {
        return f(this.f565a.getDrawable(i), true);
    }

    public final Drawable b(int i, boolean z) {
        return f(this.f565a.getDrawable(i), z);
    }

    public abstract boolean c();

    public final Drawable d(Drawable drawable) {
        return drawable instanceof g ? drawable : f(drawable, true);
    }

    public final Drawable e(Drawable drawable, boolean z) {
        return drawable instanceof g ? drawable : f(drawable, z);
    }

    abstract Drawable f(Drawable drawable, boolean z);
}
