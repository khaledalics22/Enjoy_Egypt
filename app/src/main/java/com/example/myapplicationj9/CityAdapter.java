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

import java.text.DecimalFormat;
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
        City fromCity=getItem(0);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item_city,parent,false);
        }
        //assume current city is cairo we will measure all distance to it

        ImageView imageView=convertView.findViewById(R.id.iv_list_item);
        TextView  cityName=convertView.findViewById(R.id.tv_city_name_list);
        TextView  distance=convertView.findViewById(R.id.tv_distance);
        TextView  distanceUnit=convertView.findViewById(R.id.tv_distance_unit);
        TextView  sights=convertView.findViewById(R.id.tv_famous_places);
        
        
        imageView.setImageResource(currCity.getImageSrcID());
        cityName.setText(currCity.getName());
        double dist=Cities_1.distFrom(fromCity.getLat(),fromCity.getLng(),currCity.getLat(),currCity.getLng());
        currCity.setDistance((int)dist);
        distance.setText(new DecimalFormat("##.###").format(dist));
        distanceUnit.setText(currCity.getDistanceUnit());
        ArrayList<Sight> sightsArray=currCity.getSights();
        String sightsString="";
        for(int i=0; i<3&&i<sightsArray.size();i++) {
            sightsString+=sightsArray.get(i).getName()+" ,";
        }
        sights.setText(sightsString);
        return convertView;
    }
}
