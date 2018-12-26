package com.bankcomm.commlibrary.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/25 16:59
 * anmnight@qq.com
 */
public class FloatFrame {

    private Context mAppCtx;

    private WindowManager windowManager;

    private View frameView;

    public FloatFrame(Context context) {
        mAppCtx = context.getApplicationContext();
    }

    public void show(View frameView) {

        windowManager = (WindowManager) mAppCtx.getSystemService(Context.WINDOW_SERVICE);

        this.frameView = frameView;

        int screenWidth, screenHeight;

        if (windowManager != null) {

            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

            Point point = new Point();

            windowManager.getDefaultDisplay().getSize(point);

            screenWidth = point.x;
            screenHeight = point.y;

            layoutParams.width = screenWidth;
            layoutParams.height = screenHeight / 2;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

            } else {

                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;

            }

            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

            layoutParams.gravity = Gravity.START | Gravity.TOP;


            layoutParams.x = 0;
            layoutParams.y = 0;

            layoutParams.format = PixelFormat.TRANSPARENT;

            windowManager.addView(frameView, layoutParams);
        }

    }

    public void destroy() {

        if (windowManager != null && frameView != null) {
            windowManager.removeView(frameView);
            frameView = null;
        }
    }




}
