package com.common.project.entity;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

public class XAxisCustom implements IAxisValueFormatter {

    List<String> list;

    public XAxisCustom(List<String> list) {
        this.list = list;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return list.get((int) value);
    }
}
