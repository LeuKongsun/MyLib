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
import android.view.View;
import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.fragment.AllBookfragment;
import com.example.kongsun.mylib.fragment.NewFragment;
import com.example.kongsun.mylib.fragment.PopularFragment;
import com.example.kongsun.mylib.fragment.SignupFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tlb_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("E-Library");

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nv_all:
                onAllClick();
                break;
            case R.id.nv_new:
                onNewClick();
                break;
            case R.id.nv_popular:
                onPopularClick();
                break;
        }
    }

    private void onPopularClick() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PopularFragment popularFragment = new PopularFragment();
        fragmentTransaction.replace(R.id.nested_fragment, popularFragment);
        fragmentTransaction.commit();
    }

    private void onNewClick() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NewFragment newFragment = new NewFragment();
        fragmentTransaction.replace(R.id.nested_fragment, newFragment);
        fragmentTransaction.commit();
    }

    private void onAllClick() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AllBookfragment allBookfragment = new AllBookfragment();
        fragmentTransaction.replace(R.id.nested_fragment, allBookfragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.nv_home:
                getSupportActionBar().setTitle("Home");
                onHomeClick();
                break;
            case R.id.nv_category:
                getSupportActionBar().setTitle("Categories");
                onCategoryClick();
                break;
            case R.id.nv_account:
                getSupportActionBar().setTitle("Account");
                onAccountClick();
                break;
            case R.id.nv_aboutus:
                onAboutUsClick();
                break;
            case R.id.nv_favorite:
                onFavoriteClick();
        }
        return true;
    }

    private void onFavoriteClick() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AllBookfragment allBookfragment = new AllBookfragment();
        fragmentTransaction.replace(R.id.layout_content, allBookfragment);
        fragmentTransaction.commit();
    }

    private void onAboutUsClick() {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

    private void onHomeClick() {
        Intent intent = new Intent(this,BookNestedRCViewActivity.class);
        startActivity(intent);
        /*
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.layout_content, homeFragment);
        fragmentTransaction.commit();*/
    }

    private void onAccountClick() {
        /*Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);*/
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignupFragment signupFragment = new SignupFragment();
        fragmentTransaction.replace(R.id.layout_content, signupFragment);
        fragmentTransaction.commit();
    }

    private void onCategoryClick() {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
        /*FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CategoryFragment categoryFragment = new CategoryFragment();
        fragmentTransaction.replace(R.id.layout_content,categoryFragment);
        fragmentTransaction.commit();*/
    }
}
