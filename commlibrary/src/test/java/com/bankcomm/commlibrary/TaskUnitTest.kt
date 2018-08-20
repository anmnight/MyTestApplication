package com.bankcomm.commlibrary

import com.bankcomm.commlibrary.task.WorkFactory
import org.junit.Test
import java.lang.Thread.sleep
import java.util.concurrent.Executors

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 10:27
 * anmnight@qq.com
 */
class TaskUnitTest {

    @Test
    fun testSynTaskFactory() {


        WorkFactory.doSynWork()
                .doThisOnWork(Runnable {
                    System.out.println("Im task1")
                })
                .doThisOnWork(Runnable {
                    System.out.println("Im task2")
                })
                .doThisOnWork(Runnable {
                    sleep(1000)
                    System.out.println("Im task3")

                })
                .doThisOnWork(Runnable {
                    System.out.println("Im task4")
                })
                .doThisOnWork(Runnable {
                    System.out.println("Im task5")
                })
                .run()


    }

    @Test
    fun testAsynTask() {

        WorkFactory.doAsynWork(2)
                .doThisOnWork(Runnable {
                    System.out.println("Im task1")
                })
                .doThisOnWork(Runnable {
                    sleep(1000)
                    System.out.println("Im task2")
                })
                .doThisOnWork(Runnable {
                    sleep(1000)
                    System.out.println("Im task3")
                })
                .doThisOnWork(Runnable {
                    sleep(1000)
                    System.out.println("Im task4")
                })
                .doThisOnWork(Runnable {
                    System.out.println("Im task5")
                })
                .run()

    }

}