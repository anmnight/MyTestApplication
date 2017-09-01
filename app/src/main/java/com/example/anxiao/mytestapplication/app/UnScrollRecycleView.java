package com.example.anxiao.mytestapplication.app;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class UnScrollRecycleView extends RecyclerView {

    public UnScrollRecycleView(Context context) {
        super(context);
    }

    public UnScrollRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UnScrollRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//
//        Logger.info("onInterceptTouchEvent fire event : " + event.getAction());
//
//        return event.getAction() == MotionEvent.ACTION_MOVE;
//    }
//
//
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Logger.info("onTouchEvent fire event : " + event.getAction());

        return false;
    }
}
