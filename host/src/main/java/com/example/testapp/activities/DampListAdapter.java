package com.example.testapp.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;

public class DampListAdapter extends RecyclerView.Adapter<DampListAdapter.Holder> {

    private LayoutInflater inflater;
    private int[] datas = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    public DampListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.simple_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(String.valueOf("PoId : " + datas[position]));
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
        }
    }
}
