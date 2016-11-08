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
public class MyComment_Fragment extends Fragment {

    private PullToRefreshListView pullToRefreshListView;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private List<ListUserContent> data;
    private MyListViewAdapter_Text myListViewAdapter_text;
    public MyComment_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_my_comment_, container, false);
        pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.pulltoRefreshListView_comment);
        getResultCallback=new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                 data= JsonToDomain.getDataUserContent(message);
                 myListViewAdapter_text=new MyListViewAdapter_Text(getActivity(),data,1);
                 pullToRefreshListView.setAdapter(myListViewAdapter_text);
            }
        };
        String path="http://xb.huabao.me/?json=gender/comment_list";
        String pathKey="sign=256AEA6B3B463E05CFF1C118F7A5F0AB&timestamp="+ GETCurrentTime.getTimeMS()+"&pageSize=20&v=2140&startId=2147483647&userId=2172827&fields=ArticleId%2CUserId%2CUserNick%2CUserIcon%2CPubtime%2CComment%2CTitle%2CContent%2CPic";
        NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);

        return view;
    }

}
