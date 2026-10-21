package pkg_f;

import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public interface l {
    public static final String CONST_PKG = pkg_f.k.d();
    public static final String CONST_PREFIX = CONST_PKG + ".";
    public static final String CONST_FILES = pkg_f.k.c();
    public static final String CONST_PREFS = CONST_FILES + "prefs/";
    public static final String CONST_GESTURES = CONST_FILES + "gestures/";
    public static final String CONST_ICONS = CONST_FILES + "icons/";
    public static final String CONST_LOCAL_PREFS = CONST_FILES + "local_prefs";
    public static final String CONST_WIDGETS = CONST_FILES + "widgets";
    public static final boolean CONST_IS_PRO = CONST_PKG.contains("pro");
    public static final String CONST_EMAIL = pkg_f.k.a();
    public static final String CONST_VERSION = pkg_f.k.f();
    public static final String CONST_TAG = (CONST_IS_PRO ? "Xposed-edge-pro-" : "Xposed-edge-") + CONST_VERSION;
    public static final String CONST_FEEDBACK = pkg_f.k.b();
    public static final String CONST_INSTALLER = pkg_f.k.e();
    public static final String CONST_APP_TAG = CONST_IS_PRO ? "XEdgePro" : "XEdge";
    public static final int CONST_APP_NAME = CONST_IS_PRO ? R.string.app_name_pro : R.string.app_name;
}
