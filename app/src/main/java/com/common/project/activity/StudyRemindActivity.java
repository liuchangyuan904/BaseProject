package com.common.project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.Constants;
import com.common.project.R;
import com.common.project.util.SharePreferenceUtil;
import com.common.project.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudyRemindActivity extends AppCompatActivity {
    private static final String TAG = "StudyRemindActivity";
    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.hourEditText)
    EditText hourEditText;
    @BindView(R.id.minuteEditText)
    EditText minuteEditText;
    @BindView(R.id.switchView)
    Switch switchView;
    @BindView(R.id.resetTextView)
    TextView resetTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_remind);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        hourEditText.setText(SharePreferenceUtil.getString(this, Constants.HOUR));
        minuteEditText.setText(SharePreferenceUtil.getString(this, Constants.MINUTE));
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, Constants.REMIND_TIME)) && SharePreferenceUtil.getString(this, Constants.REMIND_TIME).equals("open")) {
            switchView.setChecked(true);

        } else {
            switchView.setChecked(false);
        }
        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(TAG, "onCheckedChanged: " + b);
                if (b) {
                    if (TextUtils.isEmpty(hourEditText.getText().toString().trim()) || TextUtils.isEmpty(minuteEditText.getText().toString().trim())) {
                        ToastUtil.showToast(StudyRemindActivity.this, "请设置提醒时间");
                        switchView.setChecked(false);
                        return;
                    }
                    if (Integer.valueOf(hourEditText.getText().toString().trim()) > 23) {
                        ToastUtil.showToast(StudyRemindActivity.this, "请设置在0-23时区间内");
                        switchView.setChecked(false);
                        return;
                    }
                    if (Integer.valueOf(minuteEditText.getText().toString().trim()) > 59) {
                        ToastUtil.showToast(StudyRemindActivity.this, "请设置在0-59分区间内");
                    }
                    SharePreferenceUtil.saveString(StudyRemindActivity.this, Constants.HOUR, hourEditText.getText().toString().trim());
                    SharePreferenceUtil.saveString(StudyRemindActivity.this, Constants.MINUTE, minuteEditText.getText().toString().trim());
                    SharePreferenceUtil.saveString(StudyRemindActivity.this, Constants.REMIND_TIME, "open");
                } else {
                    SharePreferenceUtil.saveString(StudyRemindActivity.this, Constants.REMIND_TIME, "");
                }
            }
        });

        resetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePreferenceUtil.saveString(StudyRemindActivity.this, Constants.REMIND_TIME, "");
                SharePreferenceUtil.saveString(StudyRemindActivity.this, Constants.HOUR, "");
                SharePreferenceUtil.saveString(StudyRemindActivity.this, Constants.MINUTE, "");
                switchView.setChecked(false);
                hourEditText.setText("");
                minuteEditText.setText("");
            }
        });
    }
}
