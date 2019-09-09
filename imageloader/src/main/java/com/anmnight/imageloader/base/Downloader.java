package com.anmnight.imageloader.base;

import java.io.IOException;
import java.io.InputStream;

public interface Downloader {

    //获取数据流
    public InputStream getStream(String url) throws IOException;

}
