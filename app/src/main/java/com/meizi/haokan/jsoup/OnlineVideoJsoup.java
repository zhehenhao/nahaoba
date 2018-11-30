package com.meizi.haokan.jsoup;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.listener.FindVideoListener;
import com.meizi.haokan.model.Video;

import java.util.Random;

public class OnlineVideoJsoup extends BaseJsoup {
    private Video video;
    private String title;
    private String url;
   private FindVideoListener findVideoListener;

    public void setFindVideoListener(FindVideoListener findVideoListener) {
        this.findVideoListener = findVideoListener;
    }

    OnlineVideoJsoup(String title,String url){
        this.title=title;
        this.url=url;
    }

    @Override
    public void run() {
        super.run();try {
            String con=getResponse(getplayurl());
            LogUtils.e(con);
        }catch (Exception e){

        }



    }
//    https://baidu.zaoxiaosan.info/swfhd/AmateurhardcorewithschoolgirlJapaneseWakana/play-222297-0-1.html
    private String getplayurl() {
        String videostr=subString(url,"play-","-");
        Random random=new Random();
        int r=random.nextInt(100000000);
        return  String.format("https://baidu.zaoxiaosan.info/Vcode-insert-vid-%s-t-%d.html",videostr,r);
    }
 private String subString(String str,String strStart,String strEnd){
     int strStartIndex = str.indexOf(strStart);
      String result=str.substring(strStartIndex+strStart.length());
          int strEndIndex = str.indexOf(result);
          result=result.substring(0,strEndIndex);
     LogUtils.e(result);
     /* 开始截取 */
//     String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
     return result;
 }

}
