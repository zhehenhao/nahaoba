package com.meizi.haokan.text;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.TxtListJsoup;
import com.meizi.haokan.listener.FindTxtListener;
import com.meizi.haokan.model.Txt;
import com.meizi.haokan.text.adapter.TextAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TxtlistFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.txtrecycle)
    RecyclerView txtrecycle;
    @BindView(R.id.txtrefreshlayout)
    SmartRefreshLayout txtrefreshlayout;
    Unbinder unbinder;


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private String title = "";
    private int page = 1;
    private int sort = 1;
    private List<Txt> txtList = new ArrayList<>();
    private Context context;
    private static final String ARG_SECTION_NUMBER = "title";
    private TextAdapter adapter;
    private TxtListJsoup jsoup;
    private Handler uihandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 4201:
                    ToastUtils.showLong("资源获取完毕");
                    break;
                case 4202:
                    txtrefreshlayout.finishLoadMore(600);
                    txtrefreshlayout.finishRefresh(600);
                    adapter.notifyDataSetChanged();

                    break;
                case 4203:
                    ToastUtils.showLong((String) msg.obj);
                    txtrefreshlayout.finishLoadMore(false);
                    txtrefreshlayout.finishRefresh(false);
                    break;
            }
        }
    };


    public TxtlistFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TxtlistFragment newInstance(String title) {
        TxtlistFragment fragment = new TxtlistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_SECTION_NUMBER);
        }
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text_webone, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        initview(rootView);
        return rootView;
    }

    private void initview(View rootView) {
        txtrefreshlayout.setOnLoadMoreListener(this);
        txtrefreshlayout.setOnRefreshListener(this);
        txtrecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TextAdapter(getContext(), txtList);
        txtrecycle.setAdapter(adapter);
        txtrecycle.post(new Runnable() {
            @Override
            public void run() {
                txtrefreshlayout.autoRefresh();
            }
        });


    }

    public void lastpage() {
        if (page == 1) {
            return;
        }
        page--;
        txtrefreshlayout.autoRefresh();
    }

    public void nextpage() {
        page++;
        txtrefreshlayout.autoRefresh();
    }


    public void setPage(int page) {
        if (this.page == page) {
            return;
        }
        this.page = page;
        txtrefreshlayout.autoRefresh();
    }

    public void setSort(int sort) {
        if (this.sort == sort) {
            return;
        }
        this.sort = sort;
        txtrefreshlayout.autoRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void requestdata() {
        jsoup = new TxtListJsoup(getContext(), title, page, sort);
        jsoup.setFindTxtListener(new FindTxtListener() {
            @Override
            public void onSucceed(List<Txt> txtList) {
                Message message = new Message();
                message.what = 4201;
                uihandler.sendMessage(message);

            }

            @Override
            public void onSimplySucceed(Txt txt) {
                txtList.add(txt);
                Message message = new Message();
                message.what = 4202;
                uihandler.sendMessage(message);


            }

            @Override
            public void onFailed(String e) {
                Message message = new Message();
                message.what = 4203;
                message.obj = e;
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

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        txtList.clear();
        requestdata();
    }

}