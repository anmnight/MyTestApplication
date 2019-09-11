package com.anmnight.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;

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
        paint = new Paint();
        paint.setColor(Color.WHITE);
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
                sum += 5;
                progressAngle = count * 360 / sum;
                postInvalidate();
            }

            @Override
            public void onLoaded(String path, final Bitmap image) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        setImageBitmap(image);
                        invalidate();
                    }
                });
            }


            @Override
            void onComplete() {
                if (progressAngle < 360) {
                    progressAngle = 360;
                }
            }

            @Override
            void onDownloadError(Throwable throwable) {
                throwable.printStackTrace();
            }
        };
    }


    private Paint paint;
    private float progressAngle = 0;
    RectF rectF = new RectF();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (progressAngle < 360) {

            float cx = getWidth() / 2;
            float cy = getHeight() / 2;
            float radius;
            if (cx > cy) {
                radius = cy / 3;
            } else {
                radius = cx / 3;
            }

            rectF.top = (getHeight() - radius) / 2;
            rectF.bottom = (getHeight() + radius) / 2;
            rectF.left = (getWidth() - radius) / 2;
            rectF.right = (getWidth() + radius) / 2;

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            canvas.drawCircle(cx, cy, radius / 2 + 1, paint);
            paint.setStyle(Paint.Style.FILL);


            canvas.drawArc(rectF, -90, progressAngle, true, paint);
        }
    }
}
