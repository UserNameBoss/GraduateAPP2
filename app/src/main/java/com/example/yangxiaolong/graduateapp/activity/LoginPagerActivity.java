package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.login.LoginApi;
import com.example.yangxiaolong.graduateapp.login.OnLoginListener;
import com.example.yangxiaolong.graduateapp.login.UserInfo;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginPagerActivity extends Activity {

    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(R.id.linearLayout_logn_sina)
    LinearLayout linearLayoutLognSina;
    @BindView(R.id.linearLayout_logn_qq)
    LinearLayout linearLayoutLognQq;
    @BindView(R.id.linearLayout_logn_wechat)
    LinearLayout linearLayoutLognWechat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pager);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_back, R.id.linearLayout_logn_sina, R.id.linearLayout_logn_qq, R.id.linearLayout_logn_wechat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.linearLayout_logn_sina:
                break;
            case R.id.linearLayout_logn_qq:
                LoginApi api = new LoginApi();
                //设置登陆的平台后执行登陆的方法
                api.setPlatform("QQ");
                api.setOnLoginListener(new OnLoginListener() {
                    public boolean onLogin(String platform, HashMap<String, Object> res) {
                        // 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
                        // 此处全部给回需要注册
                        return true;
                    }

                    public boolean onRegister(UserInfo info) {
                        // 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
                        return true;
                    }
                });
                api.login(this);
                break;
            case R.id.linearLayout_logn_wechat:
                break;
        }
    }
}
