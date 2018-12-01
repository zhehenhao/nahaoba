package com.meizi.haokan.jsoup;

import com.meizi.haokan.listener.FindXmovieListener;
import com.meizi.haokan.model.Xmovie;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;

/*http://www.ygdy8.net
* */
public class MovieListJsoup extends BaseJsoup {

private String title="最新";
private  int page=1;
private HashMap hashMap;
private  String url;
private FindXmovieListener findXmovieListener;
private List<Xmovie> xmovies;
//    hashMap =new HashMap();
//       hashMap.put("最新","http://www.ygdy8.net/html/gndy/dyzz/list_23_%d.html");
//       hashMap.put("国内","http://www.ygdy8.net/html/gndy/china/list_4_%d.html");
//       hashMap.put("欧美","http://www.ygdy8.net/html/gndy/oumei/list_7_%d.html");
//       hashMap.put("日韩","http://www.ygdy8.net/html/gndy/rihan/list_6_%d.html");
//       hashMap.put("综合","http://www.ygdy8.net/html/gndy/jddy/list_63_%d.html");


   public  MovieListJsoup(String type,int page){
       switch (type){
           case "最新":
               url=String.format("http://www.ygdy8.net/html/gndy/dyzz/list_23_%d.html",page);
               break;
           case "国内":
               url=String.format("http://www.ygdy8.net/html/gndy/china/list_4_%d.html",page);
               break;
           case "欧美":
               url=String.format("http://www.ygdy8.net/html/gndy/oumei/list_7_%d.html",page);
               break;
           case "日韩":
               url=String.format("http://www.ygdy8.net/html/gndy/rihan/list_6_%d.html",page);
               break;
           case "综合":
               url=String.format("http://www.ygdy8.net/html/gndy/jddy/list_63_%d.html",page);
               break;
               default:
                   url=String.format("http://www.ygdy8.net/html/gndy/dyzz/list_23_%d.html",page);
                   break;
       }

   }

    @Override
    public void run() {
        super.run();
        Document doc=null;
        try {
            doc=getRequestAddHeader(url);
            if(doc!=null){
                Elements es=doc.select("#header > div > div.bd2 > div.bd3 > div.bd3r > div.co_area2 > div.co_content8 > ul > table");
                for(Element e:es){
                    String titile=e.select(" tbody > tr:nth-child(2) > td:nth-child(2) > b > a").text();
                    String wurl=e.select(" tbody > tr:nth-child(2) > td:nth-child(2) > b > a").attr("abs:src");
                    String desribe=e.select(" tbody > tr:nth-child(4) > td").text();
                    Xmovie xmovie=new Xmovie();
                        xmovie.setTitle(title);
                        xmovie.setUrl(wurl);
                        xmovie.setDescribe(desribe);
                        findXmovieListener.onSimpleSucceed(xmovie);
                        xmovies.add(xmovie);
                }
                findXmovieListener.onSucceed(xmovies);


            }else{
                findXmovieListener.onFailed("获取资源失败");
            }

        }catch (Exception e){

        }
    }

    public void setFindXmovieListener(FindXmovieListener findXmovieListener) {
        this.findXmovieListener = findXmovieListener;
    }
}
