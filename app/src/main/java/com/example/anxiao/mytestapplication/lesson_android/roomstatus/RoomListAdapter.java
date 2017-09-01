package com.example.anxiao.mytestapplication.lesson_android.roomstatus;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
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

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.RoomStatusListVH> {

    private Activity mCtx;

    private List<RoomStatusListModel> mDates = new ArrayList<>();

    private LayoutInflater mInflater;

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
    public RoomStatusListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.room_status_item_list, parent, false);
        return new RoomStatusListVH(view);
    }

    @Override
    public void onBindViewHolder(RoomStatusListVH holder, int position) {
        RoomStatusListModel statusListModel = mDates.get(position);
        holder.roomName.setText(statusListModel.getRoomInfo().getRoomNo());
        holder.adapter.setDates(statusListModel.getStatusList());

    }


    @Override
    public int getItemCount() {
        return mDates.size();
    }

    class RoomStatusListVH extends RecyclerView.ViewHolder {
        @BindView(R.id.room_status_list)
        RecyclerView roomStatusList;
        @BindView(R.id.room_name)
        TextView roomName;

        private RoomStatusListAdapter adapter;

        public RoomStatusListVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            adapter = new RoomStatusListAdapter(mCtx);
            LinearLayoutManager manager = new LinearLayoutManager(mCtx, LinearLayoutManager.HORIZONTAL, false);
            roomStatusList.setLayoutManager(manager);
            roomStatusList.setAdapter(adapter);

        }
    }
}
