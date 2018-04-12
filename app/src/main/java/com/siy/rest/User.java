package com.siy.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 3/14/2018.
 */

public class User {


    @SerializedName("user_id")
    private int user_id;

    @SerializedName("email")
    private String email;

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("device_token")
    private String device_token;

    @SerializedName("device_type")
    private String device_type;


    public User() {
    }


    public int getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDevice_token() {
        return device_token;
    }

    public String getDevice_type() {
        return device_type;
    }
}
