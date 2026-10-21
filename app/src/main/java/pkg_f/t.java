package pkg_f;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f485a;

    public t(int i) {
        this.f485a = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && t.class == obj.getClass() && this.f485a == ((t) obj).f485a;
    }

    public int hashCode() {
        return this.f485a;
    }

    public String toString() {
        return Integer.toString(this.f485a);
    }
}
