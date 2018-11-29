package com.meizi.haokan.jsoup;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.model.Video;

import org.jsoup.nodes.Document;

/* xf.0ady.info
* */
public class Xfweb2Jsoup extends BaseJsoup {
    private  String weburl;//详情页

    private FindVideoListener findVideoListener;
    private Video video=null;
    private String playurl=null;

    public Xfweb2Jsoup(String url){
        this.weburl=url;
    }

    @Override
    public void run() {
        super.run();
        LogUtils.e("运行开始");
        if(zhuaquxiangqing(weburl)){
            zhuaquxfplayurl(playurl);
        }
    }

    private void zhuaquxfplayurl(String url) {
        Document doc=null;
        try {
            doc = getRequestAddHeader(url);
            if(doc!=null){
                LogUtils.e("播放页抓取成功");
               String burl=doc.select("#main > div > div:nth-child(1) > script:nth-child(1)").attr("abs:src");
                LogUtils.e("burl抓取结果"+burl);
               if(burl.contains("playdata")){
                   LogUtils.e("判断burl正确");
               }else{
                   String s=doc.toString();
                   String  t=s.substring(s.indexOf("src=\"/playdata")+5);
                    burl="https://xf.0ady.info"+t.substring(0,t.indexOf("\""));
                   LogUtils.e("判断burl错误重新抓取"+burl);
                }
                String docs=getRequestAddHeader(burl).toString();
                LogUtils.e("burl获得内容"+docs);
                if(docs!=null){
                    String  ss=docs.substring(docs.indexOf("xfplay://")) ;
                    String xfur=ss.substring(0, ss.indexOf("$xfplay"));
                    Log.d("从详情页抓取的播放页链接抓取先锋链接", "抓取到的先锋链接: "+xfur);
                    if(xfur.startsWith("xfplay:")){
                        video.setXfplay(xfur);
                        findVideoListener.onSucceed(video);
                    }
                    LogUtils.e(xfur);
                }else{
                    LogUtils.e("找不到js脚本内容");
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
               String title=doc.select("#main > div.mains > div.movie_a > div.movie_a_left > div.movie_a_left_title > h1").text();
                String zhuyan=doc.select("#main > div.mains > div.movie_a > div.movie_a_left > div:nth-child(3) > a").text();
                String type=doc.select("body > div.p_vod > div > section.main_wrap > div.main.sec.clearfix > div.l.fl.clearfix > div > ul > li:nth-child(3) > p").text();
//                String bigimg=doc.select("#main > div.mains > div.movie_c > div:nth-child(7) > img:nth-child(18)").attr("src");
                 playurl=doc.select("#mhlist > ul > ul > li > a").attr("abs:href");
                video=new Video();
                video.setName(title);
                video.setActor(zhuyan);
                video.setType(type);
                video.setXiangqingurl(url);
//                video.setBigimg(bigimg);
                video.setPlayurl(playurl);
                 if(playurl!=null){
                    return true;
                }
                LogUtils.e(playurl);
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
