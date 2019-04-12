package com.example.testapp;

import org.junit.Test;
import org.w3c.dom.Entity;

public class JavaTest {


    @Test
    public void test() {

        int a = 1;
        int b = 1;

        System.out.println(a==b);

        b=a;

        System.out.println(a==b);

    }

    @Test
    public void testFun() {

        int i = exceptionFun();
        TestLog.ptl("i : " + i);
    }


    private int exceptionFun() {

        int ret = 0;
        try {
            throw new Exception();
        } catch (Exception e) {
            ret = 1;
            return ret;
        } finally {
            ret = 2;
            return ret;

        }


    }


}
