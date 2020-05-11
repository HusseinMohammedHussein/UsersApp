package com.example.getjson.Retrofit.Interfaces.GetList;

import com.example.getjson.Retrofit.Models.AlbumsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumsListInterface {
    @GET("albums")
    Call<List<AlbumsModel>> getAlbumsList();

}
