package com.siy.controller.RecorderSignUp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.siy.R;
import com.siy.controller.ExplorerSignUp.ProfileActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecorderProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backArrow;
    private CircleImageView civProfilePic;
    private CircleImageView civUploadprofile;
    private ImageView ivUpdateProfilePic;
    private CircleImageView civUpadateUploadProfile;
    private EditText etFirstName;
    private EditText etLastName;
    private TextView tvDOB;
    private EditText etCurrentAddress;
    private Spinner spinnerProfession;
    private EditText etDistance;
    private EditText etPaymentSetting;
    private CheckBox cbCondition;
    private Button btnSubmit;
    private static boolean value=true;
    private static final int GALLERY_CONSTANT = 12;
    private static final int CAMERA_CONSTANT = 20;
    public Uri imageuri;
    public Bitmap bitmap;
    private String profession[];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder_profile);


        backArrow = (ImageView) findViewById(R.id.iv_backarrow);
        civProfilePic = (CircleImageView) findViewById(R.id.civ_profile_pic);
        civUploadprofile = (CircleImageView) findViewById(R.id.civ_upload_image);
        ivUpdateProfilePic = (ImageView) findViewById(R.id.iv_update_profilepic);
        civUpadateUploadProfile = (CircleImageView) findViewById(R.id.civ_update_upload_pic);
        etFirstName = (EditText) findViewById(R.id.et_fname);
        etLastName = (EditText) findViewById(R.id.et_lname);
        tvDOB = (TextView) findViewById(R.id.tv_dob);
        etCurrentAddress = (EditText) findViewById(R.id.et_current_address);
        spinnerProfession = (Spinner) findViewById(R.id.spinner_i_am_a);
        etDistance = (EditText) findViewById(R.id.et_distance);
        etPaymentSetting = (EditText) findViewById(R.id.et_edit_payment_setting);
        cbCondition = (CheckBox) findViewById(R.id.checkbox_condition);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        showProfession();


        backArrow.setOnClickListener(this);
        civUploadprofile.setOnClickListener(this);
        civUpadateUploadProfile.setOnClickListener(this);
        tvDOB.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_backarrow:
                super.onBackPressed();
                break;
            case R.id.civ_upload_image:
                captureImage();
                break;

            case R.id.civ_update_upload_pic:
                value=false;
                captureImage();
                break;

            case R.id.btn_submit:
                startActivity(new Intent(this,VerifyEmailIdActivity.class));
                break;

            case R.id.tv_dob:
                displayDatePickerDialog();
                break;

        }
    }

    //showProfessions
    public void showProfession() {
        profession = getResources().getStringArray(R.array.profession);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, profession);
        spinnerProfession.setAdapter(adapter);
        spinnerProfession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String SelectedLanguage = profession[i];
                Log.d("Test", "selectedLanguage=" + SelectedLanguage);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

    }






    //for getting image from gallery and camera
    private void captureImage() {

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
                if (value==true) {
                    civProfilePic.setImageURI(imageuri);
                }else
                {
                    ivUpdateProfilePic.setImageURI(imageuri);
                }
                }
        } else if (requestCode == CAMERA_CONSTANT) {
            if (resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
                if (value==true) {
                    civProfilePic.setImageBitmap(bitmap);
                }else
                {
                    ivUpdateProfilePic.setImageBitmap(bitmap);
                }

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datepicker = new DatePickerDialog(this, new RecorderProfileActivity.MyDatePickerListener(), year, month, day);
        datepicker.show();
    }

    //datepicker class
    class MyDatePickerListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            String selectedDate = year + "-" + (++month) + "-" + day;
            Log.d("TEST", "Selected Date : " + selectedDate);
            tvDOB.setText("DOB: " + selectedDate);
        }
    }

}
