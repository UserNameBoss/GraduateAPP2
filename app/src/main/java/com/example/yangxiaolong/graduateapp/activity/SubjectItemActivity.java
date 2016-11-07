package com.example.yangxiaolong.graduateapp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangxiaolong.graduateapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubjectItemActivity extends Activity implements View.OnClickListener {

    //声明投稿的内容
    @BindView(R.id.editText_article)
    EditText editText_article;
    @BindView(R.id.imageButton_take_photo)
    ImageButton imageButton_take_photo;
    @BindView(R.id.textView_uploadImage)
    TextView textView_uploadImage;

    private Dialog dialog;
    private ImageView imageView;
    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE=3;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private  File output;
    private Uri imageUri;

    //声明一个判断是否设置图片的标志
    Boolean flag=true;
    //声明一个判断是否设了title值的标志
    Boolean flag2;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
       /* if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }*/
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        Intent intent_title=getIntent();
        title=intent_title.getExtras().getString("title");
        editText_article.setText("#"+title+"#");
        editText_article.setTextColor(Color.BLACK);

        String str=editText_article.getText().toString();
        //int fstart=str.indexOf("#"+title+"#");
        int fend=("#"+title+"#").length();
        SpannableStringBuilder style=new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(Color.RED),0,fend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText_article.setText(style);
    }


    @OnClick({R.id.imageButton_back, R.id.imageButton_take_photo, R.id.imageButton_tab_pic,
            R.id.imageButton_subject, R.id.imageButton_tab_audio,R.id.textView_post,
    })
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.imageButton_back:
                if (editText_article.getText().toString().trim().equals("#"+title+"#")){
                    intent = new Intent(this, SubjectActivity.class);
                    startActivity(intent);
                }else{
                    systemHint();
                }
                break;
            case R.id.imageButton_take_photo:

                if (flag){
                    pickPicture();
                }else{
                    openMenu(view);
                }

                break;
            case R.id.imageButton_tab_pic:
                pickPicture();
                break;
            case R.id.imageButton_subject:
                intent=new Intent(this,SubjectActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButton_tab_audio:
                intent=new Intent(this,PostAudioActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_post:
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
               /* imageButton_take_photo.setImageResource(R.drawable.btn_take_photo);
                flag=true;
                textView_uploadImage.setVisibility(View.VISIBLE);*/
                break;

            //拍照
            case R.id.button_takePhoto:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE2);

                }else {
                    takePhoto();
                }
                break;
            //选择图片
            case R.id.button_pick:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE2);

                }else {
                    choosePhoto();
                }
                break;


            case R.id.button_cancel:
                dialog.dismiss();
                break;
            case R.id.button_confirm:
                intent = new Intent(this, SubjectActivity.class);
                startActivity(intent);
                break;
        }
    }



    /**
     * 打开弹出菜单重新选择或者移除相片
     */
    private void openMenu(View view) {
        //1.实例化弹出菜单对象
        PopupMenu popupMenu=new PopupMenu(this,view);
        //2.使用菜单填充器对象将指定的菜单文件转化成菜单对象并挂载到弹出菜单对应的菜单上
        popupMenu.getMenuInflater().inflate(R.menu.menu_post,popupMenu.getMenu());
        //3.给弹出菜单注册监听器对象
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int order=item.getOrder();
                if (order==100){
                    pickPicture();
                }else{
                    imageButton_take_photo.setImageResource(R.drawable.btn_take_photo);
                    flag=true;
                    textView_uploadImage.setVisibility(View.VISIBLE);
                }
                //Toast.makeText(PostActivity.this, "用户点击了["+item.getOrder()+"]菜单项", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //4.显示弹出菜单
        popupMenu.show();

    }



    /**
     * 拍照
     * 1创建存放图片的文件夹
     * 2.2将文件夹路径转换为uri
     * 2.3隐式启动相机的Activity，uri作为intent的一个参数.
     * 2.4拍照结束后，执行onActivityResult(…)获得图片 相册选取图片
     * 3.1启动相册Activity
     * 3.2选择结束后，执行onActivityResult(…)获得图片 动态权限管理
     */
    private void takePhoto() {
        /**
         * 最后一个参数是文件夹的名称，可以随便起
         */
        File file=new File(Environment.getExternalStorageDirectory(),"拍照");
        if(!file.exists()){
            file.mkdir();
        }
        /**
         * 这里将时间作为不同照片的名称
         */
        output=new File(file,System.currentTimeMillis()+".jpg");

        /**
         * 如果该文件夹已经存在，则删除它，否则创建一个
         */
        try {
            if (output.exists()) {
                output.delete();
            }
            output.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 隐式打开拍照的Activity，并且传入CROP_PHOTO常量作为拍照结束后回调的标志
         * 将文件转化为uri
         */
        imageUri = Uri.fromFile(output);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CROP_PHOTO);


    }

    /**
     * 从相册选取图片
     */
    private void choosePhoto() {
        /**
         * 打开选择图片的界面
         */
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    public void onActivityResult(int req, int res, Intent data) {
        switch (req) {
            /**
             * 拍照的请求标志
             */
            case CROP_PHOTO:
                if (res==RESULT_OK) {
                    try {
                        /**
                         * 该uri就是照片文件夹对应的uri
                         */
                        //Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        Bitmap bitmap= this.twoRatio(imageUri,130,100);
                        imageButton_take_photo.setImageBitmap(bitmap);

                        //设完图片flag置为false
                        flag=false;

                        //去掉上传图片四个字
                        this.textView_uploadImage.setVisibility(View.GONE);
                        //去掉对话框
                        this.dialog.dismiss();
                        imageButton_take_photo.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        Toast.makeText(this,"程序崩溃",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Log.i("tag", "失败");
                }

                break;
            /**
             * 从相册中选取图片的请求标志
             */

            case REQUEST_CODE_PICK_IMAGE:
                if (res == RESULT_OK) {
                    try {
                        /**
                         * 该uri是上一个Activity返回的
                         */
                        Uri uri = data.getData();
                        //Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        Bitmap bitmap2= this.twoRatio(uri,130,100);
                        imageButton_take_photo.setImageBitmap(bitmap2);

                        //设完图片flag置为false
                        flag=false;

                        //去掉上传图片四个字
                        this.textView_uploadImage.setVisibility(View.GONE);
                        //去掉对话框
                        this.dialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("tag",e.getMessage());
                        Toast.makeText(this,"程序崩溃",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Log.i("liang", "失败");
                }

                break;

            default:
                break;
        }
    }


    /**
     * 根据资源id将图片进行二次采样生成图片后返回
     * @param uri 图片资源
     *@param newWidth 缩放后图片的宽度
     * @param newHeight 缩放后图片的高度
     * @return 二次采样后生成的图片对象
     */
    public Bitmap twoRatio(Uri uri, int newWidth, int newHeight){
        Bitmap bitmap=null;
        Bitmap ratioBitmap=null;
        //1.实例化选项对象
        BitmapFactory.Options options=new BitmapFactory.Options();
        //2.在加载图片时不会加载整张图片,而只会加载图片的宽和高的信息
        options.inJustDecodeBounds=true;
        //调用BitmapFactory.decodeResource(res,id,options)进行二次采样
       /* Bitmap bitmap= BitmapFactory.decodeResource(
                this.getResources(),//资源对象
                resId,//需要进行二次采用的图片的资源id
                options//选项对象
        );*/
        try {
            bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(uri),null,options);
            System.out.println("bitmap="+bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //获取原始图片的宽度
        int oldWidth= options.outWidth;
        int oldHeight=options.outHeight;

        //根据原始图片的宽高和缩放后图片的宽高算出最小值作为图片的采样率
        int ratio=Math.min(oldWidth/newWidth,oldHeight/newHeight);

        System.out.println("ratio="+ratio);
        //设置图片的采用率
        options.inSampleSize=ratio;

        //在加载图片时就会加载整张图片了
        options.inJustDecodeBounds=false;

        //二次采样后生成的图片对象
        try {
            ratioBitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(uri),null,options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ratioBitmap;
    }




    /**
     *  判断用户是否授予该权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                takePhoto();
            } else
            {
                // Permission Denied
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                choosePhoto();
            } else
            {
                // Permission Denied
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    /**
     * 弹出选择图片对话框
     */
    private void pickPicture() {

        dialog = new Dialog(this, R.style.dialog);
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

    /**
     * 系统提示对话框
     * 提示是否保存草稿
     */
    private void systemHint() {
        dialog = new Dialog(this, R.style.dialog);
        dialog.setContentView(R.layout.custom_system_hint);
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        window.setAttributes(params);//此句代码一定要放在show()后面，否则不起作用
        dialog.setCanceledOnTouchOutside(true);
        Button button_cancel = (Button) window.findViewById(R.id.button_cancel);
        Button button_confirm = (Button) window.findViewById(R.id.button_confirm);
        button_cancel.setOnClickListener(this);
        button_confirm.setOnClickListener(this);
    }

}