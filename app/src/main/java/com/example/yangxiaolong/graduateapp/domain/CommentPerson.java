package com.example.yangxiaolong.graduateapp.domain;

/**
 * Created by yangxiaolong on 2016/11/4.
 */
public class CommentPerson {

    /**
     * CommentId : 37335594
     * Comment : 你老婆光明
     * Pubtime : 1468071706000
     * CommentGoods : 0
     * AtUserId : 0
     * AtCommentId : 0
     * Floor : 37
     * UserId : 1694808
     * UserLevel : 52755
     * UserLevel2 : 8
     * UserNick : 刚刚好
     * UserIcon : http://qzapp.qlogo.cn/qzapp/100588605/5F86866E6C3559186FD05C1231243131/100
     * Subject :
     * IsVip : 0
     * VipPoint : 0
     * LonX : 117.195631
     * LatY : 36.975818937689
     * Location : 山东省 济南
     * CommentStyle :
     */

    private int commentors;
    private int count;

    private boolean isontop;

    //评论的id
    private int CommentId;
    //评论的内容
    private String Comment;
    //发表时间
    private long Pubtime;
    //点赞的人数
    private int CommentGoods;
    //@的人的id
    private int AtUserId;
    //@的评论的id
    private int AtCommentId;
    //楼
    private int Floor;
    //用户id
    private int UserId;
    //用户的等级经验
    private int UserLevel;
    //用户的等级
    private int UserLevel2;
    //用户名
    private String UserNick;
    //用户头像
    private String UserIcon;
    //不知道
    private String Subject;
    //是否是会员
    private int IsVip;
    //会员点数
    private int VipPoint;
    //位置
    private double LonX;
    private double LatY;
    private String Location;

    //评论的样式
    private String CommentStyle;

    //图片
    private Smilies smilies;

    public CommentPerson(boolean isontop,int commentors,int count,int commentId, String comment, long pubtime, int commentGoods, int atUserId, int atCommentId, int floor, int userId, int userLevel, int userLevel2, String userNick, String userIcon, String subject, int isVip, int vipPoint, double lonX, double latY, String location, String commentStyle, Smilies smilies) {
        this.isontop=isontop;
        this.commentors=commentors;
        this.count=count;
        CommentId = commentId;
        Comment = comment;
        Pubtime = pubtime;
        CommentGoods = commentGoods;
        AtUserId = atUserId;
        AtCommentId = atCommentId;
        Floor = floor;
        UserId = userId;
        UserLevel = userLevel;
        UserLevel2 = userLevel2;
        UserNick = userNick;
        UserIcon = userIcon;
        Subject = subject;
        IsVip = isVip;
        VipPoint = vipPoint;
        LonX = lonX;
        LatY = latY;
        Location = location;
        CommentStyle = commentStyle;
        this.smilies = smilies;
    }


    public CommentPerson(int commentors, int count, boolean isontop, int commentId, String comment, long pubtime, int commentGoods, int atUserId, int atCommentId, int floor, int userId, int userLevel, int userLevel2, String userNick, String userIcon, String subject, int isVip, int vipPoint, double lonX, double latY, String location, String commentStyle) {
        this.commentors = commentors;
        this.count = count;
        this.isontop = isontop;
        CommentId = commentId;
        Comment = comment;
        Pubtime = pubtime;
        CommentGoods = commentGoods;
        AtUserId = atUserId;
        AtCommentId = atCommentId;
        Floor = floor;
        UserId = userId;
        UserLevel = userLevel;
        UserLevel2 = userLevel2;
        UserNick = userNick;
        UserIcon = userIcon;
        Subject = subject;
        IsVip = isVip;
        VipPoint = vipPoint;
        LonX = lonX;
        LatY = latY;
        Location = location;
        CommentStyle = commentStyle;
    }

    public void setCommentId(int CommentId) {
        this.CommentId = CommentId;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public void setPubtime(long Pubtime) {
        this.Pubtime = Pubtime;
    }

    public void setCommentGoods(int CommentGoods) {
        this.CommentGoods = CommentGoods;
    }

    public Smilies getSmilies() {
        return smilies;
    }

    public void setSmilies(Smilies smilies) {
        this.smilies = smilies;
    }

    public void setAtUserId(int AtUserId) {
        this.AtUserId = AtUserId;
    }

    public void setAtCommentId(int AtCommentId) {
        this.AtCommentId = AtCommentId;
    }

    public void setFloor(int Floor) {
        this.Floor = Floor;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public void setUserLevel(int UserLevel) {
        this.UserLevel = UserLevel;
    }

    public void setUserLevel2(int UserLevel2) {
        this.UserLevel2 = UserLevel2;
    }

    public void setUserNick(String UserNick) {
        this.UserNick = UserNick;
    }

    public void setUserIcon(String UserIcon) {
        this.UserIcon = UserIcon;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public void setIsVip(int IsVip) {
        this.IsVip = IsVip;
    }

    public void setVipPoint(int VipPoint) {
        this.VipPoint = VipPoint;
    }

    public void setLonX(double LonX) {
        this.LonX = LonX;
    }

    public void setLatY(double LatY) {
        this.LatY = LatY;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setCommentStyle(String CommentStyle) {
        this.CommentStyle = CommentStyle;
    }

    public int getCommentId() {
        return CommentId;
    }

    public String getComment() {
        return Comment;
    }

    public long getPubtime() {
        return Pubtime;
    }

    public int getCommentGoods() {
        return CommentGoods;
    }

    public int getAtUserId() {
        return AtUserId;
    }

    public int getAtCommentId() {
        return AtCommentId;
    }

    public int getFloor() {
        return Floor;
    }

    public int getUserId() {
        return UserId;
    }

    public int getUserLevel() {
        return UserLevel;
    }

    public int getUserLevel2() {
        return UserLevel2;
    }

    public String getUserNick() {
        return UserNick;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public String getSubject() {
        return Subject;
    }

    public int getIsVip() {
        return IsVip;
    }

    public int getVipPoint() {
        return VipPoint;
    }

    public double getLonX() {
        return LonX;
    }

    public double getLatY() {
        return LatY;
    }

    public String getLocation() {
        return Location;
    }

    public String getCommentStyle() {
        return CommentStyle;
    }


}
