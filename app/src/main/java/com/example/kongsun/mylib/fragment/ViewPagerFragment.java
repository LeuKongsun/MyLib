package com.example.kongsun.mylib.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kongsun.mylib.R;

/**
 * Created by kongsun on 7/22/17.
 */

public class ViewPagerFragment extends Fragment{
    private SelectionsPagerAdapter mSelectionsPagerAdpater;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pagger,container,false);
        ViewPager pager = (ViewPager) view.findViewById(R.id.container);
        pager.setAdapter(new SelectionsPagerAdapter(getFragmentManager()));
        return view;
    }

    public class SelectionsPagerAdapter extends FragmentPagerAdapter
    {

        public SelectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }
    }
}
