package com.example.myapplicationj9;

import java.security.PrivateKey;
import java.util.ArrayList;

public class Sight {
    private String name;
    private String details;
    private String cordinates;
    private int ImageSrcId;
    private ArrayList<Content> contents;
    private String videoPath;

    public String getVideoPath() {
        return videoPath;
    }

    public ArrayList<Content> getContents() {
        return contents;
    }

    public void setContents(ArrayList<Content> contents) {
        this.contents = contents;
    }

    private ArrayList<Comment> comments;

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public int getImageSrcId() {
        return ImageSrcId;
    }

    public void setImageSrcId(int imageSrcId) {
        ImageSrcId = imageSrcId;
    }

    public Sight(String name, String details, String cordinates, int imageSrcId) {
        this.name = name;
        this.details = details;
        this.cordinates = cordinates;
        ImageSrcId = imageSrcId;
        videoPath= "android.resource://com.example.myapplicationj9/"+R.raw.cairo_tower;

    }

    public Sight(String name, String details, String cordinates,int imageSrcId,ArrayList<Content> contents) {
        this.name = name;
        this.details = details;
        this.cordinates = cordinates;
        ImageSrcId = imageSrcId;
        this.contents = contents;
        this.comments = comments;
        videoPath = "android.resource://com.example.myapplicationj9/"+R.raw.cairo_tower;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setCordinates(String cordinates) {
        this.cordinates = cordinates;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getCordinates() {
        return cordinates;
    }

    public Sight(String name, String details, String cordinates) {
        this.name = name;
        this.details = details;
        this.cordinates = cordinates;
        videoPath= "android.resource://com.example.myapplicationj9/"+R.raw.cairo_tower;

    }
}
