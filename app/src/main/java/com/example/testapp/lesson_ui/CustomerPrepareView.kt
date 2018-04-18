package com.example.testapp.lesson_ui

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import com.example.testapp.app.Logger
import unit.DisPlayUnit

class CustomerPrepareView : ImageView {

    private var mWidth:Int = 0
    private var mHeight:Int = 0

    var dWidth:Int =0
    var dHeight:Int=0
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
         mWidth = MeasureSpec.getSize(widthMeasureSpec)
         mHeight = MeasureSpec.getSize(heightMeasureSpec)

         dWidth = drawable.intrinsicWidth
         dHeight = drawable.intrinsicHeight


        Logger.info("Spec : $mWidth , $mHeight")
        Logger.info("Drawable : $dWidth,$dHeight")
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val shader = drawable2BitmapShader(drawable)

        mPaint.shader = shader
        mPaint.color = Color.RED
        mPaint.strokeWidth = 1F

        val top = ((DisPlayUnit.devicesDisPlay().heightPixels-drawable.intrinsicHeight)/2).toFloat()
        val bottom = drawable.intrinsicHeight+top

        canvas.drawRect(0F, 0F, mWidth.toFloat(), dHeight.toFloat(), mPaint)

    }

    private fun drawable2BitmapShader(drawable: Drawable): BitmapShader {
        if (drawable is BitmapDrawable)
            return BitmapShader(drawable.bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP )
        val bitmap = Bitmap.createBitmap(dWidth, dHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0,0,mWidth,dHeight)
        drawable.draw(canvas)
        return BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
    }


    fun drawable2Bitmap(drawable: Drawable):Bitmap{
        if (drawable is BitmapDrawable)
            return drawable.bitmap

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,drawable.intrinsicHeight,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0,0,drawable.intrinsicWidth,drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }
}