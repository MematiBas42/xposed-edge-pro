package pkg_f;

import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public class d0 implements s {
    private final Parcel z;

    public d0(Parcel parcel) {
        this.z = parcel;
    }

    @Override // pkg_f.s
    public String a() {
        return this.z.readString();
    }

    @Override // pkg_f.s
    public String e() {
        return a();
    }

    @Override // pkg_f.s
    public pkg_a.b g() {
        return pkg_a.b.s(this);
    }

    @Override // pkg_f.s
    public int h() {
        return this.z.readInt();
    }

    public boolean i() {
        return true;
    }

    public d0 j() {
        return this;
    }

    public void k() {
    }

    public void l() {
    }

    @Override // pkg_f.s
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public d0 d(int i) {
        this.z.writeInt(i);
        return this;
    }

    @Override // pkg_f.s
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public d0 f(pkg_a.b bVar) {
        bVar.y(this);
        return this;
    }

    @Override // pkg_f.s
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public d0 c(String str) {
        this.z.writeString(str);
        return this;
    }

    @Override // pkg_f.s
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public d0 b(String str) {
        return c(str);
    }
}
