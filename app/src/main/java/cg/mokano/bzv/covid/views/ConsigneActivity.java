package cg.mokano.bzv.covid.views;

import android.content.Intent;
import android.os.Bundle;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.widget.LinearLayout;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.services.preferences.SharedPreferencesStatutActivity;
import cg.mokano.bzv.covid.services.preferences.SharedPreferencesStatutApp;
import cg.mokano.bzv.covid.services.utils.ExpandAndCollapseViewUtil;


public class ConsigneActivity extends AppCompatActivity implements View.OnClickListener {
    CardView cardView1, cardView2, cardView3, cardView4, cardView5;
    private LinearLayout llExpand1, llExpand2, llExpand3, llExpand4, llExpand5;
    private static final int DURATION = 700;
    Toolbar toolbar;
    SharedPreferencesStatutActivity statutActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consigne);

        findViewById();
        statutActivity = new SharedPreferencesStatutActivity(this);
        if (!statutActivity.isLoggedRB())
            targetRB();

        getToolbar();

        clickListernner();
    }

    void targetRB(){
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.card1),
                        "Cliquer sur le text pour voir le detail de la question",
                        ""));
        statutActivity.saveLoggedRB(true);

    }

    //Toolbar
    public void getToolbar(){
        toolbar.setTitle("Règles barrières");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void findViewById() {
        toolbar = findViewById(R.id.toolbar);
        llExpand1 = findViewById(R.id.ll_expand1);
        cardView1 = findViewById(R.id.card1);
        llExpand2 = findViewById(R.id.ll_expand2);
        cardView2 = findViewById(R.id.card2);
        llExpand3 = findViewById(R.id.ll_expand3);
        cardView3 = findViewById(R.id.card3);
        llExpand4 = findViewById(R.id.ll_expand4);
        cardView4 = findViewById(R.id.card4);
        llExpand5 = findViewById(R.id.ll_expand5);
        cardView5 = findViewById(R.id.card5);
    }

    private void clickListernner() {
        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);
        cardView5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.card1){
            toggleLinear(llExpand1);
        }else if (v.getId() == R.id.card2){
            toggleLinear(llExpand2);
        }else if (v.getId() == R.id.card3){
            toggleLinear(llExpand3);
        }else if (v.getId() == R.id.card4){
            toggleLinear(llExpand4);
        }else if (v.getId() == R.id.card5){
            toggleLinear(llExpand5);
        }
    }

    private void toggleLinear(LinearLayout linearLayout){
        if (linearLayout.getVisibility() == View.GONE) {
            ExpandAndCollapseViewUtil.expand(linearLayout, DURATION);
        } else {
            ExpandAndCollapseViewUtil.collapse(linearLayout, DURATION);
        }
    }
}
