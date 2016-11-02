package com.example.yangxiaolong.graduateapp.utils;

import android.support.annotation.NonNull;

import com.example.yangxiaolong.graduateapp.domain.Audio;
import com.example.yangxiaolong.graduateapp.domain.ListUserContent;
import com.example.yangxiaolong.graduateapp.domain.Pic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaolong on 2016/11/1.
 */
public class JsonToDomain {
    //文章ID
    private int articleId;
    //种类ID
    private int categoryId;
    //评论的人数
    private int comments;
    //发送的内容
    private String content;
    //发送的标题
    private String title;
    //收藏的人数
    private int favorites;
    //点赞的人数
    private int goods;
    //点击数
    private int hits;
    //用户头像
    private String userIcon;
    //用户ID
    private int userId;
    //用户等级
    private int userLevel;
    //用户名
    private String userNick;

    public static List<ListUserContent> getData(String json){
        List<ListUserContent> data=new ArrayList<>();
        try {
            JSONArray jsonArray=new JSONArray(json);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                int articleId=jsonObject.getInt("ArticleId");
                int categoryId=jsonObject.getInt("CategoryId");
                int comments=jsonObject.getInt("Comments");
                String content=jsonObject.getString("Content");
                String title=jsonObject.getString("Title");
                int favorites=jsonObject.getInt("Favorites");
                int goods=jsonObject.getInt("Goods");
                int hits=jsonObject.getInt("Hits");
                String userIcon=jsonObject.getString("UserIcon");
                int userId=jsonObject.getInt("UserId");
                int userLevel=jsonObject.getInt("UserLevel");
                String userNick=jsonObject.getString("UserNick");
                int shares=jsonObject.getInt("Shares");
                int picCount=jsonObject.getInt("PicCount");
                ListUserContent listUserContent=null;
                System.out.println("========Json======categoryId="+categoryId);
                if(categoryId==29){
                    System.out.println("============解析声音===============");
                    System.out.println("===========jsonArray.length="+jsonArray.length());
                     Pic pic=getPic(jsonObject);
                     JSONObject jsonObject1=jsonObject.getJSONObject("Audio");
                     int duration=jsonObject1.getInt("Duration");
                     String audiop=jsonObject1.getString("Audio");
                     Audio audio=new Audio(audiop,duration);
                     listUserContent=new ListUserContent(articleId, categoryId, comments, content, title, favorites, goods, hits, userIcon,
                            userId, userLevel, userNick, shares,pic,picCount,audio);
                }else {
                    if(picCount==0) {
                        listUserContent = new ListUserContent(articleId, categoryId, comments, content, title, favorites, goods, hits, userIcon,
                                userId, userLevel, userNick, shares);
                    }else{
                        Pic pic = getPic(jsonObject);
                        listUserContent=new ListUserContent(articleId, categoryId, comments, content, title, favorites, goods, hits, userIcon,
                                userId, userLevel, userNick, shares,pic,picCount);
                    }
                }

                data.add(listUserContent);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    @NonNull
    private static Pic getPic(JSONObject jsonObject) throws JSONException {
        JSONObject jsonObject1=jsonObject.getJSONObject("Pic");
        int height=jsonObject1.getInt("Height");
        String url=jsonObject1.getString("Url");
        int width=jsonObject1.getInt("Width");
        return new Pic(height,url,width);
    }
}
