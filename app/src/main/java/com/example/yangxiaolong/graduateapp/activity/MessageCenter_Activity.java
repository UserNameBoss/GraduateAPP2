package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.MyApplication;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.MessageDomain;
import com.example.yangxiaolong.graduateapp.utils.GETCurrentTime;
import com.example.yangxiaolong.graduateapp.utils.JsonToDomain;
import com.example.yangxiaolong.graduateapp.utils.MessageAdapter;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;
import com.example.yangxiaolong.graduateapp.utils.SetShowMode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageCenter_Activity extends Activity {
    @BindView(R.id.ImageView_mes_back)
    public ImageView ImageView_mes_back;
    @BindView(R.id.ImageView_mes_set)
    public ImageView ImageView_mes_set;
    @BindView(R.id.TextView_mes_data)
    TextView TextView_MesData;

    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private List<MessageDomain> data;
    private MessageAdapter messageAdapter;
    private PullToRefreshListView pullToRefreshListView;


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
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.message_listView);
        getResultCallback = new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                data = JsonToDomain.getListMessageDomain(message);
                messageAdapter = new MessageAdapter(MessageCenter_Activity.this, data);
                pullToRefreshListView.setAdapter(messageAdapter);
                TextView_MesData.setText(String.valueOf(data.size()));
            }
        };

        String path = "http://xb.huabao.me/?json=gender/get_messages_list";
        String pathKey = "sign=F4E63EB363F909687A1C0D3B488E93DB&timestamp=" + GETCurrentTime.getTimeMS() + "&pageSize=20&v=2140&startId=2147483647&userId=2172827";
        NetWorkListUserContent.getPostResult(path, pathKey, getResultCallback);
    }

    @OnClick({R.id.ImageView_mes_back, R.id.ImageView_mes_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_mes_back:
                finish();
                break;
            case R.id.ImageView_mes_set:
                Intent intent = new Intent(this, MessageSet_Activity.class);
                startActivity(intent);
                break;
        }
    }
}
