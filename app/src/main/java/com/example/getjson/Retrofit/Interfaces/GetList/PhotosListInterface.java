package com.example.getjson.Retrofit.Interfaces.GetList;

import com.example.getjson.Retrofit.Models.PhotosModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotosListInterface {
    @GET("photos")
    Call<List<PhotosModel>> getPhotosList();
}
