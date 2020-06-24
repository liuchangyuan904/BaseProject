package com.common.project.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.BuildingEntity;

import java.util.List;

public class BuildingDetailActivity extends AppCompatActivity {
    TextView buildingNumView;
    TextView titleTextView;
    TextView contentTextView;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);
        findViewById(R.id.backImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuildingDetailActivity.this.finish();
            }
        });
        List<BuildingEntity> list = MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().list();
        position=getIntent().getIntExtra("value",0);
        buildingNumView=findViewById(R.id.buildingNumView);
        titleTextView=findViewById(R.id.titleTextView);
        contentTextView=findViewById(R.id.contentTextView);
        buildingNumView.setText("景点编号："+position);
        titleTextView.setText(list.get(position).getBuildingName());
        contentTextView.setText(list.get(position).getBuildingDesc());
    }
}
