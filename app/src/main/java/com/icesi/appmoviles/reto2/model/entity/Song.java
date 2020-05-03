package com.icesi.appmoviles.reto2.model.entity;

import android.graphics.Bitmap;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Song implements Item {

    public static final String ITEM_URL="https://api.deezer.com/track/";

    private String id;
    private String title;
    private String duration;
    private String link;
    private Date release_date;
    private Artist artist;
    private long time_add;
    private Album album;

    public Song(){

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime_add() {
        return time_add;
    }

    public void setTime_add(long time_add) {
        this.time_add = time_add;
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
        Date tmp=release_date;
        if(tmp==null){
            tmp=new Date(time_add);
        }

        SimpleDateFormat fdf=new SimpleDateFormat("yyyy");


        return fdf.format(tmp);
    }

    @Override
    public Bitmap getImage() {
        return album.getImage();
    }

    public String getPicture() {
        return album.getCover_medium();
    }

    @Override
    public void copy(Item item) {
        Song newS=(Song)item;
        setAlbum(newS.album);
        setArtist(newS.artist);
        setDuration(newS.duration);
        setId(newS.id);
        setRelease_date(newS.release_date);
        setTitle(newS.title);
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
