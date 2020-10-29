package cg.mokano.bzv.covid.services.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesInfo {
    private static final String PREF_PATIENT = "cg.mokano.bzv.covid.services.preferences.SharedPreferencesInfo";
    private Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public SharedPreferencesInfo(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_PATIENT, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void saveShareInfo(String valueVille, String valueAge, String valueProfession, String valueQuartier){
        editor.putString("keyVille",valueVille);
        editor.putString("keyAge",valueAge);
        editor.putString("keyProfession",valueProfession);
        editor.putString("keyQuartier",valueQuartier);
        editor.apply();
    }

    public void updateSharedProfession(String valueVille){
        editor.putString("keyProfession",valueVille);
        editor.commit();
    }

    public void updateSharedQuartier(String valueVille){
        editor.putString("keyQuartier",valueVille);
        editor.commit();
    }

    public void updateSharedVille(String valueVille){
        editor.putString("keyVille",valueVille);
        editor.commit();
    }

    public void updateSharedAge(String valueAge){
        editor.putString("keyAge", valueAge);
        editor.commit();
    }

    public void deleteSharedProfession(){
        editor.remove("keyProfession");
    }

    public void deleteSharedQuartier(){
        editor.remove("keyQuartier");
    }

    public void deleteSharedVille(){
        editor.remove("keyVille");
    }

    public void deleteSharedAge(){
        editor.remove("keyAge");
    }

    public String getSharedProfession(){
        return pref.getString("keyProfession", null);
    }

    public String getSharedQuartier(){
        return pref.getString("keyQuartier", null);
    }

    public String getSharedVille(){
        return pref.getString("keyVille", null);
    }

    public int getSharedAge(){
        return Integer.valueOf(pref.getString("keyAge",null));
    }
}
