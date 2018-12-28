package com.example.testapp.mytestapplication.customer_drawable

import android.content.Context
import android.graphics.*

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

import androidx.appcompat.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * Created by anxiao on 2017/8/11.
 * xfermode round image
 */

class XfermodeRoundImageView : AppCompatImageView {

    private val mPaint = Paint()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private val porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)


    val cBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)



    override fun onDraw(canvas: Canvas) {


        val width = width - paddingLeft - paddingRight
        val height = height - paddingTop - paddingBottom

        if (drawable !is BitmapDrawable) {
            throw Exception("drawable is not bitmap")
        }

        val bitmap = scale((drawable as BitmapDrawable).bitmap)

        var left = 0F
        var top = 0F

        if (bitmap.width > bitmap.height) {

            left = ((width - bitmap.width) / 2).toFloat()

        } else {

            top = ((height - bitmap.height) / 2).toFloat()

        }

        val radius = if (width > height)
            height / 2
        else
            width / 2


        mPaint.isAntiAlias = true

        val cBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val xCanvas = Canvas(cBitmap)

        xCanvas.drawCircle(radius.toFloat(), radius.toFloat(), radius.toFloat(), mPaint)

        xCanvas.drawBitmap(cBitmap, 0F, 0F, mPaint)

        mPaint.xfermode = porterDuffXfermode

        xCanvas.drawBitmap(bitmap, left, top, mPaint)

        mPaint.xfermode = null

        canvas.drawBitmap(cBitmap, 0F, 0F, mPaint)

    }


    private fun scale(bitmap: Bitmap): Bitmap {

        val cWidth = width - paddingLeft - paddingRight
        val cHeight = height - paddingTop - paddingBottom

        val oWidth = bitmap.width
        val oHeight = bitmap.height

        val matrix = Matrix()
        val temp = if (oWidth > oHeight) {
            cHeight.toFloat() / oHeight
        } else {
            cWidth.toFloat() / oWidth
        }
        matrix.postScale(temp, temp)
        return Bitmap.createBitmap(bitmap, 0, 0, oWidth, oHeight, matrix, false)
    }

}
