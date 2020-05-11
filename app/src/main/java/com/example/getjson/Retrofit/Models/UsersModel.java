package com.example.getjson.Retrofit.Models;

import com.example.getjson.Retrofit.Models.UserDataModel.AddressModel;
import com.example.getjson.Retrofit.Models.UserDataModel.CompanyModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsersModel {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("username")
    @Expose
    private String mUsername;
    @SerializedName("email")
    @Expose
    private String mEmail;
    @SerializedName("phone")
    @Expose
    private String mPhone;
    /* Address Array */
    @SerializedName("address")
    @Expose
    private AddressModel mAddress;
    /* Company Array */
    @SerializedName("company")
    @Expose
    private CompanyModel mCompany;


    public UsersModel(int mId, String mName, String mUsername, String mEmail, String phone, AddressModel address, CompanyModel mCompany) {
        this.mId = mId;
        this.mName = mName;
        this.mUsername = mUsername;
        this.mEmail = mEmail;
        this.mPhone = phone;
        this.mAddress = address;
        this.mCompany = mCompany;
    }

    public AddressModel getmAddress() {
        return mAddress;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmUsername() {
        return mUsername;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmPhone() {
        return mPhone;
    }

    public CompanyModel getmCompany() {
        return mCompany;
    }
}


