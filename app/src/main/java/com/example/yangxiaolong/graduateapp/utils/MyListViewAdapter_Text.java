package com.example.yangxiaolong.graduateapp.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.ListUserContent;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yangxiaolong on 2016/11/1.
 */
public class MyListViewAdapter_Text extends BaseAdapter {

    private List<ListUserContent> data;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyListViewAdapter_Text(Context context,List<ListUserContent> data){
        this.data=data;
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
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
        convertView=layoutInflater.inflate(R.layout.item_01,null);
        MyViewHolder myViewHolder=null;
        if(myViewHolder==null){
            myViewHolder=new MyViewHolder();
            myViewHolder.circleImageView_img= (ImageView) convertView.findViewById(R.id.circleImageView_img);
            myViewHolder.imageView_msg= (ImageView) convertView.findViewById(R.id.imageView_msg);
            myViewHolder.imageButton_good= (ImageView) convertView.findViewById(R.id.imageButton_good);
            myViewHolder.imageButton_comment= (ImageView) convertView.findViewById(R.id.imageButton_comment);
            myViewHolder.imageButton_share= (ImageView) convertView.findViewById(R.id.imageButton_share);
            myViewHolder.imageView_favorite= (ImageView) convertView.findViewById(R.id.imageButton_favorite);
            myViewHolder.textView_name= (TextView) convertView.findViewById(R.id.textView_name);
            myViewHolder.textView_goodCount= (TextView) convertView.findViewById(R.id.textView_goodCount);
            myViewHolder.textView_commentCount= (TextView) convertView.findViewById(R.id.textView_commentCount);
            myViewHolder.textView_shareCount= (TextView) convertView.findViewById(R.id.textView_shareCount);
            myViewHolder.textView_favoriteCount= (TextView) convertView.findViewById(R.id.textView_favoriteCount);
            myViewHolder.textView_content= (TextView) convertView.findViewById(R.id.textView_content);
            convertView.setTag(myViewHolder);
        }else{
            myViewHolder= (MyViewHolder) convertView.getTag();
        }
        ListUserContent listUserContent=data.get(position);
        Picasso.with(context).load(listUserContent.getUserIcon()).into(myViewHolder.circleImageView_img);
        myViewHolder.textView_name.setText(listUserContent.getUserNick());
        myViewHolder.textView_content.setText(listUserContent.getContent());

        return convertView;
    }


    class MyViewHolder{
        //头像
        ImageView circleImageView_img;
        //等级
        ImageView imageView_msg;
        //点赞图标
        ImageView imageButton_good;
        //评论图标
        ImageView imageButton_comment;
        //分享图标
        ImageView imageButton_share;
        //收藏图标
        ImageView imageView_favorite;
        //用户名
        TextView textView_name;
        //点赞数目
        TextView textView_goodCount;
        //评论数目
        TextView textView_commentCount;
        //分享数目
        TextView textView_shareCount;
        //收藏数目
        TextView textView_favoriteCount;
        //内容
        TextView textView_content;
    }
}
