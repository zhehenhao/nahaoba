package com.meizi.haokan.Base;


import com.alibaba.fastjson.JSONObject;

public class AppUpdataJsonObject  {


    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUpdatadescribe() {
        return updatadescribe;
    }

    public void setUpdatadescribe(String updatadescribe) {
        this.updatadescribe = updatadescribe;
    }

    public Boolean getForced() {
        return forced;
    }

    public void setForced(Boolean forced) {
        this.forced = forced;
    }

    private String appname;   //应用名字
    private int version;        //应用的版本
    private String downloadurl;  //下载的地址
    private String img;             //应用照片
    private String updatadescribe;  //下载说明
    private Boolean forced;

}
