package com.common.project.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
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
import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.UserInfoEntity;
import com.common.project.util.Base64Utils;
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
    @BindView(R.id.userIdTextView)
    TextView userIdTextView;
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
                SharePreferenceUtil.saveString(MainActivity.this, Constants.HOUR, "");
                SharePreferenceUtil.saveString(MainActivity.this, Constants.MINUTE, "");
                SharePreferenceUtil.saveString(MainActivity.this, Constants.REMIND_TIME, "");
                SharePreferenceUtil.saveString(MainActivity.this, Constants.PLAN_STUDY_COUNT, "");
                SharePreferenceUtil.saveString(MainActivity.this, Constants.LOGIN, "");
                SharePreferenceUtil.saveString(MainActivity.this, Constants.CHOOSE_BOOK, "");
                SharePreferenceUtil.saveString(MainActivity.this, Constants.START_REMEMBER_POSITION, "");
                SharePreferenceUtil.saveString(MainActivity.this, Constants.DAY_TIME, "");
                SharePreferenceUtil.saveString(MainActivity.this, Constants.HAVE_REMEMBER_WORD_COUNT, "");
                SharePreferenceUtil.saveString(MainActivity.this, Constants.HAVE_REVIEW_WORD_COUNT, "");
                SharePreferenceUtil.saveString(MainActivity.this, Constants.userId, "");
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                MyApplication.getInstance().getSession().getDayStudyEntityDao().deleteAll();
                MyApplication.getInstance().getSession().getUnKnownWordEntityDao().deleteAll();
                MyApplication.getInstance().getSession().getNewWordEntityDao().deleteAll();
                MyApplication.getInstance().getSession().getUserInfoEntityDao().deleteAll();
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
                            InitWordUtil.initWordBook(1, mHandler, MainActivity.this, Integer.valueOf(SharePreferenceUtil.getString(MainActivity.this, Constants.CHOOSE_BOOK)));
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
                            InitWordUtil.initWordBook(0, mHandler, MainActivity.this, Integer.valueOf(SharePreferenceUtil.getString(MainActivity.this, Constants.CHOOSE_BOOK)));
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
        UserInfoEntity userInfoEntity = MyApplication.getInstance().getSession().getUserInfoEntityDao().queryBuilder().list().get(0);
        userIdTextView.setText(Base64Utils.getFromBase64(userInfoEntity.getUserName()));
        userIdTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
                int hour = Integer.valueOf(simpleDateFormatHour.format(currentDate));
                int minute = Integer.valueOf(simpleDateFormatMinute.format(currentDate));
                String saveMinute = SharePreferenceUtil.getString(MainActivity.this, Constants.MINUTE);
                String saveHour = SharePreferenceUtil.getString(MainActivity.this, Constants.HOUR);
                int remindHour = Integer.valueOf(saveHour);
                int remindMinute = Integer.valueOf(saveMinute);
                Log.d("TimeTick", "onReceive: " + currentTime + " hour = " + hour + "  minute: " + minute);
                Log.d("TimeTick", "onReceive: saveHour = " + saveHour + " saveMinute = " + saveMinute);
                if (SharePreferenceUtil.getString(MainActivity.this, Constants.REMIND_TIME).equals("open")) {
                    if (hour == remindHour &&
                            minute == remindMinute) {
                        showNotification();
                        Log.d("TimeTick", "onReceive: Toast");
                    }
                }

            } else if (intent.ACTION_TIME_CHANGED.equals(intent.getAction())) {

            }
        }
    };
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
////        Notification notification = new NotificationCompat.Builder(MainActivity.this)
////                .setContentTitle("提示")  //设置标题
////                .setContentText("开始背单词") //设置内容
////                .setWhen(System.currentTimeMillis())  //设置时间
////                .setSmallIcon(R.mipmap.ic_launcher)
////                .setAutoCancel(false)
////                .build();
////        Log.d("TimeTick", "onReceive: Toast");
////
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            NotificationChannel channel = new NotificationChannel("5996773", "安卓10a", NotificationManager.IMPORTANCE_DEFAULT);
////            channel.enableLights(true);//是否在桌面icon右上角展示小红点
////            channel.setLightColor(Color.GREEN);//小红点颜色
////            channel.setShowBadge(false); //是否在久按桌面图标时显示此渠道的通知
////            manager.createNotificationChannel(channel);
////        }
////        manager.notify(1, notification);


//        Intent intent = new Intent(SplashActivity.this,ChatActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        Notification notification;
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder=new Notification.Builder(this,"5996773");
        }else {
            builder=new Notification.Builder(this);
        }
        //设置标题
        builder.setContentTitle("温馨提示");
        //设置内容
        builder.setContentText("背单词时间到了哦！");
        //设置状态栏显示的图标，建议图标颜色透明
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // 设置通知灯光（LIGHTS）、铃声（SOUND）、震动（VIBRATE）、（ALL 表示都设置）
        builder.setDefaults(Notification.DEFAULT_ALL);
        //灯光三个参数，颜色（argb）、亮时间（毫秒）、暗时间（毫秒）,灯光与设备有关
        builder.setLights(Color.RED, 200, 200);
        // 铃声,传入铃声的 Uri（可以本地或网上）我这没有铃声就不传了
        builder.setSound(Uri.parse("")) ;
        // 震动，传入一个 long 型数组，表示 停、震、停、震 ... （毫秒）
        builder.setVibrate(new long[]{0, 200, 200, 200, 200, 200});
        // 通知栏点击后自动消失
        builder.setAutoCancel(true);
        // 简单通知栏设置 Intent
//        builder.setContentIntent(pendingIntent);
        builder.setPriority(Notification.PRIORITY_HIGH);

        //设置下拉之后显示的图片
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("5996773", "安卓10a", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);//是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN);//小红点颜色
            channel.setShowBadge(false); //是否在久按桌面图标时显示此渠道的通知
            manager.createNotificationChannel(channel);
        }
        notification=builder.build();
        manager.notify(1,notification);


    }
}
