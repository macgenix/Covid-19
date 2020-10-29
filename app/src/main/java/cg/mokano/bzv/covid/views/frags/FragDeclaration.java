package cg.mokano.bzv.covid.views.frags;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Actualite;
import cg.mokano.bzv.covid.models.Gouv;
import cg.mokano.bzv.covid.services.adapters.ActualiteAdapter;
import cg.mokano.bzv.covid.services.adapters.GouvAdapter;
import cg.mokano.bzv.covid.services.interfaces.RecyclerViewOnclickListernner;
import cg.mokano.bzv.covid.services.utils.FireBaseUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragDeclaration extends Fragment implements RecyclerViewOnclickListernner {
    Toolbar toolbar;
    RecyclerView rv;
    ArrayList<Gouv> liste;
    GouvAdapter adapter;

    public FragDeclaration() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.frag_declaration, container, false);
        findViewById(view);

        liste = new ArrayList<>();
        adapter = new GouvAdapter(getContext(), liste);

        getRecyclerView();
        loadData();

        adapter.setRecyclerViewOnclickListernner(this);

        return view;
    }

    void findViewById(View root){
        rv = root.findViewById(R.id.rv);
    }


    private void loadData() {
        FireBaseUtil.openFbReferences("Gouv");
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    private void setsnatShot(DataSnapshot dataSnapshot){
        Gouv gouv = dataSnapshot.getValue(Gouv.class);
        gouv.setId(dataSnapshot.getKey());
        adapter.addGouvPdf(gouv);
    }

    @Override
    public void onClickListernner(Object object) {
        Gouv gouv = (Gouv)object;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(gouv.getLien()), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        } catch (Exception e) {
            // Error...
        }
    }
}
