package com.example.testapp.android

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import unit.Logger

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/7 15:30
 * anmnight@qq.com
 */
class ViewGroupTest : ViewGroup {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        Logger.info("dispatchTouchEvent : ${super.dispatchTouchEvent(ev)}")

        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        Logger.info("onInterceptTouchEvent : ${super.onInterceptTouchEvent(ev)}")

        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Logger.info("onTouchEvent : ${super.onTouchEvent(event)}")

        return true
    }





}