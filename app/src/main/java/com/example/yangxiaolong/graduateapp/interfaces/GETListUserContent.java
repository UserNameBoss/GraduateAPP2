package com.example.yangxiaolong.graduateapp.interfaces;

import com.example.yangxiaolong.graduateapp.domain.UserMessage;

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
    @POST("get_user_info")
    Call<UserMessage> addReviews(@FieldMap Map<String,String> map);
}
