package pkg_d;

import android.app.ActionBar;
import android.os.Bundle;
import com.jozein.xedgepro.R;
import com.jozein.xedgepro.ui.ActivityPerformAction;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends pkg_e.j {
    /* JADX WARN: Code duplicated, block: B:21:0x002a A[FALL_THROUGH] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1092)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:419)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:31)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:399)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:31)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:21)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    private int[] B1(int i, boolean z) {
        java.util.ArrayList<Integer> arrayList = new java.util.ArrayList<>();
        if (z) {
            boolean canEdit = false;
            if (i == 54 || i == 72 || i == 76 || i == 88 || i == 105 || i == 100 || i == 101) {
                canEdit = true;
            } else if ((i >= 61 && i <= 63) || (i >= 107 && i <= 111)) {
                canEdit = true;
            }
            if (canEdit) {
                arrayList.add(Integer.valueOf(R.string.edit_action));
            }
            if (pkg_f.l.CONST_IS_PRO && (this instanceof pkg_d.d.a)) {
                arrayList.add(Integer.valueOf(R.string.edit_icon_and_label));
            }
        }
        arrayList.add(Integer.valueOf(android.R.string.copy));
        if (z) {
            pkg_e.j0 j0Var = r();
            pkg_a.b bVar = (j0Var != null && j0Var.o() != null) ? (pkg_a.b) j0Var.o().getParcelable("clipboard") : null;
            if (bVar != null) {
                arrayList.add(Integer.valueOf(android.R.string.paste));
            }
        }
        if (i != 0 && i != 1 && i != 51 && i != 68 && i != 106 && i != 107) {
            arrayList.add(Integer.valueOf(R.string.perform));
            arrayList.add(Integer.valueOf(R.string.put_on_launcher));
            arrayList.add(Integer.valueOf(R.string.export_as_command));
        }
        arrayList.add(Integer.valueOf(R.string.action_info));
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = arrayList.get(i2).intValue();
        }
        return iArr;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x010c  */
    private void D1(pkg_a.b bVar, int i) {
        pkg_e.j0.c cVarK1;
        pkg_e.j0.b bVarU = null;
        int i2 = bVar.z;
        if (i2 == 54) {
            pkg_e.y yVar = new pkg_e.y();
            yVar.J(u(R.string.enter_duration), u(R.string.time_in_milliseconds), Integer.toString(((pkg_a.b.g0) bVar).I), 1, 9);
            N(yVar, 65538);
            return;
        }
        if (i2 != 72) {
            if (i2 == 76) {
                bVarU = new pkg_e.m().u(u(R.string.remove_activity_params));
                i = 65536;
            } else if (i2 == 88) {
                pkg_a.b.p2 p2Var = (pkg_a.b.p2) bVar;
                bVarU = new pkg_c.v().A(p2Var.I, p2Var.J, p2Var.K);
            } else if (i2 == 105) {
                pkg_a.b.z0 z0Var = (pkg_a.b.z0) bVar;
                bVarU = new pkg_c.k().D(z0Var.I, z0Var.J, z0Var.K);
            } else if (i2 == 114) {
                bVarU = new pkg_e.m().u(u(R.string.remove_activity_params));
                i = 65536;
            } else if (i2 == 100) {
                pkg_a.b.a0 a0Var = (pkg_a.b.a0) bVar;
                bVarU = new pkg_c.j().E(a0Var.K, a0Var.I, a0Var.J);
            } else {
                if (i2 != 101) {
                    switch (i2) {
                        case 61:
                            pkg_a.b.b0 b0Var = (pkg_a.b.b0) bVar;
                            bVarU = new pkg_c.j().E(b0Var.J, b0Var.I, false);
                            break;
                        case 62:
                            pkg_a.b.d0 d0Var = (pkg_a.b.d0) bVar;
                            cVarK1 = new o().K1(d0Var.I, d0Var.J, d0Var.K);
                            break;
                        case 63:
                            pkg_a.b[] bVarArr = ((pkg_a.b.q1) bVar).I;
                            if (bVarArr != null) {
                                int length = bVarArr.length;
                                pkg_a.b[] bVarArr2 = new pkg_a.b[length];
                                System.arraycopy(bVarArr, 0, bVarArr2, 0, length);
                                cVarK1 = new s0().S1(bVarArr2, true);
                            }
                            break;
                        default:
                            switch (i2) {
                                case 107:
                                    z1 z1Var = new z1();
                                    z1Var.L1(((pkg_a.b.h3) bVar).I);
                                    z1Var.b0(h().getCharSequence("next_sub_title"));
                                    P(z1Var, i);
                                    break;
                                case 108:
                                    bVarU = new pkg_c.o().M(((pkg_a.b.b1) bVar).I);
                                    break;
                                case 109:
                                    pkg_a.b.u1 u1Var = (pkg_a.b.u1) bVar;
                                    bVarU = new pkg_c.m().z(u1Var.J, u1Var.I);
                                    break;
                                case 110:
                                    bVarU = new pkg_c.r().y(((pkg_a.b.c3) bVar).I);
                                    break;
                                case 111:
                                    bVarU = new pkg_c.s().M(((pkg_a.b.u2) bVar).I);
                                    break;
                            }
                            break;
                    }
                    return;
                }
                pkg_a.b.f2 f2Var = (pkg_a.b.f2) bVar;
                cVarK1 = new p1().M1(f2Var.I, f2Var.J);
            }
            N(bVarU, i);
            return;
        }
        Bundle bundleH = h();
        cVarK1 = new t().K1((pkg_a.b.d) bVar, bundleH.getInt("flag"));
        i = bundleH.getInt("code");
        P(cVarK1, i);
    }

    protected abstract void C1(Bundle bundle, int i);

    protected void E1(int i, int i2) {
        ActionBar actionBar = getActivity().getActionBar();
        P(new pkg_d.d().H1(i, actionBar != null ? actionBar.getTitle() : null), i2);
    }

    protected void F1(pkg_a.b bVar, int i, int i2) {
        G1(bVar, i, i2, true);
    }

    protected void G1(pkg_a.b bVar, int i, int i2, boolean z) {
        Bundle bundleH = h();
        bundleH.putParcelable("action", bVar);
        bundleH.putInt("code", i);
        bundleH.putInt("flag", i2);
        pkg_a.b bVar2 = (pkg_a.b) r().o().getParcelable("clipboard");
        int[] iArrB1 = B1(bVar.z, z);
        CharSequence[] charSequenceArr = new CharSequence[iArrB1.length];
        for (int i3 = 0; i3 < iArrB1.length; i3++) {
            charSequenceArr[i3] = u(iArrB1[i3]);
            if (iArrB1[i3] == 17039371 && bVar2 != null) {
                charSequenceArr[i3] = ((Object) charSequenceArr[i3]) + "(" + ((Object) bVar2.n(getActivity())) + ')';
            }
        }
        bundleH.putIntArray("items", iArrB1);
        N(new pkg_e.z().u(charSequenceArr), 65535);
    }

    protected void H1(CharSequence charSequence) {
        h().putCharSequence("next_sub_title", charSequence);
    }

    @Override // pkg_e.j0.c
    protected final void J(Bundle bundle, int i) {
        Bundle bundleH;
        int[] intArray;
        pkg_e.j0.b bVarW;
        if (bundle == null) {
            return;
        }
        String str = null;
        pkg_a.b bVarB = null;
        str = null;
        switch (i) {
            case 65535:
                int i2 = bundle.getInt("result", -1);
                if (i2 < 0 || (intArray = (bundleH = h()).getIntArray("items")) == null) {
                    return;
                }
                int i3 = intArray[i2];
                if (i3 == 17039371) {
                    pkg_a.b bVar = (pkg_a.b) r().o().getParcelable("clipboard");
                    if (bVar == null) {
                        return;
                    }
                    bundleH.putParcelable("result", bVar);
                    C1(bundleH, bundleH.getInt("code"));
                    return;
                }
                pkg_a.b dVar = (pkg_a.b) bundleH.getParcelable("action");
                if (dVar == null) {
                    return;
                }
                switch (i3) {
                    case android.R.string.copy:
                        r().o().putParcelable("clipboard", dVar);
                        break;
                    case R.string.action_info /* 2131099690 */:
                        bVarW = new pkg_c.b().w(dVar);
                        N(bVarW, 65537);
                        break;
                    case R.string.edit_icon_and_label /* 2131099887 */:
                        if (dVar.z != 72) {
                            dVar = new pkg_a.b.d(dVar);
                            break;
                        }
                    case R.string.edit_action /* 2131099886 */:
                        D1(dVar, bundleH.getInt("code"));
                        break;
                    case R.string.export_as_command /* 2131099899 */:
                        try {
                            pkg_a.z zVarH = g().h();
                            if (zVarH == null || !zVarH.o(32)) {
                                str = "#" + ((Object) u(R.string.perform_by_broadcast_not_allowed));
                            }
                        } catch (Throwable th) {
                            pkg_f.v.d(th);
                        }
                        bVarW = new pkg_e.q().w(dVar.n(f()), pkg_a.b.g(dVar), str);
                        N(bVarW, 65537);
                        break;
                    case R.string.perform /* 2131100015 */:
                        ActivityPerformAction.a(f(), dVar);
                        break;
                    case R.string.put_on_launcher /* 2131100029 */:
                        pkg_f.l0.i(f(), dVar);
                        break;
                }
                return;
            case 65536:
                bundle = h();
                pkg_a.b bVar2 = (pkg_a.b) bundle.getParcelable("action");
                if (bVar2 instanceof pkg_a.b.g) {
                    bVarB = ((pkg_a.b.g) bVar2).B();
                } else if (bVar2 instanceof pkg_a.b.h) {
                    bVarB = ((pkg_a.b.h) bVar2).B();
                }
                if (bVarB == null) {
                    return;
                }
                bundle.putParcelable("result", bVarB);
                i = bundle.getInt("code");
                break;
            case 65537:
                return;
            case 65538:
                CharSequence charSequence = bundle.getCharSequence("result");
                if (charSequence != null) {
                    try {
                        int i4 = Integer.parseInt(charSequence.toString());
                        Bundle bundleH2 = h();
                        bundleH2.putParcelable("result", new pkg_a.b.g0(i4));
                        C1(bundleH2, bundleH2.getInt("code"));
                        return;
                    } catch (NumberFormatException e2) {
                        pkg_f.v.d(e2);
                        return;
                    }
                }
                return;
        }
        C1(bundle, i);
    }
}
