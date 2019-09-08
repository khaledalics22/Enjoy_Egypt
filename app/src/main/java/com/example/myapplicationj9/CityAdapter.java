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

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {

    public CityAdapter(@NonNull Context context, @NonNull List<City> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        City currCity=getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item_city,parent,false);
        }
        ImageView imageView=convertView.findViewById(R.id.iv_list_item);
        TextView  cityName=convertView.findViewById(R.id.tv_city_name);
        TextView  distance=convertView.findViewById(R.id.tv_distance);
        TextView  distanceUnit=convertView.findViewById(R.id.tv_distance_unit);
        TextView  sights=convertView.findViewById(R.id.tv_famous_places);
        
        
        imageView.setImageResource(currCity.getImageSrcID());
        cityName.setText(currCity.getName());
        distance.setText(""+currCity.getDistance());
        distanceUnit.setText(currCity.getDistanceUnit());
        ArrayList<String> sightsArray=currCity.getSights();
        String sightsString="";
        for(int i=0; i<3&&i<sightsArray.size();i++) {
            sightsString+=sightsArray.get(i)+" ,";
        }
        sights.setText(sightsString);
        return convertView;
    }
}
