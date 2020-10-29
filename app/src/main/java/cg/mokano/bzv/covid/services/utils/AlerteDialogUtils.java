package cg.mokano.bzv.covid.services.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.services.preferences.SharedPreferencesStatutApp;

public class AlerteDialogUtils {
    public static void alerteDialog(Context context, String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(text)
                .setTitle("Explications");


        builder.setPositiveButton("J'ai compris", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void alerteFirstDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setMessage("L'application à besoin de se connecter sur internet pour synchroniser avec le serveur")
                .setTitle("Informations");


        builder.setPositiveButton("Compris", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferencesStatutApp statutApp = new SharedPreferencesStatutApp(context);
                statutApp.saveLogged(true);
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void alerteAgeDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setMessage("Prenez contact avec un médecin généraliste au moindre doute.\n Cette application n'est pas pour l'instant adaptée aux personnes de moins de 13 ans. En cas de urgence: appelez le 3434")
                .setTitle("Oups!!!");


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferencesStatutApp statutApp = new SharedPreferencesStatutApp(context);
                statutApp.saveLogged(true);
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
