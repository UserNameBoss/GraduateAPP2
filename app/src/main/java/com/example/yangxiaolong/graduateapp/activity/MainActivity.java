package com.example.yangxiaolong.graduateapp.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.frament.Fragment_Hot;
import com.example.yangxiaolong.graduateapp.frament.Fragment_find;
import com.example.yangxiaolong.graduateapp.frament.Fragment_message;
import com.example.yangxiaolong.graduateapp.frament.Fragment_my;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_activityThem;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_essence;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_hot;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_image;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_section;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_sound;
import com.example.yangxiaolong.graduateapp.frament.childframent.Fragment_tyrant;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private LinearLayout linearLayout_hot,linearLayout_find,linearLayout_messige,linearLayout_user;
    private ImageView imageView_hot,imageView_find,imageView_message,imageView_user;
    private TextView textView_hot,textView_find,textView_message,textView_user;
    private ImageView imageView_hot_line,imageView_find_line,imageView_message_line,imageView_user_line;
    private int preId;
    private Fragment[] fragments={new Fragment_Hot(),new Fragment_find(),new Fragment_message(),new Fragment_my()};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewPager= (ViewPager) findViewById(R.id.activity_viewPager);
        this.linearLayout_hot= (LinearLayout) findViewById(R.id.linearLayout_hot);
        linearLayout_hot.setOnClickListener(this);
        this.linearLayout_find= (LinearLayout) findViewById(R.id.linearLayout_find);
        linearLayout_find.setOnClickListener(this);
        this.linearLayout_messige= (LinearLayout) findViewById(R.id.linearLayout_messige);
        linearLayout_messige.setOnClickListener(this);
        this.linearLayout_user= (LinearLayout) findViewById(R.id.linearLayout_user);
        linearLayout_user.setOnClickListener(this);
        preId=R.id.linearLayout_hot;
        this.imageView_hot= (ImageView) findViewById(R.id.imageView_hot);
        this.imageView_find= (ImageView) findViewById(R.id.imageView_find);
        this.imageView_message= (ImageView) findViewById(R.id.imageView_message);
        this.imageView_user= (ImageView) findViewById(R.id.imageView_user);

        this.textView_hot= (TextView) findViewById(R.id.textView_hot);
        this.textView_find= (TextView) findViewById(R.id.textView_find);
        this.textView_message= (TextView) findViewById(R.id.textView_message);
        this.textView_user= (TextView) findViewById(R.id.textView_user);
        this.imageView_hot_line= (ImageView) findViewById(R.id.imageView_hot_line);
        this.imageView_find_line= (ImageView) findViewById(R.id.imageView_find_line);
        this.imageView_message_line= (ImageView) findViewById(R.id.imageView_message_line);
        this.imageView_user_line= (ImageView) findViewById(R.id.imageView_my_line);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
    }




    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.linearLayout_hot:
                getBackGound0(preId);
                getBackGound1(id);
                preId=R.id.linearLayout_hot;
                viewPager.setCurrentItem(0);
                break;
            case R.id.linearLayout_find:
                getBackGound0(preId);
                getBackGound1(id);
                preId=R.id.linearLayout_find;
                viewPager.setCurrentItem(1);
                break;
            case R.id.linearLayout_messige:
                getBackGound0(preId);
                getBackGound1(id);
                preId=R.id.linearLayout_messige;
                viewPager.setCurrentItem(2);
                break;
            case R.id.linearLayout_user:
                getBackGound0(preId);
                getBackGound1(id);
                preId=R.id.linearLayout_user;
                viewPager.setCurrentItem(3);
                break;
        }
    }


    public void getBackGound1(int currentId){
        switch (currentId){
            case R.id.linearLayout_hot:
                textView_hot.setTextColor(Color.RED);
                imageView_hot.setImageResource(R.drawable.tab_xb_1);
                imageView_hot_line.setBackgroundColor(Color.RED);
                break;
            case R.id.linearLayout_find:
                textView_find.setTextColor(Color.RED);
                imageView_find.setImageResource(R.drawable.tab_discover_1);
                imageView_find_line.setBackgroundColor(Color.RED);
                break;
            case R.id.linearLayout_messige:
                textView_message.setTextColor(Color.RED);
                imageView_message.setImageResource(R.drawable.tab_message_1);
                imageView_message_line.setBackgroundColor(Color.RED);
                break;
            case R.id.linearLayout_user:
                textView_user.setTextSize(Color.RED);
                imageView_user.setImageResource(R.drawable.tab_user_center_1);
                imageView_user_line.setBackgroundColor(Color.RED);
                break;
        }
    }
    public void getBackGound0(int preId){
        switch (preId){
            case R.id.linearLayout_hot:
                textView_hot.setTextColor(Color.BLACK);
                imageView_hot.setImageResource(R.drawable.tab_xb_0);
                imageView_hot_line.setBackgroundColor(Color.WHITE);
                break;
            case R.id.linearLayout_find:
                textView_find.setTextColor(Color.BLACK);
                imageView_find.setImageResource(R.drawable.tab_discover_0);
                imageView_find_line.setBackgroundColor(Color.WHITE);
                break;
            case R.id.linearLayout_messige:
                textView_message.setTextColor(Color.BLACK);
                imageView_message.setImageResource(R.drawable.tab_message_0);
                imageView_message_line.setBackgroundColor(Color.WHITE);
                break;
            case R.id.linearLayout_user:
                textView_user.setTextSize(Color.BLACK);
                imageView_user.setImageResource(R.drawable.tab_user_center_0);
                imageView_user_line.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    class MyViewPagerAdapter extends FragmentStatePagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    /**
     * 点击add按钮进行投稿审稿
     * @param view
     */
    public void add(View view){
       // AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 自定义视图
       // View customView = getLayoutInflater().inflate(R.layout.custom_add_view, null);
        //builder.setView(customView);
        //builder.show();
        Dialog dialog = new Dialog(this,R.style.dialog);
        dialog.setContentView(R.layout.custom_add_view);
        dialog.setCancelable(true);
        dialog.show();
    }
}
