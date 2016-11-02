package com.example.yangxiaolong.graduateapp.utils;

import android.content.Context;
import android.content.Intent;
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

import com.example.yangxiaolong.graduateapp.MyView.CircleImageView;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.ListUserContent;
import com.example.yangxiaolong.graduateapp.domain.Pic;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangxiaolong on 2016/11/1.
 */
public class MyListViewAdapter_Text extends BaseAdapter implements View.OnClickListener {

    private List<ListUserContent> data;
    private Context context;
    private LayoutInflater layoutInflater;
    private LocalBroadcastManager localBroadcastManager;

    public MyListViewAdapter_Text(Context context, List<ListUserContent> data) {
        this.data = data;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        localBroadcastManager=LocalBroadcastManager.getInstance(context);
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
        if (myViewHolder == null) {
            myViewHolder = new ViewHolder(convertView);
            convertView.setTag(myViewHolder);
            myViewHolder.imageView_play.setOnClickListener(this);
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }
        System.out.println("=============list==="+listUserContent);
        if(listUserContent!=null) {
            if (listUserContent.getCategoryId() == 29) {
                System.out.println("==========Adapter.=====listUserContent.getCategoryId=" + listUserContent.getCategoryId());
                myViewHolder.textView_hits.setText(String.valueOf(listUserContent.getHits()));
                Pic pic = listUserContent.getPic();
                int height = pic.getHeight();
                String url = pic.getUrl();
                int width = pic.getWidth();
                myViewHolder.textView_title.setText(listUserContent.getTitle());
                myViewHolder.textView_title.setVisibility(View.VISIBLE);
                Picasso.with(context).load(url).resize(width, height).centerCrop().into(myViewHolder.imageView_content);
                myViewHolder.imageView_content.setVisibility(View.VISIBLE);
                myViewHolder.textView_content.setVisibility(View.GONE);
                myViewHolder.frameLayout_playSound.setVisibility(View.VISIBLE);

                myViewHolder.imageView_play.setTag(listUserContent.getAudio().getAudio());
            }else{
                if (listUserContent.getPicCount() == 0) {
                    myViewHolder.textView_content.setText(listUserContent.getContent());
                    myViewHolder.textView_title.setVisibility(View.GONE);
                    myViewHolder.imageView_content.setVisibility(View.VISIBLE);
                    myViewHolder.frameLayout_playSound.setVisibility(View.GONE);
                } else {
                    Pic pic = listUserContent.getPic();
                    int height = pic.getHeight();
                    String url = pic.getUrl();
                    int width = pic.getWidth();
                    myViewHolder.textView_title.setText(listUserContent.getTitle());
                    myViewHolder.textView_title.setVisibility(View.VISIBLE);
                    Picasso.with(context).load(url).resize(width, height).centerCrop().into(myViewHolder.imageView_content);
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
        }

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.imageView_play:
                Intent intent=new Intent("audio");
                CheckBox checkBox= (CheckBox) v;
                String path= (String) checkBox.getTag();
                intent.putExtra("path",path);
                System.out.println("==========点击了==============");
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
        TextView textView_current;
        @BindView(R.id.textView_hits)
        TextView textView_hits;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


//    class MyViewHolder{
//        //头像
//        ImageView circleImageView_img;
//        //等级
//        ImageView imageView_msg;
//        //点赞图标
//        ImageView imageButton_good;
//        //评论图标
//        ImageView imageButton_comment;
//        //分享图标
//        ImageView imageButton_share;
//        //收藏图标
//        ImageView imageView_favorite;
//        //用户名
//        TextView textView_name;
//        //点赞数目
//        TextView textView_goodCount;
//        //评论数目
//        TextView textView_commentCount;
//        //分享数目
//        TextView textView_shareCount;
//        //收藏数目
//        TextView textView_favoriteCount;
//        //内容
//        TextView textView_content;
//
//
//
//        public MyViewHolder(View view){
//
//        }
//    }


}
