package com.example.insura;


import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
        import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface JsonPlaceHolderApi {
    public String BASE_URL = "http://192.168.1.2:4000/" ;
//    @GET("posts")
//    Call<ArrayList<Post>> getPosts();

    @Multipart
    @POST("/upload")
    Call<ArrayList<ClassListItem>> upload(@Part MultipartBody.Part imageFile,
                           @Part("description") RequestBody description
    );
}
