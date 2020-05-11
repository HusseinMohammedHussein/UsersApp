package com.example.getjson.Retrofit.APIControl;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIControl {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitWithShimmer() {
        if (retrofit == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder().
                    connectTimeout(5, TimeUnit.SECONDS).
                    writeTimeout(5, TimeUnit.SECONDS).
                    readTimeout(5, TimeUnit.SECONDS).
                    callTimeout(5, TimeUnit.SECONDS).
                    build();

            retrofit = new Retrofit.Builder().
                    client(okHttpClient).
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofit() {
        retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        return retrofit;
    }
}
