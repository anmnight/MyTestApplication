package com.example.testapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.testapp.ToolsKt.debug;


public class CoordinatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);


        Button b = new Button(this);

        List<View> views = new ArrayList<>();

        views.add(b);

        for (View v : views) {
            debug("id : " + v.getId());
        }

    }


}
