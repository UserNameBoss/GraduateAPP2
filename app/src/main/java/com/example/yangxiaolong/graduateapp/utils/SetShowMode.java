package com.example.yangxiaolong.graduateapp.utils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by callmenero on 2016/11/4.
 */
public class SetShowMode {
    private   WindowManager manager;
    private   TextView tv;
    public void setMode(Context context){
        manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                3000, 3000,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.CENTER;
        tv = new TextView(context);
        tv.setBackgroundColor(0x55000000);
        manager.addView(tv,params);
    }

    public  void cancelMode(Context context){
        if(tv!=null){
            manager.removeView(tv);
        }
    }

}
