package cg.mokano.bzv.covid.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.Fakenews;
import cg.mokano.bzv.covid.services.utils.WebClient;

public class DetailFakenewsActivity extends AppCompatActivity {
    WebView webView;
    Toolbar toolbar;
    ProgressBar progressBar;
    Fakenews fakenews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fakenews);

        fakenews = getIntent().getParcelableExtra("fakenews");

        findViewById();
        toolbar();
        loadUrl();
    }

    void findViewById(){
        progressBar = findViewById(R.id.progressbar);
        webView = findViewById(R.id.webview);
        toolbar = findViewById(R.id.toolbar);
    }

    void  loadUrl(){
        webView.getSettings().setLoadsImagesAutomatically(true);

        // Configure la webview pour l'utilisation du javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Permet l'ouverture des fenêtres
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // Autorise le stockage DOM (Document Object Model)
        webSettings.setDomStorageEnabled(true);

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(fakenews.getUrlRS());
        webView.setWebViewClient(new WebClient(progressBar));
    }

    void toolbar(){
        //toolbar
        toolbar.setSubtitle(fakenews.getDescription());
        toolbar.setTitleTextColor(getResources().getColor(R.color.whrite));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
