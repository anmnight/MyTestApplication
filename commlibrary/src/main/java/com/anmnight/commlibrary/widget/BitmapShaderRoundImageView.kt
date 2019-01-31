package com.anmnight.commlibrary.widget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.widget.AppCompatImageView
import android.util.AttributeSet

class BitmapShaderRoundImageView : AppCompatImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private var mPaint: Paint = Paint()

    override fun onDraw(canvas: Canvas) {

        if (drawable !is BitmapDrawable) {
            throw Exception("drawable isn't BitmapDrawable")
        }

        val bitmap = scale((drawable as BitmapDrawable).bitmap)

        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        mPaint.shader = bitmapShader

        val radius = Math.min(width,height)/2

        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius.toFloat(), mPaint)
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