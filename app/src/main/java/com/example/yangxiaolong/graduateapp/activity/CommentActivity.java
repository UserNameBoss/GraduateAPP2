package com.example.yangxiaolong.graduateapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.CommentPerson;
import com.example.yangxiaolong.graduateapp.utils.JsonToDomain;
import com.example.yangxiaolong.graduateapp.utils.MyCommentAdapter;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

public class CommentActivity extends FragmentActivity implements View.OnClickListener {
    private String path;
    private String pathKey;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private List<CommentPerson> data;
    private PullToRefreshListView pullToRefreshListView;
    private ImageView image_comment_writeIcon;
    private TextView text_writeComment_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        image_comment_writeIcon= (ImageView) findViewById(R.id.image_writeComment_writeIcon);
        image_comment_writeIcon.setOnClickListener(this);
        text_writeComment_text= (TextView) findViewById(R.id.text_writeCommetn_text);
        text_writeComment_text.setOnClickListener(this);
        Intent intent=getIntent();
        path=intent.getStringExtra("path");
        pathKey=intent.getStringExtra("pathKey");

        pullToRefreshListView= (PullToRefreshListView) findViewById(R.id.listView_comment);
        getResultCallback=(new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {

                data= JsonToDomain.getJsonToDomain(message);
                System.out.println("=================data.size="+data.size());
                pullToRefreshListView.setAdapter(new MyCommentAdapter(getApplicationContext(),data));
            }
        });

        NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.image_writeComment_writeIcon:

                break;
            case R.id.text_writeCommetn_text:
                Intent intent=new Intent(getApplicationContext(),WriteCommentActivity.class);
                this.startActivity(intent);
                overridePendingTransition(R.anim.write_anim,R.anim.comment_anim);
                break;

        }
    }
}
