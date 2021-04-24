package com.example.insura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.khizar1556.mkvideoplayer.MKPlayer;

import java.util.ArrayList;

import static android.media.MediaMetadataRetriever.OPTION_CLOSEST;
import static android.media.MediaMetadataRetriever.OPTION_CLOSEST_SYNC;

public class Play extends AppCompatActivity {

    private ArrayList<ClassListItems> itemsArrayList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ImageView iv= (ImageView ) findViewById(R.id.capture);


        Intent intent =getIntent();
        String PATH= intent.getStringExtra("PATH");
        MKPlayer mkplayer = new MKPlayer(this);

        mkplayer.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
            boolean f=false;
            @Override
            public void onNextClick() {
                //It is the method for next song.It is called when you pressed the next icon
                //Do according to your requirement
                Log.d("jaya",String.valueOf(mkplayer.getDuration()));
            }
            @Override
            public void onPreviousClick() {
                Log.d("jay",String.valueOf(mkplayer.getCurrentPosition()));

                MediaMetadataRetriever retriever = new MediaMetadataRetriever();

// Set data source to retriever.a
//// From your code, you might wnt to use your 'String path' here.
                retriever.setDataSource(PATH);

// Get a frame in Bitmap by specifying time.
// Be aware that the parameter must be in "microseconds", not milliseconds.

                Bitmap bitmap = retriever.getFrameAtTime(mkplayer.getCurrentPosition()*1000,OPTION_CLOSEST);


                    iv.setImageBitmap(bitmap);


// Do something with your bitmap.

            }
        });





        mkplayer.play(PATH);

    }

}