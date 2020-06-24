package com.common.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.common.project.R;

public class UpdateActivity extends AppCompatActivity {

    private RelativeLayout backImg;
    private Button buildingManageBtn;
    private Button routeLineManagerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        backImg=findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateActivity.this.finish();
            }
        });
        buildingManageBtn=findViewById(R.id.buildingManageBtn);
        routeLineManagerBtn=findViewById(R.id.routeLineManagerBtn);
        buildingManageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateActivity.this,BuildingUpdateActivity.class));
            }
        });
        routeLineManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UpdateActivity.this,RouteLineUpdateActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);

            }
        });
    }
}
