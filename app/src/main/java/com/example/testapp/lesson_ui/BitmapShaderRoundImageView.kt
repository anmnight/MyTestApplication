package com.example.testapp.lesson_ui

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

class BitmapShaderRoundImageView : AppCompatImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private var mPaint:Paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        mPaint.shader = drawable2BitmapShader(drawable)
        canvas!!.drawCircle(200F, 200F, 200F, mPaint)
    }


    private fun drawable2BitmapShader(drawable: Drawable): BitmapShader {
        if (drawable is BitmapDrawable) {
            return BitmapShader(drawable.bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
        }
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
    }
}