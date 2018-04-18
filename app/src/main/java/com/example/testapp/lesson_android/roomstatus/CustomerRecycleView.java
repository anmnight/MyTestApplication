package com.example.testapp.lesson_android.roomstatus;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by anmnight on 2017/11/3 0003.
 */

public class CustomerRecycleView extends RecyclerView {
    public CustomerRecycleView(Context context) {
        super(context);
    }

    public CustomerRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
    }
}
