package com.example.kongsun.mylib.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.kongsun.mylib.R;

/**
 * Created by kongsun on 6/21/17.
 */

class BookActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        String title = getIntent().getStringExtra("title");
        Log.d("e-library", "Article detail: " + title);
    }
}
