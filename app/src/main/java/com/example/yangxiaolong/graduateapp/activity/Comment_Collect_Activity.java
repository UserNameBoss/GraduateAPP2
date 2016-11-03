package com.example.yangxiaolong.graduateapp.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.frament.commentcollectfragment.MyCollect_Fragment;
import com.example.yangxiaolong.graduateapp.frament.commentcollectfragment.MyComment_Fragment;

import java.util.ArrayList;
import java.util.List;

public class Comment_Collect_Activity extends AppCompatActivity  implements View.OnClickListener {
    private ViewPager ViewPager_Comment_Collect;
    private TextView TextView_MyComment,TextView_MyCollect;
    private ImageView ImageView_MyComment,ImageView_MyCollect;
    private Fragment[] fragment={new MyComment_Fragment(),new MyCollect_Fragment()};
    private List<TextView> textViews_title;
    private List<ImageView> imageViews_title;
    private  int oldPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment__collect);
         init();

    }

    private void init() {
        this.ViewPager_Comment_Collect= (ViewPager) this.findViewById(R.id.ViewPager_Comment_Collect);
        this.TextView_MyComment= (TextView) this.findViewById(R.id.TextView_MyComment);
        this.TextView_MyCollect= (TextView) this.findViewById(R.id.TextView_MyCollect);
        this.ImageView_MyComment= (ImageView) this.findViewById(R.id.ImageView_MyComment);
        this.ImageView_MyCollect= (ImageView) this.findViewById(R.id.ImageView_MyCollect);

        this.textViews_title=new ArrayList<>();
            textViews_title.add(TextView_MyComment);
            textViews_title.add(TextView_MyCollect);

         this.imageViews_title=new ArrayList<>();
          imageViews_title.add(ImageView_MyComment);
         imageViews_title.add(ImageView_MyCollect);

        this.TextView_MyComment.setOnClickListener(this);
        this.TextView_MyCollect.setOnClickListener(this);
        this.ViewPager_Comment_Collect.setAdapter(new MyCommentCollectViewAdapter(getSupportFragmentManager()) );
        this.ViewPager_Comment_Collect.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TextView textView_title=textViews_title.get(position);
                textView_title.setTextColor(Color.parseColor("#ff3344"));

                TextView textView_old=textViews_title.get(oldPosition);
                textView_old.setTextColor(Color.GRAY);

                ImageView imagView_title=imageViews_title.get(position);
                imagView_title.setBackgroundColor(Color.parseColor("#ff3344"));


                ImageView imageView_title_old=imageViews_title.get(oldPosition);
                imageView_title_old.setBackgroundColor(Color.parseColor("#ffffff"));
                oldPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
           int id=v.getId();
           //this.ViewPager_Comment_Collect.setCurrentItem(id);
           switch (id){
               case R.id.TextView_MyComment:

                   /*this.ImageView_MyComment.setBackgroundColor(Color.parseColor("#ff3344"));
                   this.ImageView_MyCollect.setBackgroundColor(Color.parseColor("#ffffff"));*/
                   this.ViewPager_Comment_Collect.setCurrentItem(0);
                   break;
               case  R.id.TextView_MyCollect:
                  /* this.ImageView_MyCollect.setBackgroundColor(Color.parseColor("#ff3344"));
                   this.ImageView_MyComment.setBackgroundColor(Color.parseColor("#ffffff"));*/
                   this.ViewPager_Comment_Collect.setCurrentItem(1);
                   break;
           }

    }

    class MyCommentCollectViewAdapter extends FragmentStatePagerAdapter{

        public MyCommentCollectViewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragment[position];
        }

        @Override
        public int getCount() {
            return fragment.length;
        }
    }
}
