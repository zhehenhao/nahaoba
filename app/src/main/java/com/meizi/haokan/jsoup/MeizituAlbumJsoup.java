package com.meizi.haokan.jsoup;

import com.meizi.haokan.listener.FindAlbumListener;
import com.meizi.haokan.model.Album;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MeizituAlbumJsoup extends BaseJsoup {
    private String mTitle="最新";
    private  int mpage=1;
    private String murl="https://www.mzitu.com/";
    private List<Album> albumList=new ArrayList<>();
    private FindAlbumListener findAlbumListener;

  public MeizituAlbumJsoup(String Title, int page){
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
                    findAlbumListener.onSimpleSucceed(album);
//                    LogUtils.e("img:"+img+"--name:"+name+"--:contenturl"+contenturl);
                    albumList.add(album);
                }
                if(albumList.size()>0){
                    findAlbumListener.onSucceed(albumList);
                }else{
                    findAlbumListener.onFailed("没有抓取到写真集");
                }


            }else{
                findAlbumListener.onFailed("抓取写真页失败");
            }


        }catch (Exception e){
            findAlbumListener.onFailed(e.toString());
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

    public void setFindAlbumListener(FindAlbumListener findAlbumListener) {
        this.findAlbumListener = findAlbumListener;
    }
}
