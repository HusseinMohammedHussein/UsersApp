package com.example.getjson.Retrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TODOsModel {
    @SerializedName("userId")
    @Expose
    private int mUserId;
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("completed")
    @Expose
    private boolean mCompleted;

    public TODOsModel(int mUserId, int mId, String mTitle, boolean mCompleted) {
        this.mUserId = mUserId;
        this.mId = mId;
        this.mTitle = mTitle;
        this.mCompleted = mCompleted;
    }

    public int getmUserId() {
        return mUserId;
    }

    public int getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public boolean getmCompleted() {
        return mCompleted;
    }
}
