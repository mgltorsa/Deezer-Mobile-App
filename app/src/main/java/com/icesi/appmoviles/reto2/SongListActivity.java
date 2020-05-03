package com.icesi.appmoviles.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.icesi.appmoviles.reto2.model.conection.HTTPSWebUtilDomi;
import com.icesi.appmoviles.reto2.model.conection.Delegate;
import com.icesi.appmoviles.reto2.model.conection.Response;
import com.icesi.appmoviles.reto2.model.entity.PlayList;
import com.icesi.appmoviles.reto2.model.entity.Song;

import java.io.File;
import java.lang.reflect.Type;

public class SongListActivity extends AppCompatActivity implements Response<Song> {

    private ImageView imagePlay;
    private ImageView loading;
    private TextView namePlay;
    private TextView descPlay;
    private TextView songsPlay;
    private TextView followPlay;
    private SongsListAdapter<Song> adapter;
    private ListView list;
    private AnimationDrawable animation;
    private Delegate<Song> songListDelegate;
    private TextView title;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        songListDelegate=new Delegate<>();
        loading=findViewById(R.id.loading_song);
        loading.setBackgroundResource(R.drawable.loading);
        animation=(AnimationDrawable) loading.getBackground();

        imagePlay=findViewById(R.id.image_play);
        namePlay=findViewById(R.id.name_play);
        descPlay=findViewById(R.id.desc_play);
        songsPlay=findViewById(R.id.songs);
        followPlay =findViewById(R.id.follows);
        list=findViewById(R.id.song_list);
        adapter=new SongsListAdapter<>();
        PlayList playList=(PlayList)this.getIntent().getExtras().getSerializable("playList");

        File imageCache = new File(getExternalCacheDir()+"/"+playList.getTitle());

        if(imageCache.exists()){
            loadImage(imageCache);
        }else{
            new Thread(()->{
                HTTPSWebUtilDomi webUtils = new HTTPSWebUtilDomi();
                webUtils.saveURLImageOnFile(playList.getPicture(), imageCache);
                loadImage(imageCache);
            }).start();
        }

        showPlayList(playList);

        list.setAdapter(adapter);
        adapter.setList(playList.getTracks().getData());
        Type type=new TypeToken< Song >(){}.getType();

        //getting songs
        loading.setVisibility(View.VISIBLE);
        animation.start();
        songListDelegate.getSongs(playList.getTracks().getData(),this,Song.ITEM_URL,type);

        list.setOnItemClickListener((parent, view, position, id) -> {
            Song song=adapter.getItem(position);
            Intent i=new Intent(SongListActivity.this,SongActivity.class);
            i.putExtra("song",song);
            startActivity(i);
        });
        back=findViewById(R.id.back);
        back.setOnClickListener((view)->{
            finish();
        });
        title=findViewById(R.id.toolbar_text);
        title.setText("Buscar Playlist");
    }

    public void loadImage(File imageCache){
        runOnUiThread(()-> {
            Bitmap bitmap = BitmapFactory.decodeFile(imageCache.toString());
            this.imagePlay.setImageBitmap(bitmap);
        });
    }

    private void showPlayList(PlayList playList) {
        namePlay.setText(playList.getTitle());
        descPlay.setText(playList.getDescription());
        songsPlay.setText(playList.getNb_tracks()+" Canciones");
        followPlay.setText(playList.getFans()+" fans");

    }

    @Override
    public void addItemInList(Song item) {
        runOnUiThread(()->{
            adapter.notifyDataSetChanged();
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
