package pkg_g;

import android.os.Build;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
class v extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ActionMode.Callback f624a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final EditText f625b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final u f626c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private f f627d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Runnable f628e = new a();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (v.this.f627d != null) {
                    v.this.invalidate();
                    v.this.f627d.v(false);
                }
            } catch (Throwable th) {
                pkg_f.v.d(th);
            }
        }
    }

    v(EditText editText, ActionMode.Callback callback) {
        this.f624a = callback;
        this.f625b = editText;
        this.f626c = new u(editText.getContext());
    }

    ActionMode.Callback b() {
        return this.f624a;
    }

    void c(f fVar) {
        this.f627d = fVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        try {
            this.f624a.onDestroyActionMode(this);
        } catch (Throwable th) {
            pkg_f.v.d(th);
        }
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return null;
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return this.f626c;
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return new MenuInflater(this.f625b.getContext());
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return null;
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return null;
    }

    @Override // android.view.ActionMode
    public void hide(long j) {
        if (j == -1) {
            try {
                j = Build.VERSION.SDK_INT >= 23 ? ViewConfiguration.getDefaultActionModeHideDuration() : 2000L;
            } catch (Throwable th) {
                pkg_f.v.d(th);
                return;
            }
        }
        if (j > 3000) {
            j = 3000;
        }
        this.f625b.removeCallbacks(this.f628e);
        if (j <= 0) {
            this.f628e.run();
            return;
        }
        f fVar = this.f627d;
        if (fVar != null) {
            fVar.v(true);
        }
        this.f625b.postDelayed(this.f628e, j);
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f626c.clear();
        this.f624a.onPrepareActionMode(this, this.f626c);
    }

    @Override // android.view.ActionMode
    public void onWindowFocusChanged(boolean z) {
        if (z) {
            return;
        }
        finish();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
    }
}
