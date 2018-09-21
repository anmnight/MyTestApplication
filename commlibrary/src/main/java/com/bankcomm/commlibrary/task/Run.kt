package com.bankcomm.commlibrary.task

import java.util.concurrent.Callable

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 10:19
 * anmnight@qq.com
 */
interface Run {

    //工作在子线程
    fun doThisOnWork(work: Runnable): Run

    //工作在主线程
    fun doThisOnMain(work: Runnable): Run

    //执行
    fun go()

    //强行销毁任务
    fun destroyNow()
}