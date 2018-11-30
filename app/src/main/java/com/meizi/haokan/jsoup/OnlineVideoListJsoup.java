package com.meizi.haokan.jsoup;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.listener.FindVideolistListener;
import com.meizi.haokan.model.Video;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OnlineVideoListJsoup extends BaseJsoup {
    private Context context;
    private List<Video> videoList=new ArrayList<>();
    private String title;
    private  int page=1;
    private  int sort=1;

   private FindVideolistListener findVideolistListener;

    public void setFindVideolistListener(FindVideolistListener findVideolistListener) {
        this.findVideolistListener = findVideolistListener;
    }

    public  OnlineVideoListJsoup(String title,int page, int sort){
        this.title=title;
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
                Elements elements= doc.select("#img_resize > li");
                LogUtils.e("元素文本", "zhuaquonlinelist: "+elements.toString());
                for(Element e:elements){
                    String  title=e.select(" p.name > a").text();

                    //剧照
                    String img=e.select(" a > img").attr("src");
                    //评分
                    String pingfen=e.select(" p.name > span").text();

                    //详情页
                    String  xiangqingye=e.select(" p.name > a").attr("abs:href");
                    //播放页
                     String bofangye=e.select(" p:nth-child(3) > span > a").attr("abs:href");
                    Video video=new Video();
                    video.setName(title);
                    video.setImage(img);
                    video.setXiangqingurl(xiangqingye);
                    video.setPlayurl(bofangye);
                     findVideolistListener.onSimpleSucceed(video);
                    videoList.add(video);
                }
                if(videoList.size()>0){
                    findVideolistListener.onSucceed(videoList);
                }

            }else {
                findVideolistListener.onfailed("在线视频资源页获取失败");

            }

        }catch (Exception e){
                findVideolistListener.onfailed("在线视频资源异常出错");
        }

    }

    private String geturl(){
        Random random=new Random();
        int r=random.nextInt(133333);
        String ur;
        switch (title){
            case "全部":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/index_%d_%d_%d.html",sort,r,page);
                break;
            case "高清色情":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_HDPorn/index_%d_%d_%d.html",sort,r,page);
                break;
            case "成人少女":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_TeenGirls18/index_%d_%d_%d.html",sort,r,page);
                break;
            case "色情视频":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_HardcorePornVideos/index_%d_%d_%d.html",sort,r,page);
                break;
            case "色情爱好":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_AmateurPorn/index_%d_%d_%d.html",sort,r,page);
                break;
            case "女同性爱":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_LesbianSex/index_%d_%d_%d.html",sort,r,page);
                break;
            case "欧洲色情":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_EuroPorn/index_%d_%d_%d.html",sort,r,page);
                break;
            case "亚洲女孩":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_AsianGirlsFucking/index_%d_%d_%d.html",sort,r,page);
                break;
            case "金发女孩":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_BlondeGirlsFucking/index_%d_%d_%d.html",sort,r,page);
                break;
            case "日本色情":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_Japaneseporn/index_%d_%d_%d.html",sort,r,page);
                break;
            case "双重插入":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_DoublePenetration/index_%d_%d_%d.html",sort,r,page);
                break;
            case "爆菊肛交":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_AnalAssFucking/index_%d_%d_%d.html",sort,r,page);
                break;
            case "辣妹性爱":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_BabesFucking/index_%d_%d_%d.html",sort,r,page);
                break;
            case "巨大":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_HugeTits/index_%d_%d_%d.html",sort,r,page);
                break;
            case "面部射精":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_FacialCumShots/index_%d_%d_%d.html",sort,r,page);
                break;

            case "成熟性爱":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_MatureSex/index_%d_%d_%d.html",sort,r,page);
                break;

            case "射精":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_Cumshots/index_%d_%d_%d.html",sort,r,page);
                break;

            case "自慰":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_Masturbating/index_%d_%d_%d.html",sort,r,page);
                break;

            case "肥肥":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_BBWFatSex/index_%d_%d_%d.html",sort,r,page);
                break;

            case "轮奸":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_GangBangFucking/index_%d_%d_%d.html",sort,r,page);
                break;

            case "性SM":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_FetishSex/index_%d_%d_%d.html",sort,r,page);
                break;

            case "群交":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_GroupSex/index_%d_%d_%d.html",sort,r,page);
                break;

            case "熟女":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_MILF/index_%d_%d_%d.html",sort,r,page);
                break;

            case "足交":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_FootFetish/index_%d_%d_%d.html",sort,r,page);
                break;

            case "拳交":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_FistingSex/index_%d_%d_%d.html",sort,r,page);
                break;
            case "明星":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_Celebrity/index_%d_%d_%d.html",sort,r,page);
                break;
            case "黑发女孩":
                ur=String.format("https://baidu.zaoxiaosan.info/swf/all/t_BrunetteGirlsFucking/index_%d_%d_%d.html",sort,r,page);
                break;

            default:
                    ur=String.format("https://baidu.zaoxiaosan.info/swf/all/index_%d_%d_%d.html",sort,r,page);
                    break;

        }
        return ur;
    }
}
