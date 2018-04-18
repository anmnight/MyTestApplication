package com.example.anxiao;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void bubble_sort() throws Exception {

        List<Integer> list = new ArrayList<>();

        Integer w = 0;
        while (w < 20) {
            list.add(new Random().nextInt(100));
            w++;
        }

        Integer[] arr = list.toArray(new Integer[list.size()]);

        String old = "";
        for (Integer i : arr) {
            old += (i + ",");
        }
        old = old.substring(0, old.length() - 1);
        TestUnit.INSTANCE.log("old : " + old);
        //small->big
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr.length - j - 1; k++) {
                if (arr[k] > arr[k + 1]) {
                    int t = arr[k];
                    arr[k] = arr[k + 1];
                    arr[k + 1] = t;
                }
            }
        }

        String now = "";
        for (Integer i : arr) {
            now += (i + ",");
        }
        now = now.substring(0, now.length() - 1);

        TestUnit.INSTANCE.log("now : " + now);

    }

    @Test
    public void quick_sort() {
        List<Integer> list = new ArrayList<>();

        Integer w = 0;
        while (w < 20) {
            list.add(new Random().nextInt(100));
            w++;
        }

        Integer[] arr = list.toArray(new Integer[list.size()]);

        String old = "";
        for (Integer i : arr) {
            old += (i + ",");
        }
        old = old.substring(0, old.length() - 1);
        TestUnit.INSTANCE.log("old : " + old);


        //small->big
        // TODO: 2017/9/1 快速排序
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr.length - j - 1; k++) {
                if (arr[k] > arr[k + 1]) {
                    int t = arr[k];
                    arr[k] = arr[k + 1];
                    arr[k + 1] = t;
                }
            }
        }


        String now = "";
        for (Integer i : arr) {
            now += (i + ",");
        }
        now = now.substring(0, now.length() - 1);

        TestUnit.INSTANCE.log("now : " + now);
    }
}