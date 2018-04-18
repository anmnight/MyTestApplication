package com.example.testapp.lesson_android.roomstatus

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView


class OberverScrollView : ScrollView {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        mListener.onYChange(t - oldt)
    }

    lateinit var mListener: ScrollListener
    fun setListener(listener: ScrollListener) {
        mListener = listener
    }

    interface ScrollListener {
        fun onYChange(y: Int)
    }
}