package pkg_f;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class p extends h {
    protected final File E;
    private boolean F = false;

    public p(String str) {
        this.E = new File(str);
    }

    public boolean E() {
        byte[] bArrJ = q.c(this.E.getPath()).j();
        super.i(bArrJ);
        return (bArrJ == null || bArrJ.length == 0) ? false : true;
    }

    @Override // pkg_f.h
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public p j() {
        return G(false);
    }

    public p G(boolean z) {
        this.F = this.E.exists() && z;
        super.j();
        return this;
    }

    public File H() {
        return this.E;
    }

    public long I() {
        return this.E.lastModified();
    }

    protected void J(byte[] bArr, int i) {
        FileOutputStream fileOutputStream = null;
        if (bArr == null) {
            return;
        }
        File parentFile = this.E.getParentFile();
        if (parentFile == null || parentFile.exists() || (parentFile.mkdir() && parentFile.setExecutable(true, false))) {
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(this.E, this.F);
                try {
                    fileOutputStream.write(bArr, 0, i);
                    this.F = true;
                } catch (IOException e2) {
                    fileOutputStream2 = fileOutputStream;
                    v.c(e2.toString());
                    l();
                    fileOutputStream = fileOutputStream2;
                }
            } catch (IOException e3) {
                v.c(e3.toString());
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                    v.d(e4);
                }
            }
        }
    }

    @Override // pkg_f.h
    public void i(byte[] bArr) {
        throw new UnsupportedOperationException("Use beginRead() instead!");
    }

    @Override // pkg_f.h
    public void m() {
        l();
    }

    @Override // pkg_f.h
    public void n() {
        J(p(), q());
        l();
        this.E.setReadable(true, false);
        this.F = false;
    }

    @Override // pkg_f.h
    protected byte[] u(int i) {
        byte[] bArrP = p();
        J(bArrP, q());
        v(0);
        return i <= bArrP.length ? bArrP : new byte[((i + 16384) / 16384) * 16384];
    }
}
