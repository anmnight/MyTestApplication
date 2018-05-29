package com.example.testapp.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.View
import android.view.animation.AnimationUtils

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/4 17:32
 * anmnight@qq.com
 */
class ViewTest {

    fun testDraw(context: Context) {

        val view = View(context)

        val opt = BitmapFactory.Options()

        opt.inJustDecodeBounds = true

        val bmp = BitmapFactory.decodeFile("", opt)





    }
}