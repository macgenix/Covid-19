package cg.mokano.bzv.covid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Formulaire;
import cg.mokano.bzv.covid.models.QuestionReponse;
import cg.mokano.bzv.covid.services.preferences.SharedPreferencesInfo;
import cg.mokano.bzv.covid.services.utils.FireBaseUtil;


public class FinAutodiagnosticActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEnvoyerMedecin;
    Button btnNePasEnvoyer;
    TextView tvMessage;
    TextView tvVille;
    int count = 0;
    ArrayList<QuestionReponse> allQuestion;
    SharedPreferencesInfo preferencesInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_autodiagnostic);

        findViewById();
        preferencesInfo = new SharedPreferencesInfo(this);

        if (getIntent().getParcelableArrayListExtra("liste") != null){
            allQuestion = getIntent().getParcelableArrayListExtra("liste");
        }

        for (QuestionReponse qst : allQuestion){
            if (qst.getReponse().equals("Oui")){
                count++;
            }
        }

        tvVille.setText(preferencesInfo.getSharedVille());
        messageTest(count);


        btnEnvoyerMedecin.setOnClickListener(this);
        btnNePasEnvoyer.setOnClickListener(this);
    }

    void findViewById(){
        btnEnvoyerMedecin = findViewById(R.id.btn_call);
        btnNePasEnvoyer = findViewById(R.id.btn_quitter);
        tvMessage = findViewById(R.id.tv_text);
        tvVille = findViewById(R.id.tv_ville);
    }

    void messageTest(int resultat){
        String texte;
        if (resultat > 1){
            texte = "Votre situation peut relever d'un COVID-19. Vos symptômes indiquent qu'un avis médical est nécessaire.";
        }else {
            texte = "Votre état ne semble pas préoccupant ou ne relève probablement pas du Covid-19.";
        }

        tvMessage.setText(texte);
    }

    void pushFormulaire(){
        FireBaseUtil.openFbReferences("Formulaire");
        Formulaire formulaire = new Formulaire();
        formulaire.setId(FireBaseUtil.mDatabaseReference.push().getKey());
        formulaire.setLatitude(0);
        formulaire.setLongitude(0);
        formulaire.setVille(preferencesInfo.getSharedVille());
        formulaire.setAge(preferencesInfo.getSharedAge());
        formulaire.setProfession(preferencesInfo.getSharedProfession());
        formulaire.setQuartier(preferencesInfo.getSharedQuartier());
        formulaire.setAutodiagnostics(allQuestion);

        FireBaseUtil.mDatabaseReference.child(preferencesInfo.getSharedVille()).push().setValue(formulaire);
        Toast.makeText(this, "Enregistrement éffectuer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_call:
                pushFormulaire();
                break;
            case R.id.btn_quitter:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
