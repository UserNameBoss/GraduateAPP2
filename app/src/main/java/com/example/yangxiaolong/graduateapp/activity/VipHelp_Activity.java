package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VipHelp_Activity extends Activity {
    @BindView(R.id.ImageView_viphelp_back)
    public ImageView ImageView_viphelp_back;
    @BindView(R.id.text_vip_backhelphome)
    public TextView text_vip_backhelphome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_help);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ImageView_viphelp_back, R.id.text_vip_backhelphome})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_viphelp_back:
                finish();
                break;
            case R.id.text_vip_backhelphome:
                this.text_vip_backhelphome.setTextColor(Color.parseColor("#ffffff"));
                this.text_vip_backhelphome.setBackgroundColor(Color.parseColor("#ff3344"));
                Intent intent=new Intent(this,CommonProblem_Avtivity.class);
                startActivity(intent);
                break;
        }
    }
}
