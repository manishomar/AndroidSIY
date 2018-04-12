package com.siy.rest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 3/14/2018.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("signup")
    Call<ServerResponse> signup(@Field("email") String email,
                                @Field("password") String password,
                                @Field("latitude") String latitude,
                                @Field("longitude") String longitude,
                                @Field("user_type") String user_type,
                                @Field("device_type") String device_type,
                                @Field("device_token") String device_token);


    @FormUrlEncoded
    @POST("login")
    Call<ServerResponse> login(@Field("email") String email,
                               @Field("password") String password,
                               @Field("latitude") String latitude,
                               @Field("longitude") String longitude,
                               @Field("user_type") String user_type,
                               @Field("device_type") String device_type,
                               @Field("device_token") String device_token);


    @FormUrlEncoded
    @POST("createProfile")
    Call<ServerResponse> createProfile(@Field("user_type") String user_type,
                                       @Field("first_name") String first_name,
                                       @Field("last_name") String last_name,
                                       @Field("dob") String dob,
                                       @Field("is_request_student") String is_request_student,
                                       @Field("term_condition") String term_condition,
                                       @Field("profile_status") String profile_status,
                                       @Field("language") String language,
                                       @Field("source_token") String source_token);


}
