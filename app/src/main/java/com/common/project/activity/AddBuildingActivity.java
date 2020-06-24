package com.common.project.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.dao.BuildingEntityDao;
import com.common.project.entity.BuildingEntity;

import java.util.List;

public class AddBuildingActivity extends AppCompatActivity {
    private static final String TAG = "AddBuildingActivity";
    TextView titleTextView;
    TextView saveTextView;
    EditText buildingNameEditText;
    EditText descriptionEditText;
    private Long id;
    BuildingEntity buildingEntity;
    int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_building);
        findViewById(R.id.backImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBuildingActivity.this.finish();
            }
        });
        titleTextView=findViewById(R.id.titleTextView);
        saveTextView=findViewById(R.id.saveTextView);
        buildingNameEditText=findViewById(R.id.buildingNameEditText);
        descriptionEditText=findViewById(R.id.descriptionEditText);
        if (getIntent()!=null){
            type=getIntent().getIntExtra("type",0);
        }
        if (type==0){
            titleTextView.setText("新增");
        }else {
            id=getIntent().getLongExtra("id",-1);
            titleTextView.setText("修改");
             buildingEntity = MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().where(BuildingEntityDao.Properties.Id.eq(id)).unique();
            buildingNameEditText.setText(buildingEntity.getBuildingName());
            descriptionEditText.setText(buildingEntity.getBuildingDesc());
        }
        saveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(buildingNameEditText.getText().toString().trim())||TextUtils.isEmpty(descriptionEditText.getText().toString().trim())){
                    Toast.makeText(AddBuildingActivity.this,"请输入校园建筑物名称及简介！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (type==0){
                    BuildingEntity buildingEntity=new BuildingEntity();
                    buildingEntity.setBuildingName(buildingNameEditText.getText().toString().trim());
                    buildingEntity.setBuildingDesc(descriptionEditText.getText().toString().trim());
                    MyApplication.getInstances().getDaoSession().getBuildingEntityDao().insert(buildingEntity);
                    Toast.makeText(AddBuildingActivity.this,"校园建筑物添加成功！",Toast.LENGTH_SHORT).show();
                    List<BuildingEntity> list = MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().list();
                    Log.d(TAG, "onClick: "+list.toString());
                    AddBuildingActivity.this.finish();
                }else {
                    buildingEntity.setBuildingName(buildingNameEditText.getText().toString().trim());
                    buildingEntity.setBuildingDesc(descriptionEditText.getText().toString().trim());
                    MyApplication.getInstances().getDaoSession().getBuildingEntityDao().update(buildingEntity);
                    Toast.makeText(AddBuildingActivity.this,"校园建筑物更新成功！",Toast.LENGTH_SHORT).show();
                    AddBuildingActivity.this.finish();
                }

            }
        });
    }
}
