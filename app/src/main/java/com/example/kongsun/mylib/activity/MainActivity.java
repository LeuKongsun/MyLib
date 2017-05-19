package com.example.kongsun.mylib.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.fragment.Category_fragement;
import com.example.kongsun.mylib.fragment.Recently_fragement;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tlb_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("E-Library");

        NavigationView navigationView=(NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout =(DrawerLayout) findViewById(R.id.drawer_main);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        switch (item.getItemId()){
            case R.id.nv_category:
                onCategoryClick();
                break;
            case R.id.nv_account:
                onAccountClick();
                break;
        }
        return true;
    }

    private void onRecentlyClick() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Recently_fragement recently_fragement = new Recently_fragement();
        fragmentTransaction.replace(R.id.layout_content,recently_fragement);
        fragmentTransaction.commit();
    }

    private void onAccountClick() {
        Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);
    }

    private void onCategoryClick() {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
        /*FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Category_fragement category_fragement = new Category_fragement();
        fragmentTransaction.replace(R.id.layout_content,category_fragement);
        fragmentTransaction.commit();*/
    }
}
