package com.bankcomm.commlibrary.task

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/9/14 09:26
 * anmnight@qq.com
 */
class Worker(private val mPool: ExecutorService) {

    @Synchronized
    fun <T> doOnWork(work: Callable<T>): T {

        val future = mPool.submit(work)
        val st = System.currentTimeMillis()

        while (!future.isDone) {
            val time = System.currentTimeMillis() - st
            if (time > 20 * 1000) {
                throw Exception("work spend to much time")
            }
        }

        return future.get()
    }
}