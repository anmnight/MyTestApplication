package com.anmnight.imageloader;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.anmnight.imageloader.cacher.LruDiskCache;
import com.anmnight.imageloader.cacher.PathHelper;

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

    private void init(Context context) {
        this.context = context;
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
            void onStart() {

            }

            @Override
            public void onProgress(int sum, int count) {
                Log.d(tag, "onProgress count : " + count);
            }

            @Override
            public void onLoaded(String path, byte[] image) {
                Log.i(tag, "onProgress count : " + path);
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
