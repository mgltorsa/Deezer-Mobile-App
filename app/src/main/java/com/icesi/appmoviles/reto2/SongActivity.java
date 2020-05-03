package com.icesi.appmoviles.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.icesi.appmoviles.reto2.model.conection.ListDelegate;
import com.icesi.appmoviles.reto2.model.conection.Response;
import com.icesi.appmoviles.reto2.model.entity.BitMapSerializable;
import com.icesi.appmoviles.reto2.model.entity.PlayList;
import com.icesi.appmoviles.reto2.model.entity.Song;
import com.icesi.appmoviles.reto2.model.entity.Wraper;

import java.lang.reflect.Type;

public class SongActivity extends AppCompatActivity implements Response<Song> {

    private ImageView imagePlay;
    private ImageView loading;
    private TextView namePlay;
    private TextView descPlay;
    private TextView songsPlay;
    private TextView followPlay;
    private ListAdapter<Song> adapter;
    private ListView list;
    private AnimationDrawable animation;
    private ListDelegate<Song> songListDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        songListDelegate=new ListDelegate<>();
        loading=findViewById(R.id.loading_song);
        loading.setBackgroundResource(R.drawable.loading);
        animation=(AnimationDrawable) loading.getBackground();
        loading.setVisibility(View.VISIBLE);
        animation.start();
        imagePlay=findViewById(R.id.image_play);
        namePlay=findViewById(R.id.name_play);
        descPlay=findViewById(R.id.desc_play);
        songsPlay=findViewById(R.id.songs);
        followPlay =findViewById(R.id.follows);
        list=findViewById(R.id.song_list);
        adapter=new ListAdapter<>();
        PlayList playList=(PlayList)this.getIntent().getExtras().getSerializable("playList");
        BitMapSerializable image=(BitMapSerializable)this.getIntent().getExtras().getSerializable("image");
        playList.setImage(image.getBitMap());
        showPlayList(playList);
        list.setAdapter(adapter);
        Type type=new  TypeToken< Wraper<Song> >(){}.getType();
        songListDelegate.getList(this,playList.getTracklist(),type);
    }

    private void showPlayList(PlayList playList) {
        imagePlay.setImageBitmap(playList.getImage());
        namePlay.setText(playList.getTitle());
        descPlay.setText(playList.getDescription());
        songsPlay.setText(playList.getNb_tracks()+" Canciones");
        followPlay.setText(playList.getFans()+" fans");

    }

    @Override
    public void addItemInList(Song item) {
        runOnUiThread(()->{
            adapter.addItem(item);
        });
    }

    @Override
    public void setItem(Song response) {

    }

    @Override
    public void finishRequest() {
        animation.stop();
        loading.setVisibility(View.INVISIBLE);

    }
}
