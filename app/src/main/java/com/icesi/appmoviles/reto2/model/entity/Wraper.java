package com.icesi.appmoviles.reto2.model.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Wraper<T extends Item> implements Serializable {
        private List<T> data;

        public Wraper(){

        }

        public List<T> getData() {
                return data;
        }


        public void setData(List<T> data) {
                this.data = data;
        }

        @NonNull
        @Override
        public String toString() {
                String ret="";
                if(data!=null){
                        for (T t:data ) {
                                ret+=t.toString()+"\n";
                        }
                }else{
                        ret="null";
                }
                return ret;
        }
}
