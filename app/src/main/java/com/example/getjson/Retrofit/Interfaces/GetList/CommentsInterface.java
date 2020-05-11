package com.example.getjson.Retrofit.Interfaces.GetList;

import com.example.getjson.Retrofit.Models.CommentsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CommentsInterface {
    //TODO: getComments by @URL RETROFIT
    @GET
    Call<List<CommentsModel>> getComments(@Url String url);
}
