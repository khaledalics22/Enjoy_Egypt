package com.example.myapplicationj9;

import java.util.ArrayList;

public class Content {
    private String name;
    private String details;
    private int ImageSrcId;


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

    public Content(String name, String details, String cordinates, int imageSrcId) {
        this.name = name;
        this.details = details;
        ImageSrcId = imageSrcId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }


    public Content(String name, String details, String cordinates) {
        this.name = name;
        this.details = details;
    }
}
