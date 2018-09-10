package com.example.testapp.view.bound

import android.app.Activity
import android.app.Application
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import com.bankcomm.commlibrary.logger.Logger
import com.example.testapp.TestHomeApplication

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/29 17:14
 * anmnight@qq.com
 */
class WechatHeader(app: Application) : View.OnTouchListener {

    private var mTouchSlop = 0

    //view witch need bound
    private var mBoundView: View? = null

    //bound view top margin
    private var mBoundViewTopMargin = 0

    //bound view layoutParams
    private var mBoundLayoutParams: ViewGroup.MarginLayoutParams? = null

    private lateinit var logger: Logger

    init {
        mTouchSlop = ViewConfiguration.get(app.applicationContext).scaledTouchSlop

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


        mBoundView!!.setOnTouchListener(this)


    }


    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     * the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        logger.debug("onTouch")

        return v?.onTouchEvent(event)!!
    }


}