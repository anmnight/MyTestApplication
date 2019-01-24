package com.example.testapp;

import org.junit.Test;
import org.w3c.dom.Entity;

public class StaticTest {

    @Test
    public void testFun() {


        try {
            TestLog.err("trying");
            TestLog.ptl("trying");

            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        } finally {
            System.out.println("finally");
            return;
        }


    }


}
