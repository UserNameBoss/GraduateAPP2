package com.example.yangxiaolong.graduateapp.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.MyView.CircleImageView;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.MessageDomain;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangxiaolong on 2016/11/8.
 */
public class MessageAdapter extends BaseAdapter {

    private Context context;
    private List<MessageDomain> data;
    private LayoutInflater layoutInflater;

    public MessageAdapter(Context context, List<MessageDomain> data) {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        convertView = layoutInflater.inflate(R.layout.message_item, null);
        MessageDomain messageDomain=data.get(position);
        if(convertView.getTag()==null) {

            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(messageDomain.getFromUserIcon()).into(viewHolder.circleImageView_Icon);
        viewHolder.textView_Content.setText(messageDomain.getContent());
        if (messageDomain.getExtend()!=null) {
            viewHolder.textView_Title.setText(messageDomain.getExtend().getTitle());
        }
        viewHolder.textView_PubTime.setText(FormatTiem.getFormatDate(messageDomain.getPubtime()));
        viewHolder.textView_UserName.setText(messageDomain.getFromUserNick());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.circleImageView_icon)
        CircleImageView circleImageView_Icon;
        @BindView(R.id.textView_userName)
        TextView textView_UserName;
        @BindView(R.id.textView_content)
        TextView textView_Content;
        @BindView(R.id.textView_title)
        TextView textView_Title;
        @BindView(R.id.textView_pubTime)
        TextView textView_PubTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
