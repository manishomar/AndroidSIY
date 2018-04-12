package com.siy.controller.RecorderSignUp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.siy.R;
import com.siy.utils.CustomSwapAdapter;

public class IntroSlideRecorderActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager vpSlide;
    private Button btnNext;
    private Button btnSkip;
    private CustomSwapAdapter customSwapAdapter;
    private int[] layouts={R.layout.first_slide_recorder,R.layout.second_slide_recorder,R.layout.third_slide_recorder};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slide_recorder);
        vpSlide= (ViewPager) findViewById(R.id.vp_slide_recorder);
        btnNext= (Button) findViewById(R.id.btn_next);
        btnSkip= (Button) findViewById(R.id.btn_skip);
        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
        customSwapAdapter=new CustomSwapAdapter(layouts,this);
        vpSlide.setAdapter(customSwapAdapter);

        vpSlide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==layouts.length-1){
                    btnNext.setText("start");
                    btnSkip.setVisibility(vpSlide.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_skip:
                profileActivity();
                break;

            case R.id.btn_next:
                loadNextSlide();
                break;

        }

    }

    private void loadNextSlide() {
        int next_slide = vpSlide.getCurrentItem() + 1;
        if (next_slide < layouts.length) {
            vpSlide.setCurrentItem(next_slide);
        } else {
            profileActivity();
        }
    }

    private void profileActivity() {
        startActivity(new Intent(this,RecorderProfileActivity.class));
    }
}
