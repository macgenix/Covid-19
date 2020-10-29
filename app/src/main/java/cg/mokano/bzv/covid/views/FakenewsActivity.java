package cg.mokano.bzv.covid.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Fakenews;
import cg.mokano.bzv.covid.services.adapters.FakenewsAdapter;
import cg.mokano.bzv.covid.services.interfaces.RecyclerViewOnclickListernner;
import cg.mokano.bzv.covid.services.utils.FireBaseUtil;
import cg.mokano.bzv.covid.services.utils.UploadingDialog;
import id.zelory.compressor.Compressor;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static java.security.AccessController.getContext;

public class FakenewsActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewOnclickListernner {
    FloatingActionButton fb;
    Button btnImport;
    Button btnEnregistrer;
    EditText edtUrl;
    EditText edtDescription;
    TextInputLayout inputUrl;
    TextInputLayout inputDescription;
    ImageView img;
    RecyclerView rv;
    String url;
    Fakenews fakenews;
    List<String> mPaths = null;
    UploadingDialog uploading;
    FakenewsAdapter adapter;
    ArrayList<Fakenews> liste;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fakenews);

        findViewById();
        getToolbar();
        uploading = new UploadingDialog(this);

        liste = new ArrayList<>();
        //Collections.reverse(liste);
        adapter = new FakenewsAdapter(this, liste);
        getRecyclerView();
        loadData();

        fb.setOnClickListener(this);
        adapter.setRecyclerViewOnclickListernner(this);
    }

    //Toolbar
    public void getToolbar(){
        toolbar.setTitle("Traque Fake News");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    void findViewById(){
        fb = findViewById(R.id.fab_fakenews);
        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
    }

    private void loadData() {
        FireBaseUtil.openFbReferences("Fakenews");
        ChildEventListener eventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                setsnatShot(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        FireBaseUtil.mDatabaseReference.addChildEventListener(eventListener);
    }

    private void getRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    private void setsnatShot(DataSnapshot dataSnapshot){
        Fakenews fakenews = dataSnapshot.getValue(Fakenews.class);
        fakenews.setId(dataSnapshot.getKey());
        adapter.addFakenews(fakenews);
    }

    void dialogFakenews(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_fakenews);

        btnImport = dialog.findViewById(R.id.btn_import);
        img = dialog.findViewById(R.id.img_upload);
        btnEnregistrer = dialog.findViewById(R.id.btn_enregistrer);
        edtUrl = dialog.findViewById(R.id.edt_url);
        edtDescription = dialog.findViewById(R.id.edt_description);
        inputUrl = dialog.findViewById(R.id.input_url);
        inputDescription = dialog.findViewById(R.id.input_description);

        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUrl.getText().toString().isEmpty()){
                    Toast.makeText(FakenewsActivity.this, "Veuillez saisir l'url du fake news", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .playOn(inputUrl);
                    return;
                }

                loadActuality();
                dialog.dismiss();
                uploading.uploading();
            }
        });

        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(FakenewsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(FakenewsActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    } else {
                        //Permission valider
                        showImage();
                    }
                }
                showImage();
            }
        });

        dialog.show();
    }

    void showImage(){
        MultiImageSelector.create(FakenewsActivity.this)
                .showCamera(false)
                .single()
                .start(this, 12);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 12){
            if(resultCode == RESULT_OK){
                // Get the result list of select image paths
                try {
                    mPaths = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    loadImage();
                }catch (Exception e){
                    Toast.makeText(this,"Certaine images pèsent plus de 1M", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void loadImage(){
        Glide.with(FakenewsActivity.this).load(new File(mPaths.get(0))).into(img);
    }

    void saveFakenews(String url, long timestamp){
        fakenews = new Fakenews();
        FireBaseUtil.openFbReferences("Fakenews");

        fakenews.setId(FireBaseUtil.mDatabaseReference.push().getKey());
        fakenews.setIsValidation(2);
        fakenews.setTimestamp(timestamp);
        fakenews.setUrlRS(edtUrl.getText().toString());
        fakenews.setDescription(edtDescription.getText().toString());
        fakenews.setUrlFb(url);

        FireBaseUtil.mDatabaseReference.push().setValue(fakenews);

        Toast.makeText(this, "Publication en cours de validation", Toast.LENGTH_SHORT).show();
    }

    private void loadActuality(){
        //final UploadingDialog uploading = new UploadingDialog(this);
        //uploading.uploading();

        Log.i("TAG0", "TAG0");
        if (mPaths.size() > 0){
            Log.i("TAG1", "TAG1");
            try {
                File file = new File(mPaths.get(0));
                Bitmap bitmap = new Compressor(this)
                        .setMaxWidth(320)
                        .setMaxHeight(180)
                        .setQuality(90)
                        .compressToBitmap(file);

                Log.i("TAG11", "TAG11");
                final String path = getPathFile();
                StorageReference storageReference = FireBaseUtil.mStorageRef.child(path);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = storageReference.putBytes(data);

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("TAG2", "TAG2");
                        url = taskSnapshot.getDownloadUrl().toString();

                        saveFakenews(url, taskSnapshot.getMetadata().getCreationTimeMillis());
                        Log.i("TAG3", "TAG3");
                        uploading.stop();
                        Log.i("TAG4", "TAG4");
                    }
                });
            }catch (IOException ex){
                Log.e("TAG", ex.getMessage());
            }
        }else {
            Toast.makeText(this,"Veuillez sélectionner une image", Toast.LENGTH_SHORT).show();
        }
    }

    private String generateNameFileUnique(){
        return UUID.randomUUID().toString()+".jpg";
    }

    private String getPathFile(){
        return ("images/"+generateNameFileUnique());
    }

    @Override
    public void onClick(View v) {
        dialogFakenews();
    }

    @Override
    public void onClickListernner(Object object) {
        Fakenews fakenew = (Fakenews)object;
        Intent intent = new Intent(this, DetailFakenewsActivity.class);
        intent.putExtra("fakenews", fakenew);
        startActivity(intent);
    }
}
