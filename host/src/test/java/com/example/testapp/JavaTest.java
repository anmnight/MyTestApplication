package com.example.testapp;

import org.junit.Test;
import org.w3c.dom.Entity;

public class JavaTest {

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

        }


    }


}
