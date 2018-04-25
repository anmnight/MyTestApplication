package unit

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewConfiguration
import android.view.LayoutInflater
import android.support.v7.widget.LinearLayoutManager
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.ScrollView
import com.example.testapp.app.Logger
import com.example.testapp.mytestapplication.R
import java.lang.Thread.sleep


/**
 * Created by zeusqwer on 2018/4/14.
 * refresh
 */
class RefreshScrollLayout : LinearLayout, View.OnTouchListener {

    /**
     * 下拉状态
     */
    val STATUS_PULL_TO_REFRESH = 0

    /**
     * 释放立即刷新状态
     */
    val STATUS_RELEASE_TO_REFRESH = 1

    /**
     * 正在刷新状态
     */
    val STATUS_REFRESHING = 2

    /**
     * 刷新完成或未刷新状态
     */
    val STATUS_REFRESH_FINISHED = 3

    /**
     * 下拉头部回滚的速度
     */
    val SCROLL_SPEED = -20

    /**
     * 下拉头的布局参数
     */
    private lateinit var mHeaderLayoutParams: ViewGroup.MarginLayoutParams

    /**
     * 当前处理什么状态
     */
    private var mCurrentStatus = STATUS_REFRESH_FINISHED

    private var mLastStatus = mCurrentStatus

    /**
     * 是否已加载过一次layout，这里onLayout中的初始化只需加载一次
     */
    private var loadOnce: Boolean = false

    /**
     * 当前是否可以下拉，只有Recycle滚动到头的时候才允许下拉
     */
    private var mAbleToPull: Boolean = false


    @SuppressLint("InflateParams")
    private var mHeader: View = LayoutInflater.from(context).inflate(R.layout.componts_refresh_header, null, true)
    private var mHeaderHight: Int = 0
    private var mHideHeaderHeight: Int = -150


    /**
     * 手指按下时的屏幕纵坐标
     */
    private var mYdown: Float = 0F

    /**
     * 在被判定为滚动之前用户手指可以移动的最大值。
     */
    private var mTouchSlop: Int = 0

    /**
     * 下拉刷新的回调接口
     */
    private lateinit var mListener: PullToRefreshListener

