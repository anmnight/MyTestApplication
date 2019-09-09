package com.anmnight.imageloader;

import com.anmnight.imageloader.base.Downloader;

import java.io.IOException;
import java.io.InputStream;

public class BaseDownloader implements Downloader {
    @Override
    public InputStream getStream(String url) throws IOException {
        return null;
    }
}
