package com.example.testapp.java_test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaTest {

    @Test
    public void continueTest() {

        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                continue;
            }
            System.out.println("for : " + i);
        }

        int j = 0;
        while (j < 10) {

            if (j == 5) {
                continue;
            }

            System.out.println("while : " + j);

            j++;
        }

        System.out.println("end test");


    }

    @Test
    public void breakTest() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 3) {
                    break;
                }
                System.out.println("for : " + j);
            }
        }
    }

    @Test
    public void forTest() {

        for (int i = 0; i < 10; i++) {
            i += 2;
            System.out.println("i : " + i);
        }

    }

    @Test
    public void handlerTest() {

        System.out.println(new Integer(2) == new Integer(2));


        Integer ina = 2;
        Integer inb = 2;
        System.out.println(ina == inb);

        String sa = "asd";
        String sb = "asd";
        System.out.println(sa == sb);


    }


}
