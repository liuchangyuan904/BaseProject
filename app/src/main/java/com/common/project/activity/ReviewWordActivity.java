package com.common.project.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.Constants;
import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.UnKnownWordEntity;
import com.common.project.util.InitWordUtil;
import com.common.project.util.SharePreferenceUtil;
import com.common.project.view.CommonAlterDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.common.project.Constants.HAVE_REVIEW_WORD_COUNT;
import static com.common.project.Constants.SOUND_URL;

public class ReviewWordActivity extends AppCompatActivity {
    private static final String TAG = "ReviewWordActivity";
    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.rememberWordStateTextView)
    TextView rememberWordStateTextView;
    @BindView(R.id.topLayout)
    RelativeLayout topLayout;
    @BindView(R.id.newWordTextView)
    TextView newWordTextView;
    @BindView(R.id.voiceWordTextView)
    TextView voiceWordTextView;
    @BindView(R.id.wordLayout)
    RelativeLayout wordLayout;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    String[] answers = new String[4];
    @BindView(R.id.aRadioBtn)
    RadioButton aRadioBtn;
    @BindView(R.id.bRadioBtn)
    RadioButton bRadioBtn;
    @BindView(R.id.cRadioBtn)
    RadioButton cRadioBtn;
    @BindView(R.id.dRadioBtn)
    RadioButton dRadioBtn;
    private int wordPosition;
    List<UnKnownWordEntity> unKnownWordEntities = new ArrayList<>();
    UnKnownWordEntity currentUnKnownWordEntity;
    private int have_review_word_count;
    int selectIndex;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_word);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        getReviewList();
        if (TextUtils.isEmpty(SharePreferenceUtil.getString(this, HAVE_REVIEW_WORD_COUNT))) {
            have_review_word_count = 0;
        } else {
            have_review_word_count = Integer.valueOf(SharePreferenceUtil.getString(this, HAVE_REVIEW_WORD_COUNT));
        }
        rememberWordStateTextView.setText("已背：" + have_review_word_count + "个   一共：" + unKnownWordEntities.size() + "个");

        if (unKnownWordEntities.size() > 0 && have_review_word_count < unKnownWordEntities.size()) {
            newWordTextView.setText(unKnownWordEntities.get(have_review_word_count).getWordHead());
            currentUnKnownWordEntity = unKnownWordEntities.get(have_review_word_count);
            if (Constants.wordEntityList.size() > 0) {
                makeChooseData();
                aRadioBtn.setText(answers[0]);
                bRadioBtn.setText(answers[1]);
                cRadioBtn.setText(answers[2]);
                dRadioBtn.setText(answers[3]);
            }
        } else {
            final CommonAlterDialog suredialog = new CommonAlterDialog(ReviewWordActivity.this);
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
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d(TAG, "onCheckedChanged: " + i);
                int id = radioGroup.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.aRadioBtn:
                        if (aRadioBtn.getText().equals(answers[selectIndex])) {
                            aRadioBtn.setBackground(getDrawable(R.drawable.green_btn_bg));
                            bRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            cRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            dRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            saveReviewIndex();
                        } else {
                            goToWordDetail();
                        }
                        break;
                    case R.id.bRadioBtn:
                        if (bRadioBtn.getText().equals(answers[selectIndex])) {
                            aRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            bRadioBtn.setBackground(getDrawable(R.drawable.green_btn_bg));
                            cRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            dRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            saveReviewIndex();
                        } else {
                            goToWordDetail();
                        }
                        break;
                    case R.id.cRadioBtn:
                        if (cRadioBtn.getText().equals(answers[selectIndex])) {
                            aRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            bRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            cRadioBtn.setBackground(getDrawable(R.drawable.green_btn_bg));
                            dRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            saveReviewIndex();
                        } else {
                            goToWordDetail();
                        }
                        break;
                    case R.id.dRadioBtn:
                        if (dRadioBtn.getText().equals(answers[selectIndex])) {
                            aRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            bRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            cRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                            dRadioBtn.setBackground(getDrawable(R.drawable.green_btn_bg));
                            saveReviewIndex();
                        } else {
                            goToWordDetail();
                        }
                        break;
                }
            }
        });


        voiceWordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = SOUND_URL + currentUnKnownWordEntity.getWordHead();
                InitWordUtil.playOnlineSound(uri);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void saveReviewIndex() {
        if (have_review_word_count == unKnownWordEntities.size()-1) {
            final CommonAlterDialog suredialog = new CommonAlterDialog(ReviewWordActivity.this);
            suredialog.setMessage("今日任务已完成")
                    .setTitle("提示")
                    .setSingle(true).setOnClickBottomListener(new CommonAlterDialog.OnClickBottomListener() {
                @Override
                public void onPositiveClick() {
                    have_review_word_count += 1;
                    SharePreferenceUtil.saveString(ReviewWordActivity.this, Constants.HAVE_REVIEW_WORD_COUNT, have_review_word_count + "");
                    suredialog.dismiss();
                    finish();
                }

                @Override
                public void onNegtiveClick() {
                    suredialog.dismiss();
                }
            });
            suredialog.show();
            return;
        }
        have_review_word_count += 1;
        SharePreferenceUtil.saveString(this, Constants.HAVE_REVIEW_WORD_COUNT, have_review_word_count + "");
        rememberWordStateTextView.setText("已背：" + have_review_word_count + "个   一共：" + unKnownWordEntities.size() + "个");
        if (have_review_word_count < unKnownWordEntities.size()) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    newWordTextView.setText(unKnownWordEntities.get(have_review_word_count).getWordHead());
                    currentUnKnownWordEntity = unKnownWordEntities.get(have_review_word_count);
                    if (Constants.wordEntityList.size() > 0) {
                        makeChooseData();
                        aRadioBtn.setText(answers[0]);
                        bRadioBtn.setText(answers[1]);
                        cRadioBtn.setText(answers[2]);
                        dRadioBtn.setText(answers[3]);
                        aRadioBtn.setChecked(false);
                        bRadioBtn.setChecked(false);
                        cRadioBtn.setChecked(false);
                        dRadioBtn.setChecked(false);
                        aRadioBtn.setBackground(getDrawable(R.drawable.grey_btn_bg));
                        bRadioBtn.setBackground(getDrawable(R.drawable.grey_btn_bg));
                        cRadioBtn.setBackground(getDrawable(R.drawable.grey_btn_bg));
                        dRadioBtn.setBackground(getDrawable(R.drawable.grey_btn_bg));
                    }
                }
            },1500);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void goToWordDetail() {
        switch (selectIndex) {
            case 0:
                aRadioBtn.setBackground(getDrawable(R.drawable.green_btn_bg));
                bRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                cRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                dRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                break;
            case 1:
                aRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                bRadioBtn.setBackground(getDrawable(R.drawable.green_btn_bg));
                cRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                dRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                break;
            case 2:
                aRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                bRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                cRadioBtn.setBackground(getDrawable(R.drawable.green_btn_bg));
                dRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                break;
            case 3:
                aRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                bRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                cRadioBtn.setBackground(getDrawable(R.drawable.red_error_btn_bg));
                dRadioBtn.setBackground(getDrawable(R.drawable.green_btn_bg));
                break;
        }
        Intent intent = new Intent(ReviewWordActivity.this, WordDetailActivity.class);
        intent.putExtra("startRememberWordPosition", currentUnKnownWordEntity.getIndex());
        startActivity(intent);
    }

    private void makeChooseData() {
        Random random = new Random();
        selectIndex = random.nextInt(4);
        answers[selectIndex] = currentUnKnownWordEntity.getWordTrans();
        for (int i = 0; i < 4; i++) {
            if (i != selectIndex) {
                answers[i] = getRandomWordTrans();
            }
        }

    }

    private String getRandomWordTrans() {
        String trans = "";
        Random random = new Random();
        int index = random.nextInt(Constants.wordEntityList.size());
        if (index == currentUnKnownWordEntity.getIndex()) {
            trans = getRandomWordTrans();
        } else {
            trans = Constants.wordEntityList.get(index).getContent().getWord().getContent().getTrans().get(0).getTranCn();
        }
        return trans;
    }

    private void getReviewList() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd");
        Date date = new Date();
        String currentDay = simpleDateFormat.format(date);
        String day1 = simpleDateFormat.format(new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000));
        String day3 = simpleDateFormat.format(new Date(date.getTime() - 3 * 24 * 60 * 60 * 1000));
        String day6 = simpleDateFormat.format(new Date(date.getTime() - 6 * 24 * 60 * 60 * 1000));
        String day14 = simpleDateFormat.format(new Date(date.getTime() - 14 * 24 * 60 * 60 * 1000));
        List<UnKnownWordEntity> list = MyApplication.getInstance().getSession().getUnKnownWordEntityDao().queryBuilder().list();
        for (UnKnownWordEntity unKnownWordEntity : list) {
            if (unKnownWordEntity.getDateTime().equals(currentDay) ||
                    unKnownWordEntity.getDateTime().equals(day1) ||
                    unKnownWordEntity.getDateTime().equals(day3) ||
                    unKnownWordEntity.getDateTime().equals(day6) ||
                    unKnownWordEntity.getDateTime().equals(day14)) {
                unKnownWordEntities.add(unKnownWordEntity);
            }
        }
    }
}
