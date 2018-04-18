package com.example.testapp.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent


class TableRecycleView : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)


    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        return true
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {

        if (e.action==MotionEvent.ACTION_MOVE){
            mEvent?.onMove(e)
        }
        return true
    }
    var mEvent:TouchEvent?=null

    fun registerListener(event: TouchEvent){
        mEvent = event
    }

    interface TouchEvent {
        fun onMove(e:MotionEvent)
    }
}
