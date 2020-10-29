package cg.mokano.bzv.covid.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import cg.mokano.bzv.covid.R;


public class CovidActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);

        findViewById();

        getToolbar();
    }

    //Toolbar
    public void getToolbar(){
        toolbar.setTitle("Coronavirus, c'est quoi ?");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    void findViewById(){
        toolbar = findViewById(R.id.toolbar);
    }
}
