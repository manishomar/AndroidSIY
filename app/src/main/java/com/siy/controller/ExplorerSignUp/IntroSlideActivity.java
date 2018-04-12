package com.siy.controller.ExplorerSignUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.siy.R;
import com.siy.utils.CustomSwapAdapter;

public class IntroSlideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private int[] layouts = {R.layout.first_slide, R.layout.second_slide, R.layout.third_slide};
    private CustomSwapAdapter customSwapAdapter;
    private Button btnNext;
    private Button btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slide);
        viewPager = (ViewPager) findViewById(R.id.vp_slide);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
        customSwapAdapter = new CustomSwapAdapter(layouts, this);
        viewPager.setAdapter(customSwapAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == layouts.length - 1) {
                    btnNext.setText("Start");
                    btnSkip.setVisibility(viewPager.INVISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                loadNextSlide();
                break;
            case R.id.btn_skip:
                profileActivity();
                break;

        }
    }

    public void profileActivity() {

        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void loadNextSlide() {
        int next_slide = viewPager.getCurrentItem() + 1;
        if (next_slide < layouts.length) {
            viewPager.setCurrentItem(next_slide);
        } else {
            profileActivity();
        }
    }
}
