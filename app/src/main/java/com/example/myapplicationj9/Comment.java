package com.example.myapplicationj9;

public class Comment {
    private String name="none";
    private String comment="none";
    private int profileImage=R.drawable.ic_person_black_24dp;

    public Comment() {
        }

    public Comment(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }
    public Comment(String name, String comment, int profileImage) {
        this.name = name;
        this.comment = comment;
        this.profileImage = profileImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public int getProfileImage() {
        return profileImage;
    }
}
