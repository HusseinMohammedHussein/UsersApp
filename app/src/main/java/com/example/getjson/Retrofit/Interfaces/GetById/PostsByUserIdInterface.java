package com.example.getjson.Retrofit.Interfaces.GetById;

import com.example.getjson.Retrofit.Models.PostsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostsByUserIdInterface {
    //    TODO: Use @Path -> Posts By UserId Interface
    @GET("users/{id}/posts")
    Call<List<PostsModel>> getPostsByUserId(@Path("id") int userId);
}
