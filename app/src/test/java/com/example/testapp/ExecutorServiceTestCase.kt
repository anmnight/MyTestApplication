package com.example.testapp

import com.example.testapp.app.Logger
import org.junit.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask


/**
 * Created by anmnight on 2017/10/27 0027.
 */
class ExecutorServiceTestCase {

    private val mExecutor = Executors.newSingleThreadExecutor()

    @Test
    fun runableTestCase(){

        val futureTask = FutureTask<Int>(
                Callable<Int> {
                    TestUnit.fibc(200)
                }
        )

        Logger.debug(futureTask.get())
        mExecutor.submit(futureTask)
        futureTask.get()
    }

}