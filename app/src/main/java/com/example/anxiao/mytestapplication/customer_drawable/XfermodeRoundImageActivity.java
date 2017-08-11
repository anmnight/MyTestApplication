package com.example.anxiao.mytestapplication.customer_drawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.anxiao.mytestapplication.R;

import unit.DisPlayUnit;

public class XfermodeRoundImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfermode_round_image);

        XfermodeRoundImageView imageView = (XfermodeRoundImageView) findViewById(R.id.xfermode_image);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = DisPlayUnit.devicesDisPlay().widthPixels;
        imageView.setLayoutParams(params);

        imageView.setImageResource(R.mipmap.gal);
    }
}
