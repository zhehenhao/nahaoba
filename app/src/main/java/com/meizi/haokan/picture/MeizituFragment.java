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

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.FindPicturelistListener;
import com.meizi.haokan.jsoup.MeizituJsoup;
import com.meizi.haokan.picture.adapter.AlbumAdapter;
import com.meizi.haokan.model.Album;
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
 *
 */
public class MeizituFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    private static final String ARG_PARAM1 = "title";
    @BindView(R.id.meizirecycle)
    RecyclerView meizirecycle;
    @BindView(R.id.meizirefreshlayout)
    SmartRefreshLayout meizirefreshlayout;
    Unbinder unbinder;
    private AlbumAdapter adapter;
    private List<Album> falbumList=new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String title;
    private int fpage=1;
    private Handler uihandle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 2001:
                    meizirefreshlayout.finishRefresh(1000);
                    meizirefreshlayout.finishLoadMore(1000);
                    adapter.notifyDataSetChanged();

                    break;

                case  2002:
                    meizirefreshlayout.finishRefresh(false);
                    meizirefreshlayout.finishLoadMore(false);
                    ToastUtils.showLong((String) msg.obj);

                    break;
            }
        }
    };


    public MeizituFragment() {

    }


    public static MeizituFragment newInstance(String param1) {
        MeizituFragment fragment = new MeizituFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
        View rootView = inflater.inflate(R.layout.fragment_girls, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        adapter=new AlbumAdapter(getContext(),falbumList);
        adapter.setOnItemClickListener(new AlbumAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, Album album) {

            }
        });
       meizirefreshlayout.setOnRefreshListener(this);
       meizirefreshlayout.setOnLoadMoreListener(this);
       meizirecycle.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
       meizirecycle.setAdapter(adapter);
       meizirecycle.post(new Runnable() {
           @Override
           public void run() {
               meizirefreshlayout.autoRefresh();
           }
       });
    }
   public  void requsetdata(){
       MeizituJsoup jsoup=new MeizituJsoup(title,fpage);
       jsoup.setFindPicturelistListener(new FindPicturelistListener() {
           @Override
           public void onSucceed(List<Album> albumList) {
               falbumList.addAll(albumList);
               LogUtils.e(falbumList.size()+"");
               Message msg=new Message();
               msg.what=2001;
               msg.obj=albumList;
               uihandle.sendMessage(msg);

           }

           @Override
           public void onFailed(String e) {
               Message msg=new Message();
               msg.what=2002;
               msg.obj=e;
               uihandle.sendMessage(msg);
           }
       });
     jsoup.start();

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

   public void  gopage(int s){
        fpage=s;
       requsetdata();

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        falbumList.clear();
        requsetdata();

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        fpage++;
        requsetdata();
    }
    public void gotopage(int position){
        if(fpage==position){
            return;
        }
        fpage=position;
        falbumList.clear();
        meizirefreshlayout.autoRefresh();
//        requsetdata();
    }
    public void lastpage(){

        if(fpage==1){
            return;
        }
        fpage--;
        falbumList.clear();
        meizirefreshlayout.autoRefresh();

    }
    public void nextpage(){
        fpage++;
        meizirefreshlayout.autoRefresh();

    }
}
