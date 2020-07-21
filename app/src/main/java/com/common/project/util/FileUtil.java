package com.common.project.util;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    private static final String TAG = "FileUtil";
    public static final String DEFAULT_FILENAME="gps_log";
    public static final String DEFAULT_SAVE_NAME="gps_save_log";
    public static String DATE_TO_STRING_DETAIAL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String dirPath;
    private static SimpleDateFormat simpleDateFormat;

    public static void write(String content, String filePath, String fileName) {
        try {
            makeRootDirectory(dirPath);
            File file = new File(filePath,
                    fileName);
            /**
             * 为了提高写入的效率，使用了字符流的缓冲区。
             * 创建了一个字符写入流的缓冲区对象，并和指定要被缓冲的流对象相关联。
             */
            //第二个参数意义是说是否以append方式添加内容
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

            //使用缓冲区中的方法将数据写入到缓冲区中。
//            bw.newLine();
//            bw.write(getStringDate());
            bw.write(content);
            //使用缓冲区中的方法，将数据刷新到目的地文件中去。
            bw.flush();
            //关闭缓冲区,同时关闭了FileWriter流对象
            bw.close();
            Log.d(TAG, "write: 写入成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Log.d(TAG, "getStringDate: "+dateString);
        return dateString;
    }


    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }
}
