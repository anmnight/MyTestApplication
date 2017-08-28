package com.example.anxiao.mytestapplication.lesson_fresco;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.anxiao.mytestapplication.Logger;
import com.example.anxiao.mytestapplication.R;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import unit.DisPlayUnit;
import unit.ImageUnit;

public class ThumbActivity extends AppCompatActivity {

    private static final String RPATH = "route_path";

    public static void route(Context context, String path) {
        Intent intent = new Intent(context, ThumbActivity.class);
        intent.putExtra(RPATH, path);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumb);
        String path = getIntent().getStringExtra(RPATH);
        Logger.debug(path);
        final SimpleDraweeView view = (SimpleDraweeView) findViewById(R.id.thumb_img);


        ImageUnit.showThumb(Uri.fromFile(new File(path)), view, DisPlayUnit.devicesDisPlay().widthPixels, DisPlayUnit.devicesDisPlay().heightPixels - DisPlayUnit.dip2px(60));
        final RoundingParams roundingParams = RoundingParams.asCircle();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.img_type);
        RadioButton normalBtn = (RadioButton) findViewById(R.id.normal);
        normalBtn.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.normal:
                        roundingParams.setRoundAsCircle(false);
                        view.getHierarchy().setRoundingParams(roundingParams);
                        break;
                    case R.id.circle:
                        roundingParams.setRoundAsCircle(true);
                        view.getHierarchy().setRoundingParams(roundingParams);
                        break;
                }
            }
        });

    }
}
