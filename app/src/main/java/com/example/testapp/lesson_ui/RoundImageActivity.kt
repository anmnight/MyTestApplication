package com.example.anxiao.lesson_ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.anxiao.mytestapplication.R
import com.example.anxiao.mytestapplication.customer_drawable.XfermodeRoundImageView
import kotlinx.android.synthetic.main.activity_round_image.*
import unit.DisPlayUnit

class RoundImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round_image)

        val xferImageView = findViewById(R.id.xfermode_image) as XfermodeRoundImageView
        val params = xferImageView.layoutParams
        params.width = DisPlayUnit.devicesDisPlay().widthPixels
        xferImageView.layoutParams = params

        xferImageView.setImageResource(R.mipmap.gal)

        bitmapshader_img.setImageResource(R.mipmap.gal)




    }
}
