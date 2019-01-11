package com.example.testapp.andserver;

import android.util.Log;

import com.example.testapp.andserver.manager.PathManager;
import com.yanzhenjie.andserver.annotation.Website;
import com.yanzhenjie.andserver.framework.website.StorageWebsite;

@Website
public class WebSite extends StorageWebsite {

    private String TAG = "WebSite";
    public WebSite() {
        super(PathManager.getInstance().getWebDir(), "index.html");

        Log.d(TAG,PathManager.getInstance().getWebDir());
    }
}
