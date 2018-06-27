package com.example.testapp.homeinns.rooms.di

import com.example.testapp.MainActivity
import dagger.Subcomponent

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/6/26 14:13
 * anmnight@qq.com
 */

@Subcomponent
interface SubDataRepoComponent {
    fun inject(activity: MainActivity)

}
