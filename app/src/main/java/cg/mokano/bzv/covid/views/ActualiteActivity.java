package cg.mokano.bzv.covid.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Actualite;
import cg.mokano.bzv.covid.services.adapters.ActualiteAdapter;
import cg.mokano.bzv.covid.services.interfaces.RecyclerViewOnclickListernner;
import cg.mokano.bzv.covid.services.utils.FireBaseUtil;


public class ActualiteActivity extends AppCompatActivity implements RecyclerViewOnclickListernner {
    Toolbar toolbar;
    RecyclerView rv;
    ArrayList<Actualite> liste;
    ActualiteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualite);

        findViewById();

        getToolbar();

        liste = new ArrayList<>();
        adapter = new ActualiteAdapter(this, liste);

        getRecyclerView();
        loadData();

        adapter.setRecyclerViewOnclickListernner(this);
    }

    //Toolbar
    public void getToolbar(){
        toolbar.setTitle("Actualité sur la Covid-19");
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
        rv = findViewById(R.id.rv);
    }


    private void loadData() {
        FireBaseUtil.openFbReferences("Actualite");
        ChildEventListener eventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                setsnatShot(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        FireBaseUtil.mDatabaseReference.addChildEventListener(eventListener);
    }

    private void getRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    private void setsnatShot(DataSnapshot dataSnapshot){
        Actualite actualite = dataSnapshot.getValue(Actualite.class);
        actualite.setId(dataSnapshot.getKey());
        adapter.addActualite(actualite);
    }

    @Override
    public void onClickListernner(Object object) {
        Actualite actualite = (Actualite)object;
        Intent intent = new Intent(this, DetailActualiteActivity.class);
        intent.putExtra("actualite", actualite);
        startActivity(intent);
    }
}
