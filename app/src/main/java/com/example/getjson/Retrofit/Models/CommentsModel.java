package com.example.getjson.Retrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsModel {
    @SerializedName("postId")
    @Expose
    private int mPostId;
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("email")
    @Expose
    private String mEmail;
    @SerializedName("body")
    @Expose
    private String mBody;

    public int getmPostId() {
        return mPostId;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmBody() {
        return mBody;
    }
}
