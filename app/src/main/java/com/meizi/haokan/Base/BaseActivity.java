package com.meizi.haokan.Base;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;

import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;


import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.utils.AppUpdata;
import com.meizi.haokan.utils.IntentHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

import static com.meizi.haokan.Base.AppConfig.appfilepath;

public  class BaseActivity extends AppCompatActivity {

    public static SPUtils spUtils= SPUtils.getInstance();

    public Handler basehandle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    File file= (File) msg.obj;
                    startinstallApk(file);
                    break;
                case 102:

                    break;
            }
        }
    };


    public static  String newapkfile=null;




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
        Intent intent = new Intent(Intent.ACTION_VIEW);
             Uri uri = Uri.parse(url);
            intent.setData (uri);
            startActivity(intent);

    }

}
