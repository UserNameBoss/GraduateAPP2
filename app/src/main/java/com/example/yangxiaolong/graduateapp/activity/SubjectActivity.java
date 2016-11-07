package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.ActivityThemDomain;
import com.example.yangxiaolong.graduateapp.utils.JsonToDomain;
import com.example.yangxiaolong.graduateapp.utils.MyAdapterThem;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubjectActivity extends Activity {
    private PullToRefreshListView pullToRefreshListView;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private List<ActivityThemDomain> data;
    private ListView listView;
    private String path="http://xb.huabao.me/?json=gender/get_subject_list";
    private List<String> listBanner;
    private int prePosition;
    private boolean isRunThread;
    private int preId;
    private int wedth;
    private int height;

    private String pathKey="sign=4216E0F5617E4EB1886462E81F813B74&timestamp=1478163052272&pageSize=20&startTime=9223372036854775807&v=2140&userId=2172827";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_subject);
        ButterKnife.bind(this);

        pullToRefreshListView= (PullToRefreshListView)findViewById(R.id.listView_subject);
        getResultCallback=(new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {
                data= JsonToDomain.getListThemDomain(message);
                System.out.println("=================data.size="+data.size());
                MyAdapterThem myAdapterThem=new MyAdapterThem(SubjectActivity.this,data);
                pullToRefreshListView.setAdapter(myAdapterThem);
            }
        });

        NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);

        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               TextView textView_title= (TextView) view.findViewById(R.id.textView_title);
                String title=textView_title.getText().toString().trim();
                Intent intent=new Intent();
                intent.setClass(SubjectActivity.this,SubjectItemActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

    }







    @OnClick({R.id.imageView_back, R.id.imageView_refresh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_back:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_refresh:

                break;
        }
    }
}
