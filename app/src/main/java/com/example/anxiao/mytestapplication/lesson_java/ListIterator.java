package com.example.anxiao.mytestapplication.lesson_java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.anxiao.mytestapplication.app.Logger;
import com.example.anxiao.mytestapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ListIterator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_iterator);

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

        Integer[] arr = {new Random().nextInt(100), new Random().nextInt(100), new Random().nextInt(100), new Random().nextInt(100)};

        List<Integer> temp = Arrays.asList(arr);

        List<Integer> newTemp = new ArrayList<>(temp);

        newTemp.addAll(temp);

        Logger.err("org : " + newTemp.size());

        newTemp.remove(newTemp.get(0));

        Logger.err("new : " + newTemp.size());

        Logger.err("org : " + arr.toString());

        //small->big
        for (int j = 0; j < arr.length - 1; j++) {
            for (int k = 0; k < arr.length - 1; k++) {
                if (arr[k] > arr[k + 1]) {
                    int t = arr[k];
                    arr[k + 1] = arr[k];
                    arr[k] = t;
                }
            }
        }


    }
}
