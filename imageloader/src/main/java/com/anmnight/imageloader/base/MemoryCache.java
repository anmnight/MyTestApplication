package com.anmnight.imageloader.base;

import android.graphics.Bitmap;

public interface MemoryCache {

    public Bitmap get(String key);

    public void put(Bitmap bitmap, String key);

    public void remove(String key);


}
