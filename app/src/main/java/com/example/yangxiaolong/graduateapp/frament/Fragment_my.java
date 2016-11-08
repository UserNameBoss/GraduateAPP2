package com.example.yangxiaolong.graduateapp.frament;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangxiaolong.graduateapp.MyApplication;
import com.example.yangxiaolong.graduateapp.MyView.CircleImageView;
import com.example.yangxiaolong.graduateapp.MyView.ObservableScrollView;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.activity.LoginPagerActivity;
import com.example.yangxiaolong.graduateapp.activity.My_Friends_Activity;
import com.example.yangxiaolong.graduateapp.activity.My_Gold_Activity;
import com.example.yangxiaolong.graduateapp.activity.My_Settins_Activity;
import com.example.yangxiaolong.graduateapp.activity.My_Task_Activity;
import com.example.yangxiaolong.graduateapp.activity.My_Vip_Activity;
import com.example.yangxiaolong.graduateapp.activity.My_Yellow_Activity;
import com.example.yangxiaolong.graduateapp.utils.SetShowMode;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_my extends Fragment {
    private final int REQUEST_CODE = 100;
    private final int RESPONSE_CODE = 200;

    @BindView(R.id.circleImageView_userImg)
    CircleImageView circleImageViewUserImg;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.textView_userFansNumber)
    TextView textViewUserFansNumber;
    @BindView(R.id.textView_userFocusNumber)
    TextView textViewUserFocusNumber;
    @BindView(R.id.textView_userContributeNumber)
    TextView textViewUserContributeNumber;
    @BindView(R.id.linearLayout_user_bg)
    RelativeLayout linearLayoutUserBg;
    @BindView(R.id.text_gold)
    TextView textGold;
    @BindView(R.id.textView_isvip)
    TextView textViewIsvip;
    @BindView(R.id.textView_workTask)
    TextView textViewWorkTask;
    @BindView(R.id.textView_jobNumber)
    TextView textViewJobNumber;
    @BindView(R.id.login_userName)
    TextView loginUserName;
    @BindView(R.id.text_login_userID)
    TextView textLoginUserID;
    @BindView(R.id.linearLayout_login_userID)
    LinearLayout linearLayoutLoginUserID;
    @BindView(R.id.textView_login_edit)
    TextView textViewLoginEdit;
    @BindView(R.id.relativeLayout_one)
    RelativeLayout relativeLayoutOne;
    @BindView(R.id.relativeLayout_two)
    RelativeLayout relativeLayoutTwo;
    @BindView(R.id.relativeLayout_three)
    RelativeLayout relativeLayoutThree;
    @BindView(R.id.relativeLayout_four)
    RelativeLayout relativeLayoutFour;
    @BindView(R.id.relativeLayout_five)
    RelativeLayout relativeLayoutFive;
    @BindView(R.id.relativeLayout_six)
    RelativeLayout relativeLayoutSix;
    @BindView(R.id.relativeLayout_seven)
    RelativeLayout relativeLayoutSeven;
    @BindView(R.id.relativeLayout_eight)
    RelativeLayout relativeLayoutEight;
    @BindView(R.id.linearLayout_userInformation)
    LinearLayout linearLayoutUserInformation;
    @BindView(R.id.textView_bahao)
    TextView textView_bahao;
    @BindView(R.id.img_user_gender)
    ImageView imgUserGender;
    @BindView(R.id.img_gold)
    ImageView imgGold;
    @BindView(R.id.img_vip)
    ImageView imgVip;
    @BindView(R.id.text_vip)
    TextView textVip;
    @BindView(R.id.img_task)
    ImageView imgTask;
    @BindView(R.id.text_task)
    TextView textTask;
    @BindView(R.id.img_gameList)
    ImageView imgGameList;
    @BindView(R.id.text_gameList)
    TextView textGameList;
    @BindView(R.id.img_lv)
    ImageView imgLv;
    @BindView(R.id.text_jokeLv)
    TextView textJokeLv;
    @BindView(R.id.img_nightMode)
    ImageView imgNightMode;
    @BindView(R.id.text_nightMode)
    TextView textNightMode;
    @BindView(R.id.img_changeStyle)
    ImageView imgChangeStyle;
    @BindView(R.id.img_shareFriends)
    ImageView imgShareFriends;
    @BindView(R.id.text_shareFriends)
    TextView textShareFriends;
    @BindView(R.id.img_moreSettings)
    ImageView imgMoreSettings;
    @BindView(R.id.text_moreSettings)
    TextView textMoreSettings;
    @BindView(R.id.scrollView_my)
    ObservableScrollView scrollViewMy;
    @BindView(R.id.imageView_login_add)
    ImageView imageViewLoginAdd;
    @BindView(R.id.linearLayout_my_titleBar)
    LinearLayout linearLayoutMyTitleBar;


    private ObservableScrollView scrollView_my;
    private LinearLayout linearLayout;
    private ImageView imageView_changeStyle;
    private boolean isChecked;


    private SetShowMode setShowMode;
    private int flag;

    private SharedPreferences sharedPreferences;

    private String name, gender, userIconPath;

    public Fragment_my() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_my, container, false);
        //使用黄油小刀
        ButterKnife.bind(this, view);

        this.scrollView_my = (ObservableScrollView) view.findViewById(R.id.scrollView_my);
        this.linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_my_titleBar);
        this.imageView_changeStyle = (ImageView) view.findViewById(R.id.img_changeStyle);
        this.imgStatu();
        if (setShowMode == null) {
            setShowMode = new SetShowMode();
        }

        this.flag=((MyApplication)this.getActivity().getApplication()).flag;

        if(flag==1){
            this.loginAndShow();
        }

        System.out.println("执行了onCreateView");
        this.imageView_changeStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isChecked) {
                    imageView_changeStyle.setImageResource(R.drawable.bg_chkbox_new_1);
                    isChecked = true;
                    ((MyApplication) getActivity().getApplication()).isNight = true;
                    setShowMode.setMode(getActivity());

                } else {
                    imageView_changeStyle.setImageResource(R.drawable.bg_chkbox_new_0);
                    isChecked = false;
                    ((MyApplication) getActivity().getApplication()).isNight = false;
                    setShowMode.cancelMode(getActivity());

                }
            }
        });

        this.scrollView_my.setScrollListener(new ObservableScrollView.ScrollListener() {
            @Override
            public void scrollOritention(int oritention) {
                if (oritention == ObservableScrollView.SCROLL_DOWN) {
                    linearLayout.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    linearLayout.setBackgroundColor(Color.parseColor("#ff3344"));
                }
            }
        });
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if(this.scrollView_my != null){
                this.scrollView_my.scrollTo(0, 0);
            }
            System.out.println("可见了");
        }

    }

    private void loginUserInfo(){
        this.sharedPreferences = getActivity().getSharedPreferences("userInfo.xml", Context.MODE_PRIVATE);
        if(flag==1){
            name = sharedPreferences.getString("name", null);
            gender = sharedPreferences.getString("gender", null);
            userIconPath = sharedPreferences.getString("userIcon", null);
            if (name != null) {
                loginUserName.setText(name);
            }
            if (gender != null) {
                if ("MALE".equals(gender)) {
                    imgUserGender.setImageResource(R.drawable.night_icon_man);
                }
            }
            if (userIconPath != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        InputStream inputStream = null;
                        try {
                            URL url = new URL(userIconPath);
                            inputStream = url.openStream();
                            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            circleImageViewUserImg.post(new Runnable() {
                                @Override
                                public void run() {
                                    circleImageViewUserImg.setImageBitmap(bitmap);
                                }
                            });
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                    inputStream = null;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loginUserInfo();
        if(this.scrollView_my != null){
            this.scrollView_my.scrollTo(0, 0);
        }
        boolean bl=((MyApplication)getActivity().getApplication()).loginFlag;
        if(bl){
            loginUserInfo();
            loginAndShow();
            bl=false;
        }
    }

    @OnClick({R.id.circleImageView_userImg, R.id.btn_login, R.id.textView_userFansNumber, R.id.textView_userFocusNumber, R.id.textView_userContributeNumber, R.id.linearLayout_login_userID, R.id.textView_login_edit})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.circleImageView_userImg:
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_login:
                this.showLoginPager();
                break;
            case R.id.textView_userFansNumber:
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView_userFocusNumber:
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView_userContributeNumber:
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linearLayout_login_userID:
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView_login_edit:
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void imgStatu() {
        if (this.imageView_changeStyle != null) {
            if (isChecked) {
                imageView_changeStyle.setImageResource(R.drawable.bg_chkbox_new_1);
            }
        }
    }

    @OnClick({R.id.relativeLayout_one, R.id.relativeLayout_two, R.id.relativeLayout_three, R.id.relativeLayout_four, R.id.relativeLayout_five, R.id.relativeLayout_six, R.id.relativeLayout_seven, R.id.relativeLayout_eight})
    public void onClickItem(View view) {
        switch (view.getId()) {
            case R.id.relativeLayout_one:
                Intent intentone = new Intent(getActivity(), My_Gold_Activity.class);
                startActivity(intentone);
                break;
            case R.id.relativeLayout_two:
                Intent intenttwo = new Intent(getActivity(), My_Vip_Activity.class);
                startActivity(intenttwo);
                break;
            case R.id.relativeLayout_three:
                Intent intentthree = new Intent(getActivity(), My_Task_Activity.class);
                startActivity(intentthree);
                break;
            case R.id.relativeLayout_four:
                Intent intentfour = new Intent(getActivity(), My_Yellow_Activity.class);
                startActivity(intentfour);
                break;
            case R.id.relativeLayout_five:
                Intent intentfive = new Intent(getActivity(), My_Friends_Activity.class);
                startActivity(intentfive);
                break;
            case R.id.relativeLayout_six:
                break;
            case R.id.relativeLayout_seven:
                showShare();
                break;
            case R.id.relativeLayout_eight:
                Intent intenteight = new Intent(getActivity(), My_Settins_Activity.class);
                startActivity(intenteight);
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getActivity());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESPONSE_CODE) {

            }
        }
    }

    private void showLoginPager() {
        Intent intent = new Intent(getActivity(), LoginPagerActivity.class);
        startActivity(intent);
    }

    private void loginAndShow() {
        int visibility = this.linearLayoutUserInformation.getVisibility();
        if (visibility == View.GONE) {
            this.linearLayoutUserInformation.setVisibility(View.VISIBLE);
        } else {
            this.linearLayoutUserInformation.setVisibility(View.GONE);
        }
        if (this.btnLogin.getVisibility() == View.VISIBLE) {
            this.btnLogin.setVisibility(View.GONE);
        } else {
            this.btnLogin.setVisibility(View.VISIBLE);
        }
        if (this.textViewLoginEdit.getVisibility() == View.GONE) {
            this.textViewLoginEdit.setVisibility(View.VISIBLE);
        } else {
            this.textViewLoginEdit.setVisibility(View.GONE);
        }
        if (this.linearLayoutLoginUserID.getVisibility() == View.GONE) {
            this.linearLayoutLoginUserID.setVisibility(View.VISIBLE);
        } else {
            this.linearLayoutLoginUserID.setVisibility(View.GONE);
        }
    }

}
