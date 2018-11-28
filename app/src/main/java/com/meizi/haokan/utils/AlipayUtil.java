package com.meizi.haokan.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

import java.net.URISyntaxException;

/**
 * 支付宝红包赏金领取*/
public class AlipayUtil
{
  private static final String ALIPAY_PACKAGE_NAME = "com.eg.android.AlipayGphone";
  private static final String INTENT_URL_FORMAT = "intent://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https%3A%2F%2Fqr.alipay.com%2F{urlCode}%3F_s%3Dweb-other&_t=1472443966571#Intent;scheme=alipayqr;package=com.eg.android.AlipayGphone;end";
  
  public static boolean hasInstalledAlipayClient(Context context)
  {
   PackageManager packageManager = context.getPackageManager();
    boolean bool = false;
    try
    {
     PackageInfo packageInfo = packageManager.getPackageInfo("com.eg.android.AlipayGphone", 0);
      if (packageInfo != null) {
        bool = true;
      }
      return bool;
    }
    catch (NameNotFoundException e)
    {
      e.printStackTrace();
    }
    return false;
  }


  public static void AlipaySaomiao(Activity activity,String payUrl){
      try {
          Intent intent = new Intent();
          intent.setAction("android.intent.action.VIEW");
          //实现payUrl
          intent.setData(Uri.parse("alipayqr://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=" + payUrl));
          activity.startActivity(intent);
      }
      catch (Exception e) {

      }

  }
  public static  void AlipayOpenUrl(Activity activity,String url){
      try {

          Intent intent=new Intent();
          intent.setAction("android.intent.action.VIEW");
          Uri murl=Uri.parse(url);
         intent.setData(murl);
         intent.setClassName("com.eg.android.AlipayGphone","com.alipay.mobile.quinox.SchemeLauncherActivity");
           activity.startActivity(intent);

      }catch (Exception e){
          e.printStackTrace();
      }

  }
  public static boolean startAlipayClient(Activity paramActivity, String paramString)
  {
    return startIntentUrl(paramActivity, "intent://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https%3A%2F%2Fqr.alipay.com%2F{urlCode}%3F_s%3Dweb-other&_t=1472443966571#Intent;scheme=alipayqr;package=com.eg.android.AlipayGphone;end".replace("{urlCode}", paramString));
  }
  
  public static boolean startIntentUrl(Activity mActivity, String url)
  {
    try
    {
      mActivity.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME));
      return true;
    }
    catch (ActivityNotFoundException e)
    {      e.printStackTrace();
      return false;
    }
    catch (URISyntaxException e)
    {
      e.printStackTrace();
    }
    return false;
  }
}