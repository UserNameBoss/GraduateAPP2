package com.example.yangxiaolong.graduateapp.utils;

import java.util.Date;

/**
 * Created by yangxiaolong on 2016/11/2.
 */
public class GETCurrentTime {

    public static long getTimeMS(){
        Date date=new Date();
        long timeMs=date.getTime();
        return timeMs;
    }
}
