package com.anmnight.localserver;

import android.util.Log;

import com.anmnight.commlibrary.unit.PathManager;
import com.yanzhenjie.andserver.annotation.Website;
import com.yanzhenjie.andserver.framework.website.StorageWebsite;

@Website
public class WebSite extends StorageWebsite {

    private String TAG = "WebSite";

    public WebSite() {
        super(PathManager.getInstance(LocalServerApplication.getInstance()).getWebDir(), "index.html");

        Log.d(TAG, PathManager.getInstance(LocalServerApplication.getInstance()).getWebDir());
    }
}
