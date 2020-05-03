package com.icesi.appmoviles.reto2.model.connections;

import com.icesi.appmoviles.reto2.model.entities.Item;


public interface Response<T extends Item> {

     void addItemInList(T item);
     void setItem(T response);
     void finishRequest();
}
