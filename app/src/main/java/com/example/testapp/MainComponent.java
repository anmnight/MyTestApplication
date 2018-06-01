package com.example.testapp;

import dagger.Component;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/30 19:04
 * anmnight@qq.com
 */

@Component
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
