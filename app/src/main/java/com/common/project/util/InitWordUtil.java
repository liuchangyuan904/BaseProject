package com.common.project.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.common.project.Constants;
import com.common.project.entity.WordEntity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InitWordUtil {
    public static void initWordBook(Handler handler, Context context, int bookId) {
        String fileName;
        if (bookId == 0) {
            fileName = "IELTSluan_2.json";
        } else if (bookId == 1) {
            fileName = "TOEFL_2.json";
        } else {
            fileName = "GMATluan_2.json";
        }
        InputStream instream = null;
        try {
            instream = context.getAssets().open(fileName);
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            String line;
            //分行读取
            while ((line = buffreader.readLine()) != null) {
                WordEntity wordEntity = new WordEntity();
                wordEntity = new Gson().fromJson(line, WordEntity.class);
                Constants.wordEntityList.add(wordEntity);
            }
            Message message=new Message();
            message.what=0;
            handler.sendMessage(message);
            instream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
