package com.example.student.xml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        wv = (WebView) findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebChromeClient(new WebChromeClient());
        Intent it = getIntent();
        String url = it.getStringExtra("LINK");
        wv.loadUrl(url);

    }
}
