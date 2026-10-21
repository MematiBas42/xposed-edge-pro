package pkg_d;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import com.jozein.xedgepro.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class y1 extends e {
    private ArrayList<Pair<CharSequence, Uri>> M = null;
    private ArrayList<Pair<CharSequence, Uri>> N = null;
    private ArrayList<Pair<CharSequence, Uri>> O = null;
    private Ringtone P = null;

    class a implements View.OnClickListener {
        final /* synthetic */ int z;

        a(int i) {
            this.z = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            y1.this.I1(this.z);
        }
    }

    private ArrayList<Pair<CharSequence, Uri>> F1(int i) {
        if (i == 0) {
            if (this.M == null) {
                this.M = H1(1);
            }
            return this.M;
        }
        if (i == 1) {
            if (this.N == null) {
                this.N = H1(2);
            }
            return this.N;
        }
        if (i != 2) {
            return null;
        }
        if (this.O == null) {
            this.O = H1(4);
        }
        return this.O;
    }

    private static int G1(int i) {
        if (i == 0) {
            return 2;
        }
        return i == 2 ? 4 : 5;
    }

    private ArrayList<Pair<CharSequence, Uri>> H1(int i) {
        Context contextM0 = M0();
        ArrayList<Pair<CharSequence, Uri>> arrayList = new ArrayList<>();
        arrayList.add(new Pair<>(u(R.string.system_default), RingtoneManager.getDefaultUri(i)));
        RingtoneManager ringtoneManager = new RingtoneManager(contextM0);
        ringtoneManager.setType(i);
        Cursor cursor = ringtoneManager.getCursor();
        while (cursor.moveToNext()) {
            arrayList.add(new Pair<>(cursor.getString(1), ContentUris.withAppendedId(Uri.parse(cursor.getString(2)), cursor.getLong(0))));
        }
        cursor.close();
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I1(int i) {
        ArrayList<Pair<CharSequence, Uri>> arrayListF1 = F1(I0());
        if (arrayListF1 != null) {
            h().putString("uri", ((Uri) arrayListF1.get(i).second).toString());
            N(new pkg_e.z().u(new CharSequence[]{u(R.string.play_once), u(R.string.repeat)}), 1);
        }
    }

    @Override // pkg_e.j
    protected int B0() {
        d0(u(R.string.action_play_sound));
        return 4;
    }

    @Override // pkg_d.e
    protected void B1(Bundle bundle, int i) {
        if (i == 1) {
            int i2 = bundle.getInt("result", -1);
            if (i2 == 0 || i2 == 1) {
                ArrayList<Pair<CharSequence, Uri>> arrayListF1 = F1(I0());
                String string = h().getString("uri");
                if (arrayListF1 == null || string == null) {
                    return;
                }
                C1(new pkg_a.b.y1(string, G1(I0()), i2 == 1));
            }
        }
    }

    @Override // pkg_e.j
    protected boolean N0(int i) {
        return i < 3;
    }

    @Override // pkg_e.j
    protected View h1(int i) {
        if (i == 0) {
            return new pkg_e.j.k(this, u(R.string.ringtone));
        }
        if (i != 1) {
            return i != 2 ? new pkg_e.j.k(this, u(R.string.stop_play_sound)) : new pkg_e.j.k(this, u(R.string.alarm));
        }
        return new pkg_e.j.k(this, u(R.string.notification));
    }

    @Override // pkg_e.j
    protected View i1(int i) {
        Context contextM0 = M0();
        ArrayList<Pair<CharSequence, Uri>> arrayListF1 = F1(I0());
        if (arrayListF1 == null) {
            return new View(contextM0);
        }
        pkg_e.j.g gVar = new pkg_e.j.g((CharSequence) arrayListF1.get(i).first, (CharSequence) null, C0(R.drawable.ic_select));
        gVar.E.setOnClickListener(new a(i));
        return gVar;
    }

    @Override // pkg_e.j
    protected void k1(int i) {
        ArrayList<Pair<CharSequence, Uri>> arrayListF1 = F1(i);
        if (arrayListF1 != null) {
            y1(i, arrayListF1.size());
        } else {
            C1(new pkg_a.b.y1("", 0, false));
        }
    }

    @Override // pkg_e.j
    protected void o1(int i) {
        ArrayList<Pair<CharSequence, Uri>> arrayListF1 = F1(I0());
        if (arrayListF1 != null) {
            Ringtone ringtone = this.P;
            if (ringtone != null) {
                ringtone.stop();
            }
            Ringtone ringtone2 = RingtoneManager.getRingtone(M0(), (Uri) arrayListF1.get(i).second);
            this.P = ringtone2;
            if (ringtone2 != null) {
                ringtone2.setStreamType(G1(I0()));
                this.P.play();
            }
        }
    }

    @Override // pkg_e.j, pkg_e.j0.c, android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Ringtone ringtone = this.P;
        if (ringtone != null) {
            ringtone.stop();
            this.P = null;
        }
    }

    @Override // pkg_e.j
    protected boolean p1(int i) {
        I1(i);
        return true;
    }
}
