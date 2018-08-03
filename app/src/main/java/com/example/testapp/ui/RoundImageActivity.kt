package com.example.testapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.mytestapplication.R
import com.example.testapp.mytestapplication.customer_drawable.XfermodeRoundImageView
import kotlinx.android.synthetic.main.activity_round_image.*

class RoundImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round_image)

        val xferImageView = findViewById(R.id.xfermode_image) as XfermodeRoundImageView
        val params = xferImageView.layoutParams
        xferImageView.layoutParams = params

        xferImageView.setImageResource(R.mipmap.gal)

        bitmapshader_img.setImageResource(R.mipmap.gal)




    }
}
