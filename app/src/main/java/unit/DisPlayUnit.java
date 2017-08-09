package unit;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.anxiao.mytestapplication.HomeApplication;

/**
 * Created by anxiao on 2017/8/9.
 * 屏幕相关参数
 */

public class DisPlayUnit {

    public static DisplayMetrics devicesDisPlay() {
        DisplayMetrics metrics = new DisplayMetrics();
        Context context = HomeApplication.getInstance();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }



}
