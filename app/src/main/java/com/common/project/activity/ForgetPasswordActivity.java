package com.common.project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.userEditText)
    EditText userEditText;
    @BindView(R.id.pinEditText)
    EditText pinEditText;
    @BindView(R.id.act_phone_verify_txt)
    EditText actPhoneVerifyTxt;
    @BindView(R.id.verifyPassWordEditText)
    EditText verifyPassWordEditText;
    @BindView(R.id.act_phone_commit)
    TextView actPhoneCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
    }
}
