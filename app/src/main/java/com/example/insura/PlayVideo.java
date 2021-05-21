package com.example.insura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khizar1556.mkvideoplayer.MKPlayer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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

import static android.media.MediaMetadataRetriever.OPTION_CLOSEST;
import static java.lang.Math.min;

public class PlayVideo extends AppCompatActivity {

    private ArrayList<ClassListItem> itemsArrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    String PATH;
    File attachment;
    MKPlayer mkplayer;
    ArrayList<ArrayList<ClassListItem>> timeFrame;
    SwipeRefreshLayout swipeRefreshLayout;
    int i;
    MediaMetadataRetriever mediaMetadataRetriever;
  //  private MyAppAdapter myAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);


        Intent intent = getIntent();
       PATH = intent.getStringExtra("PATH");




        mkplayer = new MKPlayer(this);

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

             //   MediaMetadataRetriever retriever = new MediaMetadataRetriever();

// Set data source to retriever.a
//// From your code, you might wnt to use your 'String path' here.
              //  retriever.setDataSource(PATH);

// Get a frame in Bitmap by specifying time.
// Be aware that the parameter must be in "microseconds", not milliseconds.

          //      Bitmap bitmap = retriever.getFrameAtTime(mkplayer.getCurrentPosition() * 1000, OPTION_CLOSEST);


// Do something with your bitmap.

            }
        });
        attachment = new File(PATH);
        itemsArrayList = new ArrayList<ClassListItem>();
       //upload();
     //   System.out.println("kitna" + timeFrame.size());

       mkplayer.play(PATH);
//        while(true){
//            int pos=mkplayer.getCurrentPosition()/1000000;
//            System.out.println("time now is "+ pos);
//            recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
//            recyclerView.setHasFixedSize(true);
    //            recyclerView.setLayoutManager(new LinearLayoutManager(this));
    //
    //            ArrayList<Product> prd=timeFrame.get(pos);
    //           itemsArrayList=new ArrayList<ClassListItem>();
    //           for( Product p:prd){
    //               ClassListItem cls=new ClassListItem(pos, p.getName(),p.getUrl(),p.getImgUrl());
//           }
//
//            adapter=new MyAdapter(itemsArrayList,this);
//            recyclerView.setAdapter(adapter);
//
//        }


//        int sz=timeFrame.size();
//        Log.d("jay","timeFra(sz)"+sz);
//        for( ArrayList<ClassListItem> frame:timeFrame){
//
//
//            final Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    // Do something after 5s = 5000ms
//                    recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
//                    recyclerView.setHasFixedSize(true);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//                    //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//                    adapter = new MyAdapter(frame, getApplicationContext());
//                    recyclerView.setAdapter(adapter);
//
//                }
//            }, 4000);
//
//
//
//        }




        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        adapter=new MyAdapter(itemsArrayList,this);
        recyclerView.setAdapter(adapter);

        i=0;

//        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//
//                    recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
//                    recyclerView.setHasFixedSize(true);
//                    //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                    int pos=mkplayer.getCurrentPosition()/1000;
//                    pos = min(pos,timeFrame.size()-1);
//                    Log.d("JAY","time is "+pos);
//                    if(pos>=0  && timeFrame.size()>0 ){
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//                    adapter = new MyAdapter(timeFrame.get(pos), getApplicationContext());
//                    recyclerView.setAdapter(adapter);
//                }
//
//
//
//                    swipeRefreshLayout.setRefreshing(false);
//
//            }
//        });

    }



    public void uploadVideo(View v) {

       System.out.println("inside uupload");
        // add another part within the multipart request
        String descriptionString = "file";


        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, descriptionString);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), attachment);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", attachment.getName(), requestFile);

        Gson gson = new GsonBuilder().setLenient().create();


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(90000, TimeUnit.SECONDS)
                .connectTimeout(90000, TimeUnit.SECONDS)
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
                    timeFrame=new ArrayList<ArrayList<ClassListItem>>();


                    for (ClassListItem item: itemsArrayList){
                     //   System.out.println("hai raam"+item.getTime());
                        if(item.getTime()>=timeFrame.size()){
                            ArrayList<ClassListItem> newTime=new ArrayList<ClassListItem>();
                            newTime.add(item);
                            timeFrame.add(newTime);
                        }else{
                            timeFrame.get(item.getTime()).add(item);
                        }
                    }
                    System.out.println("kitna" + timeFrame.size());
                   // mkplayer.play(PATH);
//                    for( ArrayList<ClassListItem> frame:timeFrame){
//

//                                final Handler handler = new Handler();
//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        // Do something after 5s = 5000ms
//                                        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
//                                        recyclerView.setHasFixedSize(true);
//                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//                                        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//                                        adapter = new MyAdapter(frame, getApplicationContext());
//                                        recyclerView.setAdapter(adapter);
//
//                                    }
//                                }, 4000);



//                      }











//
//                    itemsArrayList.add(new ClassListItem("da","corona"));
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                    adapter = new MyAdapter(itemsArrayList, getApplicationContext());
//                    recyclerView.setAdapter(adapter);


                } else System.out.println("Response Failed!");


                Toast.makeText(PlayVideo.this, "Successful!", Toast.LENGTH_SHORT).show();

                recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
                                        recyclerView.setHasFixedSize(true);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                                        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                                        adapter = new MyAdapter(response.body(), getApplicationContext());
                                        recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<ClassListItem>> call, Throwable t) {

                itemsArrayList.add(new ClassListItem("Failure","connection failed"));
                //   recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                // adapter = new MyAdapter(itemsArrayList, getApplicationContext());
                recyclerView.setAdapter(adapter);

                System.out.println("corona" + t.getMessage());
                Toast.makeText(PlayVideo.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void uploadImage(View view) {
        mkplayer.pause();
        // add another part within the multipart request
        String descriptionString = "file";

        //calls mkplayer to find current pos of seekbar
        //use pos to find frame using media retriever and storing in bmFrame
        //converting this bmFrame to file
        //sending this file as attachement
        mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(PATH);
        int pos= mkplayer.getCurrentPosition();
        Bitmap bmFrame = mediaMetadataRetriever
                .getFrameAtTime(pos * 1000);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmFrame.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Random rand = new Random();
        int rand_int1 = rand.nextInt(1000);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file"+rand_int1+".jpg");
        System.out.println(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");

        try {
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.flush();
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //attachment=file;
        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, descriptionString);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

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


                Toast.makeText(PlayVideo.this, "Successful!", Toast.LENGTH_SHORT).show();
                mkplayer.start();
            }

            @Override
            public void onFailure(Call<ArrayList<ClassListItem>> call, Throwable t) {

                itemsArrayList.add(new ClassListItem("jay","https://static.toiimg.com/photo/msid-24245804,width-96,height-65.cms"));
                //   recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                // adapter = new MyAdapter(itemsArrayList, getApplicationContext());
                recyclerView.setAdapter(adapter);

                System.out.println("corona" + t.getMessage());
                Toast.makeText(PlayVideo.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
        //mkplayer.start();
    }



}