    private var mParentLayout: LinearLayout


    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {

        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop

        mParentLayout = LinearLayout(context)

        addView(mHeader)

    }


    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        if (changed && !loadOnce) {

//            val childview = getChildAt(0)
//
//            listView = childview as RecyclerView
//
//            removeAllViews()
//
//            addView(mParentLayout)
//
//            mParentLayout.orientation = LinearLayout.VERTICAL
//            val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
//            mParentLayout.layoutParams = layoutParams
//
//            if (mHeader.parent is ViewGroup) {
//                val temp = mHeader.parent as ViewGroup
//                temp.removeView(mHeader)
//            }
//
//            mParentLayout.addView(mHeader, 0)
//            mHeaderLayoutParams = mHeader.layoutParams as MarginLayoutParams
//            mHeaderLayoutParams.topMargin = mHideHeaderHeight
//            mHeader.layoutParams = mHeaderLayoutParams
//
//            if (childview.parent is ViewGroup) {
//                val temp = childview.parent as ViewGroup
//                temp.removeView(childview)
//            }
//
//            mParentLayout.addView(childview, 1)
//
////            this.layoutParams = mParentLayout.layoutParams


            mHeaderLayoutParams = mHeader.layoutParams as MarginLayoutParams
            mHeaderLayoutParams.topMargin = mHideHeaderHeight
            mHeader.layoutParams = mHeaderLayoutParams
            mHeaderHight = mHeader.height


            mListView = getChildAt(1) as RecyclerView

            mListView.setOnTouchListener(this)
            loadOnce = true
        }
    }

    private lateinit var mListView: RecyclerView


    private fun setIsAbleToPull(event: MotionEvent) {
        val firstChild = mListView.getChildAt(0)
        if (firstChild != null) {
            val layoutManager = mListView.layoutManager
            val linearManager = layoutManager as LinearLayoutManager
            val firstVisiblePos = linearManager.findFirstVisibleItemPosition()

            if (firstVisiblePos == 0 && firstChild.top == 0) {
                mYdown = event.rawY
                mAbleToPull = true
            } else {
                if (mHeaderLayoutParams.topMargin != mHideHeaderHeight) {
                    mHeaderLayoutParams.topMargin = mHideHeaderHeight
                    mHeader.layoutParams = mHeaderLayoutParams
                }
                mAbleToPull = false
            }
        } else {
            mAbleToPull = true
        }
    }


    //检查头部是否可以下拉
    fun ableToPull(): Boolean {
        val layoutManager = mListView.layoutManager
        val linearManager = layoutManager as LinearLayoutManager
        val firstVisiblePos = linearManager.findFirstVisibleItemPosition()

        return firstVisiblePos == 0
    }

    //检查头部是否可以向上滑动
    fun ableToSwipeUp(): Boolean {
        return mHeaderLayoutParams.topMargin > -mHeaderHight

    }


    override fun onTouch(view: View?, event: MotionEvent?): Boolean {


//        if (event != null) {
//            setIsAbleToPull(event)
//        }

//        if (mAbleToPull) {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> mYdown = event.rawY

            MotionEvent.ACTION_MOVE -> {
                val yMove = event.rawY
                val distance = yMove - mYdown


                if (distance > 0 && ableToPull()) {
                    mHeaderLayoutParams.topMargin = (distance / 2 + mHideHeaderHeight).toInt()
                    mHeader.layoutParams = mHeaderLayoutParams

                    if (mCurrentStatus != STATUS_REFRESHING) {
                        mCurrentStatus = if (mHeaderLayoutParams.topMargin > 0)
                            STATUS_RELEASE_TO_REFRESH
                        else
                            STATUS_PULL_TO_REFRESH
                    }
                }

                if (distance < 0 && ableToSwipeUp()) {
                    mHeaderLayoutParams.topMargin = distance.toInt()
                    mHeader.layoutParams = mHeaderLayoutParams
                }


//                if (distance <= 0 && mHeaderLayoutParams.topMargin <= mHideHeaderHeight) {
//                    return false
//                }
//                if (distance < mTouchSlop) {
//                    return false
//                }
//                if (mCurrentStatus != STATUS_REFRESHING) {
//                    mCurrentStatus = if (mHeaderLayoutParams.topMargin > 0) {
//                        STATUS_RELEASE_TO_REFRESH
//                    } else {
//                        STATUS_PULL_TO_REFRESH
//                    }
//                    // 通过偏移下拉头的topMargin值，来实现下拉效果
//                    mHeaderLayoutParams.topMargin = (distance / 2 - 150).toInt()
//                    mHeader.layoutParams = mHeaderLayoutParams
//                }
            }

//            else -> {
//                if (mCurrentStatus == STATUS_RELEASE_TO_REFRESH) {
//                    RefreshingTask().execute()
//                } else if (mCurrentStatus == STATUS_PULL_TO_REFRESH) {
//                    HideHeaderTask().execute()
//                }
//            }
        }

//        if (mCurrentStatus == STATUS_PULL_TO_REFRESH || mCurrentStatus == STATUS_RELEASE_TO_REFRESH) {
//            // 当前正处于下拉或释放状态，要让ListView失去焦点，否则被点击的那一项会一直处于选中状态
//            isPressed = false
//            isFocusable = false
//            isFocusableInTouchMode = false
//            mLastStatus = mCurrentStatus
//            return true
//        }
//        }

        return false
    }


    /**
     * 当所有的刷新逻辑完成后，记录调用一下，否则你的ListView将一直处于正在刷新状态。
     */
    fun finishRefreshing() {
        mCurrentStatus = STATUS_REFRESH_FINISHED
        HideHeaderTask().execute()
    }

    /**
     * 正在刷新的任务，在此任务中会去回调注册进来的下拉刷新监听器。
     *
     * @author guolin
     */
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
                sleep(10)
            }
            mCurrentStatus = STATUS_REFRESHING
            publishProgress(0)
            mListener.onRefresh()
            return null
        }

        override fun onProgressUpdate(vararg topMargin: Int?) {
            mHeaderLayoutParams.topMargin = topMargin[0]!!
            mHeader.layoutParams = mHeaderLayoutParams
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
                sleep(10)
            }
            return topMargin
        }

        override fun onProgressUpdate(vararg topMargin: Int?) {
            mHeaderLayoutParams.topMargin = topMargin[0]!!
            mHeader.layoutParams = mHeaderLayoutParams
        }

        override fun onPostExecute(topMargin: Int?) {
            mHeaderLayoutParams.topMargin = topMargin!!
            mHeader.layoutParams = mHeaderLayoutParams
            mCurrentStatus = STATUS_REFRESH_FINISHED
        }
    }


    fun setOnRefreshListener(listener: PullToRefreshListener) {
        mListener = listener
    }

    interface PullToRefreshListener {
        fun onRefresh()
    }

}