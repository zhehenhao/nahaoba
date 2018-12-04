package com.meizi.haokan.online;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

import android.widget.TextView;

import com.meizi.haokan.Base.BaseContentListActivity;
import com.meizi.haokan.R;

import java.util.ArrayList;
import java.util.List;

public class OnlineWeb2Activity extends BaseContentListActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private String[]   titles;
    private String[]   web1={"全部","少女","户外","美女靓妹","丝袜","制服诱惑","主播视频秀","学生妹校园","性爱","国产"};
    private String[]   web2={"SM调教","小便","肛交","口交","女同","口爆颜射","足交","自慰","群交","强奸"};
    private String[]   web3={"人妻熟女","日本无码","亚洲","欧美","巨乳","中文字幕","素人","3P"};
    private String[]   web4={"乱伦或偷情","长视频","性派对","高清","大屌"};

private int webtype;
    private ViewPager mViewPager;
    private int sort=1;
    private int currentposition;
    private List<OnlineViewlist2Fragment> fragmentList=new ArrayList<>();
  private OnlineViewlist2Fragment currentfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_web2);
        webtype=getIntent().getIntExtra("type",1);
        switch(webtype){
            case 1:
                titles=web1;
                break;
            case 2:
                titles=web2;
                break;
            case 3:
                titles=web3;
                break;
            case 4:
                titles=web4;
                break;

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        for (int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
            OnlineViewlist2Fragment fragment=OnlineViewlist2Fragment.newInstance(titles[i]);
            fragmentList.add(fragment);
        }
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentposition=position;
                currentfragment=fragmentList.get(position);
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
        getMenuInflater().inflate(R.menu.menu_online_web2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      switch (id){
          case R.id.time   :
              sort=1;
              break;
          case R.id.playtime  :
              sort=2;
              break;
          case R.id.rate   :
              sort=3;
              break;
          case R.id.discuss :
              sort=4;
              break;
          case R.id.download  :
              sort=5;
              break;
          case R.id.shichang :
              sort=6;
              break;
          case R.id.yikan :
              sort=7;
              break;

      }
    if(currentfragment==null){
          currentfragment=fragmentList.get(currentposition);
    }
    currentfragment.setSort(sort);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void gotopage(int i) {
        if(currentfragment==null){
            currentfragment=fragmentList.get(currentposition);
        }
        currentfragment.setPage(i);
    }

    @Override
    protected void nextpage() {
        if(currentfragment==null){
            currentfragment=fragmentList.get(currentposition);
        }
        currentfragment.nextpage();

    }

    @Override
    protected void lastpage() {
        if(currentfragment==null){
            currentfragment=fragmentList.get(currentposition);
        }
        currentfragment.lastpage();
    }

    /**
     * A placeholder fragment containing a simple view.

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
            return fragmentList.size();
        }
    }
}
