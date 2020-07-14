package com.common.project.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {
    public static void saveString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("remember", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString(key, value);
        editor.commit();//提交修改
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("remember", Context.MODE_PRIVATE); //私有数据
        return sharedPreferences.getString(key, "");
    }
}
