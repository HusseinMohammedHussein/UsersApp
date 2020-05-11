package com.example.getjson.Retrofit.Interfaces.GetById;

import com.example.getjson.Retrofit.Models.CommentsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommentsByPostIdInterface {
    //    TODO: Use @Query  -> Comments By PostId Interface Class
    @GET("comments")
    Call<List<CommentsModel>> getComments(@Query("postId") int postId);

}
