package com.example.getjson.Retrofit.Models.UserDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressModel {
    @SerializedName("street")
    @Expose
    private String mStreet;
    @SerializedName("suite")
    @Expose
    private String mSuite;
    @SerializedName("city")
    @Expose
    private String mCity;
    @SerializedName("geo")
    @Expose
    private GeoModel mGeo;

    public String getmStreet() {
        return mStreet;
    }

    public String getmSuite() {
        return mSuite;
    }

    public String getmCity() {
        return mCity;
    }

    public GeoModel getmGeo() {
        return mGeo;
    }
}
