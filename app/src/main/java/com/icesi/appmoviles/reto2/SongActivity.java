package com.icesi.appmoviles.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.icesi.appmoviles.reto2.model.conection.HTTPSWebUtilDomi;
import com.icesi.appmoviles.reto2.model.entity.Song;

import java.io.File;

public class SongActivity extends AppCompatActivity {

    private final String DEEZER_APP="deezer.android.app";

    private ImageView image;
    private TextView name;
    private TextView albumName;
    private TextView artistName;
    private TextView duration;
    private Button listen;
    private TextView title;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Song song=(Song)this.getIntent().getExtras().getSerializable("song");
        image=findViewById(R.id.image_song);

        File imageCache = new File(getExternalCacheDir()+"/"+song.getId());
        if(imageCache.exists()){
            loadImage(imageCache);
        }else{

            new Thread(()->{
                HTTPSWebUtilDomi webUtils = new HTTPSWebUtilDomi();
                webUtils.saveURLImageOnFile(song.getPicture(), imageCache);
                loadImage(imageCache);
            }).start();



        }

        name=findViewById(R.id.song_name);
        name.setText(song.getTitle());
        albumName=findViewById(R.id.album_name);
        albumName.setText(song.getAlbum().getTitle());
        artistName=findViewById(R.id.artist_name);
        artistName.setText(song.getArtist().getName());
        duration=findViewById(R.id.song_time);
        duration.setText(song.getDuration()+" Segundos");
        listen=findViewById(R.id.listen);

        listen.setOnClickListener((view)->{


            Intent mediaPlayer=getPackageManager().getLaunchIntentForPackage(DEEZER_APP);
            Uri uri=Uri.parse(song.getLink());

            if(mediaPlayer==null){
                mediaPlayer=new Intent(Intent.ACTION_VIEW,uri);
            }else{
                mediaPlayer.setData(uri);
            }
            startActivity(mediaPlayer);
        });
        back=findViewById(R.id.back);
        back.setOnClickListener((view)->{
            finish();
        });
        title=findViewById(R.id.toolbar_text);
        title.setText("Ver canción");
    }

    public void loadImage(File imageFile){
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.toString());
        this.image.setImageBitmap(bitmap);
    }
}
