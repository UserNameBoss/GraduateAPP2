package com.example.yangxiaolong.graduateapp.frament.commentcollectfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.ListUserContent;
import com.example.yangxiaolong.graduateapp.utils.GETCurrentTime;
import com.example.yangxiaolong.graduateapp.utils.JsonToDomain;
import com.example.yangxiaolong.graduateapp.utils.MyListViewAdapter_Text;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCollect_Fragment extends Fragment {


    private PullToRefreshListView pullToRefreshListView;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private List<ListUserContent> data;
    private MyListViewAdapter_Text myListViewAdapter_text;

    public MyCollect_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_my_collect, container, false);
       pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.pullToRefreshListView_collect);
        getResultCallback=new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                data= JsonToDomain.getDataUserContent(message);
                myListViewAdapter_text=new MyListViewAdapter_Text(getActivity(),data,1);
                pullToRefreshListView.setAdapter(myListViewAdapter_text);
            }
        };

        String path="http://xb.huabao.me/?json=gender/get_favorite_list";
        String pathKey="sign=D6B6B764CB7BFE90A5D0E126D62A19D7&timestamp="+ GETCurrentTime.getTimeMS()+"&pageSize=20&v=2140&startId=2147483647&userId=2172827&fields=ArticleId%2CTitle%2CUserId%2CUserNick%2CUserIcon%2CContent%2CPic%2CPubtime%2CHits%2CGoods%2CShares%2CComments";
        NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);
        return view;
    }

}
