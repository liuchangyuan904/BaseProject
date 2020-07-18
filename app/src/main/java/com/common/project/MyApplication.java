package com.common.project;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.common.project.greendao.DaoMaster;
import com.common.project.greendao.DaoSession;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.NoHttp;

public class MyApplication extends Application {
    private DaoMaster.DevOpenHelper dbHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        InitializationConfig config = InitializationConfig.newBuilder(this)
                .connectionTimeout(30 * 1000)
                .readTimeout(30 * 1000)
                .retry(10)
                .build();
        NoHttp.initialize(config);
        init();
        initDatabass();
    }
    public static MyApplication getInstance(){
        return instance;
    }
    private void initDatabass() {
        //这里之后会修改，关于升级数据库
        dbHelper = new DaoMaster.DevOpenHelper(this, "word-db", null);
        db = dbHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getSession(){
        return mDaoSession;
    }
    public SQLiteDatabase getDb(){
        return db;
    }
    private void init() {
        instance = this;
    }
}