package com.example.myapplicationj9;

import android.util.ArraySet;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class City {
    private String name;
    private ArrayList<String> sights;
    private int distance;
    private int ImageSrcID;
    private String distanceUnit;
    private ArrayList<Comment> comments;
    private String Details;
    private  ArrayList<String>Rate;
    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public City(String name, ArrayList<String> sights, int distance, String distanceUnit, int imageSrcID,  ArrayList<Comment> comments, String details,ArrayList<String> rate) {
        this.name = name;
        this.sights = sights;
        this.distance = distance;
        ImageSrcID = imageSrcID;
        this.distanceUnit = distanceUnit;
        this.comments = comments;
        Details = details;
        Rate=rate;
    }
    public City(String name, ArrayList<String> sights, int distance, String distanceUnit, int imageSrcID,  ArrayList<Comment> comments, String details) {
        this.name = name;
        this.sights = sights;
        this.distance = distance;
        ImageSrcID = imageSrcID;
        this.distanceUnit = distanceUnit;
        this.comments = comments;
        Details = details;
        Rate=new ArrayList<String>();
    }
    public void setRate(ArrayList<String> rate) {
        Rate= rate;
    }
    public void addRate(String rate)
    {
        Rate.add(rate);
    }

    public ArrayList<String> getRate() {
        return Rate;
    }

    public int getRateAverage(){
        int avr=0;
        for(int i=0; i<Rate.size();i++)
        {
            avr+=Integer.parseInt(Rate.get(i));
        }
        avr/=(Rate.size()-1);
        return avr;
    }
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment)
    {
        comments.add(comment);
    }


    public City(String name,ArrayList<String> sights, int distance,String distanceUnit, int imageSrcID) {
        this.name = name;
        this.sights = sights;
        this.distance = distance;
        ImageSrcID = imageSrcID;
        this.distanceUnit = distanceUnit;
        this.comments = new ArrayList<>();
    }
    public int getImageSrcID() {
        return ImageSrcID;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setImageSrcID(int imageSrcID) {
        ImageSrcID = imageSrcID;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public City() {
       name = "none"  ;
       sights=new ArrayList<String>();
       distance=-1;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getSights() {
        return sights;
    }

    public long getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSights(ArrayList<String> sights) {
        this.sights = sights;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
