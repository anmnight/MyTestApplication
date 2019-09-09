package com.anmnight.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;
import com.anmnight.imageloader.base.Displayer;

import java.io.IOException;

public class BaseDisplayer implements Displayer {

    private ImageView imageView;

    private Handler mainHandler;
    private String tag = "BaseDisplayer";

    BaseDisplayer(ImageView imageView,Handler mainHandler) {
        this.imageView = imageView;
        this.mainHandler = mainHandler;
    }

    @Override
    public Bitmap pretreatmentBitmap(byte[] image) throws IOException {

        int height = imageView.getMeasuredHeight();
        int width = imageView.getMeasuredWidth();

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(image, 0, image.length, options);


        if (height == 0 || width == 0) {
            options.inSampleSize = 4;
        } else {
            options.inSampleSize = calculateInSampleSize(options, width, height);
        }


        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, options);

        if (bitmap == null) {
            throw new IOException("bitmap decode error");
        }
        return bitmap;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    @Override
    public void display(final Bitmap bitmap) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageBitmap(bitmap);
            }
        });

    }
}
