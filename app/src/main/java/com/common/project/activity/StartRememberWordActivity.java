package com.common.project.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.Constants;
import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.NewWordEntity;
import com.common.project.entity.UnKnownWordEntity;
import com.common.project.entity.WordEntity;
import com.common.project.util.InitWordUtil;
import com.common.project.util.SharePreferenceUtil;
import com.common.project.view.CommonAlterDialog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.common.project.Constants.SOUND_URL;

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
    private MediaPlayer mMediaPlayer;

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
        if (TextUtils.isEmpty(SharePreferenceUtil.getString(this, Constants.HAVE_REMEMBER_WORD_COUNT))) {
            rememberWordStateTextView.setText("已背： 0个   一共：" + SharePreferenceUtil.getString(this, Constants.PLAN_STUDY_COUNT) + "个");
        }else {
            rememberWordStateTextView.setText("已背：" + SharePreferenceUtil.getString(this, Constants.HAVE_REMEMBER_WORD_COUNT) + "个   一共：" + SharePreferenceUtil.getString(this, Constants.PLAN_STUDY_COUNT) + "个");
        }
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
                UnKnownWordEntity unKnownWordEntity = new UnKnownWordEntity();
                unKnownWordEntity.setIndex(startRememberWordPosition);
                unKnownWordEntity.setWordHead(Constants.wordEntityList.get(startRememberWordPosition).getHeadWord());
                unKnownWordEntity.setWordTrans(Constants.wordEntityList.get(startRememberWordPosition).getContent().getWord().getContent().getTrans().get(0).getTranCn());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd");
                String currentDate = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                unKnownWordEntity.setDateTime(currentDate);
                if (!isExist(startRememberWordPosition)) {
                    MyApplication.getInstance().getSession().getUnKnownWordEntityDao().insert(unKnownWordEntity);
                }
                startRememberWordPosition += 1;
                SharePreferenceUtil.saveString(StartRememberWordActivity.this, Constants.START_REMEMBER_POSITION, startRememberWordPosition + "");
                Log.d(TAG, "unKnownWordTextView onClick: " + startRememberWordPosition);
                saveRememberCount();
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
                Log.d(TAG, "knownWordTextView onClick: " + startRememberWordPosition);
                saveRememberCount();
            }
        });

        voiceWordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri=SOUND_URL+currentWordEntity.getHeadWord();
                InitWordUtil.playOnlineSound(uri);
            }
        });



    }

    private boolean isExist(int startRememberWordPosition) {
        List<UnKnownWordEntity> newWordEntityList = MyApplication.getInstance().getSession().getUnKnownWordEntityDao().queryBuilder().list();
        for (UnKnownWordEntity wordEntity : newWordEntityList) {
            if (startRememberWordPosition == wordEntity.getIndex()) {
                return true;
            }
        }
        return false;
    }

    private void saveRememberCount() {
        int rememberCount;
        if (TextUtils.isEmpty(SharePreferenceUtil.getString(this, Constants.HAVE_REMEMBER_WORD_COUNT))) {
            rememberCount = 0;
        } else {
            rememberCount = Integer.valueOf(SharePreferenceUtil.getString(this, Constants.HAVE_REMEMBER_WORD_COUNT));

        }
        SharePreferenceUtil.saveString(this, Constants.HAVE_REMEMBER_WORD_COUNT, (rememberCount + 1) + "");
        rememberWordStateTextView.setText("已背：" + SharePreferenceUtil.getString(this, Constants.HAVE_REMEMBER_WORD_COUNT) + "个   一共：" + SharePreferenceUtil.getString(this, Constants.PLAN_STUDY_COUNT) + "个");
        if (TextUtils.equals(rememberCount + 1 + "", SharePreferenceUtil.getString(this, Constants.PLAN_STUDY_COUNT))) {
            showCompleteDialog();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentWordEntity = Constants.wordEntityList.get(startRememberWordPosition);
        newWordTextView.setText(currentWordEntity.getHeadWord());
        rememberWordStateTextView.setText("已背：" + SharePreferenceUtil.getString(this, Constants.HAVE_REMEMBER_WORD_COUNT) + "个   一共：" + SharePreferenceUtil.getString(this, Constants.PLAN_STUDY_COUNT) + "个");
    }

    private void showCompleteDialog() {
        final CommonAlterDialog suredialog = new CommonAlterDialog(StartRememberWordActivity.this);
        suredialog.setMessage("今日任务已完成")
                .setTitle("提示")
                .setSingle(true).setOnClickBottomListener(new CommonAlterDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {
                suredialog.dismiss();
                finish();
            }

            @Override
            public void onNegtiveClick() {
                suredialog.dismiss();
            }
        });
        suredialog.show();
    }
}
