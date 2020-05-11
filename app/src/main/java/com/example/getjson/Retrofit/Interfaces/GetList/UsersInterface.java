package com.example.getjson.Retrofit.Interfaces.GetList;


import com.example.getjson.Retrofit.Models.UsersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersInterface {
    @GET("users")
    Call<List<UsersModel>> getUsers();
}
