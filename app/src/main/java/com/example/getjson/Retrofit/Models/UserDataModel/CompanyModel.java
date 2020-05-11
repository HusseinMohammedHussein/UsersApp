package com.example.getjson.Retrofit.Models.UserDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyModel {

    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("catchPhrase")
    @Expose
    private String mCatchPhrase;
    @SerializedName("bs")
    @Expose
    private String mBs;

    public String getmName() {
        return mName;
    }

    public String getmCatchPhrase() {
        return mCatchPhrase;
    }

    public String getmBs() {
        return mBs;
    }
}
