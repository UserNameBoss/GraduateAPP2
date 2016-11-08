package com.example.yangxiaolong.graduateapp.domain;

/**
 * Created by yangxiaolong on 2016/11/8.
 */
public class MessageDomain {

    private String Content;
    private String FromUserIcon;
    private int FromUserId;
    private String FromUserNick;
    private boolean IsPublish;
    private boolean IsReaded;
    private long MessageId;
    private long Pubtime;
    private int UserLevel;
    private Extend extend;


    public MessageDomain(String content, String fromUserIcon, int fromUserId, String fromUserNick, boolean isPublish, boolean isReaded, long messageId, long pubtime, int userLevel, Extend extend) {
        Content = content;
        FromUserIcon = fromUserIcon;
        FromUserId = fromUserId;
        FromUserNick = fromUserNick;
        IsPublish = isPublish;
        IsReaded = isReaded;
        MessageId = messageId;
        Pubtime = pubtime;
        UserLevel = userLevel;
        this.extend = extend;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getFromUserIcon() {
        return FromUserIcon;
    }

    public void setFromUserIcon(String fromUserIcon) {
        FromUserIcon = fromUserIcon;
    }

    public int getFromUserId() {
        return FromUserId;
    }

    public void setFromUserId(int fromUserId) {
        FromUserId = fromUserId;
    }

    public String getFromUserNick() {
        return FromUserNick;
    }

    public void setFromUserNick(String fromUserNick) {
        FromUserNick = fromUserNick;
    }

    public boolean isPublish() {
        return IsPublish;
    }

    public void setPublish(boolean publish) {
        IsPublish = publish;
    }

    public boolean isReaded() {
        return IsReaded;
    }

    public void setReaded(boolean readed) {
        IsReaded = readed;
    }

    public long getMessageId() {
        return MessageId;
    }

    public void setMessageId(long messageId) {
        MessageId = messageId;
    }

    public long getPubtime() {
        return Pubtime;
    }

    public void setPubtime(long pubtime) {
        Pubtime = pubtime;
    }

    public int getUserLevel() {
        return UserLevel;
    }

    public void setUserLevel(int userLevel) {
        UserLevel = userLevel;
    }

    public Extend getExtend() {
        return extend;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }
}
