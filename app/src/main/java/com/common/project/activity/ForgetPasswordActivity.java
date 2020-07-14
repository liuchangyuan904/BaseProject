package com.common.project.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.common.project.R;
import com.common.project.http.HttpUtil;
import com.common.project.listener.HttpResponseListener;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }
}
