package cg.mokano.bzv.covid.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.View;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.services.adapters.ConseilAdapter;
import cg.mokano.bzv.covid.services.preferences.SharedPreferencesStatutActivity;

import static cg.mokano.bzv.covid.services.datas.BDConseils.allConseils;


public class ConseilActivity extends AppCompatActivity{
    Toolbar toolbar;
    ConseilAdapter adapter;
    RecyclerView rv;
    SharedPreferencesStatutActivity statutActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseil);

        findViewById();

        adapter = new ConseilAdapter(allConseils(this), this);

        myDataIntent();
        getToolbar();

        recyclerView();
    }



    //Toolbar
    public void getToolbar(){
        toolbar.setTitle("Questions Fréquentes");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    void myDataIntent(){
        Intent intent = getIntent();
        if (!(intent.getStringExtra("titre") == null)){
            toolbar.setTitle(intent.getStringExtra("titre"));
        }
    }

    void findViewById(){
        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
    }

    private void recyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }
}
