package com.common.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDataActivity extends AppCompatActivity {

    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.myStudyTextView)
    TextView myStudyTextView;
    @BindView(R.id.myReviewTextView)
    TextView myReviewTextView;
    @BindView(R.id.studyProgressTextView)
    TextView studyProgressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        myStudyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyDataActivity.this,MyStudyActivity.class));
            }
        });

        myReviewTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyDataActivity.this,MyReviewActivity.class));
            }
        });

        studyProgressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyDataActivity.this,MyStudyProgressActivity.class));
            }
        });
    }
}
