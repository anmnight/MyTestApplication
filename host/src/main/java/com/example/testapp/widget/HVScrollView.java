package com.example.testapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;

public class HVScrollView extends FrameLayout implements NestedScrollingChild2 {

    private String tag = "HVScrollView";
    private Context context;
    private AttributeSet attrs;
    private NestedScrollingChildHelper mNestedScrollingChildHelper;
    private int[] consumed = new int[2];
    private int[] offset = new int[2];

    public HVScrollView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public HVScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HVScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public HVScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private int mLastX = 0;
    private int mLastY = 0;
    private boolean mIsBeingDragged = false;
    private int mOverScrollDistance;
    private int mTouchSlop;

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        this.attrs = attrs;

        ViewConfiguration configuration = ViewConfiguration.get(context);

        mTouchSlop = configuration.getScaledTouchSlop();

        mOverScrollDistance = configuration.getScaledOverscrollDistance();

        mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        mNestedScrollingChildHelper.setNestedScrollingEnabled(true);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                mIsBeingDragged = true;

                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;

            case MotionEvent.ACTION_MOVE:

                int curX = (int) event.getX();
                int curY = (int) event.getY();

                int deltaX = mLastX - curX;
                int deltaY = mLastY - curY;

                if (!mIsBeingDragged && (Math.abs(deltaX) > mTouchSlop || Math.abs(deltaY) > mTouchSlop)) {
                    mIsBeingDragged = true;

                    if (deltaX > 0) {
                        deltaX -= mTouchSlop;
                    } else {
                        deltaX += mTouchSlop;
                    }
                    if (deltaY > 0) {
                        deltaY -= mTouchSlop;
                    } else {
                        deltaY += mTouchSlop;
                    }
                }

                if (mIsBeingDragged) {

                    overScrollByCompat(deltaX, deltaY, getScrollX(), getScrollY(),
                            getScrollRangeX(), getScrollRangeY(), mOverScrollDistance, mOverScrollDistance);

                    mLastX = curX;
                    mLastY = curY;
                }

                break;

            default:

                mLastX = 0;
                mLastY = 0;
                mIsBeingDragged = false;

                break;

        }

        return mIsBeingDragged;


    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {


        if (
                (mNestedScrollingChildHelper.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
                        || mNestedScrollingChildHelper.startNestedScroll(ViewCompat.SCROLL_AXIS_HORIZONTAL)
                )
                        &&
                        mNestedScrollingChildHelper.dispatchNestedPreScroll(scrollX, scrollY, consumed, offset)) {

            super.scrollTo(scrollX, scrollY);
        }

    }


    private int getScrollRangeY() {
        int scrollRange = 0;
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            NestedScrollView.LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int childSize = child.getHeight() + lp.topMargin + lp.bottomMargin;
            int parentSpace = getHeight() - getPaddingTop() - getPaddingBottom();
            scrollRange = Math.max(0, childSize - parentSpace);
        }
        return scrollRange;
    }

    private int getScrollRangeX() {
        int scrollRange = 0;
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            NestedScrollView.LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int childSize = child.getWidth() + lp.leftMargin + lp.rightMargin;
            int parentSpace = getWidth() - getPaddingLeft() - getPaddingRight();
            scrollRange = Math.max(0, childSize - parentSpace);
        }
        return scrollRange;
    }

    //设定滚动边界
    private void overScrollByCompat(int deltaX, int deltaY,
                                    int scrollX, int scrollY,
                                    int scrollRangeX, int scrollRangeY,
                                    int maxOverScrollX, int maxOverScrollY) {

        final int overScrollMode = getOverScrollMode();

        final boolean canScrollHorizontal =
                computeHorizontalScrollRange() > computeHorizontalScrollExtent();

        final boolean canScrollVertical =
                computeVerticalScrollRange() > computeVerticalScrollExtent();

        final boolean overScrollHorizontal = overScrollMode == View.OVER_SCROLL_ALWAYS
                || (overScrollMode == View.OVER_SCROLL_IF_CONTENT_SCROLLS && canScrollHorizontal);

        final boolean overScrollVertical = overScrollMode == View.OVER_SCROLL_ALWAYS
                || (overScrollMode == View.OVER_SCROLL_IF_CONTENT_SCROLLS && canScrollVertical);

        int newScrollX = scrollX + deltaX;

        if (!overScrollHorizontal) {
            maxOverScrollX = 0;
        }

        int newScrollY = scrollY + deltaY;

        if (!overScrollVertical) {
            maxOverScrollY = 0;
        }

        // Clamp values if at the limits and record
        final int left = -maxOverScrollX;

        final int right = maxOverScrollX + scrollRangeX;

        final int top = -maxOverScrollY;

        final int bottom = maxOverScrollY + scrollRangeY;

        boolean clampedX = false;
        if (newScrollX > right) {

            newScrollX = right;

            clampedX = true;

        } else if (newScrollX < left) {

            newScrollX = left;
            clampedX = true;

        }

        boolean clampedY = false;
        if (newScrollY > bottom) {

            newScrollY = bottom;
            clampedY = true;

        } else if (newScrollY < top) {

            newScrollY = top;
            clampedY = true;
        }

        onOverScrolled(newScrollX, newScrollY, clampedX, clampedY);

    }


    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return (LayoutParams) new MarginLayoutParams(context, attrs);
    }


    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return mNestedScrollingChildHelper.startNestedScroll(axes, type);
    }

    @Override
    public void stopNestedScroll(int type) {
        mNestedScrollingChildHelper.stopNestedScroll(type);
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return mNestedScrollingChildHelper.hasNestedScrollingParent(type);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }
}
