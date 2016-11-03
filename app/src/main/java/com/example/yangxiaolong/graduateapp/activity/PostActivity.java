package com.example.yangxiaolong.graduateapp.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.yangxiaolong.graduateapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostActivity extends AppCompatActivity implements View.OnClickListener{

    //声明投稿的内容
    @BindView(R.id.editText_article)
    EditText editText_article;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);


    }




    @OnClick({R.id.imageButton_back, R.id.imageButton_take_photo, R.id.imageButton_tab_pic, R.id.imageButton_subject, R.id.imageButton_tab_audio})
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.imageButton_back:
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButton_take_photo:
                pickPicture();
                break;
            case R.id.imageButton_tab_pic:
                pickPicture();
                break;
            case R.id.imageButton_subject:

                break;
            case R.id.imageButton_tab_audio:
                break;

            /**
             * 选择图片对话框的两个按钮
             */
            case R.id.button_takePhoto:

                break;
            case R.id.button_pick:

                break;

        }
    }

    /**
     * 弹出选择图片对话框
     */
    private void pickPicture() {
        dialog= new Dialog(this,R.style.dialog);
        dialog.setContentView(R.layout.custom_pick_picture);
        //dialog.setCancelable(true);
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        //params.width = config.getScreenWidth();  //设置对话框的宽度为屏幕宽 （此处得到的是我一开始获得并存放起来的屏幕宽）
        window.setAttributes(params);//此句代码一定要放在show()后面，否则不起作用
        dialog.setCanceledOnTouchOutside(true);
        Button button_takePhoto = (Button) window.findViewById(R.id.button_takePhoto);
        Button button_pick = (Button) window.findViewById(R.id.button_pick);
        button_takePhoto.setOnClickListener(this);
        button_pick.setOnClickListener(this);
    }



}
