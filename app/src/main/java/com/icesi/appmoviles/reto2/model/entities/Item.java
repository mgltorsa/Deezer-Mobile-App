package com.icesi.appmoviles.reto2.model.entities;

import java.io.Serializable;

public interface Item extends Serializable {

     String getId();
     String getField1();
     String getField2();
     String getField3();
     String getPicture();
     void copy(Item item);
}
