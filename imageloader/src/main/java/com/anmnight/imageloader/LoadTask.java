package com.anmnight.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.anmnight.imageloader.base.DiskCache;
import com.anmnight.imageloader.base.Downloader;
import com.anmnight.imageloader.base.MemoryCache;

import java.io.IOException;
import java.io.InputStream;

public abstract class LoadTask implements Runnable {

    private Downloader downloader;
    private DiskCache diskCache;
    private MemoryCache memoryCache;
    private HexNameGenerate nameGenerate;
    private String imageUrl;

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
        bytes = downloadAndMakeDiskCache(imageUrl);
        if (bytes == null) {
            onDownloadError(new Throwable("save or download error"));
            return;
        }
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        memoryCache.put(bitmap, nameGenerate.generate(imageUrl));
        onLoaded(imageUrl, bitmap);
        onComplete();

    }

    //磁盘缓存
    private byte[] downloadAndMakeDiskCache(String path) {
        try {
            InputStream stream = downloader.getStream(path);
            byte[] image = diskCache.put(stream, nameGenerate.generate(path), this);
            stream.close();
            return image;
        } catch (IOException e) {
            return null;
        }
    }

    //下载监听
    public abstract void onProgress(int sum, int count);

    public abstract void onLoaded(String path, Bitmap image);

    abstract void onComplete();

    abstract void onDownloadError(Throwable throwable);
}
