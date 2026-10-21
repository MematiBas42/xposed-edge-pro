package pkg_c;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jozein.xedgepro.R;
import pkg_e.j0;
import pkg_e.y;
import pkg_g.x;

/* JADX INFO: loaded from: classes.dex */
public class r extends j0.b {
    private EditText C;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(View view) {
        pkg_a.b.t(view.getContext(), new pkg_a.b.c3(this.C.getText().toString()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(DialogInterface dialogInterface, int i) {
        q("result", new pkg_a.b.c3(this.C.getText().toString()));
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        CharSequence charSequence = e().getCharSequence("content", "");
        Activity activity = getActivity();
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(1);
        x xVarG = g();
        int i = xVarG.f638f;
        linearLayout.setPadding(i, i, i, i);
        pkg_g.p pVarM = m(f(R.drawable.ic_play));
        pVarM.setOnClickListener(new View.OnClickListener() { // from class: c.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                r.this.w(view);
            }
        });
        int i2 = xVarG.f634b;
        linearLayout.addView(pVarM, i2, i2);
        EditText editText = new EditText(activity);
        this.C = editText;
        r(editText);
        this.C.setInputType(131073);
        this.C.setHint(R.string.speech_hint);
        this.C.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4096)});
        this.C.setText(charSequence);
        this.C.setSelection(charSequence.length());
        linearLayout.addView(this.C);
        TextView textView = new TextView(activity);
        textView.setText(R.string.speech_description);
        linearLayout.addView(textView);
        y.L(xVarG, textView, this.C);
        return new AlertDialog.Builder(activity).setTitle(R.string.action_speech).setView(linearLayout).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: c.p
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                r.this.x(dialogInterface, i3);
            }
        }).setNegativeButton(android.R.string.cancel, j0.b.B).create();
    }

    @Override // pkg_e.j0.b, android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        e().putCharSequence("content", this.C.getText());
    }

    public r y(CharSequence charSequence) {
        e().putCharSequence("content", charSequence);
        return this;
    }
}
