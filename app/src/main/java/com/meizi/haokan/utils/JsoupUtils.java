package com.meizi.haokan.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupUtils {
    /*
     * jsoup添加header请求
     * */
    public static Document getRequestAddHeader(String url) {

        Document doc = null;
        try {

            doc = Jsoup.connect(url)
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Referer", "https://www.baidu.com/")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .ignoreContentType(true)
                    .maxBodySize(0)
                    .timeout(60000)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
    //post get网页
    public static Document postRequestAddHeader(String url,String postbody,String referer) {
        Document doc = null;
        String mreferer=null;
        if(referer==null){
            mreferer="https://www.baidu.com/";
        }else {
            mreferer=referer;
        }

        try {
            if(referer==null){

            }
            doc = Jsoup.connect(url)
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Referer", mreferer)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .maxBodySize(0)
                    .timeout(60000)
                    .requestBody(postbody)
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }



    //post get网页
    public static Document postRequestAddHeader(String url,String postbody) {
        return postRequestAddHeader(url,postbody,"https://www.baidu.com/");

    }

}
