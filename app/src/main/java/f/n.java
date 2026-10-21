package f;

import android.net.Uri;
import java.io.File;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f465a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final p f466b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f468d = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final u<String, String> f467c = new u<>();

    public n(String str) {
        this.f465a = str;
        this.f466b = new p(str + "_list");
    }

    private void h() {
        this.f466b.j();
        int size = this.f467c.size();
        for (int i = 0; i < size; i++) {
            this.f466b.c(this.f467c.e(i)).c(this.f467c.k(i)).C();
        }
        this.f466b.n();
        this.f468d = this.f466b.I();
    }

    public String a(String str) {
        String hexString;
        String strEncode = Uri.encode(str);
        f();
        do {
            hexString = Integer.toHexString(p0.r());
        } while (this.f467c.containsKey(hexString));
        this.f466b.G(true).c(hexString).c(strEncode).C().n();
        this.f467c.put(hexString, strEncode);
        this.f468d = this.f466b.I();
        return hexString;
    }

    public String b(String str) {
        if (str == null) {
            return null;
        }
        f();
        String str2 = this.f467c.get(str);
        if (str2 != null) {
            return Uri.decode(str2);
        }
        return null;
    }

    public Set<String> c() {
        f();
        return this.f467c.keySet();
    }

    public void d(String str) {
        if (str == null) {
            return;
        }
        f();
        int iD = this.f467c.d(str);
        if (iD == -1) {
            return;
        }
        this.f467c.g(iD);
        h();
    }

    public void e(String str) {
        if (str == null) {
            return;
        }
        f();
        int iD = this.f467c.d(str);
        if (iD == -1) {
            return;
        }
        this.f467c.h(iD);
        h();
    }

    public void f() {
        long jI = this.f466b.I();
        if (jI <= 0 || jI == this.f468d) {
            return;
        }
        this.f467c.clear();
        if (this.f466b.E()) {
            do {
                this.f467c.put(this.f466b.a(), this.f466b.a());
            } while (this.f466b.t());
        }
        this.f466b.m();
        this.f468d = jI;
    }

    public void g(String str) {
        f();
        if (!this.f467c.containsKey(str)) {
            v.c("not contains: " + str);
            return;
        }
        this.f467c.remove(str);
        new File(this.f465a + str).delete();
        h();
    }

    public void i(String str, String str2) {
        if (str == null) {
            return;
        }
        f();
        if (this.f467c.containsKey(str)) {
            this.f467c.put(str, Uri.encode(str2));
            h();
        }
    }
}
