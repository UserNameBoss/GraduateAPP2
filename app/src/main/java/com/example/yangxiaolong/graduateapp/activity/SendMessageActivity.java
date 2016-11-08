package com.example.yangxiaolong.graduateapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.utils.GETCurrentTime;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.editText_sendMessag)
    EditText editText_SendMessag;
    @BindView(R.id.textView_sendMessag)
    TextView textView_SendMessag;
    @BindView(R.id.textView_toUserName)
    TextView textView_ToUserName;

    private Intent intent;
    private int userId;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        ButterKnife.bind(this);
        intent = getIntent();
        userId = intent.getIntExtra("userId", 0);
        userName = intent.getStringExtra("userName");
        editText_SendMessag.setOnClickListener(this);
        textView_SendMessag.setOnClickListener(this);
        textView_ToUserName.setText(userName);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.textView_sendMessag:
                String message = editText_SendMessag.getText().toString();
                if (TextUtils.isEmpty(message)) {
                    editText_SendMessag.setError("发送的内容不能为空！");
                    editText_SendMessag.setFocusable(true);
                    return;
                }
                String path = "http://xb.huabao.me/?json=gender/send_message";
                String pathKey = "sign=98084F3C28C921B9A05A5A1818059AC3&timestamp=" + GETCurrentTime.getTimeMS() + "&content=" + message + "&v=2140&fromUserId=2172827&toUserId=" + userId;
                NetWorkListUserContent.getPostResult(path, pathKey, null);
                editText_SendMessag.setText("");
                break;
        }
    }
}
