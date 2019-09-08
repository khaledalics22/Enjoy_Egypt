package com.example.myapplicationj9;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    private ArrayList<Comment> comments;
    public CommentAdapter(ArrayList<Comment> comments) {
        if(comments!=null)
        this.comments = comments;
        else
            this.comments=new ArrayList<Comment>();


    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvComment;
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvComment=itemView.findViewById(R.id.person_comment);
            tvName=itemView.findViewById(R.id.person_name);

        }
    }
    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {


        Comment CurrComment= comments.get(position);
        holder.itemView.setTag(CurrComment);
        holder.tvName.setText(CurrComment.getName());
        holder.tvComment.setText(CurrComment.getComment());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

}
