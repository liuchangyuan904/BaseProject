package com.common.project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.common.project.CommonHead;
import com.common.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDataActivity extends AppCompatActivity {

    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;

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
    }
}
