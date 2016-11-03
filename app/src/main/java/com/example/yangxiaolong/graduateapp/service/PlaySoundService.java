package com.example.yangxiaolong.graduateapp.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.ProgressBar;

import java.io.IOException;

/**
 * Created by yangxiaolong on 2016/11/2.
 */
public class PlaySoundService extends Service{
    private MediaPlayer mediaPlayer;
    private IntentFilter intentFilter=new IntentFilter("audio");
    private LocalBroadcastManager localBroadcastManager;
    private String prePath;
    private Intent intent= new Intent("Progress");

    public interface ProgressInterface{
        void setMyProgress(ProgressBar progress);
    }

    public static ProgressInterface progressInterface;

    public  void setProgressInterface(ProgressInterface progressInterface){
        this.progressInterface=progressInterface;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("===============服务已启动===========");
        this.mediaPlayer=new MediaPlayer();
        localBroadcastManager=LocalBroadcastManager.getInstance(getApplication());
        localBroadcastManager.registerReceiver(new PathBroadcastReceiver(),intentFilter);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                intent.putExtra("isStop",true);
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }


    public void getPlayMedia(String path){
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if(mediaPlayer.isPlaying()) {
                            SystemClock.sleep(500);
                            int currentTime = mediaPlayer.getCurrentPosition();
                            intent.putExtra("isStop",false);
                            intent.putExtra("progress", currentTime);
                            System.out.println("======service.progress=" + currentTime );
                            localBroadcastManager.sendBroadcast(intent);
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class PathBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean playStart=intent.getBooleanExtra("playStart",false);
            System.out.println("=======service.PlayStart="+playStart);
            String path = intent.getStringExtra("path");
            if(playStart) {
                if(prePath!=path) {
                    System.out.println("===========path=" + path);
                    getPlayMedia(path);
                    prePath=path;
                }else {
                    mediaPlayer.start();
                }
            }else{
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        }
    }
}
