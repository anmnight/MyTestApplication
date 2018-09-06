package com.example.testapp.view.wave

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.testapp.R

class WaveView @JvmOverloads constructor(context: Context,
                                         attrs: AttributeSet? = null,
                                         defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private val mPath = Path()

    private val mPaint = Paint()

    private var mWidth = 0
    private var mHeight = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        mPaint.color = context.resources.getColor(R.color.white)


        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPath.reset()
        mPath.moveTo(0F, (mHeight / 2).toFloat())
        mPath.quadTo(300F, 100F, 526F, (mHeight / 2).toFloat())
        canvas?.drawPath(mPath, mPaint)


    }

}