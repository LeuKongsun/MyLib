package com.example.kongsun.mylib.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kongsun.mylib.R;

/**
 * Created by kongsun on 5/16/17.
 */

public class Recently_fragement extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_row_list, container, false);
    }
}
