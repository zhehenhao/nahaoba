package com.meizi.haokan.jsoup;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.listener.FindTxtListener;
import com.meizi.haokan.model.Txt;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class TxtListJsoup extends BaseJsoup {
    private List<Txt> txtList=new ArrayList<>();
    private Context context;

    private String title;
    private int page=1;
    private int sort=1;
    private FindTxtListener findTxtListener;

    public void setFindTxtListener(FindTxtListener findTxtListener) {
        this.findTxtListener = findTxtListener;
    }


    public  TxtListJsoup(Context context, String title, int page, int sort){
        this.context=context;
        this.title=title;
        this.page=page;
        this.sort=sort;
    }


    @Override
    public void run() {
        super.run();
        Document doc=null;
        try {
            doc=getRequestAddHeader(gettxturl());
            LogUtils.e(doc);

            if(doc!=null){
                Elements es=doc.select("body > div.p_list > div.wrapper1200 > div > section.main.fl > div.con > div.bd > ul > li");
                for(Element e:es){
                    String title=e.select("a").attr("title");
                    String conturl=e.select("a").attr("abs:href");
                    String pulishtime=e.select(" p:nth-child(3)").text();
                    String  desribe=e.select("div > p").text();
                    Txt txt=new Txt();
                    txt.setTitle(title);
                    txt.setContenturl(conturl);
                    txt.setUpdatatime(pulishtime);
                    txt.setIntroduction(desribe);
                    LogUtils.e(title,conturl,pulishtime,desribe);
                    findTxtListener.onSimplySucceed(txt);
                    txtList.add(txt);
                }
                if(txtList.size()>0){
                findTxtListener.onSucceed(txtList);}
            }else{
                findTxtListener.onFailed("获取小说资源失败");
            }
        }catch (Exception e){

        }
    }
    private String gettxturl() {
        String ur;
        switch (title){
            case "全部":
                ur=String.format("https://baidu.zaoxiaosan.info/txtsex/index_%d_1_%d.html",sort,page);
                break;
            case "都市激情":
                ur=String.format("https://baidu.zaoxiaosan.info/xsjq/index_%d_1_%d.html",sort,page);
                break;
            case "家庭乱伦":
                ur=String.format("https://baidu.zaoxiaosan.info/xsll/index_%d_1_%d.html",sort,page);
                break;
            case "淫荡人妻":
                ur=String.format("https://baidu.zaoxiaosan.info/xsyd/index_%d_1_%d.html",sort,page);
                break;
            case "校园春色":
                ur=String.format("https://baidu.zaoxiaosan.info/xscs/index_%d_1_%d.html",sort,page);
                break;
            case "武侠情色":
                ur=String.format("https://baidu.zaoxiaosan.info/xswx/index_%d_1_%d.html",sort,page);
                break;
            case "暴力强奸":
                ur=String.format("https://baidu.zaoxiaosan.info/xsbl/index_%d_1_%d.html",sort,page);
                break;
            case "情色笑话":
                ur=String.format("https://baidu.zaoxiaosan.info/xsxh/index_%d_1_%d.html",sort,page);
                break;
            case "性爱技巧":
                ur=String.format("https://baidu.zaoxiaosan.info/xsxa/index_%d_1_%d.html",sort,page);
                break;
            case "长篇连载" :
                ur=String.format("https://baidu.zaoxiaosan.info/xslz/index_%d_1_%d.html",sort,page);
             break;
            default:   ur=String.format("https://baidu.zaoxiaosan.info/txtsex/index_%d_1_%d.html",sort,page);
        break;
        }
        LogUtils.e(ur);
        return  ur;
    }


}
