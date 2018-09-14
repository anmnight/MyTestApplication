package com.example.testapp.view.bound

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import com.bankcomm.commlibrary.logger.Logger
import com.example.testapp.TestHomeApplication
import com.example.testapp.TestHomeApplication.application

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/29 17:14
 * anmnight@qq.com
 */
class WechatHeader : ViewGroup {


    private var mTouchSlop = 0

    //view witch need bound
    private var mBoundView: View? = null

    //bound view top margin
    private var mBoundViewTopMargin = 0

    //bound view layoutParams
    private var mBoundLayoutParams: ViewGroup.MarginLayoutParams? = null

    private lateinit var logger: Logger

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    init {
        mTouchSlop = ViewConfiguration.get(application.applicationContext).scaledTouchSlop

    }

    fun setActivityBound(activity: Activity) {


        logger = TestHomeApplication.application.mLogger


        val decorView: ViewGroup = activity.window.decorView as ViewGroup
        val linerLayout = decorView.getChildAt(0) as ViewGroup
        mBoundView = linerLayout.getChildAt(1) as ViewGroup

        mBoundLayoutParams = mBoundView?.layoutParams as ViewGroup.MarginLayoutParams
        mBoundViewTopMargin = mBoundLayoutParams!!.topMargin

        logger.debug(mBoundView as ViewGroup)

        logger.debug("mBoundViewTopMargin : $mBoundViewTopMargin")

    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}