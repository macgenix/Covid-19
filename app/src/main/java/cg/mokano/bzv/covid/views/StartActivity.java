package cg.mokano.bzv.covid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.textfield.TextInputLayout;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.services.preferences.SharedPreferencesInfo;

import static cg.mokano.bzv.covid.services.utils.AlerteDialogUtils.alerteAgeDialog;


public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnStartAuto;
    TextInputLayout inputVille;
    TextInputLayout inputAge;
    EditText edtVille;
    EditText edtQuartier;
    EditText edtSecteurActivite;
    EditText edtAge;
    TextView tvQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById();
        btnStartAuto.setOnClickListener(this);
        tvQuitter.setOnClickListener(this);
    }

    void findViewById(){
        inputVille = findViewById(R.id.text_input_ville);
        inputAge = findViewById(R.id.text_input_age);
        btnStartAuto = findViewById(R.id.btn_continuer);
        edtVille = findViewById(R.id.edt_ville);
        edtAge = findViewById(R.id.edt_age);
        edtQuartier = findViewById(R.id.edt_quartier);
        edtSecteurActivite = findViewById(R.id.edt_profession);
        tvQuitter = findViewById(R.id.tv_quitter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_continuer){
            if (edtVille.getText().toString().isEmpty()){
                Toast.makeText(this, "Veuillez saisir la Ville", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .playOn(inputVille);
                return;
            }

            if (edtAge.getText().toString().isEmpty()){
                Toast.makeText(this, "Veuillez saisir l'âge", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .playOn(inputAge);
                return;
            }

            if (Integer.valueOf(edtAge.getText().toString().trim())< 13){
                dialogInfo();
                return;
            }

            SharedPreferencesInfo preferencesInfo = new SharedPreferencesInfo(this);
            preferencesInfo.saveShareInfo(edtVille.getText().toString(), edtAge.getText().toString(), edtSecteurActivite.getText().toString(), edtQuartier.getText().toString());

            Intent intent = new Intent(this, ActivityDiagnostic.class);
            startActivity(intent);
        }else if (v.getId() == R.id.tv_quitter){
            onBackPressed();
        }
    }

  void dialogInfo(){
      alerteAgeDialog(this);
    }
}
