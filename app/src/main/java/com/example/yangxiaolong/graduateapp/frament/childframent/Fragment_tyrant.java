package com.example.yangxiaolong.graduateapp.frament.childframent;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.yangxiaolong.graduateapp.MyView.CircleImageView;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.ListUserContent;
import com.example.yangxiaolong.graduateapp.utils.FormatTiem;
import com.example.yangxiaolong.graduateapp.utils.GETCurrentTime;
import com.example.yangxiaolong.graduateapp.utils.JsonToDomain;
import com.example.yangxiaolong.graduateapp.utils.MyListViewAdapter_Text;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_tyrant extends Fragment {
    private PullToRefreshListView pullToRefreshListView;
    private LinearLayout linearLayout_head;
    private LinearLayout linearLayout_head_icon;
    private CircleImageView circleImageView;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private List<ListUserContent> data;
    private String path = "http://xb.huabao.me/?json=gender/category_article_list";
    private String pathKey = "sign=40C920905CE56CC789ED9D474B08B0E1&timestamp=1478159671873&lastTime=1478159459000&v=2140&beforeDate=2016-11-05&subject=%E9%9C%B8%E5%8F%8B%E7%A7%80&fields=ArticleId%2CTitle%2CUserId%2CUserNick%2CUserIcon%2CContent%2CPic%2CPubtime%2CHits%2CGoods%2CShares%2CComments";
    private ListView listView;
    MyListViewAdapter_Text myListViewAdapter_text;
    public Fragment_tyrant() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("=======================================ONCreateVeiw=====================");
        View view = inflater.inflate(R.layout.fragment_tyrant, container, false);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.childListView_tyrant);
        View view1=inflater.inflate(R.layout.empthy_background,null);
        pullToRefreshListView.setEmptyView(view1);

        getResultCallback = (new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                System.out.println("=============================tyrant.ONCreagetView.message============="+message);
                if(data==null) {
                    System.out.println("==========================tyrant.OnCreagetView.data===null================");
                    data = JsonToDomain.getData(message);
                    myListViewAdapter_text = new MyListViewAdapter_Text(getContext(), data, 2);
                    pullToRefreshListView.setAdapter(myListViewAdapter_text);


                List<String> icon = myListViewAdapter_text.getPathIcon();
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View view1 = layoutInflater.inflate(R.layout.tyrant_head, null);
                getHead(view1,icon);
                listView = pullToRefreshListView.getRefreshableView();
                listView.addHeaderView(view1);
                }else{
                    data=JsonToDomain.getData(message);
                    System.out.println("==========================tyrant.OnCreagetView.data===!null================"+data.size());
                    myListViewAdapter_text = new MyListViewAdapter_Text(getContext(), data, 2);
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
        NetWorkListUserContent.getPostResult(path, pathKey, getResultCallback);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                System.out.println("===================YEAR="+ FormatTiem.getFormatDateYear(GETCurrentTime.getTimeMS()));
                String pathKey = "sign=40C920905CE56CC789ED9D474B08B0E1&timestamp="+GETCurrentTime.getTimeMS()+"&lastTime=1478159459000&v=2140&beforeDate=20"+FormatTiem.getFormatDateYear(GETCurrentTime.getTimeMS())+"&subject=%E9%9C%B8%E5%8F%8B%E7%A7%80&fields=ArticleId%2CTitle%2CUserId%2CUserNick%2CUserIcon%2CContent%2CPic%2CPubtime%2CHits%2CGoods%2CShares%2CComments";
                NetWorkListUserContent.getPostResult(path, pathKey, getResultCallback);
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

    }

    public void getHead(View view, List<String> list) {
//        linearLayout_head=new LinearLayout(getContext());
//        linearLayout_head.setOrientation(LinearLayout.VERTICAL);
//        linearLayout_head_icon=new LinearLayout(getContext());
//        AbsListView.LayoutParams params=new AbsListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout_head_icon.setOrientation(LinearLayout.HORIZONTAL);
//        linearLayout_head_icon.setLayoutParams(params);
//        linearLayout_head.setLayoutParams(params);
        AbsListView.LayoutParams params2=new AbsListView.LayoutParams(60,60);
        LinearLayout linearLayout_icon = (LinearLayout) view.findViewById(R.id.linearLayout_heard);
        for (int i = 0; i < 15; i++) {
            circleImageView = new CircleImageView(getContext());
            circleImageView.setLayoutParams(params2);
            Picasso.with(getContext()).load(list.get(i)).into(circleImageView);
            circleImageView.setImageResource(R.mipmap.ic_launcher);
            linearLayout_icon.addView(circleImageView);
        }
    }
}
