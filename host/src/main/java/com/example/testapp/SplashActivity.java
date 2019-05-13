package com.example.testapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.TitleViewPager;

import com.example.testapp.activities.CoordinatorActivity;
import com.example.testapp.activities.RoundImageEntryActivity;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends Activity {

    private String tag = "SplashActivity";

    private int[] viewPagerImages = {R.drawable.desert, R.drawable.hydrangeas, R.drawable.jellyfish, R.drawable.koala, R.drawable.lighthouse, R.drawable.penguins, R.drawable.tulips, R.drawable.chrysanthemum};

    private List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViewById(R.id.coor).setOnClickListener((view) -> startActivity(new Intent(this, CoordinatorActivity.class)));

        findViewById(R.id.round).setOnClickListener((view) -> startActivity(new Intent(this, RoundImageEntryActivity.class)));

        test();


        TitleViewPager viewPager = findViewById(R.id.viewpager);

        viewPager.setAdapter(new ViewPagerAdapter());


    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewPagerImages.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(imageViews.get(position));
        }
    }

    private void test() {
        for (int resId : viewPagerImages) {
            imageViews.add(createImageView(this, resId));
        }
    }

    private ImageView createImageView(Context context, int resId) {
        ImageView imageView = new ImageView(SplashActivity.this);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(lp);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageDrawable(context.getDrawable(resId));
        return imageView;
    }

}
