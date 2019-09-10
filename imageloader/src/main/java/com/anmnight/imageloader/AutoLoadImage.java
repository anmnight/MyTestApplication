package com.anmnight.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

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
    private LoaderManager loaderManager;


    private void init(Context context) {
        this.context = context;
        mainHandler = new Handler(Looper.getMainLooper());
        loaderManager = LoaderManager.getInstance(context);
    }


    public void displayImage(String url) {
        loaderManager.addTask(createNewTask(url));
    }

    private LoadTask createNewTask(String url) {

        return new LoadTask(url,
                loaderManager.getDownloader(),
                loaderManager.getDiskCache(),
                loaderManager.getMemoryCache(),
                loaderManager.getNameGenerate()) {
            @Override
            public void onProgress(int sum, int count) {

            }

            @Override
            public void onLoaded(String path, final Bitmap image) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        setImageBitmap(image);
                    }
                });
            }


            @Override
            void onComplete() {

            }

            @Override
            void onDownloadError(Throwable throwable) {
                throwable.printStackTrace();
            }
        };
    }

}
