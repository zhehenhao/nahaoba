package com.meizi.haokan.xfplay;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.Base.BaseXfplaylistActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.FindVideolistListener;
import com.meizi.haokan.jsoup.Xfweb3listJsoup;
import com.meizi.haokan.realm.Video;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

import static com.meizi.haokan.Base.AppConfig.spanCount;

public class XfplaythreeActivity extends BaseXfplaylistActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private String[] types={"最近更新","最受欢迎","评分最高","本周热播","日韩情色","制服丝袜","强奸乱伦","变态另类","欧美性爱","国产精品","偷拍自拍"};//,"最受欢迎","评分最高","本周热播","日韩情色","制服丝袜","强奸乱伦","变态另类","欧美性爱","国产精品","偷拍自拍"
    private static String[]   typeurls={"https://tongxingse.info/sexmovie/all/index_1_1_%d.html" ,
            "https://tongxingse.info/sexmovie/all/index_2_1_%d.html",
            "https://tongxingse.info/sexmovie/all/index_3_1_%d.html",
            "https://tongxingse.info/sexmovie/all/index_5_1_%d.html",
            "https://tongxingse.info/sexrh/index_%d_1_%d.html",
            "https://tongxingse.info/sexzf/index_%d_1_%d.html",
            "https://tongxingse.info/sexqj/index_%d_1_%d.html",
            "https://tongxingse.info/sexbt/index_%d_1_%d.html",
            "https://tongxingse.info/sexom/index_%d_1_%d.html",
            "https://tongxingse.info/sexgc/index_%d_1_%d.html",
            "https://tongxingse.info/sextp/index_%d_1_%d.html"  };
    private int  type=1;
    private int  sort=1;
    private int  page=1;

    private PlaceholderFragment currentFragmet=null;
    private  List<PlaceholderFragment> fragmentList=new ArrayList<>();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private int currentposition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfplaythree);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container3);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs3);

    for(int i=0;i<types.length;i++){
       tabLayout.addTab(tabLayout.newTab().setText(types[i]));
       PlaceholderFragment fragment=PlaceholderFragment.newInstance(i);
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab3);
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
        getMenuInflater().inflate(R.menu.menu_xfplaythree, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_one:
                sort=1;
                break;
            case R.id.action_two:
                sort=2;
                break;
            case R.id.action_three:
                sort=3;
                break;
            case R.id.action_four:
                sort=4;
                break;
            case R.id.action_five:
                sort=5;
                break;
        }
        currentFragmet=fragmentList.get(currentposition);
       currentFragmet.setMsort(sort);
//       Toast.makeText(this,"请手动刷新列表",Toast.LENGTH_LONG).show();
        return true;
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
    public static class PlaceholderFragment extends Fragment implements OnRefreshLoadMoreListener{
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
        private String weburl="https://tongxingse.info/sexmovie/all/index_1_1_1.html";
        private int mpage=1;
        private int msort=1;
        private Handler uihandle=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1301:
                        adapter.notifyDataSetChanged();
                        smartRefreshLayout.finishRefresh(1000);
                        smartRefreshLayout.finishLoadMore(1000);
                        break;
                    case  1302:
                        smartRefreshLayout.finishRefresh(false);
                        smartRefreshLayout.finishLoadMore(false);
                        Toast.makeText(mcontext,msg.obj.toString(),Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        private List<Video> mList =new ArrayList<>();
        public PlaceholderFragment() {
//            Activity activity=getActivity();

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
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
            View rootView = inflater.inflate(R.layout.fragment_xfplaythree, container, false);
//            TextView tv3=rootView.findViewById(R.id.tv3);
//            tv3.setText("位置ss"+position);
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
                    i.putExtra("web",3);
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
            recyclerView=rootView.findViewById(R.id.recyclerview3);
            smartRefreshLayout=rootView.findViewById(R.id.smartrefreshlayout3);
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
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Realm realm = Realm.getDefaultInstance();
                    try {
                        // ... Use the Realm instance ...
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.copyToRealmOrUpdate(video);
                            }
                        });

                    } finally {
                        realm.close();
                    }
                }
            });
            thread.start();
        }

        public void requestdata(){
           if(position<4){
               weburl=String.format(typeurls[position],mpage);

           }else{
               weburl=String.format(typeurls[position],msort,mpage);
           }
           LogUtils.e(position+"-"+weburl);
           Xfweb3listJsoup xfweb3ListJsoup =new Xfweb3listJsoup(weburl);
           xfweb3ListJsoup.setRequestListener(new FindVideolistListener() {

               @Override
               public void onSucceed(List<Video> list) {
                   LogUtils.e("接受成功结束");
                 mList.addAll(list);
                Message message=new Message();
                message.what=1301;
                uihandle.sendMessage(message);

               }

               @Override
               public void onfailed(String e) {
                   Message message=new Message();
                   message.what=1302;
                   uihandle.sendMessage(message);

               }
           });
           xfweb3ListJsoup.start();

       }

        public void lastpage(){
            if(mpage==1){return;}
            mpage=mpage-1;
            smartRefreshLayout.autoRefresh();
            requestdata();
        }
        public  void nextpage(){
            mpage++;
            smartRefreshLayout.autoRefresh();
            requestdata();
        }

        public void setMpage(int page) {
            if(mpage==page){return;}
            mpage=page;
            smartRefreshLayout.autoRefresh();
            requestdata();
        }

        public void setMsort(int msort) {
            if(position>3){
            this.msort = msort;
            smartRefreshLayout.autoRefresh();
            requestdata();
            }
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
     * one of the sections/tabs/pages.
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
            // Show 3 total pages.
            return types.length;
        }
    }

}
