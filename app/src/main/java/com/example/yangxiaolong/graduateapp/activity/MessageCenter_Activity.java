package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.yangxiaolong.graduateapp.MyApplication;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.utils.SetShowMode;

public class MessageCenter_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center_);
        SetShowMode setShowMode=new SetShowMode();
        if(((MyApplication)getApplication()).isNight){
            setShowMode=new SetShowMode();
            setShowMode.setMode(this);
        }else {
            setShowMode.cancelMode(this);
        }
    }
}
