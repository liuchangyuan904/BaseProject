package com.common.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.project.R;
import com.common.project.http.HttpUtil;
import com.common.project.listener.HttpResponseListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.userEditText)
    EditText userEditText;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.loginCommitTextView)
    TextView loginCommitTextView;
    @BindView(R.id.registerCommitTextView)
    TextView registerCommitTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.loginCommitTextView,R.id.registerCommitTextView})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.loginCommitTextView:
                HttpUtil.login(this, userEditText.getText().toString().trim(), passwordEditText.getText().toString().trim(), new HttpResponseListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.d(TAG, "onSuccess: "+json);
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailed(String json) {
                        Log.d(TAG, "onFailed: "+json);
                    }
                });
                break;
            case R.id.registerCommitTextView:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
}