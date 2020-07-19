package com.common.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.project.Constants;
import com.common.project.R;
import com.common.project.http.HttpUtil;
import com.common.project.listener.HttpResponseListener;
import com.common.project.util.SharePreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.common.project.Constants.PLAN_STUDY_COUNT;


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
    @BindView(R.id.layoutContent)
    LinearLayout layoutContent;
    @BindView(R.id.forgetTextView)
    TextView forgetTextView;
    @BindView(R.id.googleTextView)
    TextView googleTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd");
        String currentDate = simpleDateFormat.format(new Date(currentTimeMillis));
        Log.d(TAG, "onCreate: " + currentDate);
        if (!currentDate.equals(SharePreferenceUtil.getString(this, Constants.DAY_TIME))) {
            SharePreferenceUtil.saveString(this, Constants.DAY_TIME, currentDate);
            SharePreferenceUtil.saveString(this, Constants.HAVE_REMEMBER_WORD_COUNT, "0");
        }
        if (SharePreferenceUtil.getString(this, Constants.LOGIN).equals("login")) {
            //未选择单词书则需要跳转到单词书页面
            if (TextUtils.isEmpty(SharePreferenceUtil.getString(this, Constants.CHOOSE_BOOK))) {
                startActivity(new Intent(this, ChooseBookActivity.class));
            } else if (TextUtils.isEmpty(SharePreferenceUtil.getString(this, PLAN_STUDY_COUNT))) {
                //未设置学习计划，则需要跳转到学习计划页面
                startActivity(new Intent(this, SetStudyCountPlanActivity.class));
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
        } else {
            layoutContent.setVisibility(View.VISIBLE);
        }
        forgetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
            }
        });
    }

    @OnClick({R.id.loginCommitTextView, R.id.registerCommitTextView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginCommitTextView:
                HttpUtil.login(this, userEditText.getText().toString().trim(), passwordEditText.getText().toString().trim(), new HttpResponseListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.d(TAG, "onSuccess: " + json);
                        SharePreferenceUtil.saveString(LoginActivity.this, Constants.LOGIN, "login");
                        startActivity(new Intent(LoginActivity.this, ChooseBookActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailed(String json) {
                        Log.d(TAG, "onFailed: " + json);
                    }
                });
                break;
            case R.id.registerCommitTextView:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}