package com.anmnight.imageloader.base;

public interface DiskCache {


    byte[] get(String key);

    void put(byte[] bytes, String key);

    boolean remove(String key);

    void close();


}
