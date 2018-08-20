package com.bankcomm.commlibrary.task

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 10:19
 * anmnight@qq.com
 */
interface Run {
    //工作在子线程
    fun doThisOnWork(task: Runnable): Run

    //工作在主线程
    fun doThisOnMain(task: Runnable): Run

    //执行
    fun run()

    //todo 页面关闭时 强制关闭所有任务，抛出异常以及任务
}