package com.common.project.activity;

import android.content.Intent;
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
import com.common.project.dao.ArcCellDao;
import com.common.project.dao.BuildingEntityDao;
import com.common.project.entity.ArcCell;
import com.common.project.entity.BuildingEntity;

import java.util.List;

public class AddRouteActivity extends AppCompatActivity {
    private static final String TAG = "AddRouteActivity";
    TextView chooseDestTextView;
    TextView chooseSrcTextView;
    private Long srcId, destId;
    String name;
    EditText distanceEditText;
    private int functionFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);
        chooseDestTextView = findViewById(R.id.chooseDestTextView);
        chooseSrcTextView = findViewById(R.id.chooseSrcTextView);
        distanceEditText = findViewById(R.id.distanceEditText);
        findViewById(R.id.backImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent()!=null){
            functionFlag=getIntent().getIntExtra("functionFlag",0);
            if (functionFlag==1){
                srcId=getIntent().getLongExtra("srcId",0);
                destId=getIntent().getLongExtra("desId",0);
                BuildingEntity buildingEntity=MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().where(BuildingEntityDao.Properties.Id.eq(srcId)).unique();
                BuildingEntity endBuildingEntity=MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().where(BuildingEntityDao.Properties.Id.eq(destId)).unique();
                chooseSrcTextView.setText(buildingEntity.getBuildingName());
                chooseDestTextView.setText(endBuildingEntity.getBuildingName());
                distanceEditText.setText(""+getIntent().getIntExtra("adj",0));
            }
        }
        chooseDestTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddRouteActivity.this, OverviewActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 1001);
            }
        });
        chooseSrcTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddRouteActivity.this, OverviewActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 1002);
            }
        });
        findViewById(R.id.addLineBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (destId == srcId||TextUtils.isEmpty(name)){
                    Toast.makeText(AddRouteActivity.this,"起点终点相同或未选择目的地",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(distanceEditText.getText().toString().trim())){
                    Toast.makeText(AddRouteActivity.this,"请输入距离",Toast.LENGTH_SHORT).show();
                    return;
                }

                ArcCell unique1 = MyApplication.getInstances().getDaoSession().getArcCellDao().queryBuilder().where(ArcCellDao.Properties.SrcId.eq(srcId), ArcCellDao.Properties.DesId.eq(destId)).unique();
                if (unique1!=null){
                    unique1.setAdj(Integer.parseInt(distanceEditText.getText().toString().trim()));
                    MyApplication.getInstances().getDaoSession().getArcCellDao().update(unique1);
                    printDatas();
                    return;
                }
                ArcCell unique2 = MyApplication.getInstances().getDaoSession().getArcCellDao().queryBuilder().where(ArcCellDao.Properties.SrcId.eq(destId), ArcCellDao.Properties.DesId.eq(srcId)).unique();
                if (unique2!=null){
                    unique2.setAdj(Integer.parseInt(distanceEditText.getText().toString().trim()));
                    MyApplication.getInstances().getDaoSession().getArcCellDao().update(unique2);
                    printDatas();
                    return;
                }
                ArcCell arcCell=new ArcCell();
                arcCell.setAdj(Integer.parseInt(distanceEditText.getText().toString().trim()));
                arcCell.setSrcId(srcId);
                arcCell.setDesId(destId);
                MyApplication.getInstances().getDaoSession().getArcCellDao().insert(arcCell);
                printDatas();
            }
        });
    }
    private void printDatas(){
        List<ArcCell> list = MyApplication.getInstances().getDaoSession().getArcCellDao().queryBuilder().list();
        Log.d(TAG, "onClick: "+list.toString());
        Toast.makeText(AddRouteActivity.this,"路径保存成功！",Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            name = data.getStringExtra("name");
            destId = data.getLongExtra("id", 0);
            chooseDestTextView.setText(data.getStringExtra("name"));
        } else if (requestCode == 1002) {
            srcId = data.getLongExtra("id", 0);
            chooseSrcTextView.setText(data.getStringExtra("name"));
        }
    }
}
