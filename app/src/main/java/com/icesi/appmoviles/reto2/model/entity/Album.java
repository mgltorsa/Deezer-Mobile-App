package com.icesi.appmoviles.reto2.model.entity;

import android.graphics.Bitmap;

public class Album {
    private String title;
    private String cover_small;
    private Bitmap image;

    public Album(){

    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
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
}
