package com.example.anxiao.mytestapplication.customer_drawable

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

class BitmapShaderRoundImageView : AppCompatImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    override fun onDraw(canvas: Canvas?) {
        val oBitmap = drawable2Bitmap(drawable)
        val paint = Paint()
        val shader = BitmapShader(oBitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
        paint.shader = shader
        canvas!!.drawCircle(200F, 200F, 200F,paint)
    }


    private fun drawable2Bitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}