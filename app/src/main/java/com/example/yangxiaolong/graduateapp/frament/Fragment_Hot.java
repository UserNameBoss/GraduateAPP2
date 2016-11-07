package com.example.yangxiaolong.graduateapp.frament;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_activityThem;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_essence;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_hot;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_image;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_section;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_sound;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_tyrant;
import com.example.yangxiaolong.graduateapp.service.PlaySoundService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Hot extends Fragment implements View.OnClickListener {
    //标题控件数组
    private List<TextView> textViews_title;
    //图片线条数组
    private List<ImageView> imageViews_line;

    private LinearLayout linearLayout_title;
    private LinearLayout linearLayout_line;

    private MyFragementAdapater adapater;

//声明旧的索引
    private int oldPosition;

    private ViewPager viewPager;

    private List<Fragment> fragments;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter=new IntentFilter("audio");

    private PlaySoundService soundService;
    private int preFragment;

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            soundService= (PlaySoundService) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public Fragment_Hot() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frament__hot, container, false);

        localBroadcastManager= LocalBroadcastManager.getInstance(getActivity());
        localBroadcastManager.registerReceiver(new PathBroadcastReceiver(),intentFilter);

        this.linearLayout_title=(LinearLayout)view.findViewById(R.id.linearLayout_title);
        this.linearLayout_line=(LinearLayout)view.findViewById(R.id.linearLayout_line);
        this.viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        this.textViews_title=new ArrayList<>();
        this.imageViews_line=new ArrayList<>();
        this.fragments=new ArrayList<>();
        getRest();

        this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TextView textView_title=textViews_title.get(position);
                textView_title.setTextColor(Color.RED);
                ImageView imageView_line=imageViews_line.get(position);
                imageView_line.setBackgroundColor(Color.RED);

                textViews_title.get(oldPosition).setTextColor(Color.GRAY);
                imageViews_line.get(oldPosition).setBackgroundColor(Color.TRANSPARENT);

                oldPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        int count_line=this.linearLayout_line.getChildCount();
        for(int i=0;i<count_line;i++){
            ImageView imageView=(ImageView)linearLayout_line.getChildAt(i);
            imageView.setId(i);
            this.imageViews_line.add(imageView);
        }



        int count_title=this.linearLayout_title.getChildCount();
        for(int i=0;i<count_title;i++){
            TextView textView=(TextView) linearLayout_title.getChildAt(i);
            textView.setId(i);
            textView.setOnClickListener(this);
            this.textViews_title.add(textView);
        }
        return view;
    }

    private void getRest() {
        Fragment fragment_hot=new Fragment_hot();
        Fragment fragment_essence=new Fragment_essence();
        Fragment fragment_image=new Fragment_image();
        Fragment fragment_section=new Fragment_section();
        Fragment fragment_sound=new Fragment_sound();
        Fragment fragment_tyrant=new Fragment_tyrant();
        Fragment fragment_activityTheme=new Fragment_activityThem();

        this.fragments.add(fragment_hot);
        this.fragments.add(fragment_essence);
        this.fragments.add(fragment_image);
        this.fragments.add(fragment_section);
        this.fragments.add(fragment_sound);
        this.fragments.add(fragment_tyrant);
        this.fragments.add(fragment_activityTheme);

        //设置适配器
        this.adapater=new MyFragementAdapater(getFragmentManager());
        this.viewPager.setAdapter(adapater);
    }

    @Override
    public void onClick(View view) {
        int position=view.getId();
        this.viewPager.setCurrentItem(position);
    }

    private class MyFragementAdapater extends FragmentPagerAdapter {

        public MyFragementAdapater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        oldPosition=0;
        getActivity().unbindService(serviceConnection);
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent=new Intent(getActivity(), PlaySoundService.class);
        getActivity().bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
        System.out.println("==============HOT.Service============");
        getRest();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    class PathBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String path=intent.getStringExtra("path");
            System.out.println("===========path="+path);

        }
    }
}
