package com.bankcomm.commlibrary.task

import android.os.Handler
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.bankcomm.commlibrary.task.Work.*

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 10:21
 * anmnight@qq.com
 */
class WorkFactory : Run {

    private var mType: TaskType
    private var mPool: ExecutorService
    private val mTasks = ArrayList<Work>()

    //todo 重构工程模式
    //todo 获取android mainThread
    //todo 检查pool 终止后延时任务执行状态（task3）
    private val mainHandler = Handler()

    constructor() {
        mType = TaskType.SYN
        mPool = Executors.newSingleThreadExecutor()

    }

    constructor(maxTask: Int) {
        mType = TaskType.ASYN
        mPool = Executors.newFixedThreadPool(maxTask)
    }


    override fun doThisOnWork(task: Runnable): Run {

        mTasks.add(Work(task, RunType.WORK))
        return mInstance
    }

    override fun doThisOnMain(task: Runnable): Run {

        mTasks.add(Work(task, RunType.MAIN))
        return mInstance
    }

    override fun run() {

        if (mType == TaskType.SYN) {
            for (work in mTasks) {
                doSynWork(work)
            }
        }

        if (mType == TaskType.ASYN) {
            for (work in mTasks) {
                mPool.execute(work.runnable)
            }
        }


        try {
            mPool.shutdown()
        } catch (e: Exception) {
            mPool.shutdownNow()
        }

    }


    //执行同步线程
    @Synchronized
    private fun doSynWork(work: Work) {
        if (work.runType == RunType.WORK) {
            val future = mPool.submit(work.runnable)
            while (!future.isDone) {
                //等待任务完成
            }
        }

        //主线程不可做耗时任务，线程无需等待回调
        if (work.runType == RunType.MAIN) {
            mainHandler.post { work.runnable }
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