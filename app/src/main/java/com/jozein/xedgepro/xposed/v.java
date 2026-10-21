package com.jozein.xedgepro.xposed;

import android.hardware.display.DisplayManager;
import android.os.Build;
import android.view.Display;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class v {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final boolean f242f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int[] f243g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final w1 f244a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<Integer> f245b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DisplayManager f246c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private DisplayManager.DisplayListener f247d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int[] f248e = f243g;

    class a implements DisplayManager.DisplayListener {
        a() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
            v.this.f();
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
            v.this.f();
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
            v.this.f();
        }
    }

    static {
        f242f = Build.VERSION.SDK_INT >= 29;
        f243g = new int[]{0};
    }

    v(w1 w1Var) {
        this.f244a = w1Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        int[] iArr;
        try {
            Display[] displays = c().getDisplays();
            List<Integer> list = this.f245b;
            for (Display display : displays) {
                if (display.getDisplayId() == 0 || display.isValid()) {
                    list.add(Integer.valueOf(display.getDisplayId()));
                }
            }
            int size = list.size();
            if (size == 0 || (size == 1 && list.get(0).intValue() == 0)) {
                iArr = f243g;
            } else {
                iArr = new int[size];
                for (int i = 0; i < size; i++) {
                    iArr[i] = list.get(i).intValue();
                }
            }
            list.clear();
            this.f248e = iArr;
        } catch (Throwable th) {
            f.v.d(th);
        }
    }

    int b() {
        return this.f248e.length;
    }

    DisplayManager c() {
        if (this.f246c == null) {
            this.f246c = (DisplayManager) this.f244a.T1().getSystemService("display");
        }
        return this.f246c;
    }

    boolean d() {
        return b() > 1;
    }

    void e() {
        if (f242f && this.f247d == null) {
            a aVar = new a();
            try {
                c().registerDisplayListener(aVar, this.f244a.U1());
                this.f247d = aVar;
            } catch (Throwable th) {
                f.v.d(th);
            }
        }
    }
}
