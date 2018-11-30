package com.meizi.haokan.picture;

import android.content.Context;
import android.net.Uri;
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
import com.meizi.haokan.jsoup.InfoAlbumJsoup;
import com.meizi.haokan.listener.FindAlbumListener;
import com.meizi.haokan.model.Album;
import com.meizi.haokan.model.Picture;
import com.meizi.haokan.picture.adapter.AlbumAdapter;
import com.meizi.haokan.picture.adapter.InfoAlbumAdapter;
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
 * 同性色的美女美图
 */
public class InfoAlbumFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.inforecycle)
    RecyclerView inforecycle;
    @BindView(R.id.inforefreshlayout)
    SmartRefreshLayout inforefreshlayout;
    Unbinder unbinder;
    private int page = 1;
    private int sort = 1;
    private String title = null;
    private List<Album> albumList=new ArrayList<>();
    private InfoAlbumJsoup jsoup;
    private InfoAlbumAdapter adapter;
    private Handler uihandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 3021:
                    inforefreshlayout.finishLoadMore(600);
                    inforefreshlayout.finishRefresh(600);
                    ToastUtils.showLong("本写真集已经全部加载完毕");
                    break;
                case  3022:
                    inforefreshlayout.finishLoadMore(600);
                    inforefreshlayout.finishRefresh(600);
                    adapter.notifyDataSetChanged();

                    break;
                case  3023:
                    ToastUtils.showLong((String) msg.obj);

                    break;
            }

        }
    };




    public InfoAlbumFragment() {

    }

    /**
     *
     */
    // TODO: Rename and change types and number of parameters
    public static InfoAlbumFragment newInstance(String title) {
        InfoAlbumFragment fragment = new InfoAlbumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_album, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        inforefreshlayout.setOnRefreshListener(this);
        inforefreshlayout.setOnLoadMoreListener(this);
        inforecycle.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter=new InfoAlbumAdapter(getContext(),albumList);
        inforecycle.setAdapter(adapter);
        inforecycle.post(new Runnable() {
            @Override
            public void run() {
               inforefreshlayout.autoRefresh();
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        albumList.clear();
        requestdata();

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        requestdata();

    }


    public void setPage(int page) {
        this.page = page;
        inforefreshlayout.autoRefresh();
    }

    public void setSort(int sort) {
        this.sort = sort;
         inforefreshlayout.autoRefresh();
    }

    private void requestdata() {
        jsoup=new InfoAlbumJsoup(title,page,sort);
        jsoup.setFindAlbumListener(new FindAlbumListener() {
            @Override
            public void onSucceed(List<Album> albumList) {
                Message msg=new Message();
                msg.what=3021;
                uihandler.sendMessage(msg);
            }

            @Override
            public void onFailed(String e) {
                Message msg=new Message();
                msg.what=3023;
                msg.obj=e;
                uihandler.sendMessage(msg);
            }

            @Override
            public void onSimpleSucceed(Album album) {
                albumList.add(album);
                Message msg=new Message();
                msg.what=3022;
                uihandler.sendMessage(msg);

            }
        });
        jsoup.start();

    }

    public  void lastpage(){
        if(page==1){return;}
        page--;
        inforefreshlayout.autoRefresh();
    }
    public  void nextpage(){
        page++;
        inforefreshlayout.autoRefresh();

    }
    public  void gotopage(int position){
        if(page==position){
            return;
        }
        page=position;
        inforefreshlayout.autoRefresh();


    }

}
