package com.siy.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*import com.google.android.gms.location.places.Place; // "Place" is not resolved
import com.google.android.gms.location.places.ui.PlacePicker; // "ui" is not resolved
import com.google.android.gms.maps.model.LatLng;*/

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.siy.R;
import com.siy.SlideBar.ChatListActivity;
import com.siy.SlideBar.HelpActivity;
import com.siy.SlideBar.PaymentActivity;
import com.siy.SlideBar.SettingActivity;
import com.siy.controller.logIn.BookingConfirmActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {
    private Context context;
    private GoogleMap mMap;

    private ImageView ivArrowSlidebar;
    private TextView tvPlacePicker;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ImageView ivHamburger;
    private TextView tvChatList;
    private TextView tvPayment;
    private TextView tvSetting;
    private TextView tvHelp;
    private LinearLayout slideContent;
    private RelativeLayout slideHeader;
    private Button btnHire;
    private final static int Place_Picker_Request = 1;
    private final static LatLngBounds bounds = new LatLngBounds(new LatLng(51.5152192, -0.1321900), new LatLng(51.5166013, -0.1299262));
    public static LatLng latLng = null;
    private double selectedLatitude;
    private double selectedLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        ivArrowSlidebar = (ImageView) findViewById(R.id.iv_slidebar_backarrow);
        toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        tvPlacePicker = (TextView) findViewById(R.id.tv_place_picker);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ivHamburger = (ImageView) toolbar.findViewById(R.id.iv_hamburger);
        slideContent = (LinearLayout) findViewById(R.id.include_slidbar_content);
        slideHeader = (RelativeLayout) findViewById(R.id.include_slidebar_header);
        tvChatList = (TextView) slideContent.findViewById(R.id.tv_chatlist);
        tvPayment = (TextView) slideContent.findViewById(R.id.tv_payment);
        tvSetting = (TextView) slideContent.findViewById(R.id.tv_setting);
        tvHelp = (TextView) slideContent.findViewById(R.id.tv_help);
        btnHire = (Button) findViewById(R.id.btn_hire_recorder);

        tvChatList.setOnClickListener(this);
        tvPayment.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        tvHelp.setOnClickListener(this);
        ivHamburger.setOnClickListener(this);
        btnHire.setOnClickListener(this);
        tvPlacePicker.setOnClickListener(this);
        ivArrowSlidebar.setOnClickListener(this);

        ActionBarDrawerToggle drawableToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.draweropen, R.string.drawerclose);
        drawerLayout.addDrawerListener(drawableToggle);
    }


    private void showSlideBar() {
        drawerLayout.openDrawer(Gravity.START);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_chatlist:
                startActivity(new Intent(this, ChatListActivity.class));
                break;
            case R.id.tv_payment:
                startActivity(new Intent(this, PaymentActivity.class));
                break;
            case R.id.tv_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.tv_help:
                startActivity(new Intent(this, HelpActivity.class));
                break;
            case R.id.iv_hamburger:
                showSlideBar();
                break;
            case R.id.btn_hire_recorder:
                showBufferingBox();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(HomeActivity.this, BookingConfirmActivity.class));
                    }
                }, 2000);
                break;

            case R.id.tv_place_picker:

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                builder.setLatLngBounds(bounds);
                try {
                    Intent intent = builder.build(HomeActivity.this);
                    startActivityForResult(intent, Place_Picker_Request);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.iv_slidebar_backarrow:
                drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    private void showBufferingBox() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.addressbuffering, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

      /*  Dialog dialog=new Dialog(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.addressbuffering, null);
        dialog.setContentView(view);
        dialog.show();*/

    }

    //Map result Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Place_Picker_Request) {
            //make sure the request is successful
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(HomeActivity.this, data);
                String name=place.getName().toString();
                String address = place.getAddress().toString();
                String CompleteAddress= name+address;
                tvPlacePicker.setText(CompleteAddress);

                LatLng searchedLatLng = place.getLatLng();

                this.selectedLatitude = searchedLatLng.latitude;
                this.selectedLongitude = searchedLatLng.longitude;


                //Add marker to the Selected Location.
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(searchedLatLng).title(address));

                //Add circle to Google Map.

                CircleOptions circleOptions = new CircleOptions()
                        .center(searchedLatLng)
                        .radius(500)

                        // Fill color of the circle
                        // 0x represents, this is an hexadecimal code
                        // 55 represents percentage of transparency. For 100% transparency, specify 00.
                        // For 0% transparency ( ie, opaque ) , specify ff
                        // The remaining 6 characters(37aea1) specify the fill color

                        .fillColor(0x2237aea1)
                        .strokeColor(Color.BLACK);
                // .strokeWidth(SyncStateContract.Constants.STROKE_WIDTH);

                mMap.addCircle(circleOptions);

                //Zoom the camera to the Selected Location.
                CameraUpdate location = CameraUpdateFactory.newLatLngZoom(searchedLatLng, 15);
                mMap.animateCamera(location);


            }
        }
    }

    //MapActivity

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        selectedLatitude = 28.634222;
        selectedLongitude = 77.369760;

        LatLng sydney = new LatLng(selectedLatitude, selectedLongitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in India"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 11));
    }

    // Add a marker in Sydney and move the camera
    /*    LatLng sydney = new LatLng(latLng.latitude, latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in India"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 11));
    }*/
}


