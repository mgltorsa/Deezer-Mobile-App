package com.icesi.appmoviles.reto2.model.entity;

import java.io.Serializable;

public class Artist implements Serializable {

    private String name;

    public Artist(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
