package com.jozein.xedgepro.xposed;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
class t2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final pkg_a.p f228a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f229b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f230c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f231d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f232e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private AbsListView.LayoutParams f233f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private AbsListView.LayoutParams f234g;
    private FrameLayout.LayoutParams h;
    private FrameLayout.LayoutParams i;
    private FrameLayout.LayoutParams j;
    private int k = -1;
    private int l = -1;

    class a extends FrameLayout {
        final /* synthetic */ c z;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, c cVar) {
            super(context);
            this.z = cVar;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getActionMasked() == 1) {
                this.z.b();
            }
            return super.dispatchTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public boolean onGenericMotionEvent(MotionEvent motionEvent) {
            View childAt = getChildAt(0);
            return childAt != null ? childAt.onGenericMotionEvent(motionEvent) : super.onGenericMotionEvent(motionEvent);
        }
    }

    class b extends GridView {
        final /* synthetic */ o2 z;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Context context, o2 o2Var) {
            super(context);
            this.z = o2Var;
        }

        @Override // android.view.View
        public boolean hasWindowFocus() {
            return true;
        }

        @Override // android.widget.AbsListView, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (this.z.c()) {
                return true;
            }
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Throwable th) {
                this.z.b(th);
                t2.this.k = -1;
                return true;
            }
        }
    }

    private class c extends BaseAdapter implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
        private final Drawable[] A;
        private final CharSequence[] B;
        private boolean C = false;
        private final e z;

        c(e eVar, boolean z) {
            int count = 0;
            this.z = eVar;
            try {
                count = eVar.getCount();
            } catch (Throwable th) {
                pkg_f.v.d(th);
            }
            this.A = new Drawable[count];
            this.B = z ? new CharSequence[count] : null;
        }

        void b() {
            if (this.C) {
                this.C = false;
                try {
                    t2.this.j();
                    this.z.f();
                } catch (Throwable th) {
                    pkg_f.v.d(th);
                }
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.A.length;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            d dVar;
            boolean z = this.B != null;
            Drawable[] drawableArr = this.A;
            if (drawableArr[i] == null) {
                try {
                    drawableArr[i] = this.z.c(i);
                    if (z) {
                        this.B[i] = this.z.d(i);
                    }
                } catch (Throwable th) {
                    pkg_f.v.d(th);
                }
            }
            if (view == null) {
                dVar = t2.this.new d(viewGroup.getContext(), this.B != null);
                t2 t2Var = t2.this;
                dVar.setLayoutParams(z ? t2Var.f234g : t2Var.f233f);
            } else {
                dVar = (d) view;
            }
            dVar.z.setImageDrawable(this.A[i]);
            if (z) {
                dVar.A.setText(this.B[i]);
            }
            return dVar;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                this.z.e(i, view);
            } catch (Throwable th) {
                pkg_f.v.d(th);
            }
            t2.this.j();
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                if (this.z.b(i)) {
                    this.C = true;
                    return true;
                }
            } catch (Throwable th) {
                pkg_f.v.d(th);
            }
            t2.this.j();
            return false;
        }
    }

    private class d extends FrameLayout {
        final TextView A;
        final ImageView z;

        d(Context context, boolean z) {
            super(context);
            if (!z) {
                ImageView imageView = new ImageView(context);
                this.z = imageView;
                addView(imageView, t2.this.h);
                this.A = null;
                return;
            }
            ImageView imageView2 = new ImageView(context);
            this.z = imageView2;
            addView(imageView2, t2.this.i);
            TextView textView = new TextView(context);
            this.A = textView;
            textView.setTextSize(0, t2.this.f232e);
            textView.setTextColor(t2.this.l);
            textView.setPadding(t2.this.f232e / 4, 0, t2.this.f232e / 4, 0);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            addView(textView, t2.this.j);
        }
    }

    interface e {
        boolean a();

        boolean b(int i);

        Drawable c(int i);

        CharSequence d(int i);

        void e(int i, View view);

        void f();

        int getCount();
    }

    t2(w1 w1Var) {
        this.f228a = w1Var.b2();
        this.f229b = w1Var.T1();
    }

    private void k() {
        pkg_g.x xVarB = pkg_g.x.b(this.f229b);
        float fV = this.f228a.v(28, 100) / 100.0f;
        int i = (int) ((xVarB.f633a * fV) + 0.5f);
        this.f230c = i;
        this.f232e = (int) ((xVarB.f636d * fV) + 0.5f);
        this.f231d = i;
        int i2 = (int) ((xVarB.f634b * fV) + 0.5f);
        this.f233f = new AbsListView.LayoutParams(-1, this.f230c);
        this.h = new FrameLayout.LayoutParams(i2, i2, 17);
        int i3 = (int) (((this.f230c - i2) - this.f232e) * 0.3f);
        this.f234g = new AbsListView.LayoutParams(-1, this.f231d);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i2, i2, 49);
        this.i = layoutParams;
        layoutParams.topMargin = i3;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2, 81);
        this.j = layoutParams2;
        layoutParams2.bottomMargin = i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(GridView gridView) {
        int width = gridView.getWidth() / this.f230c;
        if (width > 0) {
            gridView.setNumColumns(width);
        }
    }

    void j() {
        t3.n().t(18);
        this.k = -1;
    }

    boolean l(int i) {
        return i == this.k && t3.n().p(18);
    }

    /* JADX WARN: Code duplicated, block: B:81:0x01bd  */
    void n(e eVar, int i, int i2, int i3) {
        float f2;
        FrameLayout.LayoutParams layoutParams;
        long j;
        AnimationSet animationSet;
        FrameLayout.LayoutParams layoutParams2 = null;
        TranslateAnimation translateAnimation = null;
        t3 t3VarN = t3.n();
        t3VarN.t(18);
        k();
        int iV = this.f228a.v(i == 2 ? 33 : 2, -16764873);
        this.l = pkg_g.a0.r(iV) ? -1 : -16777216;
        boolean zA = eVar.a();
        c cVar = new c(eVar, zA);
        a aVar = new a(this.f229b, cVar);
        o2 o2Var = new o2(this.f229b, 18);
        final b bVar = new b(this.f229b, o2Var);
        o2Var.a(bVar);
        bVar.setSelector(pkg_g.a0.l());
        Point pointN = pkg_g.a0.n(this.f229b, new Point());
        int i4 = i2 > 0 ? i2 : pointN.x / this.f230c;
        bVar.setNumColumns(i4);
        this.k = i3;
        bVar.setAdapter((ListAdapter) cVar);
        bVar.setOnItemClickListener(cVar);
        bVar.setOnItemLongClickListener(cVar);
        if (i == 1) {
            f2 = this.f232e;
        } else {
            f2 = (i == 3 || i == 4) ? this.f232e * 0.5f : 0.0f;
        }
        pkg_g.a0.w(aVar, iV, f2);
        aVar.addView(bVar, new FrameLayout.LayoutParams(-1, -1));
        TranslateAnimation translateAnimation2 = null;
        long j2 = 200;
        if (i == 1) {
            layoutParams = new FrameLayout.LayoutParams(this.f230c * i4, -2, 81);
            int i5 = pointN.x;
            int i6 = pointN.y;
            if (i5 >= i6) {
                i5 = i6;
            }
            int length = ((cVar.A.length + i4) - 1) / i4;
            if (length == 0) {
                length = 1;
            }
            layoutParams.bottomMargin = (i5 - ((zA ? this.f231d : this.f230c) * length)) / 2;
        } else {
            if (i != 2) {
                if (i == 3) {
                    int length2 = (zA ? this.f231d : this.f230c) * cVar.A.length;
                    int iP = pkg_g.a0.p();
                    if (length2 == 0) {
                        length2 = zA ? this.f231d : this.f230c;
                    } else {
                        int i7 = pointN.y;
                        if (length2 > i7 - iP) {
                            length2 = i7 - iP;
                        }
                    }
                    layoutParams2 = new FrameLayout.LayoutParams(this.f230c, length2, 83);
                    layoutParams2.leftMargin = iP;
                    translateAnimation = new TranslateAnimation(((-iP) - this.f230c) / 2.0f, 0.0f, 0.0f, 0.0f);
                } else if (i != 4) {
                    layoutParams2 = new FrameLayout.LayoutParams(-2, -2, 17);
                } else {
                    int length3 = (zA ? this.f231d : this.f230c) * cVar.A.length;
                    int iP2 = pkg_g.a0.p();
                    if (length3 == 0) {
                        length3 = zA ? this.f231d : this.f230c;
                    } else {
                        int i8 = pointN.y;
                        if (length3 > i8 - iP2) {
                            length3 = i8 - iP2;
                        }
                    }
                    layoutParams2 = new FrameLayout.LayoutParams(this.f230c, length3, 85);
                    layoutParams2.rightMargin = iP2;
                    translateAnimation = new TranslateAnimation((iP2 + this.f230c) / 2.0f, 0.0f, 0.0f, 0.0f);
                }
                translateAnimation2 = translateAnimation;
                layoutParams = layoutParams2;
                j = 82;
                t3VarN.k(aVar, 18, layoutParams);
                animationSet = new AnimationSet(true);
                animationSet.addAnimation(new AlphaAnimation(0.0f, 1.0f));
                if (translateAnimation2 != null) {
                    animationSet.addAnimation(translateAnimation2);
                }
                pkg_g.a0.z(aVar, animationSet, j);
            }
            int length4 = ((cVar.A.length + i4) - 1) / i4;
            int i9 = pointN.x >= pointN.y ? 2 : 3;
            if (length4 > i9) {
                length4 = i9;
            } else if (length4 == 0) {
                length4 = 1;
            }
            int i10 = ((zA ? this.f231d : this.f230c) * length4) + 2;
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, i10, 80);
            TranslateAnimation translateAnimation3 = new TranslateAnimation(0.0f, 0.0f, i10 / 2.0f, 0.0f);
            j2 = ((long) length4) * 66;
            bVar.post(new Runnable() { // from class: com.jozein.xedgepro.xposed.s2
                @Override // java.lang.Runnable
                public final void run() {
                    t2.this.m(bVar);
                }
            });
            layoutParams = layoutParams3;
            translateAnimation2 = translateAnimation3;
        }
        j = j2;
        t3VarN.k(aVar, 18, layoutParams);
        animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(0.0f, 1.0f));
        if (translateAnimation2 != null) {
            animationSet.addAnimation(translateAnimation2);
        }
        pkg_g.a0.z(aVar, animationSet, j);
    }

    void o(e eVar, int i) {
        n(eVar, 2, 0, i);
    }
}
