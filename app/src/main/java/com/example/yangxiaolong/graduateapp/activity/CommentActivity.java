package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.MyView.CircleImageView;
import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.domain.CommentPerson;
import com.example.yangxiaolong.graduateapp.utils.FormatTiem;
import com.example.yangxiaolong.graduateapp.utils.JsonToDomain;
import com.example.yangxiaolong.graduateapp.utils.MyCommentAdapter;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends Activity implements View.OnClickListener {
    private String path;
    private String pathKey;
    private NetWorkListUserContent.GetResultCallback getResultCallback;
    private List<CommentPerson> data;
    private PullToRefreshListView pullToRefreshListView;
    private ImageView image_comment_writeIcon;
    private TextView text_writeComment_text;
    private Intent intent;
    private LayoutInflater layoutInflater;
    private Bundle bundle;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        image_comment_writeIcon= (ImageView) findViewById(R.id.image_writeComment_writeIcon);
        image_comment_writeIcon.setOnClickListener(this);
        text_writeComment_text= (TextView) findViewById(R.id.text_writeCommetn_text);
        text_writeComment_text.setOnClickListener(this);
        layoutInflater=LayoutInflater.from(this);
        intent=getIntent();
        path=intent.getStringExtra("path");
        pathKey=intent.getStringExtra("pathKey");
        bundle=intent.getExtras();
        pullToRefreshListView= (PullToRefreshListView) findViewById(R.id.listView_comment);
        getResultCallback=(new NetWorkListUserContent.GetResultCallback() {
            @Override
            public void getMessage(String message) {

                data= JsonToDomain.getJsonToDomain(message);
                System.out.println("=================data.size="+data.size());
                pullToRefreshListView.setAdapter(new MyCommentAdapter(getApplicationContext(),data));
                listView=pullToRefreshListView.getRefreshableView();
                View view=getHead();
                listView.addHeaderView(view);
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
                intent.putExtra("articleId",bundle.getInt("articleId"));
                this.startActivity(intent);
                overridePendingTransition(R.anim.write_anim,R.anim.comment_anim);
                break;
            //播放
            case R.id.imageView_play:

                break;
        }
    }
    public View getHead(){
        View view=layoutInflater.inflate(R.layout.item_01,null);
        ViewHolder viewHolder=new ViewHolder(view);
        if (bundle.getInt("categoryId")== 29) {
            viewHolder.textView_hits.setText(String.valueOf(bundle.getInt("hits"))+"次播放");

            if(bundle.containsKey("url")) {
                int height = bundle.getInt("height");
                String url = bundle.getString("url");
                int width = bundle.getInt("width");
                Picasso.with(this).load(url).resize(width, height).centerCrop().into(viewHolder.imageView_content);

            }else{
                viewHolder.imageView_content.setImageResource(R.mipmap.ic_launcher);
                viewHolder.imageView_play.setOnClickListener(this);
            }
            viewHolder.textView_title.setText(bundle.getString("title"));
            viewHolder.textView_title.setVisibility(View.VISIBLE);
            viewHolder.imageView_content.setVisibility(View.VISIBLE);
            viewHolder.textView_content.setVisibility(View.GONE);
            viewHolder.frameLayout_playSound.setVisibility(View.VISIBLE);
            viewHolder.textView_currentTime.setText(FormatTiem.formatTime(bundle.getInt("duration")*1000));
            viewHolder.textView_audioPath.setText(String.valueOf(bundle.getString("audio")));
            viewHolder.progressBar.setMax(bundle.getInt("duration")*1000);
        }else{
            if (!bundle.containsKey("url")) {
                viewHolder.textView_content.setText(bundle.getString("content"));
                viewHolder.textView_content.setVisibility(View.VISIBLE);
                viewHolder.textView_title.setVisibility(View.GONE);
                viewHolder.imageView_content.setVisibility(View.GONE);
                viewHolder.frameLayout_playSound.setVisibility(View.GONE);
            } else {
                int height = bundle.getInt("height");
                String url = bundle.getString("url");
                int width = bundle.getInt("width");
                System.out.println("=================评论=========URl="+url);
                Picasso.with(this).load(url).resize(width, height).centerCrop().into(viewHolder.imageView_content);
                viewHolder.textView_title.setText(bundle.getString("title"));
                viewHolder.textView_title.setVisibility(View.VISIBLE);
                viewHolder.imageView_content.setVisibility(View.VISIBLE);
                viewHolder.textView_content.setVisibility(View.GONE);
                viewHolder.frameLayout_playSound.setVisibility(View.GONE);
            }
        }

        Picasso.with(this).load(bundle.getString("userNameIcon")).into(viewHolder.circleImageView_Img);
        viewHolder.textView_name.setText(bundle.getString("userNick"));
        viewHolder.textView_commentCount.setText(String.valueOf(bundle.getInt("comments")));
        viewHolder.textView_goodCount.setText(String.valueOf(bundle.getInt("goods")));
        viewHolder.textView_favoriteCount.setText(String.valueOf(bundle.getInt("favorites")));
        viewHolder.textView_shareCount.setText(String.valueOf(bundle.get("shares")));
        viewHolder.imageButton_good.setOnClickListener(this);
        viewHolder.imageButton_favorite.setOnClickListener(this);
        if(bundle.getBoolean("isGoods")){
            viewHolder.imageButton_good.setImageResource(R.drawable.btn_good_1);
        }
        if(bundle.getBoolean("isisFavorites")){
            viewHolder.imageButton_favorite.setImageResource(R.drawable.btn_favorite_1);
        }

        if(bundle.getInt("flag")==2){
            viewHolder.textView_type.setText("#"+"霸友秀"+"#");
            viewHolder.textView_type.setVisibility(View.VISIBLE);
            viewHolder.textView_type.setTextColor(Color.RED);
        }
        viewHolder.imageButton_comment.setOnClickListener(this);
        viewHolder.textView_articleId.setText(String.valueOf(bundle.getInt("articleId")));
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.circleImageView_img)
        CircleImageView circleImageView_Img;
        @BindView(R.id.textView_name)
        TextView textView_name;
        @BindView(R.id.imageView_msg)
        ImageView imageView_msg;
        @BindView(R.id.textView_content)
        TextView textView_content;
        @BindView(R.id.imageButton_good)
        ImageButton imageButton_good;
        @BindView(R.id.textView_goodCount)
        TextView textView_goodCount;
        @BindView(R.id.imageButton_comment)
        ImageButton imageButton_comment;
        @BindView(R.id.textView_commentCount)
        TextView textView_commentCount;
        @BindView(R.id.imageButton_share)
        ImageButton imageButton_share;
        @BindView(R.id.textView_shareCount)
        TextView textView_shareCount;
        @BindView(R.id.imageButton_favorite)
        ImageButton imageButton_favorite;
        @BindView(R.id.textView_favoriteCount)
        TextView textView_favoriteCount;
        @BindView(R.id.imageView_content)
        ImageView imageView_content;
        @BindView(R.id.textVIew_title)
        TextView textView_title;
        @BindView(R.id.frameLayout_playSound)
        FrameLayout frameLayout_playSound;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;
        @BindView(R.id.imageView_play)
        CheckBox imageView_play;
        @BindView(R.id.textView_current)
        TextView textView_currentTime;
        @BindView(R.id.textView_hits)
        TextView textView_hits;
        @BindView(R.id.textVeiw_audioPath)
        TextView textView_audioPath;
        @BindView(R.id.textView_type)
        TextView textView_type;
        @BindView(R.id.textView_articleId)
        TextView textView_articleId;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
