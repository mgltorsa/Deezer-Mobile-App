package com.icesi.appmoviles.reto2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.icesi.appmoviles.reto2.model.conection.Delegate;
import com.icesi.appmoviles.reto2.model.conection.Response;
import com.icesi.appmoviles.reto2.model.entity.PlayList;
import com.icesi.appmoviles.reto2.model.entity.Wraper;

public class MainActivity extends AppCompatActivity implements Response<PlayList> {

    private EditText searchEdit;
    private Button search;
    private RecyclerView list;
    private PlayListAdapter adapter;
    private Delegate<PlayList> delegate;
    private PlayList selected;
    private ImageView loading;
    private AnimationDrawable animation;
    private Button back;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loading=findViewById(R.id.loading);
        loading.setBackgroundResource(R.drawable.loading);
        animation=(AnimationDrawable) loading.getBackground();

        searchEdit=findViewById(R.id.search_edit);
        search=findViewById(R.id.search_button);
        list=findViewById(R.id.search_list);
        adapter=new PlayListAdapter(this);

        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        delegate=new Delegate<>();
        search.setOnClickListener((view)->{
            String name=searchEdit.getText().toString();
            searchEdit.setText("");
            loading.setVisibility(View.VISIBLE);
            animation.start();
            adapter.clear();
            delegate.getList(this,PlayList.RequestListUrl +name,new TypeToken<Wraper<PlayList>>(){}.getType());
        });




        back=findViewById(R.id.back);
        back.setOnClickListener((view)->{
            finish();
        });
        title=findViewById(R.id.toolbar_text);
        title.setText("Buscar Playlist");

    }

    public void selectPlayList(PlayList playlist){
        selected=playlist;
        loading.setVisibility(View.VISIBLE);
        animation.start();
        delegate.getItem(MainActivity.this, PlayList.RequestItemUrl + selected.getId(), PlayList.class);
    }

    @Override
    public void addItemInList(PlayList response) {
        runOnUiThread(()->{
            adapter.addItem(response);
        });

    }

    @Override
    public void setItem(PlayList response) {
        runOnUiThread(()->{
            Intent i=new Intent(MainActivity.this, SongListActivity.class);
            selected.copy(response);
            i.putExtra("playList",selected);
            startActivity(i);
        });

    }

    @Override
    public void finishRequest() {
        animation.stop();
        loading.setVisibility(View.INVISIBLE);

    }

}
