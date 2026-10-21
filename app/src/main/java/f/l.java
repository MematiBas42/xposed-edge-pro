package f;

import com.jozein.xedgepro.R;

/* JADX INFO: loaded from: classes.dex */
public interface l {
    public static final String j = f.k.d();
    public static final String k = j + '.';
    public static final String l = f.k.c();
    public static final String m = l + "prefs/";
    public static final String n = l + "gestures/";
    public static final String o = l + "icons/";
    public static final String p = l + "local_prefs";
    public static final String q = l + "widgets";
    public static final boolean r = j.contains("pro");
    public static final String s = f.k.a();
    public static final String t = f.k.f();
    public static final String u = (r ? "Xposed-edge-pro-" : "Xposed-edge-") + t;
    public static final String v = f.k.b();
    public static final String w = f.k.e();
    public static final String x = r ? "XEdgePro" : "XEdge";
    public static final int y = r ? R.string.app_name_pro : R.string.app_name;
}
