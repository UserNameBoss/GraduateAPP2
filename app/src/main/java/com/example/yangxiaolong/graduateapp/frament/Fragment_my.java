package com.example.yangxiaolong.graduateapp.frament;


import android.annotation.TargetApi;
import android.content.Intent;
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

import com.example.yangxiaolong.graduateapp.MyView.CircleImageView;
import com.example.yangxiaolong.graduateapp.MyView.ObservableScrollView;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.activity.My_Friends_Activity;
import com.example.yangxiaolong.graduateapp.activity.My_Gold_Activity;
import com.example.yangxiaolong.graduateapp.activity.My_Settins_Activity;
import com.example.yangxiaolong.graduateapp.activity.My_Task_Activity;
import com.example.yangxiaolong.graduateapp.activity.My_Vip_Activity;
import com.example.yangxiaolong.graduateapp.activity.My_Yellow_Activity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_my extends Fragment {


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
    private ObservableScrollView scrollView_my;
    private LinearLayout linearLayout;
    private ImageView imageView_changeStyle;
    private boolean isChecked;

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
        ButterKnife.bind(this,view);

        this.scrollView_my = (ObservableScrollView) view.findViewById(R.id.scrollView_my);
        this.linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_my_titleBar);
        this.imageView_changeStyle=(ImageView)view.findViewById(R.id.img_changeStyle) ;
        this.imgStatu();

        this.imageView_changeStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isChecked){
                    imageView_changeStyle.setImageResource(R.drawable.bg_chkbox_new_1);
                    isChecked=true;
                }else{
                    imageView_changeStyle.setImageResource(R.drawable.bg_chkbox_new_0);
                    isChecked=false;
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

    private void initView() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && this.scrollView_my != null) {
            this.scrollView_my.scrollTo(0, 0);
        }
        /*if(imageView_changeStyle!=null){
            if(!isChecked){
                imageView_changeStyle.setImageResource(R.drawable.bg_chkbox_new_1);
            }else{
                imageView_changeStyle.setImageResource(R.drawable.bg_chkbox_new_0);
            }
        }*/

    }

    @OnClick({R.id.circleImageView_userImg, R.id.btn_login, R.id.textView_userFansNumber, R.id.textView_userFocusNumber, R.id.textView_userContributeNumber, R.id.linearLayout_login_userID, R.id.textView_login_edit})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.circleImageView_userImg:
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_login:
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
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
   private void imgStatu(){
       if(this.imageView_changeStyle!=null){
           if(isChecked){
               imageView_changeStyle.setImageResource(R.drawable.bg_chkbox_new_1);
           }
       }
   }

    @OnClick({R.id.relativeLayout_one, R.id.relativeLayout_two, R.id.relativeLayout_three, R.id.relativeLayout_four, R.id.relativeLayout_five, R.id.relativeLayout_six, R.id.relativeLayout_seven, R.id.relativeLayout_eight})
    public void onClickItem(View view) {
        switch (view.getId()) {
            case R.id.relativeLayout_one:
                Intent intentone=new Intent(getActivity(),My_Gold_Activity.class);
                startActivity(intentone);
                break;
            case R.id.relativeLayout_two:
                Intent intenttwo=new Intent(getActivity(),My_Vip_Activity.class);
                startActivity(intenttwo);
                break;
            case R.id.relativeLayout_three:
                Intent intentthree=new Intent(getActivity(), My_Task_Activity.class);
                startActivity(intentthree);
                break;
            case R.id.relativeLayout_four:
                Intent intentfour=new Intent(getActivity(),My_Yellow_Activity.class);
                startActivity(intentfour);
                break;
            case R.id.relativeLayout_five:
                Intent intentfive=new Intent(getActivity(),My_Friends_Activity.class);
                startActivity(intentfive);
                break;
            case R.id.relativeLayout_six:
                break;
            case R.id.relativeLayout_seven:
                showShare();
                break;
            case R.id.relativeLayout_eight:
                Intent intenteight=new Intent(getActivity(), My_Settins_Activity.class);
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
}
