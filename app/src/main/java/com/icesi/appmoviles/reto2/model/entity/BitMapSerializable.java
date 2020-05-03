package com.icesi.appmoviles.reto2.model.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

public class BitMapSerializable implements Serializable {

    private final int[] pixeles;
    private final int width,height;

    public BitMapSerializable(Bitmap map){
        width=map.getWidth();
        height=map.getHeight();
        pixeles=new int[width*height];
        map.getPixels(pixeles,0,width,0,0,width,height);
    }
    public Bitmap getBitMap(){
        return Bitmap.createBitmap(pixeles,width,height, Bitmap.Config.ARGB_8888);
    }
}
