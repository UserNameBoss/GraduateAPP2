package com.example.yangxiaolong.graduateapp.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.yangxiaolong.graduateapp.domain.ActivityThemDomain;
import com.example.yangxiaolong.graduateapp.domain.Audio;
import com.example.yangxiaolong.graduateapp.domain.CommentPerson;
import com.example.yangxiaolong.graduateapp.domain.ListUserContent;
import com.example.yangxiaolong.graduateapp.domain.Pic;
import com.example.yangxiaolong.graduateapp.domain.Smilies;
import com.example.yangxiaolong.graduateapp.domain.UserMessage;

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


    public static List<CommentPerson> getJsonToDomain(String json){
        List<CommentPerson> listComment=new ArrayList<>();
        CommentPerson commentPerson;
        try {
            JSONObject jsonObject=new JSONObject(json);
            int commentors=jsonObject.getInt("commentors");
            int count=jsonObject.getInt("count");
            boolean isnotop=!jsonObject.isNull("ontop");

            JSONArray jsonArray=jsonObject.getJSONArray("result");
            JSONArray jsonArray1;
            getCommentPerosn(listComment, commentors, count, isnotop, jsonArray);

            if(isnotop){
                jsonArray1=jsonObject.getJSONArray("ontop");
                getCommentPerosn(listComment, commentors, count, isnotop, jsonArray1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listComment;
    }

    private static void getCommentPerosn(List<CommentPerson> listComment, int commentors, int count, boolean isnotop, JSONArray jsonArray) throws JSONException {
        JSONObject jsonObject;
        CommentPerson commentPerson;
        for(int i = 0; i<jsonArray.length(); i++){
            jsonObject=jsonArray.getJSONObject(i);
            int CommentId=jsonObject.getInt("CommentId");
            //评论的内容
            String Comment=jsonObject.getString("Comment");
            //发表时间
            long Pubtime=jsonObject.getLong("Pubtime");
            //点赞的人数
            int CommentGoods=jsonObject.getInt("CommentGoods");
            //@的人的id
            int AtUserId=jsonObject.getInt("AtUserId");
            //@的评论的id
            int AtCommentId=jsonObject.getInt("AtCommentId");
            //楼
            int Floor=jsonObject.getInt("Floor");
            //用户id
            int UserId=jsonObject.getInt("UserId");
            //用户的等级经验
            int UserLevel=jsonObject.getInt("UserLevel");
            //用户的等级
            int UserLevel2=jsonObject.getInt("UserLevel2");
            //用户名
            String UserNick=jsonObject.getString("UserNick");
            //用户头像
            String UserIcon=jsonObject.getString("UserIcon");
            //不知道
            String Subject=jsonObject.getString("Subject");
            //是否是会员
            int IsVip=jsonObject.getInt("IsVip");
            //会员点数
            int VipPoint=jsonObject.getInt("VipPoint");
            //位置
            double LonX=jsonObject.getDouble("LonX");
            double LatY=jsonObject.getDouble("LatY");
            String Location=jsonObject.getString("Location");

            //评论的样式
            String CommentStyle=jsonObject.getString("CommentStyle");

            //图片

            if(!jsonObject.isNull("Smilies")){
                int Height=jsonObject.getInt("Height");
                String Icon=jsonObject.getString("Icon");
                int SmiliesId=jsonObject.getInt("SmiliesId");
                String Name=jsonObject.getString("Name");
                int Width=jsonObject.getInt("Width");
                Smilies smilies=new Smilies(Width,Name,SmiliesId,Icon,Height);
                commentPerson=new CommentPerson(isnotop,commentors,count,CommentId,Comment,Pubtime,CommentGoods,AtUserId,AtCommentId,Floor,
                        UserId, UserLevel,UserLevel2,UserNick,UserIcon,Subject,IsVip,VipPoint,LonX,LatY,Location,CommentStyle,smilies);
            }else{
                commentPerson=new CommentPerson(commentors,count,isnotop,CommentId,Comment,Pubtime,CommentGoods,AtUserId,AtCommentId,Floor,
                        UserId, UserLevel,UserLevel2,UserNick,UserIcon,Subject,IsVip,VipPoint,LonX,LatY,Location,CommentStyle);

            }
            listComment.add(commentPerson);
        }
    }
    public ListUserContent getListUserContent(Bundle bundle){
        ListUserContent listUserContent;
        int articleId=bundle.getInt("articleId");
        int categoryId=bundle.getInt("aategoryId");
        int comments=bundle.getInt("comments");
        String content=bundle.getString("content");
        String title=bundle.getString("title");
        int favorites=bundle.getInt("favorites");
        int goods=bundle.getInt("goods");
        int hits=bundle.getInt("hits");
        String userIcon=bundle.getString("userIcon");
        int userId=bundle.getInt("userId");
        int userLevel=bundle.getInt("userLevel");
        String userNick=bundle.getString("userNick");
        int shares=bundle.getInt("shares");
        int picCount=bundle.getInt("picCount");
        String subject=bundle.getString("subject");
        if(bundle.getInt("categoryId")==29){

            int duration=bundle.getInt("duration");
            String audiop=bundle.getString("audio");
            Audio audio=new Audio(audiop,duration);
            Pic pic=getPic(bundle);
            listUserContent=new ListUserContent(articleId, categoryId, comments, content, title, favorites, goods, hits, userIcon,
                    userId, userLevel, userNick, shares,pic,picCount,audio);
        }else {
            if(picCount==0) {
                listUserContent = new ListUserContent(articleId, categoryId, comments, content, title, favorites, goods, hits, userIcon,
                        userId, userLevel, userNick, shares,subject);
            }else{
                Pic pic = getPic(bundle);
                listUserContent=new ListUserContent(articleId, categoryId, comments, content, title, favorites, goods, hits, userIcon,
                        userId, userLevel, userNick, shares,pic,picCount,subject);
            }
        }
        return listUserContent;
    }


    private static Pic getPic(Bundle bundle){
        if(!bundle.getBoolean("url")) {

            int height = bundle.getInt("height");
            String url = bundle.getString("url");
            int width = bundle.getInt("width");
            return new Pic(height, url, width);
        }
        return null;
    }


    public static UserMessage getUserMessage(String json){
        try {
            JSONObject jsonObject=new JSONObject(json);
            int UserId=jsonObject.getInt("UserId");
            int UserLevel=jsonObject.getInt("UserLevel");
            int Level=jsonObject.getInt("Level");
            String UserName=jsonObject.getString("UserName");
            String UserNick=jsonObject.getString("UserNick");
            String UserIcon=jsonObject.getString("UserIcon");
            int Posts=jsonObject.getInt("Posts");
            int AuditedPosts=jsonObject.getInt("AuditedPosts");
            int Favorites=jsonObject.getInt("Favorites");
            int Comments=jsonObject.getInt("Comments");
            int Money=jsonObject.getInt("Money");
            int Point=jsonObject.getInt("Point");
            String Mobile=jsonObject.getString("Mobile");
            String QQ=jsonObject.getString("QQ");
            String Address=jsonObject.getString("Address");
            String RealName=jsonObject.getString("RealName");
            String Gender=jsonObject.getString("Gender");
            String City=jsonObject.getString("City");
            int AllMessages=jsonObject.getInt("AllMessages");
            int UnreadMessages=jsonObject.getInt("UnreadMessages");
            int Attentions=jsonObject.getInt("Attentions");
            int Fans=jsonObject.getInt("Fans");
            int Messages=jsonObject.getInt("Messages");
            int IsVip=jsonObject.getInt("IsVip");
            long VipExpiresp=jsonObject.getLong("VipExpires");
            int VipPoint=jsonObject.getInt("VipPoint");
            int CanTryoutVip=jsonObject.getInt("CanTryoutVip");
            String CurrentCover=jsonObject.getString("CurrentCover");
            int UserNumber=jsonObject.getInt("UserNumber");
            String CommentStyle=jsonObject.getString("CommentStyle");
            String Location=jsonObject.getString("Location");
            boolean IsAttentioned=jsonObject.getBoolean("IsAttentioned");
            boolean CanMessage=jsonObject.getBoolean("CanMessage");
            UserMessage userMessage=new UserMessage(UserId, UserLevel, Level,UserName, UserNick, UserIcon,Posts, AuditedPosts,Favorites,Comments, Money, Point,Mobile,QQ, Address,RealName,Gender,City, AllMessages,UnreadMessages, Attentions,Fans,Messages,IsVip,VipExpiresp,VipPoint,CanTryoutVip,CurrentCover,UserNumber,CommentStyle,Location,IsAttentioned,CanMessage);
            return userMessage;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<ListUserContent> getDataUserContent(String json){
        List<ListUserContent> data=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("result");
            for(int i=0;i<jsonArray.length();i++){
                jsonObject=jsonArray.getJSONObject(i);
                int articleId=jsonObject.getInt("ArticleId");
                int categoryId=0;
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
}
