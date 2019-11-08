package com.example.testapp.activities.android

import android.os.Bundle
import android.widget.RadioGroup
import com.example.testapp.BaseActivity

import com.example.testapp.R
import com.example.testapp.debug
import kotlinx.android.synthetic.main.activity_popupwindow.*

class PopupWindowActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popupwindow)

        initView()
        setListener()

    }

    private fun initView() {

    }


    private fun setListener() {

        popup_position.setOnCheckedChangeListener { group, checkedId ->
            debug("checkedId+${popup_position.checkedRadioButtonId}")
        }

    }
}