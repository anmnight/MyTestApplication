package com.example.testapp.java_threadlocal

import org.junit.Test

class ThreadLocalTest {


    val mThreadLocal = ThreadLocal<Any>()

    @Test
    fun go() {
        Thread(TestRunnable()).start()
    }

    inner class TestRunnable : Runnable {
        override fun run() {
            if (mThreadLocal.get() == null) {
                mThreadLocal.set("im value ${System.currentTimeMillis()}")
            }
        }
    }

}
