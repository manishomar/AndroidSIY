package com.siy.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 4/10/2018.
 */

public class ServerResponse {

    @SerializedName("message")
    public String message;

    @SerializedName("result")
    public User user;

}
