package com.bankcomm.commlibrary.logger

import android.annotation.SuppressLint
import android.app.Application
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.bankcomm.commlibrary.BuildConfig
import com.bankcomm.commlibrary.storage.SdPath
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/23 19:41
 * anmnight@qq.com
 */
class LogFactory(private val ctx: Application) : Logger {

    private val Tag = "commlibrary"
    private var writerHandler: Handler? = null
    private val writerThread = HandlerThread("log")

    init {
        writerThread.start()
    }

    override fun debug(message: Any) {
        if (BuildConfig.DEBUG) {
            Log.d(Tag, message.toString())
        }
    }

    override fun info(message: Any) {
        Log.i(Tag, message.toString())
    }

    @SuppressLint("SimpleDateFormat")
    override fun log(message: Any) {

        //todo 类信息获取

        if (writerHandler == null) {
            writerHandler = Handler(writerThread.looper)
        }

        val fileName = "${SimpleDateFormat("yyyyMMdd").format(Date())}.log"

        val file = File(SdPath.externalFile(ctx = ctx), fileName)

        if (!file.exists()) {
            file.createNewFile()
        }

        val fileWriter = FileWriter(file, true)

        info(message)

        val task = Runnable {
            fileWriter.write("-----------------------------------")
            fileWriter.write("\r\n")
            fileWriter.write(message.toString())
            fileWriter.write("\r\n")
            fileWriter.close()
        }
        writerHandler?.post(task)

    }


    object Builder {

        private var mLogFactory: LogFactory? = null

        fun initLogService(ctx: Application): LogFactory {

            return if (mLogFactory != null) {
                mLogFactory!!
            } else {
                LogFactory(ctx)
            }

        }
    }


}