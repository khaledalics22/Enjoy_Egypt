package com.example.myapplicationj9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<Sight> sights;
//////////////////////////////


///////////////////////
    public MainAdapter(Context context, ArrayList<Sight> sights) {
        this.sights = sights;
        activity=(openDetailOfSight)context;

    }
    private openDetailOfSight activity;
    public interface openDetailOfSight{
        void openDetailsListener(Sight sight);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView sightName,caption;
        ImageView iv_sight_image;
        Button button;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            sightName=itemView.findViewById(R.id.sight_name_main);
            caption=itemView.findViewById(R.id.caption);
            iv_sight_image=itemView.findViewById(R.id.iv_sight_image_main);
            button=itemView.findViewById(R.id.btn_open_details);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.openDetailsListener((Sight) itemView.getTag());
                }
            });



        }
  void openDetailsListener(Sight s)
  {


  }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sight currSight= sights.get(position);
        holder.itemView.setTag(currSight);
        holder.caption.setText(currSight.getDetails());
        holder.iv_sight_image.setImageResource(currSight.getImageSrcId());
        holder.sightName.setText(currSight.getName());
        
    }


    @Override
    public int getItemCount() {
        return sights.size();
    }


}
