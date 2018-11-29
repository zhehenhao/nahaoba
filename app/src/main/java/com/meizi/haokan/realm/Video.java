package com.meizi.haokan.realm;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Video {

    private String updatatime;  //更新时间
    @PrimaryKey
    private String name;   //视频名
    private String daoyan;  //导游
    private String actor;   //演员
    private String type;   //类型
    private String area;   //地区
    private String image;  //照片

    private String bigimg;//大照片
    private float score; //评分
    private String havama;// 有码 无码
    private   long   playbackprogress;   //播放进度
    private   long   playnumber;   //播放次数
    private  long   latestplaytime;  //上次播放时间

    private Boolean iscollect;  // 是否收藏
    @Index
    private String listurl;  //所属的列表页
    private String xiangqingurl; //详情页
    private String playurl;   //播放页
    private String xfplay; //先锋链接
    private String xigua; //西瓜链接
    private String kuaibo; //快播链接
    private String online; //在线

    public String getBigimg() {
        return bigimg;
    }

    public void setBigimg(String bigimg) {
        this.bigimg = bigimg;
    }

    public String getHavama() {
        return havama;
    }

    public void setHavama(String havama) {
        this.havama = havama;
    }

    public void setPlaybackprogress(long playbackprogress) {
        this.playbackprogress = playbackprogress;
    }

    public void setPlaynumber(long playnumber) {
        this.playnumber = playnumber;
    }

    public void setLatestplaytime(long latestplaytime) {
        this.latestplaytime = latestplaytime;
    }

    public String getUpdatatime() {
        return updatatime;
    }

    public void setUpdatatime(String updatatime) {
        this.updatatime = updatatime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDaoyan() {
        return daoyan;
    }

    public void setDaoyan(String daoyan) {
        this.daoyan = daoyan;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public long  getPlaybackprogress() {
        return playbackprogress;
    }

    public void setPlaybackprogress(int playbackprogress) {
        this.playbackprogress = playbackprogress;
    }

    public long getPlaynumber() {
        return playnumber;
    }

    public void setPlaynumber(int playnumber) {
        this.playnumber = playnumber;
    }

    public long  getLatestplaytime() {
        return latestplaytime;
    }

    public void setLatestplaytime(int latestplaytime) {
        this.latestplaytime = latestplaytime;
    }

    public Boolean getIscollect() {
        return iscollect;
    }

    public void setIscollect(Boolean iscollect) {
        this.iscollect = iscollect;
    }

    public String getListurl() {
        return listurl;
    }

    public void setListurl(String listurl) {
        this.listurl = listurl;
    }

    public String getXiangqingurl() {
        return xiangqingurl;
    }

    public void setXiangqingurl(String xiangqingurl) {
        this.xiangqingurl = xiangqingurl;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public String getXfplay() {
        return xfplay;
    }

    public void setXfplay(String xfplay) {
        this.xfplay = xfplay;
    }

    public String getXigua() {
        return xigua;
    }

    public void setXigua(String xigua) {
        this.xigua = xigua;
    }

    public String getKuaibo() {
        return kuaibo;
    }

    public void setKuaibo(String kuaibo) {
        this.kuaibo = kuaibo;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }





}
