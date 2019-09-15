package com.example.myapplicationj9;

import java.util.ArrayList;

public class City {
    private String name;
    private ArrayList<Sight> sights;
    private int distance=0;
    private int ImageSrcID;
    private String distanceUnit;
    private ArrayList<Comment> comments;
    private String Details;
    private double lat=0;
    private double lng=0;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    private  ArrayList<String>Rate;
    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public City(String name, ArrayList<Sight> sights, int distance, String distanceUnit, int imageSrcID, ArrayList<Comment> comments, String details, ArrayList<String> rate) {
        this.name = name;
        this.sights = sights;
        this.distance = distance;
        ImageSrcID = imageSrcID;
        this.distanceUnit = distanceUnit;
        this.comments = comments;
        Details = details;
        Rate=rate;
    }
    public City(String name, ArrayList<Sight> sights, int distance, String distanceUnit, int imageSrcID, ArrayList<Comment> comments, String details) {
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


    public City(String name, ArrayList<Sight> sights, int distance, String distanceUnit, int imageSrcID) {
        this.name = name;
        this.sights = sights;
        this.distance = distance;
        ImageSrcID = imageSrcID;
        this.distanceUnit = distanceUnit;
        this.comments = new ArrayList<>();
    }
    public City(String name, ArrayList<Sight> sights, int distance, String distanceUnit, int imageSrcID,double lat,double lng) {
        this.name = name;
        this.sights = sights;
        this.distance = distance;
        ImageSrcID = imageSrcID;
        this.distanceUnit = distanceUnit;
        this.comments = new ArrayList<>();
        this.lat=lat;
        this.lng=lng;
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
       sights=new ArrayList<Sight>();
       distance=-1;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Sight> getSights() {
        return sights;
    }

    public long getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSights(ArrayList<Sight> sights) {
        this.sights = sights;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
