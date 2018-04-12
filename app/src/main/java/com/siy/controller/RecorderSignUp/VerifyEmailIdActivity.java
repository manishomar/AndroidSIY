package com.siy.controller.RecorderSignUp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.siy.Map.HomeActivity;
import com.siy.R;
import com.siy.controller.ExplorerSignUp.ProfileActivity;

public class VerifyEmailIdActivity extends AppCompatActivity {
    private RelativeLayout rootLayout;

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email_id);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        rootLayout= (RelativeLayout) findViewById(R.id.root_layout);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyRecorder();
                //startActivity(new Intent(VerifyEmailIdActivity.this, RecorderHomeActivity.class));
            }
        });
    }

    private void verifyRecorder() {
        showConfirmationDialogBox();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(VerifyEmailIdActivity.this, RecorderHomeActivity.class));
                finish();
            }
        }, 2000);


    }

    private void showConfirmationDialogBox() {
        rootLayout.setVisibility(View.INVISIBLE);
        final AlertDialog.Builder builder = new AlertDialog.Builder(VerifyEmailIdActivity.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.congratulation, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void backActivity(View view) {
        super.onBackPressed();
    }
}
