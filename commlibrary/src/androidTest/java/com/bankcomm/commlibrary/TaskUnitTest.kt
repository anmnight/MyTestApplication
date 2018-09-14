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


        WorkFactory.doSynWork()
                .doThisOnWork(Callable {
                    System.out.println("Im task1")
                })
                .doThisOnWork(Callable {
                    System.out.println("Im task2")
                })
                .doThisOnWork(Callable {

                    System.out.println("start")
                    val st = System.currentTimeMillis()
                    sleep(10000)
                    System.out.println("spend : ${System.currentTimeMillis() - st}")

                    System.out.println("Im task3")
                })
                .doThisOnWork(Callable {
                    System.out.println("Im task4")
                })
                .doThisOnWork(Callable {
                    System.out.println("Im task5")
                })
                .go()


    }

    @Test
    fun testAsynTask() {

        WorkFactory.doAsynWork(4)
                .doThisOnWork(Callable {
                    System.out.println("Im task1")
                })
                .doThisOnWork(Callable {
                    System.out.println("Im task2")
                })
                .doThisOnWork(Callable {
                    System.out.println("Im task3")
                })
                .doThisOnWork(Callable {
                    System.out.println("Im task4")
                })
                .doThisOnWork(Callable {
                    System.out.println("Im task5")
                })
                .go()

    }

}