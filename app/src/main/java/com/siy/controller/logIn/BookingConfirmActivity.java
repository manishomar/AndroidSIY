package com.siy.controller.logIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.siy.Map.HomeActivity;
import com.siy.R;

public class BookingConfirmActivity extends AppCompatActivity {
    private ImageView ivArrowback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);
        ivArrowback= (ImageView) findViewById(R.id.iv_arrow_booking_confirm);
        ivArrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               BookingConfirmActivity.super.onBackPressed();
            }
        });
    }
}
