package com.example.serviceboundmusic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private List<Music> listData;
    private LayoutInflater layoutInflater;
    private MyPlayer myPlayer;


    public MusicAdapter(List<Music> listData, Context context) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.inflate(R.layout.item,
                parent, false);
        return new MusicViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        String mCurrent = listData.get(position).getName();
        holder.tvName.setText(mCurrent);
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), activity_Play.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", listData.get(position).getName());
                bundle.putInt("mp3", listData.get(position).getMp3());
                intent.putExtras(bundle);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public class MusicViewHolder extends RecyclerView.ViewHolder {
        final MusicAdapter mAdapter;
        public TextView tvName;

        public MusicViewHolder(@NonNull View itemView, MusicAdapter mAdapter) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            this.mAdapter = mAdapter;
        }
    }
}
