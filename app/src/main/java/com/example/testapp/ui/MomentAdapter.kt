package com.example.testapp.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * © 1908 anxiao Co.,Ltd All Rights Reserved.
 * 作者：anxiao on 2018/4/18 15:30
 * 邮箱：anxiao@bankcomm.com
 */
class MomentAdapter(private val context: Context, private val items: ArrayList<String>) : RecyclerView.Adapter<MomentAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.text.text = items[position]
    }


    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(android.R.id.text1)
    }
}