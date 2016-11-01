package com.example.yangxiaolong.graduateapp.utils;

import com.example.yangxiaolong.graduateapp.domain.ListUserContent;

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
                ListUserContent listUserContent=new ListUserContent(articleId,categoryId,comments,content,title,favorites,goods,hits,userIcon,
                        userId,userLevel,userNick);
                data.add(listUserContent);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }
}
