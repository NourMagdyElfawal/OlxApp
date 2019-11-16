package com.example.olxapp.Interface;

import java.io.Serializable;


public class category implements Serializable {
   private String describtion;
    private String hourOfWork;
    private String location;
    private String image;
   private String name;
    private String type;
    private int id;
    private int visitDuration;
    private boolean show;
    String imageUrl;


    //the constractor
    public category(){}
    public category(String myDescribtion, String myHourOfWork, String myLocation, String myImage, String myName, String mytype, int myid, int MyvisitDuration, boolean show){
      this.describtion = myDescribtion;
        this.hourOfWork = myHourOfWork;
        this.image = myImage;
        this.name = myName;
        this.type = mytype;
        this.location = myLocation;
        this.id = myid;
        this.visitDuration = MyvisitDuration;
        this.show = show;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getHourOfWork() {
        return hourOfWork;
    }

    public void setHourOfWork(String hourOfWork) {
        this.hourOfWork = hourOfWork;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVisitDuration() {
        return visitDuration;
    }

    public void setVisitDuration(int visitDuration) {
        this.visitDuration = visitDuration;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
