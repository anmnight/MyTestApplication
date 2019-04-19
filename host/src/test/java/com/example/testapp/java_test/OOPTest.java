package com.example.testapp.java_test;

import org.junit.Test;

public class OOPTest {


    /**
     * 多态
     */
    @Test
    public void childTest(){

        //编译类型 BaseParent
        //执行类型 Child

        BaseParent baseParent = new Child();
        System.out.println(baseParent.field);
        baseParent.parentFun();

        //baseParent.childFun();
    }






}
