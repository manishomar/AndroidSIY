package com.siy.controller.ExplorerSignUp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.siy.R;
import com.siy.controller.logIn.MainActivity;
import com.siy.controller.logIn.SignIn;
import com.siy.rest.ApiClient;
import com.siy.rest.ApiInterface;
import com.siy.rest.ServerResponse;
import com.siy.rest.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExplorerSignUpActivity extends AppCompatActivity {
    private boolean value = true;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnSignUp;
    private String email;
    private String password;
    private String confirmPassword;
    private String latitude;
    private String longitude;
    private String user_type;
    private String device_type;
    private String device_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer_sign_up);

        etEmail = (EditText) findViewById(R.id.et_email_signup_explorer);
        etPassword = (EditText) findViewById(R.id.et_password_signup_explorer);
        etConfirmPassword = (EditText) findViewById(R.id.et_confirmpassword_signup_explorer);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        /*email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirmPassword = etConfirmPassword.getText().toString().trim();*/

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputFields();
            }
        });


       /* btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputFields();
            }
        });*/


    }

    private void validateInputFields() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirmPassword = etConfirmPassword.getText().toString().trim();
        latitude = "45.10";
        longitude = "85.12";
        user_type = "1";
        device_type = "1";
        device_token = "12sdsfgf4ver4bgh510";


        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confirmPassword)) {
            etEmail.setError("Enter Email!");
            etPassword.setError("Enter Password!");
            etConfirmPassword.setError("Enter Confirm Password!");
            etEmail.requestFocus();
            return;
            // Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            etPassword.setError("Enter Email!");
            etEmail.requestFocus();
            return;
            //Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError("Enter Password!");
            etPassword.requestFocus();
            return;
            //Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmPassword)) {
            etPassword.setError("Enter Confirm Password!");
            etConfirmPassword.requestFocus();
            return;
            //Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password don't matchces", Toast.LENGTH_SHORT).show();
            etPassword.requestFocus();
            return;
        } else if (!email.matches(emailPattern)) {

            Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return;
        } else if (!(password.length() > 4)) {

            Toast.makeText(this, "Password should be more than 4 character", Toast.LENGTH_SHORT).show();
            etPassword.requestFocus();
            return;
        } else {
            /*Intent intent = new Intent(this, IntroSlideActivity.class);
            intent.putExtra("STATUS", value);
            startActivity(intent)
            ;*/
            signupUser(email, password, latitude, longitude, user_type, device_type, device_token);
        }
    }

    private void signupUser(String email, String password, String latitude, String longitude, String user_type, String device_type, String device_token) {
        // Check for Internet connection.

        Log.d("TEST", "EMAIL_ERROR" + email);
        // Progress Dialog
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ServerResponse> call = apiInterface.signup(email, password, latitude, longitude, user_type, device_type, device_token);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                // dimiss
                if (response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    String message = serverResponse.message;
                    String accessToken=serverResponse.user.getAccess_token();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ExplorerSignUpActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("access_token", accessToken);
                    editor.commit();

                    Toast.makeText(ExplorerSignUpActivity.this, "Registration Success" + message, Toast.LENGTH_SHORT).show();
                    User user = serverResponse.user;
                    Intent intent = new Intent(ExplorerSignUpActivity.this, IntroSlideActivity.class);
                    intent.putExtra("STATUS", value);
                    startActivity(intent);
                } else {
                    try {
                        String error = response.errorBody().string();

                        Log.d("TEST", "Error : " + error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // dismiss
                Toast.makeText(ExplorerSignUpActivity.this, "failure" + t, Toast.LENGTH_LONG).show();
                Log.d("TEST", "Failure :  " + t.getMessage());
            }
        });


    }

    public void imgPrev(View view) {
        super.onBackPressed();
    }

    public void btnSignIn(View view) {
        Intent intentsignin = new Intent(this, SignIn.class);
        intentsignin.putExtra("STATUS", value);
        startActivity(intentsignin);
    }

}



































        /*btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUsertoServer(email, password);
               Intent intent=new Intent(ExplorerSignUpActivity.this, IntroSlideActivity.class);
                intent.putExtra("STATUS",value);
                startActivity(intent);
            }
        });


    }


    public void imgPrev(View view) {
        startActivity(new Intent(ExplorerSignUpActivity.this, MainActivity.class));
    }

    public void btnSignIn(View view) {
        Intent intentsignin = new Intent(this, SignIn.class);
        intentsignin.putExtra("STATUS", value);
        startActivity(intentsignin);
    }

    //registeruser method for retrofit
    *//*private void registerUsertoServer(String email, String password) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RegisterModel> call=apiService.registerUser(email,password);
                call.enqueue(new Callback<RegisterModel>() {
                    @Override
                    public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                        Toast.makeText(ExplorerSignUpActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<RegisterModel> call, Throwable t) {
                        Toast.makeText(ExplorerSignUpActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
    }*/

