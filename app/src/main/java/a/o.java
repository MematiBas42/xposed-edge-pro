package a;

import android.net.Uri;
import f.p0;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final b[] f7c = new b[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b[] f9b;

    public o(f.s sVar) {
        this.f8a = Uri.decode(sVar.a());
        int iH = sVar.h();
        if (iH <= 0) {
            this.f9b = f7c;
            return;
        }
        this.f9b = new b[iH];
        for (int i = 0; i < iH; i++) {
            this.f9b[i] = sVar.g();
        }
    }

    public o(String str, b[] bVarArr) {
        this.f8a = str == null ? p0.f() : str;
        this.f9b = bVarArr == null ? f7c : bVarArr;
    }

    public void a(f.s sVar) {
        sVar.c(Uri.encode(this.f8a)).d(this.f9b.length);
        for (b bVar : this.f9b) {
            bVar.y(sVar);
        }
    }
}
