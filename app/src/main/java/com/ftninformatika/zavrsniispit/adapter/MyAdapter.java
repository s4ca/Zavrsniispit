package com.ftninformatika.zavrsniispit.adapter;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftninformatika.zavrsniispit.R;
import com.ftninformatika.zavrsniispit.model.Search;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    public List<Search> srchList;
    public OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick (Search search);
    }

    public MyAdapter(List<Search> srchList, OnItemClickListener listener) {
        this.srchList = srchList;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvNaslovR;
        TextView tvGodinaR;
        ImageView ivSlikaR;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNaslovR = itemView.findViewById(R.id.tv_naslovRv);
            tvGodinaR = itemView.findViewById(R.id.tv_godinaRv);
            ivSlikaR = itemView.findViewById(R.id.iv_slikaRv);
        }
        public void bind (final Search search, final OnItemClickListener listener){
            tvNaslovR.setText(search.getTitle());
            tvGodinaR.setText(search.getYear());
           // Picasso.get().load(search.getPoster()).into(ivSlikaR);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(search);
                }
            });
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item, viewGroup, false);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(srchList.get(i), listener);
        final Search search = srchList.get(i);
    }

    @Override
    public int getItemCount() {
        return srchList.size();
    }




}
