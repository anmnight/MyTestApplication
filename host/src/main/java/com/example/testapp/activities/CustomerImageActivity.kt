package com.example.testapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.R
import kotlinx.android.synthetic.main.activity_customer_image.*

class CustomerImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_image)

        auto_load_img.displayImage("https://up.enterdesk.com/edpic_source/53/a6/78/53a678b383be6eb9607e875339b1d052.jpg");

    }
}
