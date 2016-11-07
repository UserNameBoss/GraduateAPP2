package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.yangxiaolong.graduateapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageSet_Activity extends Activity {

    @BindView(R.id.ImageView_messet_back)
    ImageView ImageViewMessetBack;
    @BindView(R.id.ImageView_messet_delete)
    ImageView ImageViewMessetDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_set);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ImageView_messet_back, R.id.ImageView_messet_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_messet_back:
                finish();
                break;
            case R.id.ImageView_messet_delete:
                Intent intent=new Intent(this,Messagedelete_Activity.class);
                startActivity(intent);
                break;
        }
    }
}
