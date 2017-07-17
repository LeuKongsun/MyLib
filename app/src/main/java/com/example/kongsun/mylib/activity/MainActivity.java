package com.example.kongsun.mylib.activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.db.User;
import com.example.kongsun.mylib.fragment.AboutUs;
import com.example.kongsun.mylib.fragment.FavoriteFragment;
import com.example.kongsun.mylib.fragment.SignupFragment;
import com.facebook.Profile;
import com.facebook.login.LoginManager;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tlb_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("E-library");

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        TextView txtUsername = (TextView) headerView.findViewById(R.id.txt_Username);
        NetworkImageView imgProfile = (NetworkImageView) headerView.findViewById(R.id.img_profile);

        /*if (Myapp.getInstance(this).getLoginMethod() == Myapp.LOGIN_METHOD_USERNAME_PASSWORD) {
            User currentUser = Myapp.getInstance(this).getCurrentUser();
            txtUsername.setText(currentUser.getFirstname() + currentUser.getLastname());
        } else {
            Profile profile = Profile.getCurrentProfile();
            txtUsername.setText(profile.getName());
            String profileImageUrl = profile.getProfilePictureUri(230, 230).toString();
            imgProfile.setImageUrl(profileImageUrl, Myapp.getInstance(this).getImageLoader());
        }
*/
        TextView txtSignOut = (TextView) headerView.findViewById(R.id.txt_signOut);
        txtSignOut.setOnClickListener(this);
        onHomeClick();
    }
    public void onClick(View view) {
        if (view.getId() == R.id.txt_signOut) {
            if (Myapp.getInstance(this).getLoginMethod() == Myapp.LOGIN_METHOD_USERNAME_PASSWORD) {
                Log.d("e-library", "Logout from username/password");
                SharedPreferences preferences = getSharedPreferences(LoginActivity.PREFERENCE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove(LoginActivity.KEY_USERNAME);
                editor.apply();
            } else {
                Log.d("e-library", "Logout from Facebook");
                LoginManager.getInstance().logOut();
            }
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.nv_home:
                getSupportActionBar().setTitle("Home");
                onHomeClick();
                break;
            case R.id.nv_favorite:
                onFavoriteClick();
                break;
            case R.id.nv_account:
                getSupportActionBar().setTitle("Account");
                onAccountClick();
                break;
            case R.id.nv_aboutus:
                onAboutUsClick();
                break;
        }
        return true;
    }

    private void onFavoriteClick() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        fragmentTransaction.replace(R.id.layout_content, favoriteFragment);
        fragmentTransaction.commit();

    }


    private void onAboutUsClick() {
        /*Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);*/
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AboutUs aboutUsFragement = new AboutUs();
        fragmentTransaction.replace(R.id.layout_content, aboutUsFragement);
        fragmentTransaction.commit();
    }

    private void onHomeClick() {
        /*Intent intent = new Intent(this,HomeFragment.class);
        startActivity(intent);*/

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NestedRecyclerView homeFragment = new NestedRecyclerView();
        fragmentTransaction.replace(R.id.layout_content, homeFragment);
        fragmentTransaction.commit();
    }

    private void onAccountClick() {
        /*Intent intent = new Intent(this,FragmentActivity.class);
        startActivity(intent);*/
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignupFragment signupFragment = new SignupFragment();
        fragmentTransaction.replace(R.id.layout_content, signupFragment);
        fragmentTransaction.commit();
    }
}
