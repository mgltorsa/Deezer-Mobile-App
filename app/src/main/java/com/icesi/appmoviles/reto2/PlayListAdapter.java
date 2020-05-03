package com.icesi.appmoviles.reto2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.icesi.appmoviles.reto2.model.conection.HTTPSWebUtilDomi;
import com.icesi.appmoviles.reto2.model.entity.Item;
import com.icesi.appmoviles.reto2.model.entity.PlayList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayListAdapter<T extends Item> extends RecyclerView.Adapter<PlayListAdapter.CustomViewHolder> {

    private List<T> items;
    private MainActivity activity;

    public PlayListAdapter(MainActivity activity){
        this.items =new ArrayList<>();
        this.activity=activity;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.list_item, null);

        CustomViewHolder vh = new CustomViewHolder(rootView, activity);

        return vh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        T item = items.get(position);
        holder.field1.setText(item.getField1());
        holder.field2.setText(item.getField2());
        holder.field3.setText(item.getField3());
        holder.item = item;

        File imageCache = new File(holder.view.getContext().getExternalCacheDir() + "/" + item.getField1());
        if (imageCache.exists()) {
            loadImage(holder, imageCache);
        } else {
            new Thread(() -> {
                HTTPSWebUtilDomi webUtil = new HTTPSWebUtilDomi();
                webUtil.saveURLImageOnFile(item.getPicture(), imageCache);

                loadImage(holder, imageCache);

            }).start();
        }
    }

    public void addItem(T item) {
        items.add(item);
        this.notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        this.notifyDataSetChanged();
    }

    public void setList(List<T> list) {
        items = list;
        this.notifyDataSetChanged();
    }


    public void loadImage(CustomViewHolder holder, File imageFile){
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.toString());
        holder.imageView.setImageBitmap(bitmap);
    }



    public static class CustomViewHolder extends RecyclerView.ViewHolder{

        private View view;
        public TextView field1;
        public TextView field2;
        public TextView field3;
        public ImageView imageView;
        private Item item;


        public CustomViewHolder(View rootview, MainActivity activity){
            super(rootview);
            view=rootview;
            field1 = view.findViewById(R.id.field1);
            field2 = view.findViewById(R.id.field2);
            field3 = view.findViewById(R.id.field3);
            imageView = view.findViewById(R.id.image_item);

            view.setOnClickListener(view1 -> {
                activity.selectPlayList((PlayList)item);
            });
        }


    }

}
