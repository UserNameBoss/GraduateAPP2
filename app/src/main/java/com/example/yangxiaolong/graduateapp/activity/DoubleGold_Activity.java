package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yangxiaolong.graduateapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoubleGold_Activity extends Activity {

    @BindView(R.id.ImageView_doublegold_back)
    ImageView ImageViewDoublegoldBack;
    @BindView(R.id.button_doublegold_dredgevip)
    Button buttonDoublegoldDredgevip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_gold);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ImageView_doublegold_back, R.id.button_doublegold_dredgevip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_doublegold_back:
                finish();
                break;
            case R.id.button_doublegold_dredgevip:
                Intent intent=new Intent(this,BuyVip_Activity.class);
                startActivity(intent);
                break;
        }
    }
}
