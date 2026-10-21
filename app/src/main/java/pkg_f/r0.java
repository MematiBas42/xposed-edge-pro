package pkg_f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class r0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f483a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f484b;

    public r0(File file) {
        this.f483a = new byte[51200];
        this.f484b = file;
    }

    public r0(String str) {
        this.f483a = new byte[51200];
        this.f484b = new File(str);
    }

    private void c(File file) {
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            c(parentFile);
            parentFile.mkdir();
        }
        file.mkdir();
    }

    private void g(File file, ZipOutputStream zipOutputStream, String str) throws Throwable {
        File[] fileArrListFiles = file.listFiles();
        String str2 = str + file.getName() + '/';
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            try {
                zipOutputStream.putNextEntry(new ZipEntry(str2));
                return;
            } finally {
                zipOutputStream.closeEntry();
            }
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                g(file2, zipOutputStream, str2);
            } else {
                h(file2, zipOutputStream, str2);
            }
        }
    }

    private void h(File file, ZipOutputStream zipOutputStream, String str) throws Throwable {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
            while (true) {
                int i = fileInputStream.read(this.f483a);
                if (i <= 0) {
                    zipOutputStream.closeEntry();
                    return;
                }
                zipOutputStream.write(this.f483a, 0, i);
            }
        }
    }

    public File a() {
        return this.f484b;
    }

    public boolean b(String str, String str2) {
        try {
            Enumeration<? extends ZipEntry> enumerationEntries = new ZipFile(this.f484b).entries();
            while (enumerationEntries.hasMoreElements()) {
                String name = enumerationEntries.nextElement().getName();
                if (name.equals(str) || name.equals(str2)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            v.d(th);
            return false;
        }
    }

    public void d(String str) throws IOException {
        try (ZipFile zipFile = new ZipFile(this.f484b)) {
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                File file = new File(str + zipEntryNextElement.getName());
                if (!file.exists()) {
                    File parentFile = file.getParentFile();
                    if (parentFile != null && !parentFile.exists()) {
                        c(parentFile);
                    }
                    if (zipEntryNextElement.getName().endsWith("/")) {
                        file.mkdir();
                        continue;
                    } else {
                        file.createNewFile();
                    }
                }
                try (InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
                     FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    while (true) {
                        int i = inputStream.read(this.f483a);
                        if (i <= 0) {
                            break;
                        }
                        fileOutputStream.write(this.f483a, 0, i);
                    }
                }
            }
        }
    }

    public void e(File[] fileArr) throws Throwable {
        if (!this.f484b.exists() && !this.f484b.createNewFile()) {
            throw new IOException("File creation failed!");
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(this.f484b));
        try {
            for (File file : fileArr) {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        g(file, zipOutputStream, "");
                    } else {
                        h(file, zipOutputStream, "");
                    }
                }
            }
        } finally {
            zipOutputStream.close();
        }
    }

    public void f(String[] strArr) throws IOException {
        File[] fileArr = new File[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            fileArr[i] = new File(strArr[i]);
        }
        try {
            e(fileArr);
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }
}
