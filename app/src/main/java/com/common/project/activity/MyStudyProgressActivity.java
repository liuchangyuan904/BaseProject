package com.common.project.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.Constants;
import com.common.project.R;
import com.common.project.util.SharePreferenceUtil;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyStudyProgressActivity extends AppCompatActivity {
    private static final String TAG = "MyStudyProgressActivity";
    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.pieChart)
    PieChart pieChart;
    @BindView(R.id.haveStudyCountTextView)
    TextView haveStudyCountTextView;
    @BindView(R.id.unStudyTextView)
    TextView unStudyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_study_progress);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });

        pieChart.setNoDataText("您还没开始学习哦，快点学习吧！");
        int studyPosition;
        if (TextUtils.isEmpty(SharePreferenceUtil.getString(this, Constants.START_REMEMBER_POSITION))) {
            studyPosition = 0;
            haveStudyCountTextView.setVisibility(View.GONE);
            unStudyTextView.setVisibility(View.GONE);
            pieChart.setNoDataText("您还没开始学习哦，快点学习吧！");
        } else {
            studyPosition = Integer.valueOf(SharePreferenceUtil.getString(this, Constants.START_REMEMBER_POSITION));
            float studyProgress = studyPosition * 1.0f / Constants.wordEntityList.size();

            haveStudyCountTextView.setText("已学习单词个数："+studyPosition+"个");
            unStudyTextView.setText("未学习单词个数："+(Constants.wordEntityList.size()-studyPosition)+"个");
            Log.d(TAG, "onCreate: 当前学习位置： " + studyPosition + "  当前词总数： " + Constants.wordEntityList.size() + " 学习进度：" + studyProgress);
            DecimalFormat df = new DecimalFormat("#.000");
            String format = df.format(studyProgress * 100);
            PieEntry pieEntry1 = new PieEntry(100f - Float.valueOf(format), "待学习");
            PieEntry pieEntry2 = new PieEntry(Float.valueOf(format), "已学习");

            List<PieEntry> list = new ArrayList<>();
            list.add(pieEntry1);
            list.add(pieEntry2);

            PieDataSet pieDataSet = new PieDataSet(list, "");

            //一般有多少项数据，就配置多少个颜色的，少的话会复用最后一个颜色，多的话无大碍
            pieDataSet.setColors(Color.parseColor("#feb64d"), Color.parseColor("#ff7c7c"));

            PieData pieData = new PieData(pieDataSet);

            pieChart.setData(pieData);
            //显示值格式化，这里使用Api,添加百分号
            pieData.setValueFormatter(new PercentFormatter());
            //设置值得颜色
            pieData.setValueTextColor(Color.parseColor("#FFFFFF"));
            //设置值得大小
            pieData.setValueTextSize(10f);

            Description description = new Description();

            description.setText("");
            //把右下边的Description label 去掉，同学也可以设置成饼图说明
            pieChart.setDescription(description);

            //去掉中心圆，此时中心圆半透明
            pieChart.setHoleRadius(0);
            //去掉半透明
            pieChart.setTransparentCircleAlpha(0);

            pieChart.setDrawEntryLabels(true);

            pieChart.invalidate();
        }


    }
}
