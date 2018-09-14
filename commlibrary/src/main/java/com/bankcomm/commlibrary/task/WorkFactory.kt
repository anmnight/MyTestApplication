package com.bankcomm.commlibrary.task

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Callable

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 10:21
 * anmnight@qq.com
 */
class WorkFactory : Run {

    private var mType: TaskType
    private var mPool: ExecutorService
    private var mWorker: Worker

    private val mainHandler = Handler(Looper.getMainLooper())


    constructor() {
        mType = TaskType.SYN
        mPool = Executors.newSingleThreadExecutor()
        mWorker = Worker(mPool)
    }

    constructor(maxTask: Int) {
        mType = TaskType.ASYN
        mPool = Executors.newFixedThreadPool(maxTask)
        mWorker = Worker(mPool)
    }

    var mResult: Any? = null

    override fun <T> doThisOnWork(task: Callable<T>): Run {

        if (mType == TaskType.SYN) {
            mResult = mWorker.doOnWork(task)
        } else {
            mPool.submit(task)
        }

        return mInstance

    }

    override fun doThisOnMain(task: Runnable): Run {

        mainHandler.post(task)

        return mInstance
    }


    override fun go() {
        try {
            mPool.shutdown()
        } catch (e: Exception) {
            mPool.shutdownNow()
        }
    }

    override fun destroyNow() {
        if (!mPool.isTerminated){
            mPool.shutdownNow()
            throw Exception("all work does not do finish")
        }

    }


    companion object {
        private lateinit var mInstance: WorkFactory

        fun doSynWork(): WorkFactory {
            mInstance = WorkFactory()
            return mInstance
        }

        fun doAsynWork(maxTask: Int): WorkFactory {
            mInstance = WorkFactory(maxTask)
            return mInstance
        }
    }

    enum class TaskType {
        //异步
        ASYN,
        //同步
        SYN
    }

}