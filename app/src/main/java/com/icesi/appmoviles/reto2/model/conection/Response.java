package com.icesi.appmoviles.reto2.model.conection;

import com.icesi.appmoviles.reto2.model.entity.Item;


public interface Response<T extends Item> {

     void addItemInList(T item);
     void setItem(T response);
     void finishRequest();
}
