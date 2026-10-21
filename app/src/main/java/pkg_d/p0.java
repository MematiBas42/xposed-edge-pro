package pkg_d;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import com.jozein.xedgepro.ApplicationMain;
import com.jozein.xedgepro.R;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class p0 extends pkg_e.j {
    private static final int[] T = {R.drawable.ic_gesture_enabled, R.drawable.ic_key_enabled, R.drawable.ic_pie, R.drawable.ic_shortcuts_panel, R.drawable.ic_custom_panel, R.drawable.ic_left_side_bar, R.drawable.ic_right_side_bar, R.drawable.ic_quick_settings_tiles, R.drawable.ic_schedule, R.drawable.ic_app, R.drawable.ic_more_triggers, R.drawable.ic_saved_multi_action, R.drawable.ic_gesture_records, R.drawable.ic_action_collection, R.drawable.ic_freezer, R.drawable.ic_hidden_apps, R.drawable.ic_preferences, R.drawable.ic_more};
    private static final int[] U = {R.string.gestures, R.string.keys, R.string.pie, R.string.shortcuts_panel, R.string.custom_panels, R.string.left_side_bar, R.string.right_side_bar, R.string.tiles, R.string.schedule, R.string.app_state, R.string.more_triggers, R.string.saved_multi_actions, R.string.gesture_records, R.string.action_collection, R.string.freezer, R.string.hidden_apps, R.string.preferences, R.string.more};
    private static boolean V = false;
    private pkg_f.g0 M;
    private pkg_a.z O;
    private int[] Q;
    private Runnable N = null;
    private pkg_a.z.b P = null;
    private BroadcastReceiver mLogsReceiver = null;
    boolean S = false;

    class a implements pkg_a.z.b {
        a() {
        }

        @Override // pkg_a.z.b
        public void a(boolean z) {
            ((pkg_e.j.f) p0.this.L0(0)).setChecked(z);
        }

        @Override // pkg_a.z.b
        public void b(boolean z) {
            ((pkg_e.j.f) p0.this.L0(1)).setChecked(z);
        }
    }

    class b extends pkg_f.g0 {
        b(Handler handler, int i, long j) {
            super(handler, i, j);
        }

        @Override // pkg_f.g0
        protected boolean a(int i) {
            if (pkg_f.o0.m() != 0) {
                boolean unused = p0.V = true;
                p0.this.N(new pkg_e.m().u(p0.this.u(R.string.user_not_supported)), 1);
                return false;
            }
            if (Build.VERSION.SDK_INT < 26 && !ApplicationMain.l()) {
                boolean unused2 = p0.V = true;
                p0.this.N(new pkg_e.m().u(p0.this.u(R.string.xposed_not_installed)), 1);
                return false;
            }
            if (!ApplicationMain.isModuleActivated()) {
                return true;
            }
            boolean unused3 = p0.V = true;
            return false;
        }

        @Override // pkg_f.g0
        protected void b() {
            p0.this.M = null;
            if (p0.V) {
                return;
            }
            boolean unused = p0.V = true;
            p0.this.N(new pkg_e.m().u(p0.this.u(R.string.module_not_activated)), 2);
        }
    }

    class c extends BroadcastReceiver {
        c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("logs");
            String strI = ApplicationMain.i();
            String strJ = ApplicationMain.j();
            boolean z = strJ == null || strJ.equals(pkg_f.l.CONST_VERSION);
            if (!(strI == null || strI.equals(pkg_f.l.CONST_EMAIL)) || !z) {
                StringBuilder sb = new StringBuilder();
                sb.append("Please reboot to activate new version: ");
                sb.append(pkg_f.l.CONST_TAG);
                if (z) {
                    sb.append('-');
                    sb.append(pkg_f.l.CONST_EMAIL);
                    sb.append('(');
                    sb.append(strI);
                } else {
                    sb.append('(');
                    sb.append(strJ);
                }
                sb.append(')');
                sb.append("\n\n");
                sb.append(stringExtra);
                stringExtra = sb.toString();
            }
            p0.this.O(new l0().j0(stringExtra));
        }
    }

    private int[] Q1() {
        if (this.Q == null) {
            if (ApplicationMain.isModuleActivated()) {
                this.Q = pkg_f.l.CONST_IS_PRO ? new int[]{R.string.backup_restore, R.string.rate, R.string.share, R.string.help_and_support, R.string.about, R.string.logs} : new int[]{R.string.backup_restore, R.string.rate, R.string.share, R.string.get_pro_version, R.string.help_and_support, R.string.about, R.string.logs};
            } else {
                this.Q = pkg_f.l.CONST_IS_PRO ? new int[]{R.string.backup_restore, R.string.rate, R.string.share, R.string.help_and_support, R.string.about} : new int[]{R.string.backup_restore, R.string.rate, R.string.share, R.string.get_pro_version, R.string.help_and_support, R.string.about};
            }
        }
        return this.Q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void R1(CompoundButton compoundButton, boolean z) {
        U1(compoundButton, z, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void S1(CompoundButton compoundButton, boolean z) {
        U1(compoundButton, z, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void T1(View view) {
        P(new e0(), 5);
    }

    private void U1(CompoundButton compoundButton, boolean z, int i) {
        if (!this.S) {
            this.S = true;
            if (!ApplicationMain.isModuleActivated()) {
                e0(R.string.module_not_activated);
                compoundButton.setChecked(this.O.p(i));
                return;
            }
        }
        boolean zP = this.O.p(i);
        if (zP != z) {
            this.O.X(compoundButton.getContext(), i, z);
        }
        compoundButton.setChecked(zP);
        if (zP && z && i == 1) {
            pkg_a.b.s0.C(compoundButton.getContext(), 8, -1);
        }
    }

    private void W1() {
        Context contextM0 = M0();
        int i = Build.VERSION.SDK_INT;
        if (i < 30 || Environment.isExternalStorageLegacy()) {
            if (i >= 23 && (contextM0.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0 || contextM0.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0)) {
                requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                return;
            }
        } else if (!Environment.isExternalStorageManager()) {
            String str = pkg_f.l.CONST_PKG;
            if (pkg_f.p0.q(contextM0, str, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
                if (Y1()) {
                    try {
                        Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
                        intent.setData(Uri.fromParts("package", str, null));
                        startActivityForResult(intent, 2);
                        return;
                    } catch (Throwable th) {
                        g0(th);
                    }
                } else {
                    e0(R.string.permission_denied);
                }
            }
        }
        P(new m(), 1);
    }

    private boolean Y1() {
        try {
            int iLastModified = (int) new File(f().getApplicationInfo().sourceDir).lastModified();
            if (pkg_a.m.b().a(5) == iLastModified) {
                return false;
            }
            pkg_a.m.b().f(5, iLastModified);
            return true;
        } catch (Throwable th) {
            pkg_f.v.d(th);
            return false;
        }
    }

    private void Z1(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", charSequence2);
        intent.putExtra("android.intent.extra.TEXT", charSequence3);
        try {
            startActivity(Intent.createChooser(intent, charSequence));
        } catch (Throwable unused) {
        }
    }

    private void a2() {
        if (Build.VERSION.SDK_INT >= 26 || b2("de.robv.android.xposed.installer", 1) || b2("org.meowcat.edxposed.manager", 3)) {
            return;
        }
        b2("com.solohsu.android.edxp.manager", 1);
    }

    private boolean b2(String str, int i) {
        try {
            Intent launchIntentForPackage = f().getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return false;
            }
            launchIntentForPackage.putExtra("fragment", i);
            launchIntentForPackage.putExtra("section", "modules");
            launchIntentForPackage.setFlags(805306368);
            startActivity(launchIntentForPackage);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // pkg_e.j0.c
    protected void B() {
        super.B();
        CharSequence charSequenceU = u(pkg_f.l.CONST_APP_NAME);
        int iM = pkg_f.o0.m();
        if (iM != 0) {
            charSequenceU = ((Object) charSequenceU) + " (" + iM + ")";
        }
        d0(charSequenceU);
        if (h().getBoolean("init", false)) {
            S(R.drawable.ic_action_bar_help, new View.OnClickListener() { // from class: d.m0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    p0.this.T1(view);
                }
            });
        }
    }

    @Override // pkg_e.j
    protected int B0() {
        return U.length;
    }

    @Override // pkg_e.j0.c
    protected void F(Intent intent) {
        super.F(intent);
        int intExtra = intent.getIntExtra("clicked", -1);
        if (intExtra >= 0) {
            V = true;
            k1(intExtra);
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x003e A[PHI: r6
      0x003e: PHI (r6v3 pkg_e.j$j) = (r6v2 pkg_e.j$j), (r6v6 pkg_e.j$j) binds: [B:24:0x003c, B:19:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // pkg_e.j0.c
    protected void J(Bundle bundle, int i) {
        pkg_e.j.ViewOnTouchListenerC0004j viewOnTouchListenerC0004j;
        ListView listViewG0;
        if (bundle == null) {
            return;
        }
        boolean z = false;
        if (i == 2) {
            if (bundle.getBoolean("result", false)) {
                a2();
                return;
            }
            return;
        }
        if (i == 3) {
            int i2 = bundle.getInt("result");
            if (i2 > 1) {
                return;
            }
            viewOnTouchListenerC0004j = (pkg_e.j.ViewOnTouchListenerC0004j) L0(0);
            if (i2 == 0) {
                z = true;
            }
        } else {
            if (i != 4) {
                if (i == 5 && (listViewG0 = G0()) != null) {
                    listViewG0.setSelectionFromTop(0, 0);
                    x1();
                    return;
                }
                return;
            }
            int i3 = bundle.getInt("result");
            if (i3 > 1) {
                return;
            }
            viewOnTouchListenerC0004j = (pkg_e.j.ViewOnTouchListenerC0004j) L0(1);
            if (i3 == 0) {
                z = true;
            }
        }
        viewOnTouchListenerC0004j.setChecked(z);
    }

    @Override // pkg_e.j
    protected boolean N0(int i) {
        return i == 17;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:15:0x0037  */
    /* JADX WARN: Code duplicated, block: B:17:0x0057  */
    @Override // pkg_e.j
    /* JADX INFO: renamed from: V1, reason: merged with bridge method [inline-methods] */
    public pkg_e.j.k h1(int i) {
        boolean z;
        if (i == 0) {
            pkg_e.j.f fVar = new pkg_e.j.f(C0(T[i]), u(R.string.gestures), (CharSequence) null, this.O.p(1));
            fVar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: d.o0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    p0.this.R1(compoundButton, z2);
                }
            });
            return fVar;
        }
        if (i == 1) {
            pkg_e.j.f fVar2 = new pkg_e.j.f(C0(T[i]), u(R.string.keys), (CharSequence) null, this.O.p(0));
            fVar2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: d.n0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    p0.this.S1(compoundButton, z2);
                }
            });
            return fVar2;
        }
        switch (i) {
            case 7:
                if (Build.VERSION.SDK_INT < 24) {
                    pkg_e.j.g gVar = new pkg_e.j.g(C0(T[i]), u(U[i]), u(R.string.since_nougat));
                    gVar.setEnabled(false);
                    return gVar;
                }
                z = false;
                if (!z) {
                    return new pkg_e.j.g(C0(T[i]), u(U[i]), (CharSequence) null);
                }
                pkg_e.j.g gVar2 = new pkg_e.j.g(C0(T[i]), u(U[i]), u(R.string.pro_only));
                gVar2.setEnabled(false);
                return gVar2;
            case 8:
            case 9:
            case 10:
            case 11:
                z = true ^ pkg_f.l.CONST_IS_PRO;
                if (!z) {
                    return new pkg_e.j.g(C0(T[i]), u(U[i]), (CharSequence) null);
                }
                pkg_e.j.g gVar3 = new pkg_e.j.g(C0(T[i]), u(U[i]), u(R.string.pro_only));
                gVar3.setEnabled(false);
                return gVar3;
            default:
                z = false;
                if (!z) {
                    return new pkg_e.j.g(C0(T[i]), u(U[i]), (CharSequence) null);
                }
                pkg_e.j.g gVar4 = new pkg_e.j.g(C0(T[i]), u(U[i]), u(R.string.pro_only));
                gVar4.setEnabled(false);
                return gVar4;
        }
    }

    public p0 X1(int i) {
        h().putInt("clicked", i);
        return this;
    }

    @Override // pkg_e.j
    protected View i1(int i) {
        int[] iArrQ1 = Q1();
        CharSequence charSequenceU = u(iArrQ1[i]);
        switch (iArrQ1[i]) {
            case R.string.about /* 2131099648 */:
                return new pkg_e.j.g(C0(R.drawable.ic_about), charSequenceU, (CharSequence) null);
            case R.string.backup_restore /* 2131099781 */:
                if (pkg_f.l.CONST_IS_PRO) {
                    return new pkg_e.j.g(C0(R.drawable.ic_backup_restore), charSequenceU, (CharSequence) null);
                }
                pkg_e.j.g gVar = new pkg_e.j.g(C0(R.drawable.ic_backup_restore), charSequenceU, u(R.string.pro_only));
                gVar.setEnabled(false);
                return gVar;
            case R.string.get_pro_version /* 2131099930 */:
                return new pkg_e.j.g(C0(R.drawable.ic_get_pro_version), charSequenceU, (CharSequence) null);
            case R.string.help_and_support /* 2131099931 */:
                return new pkg_e.j.g(C0(R.drawable.ic_help), charSequenceU, (CharSequence) null);
            case R.string.logs /* 2131099979 */:
                return new pkg_e.j.g(C0(R.drawable.ic_activity), charSequenceU, (CharSequence) null);
            case R.string.rate /* 2131100030 */:
                return new pkg_e.j.g(C0(R.drawable.ic_rate), charSequenceU, (CharSequence) null);
            case R.string.share /* 2131100079 */:
                return new pkg_e.j.g(C0(R.drawable.ic_share), charSequenceU, (CharSequence) null);
            default:
                return null;
        }
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        pkg_e.j0.c c0Var;
        switch (i) {
            case 0:
                c0Var = new c0();
                break;
            case 1:
                c0Var = new j0();
                break;
            case 2:
                c0Var = new w0();
                break;
            case 3:
                c0Var = new w1();
                break;
            case 4:
                c0Var = new r();
                break;
            case 5:
            case 6:
                c0Var = new x1().U1(g().h(), i != 5 ? 1 : 0);
                break;
            case 7:
                if (Build.VERSION.SDK_INT < 24) {
                    return;
                } else {
                    c0Var = new c2();
                }
                break;
            case 8:
                if (!pkg_f.l.CONST_IS_PRO) {
                    return;
                } else {
                    c0Var = new u1();
                }
                break;
            case 9:
                if (!pkg_f.l.CONST_IS_PRO) {
                    return;
                } else {
                    c0Var = new j();
                }
                break;
            case 10:
                if (!pkg_f.l.CONST_IS_PRO) {
                    return;
                } else {
                    c0Var = new q0();
                }
                break;
            case 11:
                if (!pkg_f.l.CONST_IS_PRO) {
                    return;
                } else {
                    c0Var = new r1().I1(false);
                }
                break;
            case 12:
                c0Var = new a0().C1(false);
                break;
            case 13:
                c0Var = new pkg_d.b().T1(false);
                break;
            case 14:
                c0Var = new y();
                break;
            case 15:
                c0Var = new h0();
                break;
            case 16:
                c0Var = new o1();
                break;
            case 17:
                y1(17, Q1().length);
                return;
            default:
                return;
        }
        O(c0Var);
    }

    @Override // pkg_e.j
    protected boolean l1(int i) {
        pkg_e.z zVarV;
        int i2;
        if (i == 0) {
            zVarV = new pkg_e.z().v(new CharSequence[]{u(R.string.enable), u(R.string.disable)}, !this.O.p(1) ? 1 : 0);
            i2 = 3;
        } else {
            if (i != 1) {
                return false;
            }
            zVarV = new pkg_e.z().v(new CharSequence[]{u(R.string.enable), u(R.string.disable)}, !this.O.p(0) ? 1 : 0);
            i2 = 4;
        }
        N(zVarV, i2);
        return true;
    }

    @Override // pkg_e.j
    protected void o1(int i) {
        Activity activity;
        String str;
        switch (Q1()[i]) {
            case R.string.about /* 2131099648 */:
                StringBuilder sb = new StringBuilder(256);
                sb.append(pkg_f.l.CONST_TAG);
                String strI = ApplicationMain.i();
                String strJ = ApplicationMain.j();
                boolean z = strJ == null || strJ.equals(pkg_f.l.CONST_VERSION);
                if (!(strI == null || strI.equals(pkg_f.l.CONST_EMAIL)) || !z) {
                    if (z) {
                        sb.append('-');
                        sb.append(pkg_f.l.CONST_EMAIL);
                        sb.append('(');
                        sb.append(strI);
                    } else {
                        sb.append('(');
                        sb.append(strJ);
                    }
                    sb.append(')');
                }
                sb.append("\n\n");
                sb.append(u(R.string.about_description));
                N(new pkg_e.a0().u(u(R.string.about), sb.toString()), 1);
                return;
            case R.string.backup_restore /* 2131099781 */:
                if (pkg_f.l.CONST_IS_PRO) {
                    W1();
                    return;
                }
                return;
            case R.string.get_pro_version /* 2131099930 */:
                activity = getActivity();
                str = pkg_f.l.CONST_INSTALLER;
                break;
            case R.string.help_and_support /* 2131099931 */:
                P(new e0(), 5);
                return;
            case R.string.logs /* 2131099979 */:
                if (this.mLogsReceiver == null) {
                    this.mLogsReceiver = new c();
                    f().registerReceiver(this.mLogsReceiver, new IntentFilter(pkg_a.b.s0.O), 2);
                }
                pkg_a.b.s0.B(f(), 26);
                return;
            case R.string.rate /* 2131100030 */:
                activity = getActivity();
                str = pkg_f.l.CONST_PKG;
                break;
            case R.string.share /* 2131100079 */:
                CharSequence charSequenceU = u(pkg_f.l.CONST_APP_NAME);
                Z1(charSequenceU, charSequenceU, ((Object) u(R.string.description)) + "\nhttp://play.google.com/store/apps/details?id=" + pkg_f.l.CONST_PKG);
                return;
            default:
                return;
        }
        pkg_f.p0.v(activity, str);
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 2) {
            if (Build.VERSION.SDK_INT >= 30 && !Environment.isExternalStorageManager()) {
                e0(R.string.permission_denied);
            }
            P(new m(), 1);
        }
    }

    @Override // pkg_e.j, android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.O = g().h();
        this.P = new a();
        g().e(this.P);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.P != null) {
            g().o(this.P);
            this.P = null;
        }
        if (this.mLogsReceiver != null) {
            f().unregisterReceiver(this.mLogsReceiver);
            this.mLogsReceiver = null;
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        pkg_f.g0 g0Var = this.M;
        if (g0Var != null) {
            g0Var.f();
        }
    }

    @Override // android.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            for (int i2 : iArr) {
                if (i2 != 0) {
                    e0(R.string.permission_denied);
                    break;
                }
            }
            P(new m(), 1);
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        pkg_f.g0 g0Var = this.M;
        if (g0Var != null) {
            g0Var.d();
        }
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        View view = getView();
        if (view != null) {
            view.removeCallbacks(this.N);
        }
        this.N = null;
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (view == null) {
            return;
        }
        if (!V) {
            this.M = new b(pkg_f.h0.a(), 2, 1500L);
        }
        Bundle bundleH = h();
        int i = bundleH.getInt("clicked", -1);
        if (i >= 0) {
            bundleH.putInt("clicked", -1);
            k1(i);
        }
    }
}
