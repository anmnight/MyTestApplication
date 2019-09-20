package com.example.testapp.widget;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.AppCompatImageView;

import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by anxiao on 2017/8/11.
 * xfermode round image
 */

public class XfermodeRoundImageView extends AppCompatImageView {

    private String tag = "XfermodeRoundImageView";

    private Paint mPaint = new Paint();
    private PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public XfermodeRoundImageView(Context context) {
        super(context);

    }

    public XfermodeRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }



    @Override
    public void onDraw(Canvas canvas) {



        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        Bitmap cBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas xCanvas = new Canvas(cBitmap);

        Drawable drawable = getDrawable();

        if (!(drawable instanceof BitmapDrawable)) {
            try {
                throw new Exception("drawable isn't BitmapDrawable");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Bitmap bitmap = scale(((BitmapDrawable) drawable).getBitmap());

        float left = 0F;
        float top = 0F;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            left = ((width - bitmap.getWidth()) / 2F);
        } else {
            top = ((height - bitmap.getHeight()) / 2F);
        }

        float radius = width > height ? height / 2F : width / 2F;


        mPaint.setAntiAlias(true);

        xCanvas.drawCircle(radius, radius, radius, mPaint);

        xCanvas.drawBitmap(cBitmap, 0F, 0F, mPaint);

        mPaint.setXfermode(porterDuffXfermode);

        xCanvas.drawBitmap(bitmap, left, top, mPaint);

        mPaint.setXfermode(null);

        canvas.drawBitmap(cBitmap, 0F, 0F, mPaint);

    }


    private Bitmap scale(Bitmap bitmap) {

        float cWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        float cHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        int oWidth = bitmap.getWidth();
        int oHeight = bitmap.getHeight();

        Matrix matrix = new Matrix();

        float temp = oWidth > oHeight ? cHeight / oHeight : cWidth / oWidth;

        matrix.postScale(temp, temp);

        return Bitmap.createBitmap(bitmap, 0, 0, oWidth, oHeight, matrix, false);
    }

}
