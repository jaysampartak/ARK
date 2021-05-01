package com.example.insura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayImage extends AppCompatActivity {
    private ArrayList<ClassListItem> itemsArrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_image);


        Intent intent = getIntent();
        String path = intent.getStringExtra("PATH");
        img=(ImageView)findViewById(R.id.img);

        File imgFile = new File(path);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            img.setImageBitmap(myBitmap);

        }
//        img.setImageURI(uri);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        itemsArrayList=new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    ClassListItem listItem=new ClassListItem("response not successful","Code: " + response.code() );
                    itemsArrayList.add(listItem);

                    return;
                }
               List<Post> posts =response.body();
                for (Post post: posts) {

                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    String j=content;
                    ClassListItem listItem=new ClassListItem("Success", j );
                   // itemsArrayList.add(0,listItem);
                    Log.d("jay", "onResponse: "+content);

                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
                adapter=new MyAdapter(itemsArrayList,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                ClassListItem listItem=new ClassListItem("Failed",t.getMessage() );
                itemsArrayList.add(listItem);

            }
        });

        String s=itemsArrayList.toString();
        Log.d("Araylistsize", s);

        for(int i=0;i<10;i++){
            ClassListItem listItem=new ClassListItem("Love RNN","CP jindabad" );
            itemsArrayList.add(listItem);
        }





    }

}