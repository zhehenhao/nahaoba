package com.meizi.haokan.Base;


import com.alibaba.fastjson.JSONObject;

public class AppWebConfig {


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


    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }



    public String getZfburl() {
        return zfburl;
    }

    public void setZfburl(String zfburl) {
        this.zfburl = zfburl;
    }

    public String getZfbsearchword() {
        return zfbsearchword;
    }

    public void setZfbsearchword(String zfbsearchword) {
        this.zfbsearchword = zfbsearchword;
    }

    public String getSilenceapp() {
        return silenceapp;
    }

    public void setSilenceapp(String silenceapp) {
        this.silenceapp = silenceapp;
    }

    public String getSilenceapp2() {
        return silenceapp2;
    }

    public void setSilenceapp2(String silenceapp2) {
        this.silenceapp2 = silenceapp2;
    }

    public String getSilenceweburl() {
        return silenceweburl;
    }

    public void setSilenceweburl(String silenceweburl) {
        this.silenceweburl = silenceweburl;
    }

    public String getGoogleappid() {
        return googleappid;
    }

    public void setGoogleappid(String googleappid) {
        this.googleappid = googleappid;
    }

    public String getGooglebanner() {
        return googlebanner;
    }

    public void setGooglebanner(String googlebanner) {
        this.googlebanner = googlebanner;
    }

    public String getGooglechaping() {
        return googlechaping;
    }

    public void setGooglechaping(String googlechaping) {
        this.googlechaping = googlechaping;
    }


    public String getGooglereward() {
        return googlereward;
    }

    public void setGooglereward(String googlereward) {
        this.googlereward = googlereward;
    }


    public String getGooglenative() {
        return googlenative;
    }

    public void setGooglenative(String googlenative) {
        this.googlenative = googlenative;
    }

    public String getUmengappkey() {
        return umengappkey;
    }

    public void setUmengappkey(String umengappkey) {
        this.umengappkey = umengappkey;
    }

    public String getUmengchannel() {
        return umengchannel;
    }

    public void setUmengchannel(String umengchannel) {
        this.umengchannel = umengchannel;
    }

    public String getYouhuoci() {
        return youhuoci;
    }

    public void setYouhuoci(String youhuoci) {
        this.youhuoci = youhuoci;
    }

    private String  youhuoci;// 诱惑词
    private String  zfburl;  //支付宝网页
    private  String zfbsearchword; //支付宝搜索码
    private String  silenceapp;  //静默安装的app
    private  String silenceapp2; //静默安装的app2
    private  String silenceweburl; //静默打开的网页
    private  String googleappid;  //谷歌应用id
    private  String googlebanner; //谷歌横幅
    private  String googlechaping; // 谷歌插屏
    private String umengappkey;//友盟统计
    private  String umengchannel; //友盟统计
    private  String googlereward; //谷歌激励
    private  String googlenative; //谷歌原生
    private String appname;   //应用名字
    private String versionname; //版本名称
    private int version;        //应用的版本
    private String downloadurl;  //下载的地址
    private String img;             //应用照片
    private String updatadescribe;  //下载说明
    private Boolean forced; //是否强制更新

}
