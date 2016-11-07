package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yangxiaolong.graduateapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Messagedelete_Activity extends Activity {

    @BindView(R.id.ImageView_messet_back)
    ImageView ImageViewMessetBack;
    @BindView(R.id.button_imdelete)
    Button buttonImdelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagedelete);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ImageView_messet_back, R.id.button_imdelete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_messet_back:
                finish();
                break;
            case R.id.button_imdelete:
                finish();
                break;
        }
    }
}
