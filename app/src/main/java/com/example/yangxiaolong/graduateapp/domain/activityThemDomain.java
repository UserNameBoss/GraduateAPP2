package com.example.yangxiaolong.graduateapp.domain;

/**
 * Created by yangxiaolong on 2016/11/3.
 */
public class ActivityThemDomain {

    /**
     * SubjectId : 61
     * Name : 约她陪我过情人节
     * Icon : http://img.xb.huabao.me/wp-content/uploads/subject/20160214/56bff43f0abfd.png
     * Intro : 小伙伴们，又到情人节了。想不想让Ta陪你过节呢？赶紧来参与这个成功了会性福，失败了也不会尴尬的游戏吧！发“陪我过情人节，好不好？”给Ta。看看Ta是什么反应。说不定Ta就会陪你过节呢
     * Banner : http://img.xb.huabao.me/wp-content/uploads/subject/20160214/56bff43f32768.jpg
     * BeginTime : 1455379200000
     * EndTime : 1455984000000
     * Members : 104
     * Pubtime : 1455379200000
     */


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

    public void setSubjectId(int SubjectId) {
        this.SubjectId = SubjectId;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setIcon(String Icon) {
        this.Icon = Icon;
    }

    public void setIntro(String Intro) {
        this.Intro = Intro;
    }

    public void setBanner(String Banner) {
        this.Banner = Banner;
    }

    public void setBeginTime(long BeginTime) {
        this.BeginTime = BeginTime;
    }

    public void setEndTime(long EndTime) {
        this.EndTime = EndTime;
    }

    public void setMembers(int Members) {
        this.Members = Members;
    }

    public void setPubtime(long Pubtime) {
        this.Pubtime = Pubtime;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public String getName() {
        return Name;
    }

    public String getIcon() {
        return Icon;
    }

    public String getIntro() {
        return Intro;
    }

    public String getBanner() {
        return Banner;
    }

    public long getBeginTime() {
        return BeginTime;
    }

    public long getEndTime() {
        return EndTime;
    }

    public int getMembers() {
        return Members;
    }

    public long getPubtime() {
        return Pubtime;
    }


    public ActivityThemDomain(int subjectId, String name, String icon, String intro, String banner, long beginTime, long endTime, int members, long pubtime) {
        SubjectId = subjectId;
        Name = name;
        Icon = icon;
        Intro = intro;
        Banner = banner;
        BeginTime = beginTime;
        EndTime = endTime;
        Members = members;
        Pubtime = pubtime;
    }
}
