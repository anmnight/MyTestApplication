package com.tw.moment.twmoment.compents

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout

class RefreshRecycleView : RecyclerView,View.OnTouchListener {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)


    //下拉状态
    val STATUS_PULL_TO_REFRESH = 0
    //释放立即刷新状态
    val STATUS_RELEASE_TO_REFRESH = 1
    //正在刷新状态
    val STATUS_REFRESHING = 2
    //刷新完成或未刷新状态
    val STATUS_REFRESH_FINISHED = 3

    /**
     * 下拉头部回滚的速度
     */
    val SCROLL_SPEED = -20

    private var loadOnce: Boolean = false
    private var mHideHeaderHeight: Int = -150
    private lateinit var mHeaderLayoutParams: ViewGroup.MarginLayoutParams

    //当前是否可以下拉，只有Recycle滚动到头的时候才允许下拉
    private var mAbleToPull: Boolean = false
    //手指按下时的屏幕纵坐标
    private var mYdown: Float = 0F
    //在被判定为滚动之前用户手指可以移动的最大值。
    private var mTouchSlop: Int = 0
    // 当前处理什么状态
    private var mCurrentStatus = STATUS_REFRESH_FINISHED
    private var mLastStatus = mCurrentStatus

    private lateinit var mListener: RefreshRecycleView.PullToRefreshListener


    private lateinit var mHeaderView:View

    init {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        if (changed && !loadOnce) {
            mHeaderView= getChildAt(0) as ViewGroup
            mHeaderLayoutParams = mHeaderView.layoutParams as MarginLayoutParams
            mHeaderLayoutParams.topMargin = mHideHeaderHeight
            mHeaderView.layoutParams = mHeaderLayoutParams

//            mHeaderImg = mHeaderView.getChildAt(0)
//            mHeaderImgLayoutParams = mHeaderImg.layoutParams as MarginLayoutParams


            //创建loading
//            val iv = ImageView(context)
//            iv.setImageResource(R.drawable.ic_refress_progress)
//            iv.minimumHeight=30
//            iv.maxHeight = 30
//            iv.minimumWidth= 30
//            iv.maxWidth = 30
//
//            mHeaderView.addView(iv)


            this.setOnTouchListener(this)
            loadOnce = true
        }

    }


    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        if (event != null) {
            setIsAbleToPull(event)
        }

        if (mAbleToPull) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN ->{
                    mYdown = event.rawY
                }

                MotionEvent.ACTION_MOVE -> {
                    val yMove = event.rawY
                    val distance = yMove - mYdown

                    if (distance <= 0 && mHeaderLayoutParams.topMargin <= mHideHeaderHeight) {
                        return false
                    }
                    if (distance < mTouchSlop) {
                        return false
                    }

                    if (mCurrentStatus != STATUS_REFRESHING) {

                        mCurrentStatus = if (mHeaderLayoutParams.topMargin > 0) {
                            STATUS_RELEASE_TO_REFRESH
                        } else {
                            STATUS_PULL_TO_REFRESH
                        }
//                        //通过偏移下拉头的topMargin值，来实现下拉效果
                        mHeaderLayoutParams.topMargin = (distance / 2 - 150).toInt()
                        mHeaderView.layoutParams = mHeaderLayoutParams
                        mHeaderView.invalidate()
                        mHeaderView.requestLayout()
                        adapter.notifyDataSetChanged()

//                        val bg = mHeaderView.getChildAt(0)
//                        val params = bg.layoutParams as MarginLayoutParams
//                        params.topMargin = (distance / 2 - 150).toInt()
//                        bg.layoutParams = params

//                        val icon = mHeaderView.getChildAt(3)
//                        val params = icon.layoutParams as MarginLayoutParams
//                        params.topMargin = (distance / 2 - 150).toInt()
//                        icon.layoutParams = params

                    }
                }

                else -> {
                    if (mCurrentStatus == STATUS_RELEASE_TO_REFRESH) {
                        RefreshingTask().execute()
                    } else if (mCurrentStatus == STATUS_PULL_TO_REFRESH) {
                        HideHeaderTask().execute()
                    }
                }
            }

            if (mCurrentStatus == STATUS_PULL_TO_REFRESH || mCurrentStatus == STATUS_RELEASE_TO_REFRESH) {
                // 当前正处于下拉或释放状态，要让ListView失去焦点，否则被点击的那一项会一直处于选中状态
                isPressed = false
                isFocusable = false
                isFocusableInTouchMode = false
                mLastStatus = mCurrentStatus
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    private fun setIsAbleToPull(event: MotionEvent) {
            val linearManager = layoutManager as LinearLayoutManager
            val firstVisiblePos = linearManager.findFirstVisibleItemPosition()
            if (firstVisiblePos == 0 && mHeaderView.top == mHideHeaderHeight) {
                if (mYdown==0F){
                    mYdown = event.rawY
                }
                mAbleToPull = true
            } else {
                if (mHeaderLayoutParams.topMargin != mHideHeaderHeight) {
                    mHeaderLayoutParams.topMargin = mHideHeaderHeight
                    mHeaderView.layoutParams = mHeaderLayoutParams
                }
                mAbleToPull = false
            }
    }


    @SuppressLint("StaticFieldLeak")
    internal inner class RefreshingTask : AsyncTask<Void, Int, Void>() {

        override fun doInBackground(vararg params: Void): Void? {

            var topMargin = mHeaderLayoutParams.topMargin

            while (true) {
                topMargin += SCROLL_SPEED
                if (topMargin <= 0) {
                    topMargin = 0
                    break
                }
                publishProgress(topMargin)
                Thread.sleep(10)
            }
            mCurrentStatus = STATUS_REFRESHING
            publishProgress(0)
            mListener.onRefresh()
            return null
        }

        override fun onProgressUpdate(vararg topMargin: Int?) {
            mHeaderLayoutParams.topMargin = topMargin[0]!!
            mHeaderView.layoutParams = mHeaderLayoutParams
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class HideHeaderTask : AsyncTask<Void, Int, Int>() {

        override fun doInBackground(vararg params: Void): Int? {
            var topMargin = mHeaderLayoutParams.topMargin
            while (true) {
                topMargin += SCROLL_SPEED
                if (topMargin <= mHideHeaderHeight) {
                    topMargin = mHideHeaderHeight
                    break
                }
                publishProgress(topMargin)
                Thread.sleep(10)
            }
            return topMargin
        }

        override fun onProgressUpdate(vararg topMargin: Int?) {
            mHeaderLayoutParams.topMargin = topMargin[0]!!
            mHeaderView.layoutParams = mHeaderLayoutParams
        }

        override fun onPostExecute(topMargin: Int?) {
            mHeaderLayoutParams.topMargin = topMargin!!
            mHeaderView.layoutParams = mHeaderLayoutParams
            mCurrentStatus = STATUS_REFRESH_FINISHED
        }
    }

    fun setOnRefreshListener(listener: PullToRefreshListener) {
        mListener = listener
    }

    fun finishRefreshing() {
        mCurrentStatus = STATUS_REFRESH_FINISHED
        HideHeaderTask().execute()
    }

    interface PullToRefreshListener {
        fun onRefresh()
    }
}