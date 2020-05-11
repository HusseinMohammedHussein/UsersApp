package com.example.getjson.Retrofit.Models.UserDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeoModel {
    @SerializedName("lat")
    @Expose
    private String mLat;
    @SerializedName("lng")
    @Expose
    private String mLng;

    public String getmLat() {
        return mLat;
    }

    public String getmLng() {
        return mLng;
    }
}
