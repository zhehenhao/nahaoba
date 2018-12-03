package com.meizi.haokan.jsoup;

import com.meizi.haokan.listener.FindVideolistListener;
import com.meizi.haokan.model.Video;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class OnlineVideolist2Jsoup extends BaseJsoup {
    private List<Video> videoList=new ArrayList<>();
    private int sort;
    private int page;
    private String title;
    private  String url;
    private FindVideolistListener findVideolistListener;

    public void setFindVideolistListener(FindVideolistListener findVideolistListener) {
        this.findVideolistListener = findVideolistListener;
    }

    public OnlineVideolist2Jsoup(String title, int sort, int page){
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
            addtitle();
            addsort();
             addpage();
       return url;
    }

    private void addpage() {
        if(page==1){}else{
            url=url+page;
        }
    }

    private void addsort() {
        switch (sort){
            case 1:
                //什么也不做
                break;
            case 2:
                url=url+"popular/";
                break;
            case 3:
                url=url+"rated/";
                break;
            case 4:
                url=url+"discussed/";
                break;
            case 5:
                url=url+"downloaded/";
                break;
            case 6:
                url=url+"longest/";
                break;
            case 7:
                url=url+"watched/";
                break;
        }
    }

    private void addtitle() {
        switch (title){
            case "全部":
                url="http://www.avtbl.com/recent/";
                break;
            case "亚洲":
                url="http://www.avtbl.com/asian/";
                break;
            case "重口味":
                    url="http://www.avtbl.com/awesome/";
                break;
            case "少女":
                url="http://www.avtbl.com/babe/";
                break;
            case "潮吹":
                url="http://www.avtbl.com/chaochui/";
                break;
            case "大屌":
                url="http://www.avtbl.com/dadiao/";
                break;
            case "长视频":
                url="http://www.avtbl.com/duration/";
                break;
            case "口爆颜射":
                url="http://www.avtbl.com/facial/";
                break;
            case "肛交":
                url="http://www.avtbl.com/gangjiao/";
                break;
            case "男同":
                url="http://www.avtbl.com/gay/";
                break;
            case "国产":
                url="http://www.avtbl.com/guochan/";
                break;
            case "高清":
                url="http://www.avtbl.com/hd/";
                break;
            case "户外":
                url="http://www.avtbl.com/huwai/";
                break;
            case "巨乳":
                url="http://www.avtbl.com/juru/";
                break;
            case "口交":
                url="http://www.avtbl.com/koujiao/";
                break;
            case "乱伦或偷情":
                url="http://www.avtbl.com/luanlun/";
                break;
            case "美女靓妹":
                url="http://www.avtbl.com/meinv/";
                break;
            case "女同":
                url="http://www.avtbl.com/nvtong/";
                break;
            case "欧美":
                url="http://www.avtbl.com/oumei/";
                break;
            case "性派对":
                url="http://www.avtbl.com/party/";
                break;
            case "自慰":
                url="http://www.avtbl.com/qiju/";
                break;
            case "群交":
                url="http://www.avtbl.com/qunjiao/";
                break;
            case "强奸":
                url="http://www.avtbl.com/rapping/";
                break;
            case "人妻熟女":
                url="http://www.avtbl.com/renqi/";
                break;
            case "日本无码":
                url="http://www.avtbl.com/ribenwuma/";
                break;
            case "日本有码":
                url="http://www.avtbl.com/ribenyouma/";
                break;
            case "学生妹校园":
                url="http://www.avtbl.com/schoolgirl/";
                break;
            case "性爱":
                url="http://www.avtbl.com/sex/";
                break;
            case "丝袜":
                url="http://www.avtbl.com/siwa/";
                break;
            case "SM调教":
                url="http://www.avtbl.com/sm/";
                break;
            case "中文字幕":
                url="http://www.avtbl.com/subtitle/";
                break;
            case "素人":
                url="http://www.avtbl.com/suren/";
                break;
            case "3P":
                url="http://www.avtbl.com/threesome/";
                break;
            case "动漫无码":
                url="http://www.avtbl.com/wuma/";
                break;
            case "小便":
                url="http://www.avtbl.com/xiaobian/";
                break;
            case "动漫有码":
                url="http://www.avtbl.com/youma/";
                break;
            case "制服诱惑":
                url="http://www.avtbl.com/zhifu/";
                break;
            case "主播视频秀":
                url="http://www.avtbl.com/zhubo/";
                break;
            case "足交":
                url="http://www.avtbl.com/zujiao/";
                break;
                default:
                    url="http://www.avtbl.com/recent/";

                    break;

        }

    }
}
