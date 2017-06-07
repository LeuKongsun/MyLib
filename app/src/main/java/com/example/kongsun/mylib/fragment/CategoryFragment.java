package com.example.kongsun.mylib.fragment;

import android.app.Fragment;
import android.os.Bundle;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_row_list, container, false);
        //Reference to recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.category_recycler);

        //layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Category category = new Category(0, "Hello World");

        //Store Data in DatabaseManager
        DatabaseManager databaseManager = new DatabaseManager(getActivity());
        databaseManager.insertCategory(category);
        Category[] categories = databaseManager.getAllCategories();

        //Adapter
        CategoryAdapter adapter = new CategoryAdapter();
        adapter.setCategories(categories);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
