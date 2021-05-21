package com.example.insura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayImage extends AppCompatActivity {
    private ArrayList<ClassListItem> itemsArrayList;
    private ArrayList<Post> postList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    ImageView img;
    File attachment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_image);


        Intent intent = getIntent();
        String path = intent.getStringExtra("PATH");
        img = (ImageView) findViewById(R.id.img);

        attachment = new File(path);

        if (attachment.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(attachment.getAbsolutePath());
            img.setImageBitmap(myBitmap);

        }
//        img.setImageURI(uri);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);


        itemsArrayList = new ArrayList<>();
        //getPosts();
        upload();


       // itemsArrayList.add(new ClassListItem(1,"jay","www/kdlskf.com","kdjlaksfjdklsajf"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MyAdapter(itemsArrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);


        //Log.d("Araylistsize", s);


    }

    public void upload() {
        // add another part within the multipart request
        String descriptionString = "file";


        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, descriptionString);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), attachment);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", attachment.getName(), requestFile);

        Gson gson = new GsonBuilder().setLenient().create();

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(900000, TimeUnit.SECONDS)
                .connectTimeout(900000, TimeUnit.SECONDS)
                .build();


        Retrofit.Builder builder = new Retrofit.Builder()
                                        .baseUrl(JsonPlaceHolderApi.BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .client(okHttpClient);

        Retrofit retrofit = builder.build();

        //Get client and call object for the request
        JsonPlaceHolderApi client = retrofit.create(JsonPlaceHolderApi.class);

        //Execute the request
        Call<ArrayList<ClassListItem>> call = client.upload(body, description);


        call.enqueue(new Callback<ArrayList<ClassListItem>>() {
            @Override
            public void onResponse(Call<ArrayList<ClassListItem>> call, Response<ArrayList<ClassListItem>> response) {


                if (response.isSuccessful()) {
                    assert response.body() != null;
                    itemsArrayList= response.body();



                    int sz=itemsArrayList.size();
                    Log.d("jay","String.valueOf(sz)"+sz);



                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    adapter = new MyAdapter(itemsArrayList, getApplicationContext());
                    recyclerView.setAdapter(adapter);



                } else System.out.println("Response Failed!");


                Toast.makeText(PlayImage.this, "Successful!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ArrayList<ClassListItem>> call, Throwable t) {

                itemsArrayList.add(new ClassListItem("jay","https://static.toiimg.com/photo/msid-24245804,width-96,height-65.cms"));
             //   recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
               // adapter = new MyAdapter(itemsArrayList, getApplicationContext());
                recyclerView.setAdapter(adapter);

                System.out.println("corona" + t.getMessage());
                Toast.makeText(PlayImage.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }

//
//    void getPosts() {
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(JsonPlaceHolderApi.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//        Call<ArrayList<Post>> call = jsonPlaceHolderApi.getPosts();
//        call.enqueue(new Callback<ArrayList<Post>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
//                if (!response.isSuccessful()) {
//                    ClassListItem listItem = new ClassListItem("response not successful", "Code: " + response.code());
//                        .add(listItem);
//
//                    return;
//                }
//                ArrayList<Post> posts = response.body();
//                for (Post post : posts) {
//
//                    String content = "";
//                    content += "ID: " + post.getId() + "\n";
//                    content += "User ID: " + post.getUserId() + "\n";
//                    content += "Title: " + post.getTitle() + "\n";
//                    content += "Text: " + post.getText() + "\n\n";
//
//
//                    postList.add(post);
//                    Log.d("jay", "onResponse: " + content);
//
//                }
//
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//                adapter = new MyAdapter(postList, getApplicationContext());
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
//                ClassListItem listItem = new ClassListItem("Failed", t.getMessage());
//                itemsArrayList.add(listItem);
//
//            }
//        });
//
//    }
//



}