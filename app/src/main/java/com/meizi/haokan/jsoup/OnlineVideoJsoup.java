package com.meizi.haokan.jsoup;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.listener.FindOnlineVideoListener;
import com.meizi.haokan.listener.FindVideoListener;
import com.meizi.haokan.model.Video;
import com.meizi.haokan.online.model.Onlinevideolist;

import java.util.Random;

public class OnlineVideoJsoup extends BaseJsoup {
    private Video video;
    private String title;
    private String url;
   private FindOnlineVideoListener listener;

    public void setListener(FindOnlineVideoListener listener) {
        this.listener = listener;
    }

    public OnlineVideoJsoup(String title, String url){
        this.title=title;
        this.url=url;
    }

    @Override
    public void run() {
        LogUtils.e("在线视频嫁娶开始");
        super.run();
        try {
            String con=getResponse(getplayurl()).replace("\\","");
            LogUtils.e(con);
            if(con==null){
                listener.onFailed("视频资源请求失败");
            }
            Onlinevideolist onlinevideolist =JSON.parseObject(con, Onlinevideolist.class);


            if(onlinevideolist.video.size()>0){
                listener.onSucceed(onlinevideolist);
            }else {
                listener.onFailed("没有找到视频资源");
            }
//            LogUtils.e(onlinevideolist.poster);
//            LogUtils.e(onlinevideolist.autoplay);
//            LogUtils.e(onlinevideolist.video.get(0)[0]);
//            LogUtils.e(onlinevideolist.video.get(0)[1]);
//            LogUtils.e(onlinevideolist.video.get(0)[2]);
//            LogUtils.e(onlinevideolist.video.get(0)[3]);
        }catch (Exception e){

        }



    }
//    https://baidu.zaoxiaosan.info/swfhd/AmateurhardcorewithschoolgirlJapaneseWakana/play-222297-0-1.html
    private String getplayurl() {
        LogUtils.e("获得视频播放链接文件"+url);
        String videostr=subString(url,"play-","-");
        Random random=new Random();
        int r=random.nextInt(100000000);
        String s=String.format("https://baidu.zaoxiaosan.info/Vcode-insert-vid-%s-t-%d.html",videostr,r);
        LogUtils.e(s);
        return  s;
    }
 private String subString(String str,String strStart,String strEnd){
     int strStartIndex = str.indexOf(strStart);
//     LogUtils.e("play-"+strStartIndex );
      String result=str.substring(strStartIndex+strStart.length());
//     LogUtils.e("截取的内容"+result);
          int strEndIndex = result.indexOf(strEnd);
//     LogUtils.e("-："+strEndIndex);
          result=result.substring(0,strEndIndex);
//     LogUtils.e("截取的内容",result);
     /* 开始截取 */
//     String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
     return result;
 }

}
