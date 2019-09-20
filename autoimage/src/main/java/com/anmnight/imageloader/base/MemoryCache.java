package com.anmnight.imageloader.base;

import android.graphics.Bitmap;

public interface MemoryCache {

    Bitmap get(String key);

    void put(Bitmap bitmap, String key);

    void remove(String key);

}
