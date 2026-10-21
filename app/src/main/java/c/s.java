package c;

import android.os.Bundle;
import com.jozein.xedgepro.R;
import e.y;

/* JADX INFO: loaded from: classes.dex */
public class s extends y {
    public s M(CharSequence charSequence) {
        e().putCharSequence("text", charSequence);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // e.j0.b
    public void o(Bundle bundle) {
        CharSequence charSequence = bundle.getCharSequence("result");
        if (charSequence != null) {
            bundle.remove("result");
            bundle.putParcelable("result", new a.b.u2(charSequence.toString()));
        }
        super.o(bundle);
    }

    @Override // e.j0.b, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            J(k(R.string.action_toast), k(R.string.toast_description), e().getCharSequence("text"), 5, 1024);
        }
    }
}
