package com.icesi.appmoviles.reto2.model.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

public interface Item extends Serializable {

     String getField1();
     String getField2();
     String getField3();
     Bitmap getImage();
     String getPicture();
     void setImage(Bitmap image);
}
