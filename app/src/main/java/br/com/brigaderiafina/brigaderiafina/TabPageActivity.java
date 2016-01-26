package br.com.brigaderiafina.brigaderiafina;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.content.Intent;
import android.support.design.widget.TabLayout;



import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.brigaderiafina.brigaderiafina.utils.Constants;
import br.com.brigaderiafina.brigaderiafina.utils.MenuHandler;

public class TabPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String mSubgroupName;//test

    private static String SUBGROUP_FLAVOURS = "subgrupoDetailFlavoursFragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subgroup_detail);

        toolbar = (Toolbar) findViewById(R.id.appbarSubgroup);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        mSubgroupName = intent.getStringExtra(Constants.SUBGROUP_DETAILS);
        getSupportActionBar().setTitle(mSubgroupName);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Test
        if (mSubgroupName.contains("Evento")) {
            adapter.addFragment(new TabPageActivityPhotosFragment(), Constants.TAB_PHOTO);
            adapter.addFragment(new TabPageActivityDescriptionFragment(),"Descrição" );

        }else{
            adapter.addFragment(new TabPageActivityDescriptionFragment(),Constants.TAB_FLAVOUR );
            adapter.addFragment(new TabPageActivityPhotosFragment(), Constants.TAB_PHOTO);
        }

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        MenuHandler menuHandler = new MenuHandler(this,id);
        menuHandler.getActionMenuHandler();

        return super.onOptionsItemSelected(item);
    }
}
