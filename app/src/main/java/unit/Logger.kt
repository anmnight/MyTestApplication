package unit

import android.util.Log

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/3 15:07
 * anmnight@qq.com
 */
object Logger {

    private const val TAG = "TestApplication"

    fun debug(any: Any) {
        Log.d(TAG, any.toString())
    }

    fun info(any: Any) {
        Log.i(TAG, any.toString())
    }

    fun error(any: Any) {
        Log.e(TAG, any.toString())
    }
}