package com.example.yangxiaolong.graduateapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
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
    public int flag;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public boolean loginFlag;


    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(getApplicationContext());
        this.sharedPreferences=getSharedPreferences("userInfo.xml",Context.MODE_PRIVATE);

        int i=Integer.valueOf(sharedPreferences.getString("flag","0"));
        if(i==1){
            flag=1;
        }
    }

    public void deleteUserData(){
       editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
        editor.putString("flag","0");
        editor.commit();
    }

}
