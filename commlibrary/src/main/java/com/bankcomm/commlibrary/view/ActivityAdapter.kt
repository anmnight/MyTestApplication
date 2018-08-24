package com.bankcomm.commlibrary.view

import android.app.Activity
import android.app.Application
import android.support.annotation.NonNull

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/24 16:59
 * anmnight@qq.com
 */
object ActivityAdapter {

    fun setAutoViewSize(@NonNull application: Application, @NonNull activity: Activity) {


        //todo 相同比例屏幕 view 缩放
        /**
         * density 密度
         * dpi 像素密度
         * px = density * dp;
         * density = dpi / 160;
         * px = dp * (dpi / 160);
         */
        //真实参数
        val displayMetrics = application.resources.displayMetrics

        val targetDensity: Float = (displayMetrics.widthPixels / 360).toFloat()
        val targetDensityDpi: Int = (targetDensity * 160).toInt()


        displayMetrics.density = targetDensity
        displayMetrics.scaledDensity = targetDensity
        displayMetrics.densityDpi = targetDensityDpi

        val activityDensity = activity.resources.displayMetrics
        activityDensity.density = targetDensity
        activityDensity.scaledDensity = targetDensity
        activityDensity.densityDpi = targetDensityDpi
    }

}