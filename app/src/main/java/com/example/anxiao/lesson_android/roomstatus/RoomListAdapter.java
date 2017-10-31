package com.example.anxiao.lesson_android.roomstatus;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anxiao.mytestapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mCtx;

    private List<RoomStatusListModel> mDates = new ArrayList<>();

    private LayoutInflater mInflater;

    private int ROOM = 0;
    private int STATUS = 1;

    public RoomListAdapter(Activity context) {
        this.mCtx = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDates(List<RoomStatusListModel> list) {
        if (list != null) {
            this.mDates = list;
            mCtx.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ROOM) {
            return new RoomViewVH(mInflater.inflate(R.layout.stataus_room_view, parent, false));
        } else {
            return new RoomStatusViewVH(mInflater.inflate(R.layout.status_room_status_view, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int roomPosition = position / 37;
        int roomStatusPosition = (position % 37) - 1;

        if (holder instanceof RoomViewVH) {
            ((RoomViewVH) holder).roomName.setText(mDates.get(roomPosition).getRoomInfo().getRoomNo());
        }

        if (holder instanceof RoomStatusViewVH) {
            ((RoomStatusViewVH) holder).roomStatus.setText(mDates.get(roomPosition).getStatusList().get(roomStatusPosition).getRoomStatus());
        }

    }


    @Override
    public int getItemCount() {
        return mDates.size() * 36 + mDates.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 37 == 0) {
            return ROOM;
        } else {
            return STATUS;
        }
    }

    class RoomViewVH extends RecyclerView.ViewHolder {
        @BindView(R.id.room_name)
        TextView roomName;

        public RoomViewVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


    class RoomStatusViewVH extends RecyclerView.ViewHolder {
        @BindView(R.id.room_status)
        TextView roomStatus;

        public RoomStatusViewVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
