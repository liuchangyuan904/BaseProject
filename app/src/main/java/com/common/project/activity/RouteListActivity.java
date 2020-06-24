package com.common.project.activity;

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
import android.widget.Toast;

import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.dao.BuildingEntityDao;
import com.common.project.entity.ArcCell;
import com.common.project.entity.BuildingEntity;

import java.util.ArrayList;
import java.util.List;

public class RouteListActivity extends AppCompatActivity {
    private int type;
    private ListView listView;
    List<ArcCell> arcCellList = new ArrayList<>();
    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);
        listView = findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);
        findViewById(R.id.backImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent() != null) {
            type = getIntent().getIntExtra("type", 0);
            if (type == 1) {

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        arcCellList.clear();
        List<ArcCell> list = MyApplication.getInstances().getDaoSession().getArcCellDao().queryBuilder().list();
        if (list != null) {
            arcCellList.addAll(list);
            listViewAdapter.notifyDataSetChanged();
        }
    }

    class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arcCellList.size();
        }

        @Override
        public Object getItem(int i) {
            return arcCellList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(RouteListActivity.this).inflate(R.layout.route_list_item, null, false);
                view.setTag(viewHolder);
                viewHolder.startTextView = view.findViewById(R.id.startTextView);
                viewHolder.endTextView = view.findViewById(R.id.endTextView);
                viewHolder.distanceTextView = view.findViewById(R.id.distanceTextView);
                viewHolder.itemLayout = view.findViewById(R.id.itemLayout);

            } else {
                viewHolder= (ViewHolder) view.getTag();
            }
            final ArcCell arcCell=arcCellList.get(i);
            BuildingEntity buildingEntity=MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().where(BuildingEntityDao.Properties.Id.eq(arcCell.getSrcId())).unique();
            BuildingEntity endBuildingEntity=MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().where(BuildingEntityDao.Properties.Id.eq(arcCell.getDesId())).unique();
            viewHolder.startTextView.setText(buildingEntity.getBuildingName());
            viewHolder.endTextView.setText(endBuildingEntity.getBuildingName());
            viewHolder.distanceTextView.setText(arcCell.getAdj()+"米");
            viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (type!=1){
                        //删除路径
                        MyApplication.getInstances().getDaoSession().getArcCellDao().deleteByKey(arcCell.getId());
                        Toast.makeText(RouteListActivity.this,"删除成功！",Toast.LENGTH_SHORT).show();
                        arcCellList.clear();
                        List<ArcCell> dataList = MyApplication.getInstances().getDaoSession().getArcCellDao().queryBuilder().list();
                        if (dataList!=null){
                            arcCellList.addAll(dataList);
                            notifyDataSetChanged();
                        }

                    }else {
                        Intent intent=new Intent(RouteListActivity.this,AddRouteActivity.class);
                        intent.putExtra("id",arcCell.getId());
                        intent.putExtra("srcId",arcCell.getSrcId());
                        intent.putExtra("desId",arcCell.getDesId());
                        intent.putExtra("adj",arcCell.getAdj());
                        intent.putExtra("functionFlag",1);
                        startActivity(intent);

                    }
                }
            });
            return view;
        }
    }
    class ViewHolder{
        TextView startTextView;
        TextView endTextView;
        TextView distanceTextView;
        RelativeLayout itemLayout;
    }
}
