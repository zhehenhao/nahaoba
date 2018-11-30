package com.meizi.haokan.jsoup;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.listener.FindAlbumListener;
import com.meizi.haokan.model.Album;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class InfoAlbumJsoup extends BaseJsoup {
    private String mTitle="全部";
    private int page=1;
    private  int sort=1;
    private List<Album> albumList=new ArrayList<>();
    private FindAlbumListener findAlbumListener;

    public InfoAlbumJsoup(String title,int page, int sort){
        mTitle=title;
        this.page=page;
        this.sort=sort;
    }

    @Override
    public void run() {
        super.run();
 Document doc=null;
  try {
      doc=getRequestAddHeader(geturl());
      if(doc!=null){
          Elements es=doc.select("body > div.p_list > div.wrapper1200 > div > section.main.fl > div.con > div.bd > ul > li");
          for (Element e:es){
              String img=e.select(" a > img").attr("abs:src");
              String title=e.select("a").attr("title");
              String url=e.select("a").attr("abs:href");
              LogUtils.e(img+"--"+title+"--"+url);
              Album album=new Album();
              album.setImg(img);
              album.setName(title);
              album.setContenturl(url);
              findAlbumListener.onSimpleSucceed(album);
              albumList.add(album);
          }
          if(albumList.size()>0){
              findAlbumListener.onSucceed(albumList);
          }

      }else{
          findAlbumListener.onFailed("写真图集获取失败");

      }

  }catch (Exception e){
      findAlbumListener.onFailed("写真图集以外出错");
  }

    }

    private String geturl() {
        String url="https://baidu.zaoxiaosan.info/artmv/all/index_1_1_1.html";;
        switch (mTitle){
            case "全部":
                url=String.format("https://baidu.zaoxiaosan.info/artmv/all/index_%d_1_%d.html",sort,page);
            break;
            case "唯美清纯":
                url=String.format("https://baidu.zaoxiaosan.info/artwm/index_%d_1_%d.html",sort,page);
                break;

            case "美腿丝袜":
                url=String.format("https://baidu.zaoxiaosan.info/artmt/index_%d_1_%d.html",sort,page);
                break;

            case "激情套图":
                url= String.format("https://baidu.zaoxiaosan.info/artjq/index_%d_1_%d.html",sort,page);
                break;

            case "亚洲色图":
                url=String.format("https://baidu.zaoxiaosan.info/artyz/index_%d_1_%d.html",sort,page);
                break;

            case "性爱自拍":
                url= String.format("https://baidu.zaoxiaosan.info/artzp/index_%d_1_%d.html",sort,page);
                break;

            case "欧美色图":
                url= String.format("https://baidu.zaoxiaosan.info/artom/index_%d_1_%d.html",sort,page);
            break;
            case "淫荡熟女":
                url= String.format("https://baidu.zaoxiaosan.info/artyd/index_%d_1_%d.html",sort,page);
            break;
            case "玉乳明星":
                url= String.format("https://baidu.zaoxiaosan.info/artmx/index_%d_1_%d.html",sort,page);
            break;
            case "SM色图":
                url= String.format("https://baidu.zaoxiaosan.info/artsm/index_%d_1_%d.html",sort,page);
            break;
            case "同性图区":
                url=String.format("https://baidu.zaoxiaosan.info/artga/index_%d_1_%d.html",sort,page);
                break;
        }
        return  url;
    }

    public void setFindAlbumListener(FindAlbumListener findAlbumListener) {
        this.findAlbumListener = findAlbumListener;
    }
}
