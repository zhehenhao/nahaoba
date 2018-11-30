package com.meizi.haokan.jsoup;

import android.util.Log;

import com.meizi.haokan.listener.FindVideoListener;
import com.meizi.haokan.model.Video;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by woshishui on 2018/4/24.
 * tongxingse.info
 */

public class Xfweb3Jsoup extends BaseJsoup {
    private String weburl;   //详情页
    private List<String> xfplays=new ArrayList<>();
    private FindVideoListener findVideoListener;
    private Video video=null;


   public Xfweb3Jsoup(String url){
        this.weburl=url;
    }

    @Override
    public void run() {
        if(zhuaquxiangqing(weburl)){
            zhuaquxfplayurl(video.getPlayurl());
        };

    }



    private void zhuaquxfplayurl(String url) {
        Document doc=null;
        try {
            doc = getRequestAddHeader(url);
            if(doc!=null){
                String ss=doc.toString();
                ss=ss.substring(ss.indexOf("xfplay://")) ;
                String xfur=ss.substring(0, ss.indexOf("\""));
                Log.d("从详情页抓取的播放页链接抓取先锋链接", "抓取到的先锋链接: "+xfur);
                if(xfur.startsWith("xfplay:")){
                   video.setXfplay(xfur);
                   findVideoListener.onSucceed(video);
                }
            }
        }catch (Exception e){

        }

    }


    private boolean zhuaquxiangqing(String url) {
        Document doc=null;
        try {
            doc = getRequestAddHeader(url);
            if(doc!=null){

                String title=doc.select("body > div.p_vod > div > section.main_wrap > div.main.sec.clearfix > div.l.fl.clearfix > div > div > h1").text();
                String zhuyan=doc.select("body > div.p_vod > div > section.main_wrap > div.main.sec.clearfix > div.l.fl.clearfix > div > ul > li:nth-child(2) > p").text();
                String type=doc.select("body > div.p_vod > div > section.main_wrap > div.main.sec.clearfix > div.l.fl.clearfix > div > ul > li:nth-child(3) > p").text();
                String img=doc.select("#tab1 > a > img").attr("abs:src");
                String pingfen=doc.select("body > div.p_vod > div > section.main_wrap > div.main.sec.clearfix > div.l.fl.clearfix > div > ul > li:nth-child(6) > p").text();
                String  playurl=doc.select("body > div.p_vod > div > section.playlist.xfplay.sec > div > a").attr("abs:href");
                video=new Video();
                video.setName(title);
                video.setActor(zhuyan);
                video.setType(type);
                video.setXiangqingurl(url);
                video.setImage(img);
                video.setPlayurl(playurl);
                video.setScore(Float.parseFloat(pingfen));
                if(playurl!=null){
                    return true;
                }

            }else{
                findVideoListener.onFailed("详情页获取失败");
                return false;
            }

        }catch (Exception e){

        }

    return false;

    }


    public void setFindVideoListener(FindVideoListener findVideoListener) {
        this.findVideoListener = findVideoListener;
    }
}
