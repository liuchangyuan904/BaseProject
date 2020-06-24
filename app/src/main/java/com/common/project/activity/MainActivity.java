package com.common.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.MGraph;

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
        MGraph mGraph=MyApplication.getInstances().getmGraph();
        String[] sights = getResources().getStringArray(R.array.sights);
        String[] sightsDescription = getResources().getStringArray(R.array.sightsDescription);
        for (int i = 0; i <9; i++) {
            mGraph.getVex()[i].setSight(sights[i]==null?"":sights[i]);
            mGraph.getVex()[i].setDescription(sightsDescription[i]==null?"":sightsDescription[i]);
        }
        for (int i = 0; i <20; i++) {
            for (int j = 0; j < 20; j++) {
                mGraph.getArcs()[i][j].setAdj(MyApplication.MAX);;
            }
        }
        mGraph.getArcs()[0][1].setAdj(50);
        mGraph.getArcs()[1][0].setAdj(50);

        mGraph.getArcs()[1][3].setAdj(70);
        mGraph.getArcs()[3][1].setAdj(70);

        mGraph.getArcs()[0][6].setAdj(250);
        mGraph.getArcs()[6][0].setAdj(250);

        mGraph.getArcs()[1][4].setAdj(80);
        mGraph.getArcs()[4][1].setAdj(80);

        mGraph.getArcs()[2][4].setAdj(100);
        mGraph.getArcs()[4][2].setAdj(100);

        mGraph.getArcs()[3][5].setAdj(90);
        mGraph.getArcs()[5][3].setAdj(90);

        mGraph.getArcs()[5][2].setAdj(100);
        mGraph.getArcs()[2][5].setAdj(100);

        mGraph.getArcs()[4][6].setAdj(75);
        mGraph.getArcs()[6][4].setAdj(75);

        mGraph.getArcs()[7][4].setAdj(300);
        mGraph.getArcs()[4][7].setAdj(300);

        mGraph.getArcs()[2][7].setAdj(400);
        mGraph.getArcs()[7][2].setAdj(400);

        mGraph.getArcs()[7][8].setAdj(40);
        mGraph.getArcs()[8][7].setAdj(40);
        
    }
}
