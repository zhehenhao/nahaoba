package com.meizi.haokan.online;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.OnlineVideoJsoup;
import com.meizi.haokan.jsoup.OnlineVideoListJsoup;
import com.meizi.haokan.listener.FindVideolistListener;
import com.meizi.haokan.listener.VideoClickListener;
import com.meizi.haokan.model.Video;
import com.meizi.haokan.online.adapter.OnlineViedeAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class OnlineViewlistFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.onlinerecyclerview)
    RecyclerView onlinerecyclerview;
    @BindView(R.id.onlinerefreshlayout)
    SmartRefreshLayout onlinerefreshlayout;
    Unbinder unbinder;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private String title;
    private int page = 1;
    private int sort = 1;
    private List<Video> videoList=new ArrayList<>();
    private OnlineViedeAdapter adapter;
    private OnlineVideoListJsoup jsoup;
    private Context context;

    private static final String ARG_SECTION_NUMBER = "title";
private Handler uihandler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 3401:
                ToastUtils.showLong("视频资源请求完毕");
                break;
            case 3402:
                onlinerefreshlayout.finishRefresh(600);
                onlinerefreshlayout.finishLoadMore(600);
                adapter.notifyDataSetChanged();
                break;

            case 3403:
                onlinerefreshlayout.finishRefresh(false);
                onlinerefreshlayout.finishLoadMore(false);
                ToastUtils.showLong((String) msg.obj);

                break;
            case 3404:
                Video video= (Video) msg.obj;
                OnlineVideoJsoup jsoup=new OnlineVideoJsoup(video.getName(),video.getPlayurl());
                jsoup.start();
                break;
        }
    }
};
    public OnlineViewlistFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static OnlineViewlistFragment newInstance(String title) {
        OnlineViewlistFragment fragment = new OnlineViewlistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_SECTION_NUMBER);
        }
        context=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_online_web, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        initview(rootView);
        return rootView;
    }

    private void initview(View rootView) {
        onlinerefreshlayout.setOnRefreshListener(this);
        onlinerefreshlayout.setOnLoadMoreListener(this);
        adapter=new OnlineViedeAdapter(getContext(),videoList);
        adapter.setListener(new VideoClickListener() {
            @Override
            public void onItemClick(int position, Video video) {
//                Message message=new Message();
//                     message.obj=video;
//                     uihandler.sendMessage(message);
//                ToastUtils.showLong("点击了位置"+position);
                Intent intent =new Intent(context,OnlineVideoContentActivity.class);
                intent.putExtra("video",(Serializable)video);
                context.startActivity(intent);
                ToastUtils.showLong("点击了位置"+position);
            }

            @Override
            public void onItemLongClick(int position, Video video) {
                ToastUtils.showLong("长点击了位置"+position);
            }
        });
        onlinerecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        onlinerecyclerview.setAdapter(adapter);
        onlinerecyclerview.post(new Runnable() {
            @Override
            public void run() {
                onlinerefreshlayout.autoRefresh();
            }
        });

    }

    public void setSort(int sort) {
        this.sort = sort;onlinerefreshlayout.autoRefresh();

    }

    public void setPage(int page) {
        if(this.page==page){return;}
        this.page = page;
        onlinerefreshlayout.autoRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        videoList.clear();
        requestdata();

    }

    private void requestdata() {
        jsoup=new OnlineVideoListJsoup(title,page,sort);
        jsoup.setFindVideolistListener(new FindVideolistListener() {
            @Override
            public void onSucceed(List<Video> mList) {
                Message message=new Message();
                message.what=3401;
                uihandler.sendMessage(message);
            }

            @Override
            public void onfailed(String e) {
                Message message=new Message();
                message.what=3403;
                message.obj=e;
                uihandler.sendMessage(message);

            }

            @Override
            public void onSimpleSucceed(Video video) {
                videoList.add(video);
                Message message=new Message();
                message.what=3402;
                uihandler.sendMessage(message);

            }
        });
        jsoup.start();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        requestdata();

    }

    public void nextpage(){
        page++;
        onlinerefreshlayout.autoRefresh();
    }
    public  void  lastpage(){
        if(page==1){return;}
        page--;
        onlinerefreshlayout.autoRefresh();
    }

}