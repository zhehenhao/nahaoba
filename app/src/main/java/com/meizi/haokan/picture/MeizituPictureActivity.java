package com.meizi.haokan.picture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.MeizituPictureJsoup;
import com.meizi.haokan.listener.FindPictureListener;
import com.meizi.haokan.model.Picture;
import com.meizi.haokan.picture.adapter.PictureAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeizituPictureActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener {
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";
    private List<Picture> picturelist =new ArrayList<>();
    @BindView(R.id.meitupictoolbar)
    Toolbar meitupictoolbar;
    @BindView(R.id.meitupicrecyclerview)
    RecyclerView meitupicrecyclerview;
    @BindView(R.id.meitupicrefreshlayout)
    SmartRefreshLayout meitupicrefreshlayout;
    @BindView(R.id.meitupicfab)
    FloatingActionButton meitupicfab;
    private  String title;
    private  String url;
   private MeizituPictureJsoup jsoup;
   private PictureAdapter adapter;
   private boolean isover=false;

   private Handler uihandler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           switch (msg.what){
               case 3011:
                   adapter.notifyDataSetChanged();
                   meitupicrefreshlayout.finishLoadMore(500);
                   meitupicrefreshlayout.finishRefresh(500);

                   break;
               case 3012:
                   isover=true;
                   adapter.notifyDataSetChanged();
                   meitupicrefreshlayout.finishLoadMore(500);
                   meitupicrefreshlayout.finishRefresh(500);
                   ToastUtils.showLong("所有图片加载完毕");

                   break;
               case 3013:

                   meitupicrefreshlayout.finishLoadMore(false);
                   meitupicrefreshlayout.finishRefresh(false);
                   ToastUtils.showLong((String) msg.obj);

                   break;
           }
       }
   };

    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, MeizituPictureActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, desc);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizitu_picture);
        ButterKnife.bind(this);
        title=getIntent().getStringExtra(EXTRA_TITLE);
        url=getIntent().getStringExtra(EXTRA_URL);
        setSupportActionBar(meitupictoolbar);
        meitupictoolbar.setSubtitle(title);


        meitupicfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initview();


    }

    private void initview() {
        meitupicrefreshlayout.setOnRefreshListener(this);
        meitupicrefreshlayout.setOnLoadMoreListener(this);
        adapter=new PictureAdapter(this, picturelist);
        meitupicrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        meitupicrecyclerview.setAdapter(adapter);
        meitupicrecyclerview.post(new Runnable() {
            @Override
            public void run() {
                meitupicrefreshlayout.autoRefresh();
            }
        });


    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
if(isover){
    ToastUtils.showLong("本写真集所有图片已经加载完毕");
    meitupicrefreshlayout.finishLoadMore(1000);
}
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if(isover){
            ToastUtils.showLong("本写真集所有图片已经加载完毕");
            meitupicrefreshlayout.finishRefresh(1000);
            return;

        }
        picturelist.clear();
        requstdata();
    }

    private  void requstdata(){
        jsoup=new MeizituPictureJsoup(title,url);
        jsoup.setFindPictureListener(new FindPictureListener() {
            @Override
            public void onSucceed(List<Picture> list) {
                Message message =new Message();
                message.what=3012;
                message.obj=list;
                uihandler.sendMessage(message);
            }

            @Override
            public void onFailed(String e) {
                Message message =new Message();
                message.what=3013;
                message.obj=e;
                uihandler.sendMessage(message);
            }

            @Override
            public void onSimpleSucceed(Picture picture) {
                picturelist.add(picture);
                Message message =new Message();
                message.what=3011;
                uihandler.sendMessage(message);
            }
        });
        jsoup.start();

    }
}
