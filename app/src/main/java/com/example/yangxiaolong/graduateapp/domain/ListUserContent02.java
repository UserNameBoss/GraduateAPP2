package com.example.yangxiaolong.graduateapp.domain;

/**
 * Created by yangxiaolong on 2016/11/1.
 */
public class ListUserContent02 {

    /**
     * CategoryId : 30
     * Pic : {"Url":"http://img.xb.huabao.me/wp-content/uploads/2015/07/559a8346df50d.jpg","Width":560,"Height":805}
     * Content : null
     * UserName : B8F5B9977080E827378B8C872A1B0BF8
     * UserNick : 蔡蔡是我的菜
     * UserIcon : http://img.xb.huabao.me/wp-content/uploads/uicons/73/db/092549d6e24cfa7aafffc3b35364db73.jpg
     * UserLevel : 20774
     * UserId : 298334
     * LonX : 116.40387397
     * LatY : 39.91488908
     * Location : 北京市 北京
     * Distance : 0
     * IsVip : 0
     * VipPoint : 0
     * CommentStyle :
     * WapUrl : null
     * PicCount : 1
     * Subject :
     * ArticleId : 52777451
     * Title : 还不错呦！
     * Pubtime : 1436251812000
     * Goods : 2855
     * Shares : 807
     * Comments : 36
     * Hits : 9095
     * Favorites : 170
     */

    //分类ID
    private int CategoryId;

    //内容是图片
    private PicEntity Pic;
    //内容文字
    private String Content;
    //用户名 编码
    private String UserName;
    //用户名
    private String UserNick;
    //用户名Icon
    private String UserIcon;
    //用户等级
    private int UserLevel;
    //用户ID
    private int UserId;
    //用户地理位置
    private double LonX;
    private double LatY;
    //用户地址
    private String Location;
    //距离
    private int Distance;
    //Vip等级
    private int IsVip;
    //vip标志
    private int VipPoint;
    //意见风格
    private String CommentStyle;
    //wap
    private Object WapUrl;
    //计数
    private int PicCount;
    //主题
    private String Subject;
    //文章Id
    private int ArticleId;
    //文章标题
    private String Title;
    //发表时间
    private long Pubtime;
    //点赞人数
    private int Goods;
    //分享次数
    private int Shares;
    //评论人数
    private int Comments;
    //点击次数
    private int Hits;
    //收藏次数
    private int Favorites;

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public void setPic(PicEntity Pic) {
        this.Pic = Pic;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setUserNick(String UserNick) {
        this.UserNick = UserNick;
    }

    public void setUserIcon(String UserIcon) {
        this.UserIcon = UserIcon;
    }

    public void setUserLevel(int UserLevel) {
        this.UserLevel = UserLevel;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
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

    public void setDistance(int Distance) {
        this.Distance = Distance;
    }

    public void setIsVip(int IsVip) {
        this.IsVip = IsVip;
    }

    public void setVipPoint(int VipPoint) {
        this.VipPoint = VipPoint;
    }

    public void setCommentStyle(String CommentStyle) {
        this.CommentStyle = CommentStyle;
    }

    public void setWapUrl(Object WapUrl) {
        this.WapUrl = WapUrl;
    }

    public void setPicCount(int PicCount) {
        this.PicCount = PicCount;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public void setArticleId(int ArticleId) {
        this.ArticleId = ArticleId;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setPubtime(long Pubtime) {
        this.Pubtime = Pubtime;
    }

    public void setGoods(int Goods) {
        this.Goods = Goods;
    }

    public void setShares(int Shares) {
        this.Shares = Shares;
    }

    public void setComments(int Comments) {
        this.Comments = Comments;
    }

    public void setHits(int Hits) {
        this.Hits = Hits;
    }

    public void setFavorites(int Favorites) {
        this.Favorites = Favorites;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public PicEntity getPic() {
        return Pic;
    }

    public Object getContent() {
        return Content;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserNick() {
        return UserNick;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public int getUserLevel() {
        return UserLevel;
    }

    public int getUserId() {
        return UserId;
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

    public int getDistance() {
        return Distance;
    }

    public int getIsVip() {
        return IsVip;
    }

    public int getVipPoint() {
        return VipPoint;
    }

    public String getCommentStyle() {
        return CommentStyle;
    }

    public Object getWapUrl() {
        return WapUrl;
    }

    public int getPicCount() {
        return PicCount;
    }

    public String getSubject() {
        return Subject;
    }

    public int getArticleId() {
        return ArticleId;
    }

    public String getTitle() {
        return Title;
    }

    public long getPubtime() {
        return Pubtime;
    }

    public int getGoods() {
        return Goods;
    }

    public int getShares() {
        return Shares;
    }

    public int getComments() {
        return Comments;
    }

    public int getHits() {
        return Hits;
    }

    public int getFavorites() {
        return Favorites;
    }

    public static class PicEntity {
        /**
         * Url : http://img.xb.huabao.me/wp-content/uploads/2015/07/559a8346df50d.jpg
         * Width : 560
         * Height : 805
         */

        private String Url;
        private int Width;
        private int Height;

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public void setWidth(int Width) {
            this.Width = Width;
        }

        public void setHeight(int Height) {
            this.Height = Height;
        }

        public String getUrl() {
            return Url;
        }

        public int getWidth() {
            return Width;
        }

        public int getHeight() {
            return Height;
        }

        @Override
        public String toString() {
            return "PicEntity{" +
                    "Url='" + Url + '\'' +
                    ", Width=" + Width +
                    ", Height=" + Height +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ListUserContent02{" +
                "CategoryId=" + CategoryId +
                ", Pic=" + Pic +
                ", Content='" + Content + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserNick='" + UserNick + '\'' +
                ", UserIcon='" + UserIcon + '\'' +
                ", UserLevel=" + UserLevel +
                ", UserId=" + UserId +
                ", LonX=" + LonX +
                ", LatY=" + LatY +
                ", Location='" + Location + '\'' +
                ", Distance=" + Distance +
                ", IsVip=" + IsVip +
                ", VipPoint=" + VipPoint +
                ", CommentStyle='" + CommentStyle + '\'' +
                ", WapUrl=" + WapUrl +
                ", PicCount=" + PicCount +
                ", Subject='" + Subject + '\'' +
                ", ArticleId=" + ArticleId +
                ", Title='" + Title + '\'' +
                ", Pubtime=" + Pubtime +
                ", Goods=" + Goods +
                ", Shares=" + Shares +
                ", Comments=" + Comments +
                ", Hits=" + Hits +
                ", Favorites=" + Favorites +
                '}';
    }

}
