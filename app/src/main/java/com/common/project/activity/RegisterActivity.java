package com.common.project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.common.project.CommonHead;
import com.common.project.R;
import com.common.project.http.HttpUtil;
import com.common.project.listener.HttpResponseListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.userEditText)
    EditText userEditText;
    @BindView(R.id.act_phone_verify_txt)
    EditText actPhoneVerifyTxt;
    @BindView(R.id.verifyPassWordEditText)
    EditText verifyPassWordEditText;
    @BindView(R.id.act_phone_commit)
    TextView actPhoneCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        actPhoneCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (actPhoneVerifyTxt.getText().toString().equals(verifyPassWordEditText.getText().toString().trim())){
                    HttpUtil.register(RegisterActivity.this, userEditText.getText().toString().trim(), verifyPassWordEditText.getText().toString().trim(), new HttpResponseListener() {
                        @Override
                        public void onSuccess(String json) {
                            finish();
                        }

                        @Override
                        public void onFailed(String json) {

                        }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this,"两次输入的密码不一致！",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
