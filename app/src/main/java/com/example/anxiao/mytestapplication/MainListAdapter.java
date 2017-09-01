package com.example.anxiao.mytestapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ListItemVH> {

    private LayoutInflater inflater;
    private List<MainListItemBean> mDates = new ArrayList<>();
    private MainListAction mAction;

    public MainListAdapter(Context context, MainListAction action) {
        this.inflater = LayoutInflater.from(context);
        this.mAction = action;
    }

    public void setDates(List<MainListItemBean> list) {
        if (list != null) {
            this.mDates = list;
            notifyDataSetChanged();
        }
    }

    @Override
    public ListItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListItemVH(inflater.inflate(R.layout.main_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ListItemVH holder, int position) {
        holder.itemDescribe.setText(String.valueOf(mDates.get(position).getDescribe()));
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }

    class ListItemVH extends RecyclerView.ViewHolder {
        @BindView(R.id.list_item_describe)
        TextView itemDescribe;

        public ListItemVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.list_item_describe)
        void onClick() {
            mAction.itemTap(getAdapterPosition());
        }
    }

    public interface MainListAction {
        void itemTap(int index);
    }
}
