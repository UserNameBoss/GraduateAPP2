package com.example.yangxiaolong.graduateapp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.utils.GetPhoto;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostAudioActivity extends Activity implements View.OnClickListener {
    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE=3;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private  File output;
    private Uri imageUri;

    //声明一个标志
    Boolean flag=true;

    private Dialog dialog;
    @BindView(R.id.imageButton_postAudio)
     ImageButton imageButton_postAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_post_audio);
        ButterKnife.bind(this);
        pickVoicePicture();

    }

    @OnClick({R.id.imageButton_back, R.id.textView_nextStep, R.id.imageButton_postAudio, R.id.imageView_record})
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.imageButton_back:
                intent=new Intent(this,PostActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_nextStep:

                break;
            case R.id.imageButton_postAudio:
                pickVoicePictureAgain();
                break;
            case R.id.imageView_record:

                break;

            /**
             * 选择声音配图对话框
             */
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
        }
    }



    /**
     * 重新选择声音配图对话框
     */
    private void pickVoicePictureAgain() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("选项菜单");//设置对话框的标题
        final String[] options={"重新拍照","从相册选择"};

        builder.setItems(options, new DialogInterface.OnClickListener() {
            /**
             * 用户点击指定数据条目时自动调用的方法
             * @param dialog 事件源
             * @param which 数据源数组中数据的索引值
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        takePhoto();
                        break;
                    case 1:
                        choosePhoto();
                        break;
                }
                //Toast.makeText(PostAudioActivity.this, "option="+option, Toast.LENGTH_SHORT).show();

            }
        });

        //show()方法内部自动调用create()方法,显示对话框
        builder.show();

       /* //通过调用构建器对象的create()方法得到提示对话框
        AlertDialog alertDialog= builder.create();
        //用户点击提示对话框的外边不会销毁对话框,但按返回键可以取消对话框
         alertDialog.setCanceledOnTouchOutside(true);
        //让对话框显示
        alertDialog.show();*/
    }

    /**
     * 弹出选择声音配图对话框
     */
    private void pickVoicePicture() {

        dialog = new Dialog(this, R.style.dialog);
        dialog.setContentView(R.layout.custom_pick_voice_picture);
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

        // 方法1 Android获得屏幕的宽和高
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth  = display.getWidth();
        int screenHeight  = display.getHeight();

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
                        Bitmap bitmap= this.twoRatio(imageUri,screenWidth,screenHeight);
                        imageButton_postAudio.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageButton_postAudio.setImageBitmap(bitmap);
                        //去掉对话框
                        this.dialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
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

                        Bitmap bitmap2= this.twoRatio(uri,screenWidth,screenHeight);
                        imageButton_postAudio.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageButton_postAudio.setImageBitmap(bitmap2);
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
        //int ratio=(oldWidth/newWidth);

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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                takePhoto();
            } else
            {
                // Permission Denied
                Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "权限被拒绝 ", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
