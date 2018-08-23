package com.example.testapp.android.wave

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class WaveView @JvmOverloads constructor(context: Context,
                                         attrs: AttributeSet? = null,
                                         defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {


    val mStart = PointF(0F, 0F)
    val mCenter = PointF(0F, 0F)
    val mEnd = PointF(0F, 0F)

    val mWaveHeight = 400
    val mWaveWidth = 800


    private val mPaint = Paint()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }



    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawLine(0F, 0F, 200F, 200F, mPaint)






    }



    private fun drawWaveLine() {

    }
}