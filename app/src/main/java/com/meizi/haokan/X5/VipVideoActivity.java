package com.meizi.haokan.X5;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.blankj.utilcode.util.BarUtils;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;

public class VipVideoActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String[] jiekous={"http://api.baiyug.cn/vip/index.php?url=",
            "http://www.vipjiexi.com/yun.php?url=",
            "http://api.nepian.com/ckparse/?url=",
            "http://yun.mt2t.com/yun?url=",
            "http://y.mt2t.com/lines?url=",
            "http://www.sfsft.com/video.php?url=",
            "http://www.82190555.com/video.php?url=",
            "http://2.jx.72du.com/video.php?url=",
            "http://jx.vgoodapi.com/jx.php?url=",
            "http://www.dgua.xyz/webcloud/?url=",
            "https://2wk.com/vip.php?url=",
            "http://vip.jlsprh.com/index.php?url=",
            "http://jiexi.071811.cc/jx.php?url=",
            "http://jiexi.92fz.cn/player/vip.php?url=",
            "http://api.baiyug.cn/vip/index.php?url=",
            "http://www.82190555.com/index/qqvod.php?url=",
            "http://www.97panda.com/kkflv/index.php?url="
    };
    private String videourl;
    private String  vipjiekou;
   private Toolbar toolbar;
   private FloatingActionButton fab;
    private X5WebView x5WebView;
    private final Handler mHideHandler = new Handler();
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;


    private boolean mVisible;
//    private final Runnable mHidePart2Runnable = new Runnable() {
//        @SuppressLint("InlinedApi")
//        @Override
//        public void run() {
//            // Delayed removal of status and navigation bar
//
//            // Note that some of these constants are new as of API 16 (Jelly Bean)
//            // and API 19 (KitKat). It is safe to use them, as they are inlined
//            // at compile-time and do nothing on earlier devices.
//            x5WebView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        }
//    };
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
//    private final Runnable mShowPart2Runnable = new Runnable() {
//        @Override
//        public void run() {
//            // Delayed display of UI elements
//
//            if (toolbar != null) {
//                toolbar.setVisibility(View.VISIBLE);
//            }
//            fab.setVisibility(View.VISIBLE);
//        }
//    };
 public Window window;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vip_video);
        videourl=getIntent().getStringExtra("videourl");
        vipjiekou=spUtils.getString("vipjiekou",jiekous[0]);

        window=getWindow();
        window.setFormat(PixelFormat.TRANSLUCENT);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                x5WebView.reload();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        x5WebView=findViewById(R.id.x5webview);
//        x5WebView.setOnTouchListener(mDelayHideTouchListener);
        x5WebView.setUserAgent(3);
        if(isvipurl(videourl)){
        x5WebView.loadUrl(vipjiekou+videourl);}else {
            x5WebView.loadUrl(videourl);
        }
        mHideHandler.postDelayed(mHideRunnable, 20*1000);

    }

    private boolean isvipurl(String videourl) {
        return videourl.contains("iqiyi")||videourl.contains("v.qq")||videourl.contains("youku");
    }


    private static final boolean AUTO_HIDE = true;
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private void toggle() {
        if (mVisible) {
//            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
      if ( toolbar!= null) {
            toolbar.setVisibility(View.GONE);
        }
       fab.setVisibility(View.GONE);
      BarUtils.setStatusBarVisibility(window,false);

        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mHideRunnable);
//        mHideHandler.postDelayed(mHideRunnable, 10*1000);
    }


    private void show() {
        // Show the system bar
        x5WebView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        if ( toolbar!= null) {
            toolbar.setVisibility(View.VISIBLE);
        }
        fab.setVisibility(View.VISIBLE);
        BarUtils.setStatusBarVisibility(window,true);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, 5*1000);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vip_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case  R.id.quanping:
                quanping();
                break;

            case R.id.disquanping:
                disquanping();
                break;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    private void disquanping() {
        if(this.getResources().getConfiguration().orientation ==Configuration.ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }

    private void quanping() {
        if(this.getResources().getConfiguration().orientation ==Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        hide();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.jie1:
                setjiekou(1);
                break;
            case R.id.jie2:
                setjiekou(2);
                break;
            case R.id.jie3:
                setjiekou(3);
                break;
            case R.id.jie4:
                setjiekou(4);
                break;
            case R.id.jie5:
                setjiekou(5);
                break;
            case R.id.jie6:
                setjiekou(6);
                break;
            case R.id.jie7:
                setjiekou(7);
                break;
            case R.id.jie8:
                setjiekou(8);
                break;
            case R.id.jie9:
                setjiekou(9);
                break;
            case R.id.jie10:
                setjiekou(10);
                break;
            case R.id.jie11:
                setjiekou(11);
                break;
            case R.id.jie12:
                setjiekou(12);
                break;
            case R.id.jie13:
                setjiekou(13);

                break;
            case R.id.jie14:
                setjiekou(14);
                break;
            case R.id.jie15:
                setjiekou(15);
                break;
            case R.id.jie16:
                setjiekou(16);
                break;
            case R.id.jie17:
                setjiekou(17);
                break;
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        x5WebView.destroy();
    }

    public  void setjiekou(int i){
        vipjiekou=jiekous[i-1];
        spUtils.put("vipjiekou",vipjiekou);
        x5WebView.loadUrl(vipjiekou+videourl);

    }

    private void enableX5FullscreenFunc() {

        if (x5WebView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "开启X5全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", false);// true表示标准全屏，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            x5WebView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    private void disableX5FullscreenFunc() {
        if (x5WebView.getX5WebViewExtension() != null) {
            Toast.makeText(this, "恢复webkit初始状态", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", true);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            x5WebView.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            toggle();
        }
        return super.dispatchTouchEvent(ev);
    }
}
