package cg.mokano.bzv.covid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Quiz;

import static cg.mokano.bzv.covid.services.datas.BDQuiz.db;

public class QuizActivity extends AppCompatActivity  implements View.OnClickListener {
    TextView tvQuestion;
    Button btnA;
    Button btnB;
    Button btnC;
    TextView tvReuissie;
    TextView tvEchec;
    TextView tvPointReuissi;
    TextView tvPointEchouer;
    TextView tvTotalQuestion;
    boolean statusQuestion;
    private MediaPlayer player;
    ArrayList<Quiz> quesList;
    private int questRandom = 0;
    ArrayList<Integer> questionDejaPoser = new ArrayList<>();
    private int totalQuestionRepondue = 0;
    private int boeQuestion = 0;
    private int mauvaiseQuestion = 0;
    private Random random = new Random();
    private CountDownTimer downTimer;
    private static int TOTAL_QUESTION = 11;
    TextView tvTimer;
    private boolean addpoint = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        findViewById();
        quesList = db();

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);

        nextQuestion();
    }

    public void findViewById(){
        btnA = findViewById(R.id.btn1);
        btnB = findViewById(R.id.btn2);
        btnC = findViewById(R.id.btn3);
        tvQuestion = findViewById(R.id.tv_question);
        tvTimer = findViewById(R.id.tv_timer);
        tvReuissie = findViewById(R.id.tv_reuissie);
        tvEchec = findViewById(R.id.tv_echec);
        tvPointEchouer = findViewById(R.id.tv_point_echec);
        tvPointReuissi = findViewById(R.id.tv_point_reuissie);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                checkReponse(btnA);
                break;
            case R.id.btn2:
                checkReponse(btnB);
                break;
            case R.id.btn3:
                checkReponse(btnC);
                break;
        }
    }

    public void nextQuestion(){
        cancelTimer();

        randomQuest(quesList);

        if (questionDejaPoser.size() == TOTAL_QUESTION){
            Intent intent = new Intent(this, EndQuizActivity.class);
            intent.putExtra("boeRep", boeQuestion);
            intent.putExtra("mauvaiseRep", mauvaiseQuestion);
            startActivity(intent);
            finish();
        }else {
            demarreQuestion();
        }
    }

    private int randomQuest(ArrayList<Quiz> list){
        try{
            if (questionDejaPoser.size() <= list.size()){
                do {
                    questRandom = random.nextInt(list.size());
                }while (questionDejaPoser.contains(questRandom));

                questionDejaPoser.add(questRandom);
            }
        }catch (Exception ex){
            Log.e("Exception", "Random Quest");
        }
        statusQuestion = true;
        return questRandom;
    }

    public void cancelTimer(){
        if (downTimer != null){
            downTimer.cancel();
            downTimer = null;
        }
    }

    public void demarreQuestion(){
        addpoint = true;

        tvQuestion.setText(quesList.get(questRandom).getQuestion());
        AffichageRandomPropositionReponse();

        chrono(20);
        tvTimer.setTextColor(Color.BLACK);

        //decompter le nombre de question
    }

    public void AffichageRandomPropositionReponse(){
        btnA.setText(quesList.get(questRandom).getRep1());
        btnB.setText(quesList.get(questRandom).getRep2());
        btnC.setText(quesList.get(questRandom).getRep3());

        initBtn();
    }

    private void initBtn(){
        btnA.setBackgroundResource(R.drawable.btn_off);
        btnB.setBackgroundResource(R.drawable.btn_off);
        btnC.setBackgroundResource(R.drawable.btn_off);
        btnA.setClickable(true);
        btnB.setClickable(true);
        btnC.setClickable(true);
    }

    private void chrono(int duree){
        downTimer = new CountDownTimer(duree * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                tvTimer.setText("0");
                tvTimer.setTextColor(Color.argb(255, 161, 5, 5));
                addpoint = false;

                playSound(R.raw.loose);

                if (btnA.getText().toString().equals(quesList.get(questRandom).getBoeRep())){
                    btnA.setBackgroundResource(R.drawable.btn_on);
                }else if (btnB.getText().toString().equals(quesList.get(questRandom).getBoeRep())){
                    btnB.setBackgroundResource(R.drawable.btn_on);
                }else if (btnC.getText().toString().equals(quesList.get(questRandom).getBoeRep())){
                    btnC.setBackgroundResource(R.drawable.btn_on);
                }
            }
        };
        downTimer.start();
    }

    public void playSound(int sound){
        player = MediaPlayer.create(this, sound);
        player.start();
    }

    private String pointS(int nbrePoint){
        if (nbrePoint >0)
            return "points";
        else
            return "point";
    }

    private void checkReponse(Button button){
        try {
            if (button.getText().toString().trim().equals(quesList.get(questRandom).getBoeRep())) {
                //afficheur des point code ici
                if (addpoint){
                    playSound(R.raw.lifeup);
                    boeQuestion = boeQuestion + 1;

                    //Affiche les questions reuissie
                    tvReuissie.setText(String.valueOf(boeQuestion));
                    tvPointReuissi.setText(pointS(boeQuestion));
                    statusQuestion = false;
                }else {
                    playSound(R.raw.nothing);
                }
                nextQuestion();
            }else {
                addpoint = false;
                playSound(R.raw.loose);
                button.setBackgroundResource(R.drawable.btn_erreur);

                if (testerBonneReponse(btnA)){
                    btnB.setClickable(false);
                    btnC.setClickable(false);
                }else if (testerBonneReponse(btnB)){
                    btnA.setClickable(false);
                    btnC.setClickable(false);
                }else if (testerBonneReponse(btnC)){
                    btnB.setClickable(false);
                    btnA.setClickable(false);
                }
                //compter le nombre des mauvaises réponses
                totalQuestionRepondue = totalQuestionRepondue + 1;

                //Mauvaise question
                if (statusQuestion){
                    mauvaiseQuestion = mauvaiseQuestion + 1;

                    tvEchec.setText(String.valueOf(mauvaiseQuestion));
                    tvPointEchouer.setText(pointS(mauvaiseQuestion));
                    statusQuestion = false;
                }

            }
        }catch (Exception ex){
            Log.e("Exception", ex.getMessage());
        }
    }

    private boolean testerBonneReponse(Button button){
        boolean bool = false;
        if (button.getText().toString().trim().equals(quesList.get(questRandom).getBoeRep())){
            button.setBackgroundResource(R.drawable.btn_on);
            bool = true;
        }
        return bool;
    }

    @Override
    public void onPause() {
        cancelTimer();
        //downTimer.cancel();
        super.onPause();
    }

    @Override
    public void onResume() {
        downTimer.start();
        super.onResume();
    }
}
