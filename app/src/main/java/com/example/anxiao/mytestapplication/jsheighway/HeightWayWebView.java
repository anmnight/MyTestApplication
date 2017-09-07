package com.example.anxiao.mytestapplication.jsheighway;

import android.annotation.SuppressLint;
import android.support.v4.util.ArrayMap;
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
                testMethod();

            }
        });


    }


    private void jsCallBackMothed(int callbackId, String params) {
        String temp = "javascript:onCallBack(\"" + callbackId + "\",\"" + params + "\")";
        webView.loadUrl(temp);
    }

    abstract class CallBack {
        int callid;

        abstract void onCall(CallBack callBack);

        void doComplete(final String params) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    jsCallBackMothed(callid, params);
                }
            });
        }
    }

    ArrayMap<String, CallBack> requestes = new ArrayMap<>();

    public void setDoAction(String actionName, CallBack callBack) {
        requestes.put(actionName, callBack);
    }


    private void testMethod() {

        setDoAction("test", new CallBack() {
            @Override
            void onCall(CallBack callBack) {

                //do something

                callBack.doComplete("im java");

            }
        });
    }

    private class JsInteration {

        @JavascriptInterface
        public void waitingCall(int callid, String actionName, String params) throws Exception {
            CallBack callBack = requestes.get(actionName);
            if (callBack == null) {
                throw new Exception("you should register listener on java");
            } else {
                callBack.callid = callid;
            }
            callBack.onCall(callBack);

        }
    }

}
