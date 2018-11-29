package com.meizi.haokan.utils;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.Base.AppWebConfig;

public class AppUpdata {
    public final  static  String appupdateurl="https://raw.githubusercontent.com/zhehenhao/MyApplication2/master/AppConfig/AppWebConfig.json";
    public static  String getAppupdateurl(){

       String doc=JsoupUtils.getResponse(appupdateurl);
        LogUtils.e(doc.toString());
        return doc;
    }
    public static AppWebConfig getAppUpdataJsonObject(){
        AppWebConfig appWebConfig =JSON.parseObject(getAppupdateurl(),AppWebConfig.class);
        return appWebConfig;
    }

}
