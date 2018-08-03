package com.example.testapp.android.viewpager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.mytestapplication.R
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)


        tablayout.setupWithViewPager(viewpager)

        viewpager.adapter = ViewPagerAdapter(supportFragmentManager)

    }


}
