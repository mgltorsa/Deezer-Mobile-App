package com.icesi.appmoviles.reto2.model.conection;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.Gson;
import com.icesi.appmoviles.reto2.model.entity.Item;
import com.icesi.appmoviles.reto2.model.entity.Wraper;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


public class Delegate<T extends Item> {

    private Thread list;
    private Thread item;

    public void getItem(Response<T> response, String ur, Type type) {
        if(item!=null&&!item.isInterrupted()){
            item.interrupt();
        }
        item=new Thread(() -> {
            try {
                HTTPSWebUtilDomi conection = new HTTPSWebUtilDomi();
                String data = conection.GETrequest(ur);
                Gson gson = new Gson();
                T object = gson.fromJson(data, type);
                response.finishRequest();
                response.setItem(object);

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(),e);
                
            }

        });
        item.start();
    }

    public void getList(Response<T> response, String url, Type type) {
        if(list!=null&&!list.isInterrupted()){
            list.isInterrupted();
        }
        list=new Thread(() -> {
            HTTPSWebUtilDomi conection = new HTTPSWebUtilDomi();
            try {
                String data = conection.GETrequest(url);
                Gson gson = new Gson();

                Wraper<T> dat = gson.fromJson(data, type);
                for (T it:dat.getData()){
                    response.addItemInList(it);

                }

                response.finishRequest();
            } catch (Exception e) {

            }
        });
        list.start();

    }



    public void getSongs(List<T> items, Response<T> response, String url, Type type){

        new Thread(()->{
            try {
                for (T item:items){
                    HTTPSWebUtilDomi http=new HTTPSWebUtilDomi();
                    String data=http.GETrequest(url+item.getId());
                    Gson gson=new Gson();
                    T object=gson.fromJson(data,type);
                    item.copy(object);
                    response.addItemInList(item);

                }
                response.finishRequest();
            }catch (Exception e){

            }
        }).start();

    }




}
