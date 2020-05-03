package com.icesi.appmoviles.reto2.model.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Album implements Serializable {
    private String title;
    private String cover_small;
    private BitMapSerializable image;
    private String cover_medium;
    public Album(){

    }

    public Bitmap getImage() {
        return image==null?null:image.getBitMap();
    }

    public void setImage(Bitmap image) {
        this.image = new BitMapSerializable(image);
    }

    public String getCover_small() {
        return cover_small;
    }

    public void setCover_small(String cover_small) {
        this.cover_small = cover_small;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_medium() {
        return cover_medium;
    }

    public void setCover_medium(String cover_medium) {
        this.cover_medium = cover_medium;
    }
}
