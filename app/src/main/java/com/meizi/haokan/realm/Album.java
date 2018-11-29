package com.meizi.haokan.realm;


import java.util.List;

public class Album  {
    private long updatatime;
    private List<Picture> pictures;
    private String listurl;
    private String name;            //标题

    private String img;            //海报
    private String contenturl;  //内容页面
    private  boolean iscollect;  //收藏
    private  long lastseetime;   //上一次的查看时间

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getUpdatatime() {
        return updatatime;
    }

    public void setUpdatatime(long updatatime) {
        this.updatatime = updatatime;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public String getListurl() {
        return listurl;
    }

    public void setListurl(String listurl) {
        this.listurl = listurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContenturl() {
        return contenturl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl;
    }

    public boolean isIscollect() {
        return iscollect;
    }

    public void setIscollect(boolean iscollect) {
        this.iscollect = iscollect;
    }

    public long getLastseetime() {
        return lastseetime;
    }

    public void setLastseetime(long lastseetime) {
        this.lastseetime = lastseetime;
    }



}
