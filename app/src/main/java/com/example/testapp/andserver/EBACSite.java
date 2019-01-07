package com.example.testapp.andserver;

import com.yanzhenjie.andserver.annotation.Website;
import com.yanzhenjie.andserver.framework.website.AssetsWebsite;

@Website
public class EBACSite extends AssetsWebsite {

    public EBACSite() {
        super("/web","index.html");
    }
}
