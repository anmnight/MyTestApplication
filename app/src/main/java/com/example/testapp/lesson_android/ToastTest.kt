package com.example.testapp.lesson_android

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/8 10:07
 * anmnight@qq.com
 */
class ToastTest(context: Context?) : Toast(context) {

    override fun setView(view: View?) {
        super.setView(view)
    }
}