package com.anmnight.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.anmnight.imageloader.base.DiskCache;
import com.anmnight.imageloader.base.Downloader;
import com.anmnight.imageloader.base.MemoryCache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class LoadTask implements Runnable {

    private Downloader downloader;
    private DiskCache diskCache;
    private MemoryCache memoryCache;
    private HexNameGenerate nameGenerate;
    private String imageUrl;
    private boolean isRecycled = false;
    private String tag = "LoadTask";

    LoadTask(
            String imageUrl,
            Downloader downloader,
            DiskCache diskCache,
            MemoryCache memoryCache,
            HexNameGenerate nameGenerate) {
        this.imageUrl = imageUrl;
        this.downloader = downloader;
        this.diskCache = diskCache;
        this.nameGenerate = nameGenerate;
        this.memoryCache = memoryCache;
    }

    @Override
    public void run() {

        //暂停加载
        if (isPause()) {
            return;
        }

        //被回收时不再执行任务
        if (isRecycled) {
            Log.i(tag, "recycled : " + imageUrl);
            return;
        }

        //内存寻找
        Bitmap bitmap = memoryCache.get(nameGenerate.generate(imageUrl));
        if (bitmap != null) {
            onLoaded(imageUrl, bitmap);
            return;
        }

        //磁盘寻找
        byte[] bytes = diskCache.get(nameGenerate.generate(imageUrl));
        if (bytes != null) {
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            if (bitmap != null) {
                memoryCache.put(bitmap, nameGenerate.generate(imageUrl));
                onLoaded(imageUrl, bitmap);
                return;
            }
        }

        //网络寻找
        final ByteArrayOutputStream byteArrayOutputStream = download(imageUrl);
        if (byteArrayOutputStream == null || byteArrayOutputStream.toByteArray() == null) {
            onDownloadError(new Throwable("save or download error"));
            return;
        }
        bytes = byteArrayOutputStream.toByteArray();
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        memoryCache.put(bitmap, nameGenerate.generate(imageUrl));

        LoaderManager.postToCache(new Runnable() {
            @Override
            public void run() {
                diskCache.put(byteArrayOutputStream.toByteArray(), nameGenerate.generate(imageUrl));
            }
        });
        onLoaded(imageUrl, bitmap);
        onComplete();

    }

    public void setIsRecycled(boolean isRecycled) {
        this.isRecycled = isRecycled;
    }


    //解流
    private ByteArrayOutputStream transSteam(Downloader.StreamInfo streamInfo) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream stream = streamInfo.getStream();
        try {
            byte[] buffer = new byte[1024];
            int len;
            int count = 0;
            while ((len = stream.read(buffer)) != -1) {
                count += len;
                onProgress(streamInfo.getContentLength(), count);
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            onDownloadError(new Throwable(e.getMessage()));
        }
        return outputStream;
    }

    //获取流
    private ByteArrayOutputStream download(String path) {
        Downloader.StreamInfo info = null;
        try {
            info = downloader.getStream(path);
            return transSteam(info);
        } catch (IOException e) {
            onDownloadError(new Throwable(e.getMessage()));
        } finally {
            if (info != null) {
                try {
                    info.getStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private boolean isPause() {

        AtomicBoolean paused = LoaderManager.getPause();
        if (paused.get()) {
            synchronized (LoaderManager.getPauseLock()) {
                if (paused.get()) {
                    try {
                        LoaderManager.getPauseLock().wait();
                    } catch (InterruptedException e) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //下载监听
    public abstract void onProgress(int sum, int count);

    public abstract void onLoaded(String path, Bitmap image);

    abstract void onComplete();

    abstract void onDownloadError(Throwable throwable);
}
