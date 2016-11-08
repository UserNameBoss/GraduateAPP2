package com.example.yangxiaolong.graduateapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangxiaolong.graduateapp.MyView.CircleImageView;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.activity.CommentActivity;
import com.example.yangxiaolong.graduateapp.activity.IconUserInforActivity;
import com.example.yangxiaolong.graduateapp.domain.ListUserContent;
import com.example.yangxiaolong.graduateapp.domain.Pic;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by yangxiaolong on 2016/11/1.
 */
public class MyListViewAdapter_Text extends BaseAdapter implements View.OnClickListener {

    private List<ListUserContent> data;
    private Context context;
    private LayoutInflater layoutInflater;
    private LocalBroadcastManager localBroadcastManager;
    private static ProgressBar progressBar;
    private static TextView textView_currentTime;
    private IntentFilter intentFilter=new IntentFilter("Progress");
    private static CheckBox checkBox;
    private int flag;
    private NetWorkListUserContent.GetResultCallback getResultCallback;

    private SQListManager sqListManager;

    public MyListViewAdapter_Text(Context context, List<ListUserContent> data,int Flag) {
        this.data = data;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.flag=Flag;
        localBroadcastManager=LocalBroadcastManager.getInstance(context);
        localBroadcastManager.registerReceiver(new ProgressBroadcastReciver(),intentFilter);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_01, null);
        ViewHolder myViewHolder = null;
        ListUserContent listUserContent = data.get(position);
        if (convertView.getTag()== null) {
            myViewHolder = new ViewHolder(convertView);
            convertView.setTag(myViewHolder);
            myViewHolder.imageView_play.setOnClickListener(this);
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }
        if(listUserContent!=null) {
            if (listUserContent.getAudio()!=null) {
                myViewHolder.textView_hits.setText(String.valueOf(listUserContent.getHits())+"次播放");
                Pic pic = listUserContent.getPic();
                if(pic!=null) {
                    int height = pic.getHeight();
                    String url = pic.getUrl();
                    int width = pic.getWidth();
                    Picasso.with(context).load(url).resize(width, height).centerCrop().into(myViewHolder.imageView_content);

                }else{
                    myViewHolder.imageView_content.setImageResource(R.mipmap.ic_launcher);
                }
                myViewHolder.textView_title.setText(listUserContent.getTitle());
                myViewHolder.textView_title.setVisibility(View.VISIBLE);
                myViewHolder.imageView_content.setVisibility(View.VISIBLE);
                myViewHolder.textView_content.setVisibility(View.GONE);
                myViewHolder.frameLayout_playSound.setVisibility(View.VISIBLE);
                myViewHolder.textView_currentTime.setText(FormatTiem.formatTime(listUserContent.getAudio().getDuration()*1000));
                myViewHolder.textView_audioPath.setText(String.valueOf(listUserContent.getAudio().getAudio()));
                myViewHolder.progressBar.setMax(listUserContent.getAudio().getDuration()*1000);
                myViewHolder.imageView_play.setTag(myViewHolder);

            }else{
                if (listUserContent.getPicCount() == 0) {
                    myViewHolder.textView_content.setText(listUserContent.getContent());
                    myViewHolder.textView_content.setVisibility(View.VISIBLE);
                    myViewHolder.textView_title.setVisibility(View.GONE);
                    myViewHolder.imageView_content.setVisibility(View.GONE);
                    myViewHolder.frameLayout_playSound.setVisibility(View.GONE);
                } else {
                    Pic pic = listUserContent.getPic();
                    if(pic!=null) {
                        int height = pic.getHeight();
                        String url = pic.getUrl();
                        int width = pic.getWidth();
                        Picasso.with(context).load(url).resize(width, height).centerCrop().into(myViewHolder.imageView_content);
                    }else{
                        myViewHolder.imageView_content.setImageResource(R.mipmap.ic_launcher);
                    }
                    myViewHolder.textView_title.setText(listUserContent.getTitle());
                    myViewHolder.textView_title.setVisibility(View.VISIBLE);
                    myViewHolder.imageView_content.setVisibility(View.VISIBLE);
                    myViewHolder.textView_content.setVisibility(View.GONE);
                    myViewHolder.frameLayout_playSound.setVisibility(View.GONE);
                }
            }

            Picasso.with(context).load(listUserContent.getUserIcon()).into(myViewHolder.circleImageView_Img);
            myViewHolder.textView_name.setText(listUserContent.getUserNick());
            myViewHolder.textView_commentCount.setText(String.valueOf(listUserContent.getComments()));
            myViewHolder.textView_goodCount.setText(String.valueOf(listUserContent.getGoods()));
            myViewHolder.textView_favoriteCount.setText(String.valueOf(listUserContent.getFavorites()));
            myViewHolder.textView_shareCount.setText(String.valueOf(listUserContent.getShares()));
            myViewHolder.imageButton_good.setOnClickListener(this);
            myViewHolder.imageButton_favorite.setOnClickListener(this);
            if(listUserContent.isGood()){
                myViewHolder.imageButton_good.setImageResource(R.drawable.btn_good_1);
            }
            if(listUserContent.isFavorite()){
                myViewHolder.imageButton_favorite.setImageResource(R.drawable.btn_favorite_1);
            }
            myViewHolder.imageButton_good.setTag(listUserContent);
            myViewHolder.imageButton_favorite.setTag(listUserContent);
            if(flag==2){
                myViewHolder.textView_type.setText("#"+listUserContent.getSubject()+"#");
                myViewHolder.textView_type.setVisibility(View.VISIBLE);
                myViewHolder.textView_type.setTextColor(Color.RED);
            }
            myViewHolder.imageButton_comment.setOnClickListener(this);
            myViewHolder.textView_articleId.setText(String.valueOf(listUserContent.getArticleId()));
            myViewHolder.imageButton_comment.setTag(listUserContent);
            myViewHolder.circleImageView_Img.setTag(listUserContent.getUserId());
            myViewHolder.circleImageView_Img.setOnClickListener(this);
            myViewHolder.imageButton_share.setTag(listUserContent);
            myViewHolder.imageButton_share.setOnClickListener(this);
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.imageView_play:
                Intent intent=new Intent("audio");
                checkBox= (CheckBox) v;
                if(checkBox.isChecked()) {
                    ViewHolder viewHolder = (ViewHolder) checkBox.getTag();
                    intent.putExtra("path", viewHolder.textView_audioPath.getText().toString());
                    intent.putExtra("playStart",true);
                    viewHolder.progressBar.setVisibility(View.VISIBLE);
                    this.progressBar=viewHolder.progressBar;
                    System.out.println("===========progressBar.id="+progressBar.getId());
                    this.textView_currentTime=viewHolder.textView_currentTime;

                }else{
                    intent.putExtra("playStart",false);
                }
                localBroadcastManager.sendBroadcast(intent);
                System.out.println("==========点击了==============");
                break;
            case R.id.imageButton_good:
                ImageButton imageButton= (ImageButton) v;
                imageButton.setImageResource(R.drawable.btn_good_1);
                final ListUserContent listUserContent= (ListUserContent) v.getTag();
                if(listUserContent.isGood()){
                    Toast.makeText(context, "已经点过赞了", Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println("========listUserContent1=" + listUserContent);
                    getResultCallback = new NetWorkListUserContent.GetResultCallback() {
                        @Override
                        public void getMessage(String message) {

                            System.out.println("========================"+message.substring(1,2).matches("\\d"));
                            try {
                                if(!message.substring(0,1).matches("\\d")) {
                                    JSONObject jsonObject = new JSONObject(message);
                                    System.out.println("================isNUll=" + jsonObject.isNull("Message"));
                                    System.out.println("==============="+jsonObject.getString("Message"));

                                }else{
                                    String ss = message.substring(0, message.indexOf(","));
                                    System.out.println("==================messsage=" + message);
                                    listUserContent.setGoods(Integer.parseInt(ss));
                                }
                                notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    String path = "http://xb.huabao.me/?json=gender/article_action_v2";
                    String pathKey = "sign=38EA9B2CF30D99FC9A742F4C42E84EBD&timestamp="+GETCurrentTime.getTimeMS()+"&action=good&v=2140&deviceId=267f265924954af5ad20ec74d63ddcd5&articleId=" + listUserContent.getArticleId() + "&userId=2172827";
                    NetWorkListUserContent.getPostResult(path, pathKey, getResultCallback);
                    listUserContent.setGood(true);
                }
                break;

            case R.id.imageButton_favorite:
                ImageButton imageButton_favorite= (ImageButton) v;

                final ListUserContent listUserContent02= (ListUserContent) v.getTag();
                if(listUserContent02.isFavorite()){
                    Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                    imageButton_favorite.setImageResource(R.drawable.btn_favorite_0);
                    getResultCallback = new NetWorkListUserContent.GetResultCallback() {
                        @Override
                        public void getMessage(String message) {

                            System.out.println("========================"+message.substring(1,2).matches("\\d"));
                            try {
                                if(!message.substring(0,1).matches("\\d")) {
                                    JSONObject jsonObject = new JSONObject(message);
                                    System.out.println("================isNUll=" + jsonObject.isNull("Message"));
                                    System.out.println("==============="+jsonObject.getString("Message"));

                                }else{
                                    System.out.println("==================messsage=" + message);
                                    String ss = message;

                                    listUserContent02.setFavorites(Integer.parseInt(ss));
                                }
                                notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                       String path = "http://xb.huabao.me/?json=gender/article_action_v2";
                    String pathKey = "sign=D70816CA97A6B2F55711E13CD593DAF8&timestamp="+GETCurrentTime.getTimeMS()+"&action=cancel_favorite&v=2140&deviceId=267f265924954af5ad20ec74d63ddcd5&articleId="+listUserContent02.getArticleId()+"&userId=2172827";

                    NetWorkListUserContent.getPostResult(path, pathKey, getResultCallback);
                    listUserContent02.setFavorite(false);

                }else {
                    System.out.println("========listUserContent1=" + listUserContent02);
                    imageButton_favorite.setImageResource(R.drawable.btn_favorite_1);
                    getResultCallback = new NetWorkListUserContent.GetResultCallback() {
                        @Override
                        public void getMessage(String message) {

                            System.out.println("========================"+message.substring(1,2).matches("\\d"));
                            try {
                                if(!message.substring(0,1).matches("\\d")) {
                                    JSONObject jsonObject = new JSONObject(message);
                                    System.out.println("================isNUll=" + jsonObject.isNull("Message"));
                                    System.out.println("==============="+jsonObject.getString("Message"));

                                }else{
                                    System.out.println("==================messsage=" + message);
                                    String ss = message;

                                    listUserContent02.setFavorites(Integer.parseInt(ss));
                                }
                                notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    String path = "http://xb.huabao.me/?json=gender/article_action_v2";
                    String pathKey = "sign=sign=96D8945D8C4E7719F8918636DB36D05E&timestamp="+GETCurrentTime.getTimeMS()+"&action=favorite&v=2140&deviceId=267f265924954af5ad20ec74d63ddcd5&articleId="+listUserContent02.getArticleId()+"&userId=2172827";
                    NetWorkListUserContent.getPostResult(path, pathKey, getResultCallback);
                    listUserContent02.setFavorite(true);
                }
                break;

            case R.id.imageButton_comment:
                //ListUserContent listUserContent1= (ListUserContent) v.getTag();
                ListUserContent listUserContent1= (ListUserContent) v.getTag();

                Intent intent_comment=new Intent(context,CommentActivity.class);
                String path="http://xb.huabao.me/?json=gender/comment_list_v2";
                String pathKey="sign=B951E6A6BC6BE9A98A2D8C1819006C23&timestamp="+GETCurrentTime.getTimeMS()+"&pageSize=20&v=2140&articleId="+listUserContent1.getArticleId()+"&fields=ArticleId%2CUserId%2CUserNick%2CUserIcon%2CPubtime%2CComment";
                intent_comment.putExtra("path",path);
                intent_comment.putExtra("pathKey",pathKey);
                Bundle bundle=new Bundle();
                bundle.putInt("articleId",listUserContent1.getArticleId());
                bundle.putInt("categoryId",listUserContent1.getCategoryId());
                bundle.putInt("comments",listUserContent1.getComments());
                bundle.putString("content",listUserContent1.getContent());
                bundle.putString("title",listUserContent1.getTitle());
                bundle.putInt("favorites",listUserContent1.getFavorites());
                bundle.putInt("goods",listUserContent1.getGoods());
                bundle.putBoolean("isGoods",listUserContent1.isGood());
                bundle.putBoolean("isFavorites",listUserContent1.isFavorite());
                bundle.putInt("hits",listUserContent1.getHits());
                bundle.putString("userNameIcon",listUserContent1.getUserIcon());
                bundle.putInt("shares",listUserContent1.getShares());
                bundle.putString("userNick",listUserContent1.getUserNick());
                bundle.putInt("flag",flag);
                if(listUserContent1.getAudio()!=null){
                    String audio=listUserContent1.getAudio().getAudio();
                    int duration=listUserContent1.getAudio().getDuration();
                    bundle.putString("audio",audio);
                    bundle.putInt("duration",duration);
                }
                if(listUserContent1.getPic()!=null){
                    Pic pic=listUserContent1.getPic();
                    bundle.putString("url",pic.getUrl());
                    bundle.putInt("height",pic.getHeight());
                    bundle.putInt("width",pic.getWidth());
                }
                intent_comment.putExtras(bundle);
                intent_comment.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent_comment);
                break;
            case R.id.circleImageView_img:
                path="http://xb.huabao.me/?json=gender/get_user_info";
                int artId= (int) v.getTag();
                pathKey="sign=DF5F861868784C8D7D186619CA593BCD&timestamp="+GETCurrentTime.getTimeMS()+"&v=2140&operator=2172827&userId="+v.getTag();
                Intent intent1=new Intent(context,IconUserInforActivity.class);
                intent1.putExtra("path",path);
                intent1.putExtra("pathKey",pathKey);
                intent1.putExtra("userId",artId);
                System.out.println("=======================onClick.UserIcon============");

                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);

                break;
            case R.id.imageButton_share:
                ShareSDK.initSDK(context);
                OnekeyShare oks = new OnekeyShare();
                ListUserContent listUserContent_share= (ListUserContent) v.getTag();
                if(listUserContent_share.getPic()!=null){
                    oks.setImageUrl(listUserContent_share.getPic().getUrl());
                }else {

                }

                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
                oks.setTitle("笑神来了");
                // titleUrl是标题的网络链接，QQ和QQ空间等使用
                oks.setTitleUrl("http://xb.huabao.me/?json=gender/comment_list_v2");
                // text是分享文本，所有平台都需要这个字段
                if(listUserContent_share.getContent()!=null) {
                    oks.setText(listUserContent_share.getContent());
                }else{
                    oks.setText(listUserContent_share.getTitle().toString());
                }
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://xb.huabao.me/?json=gender/comment_list_v2");
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            //                oks.setComment("我是测试评论文本");
            // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(context.getString(R.string.app_name));
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://xb.huabao.me/?json=gender/comment_list_v2");

                // 启动分享GUI
                oks.show(context);

        }

    }

    static class ViewHolder {
        @BindView(R.id.circleImageView_img)
        CircleImageView circleImageView_Img;
        @BindView(R.id.textView_name)
        TextView textView_name;
        @BindView(R.id.imageView_msg)
        ImageView imageView_msg;
        @BindView(R.id.textView_content)
        TextView textView_content;
        @BindView(R.id.imageButton_good)
        ImageButton imageButton_good;
        @BindView(R.id.textView_goodCount)
        TextView textView_goodCount;
        @BindView(R.id.imageButton_comment)
        ImageButton imageButton_comment;
        @BindView(R.id.textView_commentCount)
        TextView textView_commentCount;
        @BindView(R.id.imageButton_share)
        ImageButton imageButton_share;
        @BindView(R.id.textView_shareCount)
        TextView textView_shareCount;
        @BindView(R.id.imageButton_favorite)
        ImageButton imageButton_favorite;
        @BindView(R.id.textView_favoriteCount)
        TextView textView_favoriteCount;
        @BindView(R.id.imageView_content)
        ImageView imageView_content;
        @BindView(R.id.textVIew_title)
        TextView textView_title;
        @BindView(R.id.frameLayout_playSound)
        FrameLayout frameLayout_playSound;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;
        @BindView(R.id.imageView_play)
        CheckBox imageView_play;
        @BindView(R.id.textView_current)
        TextView textView_currentTime;
        @BindView(R.id.textView_hits)
        TextView textView_hits;
        @BindView(R.id.textVeiw_audioPath)
        TextView textView_audioPath;
        @BindView(R.id.textView_type)
        TextView textView_type;
        @BindView(R.id.textView_articleId)
        TextView textView_articleId;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



    class ProgressBroadcastReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isStop=intent.getBooleanExtra("isStop",true);
            if(!isStop) {
                int progress = intent.getIntExtra("progress", 0);
                System.out.println("=========Reciver.progressBarid=" + progressBar.getId());
                progressBar.setProgress(progress);
                textView_currentTime.setText(FormatTiem.formatTime(progress));
            }else{
                checkBox.setChecked(false);
            }
        }
    }

    public List<String> getPathIcon(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<15;i++){
            list.add(data.get(i).getUserIcon());
        }
        return list;
    }

    public void getNotifi(List<ListUserContent> data){
        this.data=data;
        notifyDataSetChanged();
    }
}
