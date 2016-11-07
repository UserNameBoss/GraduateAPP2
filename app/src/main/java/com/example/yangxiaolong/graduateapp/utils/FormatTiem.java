package com.example.yangxiaolong.graduateapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangxiaolong on 2016/11/3.
 */
public class FormatTiem {
    public static String formatTime(long time) {
        String min=time/(1000*60)+"";
        String sec=time%(1000*60)+"";
        if(min.length()<2){
            min="0"+time/(1000*60)+"";
        }else {
            min=time/(1000*60)+"";
        }

        if(sec.length()==4){
            sec="0"+(time%(1000*60))+"";
        }else if(sec.length()==3){
            sec="00"+(time%(1000*60))+"";
        }else if(sec.length()==2){
            sec="000"+(time%(1000*60))+"";
        }else if(sec.length()==1){
            sec="0000"+(time%(1000*60))+"";
        }
        return min+":"+sec.trim().substring(0,2);
    }

    public static String getFormatDate(long time){
        Date date=new Date(time);
        System.out.println("==========time="+time);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-dd");
        return simpleDateFormat.format(date);
    }
    public static String getFormatDateYear(long time){
        Date date=new Date(time);
        System.out.println("==========time="+time);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd");
        return simpleDateFormat.format(date);
    }

}
