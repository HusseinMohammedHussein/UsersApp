package com.example.getjson.Retrofit.Interfaces.GetById;

import com.example.getjson.Retrofit.Models.PhotosModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PhotosByAlbumIdInterface {
    //    TODO: Use @Path -> Photos By AlbumId Interface
    @GET("albums/{id}/photos")
    Call<List<PhotosModel>> getAlbums(@Path("id") int albumId);
}
