package cg.mokano.bzv.covid.services.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import cg.mokano.bzv.covid.R;


public class UploadingDialog {
    Dialog dialog;

    public UploadingDialog(Context context) {
        dialog = new Dialog(context);
    }

    public void uploading(){
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.uploading);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        dialog.show();
    }

    public void stop(){
        dialog.dismiss();
    }

    public void progresse(double time){

    }
}
