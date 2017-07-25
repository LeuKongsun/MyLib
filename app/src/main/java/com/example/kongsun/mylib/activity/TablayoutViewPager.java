package com.example.kongsun.mylib.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.fragment.Entertainment;

/**
 * Created by kongsun on 7/22/17.
 */

public class TablayoutViewPager extends AppCompatActivity {
    private SelectionsPagerAdapter mSelectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_pagger);

        mSelectionsPagerAdapter = new SelectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSelectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }
    public class SelectionsPagerAdapter extends FragmentPagerAdapter
    {

        public SelectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
            }

            return null;
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Entertainment";
                case 2:
                    return "Education";
                case 3:
                    return "Science";
                case 4:
                    return "Programming";
                case 5:
                    return "Soft Skill";
                case 6:
                    return "Other";
            }
            return null;
        }
    }
}
