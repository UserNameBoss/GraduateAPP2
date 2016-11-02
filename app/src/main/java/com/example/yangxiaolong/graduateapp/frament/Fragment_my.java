package com.example.yangxiaolong.graduateapp.frament;


import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.yangxiaolong.graduateapp.MyView.ObservableScrollView;
import com.example.yangxiaolong.graduateapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_my extends Fragment {

     private ObservableScrollView scrollView_my;
     private LinearLayout linearLayout;
    public Fragment_my() {
        // Required empty public constructor
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_my, container, false);
        this.scrollView_my=(ObservableScrollView) view.findViewById(R.id.scrollView_my);
        this.linearLayout=(LinearLayout)view.findViewById(R.id.linearLayout_my_titleBar);
        this.scrollView_my.setScrollListener(new ObservableScrollView.ScrollListener() {
            @Override
            public void scrollOritention(int oritention) {
                if(oritention==ObservableScrollView.SCROLL_DOWN){
                    linearLayout.setBackgroundColor(Color.TRANSPARENT);
                }
                else{
                    linearLayout.setBackgroundColor(Color.RED);
                }
            }
        });
        return view;
    }
    private void initView(){

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&this.scrollView_my!=null){
            this.scrollView_my.scrollTo(0,0);
        }
    }

}
