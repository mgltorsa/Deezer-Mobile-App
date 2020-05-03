package com.icesi.appmoviles.reto2.model.entity;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

public class PlayList implements Item{

    public static final String RequestListUrl ="https://api.deezer.com/search/playlist?q=";
    public static final String RequestItemUrl ="https://api.deezer.com/playlist/";
    private String id;
    private String title;
    private String description;
    private String picture;
    private User user;
    private String tracklist;
    private int nb_tracks;
    private int fans;

    public PlayList(){

    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    @Override
    public String getField1() {
        return title;
    }

    @Override
    public String getField2() {
        return user.getName();
    }

    @Override
    public String getField3() {
        return ""+nb_tracks;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    private Bitmap image;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNb_tracks() {
        return nb_tracks;
    }

    public void setNb_tracks(int nb_tracks) {
        this.nb_tracks = nb_tracks;
    }

    @NonNull
    @Override
    public String toString() {
        return title+" "+(user ==null?"null": user.getName());
    }
}
