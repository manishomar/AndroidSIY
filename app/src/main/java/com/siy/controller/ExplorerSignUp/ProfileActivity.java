package com.siy.controller.ExplorerSignUp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siy.Map.HomeActivity;
import com.siy.R;
import com.siy.rest.ApiClient;
import com.siy.rest.ApiInterface;
import com.siy.rest.ServerResponse;
import com.siy.rest.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivArrow;
    private EditText etFName;
    private EditText etLName;
    private TextView tvDOB;
    private Spinner spinnerLanguage;
    private String language[];
    private CheckBox cbTerms;
    private Button btnSubmit;
    private String selectedDate;
    private String SelectedLanguage;
    private RelativeLayout rootActivity;
    private Boolean value;
    private String user_type;
    private String fName;
    private String lName;
    private String dob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivArrow = (ImageView) findViewById(R.id.iv_arrow_profile);
        etFName = (EditText) findViewById(R.id.et_fname);
        etLName = (EditText) findViewById(R.id.et_lname);
        cbTerms = (CheckBox) findViewById(R.id.cb_terms);
        rootActivity = (RelativeLayout) findViewById(R.id.root_activity);
        spinnerLanguage = (Spinner) findViewById(R.id.spinner_language);
        tvDOB = (TextView) findViewById(R.id.tv_dob);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        ivArrow.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvDOB.setOnClickListener(this);
        showLanguages();


    }

    public void showLanguages() {
        language = getResources().getStringArray(R.array.language);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, language);
        spinnerLanguage.setAdapter(adapter);
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SelectedLanguage = language[i];
                Log.d("Test", "selectedLanguage=" + SelectedLanguage);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

    }

    //for getting date
    @RequiresApi(api = Build.VERSION_CODES.N)

    private void displayDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datepicker = new DatePickerDialog(this, new MyDatePickerListener(), year, month, day);
        datepicker.show();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                validateInput();
                break;


            case R.id.tv_dob:
                displayDatePickerDialog();
                break;

            case R.id.iv_arrow_profile:
                super.onBackPressed();
                break;
        }
    }

    private void validateInput() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);




        user_type = "1";
        fName = etFName.getText().toString().trim();
        lName = etLName.getText().toString().trim();
        dob = selectedDate;
        String language = SelectedLanguage;
        String term_condition="2";
        String profile_status="1";
        String is_request_student="0";
        String source_token=preferences.getString("access_token", "");


        if (TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName) && TextUtils.isEmpty(dob)) {
            etFName.setError("Enter first Name!");
            etLName.setError("Enter Last Name!");
            tvDOB.setError("Enter DateOfBirth!");
            etFName.requestFocus();
            return;
            // Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(fName)) {
            etFName.setError("Enter First Name!");
            etFName.requestFocus();
            return;
        } else if (TextUtils.isEmpty(lName)) {
            etLName.setError("Enter Last Name!");
            etLName.requestFocus();
            return;
        } else if (TextUtils.isEmpty(dob)) {
            tvDOB.setError("Enter DOB!");
            tvDOB.requestFocus();
            return;
        } else if (!cbTerms.isChecked()) {
            Toast.makeText(this, "Please check the checkbox", Toast.LENGTH_SHORT).show();
        } else {

            showConfirmationDialogBox();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                    finish();
                }
            }, 2000);


        }

        createProfile(user_type,fName,lName,dob,is_request_student,term_condition,profile_status,language,source_token);

    }

    private void createProfile(String user_type, String first_name, String last_name, String dob,
                               String is_request_student, String term_condition, String profile_status, String language,String source_token) {


        // Check for Internet connection.

        //Log.d("TEST", "EMAIL_ERROR" + email);
        // Progress Dialog
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ServerResponse> call = apiInterface.createProfile(user_type, first_name, last_name, dob, is_request_student,term_condition, profile_status,language,source_token);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                // dimiss
                if (response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    String message = serverResponse.message;
                    String accessToken=serverResponse.user.getAccess_token();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("access_token", accessToken);
                    editor.commit();

                    Toast.makeText(ProfileActivity.this, "Registration Success" + message, Toast.LENGTH_SHORT).show();
                    User user = serverResponse.user;
                    Intent intent = new Intent(ProfileActivity.this, IntroSlideActivity.class);
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
                Toast.makeText(ProfileActivity.this, "failure" + t, Toast.LENGTH_LONG).show();
                Log.d("TEST", "Failure :  " + t.getMessage());
            }
        });




    }

    //datepicker class
    class MyDatePickerListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            selectedDate = year + "-" + (++month) + "-" + day;
            Log.d("TEST", "Selected Date : " + selectedDate);
            tvDOB.setText("DOB: " + selectedDate);
        }
    }

    private void showConfirmationDialogBox() {
        rootActivity.setVisibility(View.INVISIBLE);
        final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.congratulation, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
