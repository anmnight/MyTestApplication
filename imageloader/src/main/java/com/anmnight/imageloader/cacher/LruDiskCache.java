package com.anmnight.imageloader.cacher;

import com.anmnight.imageloader.DownloadAndSaveTask;
import com.anmnight.imageloader.base.DiskCache;
import com.anmnight.imageloader.utils.DiskLruCache;
import com.anmnight.imageloader.HexNameGenerate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class LruDiskCache implements DiskCache {

    private File cacheDir;
    private HexNameGenerate nameGenerate;


    public LruDiskCache(File cacheDir, HexNameGenerate nameGenerate) {
        this.cacheDir = cacheDir;
        this.nameGenerate = nameGenerate;
    }


    @Override
    public File getDirectory() {
        return cacheDir;
    }

    @Override
    public byte[] get(String key) {

        InputStream stream = null;
        try {
            DiskLruCache cache = DiskLruCache.open(cacheDir, 1, 1, Integer.MAX_VALUE);
            DiskLruCache.Snapshot snapshot = cache.get(nameGenerate.generate(key));
            if (snapshot == null) {
                return null;
            }
            stream = snapshot.getInputStream(0);
            byte[] bytes = new byte[stream.available()];
            int b = stream.read(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void put(InputStream inputStream, String key, DownloadAndSaveTask listener) {

        try {
            DiskLruCache cache = DiskLruCache.open(cacheDir, 1, 1, Integer.MAX_VALUE);
            DiskLruCache.Editor editor = cache.edit(nameGenerate.generate(key));
            OutputStream os = new BufferedOutputStream(editor.newOutputStream(0));
            int len;
            byte[] buffer = new byte[1024];
            int cont = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                os.write(buffer, 0, len);
                cont += len;
                listener.onProgress(inputStream.available(), cont);
            }
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean remove(String key) {
        try {
            DiskLruCache cache = DiskLruCache.open(cacheDir, 1, 1, Integer.MAX_VALUE);
            return cache.remove(nameGenerate.generate(key));
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void close() {
        try {
            DiskLruCache cache = DiskLruCache.open(cacheDir, 1, 1, Integer.MAX_VALUE);
            cache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
