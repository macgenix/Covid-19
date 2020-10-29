package cg.mokano.bzv.covid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Quiz;

public class EndQuizActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgQuizz;
    TextView tvTotalQuestion;
    TextView tvBoerep;
    TextView tvMauvaiseRep;
    TextView tvBtnQuiz;
    int boeRep, mauvaiseRep;
    Button btnRecommencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_quiz);

        findViewById();

        boeRep = getIntent().getIntExtra("boeRep", 0);
        mauvaiseRep = getIntent().getIntExtra("mauvaiseRep", 0);

        if (boeRep > 5){
            imgQuizz.setImageResource(R.drawable.ic_joie);
        }else {
            imgQuizz.setImageResource(R.drawable.ic_triste);
        }

        tvTotalQuestion.setText(String.valueOf("Total des questions : 14"));
        tvBoerep.setText(String.valueOf("Bonnes réponses : "+boeRep));
        tvMauvaiseRep.setText(String.valueOf("Mauvaises réponses : "+mauvaiseRep));

        btnRecommencer.setOnClickListener(this);
        tvBtnQuiz.setOnClickListener(this);
    }

    public void findViewById(){
        imgQuizz = findViewById(R.id.imageView);
        tvTotalQuestion = findViewById(R.id.tv_total_question);
        tvBoerep = findViewById(R.id.tv_boe_rep);
        tvMauvaiseRep = findViewById(R.id.tv_mauvaise_rep);
        tvBtnQuiz = findViewById(R.id.tv_quitter);
        btnRecommencer = findViewById(R.id.btn_recommencer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_quitter:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.btn_recommencer:
                startActivity(new Intent(this, QuizActivity.class));
                finish();
                break;
        }
    }
}
