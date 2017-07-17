package com.example.kongsun.mylib.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.adapter.CategoryAdapter;
import com.example.kongsun.mylib.adapter.OnRecyclerViewItemClickListener;
import com.example.kongsun.mylib.dataset.Category;
import com.example.kongsun.mylib.db.DatabaseManager;

/**
 * Created by kongsun on 5/3/17.
 */

public class CategoryActivity extends AppCompatActivity implements OnRecyclerViewItemClickListener{
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setToolbar and Title
        getSupportActionBar().setTitle("Category");

        //Put Recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        //LayoutManager
        RecyclerView.LayoutManager caLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(caLayoutManager);

        //SetLine
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //temporary data
        Category category = new Category(0,"Hello World");

        //Store Data in DatabaseManager
        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.insertCategory(category);
        Category[] categories = databaseManager.getAllCategories();

        //Adapter
        CategoryAdapter adapter = new CategoryAdapter();
        adapter.setCategories(categories);
        recyclerView.setAdapter(adapter);
        //prepareCategoryData();

    }


    /*private void prepareCategoryData() {
        Category category = new Category("Entertainment");
        categories.add(category);
        category = new Category("Novel");
        categories.add(category);
        category = new Category("Business");
        categories.add(category);
        category = new Category("Education");
        categories.add(category);
        category = new Category("Kid Tales");
        categories.add(category);
        category = new Category("Technology");
        categories.add(category);
        category = new Category("Self-Study");
        categories.add(category);
        category = new Category("Tour");
        categories.add(category);
        category = new Category("Other");
        categories.add(category);
    }*/

    @Override
    public void onRecyclerViewItemClickListener(int position) {
        //for(int i=0 ; i<10 ;i++)
        if (position==0) {
            Toast.makeText(getApplicationContext(), "Click first Successful "+position, Toast.LENGTH_SHORT).show();
        }/*
        if (position==1) {
            Toast.makeText(getApplicationContext(), "Click second Successful "+position, Toast.LENGTH_SHORT).show();
        }
        if (position==2) {
            Toast.makeText(getApplicationContext(), "Click third Successful "+position, Toast.LENGTH_SHORT).show();
        }*/
    }
}
