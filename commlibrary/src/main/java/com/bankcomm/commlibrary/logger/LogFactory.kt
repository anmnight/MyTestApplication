package com.bankcomm.commlibrary.logger

import android.util.Log
import com.bankcomm.commlibrary.BuildConfig

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/23 19:41
 * anmnight@qq.com
 */
class LogFactory : Logger {

    private val Tag = "commlibrary"

    override fun debug(message: Any) {
        if (BuildConfig.DEBUG) {
            Log.d(Tag, message.toString())
        }
    }

    override fun info(message: Any) {
        Log.i(Tag, message.toString())
    }

    //todo 任何版本执行，记录信息进 .log
    override fun log(message: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        val Instance: Logger = FactoryHodler.log
    }

    private object FactoryHodler {
        val log = LogFactory()
    }

}