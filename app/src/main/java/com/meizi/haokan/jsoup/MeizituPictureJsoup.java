package com.meizi.haokan.jsoup;

import com.meizi.haokan.listener.FindMeiziPictureListener;
import com.meizi.haokan.model.Picture;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class MeizituPictureJsoup extends BaseJsoup {
    private String mtitle;
    private String murl;
    private List<Picture> pictureList=new ArrayList<>();
  private FindMeiziPictureListener findMeiziPictureListener;
    public MeizituPictureJsoup(String title, String url){
      this.mtitle=title;
      this.murl=url;
    }

    @Override
    public void run() {
        super.run();
        Document doc=null;
        try {
            doc=getRequestWithReferer(murl,murl);
            if(doc!=null){

                int  pagenum=Integer.parseInt(doc.select("body > div.main > div.content > div.pagenavi > a:nth-child(7) > span").text().trim());
                for(int i=1;i<=pagenum;i++){
//                    String url=murl+"/"+i;
                    zhuaqumeizipicture(i,murl+"/"+i);

                }

            if(pictureList.size()>0){
                    findMeiziPictureListener.onSucceed(pictureList);
            }

            }else{
                findMeiziPictureListener.onFailed("写真集抓取失败");
            }

        }catch (Exception e){

        }



    }

    private void zhuaqumeizipicture(int i,String s) {
        Document doc=getRequestWithReferer(s,murl);
        if(doc!=null){
            String img=doc.select("body > div.main > div.content > div.main-image > p > a > img").attr("src");
            Picture picture=new Picture();
            picture.setImg(img);
            picture.setReferer(s);
            picture.setTitle(mtitle);
            picture.setId(i);
            picture.setDomain("https://www.mzitu.com");
            pictureList.add(picture);
                  findMeiziPictureListener.onSimpleSucceed(picture);


        }else{
            findMeiziPictureListener.onFailed("第"+i+"张照片抓取失败");
        }

    }




    public void setFindMeiziPictureListener(FindMeiziPictureListener findMeiziPictureListener) {
        this.findMeiziPictureListener = findMeiziPictureListener;
    }
}
