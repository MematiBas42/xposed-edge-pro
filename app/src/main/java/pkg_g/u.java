package pkg_g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class u implements Menu {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f617a = new a(0, 0, "");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<a> f618b = new ArrayList<>(4);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f619c;

    private final class a implements MenuItem {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f620a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f621b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CharSequence f622c;

        a(int i, int i2, CharSequence charSequence) {
            this.f620a = i;
            this.f621b = i2;
            this.f622c = charSequence;
        }

        @Override // android.view.MenuItem
        public boolean collapseActionView() {
            return false;
        }

        @Override // android.view.MenuItem
        public boolean expandActionView() {
            return false;
        }

        @Override // android.view.MenuItem
        public ActionProvider getActionProvider() {
            return null;
        }

        @Override // android.view.MenuItem
        public View getActionView() {
            return null;
        }

        @Override // android.view.MenuItem
        public char getAlphabeticShortcut() {
            return (char) 0;
        }

        @Override // android.view.MenuItem
        public int getGroupId() {
            return 0;
        }

        @Override // android.view.MenuItem
        public Drawable getIcon() {
            return null;
        }

        @Override // android.view.MenuItem
        public Intent getIntent() {
            return null;
        }

        @Override // android.view.MenuItem
        public int getItemId() {
            return this.f620a;
        }

        @Override // android.view.MenuItem
        public ContextMenu.ContextMenuInfo getMenuInfo() {
            return null;
        }

        @Override // android.view.MenuItem
        public char getNumericShortcut() {
            return (char) 0;
        }

        @Override // android.view.MenuItem
        public int getOrder() {
            return this.f621b;
        }

        @Override // android.view.MenuItem
        public SubMenu getSubMenu() {
            return null;
        }

        @Override // android.view.MenuItem
        public CharSequence getTitle() {
            return this.f622c;
        }

        @Override // android.view.MenuItem
        public CharSequence getTitleCondensed() {
            return null;
        }

        @Override // android.view.MenuItem
        public boolean hasSubMenu() {
            return false;
        }

        @Override // android.view.MenuItem
        public boolean isActionViewExpanded() {
            return false;
        }

        @Override // android.view.MenuItem
        public boolean isCheckable() {
            return false;
        }

        @Override // android.view.MenuItem
        public boolean isChecked() {
            return false;
        }

        @Override // android.view.MenuItem
        public boolean isEnabled() {
            return false;
        }

        @Override // android.view.MenuItem
        public boolean isVisible() {
            return false;
        }

        @Override // android.view.MenuItem
        public MenuItem setActionProvider(ActionProvider actionProvider) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setActionView(int i) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setActionView(View view) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setAlphabeticShortcut(char c2) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setCheckable(boolean z) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setChecked(boolean z) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setEnabled(boolean z) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setIcon(int i) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setIcon(Drawable drawable) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setIntent(Intent intent) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setNumericShortcut(char c2) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setShortcut(char c2, char c3) {
            return this;
        }

        @Override // android.view.MenuItem
        public void setShowAsAction(int i) {
        }

        @Override // android.view.MenuItem
        public MenuItem setShowAsActionFlags(int i) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setTitle(int i) {
            this.f622c = u.this.f619c.getText(i);
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setTitle(CharSequence charSequence) {
            this.f622c = charSequence;
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setTitleCondensed(CharSequence charSequence) {
            return this;
        }

        @Override // android.view.MenuItem
        public MenuItem setVisible(boolean z) {
            return this;
        }
    }

    u(Context context) {
        this.f619c = context;
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return this.f617a;
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return add(i, i2, i3, this.f619c.getText(i4));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        a aVar = new a(i2, i3, charSequence);
        for (int size = this.f618b.size() - 1; size >= 0; size--) {
            if (this.f618b.get(size).f621b <= i3) {
                this.f618b.add(size + 1, aVar);
                return aVar;
            }
        }
        this.f618b.add(0, aVar);
        return aVar;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return this.f617a;
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        return 0;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return null;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return null;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return null;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return null;
    }

    @Override // android.view.Menu
    public void clear() {
        this.f618b.clear();
    }

    @Override // android.view.Menu
    public void close() {
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        for (a aVar : this.f618b) {
            if (aVar.f620a == i) {
                return aVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        if (i < this.f618b.size()) {
            return this.f618b.get(i);
        }
        return null;
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return this.f618b.size() > 0;
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return false;
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return false;
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        for (int size = this.f618b.size() - 1; size >= 0; size--) {
            if (this.f618b.get(size).f620a == i) {
                this.f618b.remove(size);
                return;
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z) {
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
    }

    @Override // android.view.Menu
    public int size() {
        return this.f618b.size();
    }
}
