package com.example.testapp.mytestapplication.customer_drawable

import android.content.Context

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * Created by anxiao on 2017/8/11.
 * xfermode round image
 */

class XfermodeRoundImageView : AppCompatImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    override fun onDraw(canvas: Canvas) {
        val width = canvas.width - paddingLeft - paddingRight
        val height = canvas.height - paddingTop - paddingBottom
        val bitmap = drawable2Bitmap(drawable)
        canvas.drawBitmap(bitmapToCircleBitmap(bitmap, Math.min(width, height) / 2), 0f, 0f, null)
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

    private fun bitmapToCircleBitmap(oBitmap: Bitmap, radius: Int): Bitmap {
        val paint = Paint()
        paint.isAntiAlias = true
        val cBitmap = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(cBitmap)
        canvas.drawCircle(radius.toFloat(), radius.toFloat(), radius.toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        val oWidth = oBitmap.width
        val oHeight = oBitmap.height
        val margin: Float
        if(oWidth>oHeight){
            margin = ((oWidth-oHeight)/2).toFloat()
            canvas.drawBitmap(oBitmap, (0-margin), 0f, paint)
        }else{
            margin = ((oHeight-oWidth)/2).toFloat()
            canvas.drawBitmap(oBitmap, 0f, (0-margin), paint)
        }

        return cBitmap
    }
}
