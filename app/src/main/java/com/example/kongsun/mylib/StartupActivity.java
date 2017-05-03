package com.example.kongsun.mylib;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startLoginActivity();
            }
        },2000);
    }

    private void startLoginActivity() {
        Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);
    }
}
