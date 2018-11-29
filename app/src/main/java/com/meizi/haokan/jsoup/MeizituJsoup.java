package com.meizi.haokan.jsoup;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.picture.MeizituFragment;
import com.meizi.haokan.realm.Album;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MeizituJsoup extends BaseJsoup {
    private String mTitle="最新";
    private  int mpage=1;
    private String murl="https://www.mzitu.com/";
    private List<Album> albumList=new ArrayList<>();
    private FindPicturelistListener findPicturelistListener;

  public MeizituJsoup(String Title, int page){
        this.mTitle=Title;
        this.mpage=page;

    }

    @Override
    public void run() {
        super.run();
        this.murl=getUrl(mTitle,mpage);
        try {
            Document doc=null;
            doc=getRequestWithReferer(murl,murl);
            if(doc!=null){

                Elements  es=doc.select("#pins > li");
                for (Element e:es){
                    String img=e.select(" a > img").attr("data-original");
                    String name=e.select(" a > img").attr("alt");
                    String contenturl=e.select("a").attr("href");
                    Album album=new Album();
                    album.setImg(img);
                    album.setName(name);
                    album.setContenturl(contenturl);
//                    LogUtils.e("img:"+img+"--name:"+name+"--:contenturl"+contenturl);
                    albumList.add(album);
                }
                if(albumList.size()>0){
                    findPicturelistListener.onSucceed(albumList);
                }else{
                    findPicturelistListener.onFailed("没有抓取到写真集");
                }


            }else{
                findPicturelistListener.onFailed("抓取写真页失败");
            }


        }catch (Exception e){
            findPicturelistListener.onFailed(e.toString());
        }



    }


    private String getUrl(String mTitle, int mpage) {
      murl=getTitlurl(mTitle);
      if(mpage>1){
          murl=murl+"page/"+mpage+"/";
      }
      if(mTitle=="推荐"){
         return "https://www.mzitu.com/best/";
      }
      return murl;
    }

    private String getTitlurl(String mTitle) {
      String url="https://www.mzitu.com/";
      switch (mTitle){
          case "最新":
           break;
          case "最热":
              url="https://www.mzitu.com/hot/";
              break;
          case "推荐":
                url="https://www.mzitu.com/best/";
              break;
          case "性感妹子":
              url="https://www.mzitu.com/xinggan/";
              break;
          case "日本妹子":
              url="https://www.mzitu.com/japan/";
              break;
          case "台湾妹子":
              url="https://www.mzitu.com/taiwan/";
              break;
          case "清纯妹子":
              url="https://www.mzitu.com/mm/";
              break;
      }
      return url;
    }

    public void setFindPicturelistListener(FindPicturelistListener findPicturelistListener) {
        this.findPicturelistListener = findPicturelistListener;
    }
}
