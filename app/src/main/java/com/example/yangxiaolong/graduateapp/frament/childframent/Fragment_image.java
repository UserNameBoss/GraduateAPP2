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
public class Fragment_image extends Fragment {

    private String path="http://xb.huabao.me/?json=gender/category_article_list_new_v2";
    private String pathKey;
    private PullToRefreshListView pullToRefreshListView;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private NetWorkListUserContent netWorkListUserContent;
    private List<ListUserContent> data;
    String afterDate="1477991848000";
    long timeMs= GETCurrentTime.getTimeMS();
    MyListViewAdapter_Text myListViewAdapter_text;

    public Fragment_image(){

        System.out.println("========timeMs="+timeMs);
        pathKey="sign=353EEFB26AB52C965AF01136977C18CF&timestamp="+timeMs+"&afterDate="+afterDate+"&v=2140&allowRandom=1&categoryId=16";

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image, container, false);

        pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.childListView_hot);
        View view1=inflater.inflate(R.layout.empthy_background,null);
        pullToRefreshListView.setEmptyView(view1);

        getResultCallback=(new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                if(data==null) {
                    data = JsonToDomain.getData(message);
                    System.out.println("=================image.data.size=" + data.size());
                    if(getContext()!=null) {
                        myListViewAdapter_text = new MyListViewAdapter_Text(getContext(), data, 1);
                        pullToRefreshListView.setAdapter(myListViewAdapter_text);
                    }
                }else{
                    data=JsonToDomain.getData(message);
                    System.out.println("==========================image.OnCreagetView.data===!null================"+data.size());
                    System.out.println("============================image.context"+getActivity());
                    if(getActivity()!=null) {
                        myListViewAdapter_text = new MyListViewAdapter_Text(getActivity(), data, 1);
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
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                long afterDate=timeMs-100000;
                pathKey="sign=D2EC4ADEA4455C5F4FB833C183DF419A&timestamp="+timeMs+"&afterDate="+afterDate+"&v=2140&categoryId=16";
                NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);
                System.out.println("=============================下拉====================");
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
        System.out.println("=====================image.onStart=========================");

    }
}
