package com.anmnight.imageloader.utils;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.WindowManager;
import android.widget.ImageView;

public class DisplayHelper {

    private boolean isImageViewInScreen(WindowManager windowManager, ImageView imageView) {
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        int width = point.x;
        int height = point.y;
        return imageView.getLocalVisibleRect(new Rect(0, 0, width, height));
    }
}
