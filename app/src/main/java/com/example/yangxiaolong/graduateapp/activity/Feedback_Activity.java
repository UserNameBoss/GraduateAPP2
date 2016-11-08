package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yangxiaolong.graduateapp.MyApplication;
import com.example.yangxiaolong.graduateapp.MyView.MyEditext;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.utils.GETCurrentTime;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;
import com.example.yangxiaolong.graduateapp.utils.SetShowMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Feedback_Activity extends Activity implements View.OnClickListener{

    @BindView(R.id.ImageView_collect_back)
    ImageView ImageView_CollectBack;
    @BindView(R.id.MyEditext_feedback)
    MyEditext MyEditext_Feedback;
    @BindView(R.id.MyEditext_connect)
    MyEditext MyEditext_Connect;
    @BindView(R.id.button_submit)
    Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        SetShowMode setShowMode = new SetShowMode();
        if (((MyApplication) getApplication()).isNight) {
            setShowMode = new SetShowMode();
            setShowMode.setMode(this);
        } else {
            setShowMode.cancelMode(this);
        }

        button_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.button_submit:
                String content=MyEditext_Connect.getText().toString();
                if(TextUtils.isEmpty(content)){
                    MyEditext_Connect.setError("反馈内容不能为空！");
                    MyEditext_Connect.setFocusable(true);
                    return;
                }
                String path="http://xb.huabao.me/?json=gender/send_message";
                String pathKey="sign=DD0A58CD0DC82FF31F4035290F86E894&timestamp="+ GETCurrentTime.getTimeMS()+"&content=%E6%84%8F%E8%A7%81%E5%8F%8D%E9%A6%88%EF%BC%9A"+content+"+%2C"+MyEditext_Feedback.getText().toString()+"&v=2140&fromUserId=2172827&toUserId=149297";
                NetWorkListUserContent.getPostResult(path,pathKey,null);
                Intent intent=new Intent(this,SendMessageActivity.class);
                intent.putExtra("userId","149297");
                intent.putExtra("userName","霸姐");
                startActivity(intent);
                finish();
                break;
        }
    }
}
