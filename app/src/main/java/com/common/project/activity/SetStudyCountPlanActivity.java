package com.common.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.R;
import com.common.project.util.SharePreferenceUtil;
import com.common.project.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.common.project.Constants.PLAN_STUDY_COUNT;

public class SetStudyCountPlanActivity extends AppCompatActivity {
    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.studyCountEditText)
    EditText studyCountEditText;
    @BindView(R.id.confirmTextView)
    TextView confirmTextView;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_study_count_plan);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, PLAN_STUDY_COUNT))) {
            studyCountEditText.setText(SharePreferenceUtil.getString(this, PLAN_STUDY_COUNT));
        }
        if (getIntent()!=null){
            type=getIntent().getStringExtra("type");
        }
        confirmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(studyCountEditText.getText().toString().trim())) {
                    ToastUtil.showToast(SetStudyCountPlanActivity.this, "请输入每天背多少个单词哦！");
                }
                SharePreferenceUtil.saveString(SetStudyCountPlanActivity.this, PLAN_STUDY_COUNT, studyCountEditText.getText().toString().trim());
                if (TextUtils.isEmpty(type)){
                    startActivity(new Intent(SetStudyCountPlanActivity.this,MainActivity.class));
                    finish();
                }else {
                    finish();
                }

            }
        });
    }
}
