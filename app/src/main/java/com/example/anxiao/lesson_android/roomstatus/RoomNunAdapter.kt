package com.example.anxiao.lesson_android.roomstatus

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.anxiao.mytestapplication.R
import kotlinx.android.synthetic.main.room_name_layout.view.*

class RoomNunAdapter(context: Context) : RecyclerView.Adapter<RoomNunAdapter.ViewHolder>() {

    private val mInflater = LayoutInflater.from(context)

    private lateinit var mDates:List<String>

    fun setDate(date:List<String>){
        mDates = date
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.roomNum.text= mDates[position]
    }

    override fun getItemCount(): Int {
        return mDates.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(mInflater.inflate(R.layout.room_name_layout, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomNum :TextView = itemView.room_num
    }
}