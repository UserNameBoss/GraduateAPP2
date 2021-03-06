package com.example.yangxiaolong.graduateapp.utils;
import android.os.Handler;
import android.os.Message;

import com.example.yangxiaolong.graduateapp.domain.ListUserContent;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by 宇杰 on 2016/11/1.
 */
/**
 * 自定义Http使用工具类
 * Created by JZW on 2016/10/9.
 */
public class NetWorkListUserContent{

    /**
         * 根据指定的网络路径返回字节数组
         * @param path 网络路径
         * @return 网络数据对应的字节数组
         */
        public static byte[] sendRequestWithGet(String path){
            InputStream inputStream=null;
            ByteArrayOutputStream byteArrayOutputStream=null;
            try {
                URL url=new URL(path);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(3000);

                int responseode=httpURLConnection.getResponseCode();
                if (responseode==httpURLConnection.HTTP_OK){
                    inputStream=httpURLConnection.getInputStream();

                    byteArrayOutputStream=new ByteArrayOutputStream();
                    int len=0;
                    byte[] buffer=new byte[1024];
                    while ((len=inputStream.read(buffer))!=-1){
                        byteArrayOutputStream.write(buffer,0,len);

                    }
                    return byteArrayOutputStream.toByteArray();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (inputStream!=null){
                    try {
                        inputStream.close();
                        inputStream=null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            return null;
        }

        /**
         * 根据指定的网络路径返回输入流
         * @param path 网络路径
         * @return 网络数据对应的输入流
         */
        public static InputStream getInputStream(String path){

            try {
                URL url=new URL(path);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(3000);

                int responseode=httpURLConnection.getResponseCode();
                if (responseode==httpURLConnection.HTTP_OK){
                    return httpURLConnection.getInputStream();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        /**
         * 将输入流转换成字符串
         * @param inputStream
         */
        public static String inputStreamToString(InputStream inputStream) {
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int len=0;
            try {
                while((len=inputStream.read(buffer))!=-1){
                    byteArrayOutputStream.write(buffer, 0, len);
            }

            return byteArrayOutputStream.toString();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }




        /**
         * 声明回调接口
         */
        public interface GetResultCallback{
            public void getMessage(String message);
        }

        /**
         * 得到使用Post提交到服务器后返回的字符串
         * @param path 网络路径
         * @param params   参数列表
         * @param resultCallback 回调接口
         */
        public static void getPostResult(final String path, final String params,
                                         final GetResultCallback resultCallback ){

            final Handler handler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    String result=msg.obj.toString();
                    //通过接口回调调用实现类中的方法，并传入json数据
                    System.out.println("========================Net.msg="+result);
                    if(resultCallback!=null) {
                        resultCallback.getMessage(result);
                    }
                }
            };

            new Thread(new Runnable() {
                @Override
                public void run() {
                    OutputStream outputStream=null;
                    InputStream inputStream=null;
                    ByteArrayOutputStream byteArrayOutputStream=null;

                    try {
                        URL url=new URL(path);
                        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");

                        //打开运行向服务器端写出数据的开关,默认值为false,即默认情况下不允许向服务器端写出数据
                        httpURLConnection.setDoOutput(true);
                        outputStream=httpURLConnection.getOutputStream();
                        //将需要的数据转成字节数组写出到服务器端
                        outputStream.write(params.getBytes());

                        if (httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                            inputStream=httpURLConnection.getInputStream();
                            byteArrayOutputStream=new ByteArrayOutputStream();
                            byte[] buffer=new byte[1024];
                            for (int len = 0; (len=inputStream.read(buffer))!=-1 ;) {
                                byteArrayOutputStream.write(buffer,0,len);
                            }
                            String resultString=byteArrayOutputStream.toString();
                            Message message=handler.obtainMessage();
                            message.obj=resultString;
                            message.what=1;
                            handler.sendMessage(message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(outputStream!=null){
                            try {
                                outputStream.close();
                                outputStream=null;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(inputStream!=null){
                            try {
                                inputStream.close();
                                inputStream=null;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }

        public static List<ListUserContent> getList(String json){
            List<ListUserContent> list=new ArrayList<>();
            try {
                JSONArray jsonArray=new JSONArray(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }
}
