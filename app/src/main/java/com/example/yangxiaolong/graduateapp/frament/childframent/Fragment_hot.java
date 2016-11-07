package com.example.yangxiaolong.graduateapp.frament.childframent;


import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.ListUserContent;
import com.example.yangxiaolong.graduateapp.utils.GETCurrentTime;
import com.example.yangxiaolong.graduateapp.utils.JsonToDomain;
import com.example.yangxiaolong.graduateapp.utils.MyListViewAdapter_Text;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_hot extends Fragment {

    private String path="http://xb.huabao.me/?json=gender/category_article_list_new_v2";
    private String pathKey;
    private PullToRefreshListView pullToRefreshListView;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private NetWorkListUserContent netWorkListUserContent;
    private List<ListUserContent> data;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter=new IntentFilter("Progress");
    private TextView textView_current;
    String afterDate="1478003200000";
    private MyListViewAdapter_Text myListViewAdapter_text;
    long timeMs;

    public Fragment_hot(){
        timeMs= GETCurrentTime.getTimeMS();
        System.out.println("========timeMs="+timeMs);
        pathKey="sign=E1A113DE5E51C7AE2145302A9C9CEDF4&timestamp="+timeMs+"&afterDate="+afterDate+"&v=2140&allowRandom=1";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hot02, container, false);
        pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.childListView_hot);
        System.out.println("=====================hot.onCreateView====================");


        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                long time=GETCurrentTime.getTimeMS();
                long after=timeMs-200000;
                pathKey="sign=1537FFCEDEC509CD0670A793D97C699D&timestamp="+time+"&afterDate="+after+"&v=2140&allowRandom=1";
                NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("========点击条目--hot============");
                myListViewAdapter_text.onClick(view.findViewById(R.id.imageButton_comment));

            }
        });
        return view;
    }




    @Override
    public void onStart() {
        super.onStart();
        System.out.println("===============hot.onStart================");
        getResultCallback=(new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                System.out.println("=====================hot.Message=================");
                if(data==null) {
                    data = JsonToDomain.getData(message);
                    myListViewAdapter_text=new MyListViewAdapter_Text(getContext(), data, 1);
                    pullToRefreshListView.setAdapter(myListViewAdapter_text);
                }else {
                    data=JsonToDomain.getData(message);
                    System.out.println("==========================hot.OnCreagetView.data===!null================"+data.size());
                    if(getContext()!=null) {
                        myListViewAdapter_text = new MyListViewAdapter_Text(getContext(), data, 1);
                        pullToRefreshListView.setAdapter(myListViewAdapter_text);
                    }
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
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("=================hot.OnPersume=============");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("=====================hot.onPause===============");
    }
}
