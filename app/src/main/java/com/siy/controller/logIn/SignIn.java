package com.siy.controller.logIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.siy.ForgetPassword.ForgetPasswordActivity;
import com.siy.Map.HomeActivity;
import com.siy.R;
import com.siy.controller.ExplorerSignUp.ExplorerSignUpActivity;
import com.siy.controller.ExplorerSignUp.IntroSlideActivity;
import com.siy.controller.RecorderSignUp.RecorderSignUpActivity;
import com.siy.rest.ApiClient;
import com.siy.rest.ApiInterface;
import com.siy.rest.ServerResponse;
import com.siy.rest.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    private boolean status;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignIn;
    private TextView tvForgetPasswordSignIn;
    private Button btnSignUp;
    private String email;
    private String password;
    private String latitude;
    private String longitude;
    private String user_type;
    private String device_type;
    private String device_token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etEmail= (EditText) findViewById(R.id.et_email);
        etPassword= (EditText) findViewById(R.id.et_password);
        btnSignIn= (Button) findViewById(R.id.btn_signin);
        tvForgetPasswordSignIn= (TextView) findViewById(R.id.tv_forget_password_signin);
        btnSignUp= (Button) findViewById(R.id.btn_signup);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        status=getIntent().getExtras().getBoolean("STATUS");
    }



    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.btn_signin:
            fetchData();
            //startActivity(new Intent(SignIn.this, HomeActivity.class));
            break;
        case R.id.btn_signup:
            if(status){
                startActivity(new Intent(this,ExplorerSignUpActivity.class));
            }else {
                startActivity(new Intent(this,RecorderSignUpActivity.class));
            }
            break;
    }
    }

    private void fetchData() {
        email=etEmail.getText().toString().trim();
        password=etPassword.getText().toString().trim();
        latitude = "45.10";
        longitude = "85.12";
        user_type = "1";
        device_type = "1";
        device_token = "12sdsfgf4ver4bgh510";
        loginUser(email,password,latitude,longitude,user_type,device_type,device_token);
    }

    private void loginUser(String email, String password, String latitude, String longitude, String user_type, String device_type, String device_token) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ServerResponse> call = apiInterface.login(email, password, latitude, longitude, user_type, device_type, device_token);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                // dimiss
                if (response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    String message = serverResponse.message;

                    Toast.makeText(SignIn.this, "Registration Success" + message, Toast.LENGTH_SHORT).show();
                    User user = serverResponse.user;
                    startActivity(new Intent(SignIn.this, HomeActivity.class));
                 /*   Intent intent = new Intent(SignIn.this, IntroSlideActivity.class);
                    intent.putExtra("STATUS", value);
                    startActivity(intent);*/
                } else {
                    try {
                        String error = response.errorBody().string();
                        Toast.makeText(SignIn.this, "You are register as Recorder", Toast.LENGTH_SHORT).show();

                        Log.d("TEST", "Error : " + error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // dismiss
                Toast.makeText(SignIn.this, "failure" + t, Toast.LENGTH_LONG).show();
                Log.d("TEST", "Failure :  " + t.getMessage());
            }
        });




    }

    public void tvForgotPassword(View view) {
        startActivity(new Intent(this,ForgetPasswordActivity.class));
    }
}
