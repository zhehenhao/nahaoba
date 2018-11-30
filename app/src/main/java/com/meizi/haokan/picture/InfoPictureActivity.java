package com.meizi.haokan.picture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.InfoPictureJsoup;
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

public class InfoPictureActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener {
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.infopicrecyclerview)
    RecyclerView infopicrecyclerview;
    @BindView(R.id.infopicrefreshlayout)
    SmartRefreshLayout infopicrefreshlayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private String title;
    private String url;
    private PictureAdapter adapter;
    private List<Picture> pictureList=new ArrayList<>();
    private InfoPictureJsoup jsoup;
    private boolean isover=false;
    private Handler uihandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 3301:
                    isover=true;
                    infopicrefreshlayout.finishRefresh(600);
                    infopicrefreshlayout.finishLoadMore(600);
                    ToastUtils.showLong("本图集已经全部加载完成");
                    break;
                case  3302:
                    infopicrefreshlayout.finishRefresh(600);
                    infopicrefreshlayout.finishLoadMore(600);
                    adapter.notifyDataSetChanged();

                    break;
                case  3303:
                    ToastUtils.showLong((String) msg.obj);
                    infopicrefreshlayout.finishRefresh(false);
                    infopicrefreshlayout.finishLoadMore(false);
                    break;
            }
        }
    };

    public static Intent newIntent(Context context, String title, String url) {
        Intent intent = new Intent(context, InfoPictureActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_URL, url);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_picture);
        ButterKnife.bind(this);
          setSupportActionBar(toolbar);
        title=getIntent().getStringExtra(EXTRA_TITLE);
        url=getIntent().getStringExtra(EXTRA_URL);
        toolbar.setTitle(title);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        infopicrefreshlayout.setOnLoadMoreListener(this);
        infopicrefreshlayout.setOnRefreshListener(this);
        adapter=new PictureAdapter(this,pictureList);
        infopicrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        infopicrecyclerview.setAdapter(adapter);
        infopicrecyclerview.post(new Runnable() {
            @Override
            public void run() {
                infopicrefreshlayout.autoRefresh();
            }
        });

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if(isover){return;}else{
            refreshLayout.autoRefresh();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if(isover){return;}
        pictureList.clear();
        requestdata();
    }

    private void requestdata() {
        jsoup=new InfoPictureJsoup(title,url);
        jsoup.setFindPictureListener(new FindPictureListener() {
            @Override
            public void onSucceed(List<Picture> list) {
                Message message=new Message();
                message.what=3301;
                uihandler.sendMessage(message);

            }

            @Override
            public void onFailed(String e) {
                Message message=new Message();
                message.what=3303;
                message.obj=e;
                uihandler.sendMessage(message);

            }

            @Override
            public void onSimpleSucceed(Picture picture) {
            pictureList.add(picture);
                Message message=new Message();
                message.what=3302;
                uihandler.sendMessage(message);
            }
        });

        jsoup.start();
    }
}
