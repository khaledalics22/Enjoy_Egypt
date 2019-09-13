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

public class sightsAdapter  extends RecyclerView.Adapter<sightsAdapter.ViewHolder>{
    ArrayList<Sight> sights;
    private SightSelected activity;
    public interface SightSelected{
        void onSightSelectedItem(Sight sight);
    }
    public sightsAdapter(Context context, ArrayList<Sight> sights) {
        if(sights!=null)
            this.sights = sights;
        else
            sights=new ArrayList<Sight>();
        activity=(SightSelected)context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivSight;
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSight=itemView.findViewById(R.id.iv_sights);
            tvName=itemView.findViewById(R.id.tv_sight_name_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onSightSelectedItem((Sight)view.getTag());

                }
            });

        }
    }
    @NonNull
    @Override
    public sightsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.sights_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull sightsAdapter.ViewHolder holder, int position) {


        Sight currSight= sights.get(position);
        holder.itemView.setTag(currSight);
        holder.tvName.setText(currSight.getName());
        holder.ivSight.setImageResource(currSight.getImageSrcId());

    }

    @Override
    public int getItemCount() {
        return sights.size();
    }
}
