package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yangxiaolong.graduateapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuyVip_Activity extends Activity {

    @BindView(R.id.ImageView_buyvip_back)
    ImageView ImageViewBuyvipBack;
    @BindView(R.id.ime_pay)
    Button imePay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_vip);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ImageView_buyvip_back, R.id.ime_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_buyvip_back:
                finish();
                break;
            case R.id.ime_pay:
                break;
        }
    }
}
