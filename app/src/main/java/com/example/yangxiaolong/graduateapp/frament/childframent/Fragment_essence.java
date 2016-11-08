package com.example.yangxiaolong.graduateapp.frament.childframent;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
public class Fragment_essence extends Fragment {

    private String path="http://xb.huabao.me/?json=gender/category_article_list_new_v2";
    private String pathKey;
    private PullToRefreshListView pullToRefreshListView;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private NetWorkListUserContent netWorkListUserContent;
    private List<ListUserContent> data;
    String afterDate="1478077419527";
    long timeMs= GETCurrentTime.getTimeMS();
    MyListViewAdapter_Text myListViewAdapter_text;

    public Fragment_essence(){


        pathKey="sign=07635449DA61CD271D8B48275D4CBC3A&timestamp="+timeMs+"&afterDate="+afterDate+"&v=2140&allowRandom=1&categoryId=32";

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_essence, container, false);
        pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.childListView_hot);
        View view1=inflater.inflate(R.layout.empthy_background,null);
        pullToRefreshListView.setEmptyView(view1);


        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                long afterDate=timeMs-100000;
                long timeMs= GETCurrentTime.getTimeMS();
                pathKey="sign=0376A8C9611D62CE51195EF118723F6F&timestamp="+timeMs+"&afterDate="+afterDate+"&v=2140&allowRandom=1&categoryId=32";
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
        System.out.println("============================essence.onStart===================");
        getResultCallback=(new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                if(data==null) {
                    data = JsonToDomain.getData(message);
                    System.out.println("==================essence.data.size="+data.size());
                    myListViewAdapter_text = new MyListViewAdapter_Text(getContext(), data, 1);
                    pullToRefreshListView.setAdapter(myListViewAdapter_text);
                }else{
                    data=JsonToDomain.getData(message);
                    System.out.println("==========================essence.OnCreagetView.data===!null================"+data.size());
                    myListViewAdapter_text = new MyListViewAdapter_Text(getContext(), data, 1);
                    pullToRefreshListView.setAdapter(myListViewAdapter_text);
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
}
