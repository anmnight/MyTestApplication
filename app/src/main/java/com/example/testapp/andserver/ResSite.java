package com.example.testapp.andserver;

import com.anmnight.commlibrary.unit.PathManager;
import com.example.testapp.TestHomeApplication;
import com.yanzhenjie.andserver.annotation.Website;
import com.yanzhenjie.andserver.framework.website.FileBrowser;

@Website
public class ResSite extends FileBrowser {

    public ResSite() {
        super(PathManager.getInstance(TestHomeApplication.getInstance()).getImageDir());
    }
}
