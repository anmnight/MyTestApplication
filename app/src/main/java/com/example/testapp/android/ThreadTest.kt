package com.example.testapp.android

import android.os.AsyncTask
import com.example.testapp.app.Logger
import java.util.concurrent.*

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/4 10:49
 * anmnight@qq.com
 */
class ThreadTest {

    val threadPool = Executors.newSingleThreadExecutor()

    fun testFutureTask() {

        val task = FutureTask(Callable<String> {
            "running"
        })

        Logger.info("start : ${System.currentTimeMillis()}")
        threadPool.submit(task)
        Logger.err("result : ${task.get()}")


    }


    fun testAsyncTask() {

        val task = TestTask()

        task.execute("www.baidu.com")

        task.cancel(true)
    }

    class TestTask : AsyncTask<String, Int, Long>() {
        override fun doInBackground(vararg p0: String?): Long {
            val size: Long = 0
            Logger.info(p0)
            return size
        }
    }


}