package com.example.anxiao.mytestapplication.customer_drawable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import unit.DisPlayUnit


class CustomerView : View {

    private var mWidth:Int = 0
    private var mHeight:Int = 0
    private var mPaint: Paint=Paint()

    constructor(context: Context):super(context)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)


    /**
     * onMeasure 计算控件大小
     * onLayout　计算控件位置
     * onDraw　设置空间属性
     *
     */

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val display = DisPlayUnit.devicesDisPlay()
        mWidth = Math.round(display.widthPixels*0.8).toInt()
        mHeight = Math.round((mWidth*5/3).toDouble()).toInt()

        setMeasuredDimension(mWidth, mHeight)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.color = Color.BLUE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = 3F

        val top:Float = ((DisPlayUnit.devicesDisPlay().heightPixels-mHeight)/2).toFloat()
        val left:Float = ((DisPlayUnit.devicesDisPlay().widthPixels-mWidth)/2).toFloat()
        val bottom:Float = top+mHeight
        val right:Float = left+mWidth

        canvas.drawRect(top,left,bottom,right,mPaint)

    }
}