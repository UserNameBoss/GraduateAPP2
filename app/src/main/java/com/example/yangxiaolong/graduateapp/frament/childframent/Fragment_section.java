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
public class Fragment_section extends Fragment {


    private String path="http://xb.huabao.me/?json=gender/category_article_list_new_v2";
    private String pathKey;
    private PullToRefreshListView pullToRefreshListView;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private NetWorkListUserContent netWorkListUserContent;
    private List<ListUserContent> data;
    private MyListViewAdapter_Text myListViewAdapter_text;

    long timeMs= GETCurrentTime.getTimeMS();
    public Fragment_section(){

        System.out.println("========timeMs="+timeMs);
        pathKey="sign=A979A5570474628FD77728B618CC9533E&timestamp="+timeMs+"&afterDate="+(timeMs-100000)+"&v=2140&categoryId=1";

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_section, container, false);
        pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.childListView_hot);

        getResultCallback=(new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {

            if(data==null) {
                data = JsonToDomain.getData(message);
                System.out.println("=================data.size=" + data.size());
                myListViewAdapter_text=new MyListViewAdapter_Text(getContext(), data, 1);
                pullToRefreshListView.setAdapter(myListViewAdapter_text);
            }else{
                data=JsonToDomain.getData(message);
                System.out.println("==========================image.OnCreagetView.data===!null================"+data.size());
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

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                long afterDate=timeMs-100000;
                pathKey="sign=2B4DFF8138A3DF04F6240D62FA2D9977&timestamp="+timeMs+"&afterDate="+afterDate+"&v=2140&categoryId=1";
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


}
