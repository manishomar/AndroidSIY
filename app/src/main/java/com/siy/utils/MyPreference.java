package com.siy.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Anurag Chauhan on 2/26/2018.
 */
public class MyPreference {
    private Context context;
    private SharedPreferences sharedPreferences;
    private static final String MY_PERFENCE = "user";
    public static final String PROFILE_ACCESS = "profile_access";
    private SharedPreferences.Editor editor;

    public MyPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(MY_PERFENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveBoolean(String key, boolean value){
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key, false);
    }

}
