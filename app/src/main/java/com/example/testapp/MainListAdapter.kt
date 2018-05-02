package com.example.testapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.testapp.mytestapplication.R

import java.util.ArrayList

class MainListAdapter(context: Context, private val mAction: MainListAction) : RecyclerView.Adapter<MainListAdapter.ListItemVH>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var mDates = ArrayList<MainListItemBean>()

    fun setDates(list: ArrayList<MainListItemBean>) {
        this.mDates = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemVH {
        return ListItemVH(inflater.inflate(R.layout.main_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListItemVH, position: Int) {

        holder.itemDescribe.text = mDates[position].describe
        holder.itemDescribe.setOnClickListener {
            mAction.itemTap(position)
        }

    }

    override fun getItemCount(): Int {
        return mDates.size
    }

    inner class ListItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemDescribe = itemView.findViewById<TextView>(R.id.list_item_describe)
    }

    interface MainListAction {
        fun itemTap(index: Int)
    }
}
