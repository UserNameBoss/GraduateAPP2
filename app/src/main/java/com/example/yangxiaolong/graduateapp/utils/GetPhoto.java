package com.example.yangxiaolong.graduateapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by JZW on 2016/11/4.
 *
 * 该工具类用于拍照以及从相册选择图片
 */

public  class GetPhoto {
    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE=3;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private static Uri imageUri;

    /**
     * 拍照
     * 1创建存放图片的文件夹
     * 2.2将文件夹路径转换为uri
     * 2.3隐式启动相机的Activity，uri作为intent的一个参数.
     * 2.4拍照结束后，执行onActivityResult(…)获得图片 相册选取图片
     * 3.1启动相册Activity
     * 3.2选择结束后，执行onActivityResult(…)获得图片 动态权限管理
     */
    public static void takePhoto(Activity activity,File output) {

        /**
         * 隐式打开拍照的Activity，并且传入CROP_PHOTO常量作为拍照结束后回调的标志
         * 将文件转化为uri
         */
        imageUri = Uri.fromFile(output);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, CROP_PHOTO);


    }

    /**
     * 从相册选取图片
     */
    public static void choosePhoto(Activity activity) {
        /**
         * 打开选择图片的界面
         */
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        activity.startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }







}
