package com.common.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mainImg)
    ImageView mainImg;
    @BindView(R.id.rememberWordBtn)
    Button rememberWordBtn;
    @BindView(R.id.reviewWordBtn)
    Button reviewWordBtn;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.userLayout)
    RelativeLayout userLayout;
    @BindView(R.id.shengciTextView)
    TextView shengciTextView;
    @BindView(R.id.studyRemindTextView)
    TextView studyRemindTextView;
    @BindView(R.id.studyPlanTextView)
    TextView studyPlanTextView;
    @BindView(R.id.dancibenTextView)
    TextView dancibenTextView;
    @BindView(R.id.myDataTextView)
    TextView myDataTextView;
    @BindView(R.id.settingsLayout)
    RelativeLayout settingsLayout;
    @BindView(R.id.homeLayout)
    LinearLayout homeLayout;
    @BindView(R.id.settingLayout)
    LinearLayout settingLayout;
    @BindView(R.id.bottomLayout)
    LinearLayout bottomLayout;

    //Test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainLayout.setVisibility(View.GONE);
                settingsLayout.setVisibility(View.VISIBLE);
            }
        });
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainLayout.setVisibility(View.VISIBLE);
                settingsLayout.setVisibility(View.GONE);
            }
        });
        studyPlanTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,StudyPlanActivity.class));
            }
        });
        rememberWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,StartRememberWordActivity.class));
            }
        });
        reviewWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ReviewWordActivity.class));
            }
        });
        shengciTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NewWordBookActivity.class));
            }
        });
        studyRemindTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,StudyRemindActivity.class));
            }
        });
        dancibenTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,WordBookActivity.class));
            }
        });
        myDataTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MyDataActivity.class));
            }
        });
    }
}
