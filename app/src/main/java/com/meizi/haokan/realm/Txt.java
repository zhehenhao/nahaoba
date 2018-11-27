package com.meizi.haokan.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Txt extends RealmObject {
    private  int updatatime;
    @PrimaryKey
    private String title;
    private String introduction;
    private String content;
    private  long  lastreadtime;
    private  long  readtimeprogress;
    private String img;
    private Boolean iscollect;
    private String listurl;  //从什么列表也抓取的
    private String contenturl; //内容页面


    public String getContenturl() {
        return contenturl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl;
    }



    public String getListurl() {
        return listurl;
    }

    public void setListurl(String listurl) {
        this.listurl = listurl;
    }




    public int getUpdatatime() {
        return updatatime;
    }

    public void setUpdatatime(int updatatime) {
        this.updatatime = updatatime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long  getLastreadtime() {
        return lastreadtime;
    }

    public void setLastreadtime(int lastreadtime) {
        this.lastreadtime = lastreadtime;
    }

    public long  getReadtimeprogress() {
        return readtimeprogress;
    }

    public void setReadtimeprogress(int readtimeprogress) {
        this.readtimeprogress = readtimeprogress;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getIscollect() {
        return iscollect;
    }

    public void setIscollect(Boolean iscollect) {
        this.iscollect = iscollect;
    }
}
