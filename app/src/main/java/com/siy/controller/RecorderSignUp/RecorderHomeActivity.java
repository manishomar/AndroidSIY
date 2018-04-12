package com.siy.controller.RecorderSignUp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.siy.R;
import com.siy.SlideBar.ChatListActivity;
import com.siy.SlideBar.HelpActivity;
import com.siy.SlideBar.SettingActivity;

public class RecorderHomeActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout recordCapture;
    private RelativeLayout taskList;
    private RelativeLayout chatList;
    private RelativeLayout paymentHistory;
    private RelativeLayout setting;
    private RelativeLayout help;
    private static final int GALLERY_CONSTANT = 12;
    private static final int CAMERA_CONSTANT = 20;
    public Uri imageuri;
    public Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder_home);
        recordCapture = (RelativeLayout) findViewById(R.id.root_layout_record_capture);
        taskList = (RelativeLayout) findViewById(R.id.root_layout_tasklist);
        chatList = (RelativeLayout) findViewById(R.id.root_layout_chatlist);
        paymentHistory = (RelativeLayout) findViewById(R.id.root_layout_payment_history);
        setting = (RelativeLayout) findViewById(R.id.root_layout_setting);
        help = (RelativeLayout) findViewById(R.id.root_layout_help);
        recordCapture.setOnClickListener(this);
        taskList.setOnClickListener(this);
        chatList.setOnClickListener(this);
        paymentHistory.setOnClickListener(this);
        setting.setOnClickListener(this);
        help.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.root_layout_record_capture:
                capture();
                break;

            case R.id.root_layout_chatlist:
                startActivity(new Intent(this, ChatListActivity.class));
                break;

            case R.id.root_layout_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;

            case R.id.root_layout_help:
                startActivity(new Intent(this, HelpActivity.class));
                break;

        }

    }


    //for capture image from gallery and camera
    private void capture() {

        String option[] = {"camera", "gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Complete action using");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    openCamera();
                    //Toast.makeText(Ui_Component.this, "openCamera", Toast.LENGTH_SHORT).show();

                } else if (i == 1) {
                    openGallery();
                }

            }
        });

        builder.show();

    }

    private void openCamera() {

        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraintent, CAMERA_CONSTANT);

    }


    private void openGallery() {

        Intent galleryintent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent, GALLERY_CONSTANT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CONSTANT) {
            if (resultCode == RESULT_OK) {
                imageuri = data.getData();
            }
        } else if (requestCode == CAMERA_CONSTANT) {
            if (resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");


            }
        }
    }


}
