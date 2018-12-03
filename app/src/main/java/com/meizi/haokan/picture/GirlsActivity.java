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
import android.view.ViewGroup;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.Base.BaseContentListActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.picture.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class GirlsActivity extends BaseContentListActivity implements MeizipaiFragment.OnListFragmentInteractionListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private List<MeizituFragment> fragmentList=new ArrayList<>();
    private String[] Titles={"最新","最热","推荐","性感妹子","日本妹子","台湾妹子","清纯妹子"};

    private ViewPager mViewPager;
    private int currentposition=0;

    private MeizituFragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls);

        Toolbar toolbar = (Toolbar) findViewById(R.id.girlstoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        // Set up the ViewPager with the sections adapter.

        TabLayout tabLayout = (TabLayout) findViewById(R.id.girlstabs);
        for (int i=0;i<Titles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(Titles[i]));
            MeizituFragment fragment=MeizituFragment.newInstance(Titles[i]);
                    fragmentList.add(i,fragment);
        }
//        tabLayout.addTab(tabLayout.newTab().setText("妹子自拍"));
//        MeizipaiFragment meizipaiFragment=MeizipaiFragment.newInstance(2);
//        fragmentList.add(Titles.length,meizipaiFragment);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.girlscontainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentposition=position;
                currentFragment=fragmentList.get(position);
//                LogUtils.e("当前选中："+currentposition);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.girlsfab);
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
        getMenuInflater().inflate(R.menu.menu_girls, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    protected void gotopage(int i) {
        if(currentFragment==null){currentFragment=fragmentList.get(currentposition);}
        currentFragment.gotopage(i);
    }

    @Override
    protected void nextpage() {
        if(currentFragment==null){currentFragment=fragmentList.get(currentposition);}
        currentFragment.nextpage();
    }


    @Override
    protected void lastpage() {
        if(currentFragment==null){currentFragment=fragmentList.get(currentposition);}
        currentFragment.lastpage();
    }


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
