package com.bankcomm.commlibrary.task

import android.os.Handler
import android.os.Looper
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 10:21
 * anmnight@qq.com
 */
class WorkFactory : Run {

    private var mType: TaskType
    private var mPool: ExecutorService

    private val mainHandler = Handler(Looper.getMainLooper())

    //任务队列
    private val mTasks = ArrayList<Work>()

    constructor() {
        mType = TaskType.SYN
        mPool = Executors.newSingleThreadExecutor()
    }

    constructor(maxTask: Int) {
        mType = TaskType.ASYN
        mPool = Executors.newFixedThreadPool(maxTask)
    }

    override fun doThisOnWork(work: Runnable): Run {
        mTasks.add(Work(work, Work.WorkType.WORK))
        return mInstance

    }

    override fun doThisOnMain(work: Runnable): Run {
        mTasks.add(Work(work, Work.WorkType.MAIN))
        return mInstance
    }


    override fun go() {

        if (mType == TaskType.ASYN) {
            goAsyn()
        }

        if (mType == TaskType.SYN) {
            goSyn()
        }

    }

    private fun goAsyn() {
        mTasks.forEach { it ->
            if (it.type == Work.WorkType.MAIN) {
                mainHandler.post(it.work)
            }
            if (it.type == Work.WorkType.WORK) {
                mPool.submit(it.work)
            }
        }
    }

    //todo 任务超时，注销任务
    private fun goSyn() {
        Thread {
            mTasks.forEach { it ->
                if (it.type == Work.WorkType.MAIN) {
                    mainHandler.post(it.work)
                } else {
                    val future = mPool.submit(it.work)
                    while (!future.isDone) {
                    }
                }
            }
        }.start()

    }

    override fun destroyNow() {
        if (!mPool.isTerminated) {
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