package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.MyView.CircleImageView;
import com.example.yangxiaolong.graduateapp.MyView.MyLinearLayout_rectangle;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.ListUserContent;
import com.example.yangxiaolong.graduateapp.domain.UserMessage;
import com.example.yangxiaolong.graduateapp.utils.GETCurrentTime;
import com.example.yangxiaolong.graduateapp.utils.JsonToDomain;
import com.example.yangxiaolong.graduateapp.utils.MyListViewAdapter_Text;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IconUserInforActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.image_iconUser_back)
    ImageButton image_IconUserBack;
    @BindView(R.id.textView_iconUser_name)
    TextView textView_IconUserName;
    @BindView(R.id.textView_iconUser_id)
    TextView textView_IconUserId;
    @BindView(R.id.RelativeLayout_iconUser)
    RelativeLayout RelativeLayout_IconUser;
    private PullToRefreshListView pullToRefreshListView;
    private ListView listView;
    private LayoutInflater layoutInflater;
    private Intent intent;
    private String path;
    private String pathKey;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private UserMessage userMessage;
    private NetWorkListUserContent.GetResultCallback getResultCallbackContent;
    private List<ListUserContent> listUserContents;
    private ViewHolder viewHolder;
    private List<ListUserContent> listUserContents_collect;
    private List<ListUserContent> listUserContents_Contribute;
    private int flag=1;
    private int preFlag=1;
    int userId;
    MyListViewAdapter_Text myListViewAdapter_text_Contribute;
    MyListViewAdapter_Text myListViewAdapter_text_Comment;
    MyListViewAdapter_Text myListViewAdapter_text_Collect;
    private List<TextView> textViews_title=new ArrayList<>();
    private List<TextView> textViews_count=new ArrayList<>();
    private List<ImageView> imageViews_flag=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_user_infor);
        ButterKnife.bind(this);
        intent = getIntent();
        path = intent.getStringExtra("path");
        pathKey = intent.getStringExtra("pathKey");
        userId= intent.getIntExtra("userId", 0);
        System.out.println("==================Icon.UserId="+userId);
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.PullToListView_iconUser);
        listView = pullToRefreshListView.getRefreshableView();
        layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.iconuser_head, null);
        viewHolder = new ViewHolder(view);

        //得到屏幕的高度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Activity activity = this;
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;

        view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        getResultCallback = new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                System.out.println("====================IconUser.message=" + message);

                        userMessage = JsonToDomain.getUserMessage(message);
                        Picasso.with(getApplicationContext()).load(userMessage.getUserIcon()).into(viewHolder.circleImage_ViewUserImg);
                        viewHolder.textView_UserLocation.setText(userMessage.getCity());
                        viewHolder.textVeiw_TyrantPowder.setText("霸粉" + userMessage.getFans());
                        viewHolder.textView_Attention.setText("关注" + userMessage.getAttentions());
                        textView_IconUserName.setText(userMessage.getUserNick());
                        textView_IconUserId.setText("霸号：" + userMessage.getUserId());


            }
        };
        NetWorkListUserContent.getPostResult(path, pathKey, getResultCallback);
        getResultCallbackContent = new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                System.out.println("=====================IconUser.Message.Content=" + message);
                switch (flag){
                    case 1:
                            listUserContents = JsonToDomain.getDataUserContent(message);
                            myListViewAdapter_text_Comment = new MyListViewAdapter_Text(getApplicationContext(), listUserContents, 1);
                            pullToRefreshListView.setAdapter(myListViewAdapter_text_Comment);
                        break;
                    case 2:

                            listUserContents_collect = JsonToDomain.getDataUserContent(message);
                        myListViewAdapter_text_Comment = new MyListViewAdapter_Text(getApplicationContext(), listUserContents_collect, 1);
                            pullToRefreshListView.setAdapter(myListViewAdapter_text_Collect);
                        break;
                    case 3:
                            listUserContents_Contribute = JsonToDomain.getDataUserContent(message);
                        myListViewAdapter_text_Comment=new MyListViewAdapter_Text(getApplicationContext(), listUserContents_Contribute, 1);
                            pullToRefreshListView.setAdapter(myListViewAdapter_text_Contribute);
                        break;
                }

                pullToRefreshListView.setAdapter(myListViewAdapter_text_Comment);


            }
        };
        path = "http://xb.huabao.me/?json=gender/comment_list";
        pathKey = "sign=45954E26463139605EC08808F6EBC233&timestamp=" + GETCurrentTime.getTimeMS() + "&pageSize=20&v=2140&startId=2147483647&userId=" + userId + "&fields=ArticleId%2CUserId%2CUserNick%2CUserIcon%2CPubtime%2CComment%2CTitle%2CContent%2CPic";
        NetWorkListUserContent.getPostResult(path, pathKey, getResultCallbackContent);
        viewHolder.linearLayout_Collect.setOnClickListener(this);
        viewHolder.linearLayout_Comment.setOnClickListener(this);
        viewHolder.linearLayout_Contribute.setOnClickListener(this);
        viewHolder.linearLayout_IconUserAdd.setOnClickListener(this);
        viewHolder.linearLayout_IconUserMessage.setOnClickListener(this);
        viewHolder.textView_Canceladd.setOnClickListener(this);
        listView=pullToRefreshListView.getRefreshableView();
        listView.addHeaderView(view);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.linearLayout_comment:
                flag=1;
                myListViewAdapter_text_Comment.getNotifi(listUserContents);
                getBackGound0(preFlag);
                getBackGound1(flag);
                preFlag=flag;
                break;

            case R.id.linearLayout_collect:
                flag=2;
                if(listUserContents_collect==null) {
                    path = "http://xb.huabao.me/?json=gender/get_favorite_list";
                    pathKey = "sign=099249464C74936D886CDFDA445919F4&timestamp=" + GETCurrentTime.getTimeMS() + "&pageSize=20&v=2140&startId=2147483647&userId=" + userId + "&fields=ArticleId%2CTitle%2CUserId%2CUserNick%2CUserIcon%2CContent%2CPic%2CPubtime%2CHits%2CGoods%2CShares%2CComments";
                    NetWorkListUserContent.getPostResult(path, pathKey, getResultCallbackContent);
                }else{
                    myListViewAdapter_text_Comment.getNotifi(listUserContents_collect);
                }
                getBackGound0(preFlag);
                getBackGound1(flag);
                preFlag=flag;
                break;

            case R.id.linearLayout_contribute:
                flag=3;
                if(listUserContents_Contribute==null) {
                    path = "http://xb.huabao.me/?json=gender/get_my_posts";
                    pathKey = "sign=7F919BBF918D9D3EF58FE49BAAC8D5B8&timestamp=" + GETCurrentTime.getTimeMS() + "&v=2140&startId=2147483647&isAudit=1&userId=" + userId + "&pageSize=20&fields=ArticleId%2CTitle%2CUserId%2CUserNick%2CUserIcon%2CContent%2CPic%2CPubtime%2CHits%2CGoods%2CShares%2CComments";
                    NetWorkListUserContent.getPostResult(path, pathKey, getResultCallbackContent);
                }else{
                    myListViewAdapter_text_Comment.getNotifi(listUserContents_Contribute);
                }
                getBackGound0(preFlag);
                getBackGound1(flag);
                preFlag=flag;
                break;


            case R.id.linearLayout_iconUser_add:
                path="http://xb.huabao.me/?json=gender/add_attention";
                pathKey="sign=35A9041CC8264F7824A0E8D8277E2B92&timestamp="+GETCurrentTime.getTimeMS()+"&v=2140&targetId="+userId+"&userId=2172827";
                NetWorkListUserContent.getPostResult(path,pathKey,null);
                viewHolder.linearLayout_IconUserAdd.setVisibility(View.GONE);
                viewHolder.textView_Canceladd.setVisibility(View.VISIBLE);
                break;
            case R.id.textView_Canceladd:
                viewHolder.linearLayout_IconUserAdd.setVisibility(View.VISIBLE);
                viewHolder.textView_Canceladd.setVisibility(View.GONE);
                break;
            case R.id.linearLayout_iconUser_message:
                Intent intent=new Intent(this,SendMessageActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("userName",userMessage.getUserNick());
                startActivity(intent);
                break;
        }
    }


    static class ViewHolder {
        @BindView(R.id.circleImageView_userImg)
        CircleImageView circleImage_ViewUserImg;
        @BindView(R.id.textView_userLocation)
        TextView textView_UserLocation;
        @BindView(R.id.img_user_gender)
        ImageView imgUser_Gender;
        @BindView(R.id.img_user_vip)
        ImageView imgUser_Vip;
        @BindView(R.id.img_user_lv)
        ImageView imgUser_Lv;
        @BindView(R.id.linearLayout_userInformation)
        LinearLayout linearLayoutUserInformation;
        @BindView(R.id.textVeiw_tyrantPowder)
        TextView textVeiw_TyrantPowder;
        @BindView(R.id.textView_attention)
        TextView textView_Attention;
        @BindView(R.id.linearLayout_iconUser01)
        LinearLayout linearLayoutIconUser01;
        @BindView(R.id.linearLayout_iconUser_add)
        MyLinearLayout_rectangle linearLayout_IconUserAdd;
        @BindView(R.id.linearLayout_iconUser_message)
        MyLinearLayout_rectangle linearLayout_IconUserMessage;
        @BindView(R.id.linearLayout_user_bg)
        RelativeLayout linearLayout_UserBg;
        @BindView(R.id.textView_commentIcon)
        TextView textView_CommentIcon;
        @BindView(R.id.textView_commentCount)
        TextView textView_CommentCount;
        @BindView(R.id.linearLayout_comment)
        LinearLayout linearLayout_Comment;
        @BindView(R.id.textView_collectIcon)
        TextView textView_CollectIcon;
        @BindView(R.id.textView_collectCount)
        TextView textView_CollectCount;
        @BindView(R.id.linearLayout_collect)
        LinearLayout linearLayout_Collect;
        @BindView(R.id.textView_contributeIcon)
        TextView textView_ContributeIcon;
        @BindView(R.id.textView_contributeCount)
        TextView textView_ContributeCount;
        @BindView(R.id.linearLayout_contribute)
        LinearLayout linearLayout_Contribute;
        @BindView(R.id.textView_Canceladd)
        TextView textView_Canceladd;
        @BindView(R.id.imageView_contribute)
        ImageView imageView_contribute;
        @BindView(R.id.imageView_collect)
        ImageView imageView_collect;
        @BindView(R.id.iamgeView_comment)
        ImageView iamgeView_comment;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public void getBackGound1(int currentId){
        switch (currentId){
            case 1:
                viewHolder.textView_CommentIcon.setTextColor(Color.RED);
                viewHolder.iamgeView_comment.setBackgroundColor(Color.RED);
                viewHolder.textView_CommentCount.setTextColor(Color.RED);
                break;
            case 2:
                viewHolder.textView_CollectCount.setTextColor(Color.RED);
                viewHolder.imageView_collect.setBackgroundColor(Color.RED);
                viewHolder.textView_CollectIcon.setTextColor(Color.RED);
                break;
            case 3:
                viewHolder.textView_ContributeCount.setTextColor(Color.RED);
                viewHolder.imageView_contribute.setBackgroundColor(Color.RED);
                viewHolder.textView_ContributeIcon.setTextColor(Color.RED);
                break;
        }
    }
    public void getBackGound0(int preId) {
        switch (preId) {
            case 1:
                viewHolder.textView_CommentIcon.setTextColor(Color.BLACK);
                viewHolder.iamgeView_comment.setBackgroundColor(Color.WHITE);
                viewHolder.textView_CommentCount.setTextColor(Color.BLACK);
                break;
            case 2:
                viewHolder.textView_CollectCount.setTextColor(Color.BLACK);
                viewHolder.imageView_collect.setBackgroundColor(Color.WHITE);
                viewHolder.textView_CollectIcon.setTextColor(Color.BLACK);
                break;
            case 3:
                viewHolder.textView_ContributeCount.setTextColor(Color.BLACK);
                viewHolder.imageView_contribute.setBackgroundColor(Color.WHITE);
                viewHolder.textView_ContributeIcon.setTextColor(Color.BLACK);
                break;
        }
    }

}
