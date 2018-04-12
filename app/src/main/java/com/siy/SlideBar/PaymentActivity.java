package com.siy.SlideBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.siy.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    public void ivToBackActivity(View view) {
        super.onBackPressed();
    }
}
