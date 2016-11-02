package com.example.yangxiaolong.graduateapp.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import java.io.IOException;

/**
 * Created by yangxiaolong on 2016/11/2.
 */
public class PlaySoundService extends Service{
    private MediaPlayer mediaPlayer;
    private IntentFilter intentFilter=new IntentFilter("audio");
    private LocalBroadcastManager localBroadcastManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mediaPlayer=new MediaPlayer();
        localBroadcastManager=LocalBroadcastManager.getInstance(getApplication());
        localBroadcastManager.registerReceiver(new PathBroadcastReceiver(),intentFilter);
    }


    public void getPlayMedia(String path){
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class PathBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String path=intent.getStringExtra("path");
            getPlayMedia(path);
        }
    }
}
