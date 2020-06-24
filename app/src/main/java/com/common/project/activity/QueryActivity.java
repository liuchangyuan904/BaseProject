package com.common.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.ArcCell;

import java.util.List;

public class QueryActivity extends AppCompatActivity {

    private RelativeLayout backImg;
    private TextView chooseSrcTextView;
    private TextView chooseDestTextView;
    private long destId;
    private long srcId;
    List<ArcCell> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        backImg = findViewById(R.id.backImg);
        chooseSrcTextView = findViewById(R.id.chooseSrcTextView);
        chooseDestTextView = findViewById(R.id.chooseDestTextView);
        list = MyApplication.getInstances().getDaoSession().getArcCellDao().queryBuilder().list();
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryActivity.this.finish();
            }
        });
        findViewById(R.id.chooseSrcTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueryActivity.this, OverviewActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 1002);
            }
        });
        findViewById(R.id.chooseDestTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueryActivity.this, OverviewActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 1001);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            destId = data.getLongExtra("id", 0);
            chooseDestTextView.setText(data.getStringExtra("name"));
        } else if (requestCode == 1002) {
            srcId = data.getLongExtra("id", 0);
            chooseSrcTextView.setText(data.getStringExtra("name"));
        }
    }
}
