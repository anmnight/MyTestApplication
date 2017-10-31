package unit

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

import com.example.anxiao.app.HomeApplication

/**
 * Created by anxiao on 2017/8/9.
 * 屏幕相关参数
 */

object DisPlayUnit {

    fun devicesDisPlay(): DisplayMetrics {
        val metrics = DisplayMetrics()
        val context = HomeApplication.getInstance()
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        manager.defaultDisplay.getMetrics(metrics)
        return metrics
    }

    fun dip2px(dpValue: Int): Int {
        val scale = HomeApplication.getInstance().resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }


}
