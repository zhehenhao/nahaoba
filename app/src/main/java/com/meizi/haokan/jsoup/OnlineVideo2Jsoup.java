package com.meizi.haokan.jsoup;

import com.meizi.haokan.listener.FindOnlineVideo2Listener;
import com.meizi.haokan.listener.FindVideoListener;
import com.meizi.haokan.model.Video;
import com.meizi.haokan.online.model.OnlineVideo2;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class OnlineVideo2Jsoup extends BaseJsoup {
    private List<OnlineVideo2> video2s=new ArrayList<>();
    private String url;
private FindOnlineVideo2Listener findOnlineVideo2Listener;

    public void setFindOnlineVideo2Listener(FindOnlineVideo2Listener findOnlineVideo2Listener) {
        this.findOnlineVideo2Listener = findOnlineVideo2Listener;
    }

    public  OnlineVideo2Jsoup(String url){
        this.url=url;
    }

    @Override
    public void run() {
        super.run();
        Document doc=null;
        try{
            doc=getRequestAddHeader(url);
            if(doc!=null){
                Elements es=doc.select("#player_html5_api > source");
                for (Element e:es){
                    String videourl=e.attr("src");
                    String label=e.attr("label");
                    String type=e.attr("type");
                    OnlineVideo2 video2=new OnlineVideo2();
                    video2.setVideourl(videourl);
                    video2.setLabel(label);
                    video2.setType(type);
                    findOnlineVideo2Listener.onSimpleSucceed(video2);
                    video2s.add(video2);

                }
                if(video2s.size()>0){
                    findOnlineVideo2Listener.onSucceed(video2s);
                }else{
                    findOnlineVideo2Listener.onFailed("没有找到视频资源");
                }
            }else {
                findOnlineVideo2Listener.onFailed("资源获取失败");
            }
        }catch (Exception e){

        }
    }
}
