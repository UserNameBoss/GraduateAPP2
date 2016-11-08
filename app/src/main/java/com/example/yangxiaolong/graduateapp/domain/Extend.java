package com.example.yangxiaolong.graduateapp.domain;

/**
 * Created by yangxiaolong on 2016/11/8.
 */
public class Extend {
    private int ArticleId;
    private int Comments;
    private Pic pic;
    private String Title;
    private String UserIcon;
    private int UserId;
    private int UserLevel;
    private String UserNick;


    public Extend(int articleId, int comments, Pic pic, String title, String userIcon, int userId, int userLevel, String userNick) {
        ArticleId = articleId;
        Comments = comments;
        this.pic = pic;
        Title = title;
        UserIcon = userIcon;
        UserId = userId;
        UserLevel = userLevel;
        UserNick = userNick;
    }

    public int getArticleId() {
        return ArticleId;
    }

    public void setArticleId(int articleId) {
        ArticleId = articleId;
    }

    public int getComments() {
        return Comments;
    }

    public void setComments(int comments) {
        Comments = comments;
    }

    public Pic getPic() {
        return pic;
    }

    public void setPic(Pic pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(String userIcon) {
        UserIcon = userIcon;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getUserLevel() {
        return UserLevel;
    }

    public void setUserLevel(int userLevel) {
        UserLevel = userLevel;
    }

    public String getUserNick() {
        return UserNick;
    }

    public void setUserNick(String userNick) {
        UserNick = userNick;
    }
}
