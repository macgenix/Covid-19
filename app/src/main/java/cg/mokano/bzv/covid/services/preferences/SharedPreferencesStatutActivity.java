package cg.mokano.bzv.covid.services.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MOKANO on 22/01/2017.
 */

public class SharedPreferencesStatutActivity {
    private static final String PREF_STATUT_APP = "cg.mokano.bzv.covid.services.preferences.SharedPreferencesStatutActivity";
    private Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public SharedPreferencesStatutActivity(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_STATUT_APP, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public boolean isLoggedRB(){
        boolean bool = pref.getBoolean("KeyActivityRB", false);
        editor.apply();

        return bool;
    }

    public void saveLoggedRB(boolean bool){
        editor.putBoolean("KeyActivityRB", bool);
        editor.apply();
    }

}
