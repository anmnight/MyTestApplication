package com.example.testapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.testapp.R

class WindowsUtils(context: Context) {

    private var mAppContext: Context = context.applicationContext

    @SuppressLint("InflateParams")
    fun showDialog(message: String) {
        val wm = mAppContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val view = LayoutInflater.from(mAppContext).inflate(R.layout.ui_float_view,null,false)
        val params = WindowManager.LayoutParams()
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG
        wm.addView(view,params)
    }

}
