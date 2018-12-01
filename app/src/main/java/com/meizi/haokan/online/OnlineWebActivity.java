package com.meizi.haokan.online;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.meizi.haokan.Base.BaseContentListActivity;
import com.meizi.haokan.R;

import java.util.ArrayList;
import java.util.List;

public class OnlineWebActivity extends BaseContentListActivity {

    /**

     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private List<OnlineViewlistFragment> fragmentList=new ArrayList<>();
    private int currentposition;
    private OnlineViewlistFragment currentfragment;
    private String[] titles;
    private String[]  web1={"全部","高清色情","成人少女","色情视频","色情爱好","女同性爱","欧洲色情","黑发女孩","金发女孩","德国色情","日本色情","成熟性爱","辣妹性爱"};
    private String[]  web2={"双重插入","爆菊肛交","面部射精","巨大乳房","口交口爆","足交","拳交","自慰","群交","性SM","轮奸"};
    private String[]  web3={"","","","","","","","","","","","","","",""};
    private String[]  web4={"","","","","","","","","","","","","","",""};
    private int webtype=1;
    private int sort=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_web);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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


        for(int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
            OnlineViewlistFragment fragment=OnlineViewlistFragment.newInstance(titles[i]);
            fragmentList.add(fragment);
        }

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.

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
            default:
                return super.onOptionsItemSelected(item);

        }
        if(currentfragment==null){
            currentfragment=fragmentList.get(currentposition);
        }
       currentfragment.setSort(sort);
//       Toast.makeText(this,"请手动刷新列表",Toast.LENGTH_LONG).show();
        return true;

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

    protected  void gotopage(int i){
        if(currentfragment==null){
           currentfragment=fragmentList.get(currentposition);
        }
        currentfragment.setPage(i);
    }

    protected  void nextpage(){

        if(currentfragment==null){
            currentfragment=fragmentList.get(currentposition);
        }currentfragment.nextpage();
    }

    protected  void lastpage(){
     if(currentfragment==null){
            currentfragment=fragmentList.get(currentposition);
     }currentfragment.lastpage();
    }
}
