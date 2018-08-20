package com.bankcomm.commlibrary.task

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 17:26
 * anmnight@qq.com
 */
data class Work(val runnable: Runnable, val runType: RunType) {



    enum class RunType {
        MAIN, WORK
    }
}

