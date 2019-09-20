package com.anmnight.imageloader.cacher;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.anmnight.imageloader.base.MemoryCache;

public class LruMemoryCache implements MemoryCache {

    private LruCache<String, Bitmap> cache;

    public LruMemoryCache() {
        long maxSize = Runtime.getRuntime().maxMemory() / 8;
        cache = new LruCache<>((int) maxSize);
    }

    @Override
    public Bitmap get(String key) {
        return cache.get(key);
    }

    @Override
    public void put(Bitmap bitmap, String key) {
        cache.put(key, bitmap);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

}
