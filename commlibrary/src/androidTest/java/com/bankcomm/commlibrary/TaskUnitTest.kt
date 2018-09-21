package com.bankcomm.commlibrary

import com.bankcomm.commlibrary.task.WorkFactory
import org.junit.Test
import java.lang.Thread.sleep
import java.util.concurrent.Callable

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 10:27
 * anmnight@qq.com
 */
class TaskUnitTest {

    @Test
    fun testSynTaskFactory() {


        val factory = WorkFactory.doSynWork()

        factory
                .doThisOnWork(Runnable {
                    System.out.println("1")
                })
                .doThisOnMain(Runnable {
                    System.out.println("2")
                })
                .doThisOnWork(Runnable {
                    sleep(5000)
                    System.out.println("3")
                })
                .doThisOnWork(Runnable {
                    System.out.println("4")
                })
                .doThisOnMain(Runnable {
                    System.out.println("5")
                })
                .go()


    }

    @Test
    fun testAsynTask() {

        WorkFactory.doAsynWork(4)
                .doThisOnWork(Runnable {
                    System.out.println("Im task1")
                })
                .doThisOnWork(Runnable {
                    System.out.println("Im task2")
                })
                .doThisOnWork(Runnable {
                    System.out.println("Im task3")
                })
                .doThisOnWork(Runnable {
                    System.out.println("Im task4")
                })
                .doThisOnWork(Runnable {
                    System.out.println("Im task5")
                })
                .go()

    }

}