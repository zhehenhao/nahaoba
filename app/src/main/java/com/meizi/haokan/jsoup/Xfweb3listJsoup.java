package com.meizi.haokan.jsoup;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.model.Video;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Xfweb3listJsoup extends BaseJsoup {

    private String weburl;
    private List<Video> videoList=new ArrayList<>();

   public Xfweb3listJsoup(String url){
      weburl=url;
    }

    @Override
    public void run() {
        super.run();
        Document doc = null;
         doc=getRequestAddHeader(weburl);
        if (doc!=null){
            Elements lis=doc.select("body > div.p_list > div.wrapper1200 > div > section.main.fl > div.con > div.bd > ul > li");
            for(Element e:lis){
                String  name   =e.select("a").attr("title").trim();
//                String  pingfen     =e.select("a > span>span.fr").text();
                String  img         =e.select("img").attr("abs:src").toString();
                String  xiangqingurl =e.select("a.img_wrap").attr("abs:href").toString();
//                String  updatetime  =e.select("p").text();
//                LogUtils.eTag("视频名字",name);
//                LogUtils.eTag("img",img);
//                LogUtils.eTag("详情页",xiangqingurl);
                Video video     = new Video();
                video.setName(name);
                video.setImage(img);
                video.setXiangqingurl(xiangqingurl);
//                video.setUpdatatime(updatetime);
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
