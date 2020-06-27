package com.common.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.ArcCell;
import com.common.project.entity.Node;
import com.common.project.util.AllRouteDijkstra;
import com.common.project.util.Dijkstra;

import java.util.List;

public class QueryActivity extends AppCompatActivity {

    private RelativeLayout backImg;
    private TextView chooseSrcTextView;
    private TextView chooseDestTextView;
    private TextView resultTextView;
    private TextView allResultTextView;
    private long destId;
    private long srcId;
    List<ArcCell> list;
    String allResult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        backImg = findViewById(R.id.backImg);
        chooseSrcTextView = findViewById(R.id.chooseSrcTextView);
        chooseDestTextView = findViewById(R.id.chooseDestTextView);
        resultTextView = findViewById(R.id.resultTextView);
        allResultTextView = findViewById(R.id.allResultTextView);
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
        findViewById(R.id.queryBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (srcId==destId){
                    Toast.makeText(QueryActivity.this,"起点终点相同或未选择目的地",Toast.LENGTH_SHORT).show();
                    return;
                }
                Dijkstra dijkstra = new Dijkstra();
                Node startNode = dijkstra.getNode(srcId);
                Node endNode = dijkstra.getNode(destId);
                dijkstra.init(startNode, endNode);
                dijkstra.closeAddStart(startNode);
                dijkstra.computePath(startNode);
                resultTextView.setText(dijkstra.printPathInfo(endNode.getName()));

                allResult="所有路径：\n";
                AllRouteDijkstra allRouteDijkstra=new AllRouteDijkstra();
                allRouteDijkstra.setCallBack(new AllRouteDijkstra.DataCallBack() {
                    @Override
                    public void onResult(String result) {
                        allResult=allResult+result+"\n";
                        allResultTextView.setText(allResult);
                    }
                });
                allRouteDijkstra.init(QueryActivity.this);
                allRouteDijkstra. getPaths(allRouteDijkstra.getNode(srcId), null, allRouteDijkstra.getNode(srcId), allRouteDijkstra.getNode(destId));

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
