package com.meizi.haokan.online;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.OnlineVideolist2Jsoup;
import com.meizi.haokan.listener.FindVideolistListener;
import com.meizi.haokan.model.Video;
import com.meizi.haokan.online.adapter.OnlineVideo2Adapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnlineViewlist2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnlineViewlist2Fragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM2 = "title";
    @BindView(R.id.onlinerecyclerview)
    RecyclerView onlinerecyclerview;
    @BindView(R.id.onlinerefreshlayout)
    SmartRefreshLayout onlinerefreshlayout;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private int page = 1;
    private int sort = 1;
    private String title;
    private List<Video> videoList = new ArrayList<>();
    private OnlineVideo2Adapter adapter;
    private Handler uihandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 3601:
                    ToastUtils.showLong("本页资源价值完毕");

                    break;
                case 3602:
                    onlinerefreshlayout.finishRefresh(600);
                    onlinerefreshlayout.finishLoadMore(600);
                    adapter.notifyDataSetChanged();

                    break;
                case 3603:
                    onlinerefreshlayout.finishRefresh(false);
                    onlinerefreshlayout.finishLoadMore(false);
                        ToastUtils.showLong((String) msg.obj);
                    break;

            }
        }
    };
 private OnlineVideolist2Jsoup jsoup;

    public OnlineViewlist2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OnlineViewlist2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OnlineViewlist2Fragment newInstance(String title) {
        OnlineViewlist2Fragment fragment = new OnlineViewlist2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_online_viewlist2, container, false);
        unbinder = ButterKnife.bind(this, view);
        initview(view);
        return view;
    }

    private void initview(View view) {
        onlinerefreshlayout.setOnRefreshListener(this);
        onlinerefreshlayout.setOnLoadMoreListener(this);
        onlinerecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter=new OnlineVideo2Adapter(getContext(),videoList);
        onlinerecyclerview.setAdapter(adapter);
        onlinerecyclerview.post(new Runnable() {
            @Override
            public void run() {
                onlinerefreshlayout.autoRefresh();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void setPage(int page) {
        this.page = page;
        onlinerefreshlayout.autoRefresh();
    }

    public void setSort(int sort) {
        this.sort = sort;
        onlinerefreshlayout.autoRefresh();
    }
    public void nextpage(){
       page++;
       onlinerefreshlayout.autoRefresh();
    }
    public  void lastpage(){
        if(page==1){return;}
        page--;
        onlinerefreshlayout.autoRefresh();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        videoList.clear();
        requestdata();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        requestdata();
    }

    private void requestdata() {
        jsoup=new OnlineVideolist2Jsoup(title,sort,page);
        jsoup.setFindVideolistListener(new FindVideolistListener() {
            @Override
            public void onSucceed(List<Video> mList) {
                Message message=new Message();
                message.what=3601;
                uihandler.sendMessage(message);
            }

            @Override
            public void onfailed(String e) {
                Message message=new Message();
                message.what=3603;
                message.obj=e;
                uihandler.sendMessage(message);
            }

            @Override
            public void onSimpleSucceed(Video video) {
                videoList.add(video);
                Message message=new Message();
                message.what=3602;
                uihandler.sendMessage(message);

            }
        });
        jsoup.start();
    }
}
