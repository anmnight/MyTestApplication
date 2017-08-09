package com.example.anxiao.mytestapplication.lesson_fresco;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anxiao.mytestapplication.Logger;
import com.example.anxiao.mytestapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import unit.DisPlayUnit;

/**
 * Created by anxiao on 2017/8/9.
 * thumb adapter
 */

public class ThumbAdapter extends RecyclerView.Adapter<ThumbAdapter.ThumbVH> {

    private LayoutInflater mInflater;

    private List<String> mListImgs = new ArrayList<>();

    public ThumbAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDates(List<String> list) {
        if (list != null) {
            mListImgs = list;
            notifyDataSetChanged();
        }
    }


    @Override
    public ThumbVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThumbVH(mInflater.inflate(R.layout.lesson_fresco_thumb_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ThumbVH holder, int position) {
        holder.imgView.setImageURI(Uri.fromFile(new File(mListImgs.get(position))));
    }

    @Override
    public int getItemCount() {
        return mListImgs.size();
    }

    class ThumbVH extends RecyclerView.ViewHolder {
        private SimpleDraweeView imgView;

        ThumbVH(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.thumb_img);
            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            params.height = DisPlayUnit.devicesDisPlay().widthPixels / 2;
            params.width = DisPlayUnit.devicesDisPlay().widthPixels / 2;
            itemView.setLayoutParams(params);
        }
    }
}
