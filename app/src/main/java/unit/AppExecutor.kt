package unit

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask
import javax.inject.Inject
import javax.inject.Singleton

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/6/1 10:05
 * anmnight@qq.com
 */
@Singleton
class AppExecutor {

    private var diskIO: Executor
    private var networkIO: Executor
    private var mainThread: Executor

    @Inject
    constructor(diskIO: Executor,
                networkIO: Executor,
                mainThread: Executor) {
        this.diskIO = diskIO
        this.networkIO = networkIO
        this.mainThread = mainThread
    }


    fun diskIO(): Executor = diskIO

    fun networkIO(): Executor = networkIO

    fun mainThread(): Executor = mainThread


    fun <T> run(executor: Executor, callable: Callable<T>): T {
        val task = FutureTask<T>(callable)
        executor.execute(task)
        return task.get()
    }


    companion object {
        const val THREAD_COUNT = 4

        class MainThreadExecutor : Executor {
            @Inject
            constructor()

            private val handler = Handler(Looper.getMainLooper())
            override fun execute(command: Runnable?) {
                handler.post(command)
            }
        }

        class DiskIOExecutor : Executor {

            @Inject
            constructor()


            private val executor = Executors.newSingleThreadExecutor()
            override fun execute(command: Runnable?) {
                executor.execute(command)
            }
        }

        class NetworkIOExecutor : Executor {

            @Inject
            constructor()

            private val executor = Executors.newFixedThreadPool(THREAD_COUNT)
            override fun execute(command: Runnable?) {
                executor.execute(command)
            }
        }
    }
}