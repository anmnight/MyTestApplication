package com.bankcomm.commlibrary.widget;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/25 16:59
 * anmnight@qq.com
 */
public class FloatFrame {

    private Context mAppCtx;

    public FloatFrame(Context context) {
        mAppCtx = context.getApplicationContext();
    }

    public void show(View frame) {

        WindowManager wm = (WindowManager) mAppCtx.getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG;

        if (wm != null) {
            wm.addView(frame, params);
        }
    }

}
