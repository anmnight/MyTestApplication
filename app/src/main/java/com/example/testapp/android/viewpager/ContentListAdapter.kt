package com.example.testapp.android.viewpager

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.R

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/3 16:41
 * anmnight@qq.com
 */
class ContentListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.viewpager_mian_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //i not do any thing
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}