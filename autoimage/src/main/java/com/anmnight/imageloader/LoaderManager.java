package com.anmnight.imageloader;

import android.content.Context;

import com.anmnight.imageloader.base.DiskCache;
import com.anmnight.imageloader.base.Downloader;
import com.anmnight.imageloader.base.MemoryCache;
import com.anmnight.imageloader.cacher.LruDiskCache;
import com.anmnight.imageloader.cacher.LruMemoryCache;
import com.anmnight.imageloader.cacher.PathHelper;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class LoaderManager {

    private static int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingDeque<>();
    private static final Executor DOWNLOAD_THREAD_POOL;
    private static final Executor CACHE_THREAD_POOL;
    private static Downloader downloader;
    private static DiskCache diskCache;
    private static HexNameGenerate nameGenerate;
    private static MemoryCache memoryCache;
    private final static Object pauseLock = new Object();
    private final static AtomicBoolean paused = new AtomicBoolean(false);


    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "LoaderManager #" + mCount.getAndIncrement());
        }
    };

    static {
        ThreadPoolExecutor downloadQueue = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_SECONDS,
                TimeUnit.SECONDS,
                sPoolWorkQueue, sThreadFactory);
        downloadQueue.allowCoreThreadTimeOut(true);
        DOWNLOAD_THREAD_POOL = downloadQueue;
        CACHE_THREAD_POOL = Executors.newSingleThreadExecutor();
    }


    private static volatile LoaderManager mSingleton;

    static LoaderManager getInstance(Context context) {
        if (mSingleton == null) {
            synchronized (LoaderManager.class) {
                if (mSingleton == null) {
                    mSingleton = new LoaderManager(context);
                }
            }
        }
        return mSingleton;
    }

    private LoaderManager(Context context) {
        try {
            downloader = new BaseDownloader();
            diskCache = new LruDiskCache(PathHelper.externalCacheDir(context));
            nameGenerate = new HexNameGenerate();
            memoryCache = new LruMemoryCache();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void postToCache(Runnable runnable) {
        CACHE_THREAD_POOL.execute(runnable);
    }


    Downloader getDownloader() {
        return downloader;
    }

    DiskCache getDiskCache() {
        return diskCache;
    }

    MemoryCache getMemoryCache() {
        return memoryCache;
    }

    HexNameGenerate getNameGenerate() {
        return nameGenerate;
    }

    void addTask(LoadTask task) {
        DOWNLOAD_THREAD_POOL.execute(task);
    }

    public static void pause() {
        paused.set(true);
    }

    public static void resume() {
        paused.set(false);
        synchronized (pauseLock) {
            pauseLock.notifyAll();
        }
    }

    static Object getPauseLock() {
        return pauseLock;
    }

    static AtomicBoolean getPause() {
        return paused;
    }

}
