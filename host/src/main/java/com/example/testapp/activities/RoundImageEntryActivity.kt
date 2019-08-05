package com.example.testapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.R
import kotlinx.android.synthetic.main.activity_round_image.*

class RoundImageEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round_image)



        xfermode_image.setImageResource(R.drawable.gal)

        bitmapshader_img.setImageResource(R.drawable.gal)




    }
}
