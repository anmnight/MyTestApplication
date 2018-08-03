package com.example.testapp.android.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.testapp.mytestapplication.R
import kotlinx.android.synthetic.main.fragment_view_pager_content.*


class ContentListFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_pager_content, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        content_list.adapter = context?.let {
            ContentListAdapter(it)
        }


    }


}
