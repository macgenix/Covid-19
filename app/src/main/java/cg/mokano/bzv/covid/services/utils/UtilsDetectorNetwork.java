package cg.mokano.bzv.covid.services.utils;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by MOKANO on 17/08/2016.
 */
public class UtilsDetectorNetwork {
    Context context;
    ConnectivityManager cm;
    NetworkInfo info;

    public UtilsDetectorNetwork(Context context) {
        this.context = context;
    }

    public boolean isConnected(){
        cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (cm != null){
            info = cm.getActiveNetworkInfo();
            if (info != null){
                if (info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
}
