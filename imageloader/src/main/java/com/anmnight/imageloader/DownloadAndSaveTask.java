package com.anmnight.imageloader;

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
        onStart();
        try {
            byte[] image = downloadAndMakeDiskCache(imageUrl);
            if (image.length > 0) {
                onLoaded(imageUrl, image);
            }
        } catch (Exception e) {
            onDownloadError(new Throwable(e.getMessage()));
        } finally {
            onComplete();
        }
    }

    //磁盘缓存
    private byte[] downloadAndMakeDiskCache(String path) throws IOException {
        synchronized (diskCache) {
            InputStream stream = downloader.getStream(path);
            diskCache.put(stream, mNameGenerate.generate(path), this);
            stream.close();
            return diskCache.get(mNameGenerate.generate(path));
        }

    }


    abstract void onStart();

    //下载监听
    public abstract void onProgress(int sum, int count);

    public abstract void onLoaded(String path, byte[] image);

    abstract void onComplete();

    abstract void onDownloadError(Throwable throwable);
}
