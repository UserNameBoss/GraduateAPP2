package com.example.yangxiaolong.graduateapp.frament;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.yangxiaolong.graduateapp.R;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_find extends Fragment implements OnClickListener{
    private ViewPager viewPager_find_imgs;
    private View view;

    private List<Integer> data_img;
    private LinearLayout linearLayout_point;
    private List<ImageView> imageViews_point;
    private List<ImageView> imageViews;
    private ScrollView scrollView;

    private MyAdapater adapater;

    private int oldPosition;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view=inflater.inflate(R.layout.fragment_find,container,false);

        initView();

        imageViews_point=new ArrayList<>();
        final int pointCount= this.linearLayout_point.getChildCount();
        for(int i=0;i<pointCount;i++){
            ImageView imageView=(ImageView) linearLayout_point.getChildAt(i);
            imageView.setId(i);
            imageView.setOnClickListener(this);
            imageViews_point.add(imageView);

        }

        this.data_img=getData();

        imageViews=new ArrayList<>();
        for(int i=0;i<data_img.size();i++){
            ImageView imageView=new ImageView(getActivity());
            imageView.setImageResource(data_img.get(i));
            imageViews.add(imageView);
        }

        this.adapater=new MyAdapater();
        this.viewPager_find_imgs.setAdapter(adapater);
        this.viewPager_find_imgs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 ImageView imageView=imageViews_point.get(position);
                 imageView.setImageResource(R.drawable.night_icon_pager_0);
                 ImageView oldimageView=imageViews_point.get(oldPosition);
                 oldimageView.setImageResource(R.drawable.night_icon_pager_1);
                 oldPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            this.scrollView.scrollTo(0,0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initView(){
        this.viewPager_find_imgs=(ViewPager)view.findViewById(R.id.viewPager_find);
        this.linearLayout_point=(LinearLayout)view.findViewById(R.id.linearLayout_point);
        this.scrollView=(ScrollView) view.findViewById(R.id.scrollView_find);
    }


    private List getData(){
        List<Integer> data_img=new ArrayList<>();
        for(int i=0;i<imageViews_point.size();i++){
           data_img.add(R.mipmap.ic_launcher);
        }
        return data_img;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        this.viewPager_find_imgs.setCurrentItem(id);
    }

    private class MyAdapater extends PagerAdapter{



         @Override
         public int getCount() {
             return imageViews.size();
         }

         @Override
         public Object instantiateItem(ViewGroup container, int position) {
             ImageView imageView=imageViews.get(position);
             container.addView(imageView);
             return imageView;
         }

         @Override
         public void destroyItem(ViewGroup container, int position, Object object) {
             if(object instanceof View){
                 container.removeView((View) object);
             }
         }

         @Override
         public boolean isViewFromObject(View view, Object object) {
             return object==view;
         }
     }
}
