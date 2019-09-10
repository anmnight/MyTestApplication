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
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LoaderManager {

    private static int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingDeque<>();
    private static final Executor THREAD_POOL_EXECUTOR;
    private static Downloader downloader;
    private static DiskCache diskCache;
    private static HexNameGenerate nameGenerate;
    private static MemoryCache memoryCache;


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
        THREAD_POOL_EXECUTOR = downloadQueue;
    }


    private static volatile LoaderManager mSingleton;

    public static LoaderManager getInstance(Context context) {
        if (mSingleton == null) {
            synchronized (LoaderManager.class) {
                if (mSingleton == null) {
                    mSingleton = new LoaderManager(context);
                }
            }
        }
        return mSingleton;
    }


    public LoaderManager(Context context) {
        try {
            downloader = new BaseDownloader();
            diskCache = new LruDiskCache(PathHelper.externalCacheDir(context));
            nameGenerate = new HexNameGenerate();
            memoryCache = new LruMemoryCache();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Downloader getDownloader() {
        return downloader;
    }

    public DiskCache getDiskCache() {
        return diskCache;
    }

    public MemoryCache getMemoryCache() {
        return memoryCache;
    }

    public HexNameGenerate getNameGenerate() {
        return nameGenerate;
    }

    public void addTask(LoadTask task) {
        THREAD_POOL_EXECUTOR.execute(task);
    }


}
