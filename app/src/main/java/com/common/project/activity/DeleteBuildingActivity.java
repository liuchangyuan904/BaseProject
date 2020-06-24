package com.common.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.dao.BuildingEntityDao;
import com.common.project.entity.BuildingEntity;

import java.util.ArrayList;
import java.util.List;

public class DeleteBuildingActivity extends AppCompatActivity {
    private RelativeLayout backImg;
    private ListView listView;
    ListViewAdapter listViewAdapter;
    List<BuildingEntity> list=new ArrayList<>();
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_building);
        backImg = findViewById(R.id.backImg);
        listView = findViewById(R.id.listView);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteBuildingActivity.this.finish();
            }
        });
        listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);
        if (getIntent()!=null){
            type=getIntent().getStringExtra("type");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        List<BuildingEntity> dataList = MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().list();
        if (dataList!=null){
            list.addAll(dataList);
        }
        listViewAdapter.notifyDataSetChanged();
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
                view = LayoutInflater.from(DeleteBuildingActivity.this).inflate(R.layout.overview_item, null, false);
                view.setTag(viewHolder);
                viewHolder.titleTextView = view.findViewById(R.id.titleTextView);

            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.titleTextView.setText(list.get(i).getBuildingName());
            viewHolder.titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(type)){
                        MyApplication.getInstances().getDaoSession().getBuildingEntityDao().deleteByKey(list.get(i).getId());
                        Toast.makeText(DeleteBuildingActivity.this,"删除成功！",Toast.LENGTH_SHORT).show();
                        list.clear();
                        List<BuildingEntity> dataList = MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().list();
                        if (dataList!=null){
                            list.addAll(dataList);
                            notifyDataSetChanged();
                        }
                    }else {
                        Intent intent=new Intent(DeleteBuildingActivity.this,AddBuildingActivity.class);
                        intent.putExtra("type",1);
                        intent.putExtra("id",list.get(i).getId());
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
