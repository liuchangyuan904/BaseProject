package com.common.project.activity;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;

import com.common.project.CommonHead;
import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.DayStudyEntity;
import com.common.project.entity.UnKnownWordEntity;
import com.common.project.entity.XAxisCustom;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyStudyActivity extends AppCompatActivity {

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
    private List<String> xValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_study);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
//        initBarChartView();

        LinkedHashMap<String, List<Float>> chartDataMap = new LinkedHashMap<>();
        xValues = new ArrayList<>();
        xValues.add(currentDay);
        xValues.add(day1);
        xValues.add(day2);
        xValues.add(day3);
        xValues.add(day4);
        xValues.add(day5);
        xValues.add(day6);
        List<Float> yValue1 = new ArrayList<>();
        Date currentDate = new Date();
        yValue1.add(getStudyList(currentDate));
        yValue1.add(getStudyList(new Date(currentDate.getTime() - 1 * 24 * 60 * 60 * 1000)));
        yValue1.add(getStudyList(new Date(currentDate.getTime() - 2 * 24 * 60 * 60 * 1000)));
        yValue1.add(getStudyList(new Date(currentDate.getTime() - 3 * 24 * 60 * 60 * 1000)));
        yValue1.add(getStudyList(new Date(currentDate.getTime() - 4 * 24 * 60 * 60 * 1000)));
        yValue1.add(getStudyList(new Date(currentDate.getTime() - 5 * 24 * 60 * 60 * 1000)));
        yValue1.add(getStudyList(new Date(currentDate.getTime() - 6 * 24 * 60 * 60 * 1000)));


        List<Float> yValue2 = new ArrayList<>();

        yValue2.add(getReviewList(currentDate));
        yValue2.add(getReviewList(new Date(currentDate.getTime() - 1 * 24 * 60 * 60 * 1000)));
        yValue2.add(getReviewList(new Date(currentDate.getTime() - 2 * 24 * 60 * 60 * 1000)));
        yValue2.add(getReviewList(new Date(currentDate.getTime() - 3 * 24 * 60 * 60 * 1000)));
        yValue2.add(getReviewList(new Date(currentDate.getTime() - 4 * 24 * 60 * 60 * 1000)));
        yValue2.add(getReviewList(new Date(currentDate.getTime() - 5 * 24 * 60 * 60 * 1000)));
        yValue2.add(getReviewList(new Date(currentDate.getTime() - 6 * 24 * 60 * 60 * 1000)));

        List<Integer> colors = Arrays.asList(
                getResources().getColor(R.color.find_pwd_bg), getResources().getColor(R.color.orange)
        );

        chartDataMap.put("已学习", yValue1);
        chartDataMap.put("已复习", yValue2);
        showBarChart(xValues, chartDataMap, colors);
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

        Date currentDate = new Date();
        BarEntry barEntry1 = new BarEntry(0f, getStudyList(new Date(currentDate.getTime() - 6 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry2 = new BarEntry(1f, getStudyList(new Date(currentDate.getTime() - 5 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry3 = new BarEntry(2f, getStudyList(new Date(currentDate.getTime() - 4 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry4 = new BarEntry(3f, getStudyList(new Date(currentDate.getTime() - 3 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry5 = new BarEntry(4f, getStudyList(new Date(currentDate.getTime() - 2 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry6 = new BarEntry(5f, getStudyList(new Date(currentDate.getTime() - 1 * 24 * 60 * 60 * 1000)));
        BarEntry barEntry7 = new BarEntry(6f, getStudyList(currentDate));

        list.add(barEntry1);
        list.add(barEntry2);
        list.add(barEntry3);
        list.add(barEntry4);
        list.add(barEntry5);
        list.add(barEntry6);
        list.add(barEntry7);
        initX();
    }

    private float getStudyList(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd");
        String currentDay = simpleDateFormat.format(date);
        List<DayStudyEntity> dayStudyEntities = new ArrayList<>();
        List<DayStudyEntity> list = MyApplication.getInstance().getSession().getDayStudyEntityDao().queryBuilder().list();
        for (DayStudyEntity entity : list) {
            if (entity.getDayTime().equals(currentDay)) {
                dayStudyEntities.add(entity);
            }
        }
        return dayStudyEntities.size();
    }

    private void initX() {
//        barChart.getXAxis().setValueFormatter(new XAxisCustom(xAxisList));
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

    /**
     * @param xValues   X轴的值
     * @param dataLists LinkedHashMap<String, List<Float>>
     *                  key对应柱状图名字  List<Float> 对应每类柱状图的Y值
     * @param colors
     */
    public void showBarChart(final List<String> xValues, LinkedHashMap<String, List<Float>> dataLists,
                             @ColorRes List<Integer> colors) {

        List<IBarDataSet> dataSets = new ArrayList<>();
        int currentPosition = 0;//用于柱状图颜色集合的index

        for (LinkedHashMap.Entry<String, List<Float>> entry : dataLists.entrySet()) {
            String name = entry.getKey();
            List<Float> yValueList = entry.getValue();

            List<BarEntry> entries = new ArrayList<>();

            for (int i = 0; i < yValueList.size(); i++) {
                entries.add(new BarEntry(i, yValueList.get(i)));
            }
            // 每一个BarDataSet代表一类柱状图
            BarDataSet barDataSet = new BarDataSet(entries, name);
            initBarDataSet(barDataSet, colors.get(currentPosition));
            dataSets.add(barDataSet);

            currentPosition++;
        }

//        //X轴自定义值
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return xValues.get((int) value % xValues.size());
//            }
//        });
//        //右侧Y轴自定义值
//        rightAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return (int) (value) + "%";
//            }
//        });

        BarData data = new BarData(dataSets);
        //设置Y轴显示最小值，不然0下面会有空隙
        barChart.getAxisLeft().setAxisMinimum(0f);

        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);
        barChart.setTouchEnabled(false);
        //去掉中间竖线
        barChart.getXAxis().setDrawGridLines(false);
        //去掉中间横线
        barChart.getAxisLeft().setDrawGridLines(false);
        //不使用右侧Y轴
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setValueFormatter(new XAxisCustom(xValues));
        //默认显示在顶端，这是设置到底部，符合我们正常视觉
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setData(data);
    }

    /**
     * 柱状图始化设置 一个BarDataSet 代表一列柱状图
     *
     * @param barDataSet 柱状图
     * @param color      柱状图颜色
     */
    private void initBarDataSet(BarDataSet barDataSet, int color) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        //不显示柱状图顶部值
        barDataSet.setDrawValues(false);
//        barDataSet.setValueTextSize(10f);
//        barDataSet.setValueTextColor(color);
    }
    private float getReviewList(Date date) {
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
