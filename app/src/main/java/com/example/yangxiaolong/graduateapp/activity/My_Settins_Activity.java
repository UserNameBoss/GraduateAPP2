package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yangxiaolong.graduateapp.MyApplication;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.utils.SetShowMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class My_Settins_Activity extends Activity {
    @BindView(R.id.ImageView_setmore)
    public ImageView ImageView_setmore;
    @BindView(R.id.Btn_exit)
    public Button Btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__settins_);
        ButterKnife.bind(this);

        SetShowMode setShowMode = new SetShowMode();
        if (((MyApplication) getApplication()).isNight) {
            setShowMode = new SetShowMode();
            setShowMode.setMode(this);
        } else {
            setShowMode.cancelMode(this);
        }
    }


    @OnClick({R.id.ImageView_setmore, R.id.Btn_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_setmore:
                 finish();

                break;
            case R.id.Btn_exit:
               finishAffinity();
                System.exit(0);
                break;
        }
    }
}
