package pkg_f;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import com.jozein.xedgepro.ui.ActivityPerformAction;

/* JADX INFO: loaded from: classes.dex */
public class l0 implements l {
    private static ShortcutInfo a(Context context, Intent intent, Bitmap bitmap, String str) {
        return new ShortcutInfo.Builder(context, Long.toHexString(System.currentTimeMillis())).setIntent(intent).setIcon(Icon.createWithBitmap(bitmap)).setShortLabel(str).build();
    }

    public static Intent b(Context context, pkg_a.b bVar) {
        return c(context, k(bVar), pkg_g.a0.a(bVar, context), bVar.n(context).toString());
    }

    private static Intent c(Context context, Intent intent, Bitmap bitmap, String str) {
        Intent intent2 = new Intent();
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.ICON", bitmap);
        intent2.putExtra("android.intent.extra.shortcut.NAME", str);
        return intent2;
    }

    private static pkg_a.b d(Intent intent) {
        ComponentName component;
        if (intent == null || (component = intent.getComponent()) == null || !pkg_f.l.CONST_PKG.equals(component.getPackageName()) || !ActivityPerformAction.class.getName().equals(component.getClassName())) {
            return null;
        }
        pkg_a.b bVarJ = j(intent);
        if (bVarJ.z != 0) {
            return bVarJ;
        }
        return null;
    }

    public static pkg_a.b e(Intent intent, Context context, String str) {
        pkg_a.b bVarF = f(intent, context, str);
        return bVarF == null ? g(intent, context) : bVarF;
    }

    private static pkg_a.b f(Intent intent, Context context, String str) {
        Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.shortcut.INTENT");
        if (intent2 == null) {
            v.c("No result data got!");
            return null;
        }
        pkg_a.b bVarD = d(intent2);
        if (bVarD != null) {
            return bVarD;
        }
        String stringExtra = intent.getStringExtra("android.intent.extra.shortcut.NAME");
        Intent.ShortcutIconResource shortcutIconResource = (Intent.ShortcutIconResource) intent.getParcelableExtra("android.intent.extra.shortcut.ICON_RESOURCE");
        if (shortcutIconResource != null) {
            return new pkg_a.b.r2(intent2, stringExtra, shortcutIconResource);
        }
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("android.intent.extra.shortcut.ICON");
        return bitmap != null ? new pkg_a.b.r2(intent2, stringExtra, bitmap, context) : new pkg_a.b.r2(intent2, stringExtra, str);
    }

    private static pkg_a.b g(Intent intent, Context context) {
        LauncherApps launcherApps;
        LauncherApps.PinItemRequest pinItemRequest;
        ShortcutInfo shortcutInfo;
        Intent intent2;
        if (Build.VERSION.SDK_INT < 26 || (launcherApps = (LauncherApps) context.getSystemService("launcherapps")) == null || (pinItemRequest = launcherApps.getPinItemRequest(intent)) == null || (shortcutInfo = pinItemRequest.getShortcutInfo()) == null || (intent2 = shortcutInfo.getIntent()) == null) {
            return null;
        }
        pkg_a.b bVarD = d(intent2);
        if (bVarD != null) {
            return bVarD;
        }
        Drawable shortcutIconDrawable = launcherApps.getShortcutIconDrawable(shortcutInfo, 0);
        CharSequence shortLabel = shortcutInfo.getShortLabel();
        if (shortLabel == null) {
            shortLabel = shortcutInfo.getLongLabel();
        }
        return new pkg_a.b.r2(intent2, shortLabel != null ? shortLabel.toString() : null, pkg_g.a0.h(shortcutIconDrawable), context);
    }

    private static void h(Intent intent, pkg_a.b bVar) {
        new k0(intent).j().f(bVar).l();
    }

    public static void i(Context context, pkg_a.b bVar) {
        ShortcutManager shortcutManager;
        if (Build.VERSION.SDK_INT < 26 || (shortcutManager = (ShortcutManager) context.getSystemService("shortcut")) == null || !shortcutManager.isRequestPinShortcutSupported()) {
            context.sendBroadcast(b(context, bVar).setAction("com.android.launcher.action.INSTALL_SHORTCUT"));
        } else {
            shortcutManager.requestPinShortcut(a(context, k(bVar), pkg_g.a0.a(bVar, context), bVar.n(context).toString()), null);
        }
    }

    public static pkg_a.b j(Intent intent) {
        String stringExtra = intent.getStringExtra("data");
        if (stringExtra != null) {
            return pkg_a.b.f(stringExtra);
        }
        k0 k0Var = new k0(intent);
        pkg_a.b bVarG = k0Var.i() ? k0Var.g() : pkg_a.b.e();
        k0Var.k();
        return bVarG;
    }

    public static Intent k(pkg_a.b bVar) {
        Intent intent = new Intent("android.intent.action.VIEW");
        h(intent, bVar);
        intent.setComponent(new ComponentName(pkg_f.l.CONST_PKG, ActivityPerformAction.class.getName()));
        intent.setFlags(402653184);
        return intent;
    }
}
