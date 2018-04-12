package com.siy.SlideBar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.siy.Map.HomeActivity;
import com.siy.R;
import com.siy.controller.logIn.MainActivity;

public class SettingActivity extends AppCompatActivity {
    private ImageView ivBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ivBackArrow= (ImageView) findViewById(R.id.iv_arrow_setting);
      /*  ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, HomeActivity.class));
            }
        });*/
    }

    public void tvLogOut(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void ivToBackActivity(View view) {
        super.onBackPressed();
    }
}
