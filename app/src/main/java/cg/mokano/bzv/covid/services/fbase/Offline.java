package cg.mokano.bzv.covid.services.fbase;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Offline extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference ref  = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);
    }
}
