package com.example.testapp;

import com.example.testapp.dagger2.CoffeeMaker;
import com.example.testapp.dagger2.Heater;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

import javax.inject.Inject;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/10/10 09:52
 * anmnight@qq.com
 */

public class Dagger2Test {

    @Inject Heater heater;

    @Test
    public void TestCofferMaker(){

        heater.heating();

    }
}
