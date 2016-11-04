package com.example.yangxiaolong.graduateapp.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.MyView.CircleImageView;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.CommentPerson;
import com.example.yangxiaolong.graduateapp.domain.Smilies;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangxiaolong on 2016/11/4.
 */
public class MyCommentAdapter extends BaseAdapter implements View.OnClickListener {


    private Context context;
    private List<CommentPerson> data;
    private LayoutInflater layoutInflater;

    public MyCommentAdapter(Context context, List<CommentPerson> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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

        CommentPerson commentPerson=data.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.comment_item, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(commentPerson.getUserIcon()).into(viewHolder.comment_CircleImageView);
        viewHolder.commentItem_Floor.setText(String.valueOf(commentPerson.getFloor()));
        viewHolder.commentItem_UserName.setText(String.valueOf(commentPerson.getUserNick()));
        viewHolder.commentItem_Good.setText(String.valueOf(commentPerson.getCommentGoods()));
        viewHolder.commmentItem_Date.setText(FormatTiem.getFormatDate(commentPerson.getPubtime()));
        viewHolder.comment_textView_content.setText(commentPerson.getComment());
        if(commentPerson.getSmilies()!=null){
            Smilies smilies=commentPerson.getSmilies();
            Picasso.with(context).load(smilies.getIcon()).resize(smilies.getWidth(),smilies.getHeight()).centerCrop().into(viewHolder.commentItem_ImageContent);
            viewHolder.commentItem_ImageContent.setVisibility(View.VISIBLE);
        }
        viewHolder.commentItem_ImageContent.setVisibility(View.GONE);
        viewHolder.comment_CircleImageView.setOnClickListener(this);
        viewHolder.commentItem_ImageGood.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {

    }

    static class ViewHolder {
        @BindView(R.id.comment_circleImageView)
        CircleImageView comment_CircleImageView;
        @BindView(R.id.comment_item_floor)
        TextView commentItem_Floor;
        @BindView(R.id.comment_item_userName)
        TextView commentItem_UserName;
        @BindView(R.id.commment_item_date)
        TextView commmentItem_Date;
        @BindView(R.id.comment_item_imageContent)
        ImageView commentItem_ImageContent;
        @BindView(R.id.comment_item_image_good)
        ImageView commentItem_ImageGood;
        @BindView(R.id.comment_item_good)
        TextView commentItem_Good;
        @BindView(R.id.commetn_textView_content)
        TextView comment_textView_content;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
