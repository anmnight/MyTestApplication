package com.bankcomm.commlibrary.view;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/10/11 14:49
 * anmnight@qq.com
 */
public class ActivityAdapterFactory implements ActivityAdapter {

    private int mDesignWidth;
    private int mDesignHeight;
    private int mDesignDpi;
    private Application mApp;

    private ActivityAdapterFactory(int designWidth,
                                   int designHeight,
                                   int designDpi,
                                   Application app) {
        this.mApp = app;
        this.mDesignDpi = designDpi;
        this.mDesignWidth = designWidth;
        this.mDesignHeight = designHeight;

    }

    private static Type mType = Type.WIDTH;

    public static class Builder {

        public Builder setType(Type type) {
            ActivityAdapterFactory.mType = type;
            return this;
        }

        public ActivityAdapter build(int designWidth, int designHeight, int designDpi, Application application) {
            return new ActivityAdapterFactory(designWidth, designHeight, designDpi, application);
        }


    }


    public enum Type {
        WIDTH, HEIGHT
    }

    @Override
    public void setAutoViewSize(Activity activity) {

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
        float targetWidthDP = mDesignWidth * 160 / mDesignDpi;
        float targetHeightDp = mDesignHeight * 160 / mDesignDpi;

        //真实参数
        DisplayMetrics displayMetrics = mApp.getResources().getDisplayMetrics();

        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;

        float targetDensity = displayHeight / targetHeightDp;

        if (ActivityAdapterFactory.mType == Type.WIDTH) {
            targetDensity = displayWidth / targetWidthDP;
        }

        int targetDensityDpi = (int) (targetDensity * 160);

        displayMetrics.density = targetDensity;
        displayMetrics.scaledDensity = targetDensity;
        displayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityMetrics = activity.getResources().getDisplayMetrics();
        activityMetrics.density = targetDensity;
        activityMetrics.scaledDensity = targetDensity;
        activityMetrics.densityDpi = targetDensityDpi;

    }

}
