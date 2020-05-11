package com.example.getjson.Retrofit.Interfaces.GetList;

import com.example.getjson.Retrofit.Models.TODOsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TODOsListInterface {
    @GET("todos")
    Call<List<TODOsModel>> getTODOsList();
}
