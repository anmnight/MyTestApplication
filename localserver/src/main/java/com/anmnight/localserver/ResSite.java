package com.anmnight.localserver;

import com.anmnight.commlibrary.unit.PathManager;
import com.yanzhenjie.andserver.annotation.Website;
import com.yanzhenjie.andserver.framework.website.FileBrowser;

@Website
public class ResSite extends FileBrowser {

    public ResSite() {
        super(PathManager.getInstance(LocalServerApplication.getInstance()).getImageDir());
    }
}
