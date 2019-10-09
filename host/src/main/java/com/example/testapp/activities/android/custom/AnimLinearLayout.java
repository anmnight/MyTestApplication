package com.example.testapp.activities.android.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.testapp.R;

public class AnimLinearLayout extends LinearLayout {

    private Context mCtx;

    public AnimLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public AnimLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AnimLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.mCtx = context;
    }


    @Override
    public void addView(View child) {
        Animation animation = AnimationUtils.loadAnimation(mCtx, R.anim.top_from_bottom);
        child.startAnimation(animation);
        super.addView(child);
    }
}
