package com.example.testapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.testapp.R;

public class WechatPopupWindow extends PopupWindow {

    private Context context;

    public WechatPopupWindow(Context context) {
        super(context);
    }

    public WechatPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WechatPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WechatPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setContentView(View contentView) {
        super.setContentView(contentView);



    }


}
