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
import com.common.project.entity.WeatherDetailEntity;
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

public class WeatherDetailActivity extends AppCompatActivity {
    private static final String TAG = "WeatherDetailActivity";
    String cityId;
    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.userIdTextView)
    TextView userIdTextView;
    @BindView(R.id.listView)
    ListView listView;
    WeatherDetailEntity weatherDetailEntity;
    ListViewAdapter listViewAdapter;
    String currentCity;
    @BindView(R.id.currentWeatherTextView)
    TextView currentWeatherTextView;
    String wendu,shidu,pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            cityId = getIntent().getStringExtra("cityId");
            currentCity = getIntent().getStringExtra("currentCity");
            wendu = getIntent().getStringExtra("wendu");
            shidu = getIntent().getStringExtra("shidu");
            pm = getIntent().getStringExtra("pm");
            currentWeatherTextView.setText("现在温度："+wendu+"℃    湿度:"+shidu+"    PM2.5:"+pm);
            userIdTextView.setText("当前所在地：" + currentCity);
        }
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        getWeatherDetail();
    }

    private void getWeatherDetail() {
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpConfig.HTTP_WEATHER_LIST, RequestMethod.GET);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("areaId", cityId);
        request.addHeader("Authorization", Constans.userEntity.getData().getToken());
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: " + response.get().toString());
                if (response.get().optInt("status") == 0) {
                    weatherDetailEntity = new Gson().fromJson(response.get().toString(), WeatherDetailEntity.class);
                    listViewAdapter = new ListViewAdapter();
                    listView.setAdapter(listViewAdapter);
                    listViewAdapter.notifyDataSetChanged();
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

    class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return weatherDetailEntity.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return weatherDetailEntity.getData().get(i);
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
                view = LayoutInflater.from(WeatherDetailActivity.this).inflate(R.layout.activity_main_item, null, false);
                view.setTag(viewHolder);
                viewHolder.cityName = view.findViewById(R.id.cityName);
                viewHolder.weather = view.findViewById(R.id.weather);
                viewHolder.temp = view.findViewById(R.id.temp);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            WeatherDetailEntity.DataBean dataBean = weatherDetailEntity.getData().get(i);
            viewHolder.cityName.setText(dataBean.getDate());
            viewHolder.weather.setText(dataBean.getWeather() + "");
            viewHolder.temp.setText(dataBean.getWendu() + "℃");
            return view;
        }

        class ViewHolder {
            TextView cityName, weather, temp;
        }
    }
}
