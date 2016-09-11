package com.example.administrator.gamenew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;


public class WebViewActivity extends Activity {
    private WebView new_wv;
    private ImageView wv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        init();

    }

    private void init() {
        Intent intent = getIntent();
        String StringE = intent.getStringExtra("url");
        Boolean isvideo = intent.getBooleanExtra("type", false);
        String what = intent.getStringExtra("what");
        // Log.i("opp", StringE);
        if (what.equals("0")) {
            if (isvideo == true) {
                // StringE = StringE;
            } else {
                String[] sArray = StringE.split("/");
                StringE = "http://qt.qq.com/static/pages/news/phone/" + sArray[0] + "/" + sArray[1] + "?APP_BROWSER_VERSION_CODE=1&ios_version=873&imgmode=auto";
            }

        } else if (what.equals("1")) {
            String[] sArray = StringE.split("&");
            String[] sArray2 = sArray[0].split("/");
            StringE = "http://lol.duowan.com/" + sArray2[0] + "/" + sArray2[1] + ".html";
        }else if (what.equals("3")){
            //剑三
        }

        Log.i("opp", StringE);
        new_wv = (WebView) findViewById(R.id.new_wv);
        new_wv.setWebViewClient(new WebViewClient());
        WebSettings webSettings = new_wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        //	webSettings.setPluginsEnabled(true);
        webSettings.setPluginState(PluginState.ON);

        new_wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        new_wv.loadUrl(StringE);
        //new_wv.loadUrl("http://lol.duowan.com/1606/329181443632.html");

        findViewById(R.id.wv_back).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}
