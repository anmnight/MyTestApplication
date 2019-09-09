package com.example.testapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testapp.R;

public class DampLayoutActivity extends AppCompatActivity {

    private String tag = "DampLayoutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.damplayout_actity);


        //https://segmentfault.com/a/1190000019272870
        RecyclerView list = findViewById(R.id.list);
        DampLayout dampLayout = findViewById(R.id.damp_layout);
//        dampLayout.setCallback(y -> Log.d(tag, "onScrollY : " + y));

        DampListAdapter adapter = new DampListAdapter(this);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));


    }


    private class DampListAdapter extends RecyclerView.Adapter<DampListAdapter.Holder> {

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
}
