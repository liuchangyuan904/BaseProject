package com.common.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.common.project.R;

public class MainActivity extends AppCompatActivity {
    private Button overviewBtn;
    private Button queryBtn;
    private Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overviewBtn=findViewById(R.id.overviewBtn);
        queryBtn=findViewById(R.id.queryBtn);
        updateBtn=findViewById(R.id.updateBtn);
        overviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,OverviewActivity.class));
            }
        });
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,QueryActivity.class));
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UpdateActivity.class));
            }
        });
    }
}
