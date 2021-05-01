package com.example.insura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.util.Log;

import com.khizar1556.mkvideoplayer.MKPlayer;

import java.util.ArrayList;

import static android.media.MediaMetadataRetriever.OPTION_CLOSEST;

public class PlayVideo extends AppCompatActivity {

    private ArrayList<ClassListItem> itemsArrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;




  //  private MyAppAdapter myAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        Intent intent = getIntent();
        String PATH = intent.getStringExtra("PATH");


        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemsArrayList=new ArrayList<>();
        for(int i=0;i<10;i++){
            ClassListItem listItem=new ClassListItem("jay","https://nivea The Quelccaya Ice Cap (also known as the Quenamari Ice Cap) is the second-largest glaciated area in the tropics, after Coropuna. Located in the Cordillera Oriental in Peru's Andes, it covers an area of 42.8 square kilometres (16.5 sq mi) with ice up to 200 metres (660 ft) thick. It is surrounded by tall ice cliffs and a number of outlet glaciers, the largest of which is known as Qori Kalis Glacier. Quelccaya is an important source of water, eventually nourishing the Inambari and Vilcanota Rivers. It is regularly monitored and has a weather station. Quelccaya was much larger in the past, merging with neighbouring glaciers during the Pleistocene epoch. After reaching a secondary highstand (area expansion) during the Little Ice Age, Quelccaya has been shrinking due to human-caused climate change. Models predict that without aggressive climate mitigation measures, Quelccaya is likely to disappear during the 21st or 22nd century. (Full article...)");
            itemsArrayList.add(listItem);
        }
        adapter=new MyAdapter(itemsArrayList,this);
        recyclerView.setAdapter(adapter);

        MKPlayer mkplayer = new MKPlayer(this);

        mkplayer.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
            boolean f = false;

            @Override
            public void onNextClick() {
                //It is the method for next song.It is called when you pressed the next icon
                //Do according to your requirement
                Log.d("jaya", String.valueOf(mkplayer.getDuration()));
            }

            @Override
            public void onPreviousClick() {
                Log.d("jay", String.valueOf(mkplayer.getCurrentPosition()));

                MediaMetadataRetriever retriever = new MediaMetadataRetriever();

// Set data source to retriever.a
//// From your code, you might wnt to use your 'String path' here.
                retriever.setDataSource(PATH);

// Get a frame in Bitmap by specifying time.
// Be aware that the parameter must be in "microseconds", not milliseconds.

                Bitmap bitmap = retriever.getFrameAtTime(mkplayer.getCurrentPosition() * 1000, OPTION_CLOSEST);





// Do something with your bitmap.

            }
        });


        mkplayer.play(PATH);

    }






}


