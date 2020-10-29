package cg.mokano.bzv.covid.views;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.services.utils.ViewPagerAdapter;
import cg.mokano.bzv.covid.views.frags.FragApercu;
import cg.mokano.bzv.covid.views.frags.FragDeclaration;


public class DeclarationActivity extends AppCompatActivity {
    Toolbar toolbar;
    private TabLayout tab;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);

        findViewById();
        setSupportActionBar(toolbar);
        getToolbar();

        setupViewPager(viewPager);
        tab.setupWithViewPager(viewPager);
    }

    //Toolbar
    public void getToolbar(){
        toolbar.setTitle("Communiqué du gouvernement");
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
        tab = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragApercu(),"Aperçu");
        adapter.addFragment(new FragDeclaration(),"Déclarations");
        //adapter.addFragment(new FragVideo(), "Vidéos");

        viewPager.setAdapter(adapter);
    }
}
