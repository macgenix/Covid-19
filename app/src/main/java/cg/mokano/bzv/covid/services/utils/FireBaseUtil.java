package cg.mokano.bzv.covid.services.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FireBaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    public static FirebaseStorage mStorage;
    private static FireBaseUtil mFireBaseUtil;
    public static StorageReference mStorageRef;

    public FireBaseUtil() {
    }

    public static void openFbReferences(String ref){
        if (mFireBaseUtil == null){
            mFireBaseUtil = new FireBaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
        }

        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
        connectStorage();
    }

    public static void connectStorage(){
        mStorage = FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference().child("covid");
    }
}
