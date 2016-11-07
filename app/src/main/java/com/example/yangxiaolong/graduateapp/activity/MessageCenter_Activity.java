package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.yangxiaolong.graduateapp.MyApplication;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.utils.SetShowMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageCenter_Activity extends Activity {
    @BindView(R.id.ImageView_mes_back)
    public ImageView ImageView_mes_back;
    @BindView(R.id.ImageView_mes_set)
    public ImageView ImageView_mes_set;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center_);
        ButterKnife.bind(this);
        SetShowMode setShowMode = new SetShowMode();
        if (((MyApplication) getApplication()).isNight) {
            setShowMode = new SetShowMode();
            setShowMode.setMode(this);
        } else {
            setShowMode.cancelMode(this);
        }
    }

    @OnClick({R.id.ImageView_mes_back, R.id.ImageView_mes_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_mes_back:
                finish();
                break;
            case R.id.ImageView_mes_set:
                Intent intent=new Intent(this,MessageSet_Activity.class);
                startActivity(intent);
                break;
        }
    }
}
