package com.icesi.appmoviles.reto2.model.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

public interface Item extends Serializable {

     String getId();
     String getField1();
     String getField2();
     String getField3();
     Bitmap getImage();
     String getPicture();
     void copy(Item item);
     void setImage(Bitmap image);
}
