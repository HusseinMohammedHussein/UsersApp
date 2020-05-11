package com.example.getjson.Retrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostsModel {

    @SerializedName("userId")
    @Expose
    private int mUserId;
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("body")
    @Expose
    private String mText;


    public int getmUserId() {
        return mUserId;
    }

    public int getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmText() {
        return mText;
    }
}
