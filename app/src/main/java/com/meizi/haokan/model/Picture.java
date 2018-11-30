package com.meizi.haokan.model;


public class Picture  {
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getUpdatatime() {
        return updatatime;
    }

    public void setUpdatatime(int updatatime) {
        this.updatatime = updatatime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isIscollect() {
        return iscollect;
    }

    public void setIscollect(boolean iscollect) {
        this.iscollect = iscollect;
    }

    public int getLastseetime() {
        return lastseetime;
    }

    public void setLastseetime(int lastseetime) {
        this.lastseetime = lastseetime;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    private  String domain;    //
    private  int updatatime; //创建时间
    private int id;   //序号
    private String title;  //标题
    private String img; //图片地址
   private  boolean iscollect;  //收藏
    private int  lastseetime;  //上一次的查看时间
    private String referer;  //referer

}
