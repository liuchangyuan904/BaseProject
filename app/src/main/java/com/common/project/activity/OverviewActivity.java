package com.common.project.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.BuildingEntity;

import java.util.ArrayList;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {
    private RelativeLayout backImg;
    private ListView listView;
    ListViewAdapter listViewAdapter;
    List<BuildingEntity> list=new ArrayList<>();
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        if (getIntent()!=null){
            type=getIntent().getIntExtra("type",0);
        }
        backImg = findViewById(R.id.backImg);
        listView = findViewById(R.id.listView);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OverviewActivity.this.finish();
            }
        });
        listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);
        List<BuildingEntity> dataList = MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().list();
        if (dataList!=null){
            list.addAll(dataList);
        }
    }

    class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(OverviewActivity.this).inflate(R.layout.overview_item, null, false);
                view.setTag(viewHolder);
                viewHolder.titleTextView = view.findViewById(R.id.titleTextView);

            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.titleTextView.setText(list.get(i).getBuildingName());
            viewHolder.titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (type!=0){
                        setResult(Activity.RESULT_OK,new Intent().putExtra("id",list.get(i).getId()).putExtra("name",list.get(i).getBuildingName()));
                        finish();
                    }else {
                        Intent intent = new Intent(OverviewActivity.this, BuildingDetailActivity.class);
                        intent.putExtra("value", i);
                        startActivity(intent);
                    }

                }
            });
            return view;
        }

        class ViewHolder {
            TextView titleTextView;
        }
    }
}
