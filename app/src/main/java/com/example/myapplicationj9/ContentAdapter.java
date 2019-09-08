package com.example.myapplicationj9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder>{
    ArrayList<Content> contents;
    public ContentAdapter(Context context, ArrayList<Content> contents) {
        if(contents!=null)
            this.contents = contents;
        else
            this.contents=new ArrayList<Content>();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivSight;
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSight=itemView.findViewById(R.id.iv_sights);
            tvName=itemView.findViewById(R.id.tv_sight_name_list);
        }
    }
    @NonNull
    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.sights_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentAdapter.ViewHolder holder, int position) {


        Content currContent= contents.get(position);
        holder.itemView.setTag(currContent);
        holder.tvName.setText(currContent.getName());
        holder.ivSight.setImageResource(currContent.getImageSrcId());

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}
