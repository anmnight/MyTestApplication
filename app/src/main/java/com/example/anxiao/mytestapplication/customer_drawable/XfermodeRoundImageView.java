package com.example.anxiao.mytestapplication.customer_drawable;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by anxiao on 2017/8/11.
 * xfermode round image
 */

public class XfermodeRoundImageView extends AppCompatImageView {

    public XfermodeRoundImageView(Context context) {
        super(context);
    }

    public XfermodeRoundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        int width = canvas.getWidth() - getPaddingLeft() - getPaddingRight();
        int height = canvas.getHeight() - getPaddingTop() - getPaddingBottom();
        Bitmap bitmap = drawable2Bitmap(getDrawable());
        canvas.drawBitmap(bitmapToCircleBitmap(bitmap, Math.min(width, height) / 2), getPaddingLeft(), getPaddingTop(), null);
    }


    private Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private Bitmap bitmapToCircleBitmap(Bitmap oBitmap, int radius) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap cBitmap = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(cBitmap);
        canvas.drawCircle(radius, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(oBitmap, 0, 0, paint);
        return cBitmap;
    }
}
