package com.common.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.Constants;
import com.common.project.R;
import com.common.project.entity.WordEntity;
import com.common.project.util.SharePreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartRememberWordActivity extends AppCompatActivity {
    private static final String TAG = "StartRememberWordActivi";
    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.topLayout)
    RelativeLayout topLayout;
    @BindView(R.id.wordLayout)
    RelativeLayout wordLayout;
    @BindView(R.id.knownWordTextView)
    TextView knownWordTextView;
    @BindView(R.id.unKnownWordTextView)
    TextView unKnownWordTextView;
    @BindView(R.id.rememberWordStateTextView)
    TextView rememberWordStateTextView;
    @BindView(R.id.newWordTextView)
    TextView newWordTextView;
    @BindView(R.id.voiceWordTextView)
    TextView voiceWordTextView;
    int startRememberWordPosition;
    WordEntity currentWordEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_remeber_word);
        ButterKnife.bind(this);
        if (TextUtils.isEmpty(SharePreferenceUtil.getString(this, Constants.START_REMEMBER_POSITION))) {
            startRememberWordPosition = 0;
        } else {
            startRememberWordPosition = Integer.valueOf(SharePreferenceUtil.getString(this, Constants.START_REMEMBER_POSITION));
        }
        rememberWordStateTextView.setText("已背：" + SharePreferenceUtil.getString(this, Constants.HAVE_REMEMBER_WORD_COUNT) + "个   一共：" + SharePreferenceUtil.getString(this, Constants.PLAN_STUDY_COUNT) + "个");
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        unKnownWordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartRememberWordActivity.this, WordDetailActivity.class);
                intent.putExtra("startRememberWordPosition", startRememberWordPosition);
                Log.d(TAG, "unKnownWordTextView onClick: ");
                startActivity(intent);
            }
        });
        currentWordEntity = Constants.wordEntityList.get(startRememberWordPosition);
        newWordTextView.setText(currentWordEntity.getHeadWord());
        knownWordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRememberWordPosition = startRememberWordPosition + 1;
                SharePreferenceUtil.saveString(StartRememberWordActivity.this, Constants.START_REMEMBER_POSITION, startRememberWordPosition + "");
                currentWordEntity = Constants.wordEntityList.get(startRememberWordPosition);
                newWordTextView.setText(currentWordEntity.getHeadWord());
                Log.d(TAG, "knownWordTextView onClick: "+startRememberWordPosition);
                saveRememberCount();
            }
        });
    }

    private void saveRememberCount() {
        int rememberCount = Integer.valueOf(SharePreferenceUtil.getString(this, Constants.HAVE_REMEMBER_WORD_COUNT));
        SharePreferenceUtil.saveString(this, Constants.HAVE_REMEMBER_WORD_COUNT, (rememberCount + 1) + "");
        rememberWordStateTextView.setText("已背：" + SharePreferenceUtil.getString(this, Constants.HAVE_REMEMBER_WORD_COUNT) + "个   一共：" + SharePreferenceUtil.getString(this, Constants.PLAN_STUDY_COUNT) + "个");
        }

    @Override
    protected void onResume() {
        super.onResume();
        currentWordEntity = Constants.wordEntityList.get(startRememberWordPosition);
        newWordTextView.setText(currentWordEntity.getHeadWord());
        rememberWordStateTextView.setText("已背：" + SharePreferenceUtil.getString(this, Constants.HAVE_REMEMBER_WORD_COUNT) + "个   一共：" + SharePreferenceUtil.getString(this, Constants.PLAN_STUDY_COUNT) + "个");
    }
}
