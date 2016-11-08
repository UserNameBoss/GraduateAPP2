package com.example.yangxiaolong.graduateapp.domain;

/**
 * Created by 宇杰 on 2016/11/1.
 */
public class ListUserContent {
    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    private boolean isSave;
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
    //分享的数目
    private int shares;
    //图片资源
    private Pic pic;
    //图片数量
    private int picCount;

    private String subject;

    //音频文件
    private Audio audio;


    //点赞标志
    private boolean isGood;
    //收藏标志
    private boolean isFavorite;

    public ListUserContent(int articleId, int categoryId, int comments, String content, String title, int favorites, int goods, int hits, String userIcon, int userId, int userLevel, String userNick, int shares, Pic pic, int picCount, Audio audio) {
        this.articleId = articleId;
        this.categoryId = categoryId;
        this.comments = comments;
        this.content = content;
        this.title = title;
        this.favorites = favorites;
        this.goods = goods;
        this.hits = hits;
        this.userIcon = userIcon;
        this.userId = userId;
        this.userLevel = userLevel;
        this.userNick = userNick;
        this.shares = shares;
        this.pic = pic;
        this.picCount = picCount;
        this.audio = audio;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public ListUserContent(int articleId,int categoryId, int comments, String content, String title, int favorites, int goods, int hits, String userIcon, int userId, int userLevel, String userNick,int shares,String subject) {
        this.categoryId = categoryId;
        this.articleId = articleId;
        this.comments = comments;
        this.content = content;
        this.title = title;
        this.favorites = favorites;
        this.goods = goods;
        this.hits = hits;
        this.userIcon = userIcon;
        this.userId = userId;
        this.userLevel = userLevel;
        this.userNick = userNick;
        this.shares=shares;
        this.subject=subject;
    }


    public ListUserContent(int articleId, int categoryId, int comments, String content, String title, int favorites, int goods, int hits, String userIcon, int userId, int userLevel, String userNick, int shares, Pic pic, int picCount,String subject) {
        this.articleId = articleId;
        this.categoryId = categoryId;
        this.comments = comments;
        this.content = content;
        this.title = title;
        this.favorites = favorites;
        this.goods = goods;
        this.hits = hits;
        this.userIcon = userIcon;
        this.userId = userId;
        this.userLevel = userLevel;
        this.userNick = userNick;
        this.shares = shares;
        this.pic = pic;
        this.picCount = picCount;
        this.subject=subject;
    }

    public Pic getPic() {
        return pic;
    }

    public void setPic(Pic pic) {
        this.pic = pic;
    }

    public int getPicCount() {
        return picCount;
    }

    public void setPicCount(int picCount) {
        this.picCount = picCount;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return "ListUserContent{" +
                "articleId=" + articleId +
                ", categoryId=" + categoryId +
                ", comments=" + comments +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", favorites=" + favorites +
                ", goods=" + goods +
                ", hits=" + hits +
                ", userIcon='" + userIcon + '\'' +
                ", userId=" + userId +
                ", userLevel=" + userLevel +
                ", userNick='" + userNick + '\'' +
                ", shares=" + shares +
                ", pic=" + pic +
                ", picCount=" + picCount +
                ", audio=" + audio +
                '}';
    }
}
