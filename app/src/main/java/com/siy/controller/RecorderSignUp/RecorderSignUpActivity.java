package com.siy.controller.RecorderSignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.siy.R;
import com.siy.controller.ExplorerSignUp.IntroSlideActivity;
import com.siy.controller.logIn.SignIn;

public class RecorderSignUpActivity extends AppCompatActivity {
    private boolean value=false;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder_sign_up);

        etEmail= (EditText) findViewById(R.id.et_email_signup_recorder);
        etPassword= (EditText) findViewById(R.id.et_password_signup_recorder);
        etConfirmPassword= (EditText) findViewById(R.id.et_confirmpassword_signup_recorder);
        btnSignUp= (Button) findViewById(R.id.btn_signup_recorder);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputFields();
                //startActivity(new Intent(RecorderSignUpActivity.this,IntroSlideRecorderActivity.class));
            }
        });

    }

    public void imgPrev(View view) {
     super.onBackPressed();
    }

    public void btnSignIn(View view) {
        Intent intentsignin=new Intent(this,SignIn.class);
        intentsignin.putExtra("STATUS",value);
        startActivity(intentsignin);
    }

    private void validateInputFields() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

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
            Intent intent=new Intent(this,IntroSlideRecorderActivity.class);
            intent.putExtra("STATUS", value);
            startActivity(intent);
            //signupUser(email, password);
        }
    }


}
