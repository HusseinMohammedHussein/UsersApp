package com.example.getjson.Retrofit.Interfaces.GetById;

import com.example.getjson.Retrofit.Models.AlbumsModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface AlbumsByUserIdInterface {
    //    TODO: Use @QueryMap -> Albums By UserId Interface
    @GET("albums")
    Call<List<AlbumsModel>> getAlbumsByUserId(@QueryMap Map<String, String> parameters);
}
