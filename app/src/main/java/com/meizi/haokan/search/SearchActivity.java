package com.meizi.haokan.search;

import android.app.SearchManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.Base.BaseContentListActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.SearchListJsoup;
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


public class SearchActivity extends BaseContentListActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private List<PlaceholderFragment> fragmentList = new ArrayList<>();
    private int currentposition;
    private PlaceholderFragment currentfragment;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searcher);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentposition = position;
                currentfragment = fragmentList.get(position);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpagedialog();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_searcher, menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchweb(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                LogUtils.e(newText);
//                if(newText.equals("1024")){
//                    Intent intent= new Intent(X5WebviewActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
                return false;
            }
        });
        return true;
    }

    private void searchweb(String query) {
        tabLayout.addTab(tabLayout.newTab().setText(query));
        PlaceholderFragment fragment = PlaceholderFragment.newInstance(query);
        fragmentList.add(fragment);
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    private void clearsearch() {
        tabLayout.removeAllTabs();
        fragmentList.clear();
        currentfragment=null;
        currentposition=-1;
        mSectionsPagerAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clearsearch) {
            clearsearch();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void gotopage(int i) {
           if(currentposition<0){
               return;
           }
           if(currentfragment==null){
               currentfragment=fragmentList.get(currentposition);
           }
           currentfragment.setPage(i);
    }

    @Override
    protected void nextpage() {
        if(currentposition<0){
            return;
        }
        if(currentfragment==null){
            currentfragment=fragmentList.get(currentposition);
        }
        currentfragment.nextpage();
    }

    @Override
    protected void lastpage() {
        if(currentposition<0){
            return;
        }
        if(currentfragment==null){
            currentfragment=fragmentList.get(currentposition);
        }
  currentfragment.lastpage();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {
        @BindView(R.id.searchrecyclerview)
        RecyclerView searchrecyclerview;
        @BindView(R.id.searchrefreshlayout)
        SmartRefreshLayout searchrefreshlayout;
        @BindView(R.id.constraintLayout)
        ConstraintLayout constraintLayout;
        Unbinder unbinder;
        private String title;
        private List<Video>  videoList=new ArrayList<>();
        private static final String ARG_SECTION_NUMBER = "section_number";
  private int sort=1;
  private int page=1;
 private Handler uihandler=new Handler(){
     @Override
     public void handleMessage(Message msg) {
         super.handleMessage(msg);
         switch (msg.what){
             case 5101:
                 ToastUtils.showLong("加载资源完毕");
                 break;
             case 5102:
                 searchrefreshlayout.finishLoadMore(600);
                 searchrefreshlayout.finishRefresh(600);
                 adapter.notifyDataSetChanged();
                 break;
             case  5103:
                 searchrefreshlayout.finishLoadMore(false);
                 searchrefreshlayout.finishRefresh(false);
                 ToastUtils.showLong((String) msg.obj);
                 break;
         }
     }
 };
        public void setSort(int sort) {
            this.sort = sort;
            searchrefreshlayout.autoRefresh();
        }

        public void setPage(int page) {
            this.page = page;
            searchrefreshlayout.autoRefresh();
        }

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(String sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString("title", sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                title = getArguments().getString("title");
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_searcher, container, false);

            unbinder = ButterKnife.bind(this, rootView);
            initview(rootView);
            return rootView;
        }
        OnlineVideo2Adapter adapter;
        private void initview(View rootView) {
            searchrefreshlayout.setOnRefreshListener(this) ;
             searchrefreshlayout.setOnLoadMoreListener(this);
            adapter=new OnlineVideo2Adapter(getContext(),videoList);
            searchrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            searchrecyclerview.setAdapter(adapter);
            searchrecyclerview.post(new Runnable() {
                @Override
                public void run() {
                    searchrefreshlayout.autoRefresh();
                }
            });
        }


        @Override
        public void onDestroyView() {
            super.onDestroyView();
            unbinder.unbind();
        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            videoList.clear();
            requsetdata();

        }
      SearchListJsoup jsoup;
        private void requsetdata() {
            jsoup=new SearchListJsoup(title,sort,page);
            jsoup.setFindVideolistListener(new FindVideolistListener() {
                @Override
                public void onSucceed(List<Video> mList) {
                    Message message=new Message();
                    message.what=5101;
                    uihandler.sendMessage(message);
                }

                @Override
                public void onfailed(String e) {
                    Message message=new Message();
                    message.what=5103;
                    message.obj=e;
                    uihandler.sendMessage(message);

                }

                @Override
                public void onSimpleSucceed(Video video) {
                    videoList.add(video);
                    Message message=new Message();
                    message.what=5102;
                    uihandler.sendMessage(message);

                }
            });
            jsoup.start();

        }

        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            page++;
            requsetdata();

        }
    public  void nextpage(){
            if(page==1){
                return;
            }
            page++;
            searchrefreshlayout.autoRefresh();
}
        public void lastpage() {
            page++;
            searchrefreshlayout.autoRefresh();
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a OnlineViewlistFragment (defined as a static inner class below).
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return fragmentList.size();
        }
    }
}
