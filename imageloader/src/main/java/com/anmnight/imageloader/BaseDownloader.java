package com.anmnight.imageloader;

import com.anmnight.imageloader.base.Downloader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseDownloader implements Downloader {
    @Override
    public StreamInfo getStream(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        return new StreamInfo(connection.getInputStream(), connection.getContentLength());
    }

}
