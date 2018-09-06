package com.example.testapp.view.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/3 15:42
 * anmnight@qq.com
 */
class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val list = ContentListFragment()
    private val detail = ContentDetailFragment()

    override fun getItem(position: Int): Fragment {
        return if (position == 0)
            list
        else
            detail
    }

    override fun getCount(): Int {
        return 2
    }


}