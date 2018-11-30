package com.meizi.haokan.online;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meizi.haokan.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

    private static final String ARG_SECTION_NUMBER = "title";

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

    }

    public void setSort(int sort) {
        this.sort = sort;

    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }
}