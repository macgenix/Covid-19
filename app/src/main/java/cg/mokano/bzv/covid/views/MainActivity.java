package cg.mokano.bzv.covid.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Statistique;
import cg.mokano.bzv.covid.services.preferences.SharedPreferencesStatutApp;
import cg.mokano.bzv.covid.services.utils.AlerteDialogUtils;
import cg.mokano.bzv.covid.services.utils.FireBaseUtil;
import cg.mokano.bzv.covid.services.utils.UtilsDetectorNetwork;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    CardView cardCovid, cardStat, cardSolidarite, cardGouv, cardBarriere, cardFaq, cardActualite, cardQuiz, cardDiagnostic;
    private static final String POLICE = "tel:117";   //117;
    private static final String POMPIER = "tel:118";  //118;
    private static final String COVID = "tel:3434"; //3434;
    //SharedPreferencesStatutApp statutApp;
    String phoneNumber;
    Toolbar toolbar;
    FloatingActionButton fabUrgence, fabPolice, fabPompier;
    FloatingActionMenu fabMenu;
    CoordinatorLayout layout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    LinearLayout llMonitor;
    TextView tvConfirmer;
    TextView tvGueris;
    TextView tvDeces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        hideToolbar();
        globalStatistique();

        cardClickListenner();

        fabUrgence.setOnClickListener(this);
        fabPolice.setOnClickListener(this);
        fabPompier.setOnClickListener(this);

        SharedPreferencesStatutApp statutApp = new SharedPreferencesStatutApp(this);
        if (statutApp.isLogged()){
            UtilsDetectorNetwork network = new UtilsDetectorNetwork(this);
            if (network.isConnected())
                AlerteDialogUtils.alerteFirstDialog(this);
        }

    }

    void findViewById(){
        toolbar = findViewById(R.id.toolbar);
        fabUrgence = findViewById(R.id.menu_item_urgence);
        fabMenu = findViewById(R.id.fab_menu);
        fabPolice = findViewById(R.id.menu_item_police);
        fabPompier = findViewById(R.id.menu_item_pompier);
        layout = findViewById(R.id.coordinator);
        appBarLayout = findViewById(R.id.app_bar);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        llMonitor = findViewById(R.id.ll_monitor);
        tvConfirmer = findViewById(R.id.tv_confirmer);
        tvGueris = findViewById(R.id.tv_gueris);
        tvDeces = findViewById(R.id.tv_deces);
        cardCovid = findViewById(R.id.card_covid);
        cardStat = findViewById(R.id.card_stat);
        cardSolidarite= findViewById(R.id.card_solidarite);
        cardGouv = findViewById(R.id.card_gouvernement);
        cardBarriere = findViewById(R.id.card_regle);
        cardFaq = findViewById(R.id.card_faq);
        cardActualite = findViewById(R.id.card_actualite);
        cardQuiz = findViewById(R.id.card_quiz);
        cardDiagnostic = findViewById(R.id.card_diagnostic);
    }

    void cardClickListenner(){
        cardCovid.setOnClickListener(this);
        cardStat.setOnClickListener(this);
        cardGouv.setOnClickListener(this);
        cardBarriere.setOnClickListener(this);
        cardFaq.setOnClickListener(this);
        cardSolidarite.setOnClickListener(this);
        cardActualite.setOnClickListener(this);
        cardDiagnostic.setOnClickListener(this);
        cardQuiz.setOnClickListener(this);
    }

    private void globalStatistique(){
        FireBaseUtil.openFbReferences("Stat");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                countGlobalStat(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        FireBaseUtil.mDatabaseReference.addValueEventListener(eventListener);
    }

    void countGlobalStat( DataSnapshot dataSnapshot){
        int gueris = 0;
        int confirmer = 0;
        int deces = 0;

        if (dataSnapshot.exists()){
            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                for (DataSnapshot snap :snapshot.getChildren()){
                    Statistique stat = snap.getValue(Statistique.class);
                    confirmer = confirmer + stat.getConfimer();
                    gueris = gueris + stat.getGueris();
                    deces = deces + stat.getDeces();
                }
            }

            tvConfirmer.setText(String.valueOf(confirmer));
            tvGueris.setText(String.valueOf(gueris));
            tvDeces.setText(String.valueOf(deces));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_covid:
                startIntent(CovidActivity.class);
                break;
            case R.id.card_stat:
                startIntent(StatistiqueActivity.class);
                break;
            case R.id.card_gouvernement:
                startIntent(DeclarationActivity.class);
                break;
            case R.id.card_regle:
                startIntent(ConsigneActivity.class);
                break;
            case R.id.card_solidarite:
                startIntent(FakenewsActivity.class);
                break;
            case R.id.card_faq:
                startIntent(ConseilActivity.class);
                break;
            case R.id.card_actualite:
                startIntent(ActualiteActivity.class);
                break;
            case R.id.card_quiz:
                startIntent(QuizActivity.class);
                break;
            case R.id.card_diagnostic:
                startIntent(StartActivity.class);
                break;
            case R.id.menu_item_urgence:
                phoneNumber = COVID;
                callPhone();
                break;
            case R.id.menu_item_police:
                phoneNumber = POLICE;
                callPhone();
                break;
            case R.id.menu_item_pompier:
                phoneNumber = POMPIER;
                callPhone();
                break;
        }
    }

    void startIntent(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    //la methode appeler lorsqu'on clic sur le bouton appel
    public void callPhone() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE) == true) {
                    explain();
                } else {
                    askForPermission();
                }
            } else {
                appelNoYesPermission();
            }
        } else {
            appelNoYesPermission();
        }
    }

    private void appelNoYesPermission() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(phoneNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                final Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phoneNumber));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }else {
                //expliquer pourquoi nous avons besoin de la permission

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(permissions[0]) == false){
                        displayOptions();
                    }else {
                        explain();
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void explain()
    {
        Snackbar.make(layout, "Cette permission est nécessaire pour appeler", Snackbar.LENGTH_LONG).setAction("Activer", new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //demander l'autorisation
                askForPermission();
            }
        }).show();
    }

    private void askForPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] { Manifest.permission.CALL_PHONE }, 2);
        }
    }

    private void displayOptions()
    {
        Snackbar.make(layout, "Veuillez activer la permission", Snackbar.LENGTH_LONG).setAction("Paramètres", new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                final Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        }).show();
    }

    private void hideToolbar(){
        collapsingToolbarLayout.setTitle("");

        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                    YoYo.with(Techniques.SlideOutUp)
                            .duration(700)
                            .playOn(llMonitor);

                    YoYo.with(Techniques.SlideOutRight)
                            .duration(700)
                            .playOn(fabMenu);

                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    YoYo.with(Techniques.SlideInDown)
                            .duration(700)
                            .playOn(llMonitor);

                    YoYo.with(Techniques.SlideInRight)
                            .duration(700)
                            .playOn(fabMenu);

                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_condition){
            startIntent(ConditionActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
}
