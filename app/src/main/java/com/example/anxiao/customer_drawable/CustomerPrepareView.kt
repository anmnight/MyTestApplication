package com.example.anxiao.customer_drawable

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView

class CustomerPrepareView : ImageView {

    private var mWidth:Int = 0
    private var mHeight:Int = 0
    private var mTop:Int =0
    private var mLeft:Int =0
    private var mPaint: Paint=Paint()

    constructor(context: Context):super(context)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)


    /**
     * onMeasure 计算控件大小
     * onLayout　计算控件位置
     * onDraw　设置空间属性
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        mWidth = Math.round(width*0.6).toInt()
        mHeight = Math.round(height*0.6).toInt()
        mTop = Math.round(height*0.2).toInt()
        mLeft = Math.round(width*0.2).toInt()

//        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val shader = drawable2BitmapShader(drawable)
        mPaint.shader = shader
        mPaint.color = Color.RED
        mPaint.strokeWidth = 1F

        canvas.drawRect(
                mLeft.toFloat(),
                mTop.toFloat(),
                (mLeft+mWidth).toFloat(),
                (mTop+mHeight).toFloat(),
                mPaint)
    }

    private fun drawable2BitmapShader(drawable: Drawable): BitmapShader {
        if (drawable is BitmapDrawable) {
            return BitmapShader(drawable.bitmap,Shader.TileMode.MIRROR,Shader.TileMode.MIRROR )
        }
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return BitmapShader(bitmap,Shader.TileMode.MIRROR,Shader.TileMode.MIRROR)
    }
}