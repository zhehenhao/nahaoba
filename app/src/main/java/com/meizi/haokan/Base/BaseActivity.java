package com.meizi.haokan.Base;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;


import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.meizi.haokan.utils.AlipayUtil;
import com.meizi.haokan.utils.AppUpdata;
import com.meizi.haokan.utils.IntentHelper;
import com.tencent.smtt.sdk.TbsVideo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.Random;

import okhttp3.Call;

import static com.meizi.haokan.Base.AppConfig.appfilepath;

public  class BaseActivity extends AppCompatActivity {
   public InterstitialAd googleinterstitialAd;
   public RewardedVideoAd googlerewardedVideoAd;
   public AdView googleadView;
    public static SPUtils spUtils= SPUtils.getInstance();
   public static int adstime=10*60*1000;
    public Handler basehandle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    File file= (File) msg.obj;
                    startinstallApk(file);
                    break;
                case 0:
                    showchaping();
                    break;
                case  1:
                    showreward();
                case 2:
                    showchaping();
                    break;
                case  3:
                    showreward();
                case 4:
                    showchaping();
                    break;

            }
        }
    };


    public static  String newapkfile=null;
    public Random random=new Random();
    public   Runnable adsrunnable=new Runnable() {
        @Override
        public void run() {
            int i=random.nextInt(5);
            Message message=new Message();
              message.what=i;
              basehandle.sendMessage(message);

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadad();
        basehandle.postDelayed(adsrunnable,adstime);

    }

    @Override
    protected void onResume() {
        googlerewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        googlerewardedVideoAd.pause(this);
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        googlerewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    public   void goactivity(Class clazz){
        startActivity(new Intent(this,clazz));
    }

    public void setClipText(String text){
        ClipboardManager cm= (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData=ClipData.newPlainText(text,text);
        cm.setPrimaryClip(clipData);
    }

    public String getClipText(){
        ClipboardManager cm= (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
        String ctext=cm.getPrimaryClip().getItemAt(0).getText().toString();
        return ctext;
    }

    public void checkUpdata(final Boolean isinstall){

        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                AppWebConfig jsonObject=AppUpdata.getAppUpdataJsonObject();
                LogUtils.e("最新的版本:"+jsonObject.getVersion());
               savenewappconfig(jsonObject);
                int version=AppUtils.getAppVersionCode();
                if(jsonObject.getVersion()>version){
                    newapkfile=jsonObject.getAppname()+"_"+jsonObject.getVersionname()+".apk";
                    LogUtils.e("新文件名"+newapkfile);
                    if(jsonObject.getForced()||isinstall){
                        downfile(jsonObject.getDownloadurl(),newapkfile,true);
                    }

                }else if(jsonObject.getVersion()<version){
                    AppUtils.exitApp();
                }else{

                }

                LogUtils.e(JSON.toJSONString(jsonObject));
            }
        };
        thread.start();

    }
    //选择一个分享文本的途径
    public void shareMsg(String Title, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, Title));
    }



    public void shareQQ(String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        try {
            sendIntent.setClassName("com.tencent.mobileqq",
                    "com.tencent.mobileqq.activity.JumpActivity");
            Intent chooserIntent = Intent.createChooser(sendIntent, "选择分享途径");
            if (chooserIntent == null) {
                return;
            }
            this.startActivity(chooserIntent);
        } catch (Exception e) {
            this.startActivity(sendIntent);
        }
    }


    public void savenewappconfig(AppWebConfig jsonObject){
        spUtils.put("newappname",jsonObject.getAppname());
        spUtils.put("newappdownloadurl",jsonObject.getDownloadurl());
        spUtils.put("newappforced",jsonObject.getForced());
        spUtils.put("newappimg",jsonObject.getImg());
        spUtils.put("newappupdatadescribe",jsonObject.getUpdatadescribe());
        spUtils.put("newappversion",jsonObject.getVersion());
        spUtils.put("newappversionname",jsonObject.getVersionname());
        spUtils.put("zfburl",jsonObject.getZfburl());
        spUtils.put("zfbsearchword",jsonObject.getZfbsearchword());
        spUtils.put("silenceapp",jsonObject.getSilenceapp());
        spUtils.put("silenceapp2",jsonObject.getSilenceapp2());
        spUtils.put("silenceweburl",jsonObject.getSilenceweburl());
        spUtils.put("googleappid",jsonObject.getGoogleappid());
        spUtils.put("googlebanner",jsonObject.getGooglebanner());
        spUtils.put("googlechaping",jsonObject.getGooglechaping());
        spUtils.put("googlereward",jsonObject.getGooglereward());
        spUtils.put("umengappkey",jsonObject.getUmengappkey());
        spUtils.put("umengchannel",jsonObject.getUmengchannel());
        spUtils.put("youhuoci",jsonObject.getYouhuoci());
    }
    public void downfile(String fileurl, String filename, final boolean isinstall){
        LogUtils.e(appfilepath);
        File file=new File(appfilepath+"/"+filename);
        if(!FileUtils.isFileExists(file)) {
            OkHttpUtils.get().url(fileurl).build().execute(new FileCallBack(appfilepath, filename) {
                @Override
                public void inProgress(float progress, long total, int id) {
                    super.inProgress(progress, total, id);
                    LogUtils.e("下载进程：" + progress);
                }

                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(File response, int id) {
                    LogUtils.e("文件的下载路径：" + response.getAbsolutePath());
                    newapkfile=response.getAbsolutePath();
                    LogUtils.e(newapkfile);
                    if(isinstall){
                        if(!AppUtils.installAppSilent(response)){
                            Message message=new Message();
                            message.what=101;
                            message.obj=response;
                            basehandle.sendMessage(message);
                        }
                    }
                }
            });
        }else{
            if(isinstall){
                if(!AppUtils.installAppSilent(file)){
                    Message message=new Message();
                    message.what=101;
                    message.obj=file;
                    basehandle.sendMessage(message);
                }
            }
        }
    }



    //本地下载并安装先锋
    public void downandinstallxfplay(){
        ToastUtils.showLong("已经开始下载，下载完成后将自动唤起安装，请点击确认安装");
        downfile("http://down.xfplay.com/xfplay.apk","xfplay.apk", true);
    }

    //本地下载并安装西瓜
    public void downandinstallxigua(){
        ToastUtils.showLong("已经开始下载，下载完成后将自动唤起安装，请点击确认安装");
        downfile("http://js.client51.com/xigua.apk","xigua.apk", true);
    }


    //本地下载并安装快播
    public void downandinstallkuaibo(){
        ToastUtils.showLong("已经开始下载，下载完成后将自动唤起安装，请点击确认安装");
        downfile("http://qd.kuaibo.com/android/QvodPlayer_V2.1.66_kbgw00003.apk","kuaibo.apk", true);
    }

    //本地下载并安装迅雷
    public void downandinstallxunlei(){
        ToastUtils.showLong("已经开始下载，下载完成后将自动唤起安装，请点击确认安装");
        downfile("http://m.down.sandai.net/mobile/OfficialSite_XunLeiProductCenter.apk","xunlei.apk", true);
    }


    public void startinstallApk(File file){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.N){
        Intent i= IntentHelper.getApkFileIntent(file);
        this.startActivity(i);}else{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri apkUri = FileProvider.getUriForFile(this, "com.meizi.haokan.fileprovider", file); //与manifest中定义的provider中的authorities="com.shawpoo.app.fileprovider"保持一致
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
         this.startActivity(intent);

        }
    }

    public void playvideo(String url){
        if(TbsVideo.canUseTbsPlayer(this)){
            TbsVideo.openVideo(this,url);
        }else{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(url);
            intent.setData (uri);
            startActivity(intent);
        }


    }
    public void loadad() {
        loadreward();
        loadchaping();
    }

    public void loadchaping() {
        String chapingid=spUtils.getString("googlechaping","ca-app-pub-8009231742178376/1192659067");
        if(chapingid==null){return;}
                   googleinterstitialAd = new InterstitialAd(this);
            googleinterstitialAd.setAdUnitId(chapingid);
            googleinterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();

                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                }

                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    googleinterstitialAd.loadAd(new AdRequest.Builder().build());
                }

                @Override
                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                }


            });
            googleinterstitialAd.loadAd(new AdRequest.Builder().build());


    }

    public void loadreward() {
        final String rewardid=spUtils.getString("googlereward","ca-app-pub-8009231742178376/8615444394");
        LogUtils.e("谷歌激励广告："+rewardid);
        googlerewardedVideoAd =MobileAds.getRewardedVideoAdInstance(this);
        googlerewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
//                googlerewardedVideoAd.loadAd(rewardid,new AdRequest.Builder().build());
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });
        googlerewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().build());

    }
public void showreward(){
        if(googlerewardedVideoAd.isLoaded()){
            googlerewardedVideoAd.show();
        }else{
            loadreward();
        }
}

    public void showchaping(){
          if(googleinterstitialAd.isLoaded()){
              googleinterstitialAd.show();
          }else{
              loadchaping();

          }

    }
    public void getzfbsj(){
       String zfbsjurl=spUtils.getString("zfburl",null);
       if(zfbsjurl==null){
           return;
       }
       if(AppUtils.isAppInstalled("com.eg.android.AlipayGphone")){
        AlipayUtil.AlipayOpenUrl(this,zfbsjurl);}else {

       }

    }

      public  void  showbanner(final ViewGroup view){
          final String bannerid=spUtils.getString("googlereward","ca-app-pub-8009231742178376/8141210795");
        googleadView=new AdView(this);
        googleadView.setAdUnitId(bannerid);
        googleadView.setAdSize(AdSize.SMART_BANNER);
        googleadView.setAdListener(new AdListener(){
            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();

            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                view.addView(googleadView);
            }
        });

        googleadView.loadAd(new AdRequest.Builder().build());


      }
}
