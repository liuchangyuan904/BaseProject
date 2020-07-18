package com.common.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    ToastUtil.showToast(MainActivity.this,"单词书加载完成！");
                    startActivity(new Intent(MainActivity.this,StartRememberWordActivity.class));
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

        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainLayout.setVisibility(View.GONE);
                settingsLayout.setVisibility(View.VISIBLE);
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
                startActivity(new Intent(MainActivity.this,StudyPlanActivity.class));
            }
        });
        rememberWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.wordEntityList.size()==0){
                    ToastUtil.showToast(MainActivity.this,"单词书正在加载哦，请稍等片刻");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            InitWordUtil.initWordBook(mHandler,MainActivity.this,Integer.valueOf(SharePreferenceUtil.getString(MainActivity.this,Constants.CHOOSE_BOOK)));
                        }
                    }).start();
                }else {
                    startActivity(new Intent(MainActivity.this,StartRememberWordActivity.class));
                }

            }
        });
        reviewWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ReviewWordActivity.class));
            }
        });
        shengciTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NewWordBookActivity.class));
            }
        });
        studyRemindTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,StudyRemindActivity.class));
            }
        });
        dancibenTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,WordBookActivity.class));
            }
        });
        myDataTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MyDataActivity.class));
            }
        });
    }
}
