package com.example.kongsun.mylib.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.adapter.CategoryAdapter;
import com.example.kongsun.mylib.dataset.Category;
import com.example.kongsun.mylib.db.DatabaseManager;

/**
 * Created by kongsun on 6/7/17.
 */

public class CategoryFragment extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        // Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DatabaseManager dbManager = new DatabaseManager(getActivity());
        Category[] articles = dbManager.getAllCategories();

        // Adapter
        CategoryAdapter adapter = new CategoryAdapter();
        adapter.setCategories(articles);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
