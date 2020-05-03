package com.icesi.appmoviles.reto2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.icesi.appmoviles.reto2.model.conection.HTTPSWebUtilDomi;
import com.icesi.appmoviles.reto2.model.entity.Item;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ListAdapter<T extends Item> extends BaseAdapter {

    private List<T> playLists;

    public ListAdapter(){
        playLists=new ArrayList<T>();
    }

    @Override
    public int getCount() {
        return playLists.size();
    }

    @Override
    public T getItem(int position) {
        return playLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View rootView=inflater.inflate(R.layout.list_item,null);
        TextView field1=rootView.findViewById(R.id.field1);
        TextView field2=rootView.findViewById(R.id.field2);
        TextView field3=rootView.findViewById(R.id.field3);
        ImageView imageRow=rootView.findViewById(R.id.image_item);
        T item=playLists.get(position);
        field1.setText(item.getField1());
        field2.setText(item.getField2());
        field3.setText(item.getField3());

        File imageCache = new File(parent.getContext().getExternalCacheDir()+"/"+item.getField1());
        if(imageCache.exists()){
            loadImage(imageRow, imageCache);
        }else{
            new Thread(()->{
                HTTPSWebUtilDomi webUtil = new HTTPSWebUtilDomi();
                webUtil.saveURLImageOnFile(item.getPicture(), imageCache);
                rootView.post(()->{
                    loadImage(imageRow, imageCache);
                });
            }).start();
        }


        return rootView;
    }

    public void loadImage(ImageView imageRow, File imageFile){
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.toString());
        imageRow.setImageBitmap(bitmap);
    }

    public void addItem(T item){
        playLists.add(item);
        this.notifyDataSetChanged();
    }

    public void clear() {
        playLists.clear();
        this.notifyDataSetChanged();
    }
    public void setList(List<T> list){
        playLists=list;
        this.notifyDataSetChanged();
    }
}
