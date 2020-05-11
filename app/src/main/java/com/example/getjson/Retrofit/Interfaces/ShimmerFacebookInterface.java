package com.example.getjson.Retrofit.Interfaces;

import com.example.getjson.Retrofit.Models.PostsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShimmerFacebookInterface {
    @GET("posts")
    Call<List<PostsModel>> getPosts();
}
