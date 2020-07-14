package com.common.project.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.common.project.R;
import com.common.project.listener.HttpResponseListener;
import com.common.project.util.ToastUtil;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

public class HttpUtil {
    private static final String TAG = "HttpUtil";
    public static void login(final Context context, String userName, String password, final HttpResponseListener httpResponseListener) {
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)){
            Toast.makeText(context,context.getResources().getString(R.string.login_tip),Toast.LENGTH_SHORT).show();
            return;
        }
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpConfig.HTTP_LOGIN, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("username",userName);
        request.add("password",password);
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: login Success "+response.get().toString());
                if (response.get().optInt("status")==0){
                    httpResponseListener.onSuccess(response.get().toString());
                }else {
                    Toast.makeText(context,response.get().optString("msg"),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onFailed: login failed");

            }

            @Override
            public void onFinish(int what) {

            }
        });

    }
    public static void register(final Context context, String userName, String password, final HttpResponseListener httpResponseListener) {
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)){
            Toast.makeText(context,context.getResources().getString(R.string.login_tip),Toast.LENGTH_SHORT).show();
            return;
        }
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpConfig.HTTP_REGISTER, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("username",userName);
        request.add("password",password);
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: register Success "+response.get().toString());
                if (response.get().optInt("status")==0){
                    httpResponseListener.onSuccess(response.get().toString());
                    Toast.makeText(context,"注册成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,response.get().optString("msg"),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onFailed: register failed");

            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    public static void getBookList(final Context context, final HttpResponseListener httpResponseListener){
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpConfig.HTTP_GET_BOOK_LIST, RequestMethod.GET);
        //3.利用队列去添加消息请求
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: "+response.get().toString());
                if (response.get().optInt("code")==200){
                    httpResponseListener.onSuccess(response.get().toString());
                }else {
                    ToastUtil.showToast(context,"服务器异常，请稍后再试！");
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
}
