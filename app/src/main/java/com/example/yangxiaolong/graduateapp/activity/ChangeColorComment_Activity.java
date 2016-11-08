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

public class ChangeColorComment_Activity extends Activity {

    @BindView(R.id.ImageView_changecolor_back)
    ImageView ImageViewChangecolorBack;
    @BindView(R.id.button_changecolor_dredgevip)
    Button buttonChangecolorDredgevip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color_comment);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ImageView_changecolor_back, R.id.button_changecolor_dredgevip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_changecolor_back:
                finish();
                break;
            case R.id.button_changecolor_dredgevip:
                Intent intent=new Intent(this,BuyVip_Activity.class);
                startActivity(intent);
                break;
        }
    }
}
