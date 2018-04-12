package com.siy.ForgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.siy.R;

public class OTPActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
    }

    public void btnResetPassword(View view) {
        startActivity(new Intent(this,ResetPassword.class));
    }
}
