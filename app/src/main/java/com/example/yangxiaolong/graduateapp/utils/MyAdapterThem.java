package com.example.yangxiaolong.graduateapp.utils;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.ActivityThemDomain;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangxiaolong on 2016/11/3.
 */
public class MyAdapterThem extends BaseAdapter {

    private List<ActivityThemDomain> data;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyAdapterThem(Context context, List<ActivityThemDomain> data) {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
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

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activitythem_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ActivityThemDomain activityThemDomain=data.get(position);
        Picasso.with(context).load(activityThemDomain.getIcon()).into(viewHolder.imageView_Icon);
        viewHolder.textView_Members.setText(String.valueOf(activityThemDomain.getMembers()));
        viewHolder.textView_Title.setText(String.valueOf(activityThemDomain.getName()));
        if(SystemClock.currentThreadTimeMillis()>activityThemDomain.getEndTime()){
            viewHolder.textView_Start.setText("已结束");
        }else if (SystemClock.currentThreadTimeMillis()<activityThemDomain.getBeginTime()&&SystemClock.currentThreadTimeMillis()>activityThemDomain.getPubtime()){
            viewHolder.textView_Start.setText("报名中");
        }else if(SystemClock.currentThreadTimeMillis()>activityThemDomain.getBeginTime()&&SystemClock.currentThreadTimeMillis()<activityThemDomain.getEndTime()){
            viewHolder.textView_Start.setText("活动中");
        }
        viewHolder.textView_Time.setText(FormatTiem.getFormatDate(activityThemDomain.getEndTime()));

        return convertView;
    }




   static class ViewHolder {
        @BindView(R.id.imageView_icon)
        ImageView imageView_Icon;
        @BindView(R.id.textView_title)
        TextView textView_Title;
        @BindView(R.id.textView_Members)
        TextView textView_Members;
        @BindView(R.id.textView_time)
        TextView textView_Time;
        @BindView(R.id.textView_start)
        TextView textView_Start;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public List<String> getImageContent(){
        List<String> listImage=new ArrayList<>();
        for(int i=0;i<3;i++){
            listImage.add(data.get(i).getBanner());
        }
        return listImage;
    }

    public void getNoti(List<ActivityThemDomain> data){
        this.data=data;
        notifyDataSetChanged();
    }
}
