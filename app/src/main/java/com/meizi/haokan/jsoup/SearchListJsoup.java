package com.meizi.haokan.jsoup;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.listener.FindVideolistListener;
import com.meizi.haokan.model.Video;
import com.tencent.smtt.sdk.URLUtil;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SearchListJsoup extends BaseJsoup {
    private List<Video> videoList=new ArrayList<>();
    private int sort;
    private int page;
    private String title;
    private  String url;
    private FindVideolistListener findVideolistListener;

    public void setFindVideolistListener(FindVideolistListener findVideolistListener) {
        this.findVideolistListener = findVideolistListener;
    }

    public SearchListJsoup(String title, int sort, int page){
        this.title=title;
        this.sort=sort;
        this.page=page;

    }


    @Override
    public void run() {
        super.run();
        String ur= geturl();
        Document doc=null;
        try{
            doc=getRequestAddHeader(ur);
            if(doc!=null){
                Elements es=doc.select("#content > div.col-xs-12.col-sm-12.col-md-10 > div > div.panel-body.panel-padding > ul>li");
                for(Element e:es){
                    String title=e.select("div >a").attr("title");
                    String img=e.select("div>a>div>img").attr("src");
                    String url=e.select("div >a").attr("abs:href");
                    Video video=new Video();
                    video.setName(title);
                    video.setImage(img);
                    video.setPlayurl(url);
                    findVideolistListener.onSimpleSucceed(video);
                    videoList.add(video);

                }
                if(videoList.size()>0){
                    findVideolistListener.onSucceed(videoList);}
            }else{
                findVideolistListener.onfailed("资源页内容获取失败");
            }

        }catch (Exception e){

            findVideolistListener.onfailed("资源页内容获取失败");

        }

    }

    private String geturl() {
        addsearch();
        addsort();
        addpage();
        LogUtils.e(url);
        LogUtils.e(EncodeUtils.urlEncode(url));
        return url;
    }

    private void addsearch() {
        url="http://www.avtbl.com/search/video/?s="+title;
    }

    private void addpage() {
        if(page==1){}else{
            url=url+"&page="+page;
        }
    }

    private void addsort() {
        switch (sort){
            case 1:
                //什么也不做
                url=url+"&o=popular";
                break;
            case 2:
                url=url+"&o=popular";
                break;
            case 3:
                url=url+"&o=rated";
                break;
            case 4:
                url=url+"&o=discussed";
                break;
            case 5:
                url=url+"&o=downloaded";
                break;
            case 6:
                url=url+"&o=longest";
                break;
            case 7:
                url=url+"&o=watched";
                break;
        }
    }
}
