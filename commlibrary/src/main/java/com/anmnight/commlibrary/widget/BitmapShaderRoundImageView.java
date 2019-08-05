package com.anmnight.commlibrary.widget;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BitmapShaderRoundImageView extends ImageView {

    private Paint mPaint = new Paint();

    public BitmapShaderRoundImageView(Context context) {
        super(context);
    }

    public BitmapShaderRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        Bitmap bitmap = scale(((BitmapDrawable) drawable).getBitmap());

        BitmapShader mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

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

        int x = 0;
        int y = 0;
        int len = 0;
        if (oWidth > oHeight) {
            x = (oWidth - oHeight) / 2;
            len = oHeight;
        } else {
            y = (oHeight - oWidth) / 2;
            len = oWidth;
        }

        matrix.postScale(temp, temp);

        return Bitmap.createBitmap(bitmap, x, y, len, len, matrix, false);
    }
}