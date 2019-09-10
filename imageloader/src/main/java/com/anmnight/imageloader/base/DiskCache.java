package com.anmnight.imageloader.base;

import com.anmnight.imageloader.LoadTask;

import java.io.InputStream;

public interface DiskCache {


    public byte[] get(String key);

    public byte[] put(InputStream inputStream, String key, LoadTask listener);

    public boolean remove(String key);

    public void close();


}
