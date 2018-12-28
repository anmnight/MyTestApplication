package com.anmnight.remoteprocess

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.anmnight.remoteprocess.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/26 10:26
 * anmnight@qq.com
 */
class FrameViewEvent(frameView: View, callback: Callback) : View.OnClickListener {


    private var button: Button = frameView.findViewById(R.id.button)
    private var textView: TextView = frameView.findViewById(R.id.text_view)

    private var callback = callback

    init {
        button.setOnClickListener(this)
    }

    private val TAG = "FrameViewEvent"

    override fun onClick(v: View?) {

        val dateFormat = SimpleDateFormat("yyyyMMddHms", Locale.CHINA)

        val time = dateFormat.format(System.currentTimeMillis())

        val message = "at $time , Server send a message"

        callback.send(message)

    }

    interface Callback {
        fun send(message: String)
    }


}
