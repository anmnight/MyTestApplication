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
class AppExecutor constructor(private var diskIO: Executor, private var networkIO: Executor, private var mainThread: Executor) {


    fun diskIO(): Executor = diskIO

    fun networkIO(): Executor = networkIO

    fun mainThread(): Executor = mainThread

    companion object {
        const val THREAD_COUNT = 4

        class MainThreadExecutor @Inject constructor() : Executor {
            private val handler = Handler(Looper.getMainLooper())
            override fun execute(command: Runnable?) {
                handler.post(command)
            }
        }

        class DiskIOExecutor @Inject constructor() : Executor {
            private val executor = Executors.newSingleThreadExecutor()
            override fun execute(command: Runnable?) {
                executor.execute(command)
            }
        }

        class NetworkIOExecutor @Inject constructor() : Executor {

            private val executor = Executors.newFixedThreadPool(THREAD_COUNT)
            override fun execute(command: Runnable?) {
                executor.execute(command)
            }
        }
    }
}