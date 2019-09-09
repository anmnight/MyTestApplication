package com.anmnight.imageloader;

import android.graphics.BitmapFactory;

import com.anmnight.imageloader.base.DiskCache;
import com.anmnight.imageloader.base.Downloader;

import java.io.IOException;
import java.io.InputStream;

public abstract class DownloadAndSaveTask implements Runnable {

    private Downloader downloader;
    private final DiskCache diskCache;
    private HexNameGenerate mNameGenerate;
    private String imageUrl;

    DownloadAndSaveTask(
            String imageUrl,
            Downloader downloader,
            DiskCache diskCache,
            HexNameGenerate nameGenerate) {
        this.imageUrl = imageUrl;
        this.downloader = downloader;
        this.diskCache = diskCache;
        mNameGenerate = nameGenerate;
    }

    @Override
    public void run() {

        byte[] image = downloadAndMakeDiskCache(imageUrl);

        if (image == null) {
            onDownloadError(new Throwable("save or download error"));
            return;
        }

        onLoaded(imageUrl, image);
        onComplete();

    }

    //磁盘缓存
    private byte[] downloadAndMakeDiskCache(String path) {
        try {
            InputStream stream = downloader.getStream(path);
            diskCache.put(stream, mNameGenerate.generate(path), this);
            stream.close();
            return diskCache.get(mNameGenerate.generate(path));
        } catch (IOException e) {
            return null;
        }

    }

    //下载监听
    public abstract void onProgress(int sum, int count);

    public abstract void onLoaded(String path, byte[] image);

    abstract void onComplete();

    abstract void onDownloadError(Throwable throwable);
}
