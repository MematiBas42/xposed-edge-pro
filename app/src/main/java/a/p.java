package a;

import f.s;
import f.p;
import f.l;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.util.SparseArray;
import android.view.KeyEvent;
import com.jozein.xedgepro.R;
import g.a0;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class p extends r {
    public static final String P = f.l.m + "prefs";
    static final String Q;
    protected final b.q B;
    protected final b.t1 C;
    protected final b[][] D;
    protected final c E;
    protected final b[][] F;
    protected final b[][] G;
    protected final b[] H;
    protected final b.q1[] I;
    protected int J;
    protected int K;
    protected int[] L;
    protected int[] M;
    protected boolean N;
    private boolean O;

    public interface a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f10a = {R.string.edge_left, R.string.edge_right, R.string.edge_top, R.string.edge_bottom};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final int[] f11b = {R.string.key_back, R.string.key_home, R.string.key_recent_apps, R.string.key_menu, R.string.key_volume_up, R.string.key_volume_down};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final int[] f12c = {16, 17, 18, 19};

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final int[] f13d = {0, 1, 2, 3, 4, 5};
    }

    public interface b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final int[] f14e = {4, 5, 6, 0, 7, 8, 9, 1, 10, 11, 12, 2, 13, 14, 15, 3};

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final int[] f15f = {R.drawable.ic_edge_left_top, R.drawable.ic_edge_left_center, R.drawable.ic_edge_left_bottom, R.drawable.ic_edge_left, R.drawable.ic_edge_right_top, R.drawable.ic_edge_right_center, R.drawable.ic_edge_right_bottom, R.drawable.ic_edge_right, R.drawable.ic_edge_top_left, R.drawable.ic_edge_top_center, R.drawable.ic_edge_top_right, R.drawable.ic_edge_top, R.drawable.ic_edge_bottom_left, R.drawable.ic_edge_bottom_center, R.drawable.ic_edge_bottom_right, R.drawable.ic_edge_bottom};

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final int[] f16g = {R.string.edge_left_top, R.string.edge_left_center, R.string.edge_left_bottom, R.string.edge_left, R.string.edge_right_top, R.string.edge_right_center, R.string.edge_right_bottom, R.string.edge_right, R.string.edge_top_left, R.string.edge_top_center, R.string.edge_top_right, R.string.edge_top, R.string.edge_bottom_left, R.string.edge_bottom_center, R.string.edge_bottom_right, R.string.edge_bottom};
        public static final int[] h = {12, 14, 13, 0, 15, 17, 16, 1, 18, 20, 19, 2, 21, 23, 22, 3};
    }

    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b[][] f17a = new b[320][];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int[] f18b = new int[10];

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int[] f19c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private SparseArray<d> f20d = new SparseArray<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final b.q f21e = b.e();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f22f = false;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f23g = 0;

        c() {
        }

        private d d(int i, boolean z) {
            b.q qVar = this.f21e;
            d dVar = new d(z, qVar, qVar, qVar);
            if (this.f22f) {
                SparseArray<d> sparseArrayClone = this.f20d.clone();
                sparseArrayClone.append(i, dVar);
                this.f20d = sparseArrayClone;
            } else {
                this.f20d.append(i, dVar);
            }
            return dVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            p.N(this.f17a, this.f21e);
            this.f18b = new int[10];
            this.f20d = new SparseArray<>();
        }

        void c(int i) {
            p(i, 0, this.f21e);
        }

        void f() {
            if (this.f19c == null || this.f23g < this.f17a.length) {
                return;
            }
            int size = this.f20d.size();
            for (int i = 0; i < size; i++) {
                int iKeyAt = this.f20d.keyAt(i);
                if (iKeyAt <= this.f23g) {
                    if (((1 << (iKeyAt % 32)) & this.f19c[iKeyAt / 32]) != 0) {
                        this.f20d.valueAt(i).f24a = true;
                    }
                }
            }
            this.f19c = null;
        }

        b g(int i, int i2) {
            b[] bVarArr;
            b bVar;
            b[][] bVarArr2 = this.f17a;
            if (i <= bVarArr2.length) {
                return (i < 0 || (bVarArr = bVarArr2[i]) == null || (bVar = bVarArr[i2]) == null) ? this.f21e : bVar;
            }
            d dVar = this.f20d.get(i);
            return dVar != null ? dVar.a(i2, this.f21e) : this.f21e;
        }

        ArrayList<Integer> h() {
            ArrayList<Integer> arrayList = new ArrayList<>(16);
            int i = 0;
            while (true) {
                b[][] bVarArr = this.f17a;
                if (i >= bVarArr.length) {
                    break;
                }
                if (bVarArr[i] != null) {
                    arrayList.add(Integer.valueOf(i));
                }
                i++;
            }
            int size = this.f20d.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(Integer.valueOf(this.f20d.keyAt(i2)));
            }
            return arrayList;
        }

        boolean i(int i) {
            if (i < 0) {
                return false;
            }
            if (i < this.f17a.length) {
                return ((1 << (i % 32)) & this.f18b[i / 32]) != 0;
            }
            d dVar = this.f20d.get(i);
            return dVar != null && dVar.f24a;
        }

        boolean j(int i) {
            b[][] bVarArr = this.f17a;
            if (i >= bVarArr.length) {
                d dVar = this.f20d.get(i);
                return dVar != null && dVar.b();
            }
            b[] bVarArr2 = bVarArr[i];
            if (bVarArr2 != null && i(i)) {
                if (bVarArr2[0] != null && bVarArr2[0].z != 0) {
                    return true;
                }
                if (bVarArr2[1] != null && bVarArr2[1].z != 0) {
                    return true;
                }
                if (bVarArr2[2] != null && bVarArr2[2].z != 0) {
                    return true;
                }
            }
            return false;
        }

        void k(f.s sVar) {
            int iH = sVar.h();
            if (iH >= this.f17a.length) {
                this.f20d.append(iH, new d(sVar.h() != 0, sVar.g(), sVar.g(), sVar.g()));
                return;
            }
            q(iH, sVar.h() != 0);
            p(iH, 0, sVar.g());
            p(iH, 1, sVar.g());
            p(iH, 2, sVar.g());
        }

        void l(f.s sVar) {
            int iH = sVar.h();
            if (iH > this.f23g) {
                this.f23g = iH;
            }
            p(iH, sVar.h(), sVar.g());
        }

        void m(f.s sVar) {
            int i = 0;
            while (true) {
                int[] iArr = this.f18b;
                if (i >= iArr.length) {
                    break;
                }
                iArr[i] = sVar.h();
                i++;
            }
            int maxKeyCode = KeyEvent.getMaxKeyCode();
            if (maxKeyCode <= this.f17a.length) {
                return;
            }
            int[] iArr2 = new int[(maxKeyCode + 31) / 32];
            this.f19c = iArr2;
            int[] iArr3 = this.f18b;
            iArr2[iArr3.length - 1] = iArr3[iArr3.length - 1];
            int length = iArr3.length;
            while (true) {
                int[] iArr4 = this.f19c;
                if (length >= iArr4.length) {
                    return;
                }
                iArr4[length] = sVar.h();
                length++;
            }
        }

        void n(int i) {
            b[][] bVarArr = this.f17a;
            if (i < bVarArr.length) {
                bVarArr[i] = null;
            } else {
                this.f20d.remove(i);
            }
        }

        void o(f.p pVar) {
            int size = this.f20d.size();
            for (int i = 0; i < size; i++) {
                int iKeyAt = this.f20d.keyAt(i);
                d dVarValueAt = this.f20d.valueAt(i);
                if (dVarValueAt != null) {
                    pVar.d(11).d(iKeyAt).d(dVarValueAt.f24a ? 1 : 0).f(dVarValueAt.f25b).f(dVarValueAt.f26c).f(dVarValueAt.f27d).C();
                }
            }
        }

        void p(int i, int i2, b bVar) {
            if (i < 0) {
                return;
            }
            if (bVar == null) {
                bVar = this.f21e;
            }
            b[][] bVarArr = this.f17a;
            if (i >= bVarArr.length) {
                d dVarD = this.f20d.get(i);
                if (dVarD == null) {
                    dVarD = d(i, false);
                }
                dVarD.c(i2, bVar);
                return;
            }
            b[] bVarArr2 = bVarArr[i];
            if (bVarArr2 == null) {
                bVarArr2 = new b[3];
                bVarArr[i] = bVarArr2;
            }
            bVarArr2[i2] = bVar;
        }

        void q(int i, boolean z) {
            if (i < 0) {
                return;
            }
            if (i >= this.f17a.length) {
                d dVar = this.f20d.get(i);
                if (dVar != null) {
                    dVar.f24a = z;
                    return;
                } else {
                    d(i, z);
                    return;
                }
            }
            if (z) {
                int[] iArr = this.f18b;
                int i2 = i / 32;
                iArr[i2] = (1 << (i % 32)) | iArr[i2];
                return;
            }
            int[] iArr2 = this.f18b;
            int i3 = i / 32;
            iArr2[i3] = ((1 << (i % 32)) ^ (-1)) & iArr2[i3];
        }
    }

    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f24a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        b f25b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        b f26c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        b f27d;

        d(boolean z, b bVar, b bVar2, b bVar3) {
            this.f24a = z;
            this.f25b = bVar;
            this.f26c = bVar2;
            this.f27d = bVar3;
        }

        b a(int i, b bVar) {
            b bVar2;
            if (i == 0) {
                bVar2 = this.f25b;
            } else if (i != 1) {
                bVar2 = i != 2 ? null : this.f27d;
            } else {
                bVar2 = this.f26c;
            }
            return bVar2 != null ? bVar2 : bVar;
        }

        boolean b() {
            b bVar;
            b bVar2;
            b bVar3;
            return this.f24a && !(((bVar = this.f25b) == null || bVar.z == 0) && (((bVar2 = this.f26c) == null || bVar2.z == 0) && ((bVar3 = this.f27d) == null || bVar3.z == 0)));
        }

        void c(int i, b bVar) {
            if (i == 0) {
                this.f25b = bVar;
            } else if (i == 1) {
                this.f26c = bVar;
            } else {
                if (i != 2) {
                    return;
                }
                this.f27d = bVar;
            }
        }
    }

    public interface e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f28a = {R.string.action_keep_screen_on, R.string.action_torch, R.string.action_screen_filter, R.string.action_gesture_record, R.string.action_gesture_enabled, R.string.action_key_enabled, R.string.action_game_mode};
    }

    public interface f {
        public static final int[] i = {R.string.mode_default, R.string.mode_tap, R.string.mode_long_press, R.string.mode_double_tap, R.string.mode_transform};
    }

    public interface g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f29a = {0, R.string.state_boot_completed, 1, R.string.state_screen_on, 2, R.string.state_screen_off, 3, R.string.state_user_present, 4, R.string.state_battery_full, 5, R.string.state_battery_low, 6, R.string.state_battery_okay, 7, R.string.state_power_connected, 8, R.string.state_power_disconnected, 9, R.string.state_wifi_connected, 10, R.string.state_wifi_disconnected, 11, R.string.state_net_connected, 12, R.string.state_net_disconnected, 13, R.string.state_airplane_mode_on, 14, R.string.state_airplane_mode_off, 15, R.string.state_headset_plugged, 16, R.string.state_headset_unplugged, 17, R.string.state_usb_plugged, 18, R.string.state_usb_unplugged, 19, R.string.state_screen_rotated, 20, R.string.state_screen_rotated_portrait, 21, R.string.state_screen_rotated_landscape, 29, R.string.state_soft_keyboard_shown, 30, R.string.state_soft_keyboard_hidden, 31, R.string.state_status_bar_shown, 32, R.string.state_status_bar_hidden, 33, R.string.state_nav_bar_shown, 34, R.string.state_nav_bar_hidden, 35, R.string.state_full_screen, 36, R.string.state_exit_full_screen, 22, R.string.state_sms_received, 23, R.string.state_incoming_call_ringed, 24, R.string.state_incoming_call_connected, 25, R.string.state_incoming_call_ended, 26, R.string.state_outgoing_call_dialed, 27, R.string.state_outgoing_call_connected, 28, R.string.state_outgoing_call_ended};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final int f30b = f29a.length / 2;
    }

    public interface h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f31a = {R.string.vibrate_disable, R.string.vibrate_custom, R.string.vibrate_click, R.string.vibrate_double_click, R.string.vibrate_tick, R.string.vibrate_heavy_click};
    }

    static {
        Q = f.l.r ? "xedgepro_state" : "xedge_state";
    }

    public p() {
        super(f.l.k + "PREFS_CHANGED");
        this.B = b.e();
        this.C = b.r();
        this.D = new b[16][];
        this.E = new c();
        this.F = new b[4][];
        this.G = new b[4][];
        this.H = new b[g.f30b];
        this.I = new b.q1[2];
        this.J = 0;
        this.K = 0;
        this.L = new int[39];
        this.M = new int[0];
        this.N = false;
        this.O = false;
        L();
    }

    static int D(String str) {
        str.hashCode();
        if (str.equals("toast_command_output")) {
            return 36;
        }
        return !str.equals("show_app_process_state") ? -1 : 39;
    }

    public static boolean E(int i, int i2) {
        switch (i) {
            case 0:
            case 4:
            case 5:
            case 6:
                return 3 == i2;
            case 1:
            case 7:
            case 8:
            case 9:
                return 4 == i2;
            case 2:
            case 10:
            case 11:
            case 12:
                return 5 == i2;
            case 3:
            case 13:
            case 14:
            case 15:
                return 6 == i2;
            default:
                return false;
        }
    }

    private void K(int i, f.s sVar) {
        int i2 = 0;
        switch (i) {
            case 0:
                Q(sVar.h(), sVar.h(), sVar.g());
                break;
            case 1:
                this.E.l(sVar);
                break;
            case 2:
                S(sVar.h(), sVar.h(), sVar.g());
                break;
            case 3:
                T(sVar.h(), sVar.h(), sVar.g());
                break;
            case 4:
                if (!(sVar instanceof f.m)) {
                    this.K = sVar.h();
                } else {
                    V(sVar.h(), sVar.h() != 0);
                }
                break;
            case 5:
            default:
                f.v.c("Unknown type: " + i);
                break;
            case 6:
                if (!(sVar instanceof f.m)) {
                    this.E.m(sVar);
                } else {
                    a0(sVar.h(), sVar.h() != 0);
                }
                break;
            case 7:
                if (!(sVar instanceof f.m)) {
                    while (true) {
                        int[] iArr = this.L;
                        if (i2 < iArr.length) {
                            iArr[i2] = sVar.h();
                            i2++;
                        }
                    }
                } else {
                    int iH = sVar.h();
                    int iH2 = sVar.h();
                    if (iH == 36 || iH == 37 || iH == 38) {
                        Y(iH, q.b(iH2));
                    } else {
                        Z(iH, iH2);
                    }
                }
                break;
            case 8:
                R(sVar.h(), sVar.g());
                break;
            case 9:
                int iH3 = sVar.h();
                b bVarG = sVar.g();
                if (bVarG.z == 63) {
                    U(iH3, (b.q1) bVarG);
                }
                break;
            case 10:
                int iH4 = sVar.h();
                int[] iArr2 = new int[iH4];
                while (i2 < iH4) {
                    iArr2[i2] = sVar.h();
                    i2++;
                }
                this.M = iArr2;
                break;
            case 11:
                this.E.k(sVar);
                break;
        }
    }

    private static void M(b[] bVarArr, b bVar) {
        for (int length = bVarArr.length - 1; length >= 0; length--) {
            bVarArr[length] = bVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void N(b[][] bVarArr, b bVar) {
        for (int length = bVarArr.length - 1; length >= 0; length--) {
            b[] bVarArr2 = bVarArr[length];
            if (bVarArr2 != null) {
                M(bVarArr2, bVar);
            }
        }
    }

    private void Q(int i, int i2, b bVar) {
        b[][] bVarArr = this.D;
        if (bVarArr[i] == null) {
            bVarArr[i] = new b[7];
        }
        bVarArr[i][i2] = bVar;
    }

    private void S(int i, int i2, b bVar) {
        b[][] bVarArr = this.F;
        if (bVarArr[i] == null) {
            bVarArr[i] = new b[12];
        }
        bVarArr[i][i2] = bVar;
    }

    private void T(int i, int i2, b bVar) {
        b[][] bVarArr = this.G;
        if (bVarArr[i] == null) {
            bVarArr[i] = new b[4];
        }
        bVarArr[i][i2] = bVar;
    }

    private void W(int i, boolean z) {
        int i2;
        if (z) {
            i2 = (1 << i) | this.J;
        } else {
            i2 = ((1 << i) ^ (-1)) & this.J;
        }
        this.J = i2;
        this.N = true;
    }

    public static boolean f() {
        try {
            byte[] bArrJ = f.q.c(P).j();
            return bArrJ != null && bArrJ.length > 0;
        } catch (Throwable th) {
            f.v.d(th);
            return false;
        }
    }

    public static int h(int i) {
        switch (i) {
            case 0:
            case 4:
            case 5:
            case 6:
                return 0;
            case 1:
            case 7:
            case 8:
            case 9:
                return 1;
            case 2:
            case 10:
            case 11:
            case 12:
                return 2;
            case 3:
            case 13:
            case 14:
            case 15:
                return 3;
            default:
                return -1;
        }
    }

    public static boolean q(Context context, int i) {
        return (Settings.System.getInt(context.getContentResolver(), Q, 0) & (1 << i)) != 0;
    }

    f.p A() {
        return new f.p(P);
    }

    public int B() {
        int iU = u(1);
        if (iU == 0) {
            iU = 1428300288;
        }
        int i = (this.J >>> 4) & 15;
        if (i == 0) {
            return iU;
        }
        return (iU & 16777215) | (((i * 208) / 15) << 24);
    }

    public int C() {
        return (this.J >>> 4) & 15;
    }

    public boolean F(int i) {
        int i2;
        switch (i) {
            case 0:
            case 4:
            case 5:
            case 6:
                i2 = 16;
                break;
            case 1:
            case 7:
            case 8:
            case 9:
                i2 = 17;
                break;
            case 2:
            case 10:
            case 11:
            case 12:
                i2 = 18;
                break;
            case 3:
            case 13:
            case 14:
            case 15:
                i2 = 19;
                break;
            default:
                return false;
        }
        return ((1 << i2) & u(24)) != 0;
    }

    public boolean G(int i) {
        int i2 = 4;
        if (i == 3) {
            i2 = 1;
        } else if (i == 4) {
            i2 = 0;
        } else if (i != 24) {
            if (i == 25) {
                i2 = 5;
            } else if (i == 82) {
                i2 = 3;
            } else {
                if (i != 187) {
                    return false;
                }
                i2 = 2;
            }
        }
        return (u(24) & (1 << i2)) != 0;
    }

    public boolean H(int i) {
        if (i >= 0 && i < 16 && p(1)) {
            for (int i2 = 0; i2 < 2; i2++) {
                if (s(i)) {
                    b[][] bVarArr = this.D;
                    if (bVarArr[i] != null) {
                        for (b bVar : bVarArr[i]) {
                            if (bVar != null && bVar.z != 0) {
                                return true;
                            }
                        }
                    } else {
                        continue;
                    }
                }
                i = h(i);
            }
        }
        return false;
    }

    public boolean I(int i) {
        return p(0) && this.E.j(i);
    }

    public boolean J() {
        return this.O;
    }

    public boolean L() {
        f.p pVarA = A();
        if (pVarA.E()) {
            this.O = true;
            int iH = pVarA.h();
            if (iH != -1) {
                while (true) {
                    K(iH, pVarA);
                    if (!pVarA.t()) {
                        break;
                    }
                    iH = pVarA.h();
                }
            }
            this.E.f();
        } else {
            this.O = false;
        }
        pVarA.m();
        return this.O;
    }

    protected void O(f.p pVar, int i, b[][] bVarArr) {
        for (int i2 = 0; i2 < bVarArr.length; i2++) {
            if (bVarArr[i2] != null) {
                b[] bVarArr2 = bVarArr[i2];
                if (bVarArr2[0] != null) {
                    pVar.d(i).d(i2).d(0).f(bVarArr2[0]).C();
                }
                for (int i3 = 1; i3 < bVarArr2.length; i3++) {
                    if (bVarArr2[i3] != null && bVarArr2[i3].z != 0) {
                        pVar.d(i).d(i2).d(i3).f(bVarArr2[i3]).C();
                    }
                }
            }
        }
    }

    protected void P(int i, int i2, int i3, b bVar) {
        if (i == 0) {
            Q(i2, i3, bVar);
        } else if (i == 1) {
            this.E.p(i2, i3, bVar);
        } else if (i == 2) {
            S(i2, i3, bVar);
        } else if (i != 3) {
            f.v.c("unknown type: " + i);
        } else {
            T(i2, i3, bVar);
        }
        this.N = true;
    }

    protected void R(int i, b bVar) {
        b[] bVarArr = this.H;
        if (i < bVarArr.length) {
            bVarArr[i] = bVar;
            this.N = true;
        }
    }

    protected void U(int i, b.q1 q1Var) {
        this.I[i] = q1Var;
        this.N = true;
    }

    protected void V(int i, boolean z) {
        int i2;
        if (i < 32) {
            if (z) {
                i2 = (1 << i) | this.K;
            } else {
                i2 = ((1 << i) ^ (-1)) & this.K;
            }
            this.K = i2;
        }
        this.N = true;
    }

    public void X(Context context, int i, boolean z) {
        W(i, z);
        Settings.System.putInt(context.getContentResolver(), Q, this.J);
    }

    protected void Y(int i, float f2) {
        int iC = q.c(i);
        if (iC != i) {
            this.L[i] = q.a(f2);
            this.L[iC] = f2 <= 0.0f ? 0 : (int) (f2 * a0.j());
            this.N = true;
        } else {
            f.v.d(new IllegalArgumentException("Unknown flag: " + i));
        }
    }

    protected void Z(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.L;
            if (i < iArr.length) {
                iArr[i] = i2;
                this.N = true;
            }
        }
    }

    protected void a0(int i, boolean z) {
        this.E.q(i, z);
        this.N = true;
    }

    @Override // a.r
    protected void b(Intent intent) {
        int iH;
        f.m mVar = new f.m(intent);
        if (mVar.i() && (iH = mVar.h()) != -1) {
            K(iH, mVar);
        }
        mVar.k();
    }

    protected void b0(int[] iArr) {
        this.M = iArr;
    }

    public void c0(Context context, int i) {
        if (i > 15) {
            i = 15;
        }
        if (i == C()) {
            return;
        }
        this.J = (i << 4) | (this.J & (-241));
        Settings.System.putInt(context.getContentResolver(), Q, this.J);
    }

    @Override // a.r
    public void d(Context context, Handler handler) {
        this.J = Settings.System.getInt(context.getContentResolver(), Q, this.J);
        this.E.f22f = true;
        super.d(context, handler);
    }

    public void g() {
        N(this.D, this.B);
        this.E.e();
        N(this.F, this.C);
        N(this.G, this.C);
        M(this.H, this.C);
        M(this.I, new b.q1(new b[0]));
        this.O = false;
    }

    public b i(int i, int i2) {
        b[][] bVarArr = this.D;
        return (bVarArr[i] == null || bVarArr[i][i2] == null) ? this.B : bVarArr[i][i2];
    }

    public b j(int i, int i2) {
        return this.E.g(i, i2);
    }

    public b k(int i) {
        b[] bVarArr = this.H;
        if (i >= bVarArr.length) {
            return this.C;
        }
        b bVar = bVarArr[i];
        return (bVar == null || bVar.z == 0) ? this.C : bVar;
    }

    public b l(int i, int i2) {
        b[][] bVarArr = this.F;
        b bVar = bVarArr[i] == null ? null : bVarArr[i][i2];
        return (bVar == null || bVar.z == 0) ? this.C : bVar;
    }

    public b m(int i, int i2) {
        b[][] bVarArr = this.G;
        b bVar = bVarArr[i] == null ? null : bVarArr[i][i2];
        return (bVar == null || bVar.z == 0) ? this.C : bVar;
    }

    public b.q1 n(int i) {
        b.q1 q1Var = this.I[i];
        return q1Var == null ? new b.q1(new ArrayList()) : q1Var;
    }

    public boolean o(int i) {
        if (i < 32) {
            return ((1 << i) & this.K) != 0;
        }
        return ((1 << (i - 32)) & u(21)) != 0;
    }

    public boolean p(int i) {
        return ((1 << i) & this.J) != 0;
    }

    public int r() {
        int iU = u(18);
        if (iU < 0) {
            return 0;
        }
        if (iU == 0) {
            return 16;
        }
        return iU;
    }

    public boolean s(int i) {
        int i2 = 0;
        switch (i) {
            case 0:
                break;
            case 1:
                i2 = 1;
                break;
            case 2:
                i2 = 2;
                break;
            case 3:
                i2 = 3;
                break;
            case 4:
                i2 = 12;
                break;
            case 5:
                i2 = 14;
                break;
            case 6:
                i2 = 13;
                break;
            case 7:
                i2 = 15;
                break;
            case 8:
                i2 = 17;
                break;
            case 9:
                i2 = 16;
                break;
            case 10:
                i2 = 18;
                break;
            case 11:
                i2 = 20;
                break;
            case 12:
                i2 = 19;
                break;
            case 13:
                i2 = 21;
                break;
            case 14:
                i2 = 23;
                break;
            case 15:
                i2 = 22;
                break;
            default:
                return false;
        }
        return o(i2);
    }

    public float t(int i, float f2) {
        char c2;
        int i2;
        switch (i) {
            case 36:
                c2 = 0;
                i2 = this.L[i];
                break;
            case 37:
                c2 = '\b';
                i2 = this.L[i];
                break;
            case 38:
                c2 = '\t';
                i2 = this.L[i];
                break;
            default:
                return f2;
        }
        if (i2 != 0) {
            return q.b(i2);
        }
        int i3 = this.L[c2];
        return i3 > 0 ? i3 / a0.j() : f2;
    }

    public int u(int i) {
        if (i >= 0) {
            int[] iArr = this.L;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        return 0;
    }

    public int v(int i, int i2) {
        int iU = u(i);
        return iU != 0 ? iU : i2;
    }

    public boolean w(int i) {
        return this.E.i(i);
    }

    public ArrayList<Integer> x() {
        return this.E.h();
    }

    public int[] y() {
        return this.M;
    }

    public int[] z() {
        int iV = v(20, 202050057);
        int[] iArr = new int[4];
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = iV >>> (i2 * 8);
            if ((i3 & 8) != 0) {
                iArr[i] = i3 & 7;
                i++;
            }
        }
        return iArr;
    }
}
