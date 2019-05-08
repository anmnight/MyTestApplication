package com.example.testapp.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;

import androidx.annotation.CallSuper;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TitleViewPager extends ViewPager {

    private static final String tag = "TitleViewPager";

    public TitleViewPager(@NonNull Context context) {
        super(context);
    }

    public TitleViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        final int count = getChildCount();
//        int width = r - l;
//        int height = b - t;
//        int paddingLeft = getPaddingLeft();
//        int paddingTop = getPaddingTop();
//        int paddingRight = getPaddingRight();
//        int paddingBottom = getPaddingBottom();
//        final int scrollX = getScrollX();
//
//        int decorCount = 0;
//
//        // First pass - decor views. We need to do this in two passes so that
//        // we have the proper offsets for non-decor views later.
//        for (int i = 0; i < count; i++) {
//            final View child = getChildAt(i);
//            if (child.getVisibility() != GONE) {
//                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
//                int childLeft = 0;
//                int childTop = 0;
//                if (lp.isDecor) {
//                    final int hgrav = lp.gravity & Gravity.HORIZONTAL_GRAVITY_MASK;
//                    final int vgrav = lp.gravity & Gravity.VERTICAL_GRAVITY_MASK;
//                    switch (hgrav) {
//                        default:
//                            childLeft = paddingLeft;
//                            break;
//                        case Gravity.LEFT:
//                            childLeft = paddingLeft;
//                            paddingLeft += child.getMeasuredWidth();
//                            break;
//                        case Gravity.CENTER_HORIZONTAL:
//                            childLeft = Math.max((width - child.getMeasuredWidth()) / 2,
//                                    paddingLeft);
//                            break;
//                        case Gravity.RIGHT:
//                            childLeft = width - paddingRight - child.getMeasuredWidth();
//                            paddingRight += child.getMeasuredWidth();
//                            break;
//                    }
//                    switch (vgrav) {
//                        default:
//                            childTop = paddingTop;
//                            break;
//                        case Gravity.TOP:
//                            childTop = paddingTop;
//                            paddingTop += child.getMeasuredHeight();
//                            break;
//                        case Gravity.CENTER_VERTICAL:
//                            childTop = Math.max((height - child.getMeasuredHeight()) / 2,
//                                    paddingTop);
//                            break;
//                        case Gravity.BOTTOM:
//                            childTop = height - paddingBottom - child.getMeasuredHeight();
//                            paddingBottom += child.getMeasuredHeight();
//                            break;
//                    }
//                    childLeft += scrollX;
//                    child.layout(childLeft, childTop,
//                            childLeft + child.getMeasuredWidth(),
//                            childTop + child.getMeasuredHeight());
//                    decorCount++;
//                }
//            }
//        }
//
//        final int childWidth = width - paddingLeft - paddingRight;
//        // Page views. Do this once we have the right padding offsets from above.
//        for (int i = 0; i < count; i++) {
//            final View child = getChildAt(i);
//            if (child.getVisibility() != GONE) {
//                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
//                ItemInfo ii;
//                if (!lp.isDecor && (ii = infoForChild(child)) != null) {
//                    int loff = (int) (childWidth * ii.offset);
//                    int childLeft = paddingLeft + loff;
//                    int childTop = paddingTop;
//                    if (lp.needsMeasure) {
//                        // This was added during layout and needs measurement.
//                        // Do it now that we know what we're working with.
//                        lp.needsMeasure = false;
//                        final int widthSpec = MeasureSpec.makeMeasureSpec(
//                                (int) (childWidth * lp.widthFactor),
//                                MeasureSpec.EXACTLY);
//                        final int heightSpec = MeasureSpec.makeMeasureSpec(
//                                (int) (height - paddingTop - paddingBottom),
//                                MeasureSpec.EXACTLY);
//                        child.measure(widthSpec, heightSpec);
//                    }
//
//                    child.layout(childLeft, childTop,
//                            childLeft + child.getMeasuredWidth(),
//                            childTop + child.getMeasuredHeight());
//                }
//            }
//        }
//
//
//
//    }
//



}
