package com.siy.rest;

import retrofit2.Retrofit;

/**
 * Created by lenovo on 3/14/2018.
 */

/*public class ApiClient {
    private static final String BASE_URL = "http://18.222.19.128:3003/user/";
    private static Retrofit retrofit = null;



    public static Retrofit getApiClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }
}*/

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Manish-Pc on 19/12/2017.
 */

public class ApiClient {
    private static final String BASE_URL = "http://18.218.205.25/siy/api/";

    //public static final String BASE_URL = "http://18.218.205.25/siy/api/";
    public static final String BASE_URL_UPLOADED_IMAGE = "http://18.218.205.25/siy/";
    //public static final String DATA_LIBRARY_IMAGE_LOCATION = "http://18.217.196.20/siy/dataLibrary/image/";

    public static final int EXPLORER_REQUEST_STATUS_ACCEPTED = 1;
    public static final int EXPLORER_REQUEST_STATUS_PENDING = 2;
    public static final int EXPLORER_REQUEST_STATUS_NEW_USER = 3;

    private static Retrofit retrofit = null;


    public static Retrofit getApiClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        // add logging as last interceptor only in Development Mode.
        httpClient.addInterceptor(logging);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
