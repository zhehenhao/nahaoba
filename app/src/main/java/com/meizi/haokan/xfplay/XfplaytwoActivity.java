package com.meizi.haokan.xfplay;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.Base.BaseContentListActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.listener.FindVideolistListener;
import com.meizi.haokan.jsoup.Xfweb2listJsoup;
import com.meizi.haokan.model.Video;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;



import static com.meizi.haokan.Base.AppConfig.spanCount;

public class XfplaytwoActivity extends BaseContentListActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private XfplaytwoActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private String[] types={"H0930","PACOPACO","H4610","Heyzo","muramura","Gachin","C0930","mesubuta","avs-mus","1000girls","akiba"};//,"最受欢迎","评分最高","本周热播","日韩情色","制服丝袜","强奸乱伦","变态另类","欧美性爱","国产精品","偷拍自拍"
    private static String[]   typeurls={
            "https://xf.0ady.info/a/134%s.html",
            "https://xf.0ady.info/a/133%s.html",
            "https://xf.0ady.info/a/136%s.html",
            "https://xf.0ady.info/a/139%s.html",
            "https://xf.0ady.info/a/135%s.html",
            "https://xf.0ady.info/a/128%s.html",
            "https://xf.0ady.info/a/132%s.html",
            "https://xf.0ady.info/a/137%s.html",
            "https://xf.0ady.info/a/129%s.html",
            "https://xf.0ady.info/a/131%s.html",
            "https://xf.0ady.info/a/130%s.html"
    };
    private int  type=1;
    private int  sort=1;
    private int  page=1;

    private XfplaytwoActivity.PlaceholderFragment currentFragmet=null;
    private List<PlaceholderFragment> fragmentList=new ArrayList<>();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private int currentposition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfplaytwo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        mSectionsPagerAdapter = new XfplaytwoActivity.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container2);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs2);

        for(int i=0;i<types.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(types[i]));
            XfplaytwoActivity.PlaceholderFragment fragment=XfplaytwoActivity.PlaceholderFragment.newInstance(i);
            fragmentList.add(i,fragment);

        }

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout){
            @Override
            public void onPageSelected(int position) {
                currentposition=position;
                super.onPageSelected(position);

                LogUtils.e("当前选中了"+position);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showpagedialog();
            }
        });

    }
    @Override
    public void lastpage() {
        currentFragmet=fragmentList.get(currentposition);
        if(currentFragmet instanceof PlaceholderFragment){
            currentFragmet.lastpage();}
    }

    @Override
    public void nextpage() {
        currentFragmet=fragmentList.get(currentposition);
        if(currentFragmet instanceof PlaceholderFragment){
            currentFragmet.nextpage();}

    }

    @Override
    public void gotopage(int page) {
        currentFragmet=fragmentList.get(currentposition);
        if(currentFragmet instanceof PlaceholderFragment){
            currentFragmet.setMpage(page);}

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements OnRefreshLoadMoreListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private XfplayRecycleAdapter adapter;
        private int position=0;
        private static final String ARG_SECTION_NUMBER = "section_number";
        private RecyclerView recyclerView;
        private SmartRefreshLayout smartRefreshLayout;
        private Context mcontext;
        private String weburl="https://xf.0ady.info/a/134.html";
        private int mpage=1;

        private Handler uihandle=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1201:
                        adapter.notifyDataSetChanged();
                        smartRefreshLayout.finishRefresh(1000);
                        smartRefreshLayout.finishLoadMore(1000);
                        break;
                    case  1202:
                        smartRefreshLayout.finishRefresh(false);
                        smartRefreshLayout.finishLoadMore(false);
                        Toast.makeText(mcontext,msg.obj.toString(),Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        private List<Video> mList =new ArrayList<>();
        public PlaceholderFragment() {
//
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static XfplaytwoActivity.PlaceholderFragment newInstance(int sectionNumber) {
            XfplaytwoActivity.PlaceholderFragment fragment = new XfplaytwoActivity.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            LogUtils.e("初始化fragment"+sectionNumber);
            return fragment;
        }


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            position=getArguments().getInt(ARG_SECTION_NUMBER);
            mcontext=getContext();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_xfplaytwo, container, false);
            initView(rootView);

            return rootView;
        }

        private void initView(View rootView) {
            adapter=new XfplayRecycleAdapter(mcontext,mList);
            adapter.setOnItemClickListener(new XfplayRecycleAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int position, String name, String xiangqingurl, String img, Video video) {
//                    SaveVideo(video);
                    Intent i=new Intent(mcontext,XfplayVideoDetailPageActivity.class);
                    i.putExtra("web",2);
                    i.putExtra("name",name);
                    i.putExtra("xiangqingye",xiangqingurl);
                    i.putExtra("img",img);
                    getActivity().startActivity(i);
                }
            });
            adapter.setOnLongClickListener(new XfplayRecycleAdapter.onLongClickListener() {
                @Override
                public void onLongClick(int position, String name, String xiangqiangurl, String img, Video video) {

                }
            });
            recyclerView=rootView.findViewById(R.id.recyclerview2);
            smartRefreshLayout=rootView.findViewById(R.id.smartrefreshlayout2);
//            smartRefreshLayout.setRefreshContent(new BezierRadarHeader(mcontext).setEnableHorizontalDrag(true));
//            smartRefreshLayout.setRefreshFooter(new BallPulseFooter(mcontext).setSpinnerStyle(SpinnerStyle.Scale));
            smartRefreshLayout.setOnRefreshListener(this);
            smartRefreshLayout.setOnLoadMoreListener(this);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount,StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(adapter);
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    smartRefreshLayout.autoRefresh();
                }
            });

        }

        private void SaveVideo(final Video video) {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Realm realm = Realm.getDefaultInstance();
//                    try {
//                        // ... Use the Realm instance ...
//                        realm.executeTransaction(new Realm.Transaction() {
//                            @Override
//                            public void execute(Realm realm) {
//                                realm.copyToRealmOrUpdate(video);
//                            }
//                        });
//
//                    } finally {
//                        realm.close();
//                    }
//                }
//            });
//            thread.start();
        }

        public void requestdata(){
            if(mpage>1){
            weburl=String.format(typeurls[position],"_"+mpage);
            }
            else{
                weburl=String.format(typeurls[position],"");
            }
            LogUtils.e(position+"-"+weburl);
            Xfweb2listJsoup xfweb2ListJsoup =new Xfweb2listJsoup(weburl);
            xfweb2ListJsoup.setRequestListener(new FindVideolistListener() {

                @Override
                public void onSucceed(List<Video> list) {
                    LogUtils.e("接受成功结束");
                    mList.addAll(list);
                    Message message=new Message();
                    message.what=1201;
                    uihandle.sendMessage(message);
                }

                @Override
                public void onfailed(String e) {
                    Message message=new Message();
                    message.what=1202;
                    message.obj=e;
                    uihandle.sendMessage(message);
                }
            });
            xfweb2ListJsoup.start();

        }

        public void lastpage(){
            if(mpage==1){return;}
            mpage=mpage-1;
            smartRefreshLayout.autoRefresh();
//            requestdata();
        }
        public  void nextpage(){
            mpage++;
            smartRefreshLayout.autoRefresh();
//            requestdata();
        }

        public void setMpage(int page) {
            if(mpage==page){return;}
            mpage=page;
            smartRefreshLayout.autoRefresh();
//            requestdata();
        }


        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            mpage++;
            requestdata();


        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            if(mList.size()>0){
                mList.clear();}
            requestdata();

        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * two of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {

            return types.length;
        }
    }
}
