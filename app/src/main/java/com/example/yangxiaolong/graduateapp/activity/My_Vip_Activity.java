package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.MyApplication;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.utils.SetShowMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class My_Vip_Activity extends Activity {
    @BindView(R.id.ImageView_vip_back)
    public ImageView ImageView_vip_back;
    @BindView(R.id.text_help)
    public TextView text_help;
    @BindView(R.id.relativeLayout_store)
    RelativeLayout relativeLayoutStore;
    @BindView(R.id.relativeLayout_comment)
    RelativeLayout relativeLayoutComment;
    @BindView(R.id.relativeLayout_cover)
    RelativeLayout relativeLayoutCover;
    @BindView(R.id.relativeLayout_marking)
    RelativeLayout relativeLayoutMarking;
    @BindView(R.id.relativeLayout_viplever)
    RelativeLayout relativeLayoutViplever;
    @BindView(R.id.relativeLayout_accelerate)
    RelativeLayout relativeLayoutAccelerate;
    @BindView(R.id.relativeLayout_doublegold)
    RelativeLayout relativeLayoutDoublegold;
    @BindView(R.id.LinearLayout_buyten)
    LinearLayout LinearLayoutBuyten;
    @BindView(R.id.LinearLayout_buythirdty)
    LinearLayout LinearLayoutBuythirdty;
    @BindView(R.id.LinearLayout_buyfifty)
    LinearLayout LinearLayoutBuyfifty;
    @BindView(R.id.LinearLayout_buyhundred)
    LinearLayout LinearLayoutBuyhundred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__vip_);
        ButterKnife.bind(this);
        SetShowMode setShowMode = new SetShowMode();
        if (((MyApplication) getApplication()).isNight) {
            setShowMode = new SetShowMode();
            setShowMode.setMode(this);
        } else {
            setShowMode.cancelMode(this);
        }
    }

    @OnClick({R.id.ImageView_vip_back, R.id.text_help, R.id.relativeLayout_store, R.id.relativeLayout_comment, R.id.relativeLayout_cover, R.id.relativeLayout_marking, R.id.relativeLayout_viplever, R.id.relativeLayout_accelerate, R.id.relativeLayout_doublegold
    ,R.id.LinearLayout_buyten, R.id.LinearLayout_buythirdty, R.id.LinearLayout_buyfifty, R.id.LinearLayout_buyhundred})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_vip_back:
                finish();
                break;
            case R.id.text_help:
                Intent intent = new Intent(this, VipHelp_Activity.class);
                startActivity(intent);
                break;
            case R.id.relativeLayout_store:
                Intent intent1 = new Intent(this, StoreDiscount_Activity.class);
                startActivity(intent1);
                break;
            case R.id.relativeLayout_comment:
                Intent intent2 = new Intent(this, ChangeColorComment_Activity.class);
                startActivity(intent2);
                break;
            case R.id.relativeLayout_cover:
                Intent intent3 = new Intent();
                break;
            case R.id.relativeLayout_marking:
                Intent intent4 = new Intent(this, ExclusiveMarking_Activity.class);
                startActivity(intent4);
                break;
            case R.id.relativeLayout_viplever:
                Intent intent5 = new Intent(this, VIPLevel_Activity.class);
                startActivity(intent5);
                break;
            case R.id.relativeLayout_accelerate:
                Intent intent6 = new Intent(this, GoldAccelerate_Activity.class);
                startActivity(intent6);
                break;
            case R.id.relativeLayout_doublegold:
                Intent intent7 = new Intent(this, DoubleGold_Activity.class);
                startActivity(intent7);
                break;
            case R.id.LinearLayout_buyten:
                Intent intent8=new Intent(this,BuyVip_Activity.class);
                startActivity(intent8);
                break;
            case R.id.LinearLayout_buythirdty:
                Intent intent9=new Intent(this,BuyVip_Activity.class);
                startActivity(intent9);
                break;
            case R.id.LinearLayout_buyfifty:
                Intent intent10=new Intent(this,BuyVip_Activity.class);
                startActivity(intent10);
                break;
            case R.id.LinearLayout_buyhundred:
               Intent intent11=new Intent(this,BuyVip_Activity.class);
                startActivity(intent11);
                break;
        }
    }

}
