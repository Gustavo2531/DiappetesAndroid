package com.example.gustavomendez.diappetes;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ForoDiscusionActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro_discusion);
        webView = (WebView) findViewById(R.id.webView);
        //webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://diappetes.43377.n8.nabble.com");

    }
}
