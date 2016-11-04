package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yangxiaolong.graduateapp.MyApplication;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.utils.SetShowMode;

public class My_Vip_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__vip_);
        SetShowMode setShowMode=new SetShowMode();
        if(((MyApplication)getApplication()).isNight){
            setShowMode=new SetShowMode();
            setShowMode.setMode(this);
        }else {
            setShowMode.cancelMode(this);
        }
    }
}
