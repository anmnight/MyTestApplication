package com.bankcomm.commlibrary.task

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/9/14 13:53
 * anmnight@qq.com
 */
class Work(val work: Runnable, val type: WorkType)  {
    enum class WorkType {
        MAIN, WORK
    }
}