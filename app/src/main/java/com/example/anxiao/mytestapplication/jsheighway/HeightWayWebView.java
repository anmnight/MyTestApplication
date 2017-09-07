package com.example.anxiao.mytestapplication.jsheighway;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.anxiao.mytestapplication.R;
import com.example.anxiao.mytestapplication.app.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HeightWayWebView extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_way_webview);
        ButterKnife.bind(this);


        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new JsInteration(), "JsInteration");
        webView.loadUrl("file:///android_asset/index.html");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                webView.loadUrl(jsMothed("bbbbb"));
            }
        });


    }

    private String jsMothed(String str) {
        return "javascript:alertMessage(\"" + str + "\")";
    }


    private void jsCallBackMothed(int callbackId) {
        String temp = "javascript:onCallBack(\"" + callbackId + "\")";
        webView.loadUrl(temp);
    }


    private class JsInteration {
        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void waitingCall(final int callid) {

//            final JsCallback callback = new Gson().fromJson(jsonStr, JsCallback.class);

//            callback.setCallbackArg("im come from java");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                jsCallBackMothed(callid);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

}
