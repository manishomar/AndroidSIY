package com.siy.ForgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.siy.R;

public class ForgetPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    public void btnSendEmail(View view) {
        startActivity(new Intent(this, OTPActivity.class));
    }
}
