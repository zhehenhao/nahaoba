package com.meizi.haokan.picture;

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


import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.Base.BaseContentListActivity;
import com.meizi.haokan.R;

import java.util.ArrayList;
import java.util.List;

public class InfoAlbumActivity extends BaseContentListActivity {

    /**

     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     *
     */
   private String[] titles={"全部","唯美清纯","美腿丝袜","亚洲色图","性爱自拍","欧美色图","淫荡淑女","玉乳明星","SM色图","同性图区"};
    private List<InfoAlbumFragment>  fragmentList= new ArrayList<>();
    private int currentposition=1;
    private int page=1;
    private int sort=1;
    private  InfoAlbumFragment currentFragment=null;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_album);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        for(int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
            InfoAlbumFragment fragment=InfoAlbumFragment.newInstance(titles[i]);
            fragmentList.add(fragment);
        }
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentposition=position;
                currentFragment=fragmentList.get(position);
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
        getMenuInflater().inflate(R.menu.menu_info_album, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        LogUtils.e("点击了"+id);
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
                default:
                    return super.onOptionsItemSelected(item);

        }
        if(currentFragment==null){
            currentFragment=fragmentList.get(currentposition);
        }
        currentFragment.setSort(sort);
        return true;
    }

    @Override
    protected void gotopage(int i) {
        if(currentFragment==null){
            currentFragment=fragmentList.get(currentposition);
        }
        currentFragment.setPage(i);
    }

    @Override
    protected void nextpage() {
        if(currentFragment==null){
            currentFragment=fragmentList.get(currentposition);
        }
        currentFragment.nextpage();
    }

    @Override
    protected void lastpage() {
        if(currentFragment==null){
            currentFragment=fragmentList.get(currentposition);
        }
        currentFragment.lastpage();


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
            return fragmentList.get(position) ;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return fragmentList.size();
        }
    }
}
