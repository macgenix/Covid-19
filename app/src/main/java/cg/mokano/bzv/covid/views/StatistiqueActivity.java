package cg.mokano.bzv.covid.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Statistique;
import cg.mokano.bzv.covid.services.adapters.StatAdapter;
import cg.mokano.bzv.covid.services.utils.FireBaseUtil;


public class StatistiqueActivity extends AppCompatActivity{
    Toolbar toolbar;
    RecyclerView rv;
    ArrayList<Statistique> liste;
    StatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistique);

        findViewId();
        setSupportActionBar(toolbar);
        getToolbar();

        liste = new ArrayList<>();
        adapter = new StatAdapter(this, liste);

        loadData();
        getRecyclerView();


    }

    void findViewId(){
        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
    }

    //Toolbar
    public void getToolbar(){
        toolbar.setTitle("Statistique sur la Covid-19");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void loadData() {
        FireBaseUtil.openFbReferences("Stat");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setsnatShot(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        FireBaseUtil.mDatabaseReference.addValueEventListener(eventListener);
    }

    private void getRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

    }

    private void setsnatShot(DataSnapshot dataSnapshot){
        if (dataSnapshot.exists()){
            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                adapter.addStat(snapshot, snapshot.getKey());
            }
        }
    }
}
