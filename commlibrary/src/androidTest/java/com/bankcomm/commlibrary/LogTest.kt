package com.bankcomm.commlibrary

import android.app.Application
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.bankcomm.commlibrary.logger.LogFactory
import com.bankcomm.commlibrary.logger.Logger
import org.junit.Test
import org.junit.runner.RunWith

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/24 16:06
 * anmnight@qq.com
 */

@RunWith(AndroidJUnit4::class)
class LogTest {

    private val Log:Logger = LogFactory.Builder.initLogService(InstrumentationRegistry.getTargetContext().applicationContext as Application)

    @Test
    fun testLogMethod(){

        Log.log("testLogMethod")

    }
}