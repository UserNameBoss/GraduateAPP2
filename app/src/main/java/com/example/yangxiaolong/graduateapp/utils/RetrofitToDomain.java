package com.example.yangxiaolong.graduateapp.utils;

import com.example.yangxiaolong.graduateapp.domain.ListUserContent02;
import com.example.yangxiaolong.graduateapp.interfaces.GETListUserContent;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangxiaolong on 2016/11/1.
 */
public class RetrofitToDomain {

    public void getsss(String basePath,Map<String,String> map) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(basePath)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        GETListUserContent getListUserContent = retrofit.create(GETListUserContent.class);

        final Call<ListUserContent02> call = getListUserContent.addReviews(map);


        new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    ListUserContent02 response = call.execute().body();
                    System.out.println("============response="+response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
