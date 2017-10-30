package com.example.anxiao.mytestapplication.lesson_fresco;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anxiao.mytestapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import unit.DisPlayUnit;
import unit.ImageUnit;

/**
 * Created by anxiao on 2017/8/9.
 * thumb adapter
 */

public class ThumbAdapter extends RecyclerView.Adapter<ThumbAdapter.ThumbVH> {

    private LayoutInflater mInflater;

    private List<String> mListImgs = new ArrayList<>();

    private int mImageLength;

    private Context mCtx;

    public ThumbAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mCtx = context;
        mImageLength = DisPlayUnit.INSTANCE.devicesDisPlay().widthPixels / 2;
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
        ImageUnit.INSTANCE.showThumb(Uri.fromFile(new File(mListImgs.get(position))), holder.imgView, mImageLength, mImageLength);
        holder.path = mListImgs.get(position);
    }

    @Override
    public int getItemCount() {
        return mListImgs.size();
    }

    class ThumbVH extends RecyclerView.ViewHolder {
        private SimpleDraweeView imgView;
        private String path;

        ThumbVH(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.thumb_img);
            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            params.height = mImageLength;
            params.width = mImageLength;
            itemView.setLayoutParams(params);
            imgView.setOnClickListener(clickListener);

        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThumbActivity.route(mCtx, path);
            }
        };


    }
}
