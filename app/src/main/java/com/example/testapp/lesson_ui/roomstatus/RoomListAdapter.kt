package com.example.testapp.lesson_ui.roomstatus

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.testapp.homeinns.rooms.pojo.RoomStatusListBean
import com.example.testapp.mytestapplication.R
import java.util.ArrayList


class RoomListAdapter(private val mCtx: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mDates: List<RoomStatusListBean> = ArrayList()

    private val mInflater: LayoutInflater = LayoutInflater.from(mCtx)

    private val ROOM = 0
    private val STATUS = 1

    fun setDates(list: List<RoomStatusListBean>?) {
        if (list != null) {
            this.mDates = list
            mCtx.runOnUiThread { notifyDataSetChanged() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ROOM) {
            RoomViewVH(mInflater.inflate(R.layout.stataus_room_view, parent, false))
        } else {
            RoomStatusViewVH(mInflater.inflate(R.layout.status_room_status_view, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val roomPosition = position / 37
        val roomStatusPosition = position % 37 - 1

        if (holder is RoomViewVH) {
            holder.roomName!!.text = mDates[roomPosition].roomInfo.roomNo
        }

        if (holder is RoomStatusViewVH) {
            holder.roomStatus!!.text = mDates[roomPosition].statusList[roomStatusPosition].roomStatus
        }

    }


    override fun getItemCount(): Int {
        return mDates.size * 36 + mDates.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (position % 37 == 0) {
            ROOM
        } else {
            STATUS
        }
    }

    internal inner class RoomViewVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomName = itemView.findViewById<TextView>(R.id.room_name)
    }


    internal inner class RoomStatusViewVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomStatus = itemView.findViewById<TextView>(R.id.room_status)
    }
}
