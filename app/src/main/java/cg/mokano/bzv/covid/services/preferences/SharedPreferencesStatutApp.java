package cg.mokano.bzv.covid.services.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MOKANO on 22/01/2017.
 */

public class SharedPreferencesStatutApp {
    private static final String PREF_STATUT_APP = "cg.mokano.bzv.covid.services.preferences.SharedPreferencesStatutApp";
    private static final String PREF_STATUT_APP_ISLOGGED = "cg.mokano.bzv.covid.services.preferences.SharedPreferencesStatutApp.isLogged";
    private Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public SharedPreferencesStatutApp(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_STATUT_APP, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public boolean isLogged(){
        boolean bool = pref.getBoolean(PREF_STATUT_APP_ISLOGGED, false);
        editor.apply();

        return bool;
    }

    public void saveLogged(boolean bool){
        editor.putBoolean(PREF_STATUT_APP_ISLOGGED, bool);
        editor.apply();
    }
}
