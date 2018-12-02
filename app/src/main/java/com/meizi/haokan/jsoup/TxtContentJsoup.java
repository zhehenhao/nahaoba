package com.meizi.haokan.jsoup;

import android.content.Context;

import com.meizi.haokan.listener.FindTxtListener;
import com.meizi.haokan.model.Txt;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TxtContentJsoup extends BaseJsoup {

    private Txt txt;
    private String url;
    FindTxtListener findTxtListener;
   public  TxtContentJsoup(String url){
       this.url=url;

}
    public void setFindTxtListener(FindTxtListener findTxtListener) {
        this.findTxtListener = findTxtListener;
    }


    @Override
    public void run() {
        super.run();
        Document doc=null;
        try {
            doc=getRequestAddHeader(url);
            if(doc!=null){
               String  e=doc.select("#content_news").text();

               if(e!=null){
                   Txt txt=new Txt();
                   txt.setContent(e);
                   findTxtListener.onSimplySucceed(txt);
               }else {
                   findTxtListener.onFailed("资源内容获取失败");
               }

            }else {
                findTxtListener.onFailed("资源获取失败");

            }

        }catch (Exception e){

        }

    }
}
