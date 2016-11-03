package com.example.yangxiaolong.graduateapp.utils;

import android.support.annotation.NonNull;

import com.example.yangxiaolong.graduateapp.domain.ActivityThemDomain;
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

    //第二个
    //不知道
    private int SubjectId;
    //活动名字
    private String Name;
    //活动头像
    private String Icon;
    //活动介绍
    private String Intro;
    //活动组题图片
    private String Banner;
    //开始时间
    private long BeginTime;
    //结束时间
    private long EndTime;
    //参加人数
    private int Members;
    //发布时间
    private long Pubtime;


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
                String subject=jsonObject.getString("Subject");
                ListUserContent listUserContent=null;
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
                                userId, userLevel, userNick, shares,subject);
                    }else{
                        Pic pic = getPic(jsonObject);
                        listUserContent=new ListUserContent(articleId, categoryId, comments, content, title, favorites, goods, hits, userIcon,
                                userId, userLevel, userNick, shares,pic,picCount,subject);
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
            if(!jsonObject.isNull("Pic")) {
                JSONObject jsonObject1 = jsonObject.getJSONObject("Pic");
                int height = jsonObject1.getInt("Height");
                String url = jsonObject1.getString("Url");
                int width = jsonObject1.getInt("Width");
                return new Pic(height, url, width);
            }
        return null;
    }


    public static List<ActivityThemDomain> getListThemDomain(String json){
        List<ActivityThemDomain> data=new ArrayList<>();

        JSONArray jsonArray= null;
        try {
            JSONObject jsonObject=new JSONObject(json);
            jsonArray =jsonObject.getJSONArray("result");
            for(int i=0;i<jsonArray.length();i++) {
                jsonObject = jsonArray.getJSONObject(i);
                int SubjectId = jsonObject.getInt("SubjectId");
                String Name = jsonObject.getString("Name");
                String Icon = jsonObject.getString("Icon");
                String Intro = jsonObject.getString("Intro");
                String Banner = jsonObject.getString("Banner");
                long BeginTime = jsonObject.getLong("BeginTime");
                long EndTime = jsonObject.getInt("EndTime");
                int Members = jsonObject.getInt("Members");
                long Pubtime = jsonObject.getLong("Pubtime");
                ActivityThemDomain activityThemDomain=new ActivityThemDomain(SubjectId,Name,Icon,Intro,Banner,BeginTime,EndTime,Members,Pubtime);
                data.add(activityThemDomain);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

}
