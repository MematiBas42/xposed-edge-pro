package f;

import android.os.Build;
import android.system.Os;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Object f479a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f480b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Field f481c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Field f482d;

    public static void a(File file, File file2) throws Throwable {
        if (file.exists()) {
            if (file.isDirectory()) {
                d(file, file2);
            } else {
                b(file, file2);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0024 A[Catch: IOException -> 0x0027, TRY_LEAVE, TryCatch #0 {IOException -> 0x0027, blocks: (B:16:0x001f, B:18:0x0024), top: B:22:0x001f }] */
    private static void b(File file, File file2) throws Throwable {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    c(fileInputStream2, fileOutputStream);
                    try {
                        fileInputStream2.close();
                        fileOutputStream.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (IOException unused2) {
                            throw th;
                        }
                    } else if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    public static void c(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    private static void d(File file, File file2) throws IOException {
        if (!file2.exists() && !file2.mkdir()) {
            throw new IOException("Failed to make dir: " + file2.getPath());
        }
        String[] list = file.list();
        if (list != null) {
            String path = file.getPath();
            String path2 = file2.getPath();
            for (String str : list) {
                a(new File(path + '/' + str), new File(path2 + '/' + str));
            }
        }
    }

    public static boolean e(File file) {
        return !file.exists() || ((!file.isDirectory() || f(file.listFiles())) && file.delete());
    }

    public static boolean f(File[] fileArr) {
        boolean z = true;
        if (fileArr == null) {
            return true;
        }
        for (File file : fileArr) {
            if (!e(file)) {
                z = false;
            }
        }
        return z;
    }

    public static boolean g(String[] strArr) {
        File[] fileArr = new File[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            fileArr[i] = new File(strArr[i]);
        }
        return f(fileArr);
    }

    public static boolean h(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (!file.setExecutable(true, false)) {
                return false;
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    if (!file2.setReadable(true, false)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void i(File file, File file2) throws Throwable {
        boolean zRenameTo;
        try {
            zRenameTo = file.renameTo(file2);
        } catch (Throwable unused) {
            zRenameTo = false;
        }
        if (zRenameTo) {
            return;
        }
        a(file, file2);
        e(file);
    }

    public static void j(String str, String str2) throws Throwable {
        i(new File(str), new File(str2));
    }

    public static int k(String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            return Os.stat(str).st_mode;
        }
        if (f481c == null) {
            if (f479a == null) {
                f479a = Class.forName("libcore.io.Libcore").getField("os").get(null);
            }
            if (f480b == null) {
                f480b = Class.forName("libcore.io.Os").getMethod("stat", String.class);
            }
            f481c = Class.forName("libcore.io.StructStat").getField("st_mode");
        }
        return f481c.getInt(f480b.invoke(f479a, str));
    }

    public static int l(String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            return Os.stat(str).st_uid;
        }
        if (f482d == null) {
            if (f479a == null) {
                f479a = Class.forName("libcore.io.Libcore").getField("os").get(null);
            }
            if (f480b == null) {
                f480b = Class.forName("libcore.io.Os").getMethod("stat", String.class);
            }
            f482d = Class.forName("libcore.io.StructStat").getField("st_mode");
        }
        return f482d.getInt(f480b.invoke(f479a, str));
    }
}
