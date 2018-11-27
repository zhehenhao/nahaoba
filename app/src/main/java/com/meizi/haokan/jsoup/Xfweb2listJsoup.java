package com.meizi.haokan.jsoup;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.realm.Video;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Xfweb2listJsoup extends BaseJsoup {
    private String weburl;
    private List<Video> videoList=new ArrayList<>();

    public Xfweb2listJsoup(String url){
        weburl=url;
    }

    @Override
    public void run() {
        super.run();
        Document doc = null;
        doc=getRequestAddHeader(weburl);
        if (doc!=null){
            Elements pics=doc.select("#main > div.mains > div.pre_b > div > div.pre_b_classes_pic");
            Elements cons=doc.select("#main > div.mains > div.pre_b > div > div.pre_b_classes_text");
            for(int i=0;i<pics.size();i++){
                Element con=cons.get(i);
                Element pic=pics.get(i);
                String  name   =con.select(" h1 > a").text();
                String  xiangqingurl =con.select("h1 > a").attr("abs:href");
                String  img         =pic.select("img").attr("src");
                String  havema  =  con.select("ul > li:nth-child(4)").text().replace("状态：","");
                String actor=con.select("ul > li:nth-child(1)").text().replace("主演：","");
                String updatetime=con.select("ul > li:nth-child(3)").text().replace("时间：","");

//                LogUtils.eTag("视频名字",name);
//                LogUtils.eTag("img",img);
//                LogUtils.eTag("详情页",xiangqingurl);
                Video video     = new Video();
                video.setActor(actor);
                video.setHavama(havema);
                video.setName(name);
                video.setImage(img);
                video.setXiangqingurl(xiangqingurl);
                video.setUpdatatime(updatetime);
                videoList.add(video);
            }
            LogUtils.e("抓取结束");
            if(videoList.size()>0){
                mFindVideolistListener.onSucceed(videoList);
                LogUtils.e("成功回调结束");

            }else{

                mFindVideolistListener.onfailed("网页视频列表抓取不到视频内容");
                LogUtils.e("失败回调结束");
            }

        }else{
            mFindVideolistListener.onfailed("网页获取失败");
            LogUtils.e("网页回去失败回调结束");
        }


    }

}
