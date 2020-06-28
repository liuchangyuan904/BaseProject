package com.common.project;

import android.app.Application;

import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.NoHttp;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InitializationConfig config = InitializationConfig.newBuilder(this)
                .connectionTimeout(30 * 1000)
                .readTimeout(30 * 1000)
                .retry(10)
                .build();
        NoHttp.initialize(config);
    }
}
