package com.anmnight.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.anmnight.imageloader.base.DiskCache;
import com.anmnight.imageloader.base.Displayer;
import com.anmnight.imageloader.cacher.LruDiskCache;
import com.anmnight.imageloader.cacher.PathHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AutoLoadImage extends AppCompatImageView {

    public AutoLoadImage(Context context) {
        super(context);
        init(context);
    }

    public AutoLoadImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AutoLoadImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private Context context;
    private String tag = "AutoLoadImage";
    private Handler mainHandler;

    private void init(Context context) {
        this.context = context;
        mainHandler = new Handler(Looper.getMainLooper());
    }


    public void displayImage(String url) {
        LoaderManager.addTask(createNewTask(url));
    }

    private DownloadAndSaveTask createNewTask(String url) {

        return new DownloadAndSaveTask(
                url,
                new BaseDownloader(),
                new LruDiskCache(PathHelper.externalCacheDir(context), new HexNameGenerate()),
                new HexNameGenerate()) {

            @Override
            public void onProgress(int sum, int count) {

            }

            @Override
            public void onLoaded(String path, byte[] image) {
                final Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        setImageBitmap(bm);
                    }
                });
            }

            @Override
            void onComplete() {

            }

            @Override
            void onDownloadError(Throwable throwable) {

            }
        };
    }

}
