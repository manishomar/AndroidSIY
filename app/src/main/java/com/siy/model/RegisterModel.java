package com.siy.model;

import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
/**
 * Created by Manish on 3/12/2018.
 */

public class RegisterModel {



     @SerializedName("token")
    private String token;

    public String getToken() {

        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



}
