package com.common.project.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.common.project.CommonHead;
import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.UnKnownWordEntity;
import com.common.project.entity.XAxisCustom;
import com.common.project.entity.XBarCharEntity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyReviewActivity extends AppCompatActivity {

    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.barChart)
    BarChart barChart;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
    Date date = new Date();
    String currentDay = simpleDateFormat.format(date);
    String day1 = simpleDateFormat.format(new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000));
    String day2 = simpleDateFormat.format(new Date(date.getTime() - 2 * 24 * 60 * 60 * 1000));
    String day3 = simpleDateFormat.format(new Date(date.getTime() - 3 * 24 * 60 * 60 * 1000));
    String day4 = simpleDateFormat.format(new Date(date.getTime() - 4 * 24 * 60 * 60 * 1000));
    String day5 = simpleDateFormat.format(new Date(date.getTime() - 5 * 24 * 60 * 60 * 1000));
    String day6 = simpleDateFormat.format(new Date(date.getTime() - 6 * 24 * 60 * 60 * 1000));
    private BarDataSet dataSet;
    private List<BarEntry> list = new ArrayList<>();
    private BarData data;
    private String[] xAxisList = {day6, day5, day4, day3, day2, day1, currentDay};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        initBarChartView();
    }

    private void initBarChartView() {
        //为设置数据给BarChart的视图的提示
        barChart.setNoDataText("正在初始化...");

        initBarEntries();

        dataSet = new BarDataSet(list, "BarChat View");

        data = new BarData(dataSet);

        barChart.setData(data);

        barChart.invalidate();
    }

    private void initBarEntries() {
//        BarEntry barEntry1 = new BarEntry(0f, 10f);
//        BarEntry barEntry2 = new BarEntry(1f, 0f);
//        BarEntry barEntry3 = new BarEntry(2f, 50f);
//        BarEntry barEntry4 = new BarEntry(3f, 60f);
//        BarEntry barEntry5 = new BarEntry(4f, 70f);
//        BarEntry barEntry6 = new BarEntry(5f, 70f);
//        BarEntry barEntry7 = new BarEntry(6f, 70f);

        Date currentDate = new Date();
        BarEntry barEntry1 = new BarEntry(0f, getReviewList(new Date(currentDate.getTime() - 6 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry2 = new BarEntry(1f, getReviewList(new Date(currentDate.getTime() - 5 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry3 = new BarEntry(2f, getReviewList(new Date(currentDate.getTime() - 4 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry4 = new BarEntry(3f, getReviewList(new Date(currentDate.getTime() - 3 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry5 = new BarEntry(4f, getReviewList(new Date(currentDate.getTime() - 2 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry6 = new BarEntry(5f, getReviewList(new Date(currentDate.getTime() - 1 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry7 = new BarEntry(6f, getReviewList(currentDate));

        list.add(barEntry1);
        list.add(barEntry2);
        list.add(barEntry3);
        list.add(barEntry4);
        list.add(barEntry5);
        list.add(barEntry6);
        list.add(barEntry7);
        initX();
    }

    private void initX() {
        barChart.getXAxis().setValueFormatter(new XAxisCustom(xAxisList));
        //默认显示在顶端，这是设置到底部，符合我们正常视觉
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //去掉底部图例BarChatView 的提示，大家可以根据自己业务需求，对
        //Legend进行定制
        barChart.getLegend().setEnabled(false);
        //xAxisList的长度要和list的长度一直，否则会数组越界
        barChart.getXAxis().setLabelCount(xAxisList.length, false);

        barChart.getAxisLeft().setStartAtZero(false);

        //设置不可点击
        barChart.setTouchEnabled(false);
        //去掉中间竖线
        barChart.getXAxis().setDrawGridLines(false);
        //去掉中间横线
        barChart.getAxisLeft().setDrawGridLines(false);
        //不使用右侧Y轴
        barChart.getAxisRight().setEnabled(false);

        //去掉左侧Y轴
        barChart.getAxisLeft().setDrawAxisLine(true);
        barChart.getAxisLeft().setDrawZeroLine(false);
        //设置Y轴显示最小值，不然0下面会有空隙
        barChart.getAxisLeft().setAxisMinimum(0f);

        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);
        barChart.setVisibleXRange(7, 7);
    }

    private int getReviewList(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd");
        String currentDay = simpleDateFormat.format(date);
        String day1 = simpleDateFormat.format(new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000));
        String day3 = simpleDateFormat.format(new Date(date.getTime() - 3 * 24 * 60 * 60 * 1000));
        String day6 = simpleDateFormat.format(new Date(date.getTime() - 6 * 24 * 60 * 60 * 1000));
        String day14 = simpleDateFormat.format(new Date(date.getTime() - 14 * 24 * 60 * 60 * 1000));
        List<UnKnownWordEntity> unKnownWordEntities = new ArrayList<>();
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
        return unKnownWordEntities.size();
    }
}

