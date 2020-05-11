package com.example.getjson.Retrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotosModel {
    @SerializedName("albumId")
    @Expose
    private int mAlbumId;
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("url")
    @Expose
    private String mUrl;
    @SerializedName("thumbnailUrl")
    @Expose
    private String mThumbnailUrl;

    public PhotosModel(int mAlbumId, int mId, String mTitle, String mUrl, String mThumbnailUrl) {
        this.mAlbumId = mAlbumId;
        this.mId = mId;
        this.mTitle = mTitle;
        this.mUrl = mUrl;
        this.mThumbnailUrl = mThumbnailUrl;
    }

    public int getmAlbumId() {
        return mAlbumId;
    }

    public int getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmThumbnailUrl() {
        return mThumbnailUrl;
    }
}
