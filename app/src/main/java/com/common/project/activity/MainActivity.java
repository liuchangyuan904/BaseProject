package com.common.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.common.project.Constans;
import com.common.project.R;
import com.common.project.entity.WeatherListEntity;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.userIdTextView)
    TextView userIdTextView;
    @BindView(R.id.searchTextView)
    TextView searchTextView;
    @BindView(R.id.weatherTextView)
    TextView weatherTextView;
    @BindView(R.id.weatherDetailTextView)
    TextView weatherDetailTextView;
    @BindView(R.id.goToDetailTextView)
    TextView goToDetailTextView;
    @BindView(R.id.listView)
    ListView listView;
    WeatherListEntity weatherListEntity;
    ListViewAdapter listViewAdapter;
    @BindView(R.id.currentCityTextView)
    TextView currentCityTextView;
    String currentCity;
    String wendu,shidu,pm;
    //Test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getWeatherData();
        userIdTextView.setText("ID:" + Constans.userEntity.getData().getUsername() + "欢迎你");
        goToDetailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeatherDetailActivity.class);
                intent.putExtra("cityId", Constans.cityId);
                intent.putExtra("currentCity", currentCity);
                intent.putExtra("wendu", wendu);
                intent.putExtra("shidu", shidu);
                intent.putExtra("pm", pm);
                startActivity(intent);
            }
        });
        searchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SelectCityActivity.class));
            }
        });
    }

    private void getWeatherData() {
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpConfig.HTTP_AREAWEATHERLIST, RequestMethod.GET);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.addHeader("Authorization", Constans.userEntity.getData().getToken());
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: " + response.get().toString());
                if (response.get().optInt("status") == 0) {
                    weatherListEntity = new Gson().fromJson(response.get().toString(), WeatherListEntity.class);
                    listViewAdapter = new ListViewAdapter();
                    listView.setAdapter(listViewAdapter);
                    listViewAdapter.notifyDataSetChanged();
                    updateCurrentWeather();
                } else {
                    Toast.makeText(MainActivity.this, response.get().optString("msg"), Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();
        updateCurrentWeather();
    }

    private void updateCurrentWeather() {
        if (weatherListEntity == null) {
            return;
        }
        for (WeatherListEntity.DataBean dataBean : weatherListEntity.getData()) {
            if (dataBean.getId().toString().equals(Constans.cityId)) {
                weatherTextView.setText("实时天气：" + dataBean.getWeather().getWeather());
                currentCityTextView.setText("当前城市："+dataBean.getName());
                currentCity=dataBean.getName();
                wendu=dataBean.getWeather().getWendu()+"";
                shidu=dataBean.getWeather().getShidu()+"";
                pm=dataBean.getWeather().getPm25()+"";
                weatherDetailTextView.setText("现在温度："+wendu+"℃    湿度:"+shidu+"    PM2.5:"+pm);
            }
        }
    }

    class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return weatherListEntity.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return weatherListEntity.getData().get(i);
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
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_item, null, false);
                view.setTag(viewHolder);
                viewHolder.cityName = view.findViewById(R.id.cityName);
                viewHolder.weather = view.findViewById(R.id.weather);
                viewHolder.temp = view.findViewById(R.id.temp);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            WeatherListEntity.DataBean dataBean = weatherListEntity.getData().get(i);
            viewHolder.cityName.setText(dataBean.getName());
            viewHolder.weather.setText(dataBean.getWeather().getWeather() + "");
            viewHolder.temp.setText(dataBean.getWeather().getWendu() + "℃");
            return view;
        }

        class ViewHolder {
            TextView cityName, weather, temp;
        }
    }
}
