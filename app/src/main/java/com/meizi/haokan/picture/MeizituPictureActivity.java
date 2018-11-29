package com.meizi.haokan.picture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
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
    private List<String> picurelist=new ArrayList<>();
    @BindView(R.id.meitupictoolbar)
    Toolbar meitupictoolbar;
    @BindView(R.id.meitupicrecyclerview)
    RecyclerView meitupicrecyclerview;
    @BindView(R.id.meitupicrefreshlayout)
    SmartRefreshLayout meitupicrefreshlayout;
    @BindView(R.id.meitupicfab)
    FloatingActionButton meitupicfab;


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

        setSupportActionBar(meitupictoolbar);


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



        meitupicrecyclerview.post(new Runnable() {
            @Override
            public void run() {
                meitupicrefreshlayout.autoRefresh();
            }
        });
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}
