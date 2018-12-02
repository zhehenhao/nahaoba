package com.meizi.haokan.text;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.meizi.haokan.Base.BaseContentListActivity;
import com.meizi.haokan.R;

import java.util.ArrayList;
import java.util.List;

public class TextWeboneActivity extends BaseContentListActivity {

    /**

     */
    private int currentposition=1;
    private  int sort=1;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**

     */
    private ViewPager mViewPager;
    private List<TxtlistFragment> fragmentList=new ArrayList<>();
    private TxtlistFragment currentFragmet=null;
    private String[] titles={"全部","都市激情","家庭乱伦","淫荡人妻","校园春色","武侠情色","暴力强奸","情色笑话","性爱技巧","长篇连载"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_webone);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
for (int i=0;i<titles.length;i++){
    tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
    TxtlistFragment fragment=TxtlistFragment.newInstance(titles[i]);
    fragmentList.add(fragment);
}     mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentposition=position;
                currentFragmet=fragmentList.get(position);
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
        getMenuInflater().inflate(R.menu.menu_text_webone, menu);
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
            default:
                return super.onOptionsItemSelected(item);

        }
        currentFragmet=fragmentList.get(currentposition);
        currentFragmet.setSort(sort);
//       Toast.makeText(this,"请手动刷新列表",Toast.LENGTH_LONG).show();
        return true;


    }

    @Override
    protected void gotopage(int i) {
        if(currentFragmet==null){
            currentFragmet=fragmentList.get(currentposition);
        }
        currentFragmet.setPage(i);

    }

    @Override
    protected void nextpage() {
            if(currentFragmet==null){
                currentFragmet=fragmentList.get(currentposition);
            }
            currentFragmet.nextpage();
    }

    @Override
    protected void lastpage() {
        if(currentFragmet==null){
            currentFragmet=fragmentList.get(currentposition);
        }
        currentFragmet.lastpage();

    }

    /**
     * A placeholder fragment containing a simple view.
     */


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
            // Return a TxtlistFragment (defined as a static inner class below).
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return fragmentList.size();
        }
    }
}
