package com.example.anxiao.mytestapplication.lesson_java

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.anxiao.mytestapplication.R
import com.example.anxiao.app.Logger
import kotlinx.android.synthetic.main.activity_executor_service_test.*
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

@Suppress("UNUSED_EXPRESSION")
class ExecutorServiceTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_executor_service_test)
        setSupportActionBar(toolbar)

        val mExecutorService: ExecutorService = Executors.newSingleThreadExecutor()

        val mFutureTask1 = FutureTask<Int>(Callable<Int> { 123 })
        val mFutureTask2 = FutureTask<Int>(Callable<Int> { 456 })
        val mFutureTask3 = FutureTask<Int>(Callable<Int> { 789 })

        mExecutorService.submit (mFutureTask1)
        mExecutorService.submit (mFutureTask2)
        mExecutorService.submit (mFutureTask3)

        Logger.debug(mFutureTask1.get())
        Logger.debug(mFutureTask2.get())
        Logger.debug(mFutureTask3.get())


    }

}
