package com.example.anxiao.mytestapplication.lesson_android.roomstatus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anxiao.mytestapplication.R;
import com.example.anxiao.mytestapplication.app.Logger;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RoomStatusListAdapter extends RecyclerView.Adapter<RoomStatusListAdapter.RoomStatusVH> {

    private List<RoomStatusModel> mList = new ArrayList<>();
    private LayoutInflater mInflater;

    public RoomStatusListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDates(List<RoomStatusModel> list) {
        if (list != null) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    @Override
    public RoomStatusVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RoomStatusVH(mInflater.inflate(R.layout.room_status_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RoomStatusVH holder, int position) {
        final RoomStatusModel model = mList.get(position);
        holder.roomStatusName.setText(String.valueOf(mList.get(position).getRoomStatus()));
        holder.roomStatusName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.err(model.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RoomStatusVH extends RecyclerView.ViewHolder {

        @BindView(R.id.room_status_name)
        TextView roomStatusName;

        public RoomStatusVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
