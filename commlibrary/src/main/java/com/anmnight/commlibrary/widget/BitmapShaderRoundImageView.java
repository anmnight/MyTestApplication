package com.anmnight.commlibrary.widget;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;

import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

class BitmapShaderRoundImageView extends AppCompatImageView {

    private Paint mPaint = new Paint();
    private BitmapShader mBitmapShader;


    public BitmapShaderRoundImageView(Context context) {
        super(context);
        init();
    }

    public BitmapShaderRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Drawable drawable = getDrawable();
        if (!(drawable instanceof BitmapDrawable)) {
            try {
                throw new Exception("drawable isn't BitmapDrawable");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Bitmap bitmap = scale(((BitmapDrawable) drawable).getBitmap());

        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    }


    @Override
    public void onDraw(Canvas canvas) {

        mPaint.setShader(mBitmapShader);

        int radius = Math.min(getWidth(), getHeight()) / 2;

        canvas.drawCircle((getWidth() / 2F), (getHeight() / 2F), radius, mPaint);
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