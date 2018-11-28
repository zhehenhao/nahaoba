package com.meizi.haokan.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.blankj.utilcode.util.ToastUtils;

import java.io.File;

/**
 * Created by woshishui on 2018/4/28.
 */

public class IntentHelper {
    //下载先锋影音app
    public static void downxfplayapp(Context context){
        ToastUtils.showLong("将打开网页下载先锋影音，下载完成后记得安装哦，下载失败请自行从应用市场下载");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://down.xfplay.com/xfplay.apk");
        intent.setData(content_url);
        context.startActivity(intent);
    }
    //下载西瓜app
    public static void downxiguaapp(Context context){
        ToastUtils.showLong("将打开网页下载西瓜影音app，下载完成后记得安装哦，下载失败请自行从应用市场下载");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://js.client51.com/xigua.apk");
        intent.setData(content_url);
        context.startActivity(intent);
    }
    //下载迅雷app
    public static void downxunleiapp(Context context){
        ToastUtils.showLong("将打开网页下载迅雷app，下载完成后记得安装哦，下载失败请自行从应用市场下载");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://m.down.sandai.net/mobile/OfficialSite_XunLeiProductCenter.apk");
        intent.setData(content_url);
        context.startActivity(intent);
    }
    //下载快播APP
    public static void downqvodapp(Context context){
        ToastUtils.showLong("将打开网页下载快播app，下载完成后记得安装哦，下载失败请自行从应用市场下载");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://qd.kuaibo.com/android/QvodPlayer_V2.1.66_kbgw00003.apk");
        intent.setData(content_url);
        context.startActivity(intent);
    }
    //播放视频文件
 public static  void bofangVideo(Context context, String videourl){
     Intent intent = new Intent(Intent.ACTION_VIEW);
     String type   = "video/* ";
     Uri uri    = Uri.parse(videourl);
     intent.setDataAndType(uri, type);
    context.startActivity(intent);

 }

 public static  void downloadfile(Context context, String uri){
     Intent i = new Intent(Intent.ACTION_VIEW);
     i.setData(Uri.parse(uri));
     i.addCategory("android.intent.category.DEFAULT");
     context.startActivity(i);
 }
 //简单的系统下载文件
 public static void systemdownloadfile(Context context, String uri, String dirpath, String fileName){
     //创建下载任务,downloadUrl就是下载链接
     DownloadManager.Request request = null;
     if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
         request = new DownloadManager.Request(Uri.parse(uri));
         request.setDestinationInExternalPublicDir(dirpath, fileName);
//获取下载管理器
         DownloadManager downloadManager= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//将下载任务加入下载队列，否则不会进行下载
         downloadManager.enqueue(request);
     }
//指定下载路径和下载文件名

 }
    //android获取一个用于打开HTML文件的intent
    public static Intent getHtmlFileIntent(File file)
    {
        Uri uri = Uri.parse(file.toString()).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(file.toString()).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }
    //android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(File file)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "image/*");
        return intent;
    }
    //android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(File file)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }
    //android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(File file)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "text/plain");
        return intent;
    }

    //android获取一个用于打开音频文件的intent
    public static Intent getAudioFileIntent(File file)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }
    //android获取一个用于打开视频文件的intent
    public static Intent getVideoFileIntent(File file)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "video/*");
        return intent;
    }


    //android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(File file)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }


    //android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(File file)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }
    //android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(File file)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }
    //android获取一个用于打开PPT文件的intent
    public static Intent getPPTFileIntent(File file)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }
    //android获取一个用于打开apk文件的intent
    public static Intent getApkFileIntent(File file)
    {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),  "application/vnd.android.package-archive");
        return intent;
    }


}
