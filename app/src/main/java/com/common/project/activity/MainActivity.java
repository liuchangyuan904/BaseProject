package com.common.project.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.project.Constants;
import com.common.project.R;
import com.common.project.util.InitWordUtil;
import com.common.project.util.SharePreferenceUtil;
import com.common.project.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mainImg)
    ImageView mainImg;
    @BindView(R.id.rememberWordBtn)
    Button rememberWordBtn;
    @BindView(R.id.reviewWordBtn)
    Button reviewWordBtn;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.userLayout)
    RelativeLayout userLayout;
    @BindView(R.id.shengciTextView)
    TextView shengciTextView;
    @BindView(R.id.studyRemindTextView)
    TextView studyRemindTextView;
    @BindView(R.id.studyPlanTextView)
    TextView studyPlanTextView;
    @BindView(R.id.dancibenTextView)
    TextView dancibenTextView;
    @BindView(R.id.myDataTextView)
    TextView myDataTextView;
    @BindView(R.id.settingsLayout)
    RelativeLayout settingsLayout;
    @BindView(R.id.homeLayout)
    LinearLayout homeLayout;
    @BindView(R.id.settingLayout)
    LinearLayout settingLayout;
    @BindView(R.id.bottomLayout)
    LinearLayout bottomLayout;
    @BindView(R.id.logoutTextView)
    TextView logoutTextView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    ToastUtil.showToast(MainActivity.this, "单词书加载完成！");
                    startActivity(new Intent(MainActivity.this, StartRememberWordActivity.class));
                    break;
                case 1:
                    mainLayout.setVisibility(View.GONE);
                    settingsLayout.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    //Test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.wordEntityList.clear();
                SharePreferenceUtil.saveString(MainActivity.this,Constants.HOUR,"");
                SharePreferenceUtil.saveString(MainActivity.this,Constants.MINUTE,"");
                SharePreferenceUtil.saveString(MainActivity.this,Constants.REMIND_TIME,"");
                SharePreferenceUtil.saveString(MainActivity.this,Constants.PLAN_STUDY_COUNT,"");
                SharePreferenceUtil.saveString(MainActivity.this,Constants.LOGIN,"");
                SharePreferenceUtil.saveString(MainActivity.this,Constants.CHOOSE_BOOK,"");
                SharePreferenceUtil.saveString(MainActivity.this,Constants.START_REMEMBER_POSITION,"");
                SharePreferenceUtil.saveString(MainActivity.this,Constants.DAY_TIME,"");
                SharePreferenceUtil.saveString(MainActivity.this,Constants.HAVE_REMEMBER_WORD_COUNT,"");
                SharePreferenceUtil.saveString(MainActivity.this,Constants.HAVE_REVIEW_WORD_COUNT,"");
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.wordEntityList.size() == 0) {
                    ToastUtil.showToast(MainActivity.this, "单词书正在加载哦，请稍等片刻");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            InitWordUtil.initWordBook(1,mHandler, MainActivity.this, Integer.valueOf(SharePreferenceUtil.getString(MainActivity.this, Constants.CHOOSE_BOOK)));
                        }
                    }).start();
                } else {
                    mainLayout.setVisibility(View.GONE);
                    settingsLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainLayout.setVisibility(View.VISIBLE);
                settingsLayout.setVisibility(View.GONE);
            }
        });
        studyPlanTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StudyPlanActivity.class));
            }
        });
        rememberWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.wordEntityList.size() == 0) {
                    ToastUtil.showToast(MainActivity.this, "单词书正在加载哦，请稍等片刻");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            InitWordUtil.initWordBook(0,mHandler, MainActivity.this, Integer.valueOf(SharePreferenceUtil.getString(MainActivity.this, Constants.CHOOSE_BOOK)));
                        }
                    }).start();
                } else {
                    startActivity(new Intent(MainActivity.this, StartRememberWordActivity.class));
                }

            }
        });
        reviewWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ReviewWordActivity.class));
            }
        });
        shengciTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewWordBookActivity.class));
            }
        });
        studyRemindTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StudyRemindActivity.class));
            }
        });
        dancibenTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WordBookActivity.class));
            }
        });
        myDataTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyDataActivity.class));
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        registerReceiver(broadcastReceiver, filter);
        //广播的注册，其中Intent.ACTION_TIME_CHANGED代表时间设置变化的时候会发出该广播
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM HH:mm");
                SimpleDateFormat simpleDateFormatHour = new SimpleDateFormat("HH");
                SimpleDateFormat simpleDateFormatMinute = new SimpleDateFormat("mm");
                Date currentDate = new Date();
                String currentTime = simpleDateFormat.format(currentDate);
                String hour = simpleDateFormatHour.format(currentDate);
                String minute = simpleDateFormatMinute.format(currentDate);
                Log.d("TimeTick", "onReceive: " + currentTime + " hour = " + hour + "  minute: " + minute);
                if (SharePreferenceUtil.getString(MainActivity.this, Constants.REMIND_TIME).equals("open")) {
                    if (Integer.valueOf(hour) == Integer.valueOf(SharePreferenceUtil.getString(MainActivity.this, Constants.HOUR)) &&
                            Integer.valueOf(minute) == Integer.valueOf(SharePreferenceUtil.getString(MainActivity.this, Constants.MINUTE))) {
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification notification = new NotificationCompat.Builder(MainActivity.this)
                                .setContentTitle("提示")  //设置标题
                                .setContentText("开始背单词") //设置内容
                                .setWhen(System.currentTimeMillis())  //设置时间
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setAutoCancel(true)
                                .build();
                        manager.notify(1, notification);
                    }
                }

            } else if (intent.ACTION_TIME_CHANGED.equals(intent.getAction())) {

            }
        }
    };
}
