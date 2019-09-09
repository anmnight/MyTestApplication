package com.anmnight.imageloader;

import com.anmnight.imageloader.base.Downloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseDownloader implements Downloader {
    @Override
    public InputStream getStream(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection.getInputStream();
    }

}
