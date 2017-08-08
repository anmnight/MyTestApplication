package com.example.anxiao.mytestapplication.lesson_java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.anxiao.mytestapplication.Logger;
import com.example.anxiao.mytestapplication.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListIterator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_iterator);

        Logger.DEBUG("im hotfix..");



        int i = 1;
        List<Integer> num = new ArrayList<>();
        while (i < 100) {
            num.add(i);
            i++;
        }


        Log.e("test", "array size: " + num.size());


        Iterator<Integer> iterator = num.iterator();

        while (iterator.hasNext()) {
            int k = iterator.next();
            if (k % 2 == 0) {
                iterator.remove();
            }
        }

        Log.e("test", "num : " + num.size());
    }
}
