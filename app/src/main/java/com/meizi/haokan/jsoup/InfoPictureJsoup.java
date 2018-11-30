package com.meizi.haokan.jsoup;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.listener.FindPictureListener;
import com.meizi.haokan.model.Picture;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class InfoPictureJsoup extends BaseJsoup {
    private  String url;
    private String title;
    private List<Picture> pictureList=new ArrayList<>();
    private FindPictureListener findPictureListener;
    private  int id=0;
    public  InfoPictureJsoup(String title,String url){
        this.title=title;
        this.url=url;
    }

    @Override
    public void run() {
        super.run();
        Document doc=null;
        try {
            doc=getRequestAddHeader(url);
            if(doc!=null){
                String page=doc.select("#page > a").last().text().replace("尾","").replace("页","").trim();
                LogUtils.e("页数是"+page);
                int p=Integer.parseInt(page);
                for(int i=1;i<=p;i++){
                    String ur=url+"index"+i+".html";
                    zhuaquinfopicture(i,ur);
                }
                if(pictureList.size()>0){
                    findPictureListener.onSucceed(pictureList);
                }
            }else {
                findPictureListener.onFailed("图片资源获取失败");
            }
        }catch (Exception E){

        }


    }

    private void zhuaquinfopicture(int i,String ur) {
        Document doc=null;
        doc=getRequestAddHeader(ur);
        if(doc!=null){
            Elements es=doc.select("#content_news > div > img");
            for (Element e:es){
                String img=e.attr("abs:src");
                LogUtils.e(img);
                Picture picture=new Picture();
                picture.setId(id+1);
                picture.setTitle(title);
                picture.setRefer(ur);
                picture.setImg(img);
                pictureList.add(picture);
                findPictureListener.onSimpleSucceed(picture);
            }
        }
        else{
            findPictureListener.onFailed("第"+i+"页图片资源获取失败");
        }

    }

    public void setFindPictureListener(FindPictureListener findPictureListener) {
        this.findPictureListener = findPictureListener;
    }
}
