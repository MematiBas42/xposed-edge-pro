package pkg_d;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.jozein.xedgepro.R;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class f2 extends pkg_e.j {
    private static Field P;
    private AppWidgetManager M;
    private AppWidgetHost N;
    private pkg_f.u<String, ArrayList<AppWidgetProviderInfo>> O;

    private void B1(AppWidgetProviderInfo appWidgetProviderInfo) {
        int iE;
        if (appWidgetProviderInfo.configure == null && (iE = pkg_a.x.h().e(appWidgetProviderInfo.provider.flattenToShortString())) != 0) {
            F1(iE, appWidgetProviderInfo);
            return;
        }
        int iAllocateAppWidgetId = this.N.allocateAppWidgetId();
        try {
            if (this.M.bindAppWidgetIdIfAllowed(iAllocateAppWidgetId, appWidgetProviderInfo.provider)) {
                C1(iAllocateAppWidgetId, appWidgetProviderInfo);
                return;
            }
            Intent intent = new Intent("android.appwidget.action.APPWIDGET_BIND");
            intent.putExtra("appWidgetId", iAllocateAppWidgetId);
            intent.putExtra("appWidgetProvider", appWidgetProviderInfo.provider);
            startActivityForResult(intent, 1);
        } catch (Throwable th) {
            g0(th);
            this.N.deleteAppWidgetId(iAllocateAppWidgetId);
        }
    }

    private void C1(int i, AppWidgetProviderInfo appWidgetProviderInfo) {
        if (appWidgetProviderInfo == null) {
            return;
        }
        ComponentName componentName = appWidgetProviderInfo.configure;
        if (componentName != null) {
            D1(i, componentName);
        } else {
            F1(i, appWidgetProviderInfo);
        }
    }

    private void D1(int i, ComponentName componentName) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_CONFIGURE");
        intent.setComponent(componentName);
        intent.putExtra("appWidgetId", i);
        startActivityForResult(intent, 2);
    }

    private ApplicationInfo E1(int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31) {
            return this.O.k(i).get(0).getActivityInfo().applicationInfo;
        }
        if (i2 >= 21) {
            try {
                if (P == null) {
                    Field declaredField = AppWidgetProviderInfo.class.getDeclaredField("providerInfo");
                    P = declaredField;
                    declaredField.setAccessible(true);
                }
                return ((ActivityInfo) P.get(this.O.k(i).get(0))).applicationInfo;
            } catch (Throwable th) {
                pkg_f.v.c(th.toString());
            }
        }
        try {
            return pkg_f.z.K(f()).E(this.O.e(i));
        } catch (Throwable th2) {
            pkg_f.v.c(th2.toString());
            return null;
        }
    }

    private void F1(int i, AppWidgetProviderInfo appWidgetProviderInfo) {
        if (appWidgetProviderInfo != null) {
            V("result", i);
        }
        L();
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        c0(R.string.widgets);
    }

    @Override // pkg_e.j
    protected int B0() {
        Context contextF = f();
        this.M = AppWidgetManager.getInstance(contextF);
        this.N = new AppWidgetHost(contextF, 1);
        if (this.O == null) {
            this.O = pkg_f.z.K(contextF).B();
        }
        return this.O.size();
    }

    @Override // pkg_e.j
    protected boolean N0(int i) {
        return true;
    }

    @Override // pkg_e.j
    protected void g1(Object obj) {
        this.O = (pkg_f.u) obj;
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        Drawable drawableJ;
        CharSequence strE = null;
        PackageManager packageManager = f().getPackageManager();
        String strE2 = this.O.e(i);
        pkg_f.z zVarK = pkg_f.z.K(f());
        try {
            drawableJ = zVarK.C(strE2);
            try {
                strE = zVarK.G(strE2);
            } catch (Throwable th) {
                pkg_f.v.d(th);
                strE = null;
            }
        } catch (Throwable th2) {
            pkg_f.v.d(th2);
            drawableJ = null;
        }
        if (strE == null) {
            ApplicationInfo applicationInfoE1 = E1(i);
            if (applicationInfoE1 != null) {
                drawableJ = applicationInfoE1.loadIcon(packageManager);
                strE = applicationInfoE1.loadLabel(packageManager);
            } else {
                drawableJ = j(R.drawable.ic_floating_widget);
                strE = this.O.e(i);
            }
        }
        return new pkg_e.j.g(drawableJ, strE, (CharSequence) null);
    }

    @Override // pkg_e.j
    protected View i1(int i) {
        return f1(this.O.k(I0()).get(i));
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        y1(i, this.O.k(i).size());
    }

    @Override // pkg_e.j
    protected pkg_e.j.i m1() {
        Object objS = s();
        if (objS instanceof pkg_f.u) {
            this.O = (pkg_f.u) objS;
        }
        if (this.O != null) {
            return null;
        }
        return new pkg_e.j.i.c();
    }

    @Override // pkg_e.j
    protected void o1(int i) {
        B1(this.O.k(I0()).get(i));
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (intent != null) {
            try {
                int intExtra = intent.getIntExtra("appWidgetId", 0);
                if (intExtra == 0) {
                    return;
                }
                if (i != 1) {
                    if (i == 2 && i2 == -1) {
                        F1(intExtra, this.M.getAppWidgetInfo(intExtra));
                    }
                } else if (i2 == -1) {
                    C1(intExtra, this.M.getAppWidgetInfo(intExtra));
                } else {
                    this.N.deleteAppWidgetId(intExtra);
                }
            } catch (Throwable th) {
                g0(th);
            }
        }
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Z(this.O);
    }
}
