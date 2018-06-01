package com.example.testapp.lesson_java;

import javax.inject.Inject;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/30 19:01
 * anmnight@qq.com
 */
public class StudentBean {
    private int no;
    private String name;

    @Inject
    public StudentBean() {
        this.no = 1;
        this.name = "赵四";
    }


    public String getName(){
        return name;
    }

}
