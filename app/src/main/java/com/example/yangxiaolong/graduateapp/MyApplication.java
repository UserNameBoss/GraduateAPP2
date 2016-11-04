package com.example.yangxiaolong.graduateapp;

import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by callmenero on 2016/11/3.
 */
public class MyApplication extends Application{
    public boolean isNight=false;
    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(getApplicationContext());
    }

}
