package com.bankcomm.commlibrary.view

import android.app.Activity
import android.app.Application

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/24 16:59
 * anmnight@qq.com
 */
class ActivityAdapterFactory(
        private var mDesignWidth: Int,
        private var mDesignHeight: Int,
        private var mDesignDpi: Int,
        private var mApp: Application) : ActivityAdapter {

    object Builder {
        var mType = Type.WIDTH
        fun setType(type: Type): Builder {
            this.mType = type
            return this
        }

        fun build(designWidth: Int, designHeight: Int, designDpi: Int, application: Application): ActivityAdapterFactory {
            return ActivityAdapterFactory(designWidth, designHeight, designDpi, application)
        }

    }

    enum class Type {
        WIDTH, HEIGHT
    }

    override fun setAutoViewSize(activity: Activity) {
        /**
         * density / dpi 像素密度
         *
         * density = dpi / 160
         * px = dpi * dp
         *
         * ip7
         * 1334*750
         * 326dpi
         *
         * 设计图：
         * pixel c
         * 2560*1800
         * 320dpi
         *
         */


        //目标参数
        val targetWidthDP = (mDesignWidth * 160 / mDesignDpi).toFloat()
        val targetHeightDp = (mDesignHeight * 160 / mDesignDpi).toFloat()

        //真实参数
        val displayMetrics = mApp.resources.displayMetrics
        val displayWidth = displayMetrics.widthPixels
        val displayHeight = displayMetrics.heightPixels

        val targetDensity: Float

        targetDensity = if (Builder.mType == Type.WIDTH) {
            displayWidth / targetWidthDP
        } else {
            displayHeight / targetHeightDp
        }

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