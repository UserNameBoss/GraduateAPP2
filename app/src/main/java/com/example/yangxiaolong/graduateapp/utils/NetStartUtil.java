package com.example.yangxiaolong.graduateapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by yangxiaolong on 2016/11/3.
 */
public class NetStartUtil {
    //判断是否有网
    public static boolean getNetStart(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null){
            return networkInfo.isConnected();
        }
        return false;
    }
}
