package com.icesi.appmoviles.reto2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.icesi.appmoviles.reto2.model.entity.Item;

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
        View view=inflater.inflate(R.layout.list_item,null);
        TextView field1=view.findViewById(R.id.field1);
        TextView field2=view.findViewById(R.id.field2);
        TextView field3=view.findViewById(R.id.field3);
        ImageView image=view.findViewById(R.id.image_item);
        T item=playLists.get(position);
        field1.setText(item.getField1());
        field2.setText(item.getField2());
        field3.setText(item.getField3());
        if(item.getImage()!=null){
            image.setImageBitmap(item.getImage());
        }
        return view;
    }

    public void addItem(T item){
        playLists.add(item);
        this.notifyDataSetChanged();
    }

    public void clear() {
        playLists.clear();
        this.notifyDataSetChanged();
    }
}
