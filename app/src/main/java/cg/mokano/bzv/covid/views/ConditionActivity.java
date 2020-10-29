package cg.mokano.bzv.covid.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.services.utils.WebClient;

public class ConditionActivity extends AppCompatActivity {
    public static final String LIEN = "https://halte-covid.flycricket.io/privacy.html";
    WebView webView;
    Toolbar toolbar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition);

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
        webView.loadUrl(LIEN);
        webView.setWebViewClient(new WebClient(progressBar));
    }

    void toolbar(){
        //toolbar
        toolbar.setTitle(R.string.app_name);
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
