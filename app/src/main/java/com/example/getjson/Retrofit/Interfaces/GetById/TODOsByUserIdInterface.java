package com.example.getjson.Retrofit.Interfaces.GetById;

import com.example.getjson.Retrofit.Models.TODOsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface TODOsByUserIdInterface {
    //    TODO: Use @Url -> TODOs By UserId Interface
    @GET
    Call<List<TODOsModel>> getTODOsByUserId(@Url String url);
}
