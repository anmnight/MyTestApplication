package com.example.testapp.android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.R
import com.example.testapp.mytestapplication.customer_drawable.XfermodeRoundImageView
import kotlinx.android.synthetic.main.activity_round_image.*

class RoundImageEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round_image)

        val xferImageView = findViewById<XfermodeRoundImageView>(R.id.xfermode_image)
        val params = xferImageView.layoutParams
        xferImageView.layoutParams = params

        xferImageView.setImageResource(R.drawable.gal)

        bitmapshader_img.setImageResource(R.drawable.gal)




    }
}
