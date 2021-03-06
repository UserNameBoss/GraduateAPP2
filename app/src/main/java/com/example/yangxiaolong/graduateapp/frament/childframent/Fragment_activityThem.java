package com.example.yangxiaolong.graduateapp.frament.childframent;


import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.ActivityThemDomain;
import com.example.yangxiaolong.graduateapp.utils.JsonToDomain;
import com.example.yangxiaolong.graduateapp.utils.MyAdapterThem;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_activityThem extends Fragment {
    private PullToRefreshListView pullToRefreshListView;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private List<ActivityThemDomain> data;
    private ListView listView;
    private String path="http://xb.huabao.me/?json=gender/get_subject_list";
    private List<String> listBanner;
    private int prePosition;
    private ViewPager viewPager;
    private boolean isRunThread;
    private int preId;
    private int wedth;
    private int height;
    MyAdapterThem myAdapterThem;
    private String pathKey="sign=4216E0F5617E4EB1886462E81F813B74&timestamp=1478163052272&pageSize=20&startTime=9223372036854775807&v=2140&userId=2172827";
    public Fragment_activityThem() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_activity_them, container, false);
        pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.childListView_them);
        View view1=inflater.inflate(R.layout.empthy_background,null);
        pullToRefreshListView.setEmptyView(view1);

        getResultCallback=(new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                if(data==null) {
                    data = JsonToDomain.getListThemDomain(message);
                    System.out.println("=================data.size=" + data.size());
                    myAdapterThem= new MyAdapterThem(getContext(), data);
                    pullToRefreshListView.setAdapter(myAdapterThem);


                listView=pullToRefreshListView.getRefreshableView();
                LayoutInflater layoutInflater=LayoutInflater.from(getContext());
                final View view1=layoutInflater.inflate(R.layout.activitythem_head,null);
                //得到屏幕的高度
                DisplayMetrics displayMetrics=new DisplayMetrics();
                Activity activity =getActivity();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                height=displayMetrics.heightPixels;
                wedth=displayMetrics.widthPixels;

                view1.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height/4));
                listView.addHeaderView(view1);
                viewPager= (ViewPager) view1.findViewById(R.id.viewPager_them);
                listBanner=myAdapterThem.getImageContent();
                System.out.println("==========listBanner="+listBanner.size());
                viewPager.setAdapter(new MyPagerAdapter());
                final LinearLayout linearLayout= (LinearLayout) view1.findViewById(R.id.linearLayout_viewPager_icon);
                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        System.out.println("===================PageSElected==============");
                        ImageView imageView= (ImageView) linearLayout.getChildAt(position);
                        ImageView imageView1= (ImageView) linearLayout.getChildAt(preId);
                        imageView1.setImageResource(R.drawable.night_icon_pager_1);
                        imageView.setImageResource(R.drawable.night_icon_pager_0);
                        prePosition=preId=position;
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                }else{
                    data=JsonToDomain.getListThemDomain(message);
                    System.out.println("==========================tyrant.OnCreagetView.data===!null================"+data.size());
                    myAdapterThem = new MyAdapterThem(getContext(), data);
                    pullToRefreshListView.setAdapter(myAdapterThem);
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(1000);

                        if(getActivity()!=null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pullToRefreshListView.onRefreshComplete();
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);
        new MyThread().start();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pathKey="sign=12F677B3F83EF71F200503E5BA5A54C6&timestamp=1478330688640&pageSize=20&startTime=9223372036854775807&v=2140&userId=2172827";
                NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });



        return view;
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return listBanner==null?0:listBanner.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView_viewPager=new ImageView(getContext());
            imageView_viewPager.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView_viewPager.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(getContext()).load(listBanner.get(position)).into(imageView_viewPager);
            container.addView(imageView_viewPager);
            return imageView_viewPager;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }



    private class MyThread extends Thread{

        public void run() {
            if (!isRunThread) {
                prePosition=0;
                synchronized (getActivity()) {
                    while (!isRunThread) {
                        SystemClock.sleep(3000);
                        if (getActivity() != null) {
                            int i = prePosition + 1;
                            if(listBanner!=null) {
                                if (i == listBanner.size()) {
                                    prePosition = 0;
                                } else {
                                    prePosition++;
                                }
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (listBanner.size() > 0) {
                                            viewPager.setCurrentItem(prePosition);
                                            preId=prePosition;
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }

}


