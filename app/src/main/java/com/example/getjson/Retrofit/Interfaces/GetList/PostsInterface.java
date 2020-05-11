package com.example.getjson.Retrofit.Interfaces.GetList;

import com.example.getjson.Retrofit.Models.PostsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsInterface {
    //TODO:getPosts by @GET RETROFIT.
    @GET("posts")
    Call<List<PostsModel>> getPosts();
}
