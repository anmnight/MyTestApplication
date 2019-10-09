package com.example.testapp.activities.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.testapp.R
import kotlinx.android.synthetic.main.activity_linear_layout.*

class LinearLayoutActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_layout)

    }

    override fun onStart() {
        super.onStart()
        for (i in (1 ..5)){
            val textView = TextView(this)
            val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            textView.layoutParams = layoutParams
            textView.text = "text+$i"
            anim_linear_layout.addView(textView)
        }
    }
}
