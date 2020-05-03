package com.icesi.appmoviles.reto2.model.entity;

import android.graphics.Bitmap;

import java.util.Date;

public class Song implements Item {

    private String title;
    private String duration;
    private Date release_date;
    private Artist artist;
    private Album album;

    public Song(){

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getField1() {
        return title;
    }

    @Override
    public String getField2() {
        return artist.getName();
    }

    @Override
    public String getField3() {
        return release_date.getYear()+"";
    }

    @Override
    public Bitmap getImage() {
        return album.getImage();
    }

    public String getPicture() {
        return album.getCover_small();
    }

    @Override
    public void setImage(Bitmap image) {
        album.setImage(image);
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
