package com.siy.controller.logIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.siy.R;
import com.siy.controller.ExplorerSignUp.ExplorerSignUpActivity;
import com.siy.controller.RecorderSignUp.RecorderSignUpActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnExplorer;
    private Button btnRecorder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         btnExplorer = (Button) findViewById(R.id.btn_explorer);
         btnRecorder = (Button) findViewById(R.id.btn_recorder);
        btnExplorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ExplorerSignUpActivity.class));
            }
        });

        btnRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecorderSignUpActivity.class));
            }
        });

    }
}
