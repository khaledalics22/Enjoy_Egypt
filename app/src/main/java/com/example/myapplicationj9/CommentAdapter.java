package com.example.myapplicationj9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {

    public CommentAdapter(@NonNull Context context, @NonNull List<Comment> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Comment currCommnt=getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.comment_item,parent,false);
        }
        ImageView imageView=convertView.findViewById(R.id.profile_image);
        TextView  personName=convertView.findViewById(R.id.person_name);
        TextView  Comment=convertView.findViewById(R.id.person_comment);

        
        
        imageView.setImageResource(currCommnt.getProfileImage());
        personName.setText(currCommnt.getName());
        Comment.setText(currCommnt.getComment());

        return convertView;
    }
}
