package cg.mokano.bzv.covid.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.AutoDiagnostic;
import cg.mokano.bzv.covid.models.QuestionReponse;

import static cg.mokano.bzv.covid.services.datas.BDAutoDiagnostic.db;
import static cg.mokano.bzv.covid.services.utils.AlerteDialogUtils.alerteDialog;
import static cg.mokano.bzv.covid.services.utils.GlideAppli.glideLocal;

public class ActivityDiagnostic extends AppCompatActivity implements View.OnClickListener {
    private static final int MAX_VALUE_PROGRESS = 20;
    private static final int TOTAL_QUESTION = 21;
    private int questRandom = 0;
    ProgressBar progressBar;
    private ArrayList<AutoDiagnostic> quesList;
    private ArrayList<QuestionReponse> arraySaveQst;
    //LinearLayout llTop, llBtn;
    TextView tvQuestion;
    TextView tvTotalQuestion;
    TextView tvCountQuestion;
    Button btnSuivant;
    ImageButton btnImgPrec;
    ImageButton btnImgAide;
    ImageView imgQuestion;
    RadioButton rbOui, rbNon;
    RadioGroup rg;
    String ville ="";
    int age = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_diagnostic);

        findViewByid();

        quesList = db();
        arraySaveQst = new ArrayList<>();

        progressBar.setMax(MAX_VALUE_PROGRESS);
        tvTotalQuestion.setText(String.valueOf(quesList.size()));

        btnSuivant.setOnClickListener(this);
        btnImgPrec.setOnClickListener(this);
        btnImgAide.setOnClickListener(this);

        nextQuestion();
        //progressBar.setProgress(questRandom);
    }

    void findViewByid(){
        progressBar = findViewById(R.id.progress);
        btnSuivant = findViewById(R.id.btn_suivant);
        tvQuestion = findViewById(R.id.tv_question);
        tvTotalQuestion = findViewById(R.id.tv_total_question);
        tvCountQuestion = findViewById(R.id.tv_count_question);
        imgQuestion = findViewById(R.id.img_question);
        btnImgPrec = findViewById(R.id.img_btn_prec);
        rbOui = findViewById(R.id.rb_oui);
        rbNon = findViewById(R.id.rb_non);
        rg = findViewById(R.id.radioGroup);
        btnImgAide = findViewById(R.id.img_btn_aide);
    }

    String getTextRb(){
        int selectedId = rg.getCheckedRadioButtonId();
        RadioButton rb = findViewById(selectedId);

        return rb.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_suivant:
                if (rg.getCheckedRadioButtonId() != -1){
                    if (questRandom != TOTAL_QUESTION-1){
                        questRandom++;
                        nextQuestion();

                        if (questRandom !=20)
                            saveQuestion(quesList.get(questRandom).getId(),quesList.get(questRandom).getQst(), getTextRb());
                    }
                }else {
                    Toast.makeText(this, "Quelle est votre réponse", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_btn_prec:
                if (questRandom > 0){
                    questRandom--;
                    nextQuestion();

                    saveQuestion(questRandom);
                }
                break;
            case R.id.img_btn_aide:
                if (!quesList.get(questRandom).getExplication1().equals(""))
                    dialogInfo(quesList.get(questRandom).getExplication1(), quesList.get(questRandom).getExplication2());
                break;
        }

        progressBar.setProgress(questRandom);
        rg.clearCheck();
    }

    void dialogInfo(String prop1, String prop2){
        String text ;
        if (prop2.equals("")) text = prop1;
        else text = prop1 +"\n\n"+prop2;

        alerteDialog(this, text);
    }

    void nextQuestion(){
        if (questRandom == TOTAL_QUESTION-1){
            progressBar.setProgress(questRandom+1);
            Intent intent = new Intent(this, FinAutodiagnosticActivity.class);
            intent.putParcelableArrayListExtra("liste", arraySaveQst);
            startActivity(intent);
        }else {
            demarreQuestion();
        }
    }

    private void demarreQuestion() {
        animTextView(quesList.get(questRandom).getQst());
        animImageView(quesList.get(questRandom).getImage());
        tvCountQuestion.setText(String.valueOf(questRandom+1));
    }

    void saveQuestion(int position){
        saveQuestion(1,"null", "null", position, 0);
    }

    void saveQuestion(int id, String qst, String rsp){
        saveQuestion(id, qst, rsp, 0, 1);
    }

    void saveQuestion(int id, String qst, String rsp,int position, int statut){
        if (statut == 0){
            if (arraySaveQst.size()>0){
                arraySaveQst.remove(position);
            }
        }else {
            QuestionReponse qstResp = new QuestionReponse();
            qstResp.setQuestion(qst);
            qstResp.setReponse(rsp);
            arraySaveQst.add(qstResp);
        }
    }

    void animTextView(String texte){
        tvQuestion.setText(texte);
        YoYo.with(Techniques.ZoomIn)
                .duration(700)
                .playOn(tvQuestion);
    }

    void animImageView(int url){
        glideLocal(this, url, imgQuestion);
        YoYo.with(Techniques.ZoomIn)
                .duration(700)
                .playOn(imgQuestion);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (questRandom == 20)
            questRandom = questRandom-1;
    }
}
