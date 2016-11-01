package com.example.yangxiaolong.graduateapp.interfaces;

import com.example.yangxiaolong.graduateapp.domain.ListUserContent02;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yangxiaolong on 2016/11/1.
 */
public interface GETListUserContent {

    @FormUrlEncoded
    @POST("category_article_list_hot_v2")
    Call<ListUserContent02> addReviews(@FieldMap Map<String,String> map);
}
