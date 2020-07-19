package com.common.project.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.common.project.Constants;
import com.common.project.entity.WordEntity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InitWordUtil {
    private static final String TAG = "InitWordUtil";
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

    /**
     * 播放发音
     * @param soundUrlDict
     */
    public static void playOnlineSound(String soundUrlDict) {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(soundUrlDict);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (mp != null) {
                        mp.release();
                    }
                    Log.d(TAG, "onCompletion: play sound.");
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    Log.d(TAG, "Play online sound onError: " + i + ", " + i1);
                    return false;
                }
            });
        } catch (IOException e1) {
            Log.e(TAG, "url: ", e1);
        }
    }

}
