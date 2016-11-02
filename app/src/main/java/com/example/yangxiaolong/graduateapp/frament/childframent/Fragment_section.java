package com.example.yangxiaolong.graduateapp.frament.childframent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    String afterDate="1478078888000";

    public Fragment_section(){
        long timeMs= GETCurrentTime.getTimeMS();
        System.out.println("========timeMs="+timeMs);
        pathKey="sign=FFF9F22C348A8973CAD"+timeMs+"&timestamp="+timeMs+"&afterDate="+afterDate+"&v=2140&categoryId=1";

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_section, container, false);
        pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.childListView_hot);

        getResultCallback=(new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {

                System.out.println("=================message="+message);
                data= JsonToDomain.getData(message);
                System.out.println("=================data.size="+data.size());
                pullToRefreshListView.setAdapter(new MyListViewAdapter_Text(getContext(),data));
            }
        });

        NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                long timeMs= GETCurrentTime.getTimeMS();
                int a=78888000+10000000;
                afterDate=afterDate.substring(5)+a;
                pathKey="sign=FFF9F22C348A8973CAD"+timeMs+"&timestamp="+timeMs+"&afterDate="+afterDate+"&v=2140&categoryId=1";
                NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        return view;
    }


}
