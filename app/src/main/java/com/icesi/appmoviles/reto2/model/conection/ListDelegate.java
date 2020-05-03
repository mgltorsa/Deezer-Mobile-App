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


public class ListDelegate<T extends Item> {

    private Thread list;
    private Thread item;

    public void getItem(Response<T> response, String ur, Type type) {
        if(item!=null&&!item.isInterrupted()){
            item.interrupt();
        }
        item=new Thread(() -> {
            try {
                Log.e("getItem", "Start");
                HTTPSWebUtilDomi conection = new HTTPSWebUtilDomi();
                String data = conection.GETrequest(ur);
                Log.e("getItem data", data);
                Gson gson = new Gson();
                T object = gson.fromJson(data, type);
                Log.e("getItem object", object.getField1());
                response.setItem(object);
            } catch (Exception e) {

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
                Log.e("connection", "conecting");
                String data = conection.GETrequest(url);
                Log.e("connection", "conected");
                Gson gson = new Gson();

                Wraper<T> dat = gson.fromJson(data, type);
                for (T req : dat.getData()) {
                    String urlI = req.getPicture();
                    if(urlI!=null){
                        URL _url = new URL(urlI);
                        URLConnection connection = _url.openConnection();
                        connection.connect();
                        InputStream reader = connection.getInputStream();
                        BufferedInputStream inputStream = new BufferedInputStream(reader);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                        req.setImage(bitmap);
                        Log.e("data", req.getField1());
                        response.addItemInList(req);
                    }

                }
                response.finishRequest();
            } catch (Exception e) {

            }
        });
        list.start();

    }


}
