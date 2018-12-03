package com.meizi.haokan.X5;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.activity.MainActivity;

public class X5WebviewActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private X5WebView x5WebView;
    private String homeurl="http://u6.gg/fEG8f";
    private String tengxunurl="https://m.v.qq.com/index.html";
    private String aiqiyiurl="http://m.iqiyi.com/";
    private String youkuurl="https://www.youku.com/";
 private Context context;
 private LinearLayout guanggao;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_x5_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent intent=new Intent(X5WebviewActivity.this,VipVideoActivity.class);
                intent.putExtra("videourl",x5WebView.getUrl());
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        guanggao=findViewById(R.id.guanggao);
        showbanner(guanggao);
        x5WebView=findViewById(R.id.x5webview);
        x5WebView.loadUrl(homeurl);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           webviewback();
           // super.onBackPressed();
        }
    }

    private void webviewback() {
        if(x5WebView.canGoBack()){
            x5WebView.goBack();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.x5_webview, menu);
        SearchManager searchManager= (SearchManager) getSystemService(SEARCH_SERVICE);
        MenuItem item=menu.findItem(R.id.app_bar_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                LogUtils.e(newText);
                if(newText.equals("1024")){
                    Intent intent= new Intent(X5WebviewActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btnexit) {
            AppUtils.exitApp();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tengxun) {
            x5WebView.loadUrl(tengxunurl);
            // Handle the camera action
        } else if (id == R.id.nav_aiqiyi) {
            x5WebView.loadUrl(aiqiyiurl);

        } else if (id == R.id.nav_youku) {
            x5WebView.loadUrl(youkuurl);

        } else if (id == R.id.nav_home) {
            x5WebView.loadUrl(homeurl);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
