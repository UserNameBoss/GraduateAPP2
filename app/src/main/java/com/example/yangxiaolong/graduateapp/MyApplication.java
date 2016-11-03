package com.example.yangxiaolong.graduateapp;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by callmenero on 2016/11/3.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(getApplicationContext());
    }
}
