package com.example.kongsun.mylib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongsun on 5/3/17.
 */

public class BookDataActivity extends AppCompatActivity {
    //private List<Book> books = new ArrayList<>();
    private RecyclerView recyclerView;
    //private BookAdapter bookAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tlb_book);
    }
}
