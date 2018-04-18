package com.example.testapp.lesson_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.mytestapplication.R
import com.example.testapp.app.Logger
import unit.TestCase
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

class ExecutorTest : AppCompatActivity() {

    private var mExecutor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excutor_test)

        val futureTask = FutureTask<Int>(
                Callable { TestCase.fibc(20) }
        )

        mExecutor.submit(futureTask)

        Logger.debug("Result : "+futureTask.get())


        mExecutor.shutdown()




    }
}
