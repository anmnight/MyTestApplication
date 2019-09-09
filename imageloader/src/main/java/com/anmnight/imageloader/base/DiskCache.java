package com.anmnight.imageloader.base;

import com.anmnight.imageloader.DownloadAndSaveTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface DiskCache {


    public File getDirectory();

    public byte[] get(String key);

    public void put(InputStream inputStream, String key, DownloadAndSaveTask listener);

    public boolean remove(String key);

    public void close();


}
