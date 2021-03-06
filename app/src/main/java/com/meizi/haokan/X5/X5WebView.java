package com.meizi.haokan.X5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class X5WebView extends WebView {
	TextView title;
	WebSettings webSetting;
	public final static int android=1;
	public final static int iphone=2;
	public final static int pc=3;
	public final static int weixin=4;
    public Context context;
	@SuppressLint("SetJavaScriptEnabled")
	public X5WebView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
		context=arg0;
		this.setWebViewClient(client);
		this.setWebChromeClient(webChromeClient);
		// this.setWebChromeClient(chromeClient);
		// WebStorage webStorage = WebStorage.getInstance();
		initWebViewSettings();
		this.getView().setClickable(true);
	}


	public void setUserAgent(int i){
		if(i==1){

		}else if(i==2){
			setUserAgent(" \tMozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12F70 Safari/600.1.4");

		}else if(i==3){
			setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
		}else if(i==4){
			String weixinurl=getnowUserAgent()+" MicroMessenger/6.6.7";
			setUserAgent(weixinurl);
		}
	}
	public String getnowUserAgent(){
		if(webSetting==null){
			webSetting=getSettings();
		}
		return webSetting.getUserAgentString();
	}

	public void setUserAgent(String userAgent){
		if(webSetting==null){
			webSetting = this.getSettings();}
			webSetting.setUserAgent(userAgent);
	}
	private void initWebViewSettings() {
		 webSetting = this.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
		webSetting.setAllowFileAccess(true);
		webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		webSetting.setSupportZoom(true);
		webSetting.setBuiltInZoomControls(false);
		webSetting.setUseWideViewPort(true);
		webSetting.setSupportMultipleWindows(true);
		// webSetting.setLoadWithOverviewMode(true);
		webSetting.setAppCacheEnabled(true);
		// webSetting.setDatabaseEnabled(true);
		webSetting.setDomStorageEnabled(true);
		webSetting.setGeolocationEnabled(true);
		webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
		// webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
		webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
		// webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
		webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK );
		CookieSyncManager.createInstance(context);
		CookieSyncManager.getInstance().sync();
		// this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
		// settings 的设计
	}

	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		boolean ret = super.drawChild(canvas, child, drawingTime);
//		canvas.save();
//		Paint paint = new Paint();
//		paint.setColor(0x7fff0000);
//		paint.setTextSize(24.f);
//		paint.setAntiAlias(true);
//		if (getX5WebViewExtension() != null) {
//			canvas.drawText(this.getContext().getPackageName() + "-pid:"
//					+ android.os.Process.myPid(), 10, 50, paint);
//			canvas.drawText(
//					"X5  Core:" + QbSdk.getTbsVersion(this.getContext()), 10,
//					100, paint);
//		} else {
//			canvas.drawText(this.getContext().getPackageName() + "-pid:"
//					+ android.os.Process.myPid(), 10, 50, paint);
//			canvas.drawText("Sys Core", 10, 100, paint);
//		}
//		canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
//		canvas.drawText(Build.MODEL, 10, 200, paint);
//		canvas.restore();
		return ret;
	}

	public X5WebView(Context arg0) {
		super(arg0);
		setBackgroundColor(85621);
	}


	private WebViewClient client = new WebViewClient() {
		/**
		 * 防止加载网页时调起系统浏览器
		 */
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	};
	private WebChromeClient webChromeClient=new WebChromeClient() {

		@Override
		public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
								   JsResult arg3) {
			return super.onJsConfirm(arg0, arg1, arg2, arg3);
		}

//		View myVideoView;
//		View myNormalView;
//		IX5WebChromeClient.CustomViewCallback callback;
//
//		// /////////////////////////////////////////////////////////
//		//
//		/**
//		 * 全屏播放配置
//		 */
//		@Override
//		public void onShowCustomView(View view,
//									 IX5WebChromeClient.CustomViewCallback customViewCallback) {
//			FrameLayout normalView = this.myNormalView;
//			ViewGroup viewGroup = (ViewGroup) normalView.getParent();
//			viewGroup.removeView(normalView);
//			viewGroup.addView(view);
//			myVideoView = view;
//			myNormalView = normalView;
//			callback = customViewCallback;
//		}
//
//		@Override
//		public void onHideCustomView() {
//			if (callback != null) {
//				callback.onCustomViewHidden();
//				callback = null;
//			}
//			if (myVideoView != null) {
//				ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
//				viewGroup.removeView(myVideoView);
//				viewGroup.addView(myNormalView);
//			}
//		}

		@Override
		public boolean onJsAlert(WebView arg0, String arg1, String arg2,
								 JsResult arg3) {
			/**
			 * 这里写入你自定义的window alert
			 */
			return super.onJsAlert(null, arg1, arg2, arg3);
		}
	};

}
