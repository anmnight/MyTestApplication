package com.example.testapp.andserver;

import com.example.testapp.andserver.manager.PathManager;
import com.yanzhenjie.andserver.annotation.Website;
import com.yanzhenjie.andserver.framework.website.FileBrowser;

@Website
public class ResSite extends FileBrowser {

    public ResSite() {
        super(PathManager.getInstance().getImageDir());
    }
}
