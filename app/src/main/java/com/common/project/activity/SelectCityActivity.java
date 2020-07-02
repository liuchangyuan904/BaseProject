package com.common.project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.Constans;
import com.common.project.R;
import com.common.project.entity.CityListEntity;
import com.common.project.entity.ProvinceListEntity;
import com.common.project.http.HttpConfig;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCityActivity extends AppCompatActivity {
    private static final String TAG = "SelectCityActivity";
    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.provinceListView)
    ListView provinceListView;
    @BindView(R.id.cityListView)
    ListView cityListView;
    ProvinceListEntity provinceListEntity;
    ProvinceListViewAdapter provinceListViewAdapter;
    CityListEntity cityListEntity;
    CityListViewAdapter cityListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        getProvinceList();
    }

    private void getProvinceList() {
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpConfig.HTTP_PROVINCES, RequestMethod.GET);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.addHeader("Authorization",Constans.userEntity.getData().getToken());
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: "+response.get().toString());
                if (response.get().optInt("status")==0){
                    provinceListEntity=new Gson().fromJson(response.get().toString(),ProvinceListEntity.class);
                    provinceListViewAdapter=new ProvinceListViewAdapter();
                    provinceListView.setAdapter(provinceListViewAdapter);
                    provinceListViewAdapter.notifyDataSetChanged();
                    getCityList(provinceListEntity.getData().get(0).getId());
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void getCityList(String parentId) {
        if (provinceListEntity.getData().size()==0){
            return;
        }
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpConfig.HTTP_AREA_SEARCH, RequestMethod.GET);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("parentId",parentId);
        request.addHeader("Authorization",Constans.userEntity.getData().getToken());
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                if (response.get().optInt("status")==0){
                    cityListEntity=new Gson().fromJson(response.get().toString(),CityListEntity.class);
                    cityListAdapter=new CityListViewAdapter();
                    cityListView.setAdapter(cityListAdapter);
                    cityListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    class ProvinceListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return provinceListEntity.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return provinceListEntity.getData().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view==null){
                viewHolder=new ViewHolder();
                view=LayoutInflater.from(SelectCityActivity.this).inflate(R.layout.activity_select_province_item,null,false);
                view.setTag(viewHolder);
                viewHolder.cityName=view.findViewById(R.id.cityName);
            }else {
                viewHolder= (ViewHolder) view.getTag();
            }
            final ProvinceListEntity.DataBean dataBean = provinceListEntity.getData().get(i);
            viewHolder.cityName.setText(dataBean.getName());
            viewHolder.cityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getCityList(dataBean.getId());
                }
            });
            return view;
        }
        class ViewHolder{
            TextView cityName;
        }
    }
    class CityListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cityListEntity.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return cityListEntity.getData().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view==null){
                viewHolder=new ViewHolder();
                view=LayoutInflater.from(SelectCityActivity.this).inflate(R.layout.activity_select_province_item,null,false);
                view.setTag(viewHolder);
                viewHolder.cityName=view.findViewById(R.id.cityName);
            }else {
                viewHolder= (ViewHolder) view.getTag();
            }
            final CityListEntity.DataBean dataBean = cityListEntity.getData().get(i);
            viewHolder.cityName.setText(dataBean.getName());
            viewHolder.cityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constans.cityId=dataBean.getId();
                    finish();
                }
            });
            return view;
        }
        class ViewHolder{
            TextView cityName;
        }
    }
}
